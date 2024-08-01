package uy.com.bse.polizas.entidades;

import java.io.Serializable;

public class Preliquidacion implements Serializable{
	private Integer codPreliquidacion;
	private String fechaPreliquidacion;
	private Double importe;
	
	public Integer getCodPreliquidacion() {
		return codPreliquidacion;
	}
	public void setCodPreliquidacion(Integer codPreliquidacion) {
		this.codPreliquidacion = codPreliquidacion;
	}
	public String getFechaPreliquidacion() {
		return fechaPreliquidacion;
	}
	public void setFechaPreliquidacion(String fechaPreliquidacion) {
		this.fechaPreliquidacion = fechaPreliquidacion;
	}
	public Double getImporte() {
		return importe;
	}
	public void setImporte(Double importe) {
		this.importe = importe;
	}
	
}
