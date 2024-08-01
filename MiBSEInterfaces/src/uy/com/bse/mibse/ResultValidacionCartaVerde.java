package uy.com.bse.mibse;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultValidacionCartaVerde extends ResultGenerico {
	private static final long serialVersionUID = 7049432740145576061L;
	
	private String sucursal;
	private String endoso;
	private String formulario;
	private String consecutivo;
	private String resultado;

	public String getSucursal() {
		return sucursal;
	}

	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

	public String getEndoso() {
		return endoso;
	}

	public void setEndoso(String endoso) {
		this.endoso = endoso;
	}

	public String getFormulario() {
		return formulario;
	}

	public void setFormulario(String formulario) {
		this.formulario = formulario;
	}

	public String getConsecutivo() {
		return consecutivo;
	}

	public void setConsecutivo(String consecutivo) {
		this.consecutivo = consecutivo;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
}
