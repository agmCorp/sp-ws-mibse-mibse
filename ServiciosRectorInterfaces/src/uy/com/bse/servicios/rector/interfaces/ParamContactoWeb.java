package uy.com.bse.servicios.rector.interfaces;

import java.util.ArrayList;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamContactoWeb extends ParamGenerico{
	private String numDocumento;           
	private String apellido;
	private String nombre;	
	private String telefono;
	private String email;
	private ArrayList<Integer> noticia;	
	private Integer numCotizacion;	
	private Integer cotizador;
	
	public ParamContactoWeb(){
		noticia = new ArrayList<Integer>();
	}
	
	public String getNumDocumento() {
		return numDocumento;
	}
	public void setNumDocumento(String numDocumento) {
		this.numDocumento = numDocumento;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public ArrayList<Integer> getNoticia() {
		return noticia;
	}
	public void setNoticia(ArrayList<Integer> noticiasList) {
		this.noticia = noticiasList;
	}
	public Integer getNumCotizacion() {
		return numCotizacion;
	}
	public void setNumCotizacion(Integer numCotizacion) {
		this.numCotizacion = numCotizacion;
	}	
	public Integer getCotizador() {
		return cotizador;
	}
	public void setCotizador(Integer cotizador) {
		this.cotizador = cotizador;
	}	
}
