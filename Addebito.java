
/**
 * Sottoclasse di Operazione che rappresenta addebiti.
 */
public class Addebito extends Operazione {
	// come in `Ricarica` non ho idea di cosa si intenda per "opportuni override"

	public Addebito(Importo i) {
		super(i);
	}

	@Override
	public String toString() {
		return "Addebito[" + this.getImporto() + "]";
	}
}
