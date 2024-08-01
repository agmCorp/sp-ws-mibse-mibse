package uy.com.bse.recuotificacion;

import java.io.Serializable;

public class DatosCertificado implements Serializable {
	private Integer numCentificado;
	private String fechaEndoso;
	public Integer getNumCentificado() {
		return numCentificado;
	}
	public void setNumCentificado(Integer numCentificado) {
		this.numCentificado = numCentificado;
	}
	public String getFechaEndoso() {
		return fechaEndoso;
	}
	public void setFechaEndoso(String fechaEndoso) {
		this.fechaEndoso = fechaEndoso;
	}
}
