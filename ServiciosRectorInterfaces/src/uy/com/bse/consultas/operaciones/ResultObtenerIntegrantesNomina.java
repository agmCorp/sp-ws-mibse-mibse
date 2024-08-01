package uy.com.bse.consultas.operaciones;

import uy.com.bse.consultas.entidades.IntegranteNomina;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerIntegrantesNomina extends ResultGenerico{

	private static final long serialVersionUID = 1053006948145293687L;
	
	private IntegranteNomina integrante;

	public IntegranteNomina getIntegrante() {
		return integrante;
	}

	public void setIntegrante(IntegranteNomina integrante) {
		this.integrante = integrante;
	}
	
	
}
