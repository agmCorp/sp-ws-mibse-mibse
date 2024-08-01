package uy.com.bse.cotizadores.pymes;

import java.io.Serializable;

public class ModuloCobertura implements Serializable{
	
	private static final long serialVersionUID = -1859825758887445763L;
	private Integer codModCob;
	private String descModCob;
	private String montoSumaAsegurada;
	
	public Integer getCodModCob() {
		return codModCob;
	}
	public void setCodModCob(Integer codModCob) {
		this.codModCob = codModCob;
	}
	public String getDescModCob() {
		return descModCob;
	}
	public void setDescModCob(String descModCob) {
		this.descModCob = descModCob;
	}
	public String getMontoSumaAsegurada() {
		return montoSumaAsegurada;
	}
	public void setMontoSumaAsegurada(String montoSumaAsegurada) {
		this.montoSumaAsegurada = montoSumaAsegurada;
	}
	
	
	


}
