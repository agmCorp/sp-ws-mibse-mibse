package uy.com.bse.mibse;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamProCarta2 extends ParamGenerico {
	private static final long serialVersionUID = 73066894956394284L;
	
	private Integer ramo;
	private String poliza;
	private Integer certificado;
	private String cdCarta;

	public Integer getRamo() {
		return ramo;
	}

	public void setRamo(Integer ramo) {
		this.ramo = ramo;
	}

	public String getPoliza() {
		return poliza;
	}

	public void setPoliza(String poliza) {
		this.poliza = poliza;
	}

	public Integer getCertificado() {
		return certificado;
	}

	public void setCertificado(Integer certificado) {
		this.certificado = certificado;
	}

	public String getCdCarta() {
		return cdCarta;
	}

	public void setCdCarta(String cdCarta) {
		this.cdCarta = cdCarta;
	}
}
