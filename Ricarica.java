
/** Sottoclasse di Operazione che rappresenta ricariche.
 *
 * Sottoclasse concreta di Operazione, rappresenta ricariche.
 */
public class Ricarica extends Operazione {
	// in questo caso "opportuni override" sfuggono ad umana comprensione

	public Ricarica(Importo i) {
		super(i);
	}

	@Override
	public String toString() {
		return "Ricarica[" + this.getImporto() + "]";
	}
}
