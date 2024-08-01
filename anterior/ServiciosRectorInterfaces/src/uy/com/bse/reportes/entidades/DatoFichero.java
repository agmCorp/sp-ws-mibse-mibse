package uy.com.bse.reportes.entidades;

import java.io.Serializable;

public class DatoFichero implements Serializable{	
	
	private static final long serialVersionUID = 4582531454526606172L;
	
	private String nombre;
	private String extension;
	private String uri;
	private Long ultimaModificacion;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getExtension() {
		return extension;
	}
	public void setExtension(String extension) {
		this.extension = extension;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public Long getUltimaModificacion() {
		return ultimaModificacion;
	}
	public void setUltimaModificacion(Long ultimaModificacion) {
		this.ultimaModificacion = ultimaModificacion;
	}

}
