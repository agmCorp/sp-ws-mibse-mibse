package uy.com.bse.polizas.operaciones;

import java.io.Serializable;

public class ValidacionGenerica implements Serializable{
	private String mensaje;
	private String operacionAInvocar;
	private Integer numCotizacion;
	
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public String getOperacionAInvocar() {
		return operacionAInvocar;
	}
	public void setOperacionAInvocar(String operacionAInvocar) {
		this.operacionAInvocar = operacionAInvocar;
	}
	public Integer getNumCotizacion() {
		return numCotizacion;
	}
	public void setNumCotizacion(Integer numCotizacion) {
		this.numCotizacion = numCotizacion;
	}

	
}
