package uy.com.bse.servicios.rector.interfaces;

import java.io.Serializable;

public class Documento implements Serializable{
	private String tipoDocumento;
	private String descDocumento;
	
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public String getDescDocumento() {
		return descDocumento;
	}
	public void setDescDocumento(String descDocumento) {
		this.descDocumento = descDocumento;
	}
}
