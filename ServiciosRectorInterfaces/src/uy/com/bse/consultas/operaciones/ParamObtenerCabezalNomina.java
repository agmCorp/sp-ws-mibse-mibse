package uy.com.bse.consultas.operaciones;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamObtenerCabezalNomina extends ParamGenerico {

	private static final long serialVersionUID = 3445420378005612839L;
	
	private Integer numNomina;
    private Integer numIntegrante;
    
	public Integer getNumNomina() {
		return numNomina;
	}
	public void setNumNomina(Integer numNomina) {
		this.numNomina = numNomina;
	}
	public Integer getNumIntegrante() {
		return numIntegrante;
	}
	public void setNumIntegrante(Integer numIntegrante) {
		this.numIntegrante = numIntegrante;
	}
	
	
}
