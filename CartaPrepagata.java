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
	}

	/**
	 * Carica un importo sulla carta. Se l’importo non è in euro o se la ricarica
	 * è tale da far superare il plafond, solleva un’eccezione.
	 */
	public boolean ricarica(Importo i, String pin) {

	}

	/** Restituisce l’importo disponibile sulla carta. */
	public ImportoInEuro getResiduo(String pin) {

	}

	/**
	 * Restituisce la lista delle operazioni fatte con la carta, prima le
	 * ricariche e poi gli addebiti, in ordine crescente di importo.
	 */
	public Importo[] getMovimentiOrdinati() {
		// afaik `Ricarica` ed `Addebito` estendono `Operazione`, non `Importo`..
		// "restituisce la lista delle operazioni" -> public Importo[]... why?
	}

	/** Restituisce una rappresentazione dello stato dell’oggetto. */
	@Override
	public String toString() {

	}
}
