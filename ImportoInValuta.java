
/**
 * Sottoclasse concreta di Importo, rappresenta solo importi in valuta diversa
 * da euro. Ha come attributi anche il nome della valuta (String) e il tasso
 * di cambio dalla valuta a euro (double).
 */
public class ImportoInValuta extends Importo {
	private String nome;
	private double tasso;

	public ImportoInValuta(String nome, double tasso, int val, int cent) {
		super(val, cent);
		this.nome = nome;
		this.tasso = tasso;
	}

	// "opportuni override": solo i metodi astratti (`getImportoInEuro`)
	@Override
	public ImportoInEuro getImportoInEuro() {
		// in teoria  `1 (in valuta) = tasso (in €)` quindi:
		//   valInEuro = valInValuta * tasso    (o almeno lo spero)
		return new ImportoInEuro(0, (int) Math.round(
			this.getValoreInCentesimi() * this.tasso));
	}

	@Override
	public String toString() {
		return super.toString().replaceFirst("\\)",
				" " + nome + ", tasso: " + tasso + " " + nome + "/€)");
	}
}
