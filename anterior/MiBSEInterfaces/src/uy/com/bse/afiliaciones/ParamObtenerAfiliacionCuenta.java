package uy.com.bse.afiliaciones;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamObtenerAfiliacionCuenta extends ParamGenerico {
	private static final long serialVersionUID = -2504446730171728408L;

	private String paisDoc;
	private String tipoDoc;
	private String nuDocumento;
	private String tipoAfiliacion;
	private String monedaAfiliacion;

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

	public String getTipoAfiliacion() {
		return tipoAfiliacion;
	}

	public void setTipoAfiliacion(String tipoAfiliacion) {
		this.tipoAfiliacion = tipoAfiliacion;
	}

	public String getMonedaAfiliacion() {
		return monedaAfiliacion;
	}

	public void setMonedaAfiliacion(String monedaAfiliacion) {
		this.monedaAfiliacion = monedaAfiliacion;
	}
}
