package uy.com.bse.cotizaciones.operaciones;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultValidarDatos extends ResultGenerico{
	private DatosValidos datosValidos;
	private String habilitoInsertar;
	private String codPlanCobertura;

	public DatosValidos getDatosValidos() {
		return datosValidos;
	}

	public void setDatosValidos(DatosValidos datosValidos) {
		this.datosValidos = datosValidos;
	}

	public String getHabilitoInsertar() {
		return habilitoInsertar;
	}

	public void setHabilitoInsertar(String habilitoInsertar) {
		this.habilitoInsertar = habilitoInsertar;
	}

	public String getCodPlanCobertura() {
		return codPlanCobertura;
	}

	public void setCodPlanCobertura(String codPlanCobertura) {
		this.codPlanCobertura = codPlanCobertura;
	}
		
	
}
