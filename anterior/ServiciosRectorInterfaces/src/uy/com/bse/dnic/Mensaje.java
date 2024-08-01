package uy.com.bse.dnic;

import java.io.Serializable;

public class Mensaje implements Serializable {

	private static final long serialVersionUID = -6877400175788918656L;
	private int codMensaje;
    private String descripcion;
    private String datoExtra;
	public int getCodMensaje() {
		return codMensaje;
	}
	public void setCodMensaje(int codMensaje) {
		this.codMensaje = codMensaje;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getDatoExtra() {
		return datoExtra;
	}
	public void setDatoExtra(String datoExtra) {
		this.datoExtra = datoExtra;
	}

}
