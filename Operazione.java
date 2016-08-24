
/** Rappresenta una generica operazione con la carta.
 *
 * Classe astratta, rappresenta una generica operazione sulla carta prepagata,
 * caratterizzata da un importo. Deve implementare l’interfaccia Comparable
 * (prima gli addebiti e poi le ricariche, in ordine crescente di importo)
 */
public abstract class Operazione implements Comparable<Operazione> {
	private Importo importo;

	/** Costruttore che accetta un importo. */
	public Operazione(Importo i) {
		this.importo = i;
	}

	/** Restituisce l'importo dell'operazione. */
	public Importo getImporto() {
		return this.importo;
	}

	/** Restituisce una rappresentazione dello stato dell’oggetto. */
	@Override
	public String toString() {
		return "Operazione[" + getImporto() + "]";
	}

	/** Logica per il sorting (non richiesta nel pdf) */
	@Override
	public int compareTo(Operazione o) {
		return this.getImporto().compareTo(o.getImporto());
	}
}
