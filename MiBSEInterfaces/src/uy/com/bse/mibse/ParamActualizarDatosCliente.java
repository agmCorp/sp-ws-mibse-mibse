package uy.com.bse.mibse;

import java.util.ArrayList;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamActualizarDatosCliente extends ParamGenerico {
	
	private static final long serialVersionUID = 8235325601352544915L;
	private String tipoDocumento;
	private String numPersona;
	private String fechaNacimiento;
	private String sexo;
	private String codProfesion;
	
	private ArrayList<DatosComunicacion> comunicaciones = new ArrayList<DatosComunicacion>();
	
	public String getNumPersona() {
		return numPersona;
	}
	public void setNumPersona(String numPersona) {
		this.numPersona = numPersona;
	}
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getCodProfesion() {
		return codProfesion;
	}
	public void setCodProfesion(String codProfesion) {
		this.codProfesion = codProfesion;
	}
	public ArrayList<DatosComunicacion> getComunicaciones() {
		return comunicaciones;
	}
	public void setComunicaciones(ArrayList<DatosComunicacion> comunicaciones) {
		this.comunicaciones = comunicaciones;
	}
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	

}
