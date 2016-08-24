import java.util.*;

/** Rappresenta una carta prepagata.
 *
 * Classe che rappresenta una carta prepagata, caratterizzata dal nome del
 * titolare, dal plafond (l’importo massimo caricabile sulla carta) e dal pin
 * di 5 cifre. Deve tenere traccia dei movimenti (addebiti e ricariche) fatti.
 * Permette al piú tre operazioni con pin errato: ogni volta visualizzerà un
 * messaggio del tipo “Attenzione, pin errato. Hai ancora n tentativi”. Dopo il
 * terzo tenativo con pin errato la carta sarà bloccata e non sarà piú possibile
 * fare operazioni.
 */
public class CartaPrepagata {
	private String titolare;
	private int plafond; // ma sarà un int?
	private String pin;

	private List<Operazione> movimenti; // traccia dei movimenti

	private int tentativi = 3;
	private boolean attiva = true;

	private Importo importo;

	/**
	 * Costruttore che accetta nome del titolare, plafond e pin di 5 cifre.
	 * Solleva un’eccezione per pin non valido o nome nullo.
	 */
	public CartaPrepagata(String nome, int plafond, String pin) {
		if (nome == null || nome.isEmpty()) throw new IllegalArgumentException(
			"Il nome del titolare non può essere nullo!");
		if (pin == null || pin.length() != 5) throw new IllegalArgumentException(
			"Il pin deve essere di 5 cifre!");

		this.titolare = nome;
		this.plafond = plafond;
		this.pin = pin;
	}

	/** Controllo di validità del pin / stato della carta.
	 *
	 * Metodo di comodo (non nella specifica) da usare per fare i controlli di
	 * validità del pin e sullo stato della carta (bloccata/non).
	 */
	private boolean checkPin(String pin) {
		if (!attiva) {
			System.out.println("La carta è bloccata!");
			return false;
		}

		if (!pin.equals(this.pin)) {
			if (--tentativi > 0) {
				System.out.println("Attenzione, pin errato. Hai ancora " +
					tentativi + " tentativi!");
			} else {
				this.attiva = false;
				System.out.println("Numero massimo di tentativi raggiunto. " +
					"La carta è stat bloccata!");
			}

			return false;
		}

		return true;
	}

	/**
	 * Addebita l’importo sulla carta. Solleva un’eccezione se l’importo supera
	 * la ricarica disponibile.
	 */
	public boolean addebita(Importo i, String pin) {
		// essendo `boolean` presumo che debba ritornare il risultato dell'op. di
		// aggiunta all'arraylist (non che sia scritto ¯\_(ツ)_/¯)
		if (!checkPin(pin)) return false;
		if (this.importo.sottrai(i).getValoreInCentesimi() < 0)
			throw new IllegalArgumentException("Credito insufficiente!");

		this.importo = this.importo.sottrai(i);
		return this.movimenti.add(new Addebito(i));
	}

	/**
	 * Carica un importo sulla carta. Se l’importo non è in euro o se la ricarica
	 * è tale da far superare il plafond, solleva un’eccezione.
	 */
	public boolean ricarica(Importo i, String pin) {
		if (!checkPin(pin)) return false;
		if (this.importo.somma(i).getValoreInCentesimi() / 100D > plafond)
			throw new IllegalArgumentException("Raggiunto il credito massimo!");

		this.importo = this.importo.somma(i);
		return this.movimenti.add(new Ricarica(i));
	}

	/** Restituisce l’importo disponibile sulla carta. */
	public ImportoInEuro getResiduo(String pin) {
		if (!checkPin(pin)) return null;
		return this.importo.getImportoInEuro();
	}

	/**
	 * Restituisce la lista delle operazioni fatte con la carta, prima le
	 * ricariche e poi gli addebiti, in ordine crescente di importo.
	 */
	public Importo[] getMovimentiOrdinati() {
		// afaik `Ricarica` ed `Addebito` estendono `Operazione`, non `Importo`..
		// "restituisce la lista delle operazioni" -> public Importo[]... why?

		List<Importo> addebiti = new ArrayList<Importo>();
		List<Importo> ricariche = new ArrayList<Importo>();

		for (Operazione o : this.movimenti) {
			if (o instanceof Addebito) addebiti.add(o.getImporto());
			else if (o instanceof Ricarica) ricariche.add(o.getImporto());
			// ^ l'else sarebbe bastato ma la mia fiducia è assai scarsa
		}

		// sorting "naturale" (viene usato `Importo#compareTo`)
		addebiti.sort(null);
		ricariche.sort(null);

		// preparazione del risultato
		Importo[] operazioni = new Importo[addebiti.size() + ricariche.size()];

		int index = 0;
		for (Importo i : ricariche) operazioni[index++] = i;
		for (Importo i : addebiti) operazioni[index++] = i;

		return operazioni;
	}

	/** Restituisce una rappresentazione dello stato dell’oggetto. */
	@Override
	public String toString() {
		return "Carta[" + titolare + ", " + this.importo + "]";
	}
}
