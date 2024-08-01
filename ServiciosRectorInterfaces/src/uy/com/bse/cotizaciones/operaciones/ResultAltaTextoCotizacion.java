package uy.com.bse.cotizaciones.operaciones;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultAltaTextoCotizacion extends ResultGenerico{
	private Integer numConsecutivoTexto;

	public Integer getNumConsecutivoTexto() {
		return numConsecutivoTexto;
	}

	public void setNumConsecutivoTexto(Integer numConsecutivoTexto) {
		this.numConsecutivoTexto = numConsecutivoTexto;
	}	

}
