package uy.com.bse.cotizaciones.consultas;

import java.io.Serializable;

public class BonificacionCotizacion implements Serializable{
	private Integer  numCertificado;
	private String clausula;
	
	public Integer getNumCertificado() {
		return numCertificado;
	}
	public void setNumCertificado(Integer numCertificado) {
		this.numCertificado = numCertificado;
	}
	public String getClausula() {
		return clausula;
	}
	public void setClausula(String clausula) {
		this.clausula = clausula;
	}

}
