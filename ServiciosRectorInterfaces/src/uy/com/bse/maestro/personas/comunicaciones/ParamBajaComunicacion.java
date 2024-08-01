package uy.com.bse.maestro.personas.comunicaciones;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamBajaComunicacion extends ParamGenerico{
	
	private Integer codPersona;
	private Integer codComunicacion;
	
	public Integer getCodPersona() {
		return codPersona;
	}
	public void setCodPersona(Integer codPersona) {
		this.codPersona = codPersona;
	}
	public Integer getCodComunicacion() {
		return codComunicacion;
	}
	public void setCodComunicacion(Integer codComunicacion) {
		this.codComunicacion = codComunicacion;
	}
		
}
