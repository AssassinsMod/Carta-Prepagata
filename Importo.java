
/** Rappresenta un generico importo. (Yoda, sei tu?)
 * 
 * Classe astratta, rappresenta un generico importo costituito da due int,
 * valuta e centesimi della valuta (es. euro e eurocent). Deve implementare
 * l’interfaccia Comparable (ordine crescente di valore in euro).
 */
public abstract class Importo implements Comparable<Importo> {
	// "costituito da due int", ma tanto il valore viene restituito sempre
	// 'InCentesimi' e quindi che ci frega di tenere separata la parte intera?
	private int valore;

	/** 
	 * Costruttore che accetta come parametri la parte intera e la parte in
	 * centesimi dell’importo.
	 */
	public Importo(int valuta, int centesimi) {
		// parte "intera" e "in centesimi", dunque indipendentemente dalla valuta:
		this.valore = valuta * 100 + centesimi;
	}

	/** Restituisce il valore in centesimi dell’importo. */
	public int getValoreInCentesimi() {
		return this.valore;
	}

	/** Restituisce l’importo in euro equivalente all’importo. */
	public abstract ImportoInEuro getImportoInEuro();

	/** Restituisce l’importo in euro equivalente alla somma dei due importi. */
	public ImportoInEuro somma(Importo i) {
		// la "vera" implementazione risiederà in `ImportoInEuro`
		return this.getImportoInEuro().somma(i);
	}

	/**
	 * Restituisce l’importo in euro equivalente alla differenza dei due importi
	 */
	public ImportoInEuro sottrai(Importo i) {
		// come `somma`
		return this.getImportoInEuro().sottrai(i);
	}

	@Override
	public int compareTo(Importo i) {
		// i confronti vanno fatti nella stessa valuta, quindi si passa in euro:
		return this.getImportoInEuro().getValoreInCentesimi()
		         - i.getImportoInEuro().getValoreInCentesimi();
	}

	/** Restituisce una rappresentazione dello stato dell'oggetto. */
	@Override
	public String toString() {
		return "Importo(" +
			getValoreInCentesimi() % 100 + "," +
			getValoreInCentesimi() / 100 + ")";
	}
}
