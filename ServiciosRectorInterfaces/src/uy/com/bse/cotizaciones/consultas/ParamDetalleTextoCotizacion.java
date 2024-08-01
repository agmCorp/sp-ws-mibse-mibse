package uy.com.bse.cotizaciones.consultas;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamDetalleTextoCotizacion extends ParamGenerico{
	private String numCotizacion;
	private String numCertificado;
	private String numConsecutivoTexto;
	private String codTexto;
	
	public String getNumCotizacion() {
		return numCotizacion;
	}
	public void setNumCotizacion(String numCotizacion) {
		this.numCotizacion = numCotizacion;
	}
	public String getNumCertificado() {
		return numCertificado;
	}
	public void setNumCertificado(String numCertificado) {
		this.numCertificado = numCertificado;
	}
	public String getNumConsecutivoTexto() {
		return numConsecutivoTexto;
	}
	public void setNumConsecutivoTexto(String numConsecutivoTexto) {
		this.numConsecutivoTexto = numConsecutivoTexto;
	}
	public String getCodTexto() {
		return codTexto;
	}
	public void setCodTexto(String codTexto) {
		this.codTexto = codTexto;
	}
	
}
