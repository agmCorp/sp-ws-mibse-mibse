package uy.com.bse.cotizadores.pymes;

import java.io.Serializable;

public class Modulo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer codModulo;
    private String descModulo;
    private Double montoModulo;
    
    
	public Integer getCodModulo() {
		return codModulo;
	}
	public void setCodModulo(Integer codModulo) {
		this.codModulo = codModulo;
	}
	public String getDescModulo() {
		return descModulo;
	}
	public void setDescModulo(String descModulo) {
		this.descModulo = descModulo;
	}
	public Double getMontoModulo() {
		return montoModulo;
	}
	public void setMontoModulo(Double montoModulo) {
		this.montoModulo = montoModulo;
	}

}
