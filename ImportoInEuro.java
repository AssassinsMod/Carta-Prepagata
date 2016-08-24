
/**
 * Sottoclasse concreta di Importo, rappresenta solo importi in euro.
 */
public class ImportoInEuro extends Importo {
	// qui passiamo agli "opportuni override":
	//  - metodi astratti che vanno implementati (`getImportoInEuro`)
	//  - altri metodi di cui voglio fare l'override (`somma` e `sottrai`)

	public ImportoInEuro(int val, int cent) {
		super(val, cent);
	}

	@Override
	public ImportoInEuro getImportoInEuro() {
		return this;
	}

	@Override
	public ImportoInEuro somma(Importo i) {
		return new ImportoInEuro(0, this.getValoreInCentesimi() +
			i.getImportoInEuro().getValoreInCentesimi());
	}

	@Override
	public ImportoInEuro sottrai(Importo i) {
		return new ImportoInEuro(0, this.getValoreInCentesimi() -
			i.getImportoInEuro().getValoreInCentesimi());
	}

	@Override
	public String toString() {
		return super.toString().replaceFirst("\\)", "€)");
	}
}
