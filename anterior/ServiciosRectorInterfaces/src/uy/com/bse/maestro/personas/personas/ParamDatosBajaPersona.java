package uy.com.bse.maestro.personas.personas;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamDatosBajaPersona extends ParamGenerico{
	private Integer codPersona;
	private String tipoOperacion;
	private String fechaBaja; 
	private String usuario; 
	private Integer codMotivoBaja;
	
	public Integer getCodPersona() {
		return codPersona;
	}
	public void setCodPersona(Integer codPersona) {
		this.codPersona = codPersona;
	}
	public String getTipoOperacion() {
		return tipoOperacion;
	}
	public void setTipoOperacion(String tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}
	public String getFechaBaja() {
		return fechaBaja;
	}
	public void setFechaBaja(String fechaBaja) {
		this.fechaBaja = fechaBaja;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public Integer getCodMotivoBaja() {
		return codMotivoBaja;
	}
	public void setCodMotivoBaja(Integer codMotivoBaja) {
		this.codMotivoBaja = codMotivoBaja;
	}	
}
