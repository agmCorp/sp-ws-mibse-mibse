package uy.com.bse.mibse;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamCorrespondeCartaPoliza extends ParamGenerico {
	private static final long serialVersionUID = 220193336732033533L;
	
	private Integer sucursal;
	private Integer ramo;
	private Integer poliza;
	private Integer certificado;
	private String cdCarta;

	public Integer getSucursal() {
		return sucursal;
	}

	public void setSucursal(Integer sucursal) {
		this.sucursal = sucursal;
	}

	public Integer getRamo() {
		return ramo;
	}

	public void setRamo(Integer ramo) {
		this.ramo = ramo;
	}

	public Integer getPoliza() {
		return poliza;
	}

	public void setPoliza(Integer poliza) {
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
