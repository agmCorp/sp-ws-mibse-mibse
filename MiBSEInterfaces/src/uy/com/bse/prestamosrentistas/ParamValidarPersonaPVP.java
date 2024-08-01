package uy.com.bse.prestamosrentistas;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamValidarPersonaPVP extends ParamGenerico {
	private static final long serialVersionUID = -2504446730171728408L;
	
	private String paisDoc;
	private String tipoDoc;
	private String nuDocumento;

	public String getPaisDoc() {
		return paisDoc;
	}

	public void setPaisDoc(String paisDoc) {
		this.paisDoc = paisDoc;
	}

	public String getTipoDoc() {
		return tipoDoc;
	}

	public void setTipoDoc(String tipoDoc) {
		this.tipoDoc = tipoDoc;
	}

	public String getNuDocumento() {
		return nuDocumento;
	}

	public void setNuDocumento(String nuDocumento) {
		this.nuDocumento = nuDocumento;
	}
}
