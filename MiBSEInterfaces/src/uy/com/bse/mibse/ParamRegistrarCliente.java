package uy.com.bse.mibse;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamRegistrarCliente extends ParamGenerico {

	private static final long serialVersionUID = 8264209137521255944L;

	private String tipoDocumento;
	private String numDocumento;
	private String codigoAdhesion;

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getNumDocumento() {
		return numDocumento;
	}

	public void setNumDocumento(String numDocumento) {
		this.numDocumento = numDocumento;
	}

	public String getCodigoAdhesion() {
		return codigoAdhesion;
	}

	public void setCodigoAdhesion(String codigoAdhesion) {
		this.codigoAdhesion = codigoAdhesion;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	private String contrasena;

}
