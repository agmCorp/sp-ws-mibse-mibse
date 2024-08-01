package uy.com.bse.maestro.personas.consultas;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamObtenerPersonasClientes extends ParamGenerico{
	private String apellidoRazon;
	private String nombre;
	private String codCliente;
	private String rut;
	private String tipoDoc;
	private String numDocumento;
	private String tipoPersona;
	private String todosLosClientes;
	private String tel;
	private String orden; 
	private String codProductor;
	
	
	public String getCodProductor() {
		return codProductor;
	}
	public void setCodProductor(String codProductor) {
		this.codProductor = codProductor;
	}
	public String getApellidoRazon() {
		return apellidoRazon;
	}
	public void setApellidoRazon(String apellidoRazon) {
		this.apellidoRazon = apellidoRazon;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCodCliente() {
		return codCliente;
	}
	public void setCodCliente(String codCliente) {
		this.codCliente = codCliente;
	}
	public String getRut() {
		return rut;
	}
	public void setRut(String rut) {
		this.rut = rut;
	}
	public String getTipoDoc() {
		return tipoDoc;
	}
	public void setTipoDoc(String tipoDoc) {
		this.tipoDoc = tipoDoc;
	}
	public String getNumDocumento() {
		return numDocumento;
	}
	public void setNumDocumento(String numDocumento) {
		this.numDocumento = numDocumento;
	}
	public String getTipoPersona() {
		return tipoPersona;
	}
	public void setTipoPersona(String tipoPersona) {
		this.tipoPersona = tipoPersona;
	}
	
	public String getTodosLosClientes() {
		return todosLosClientes;
	}
	public void setTodosLosClientes(String todosLosClientes) {
		this.todosLosClientes = todosLosClientes;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getOrden() {
		return orden;
	}
	public void setOrden(String orden) {
		this.orden = orden;
	}
	
}
