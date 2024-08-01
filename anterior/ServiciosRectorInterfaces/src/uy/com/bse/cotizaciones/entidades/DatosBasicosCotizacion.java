package uy.com.bse.cotizaciones.entidades;

import java.io.Serializable;

public class DatosBasicosCotizacion implements Serializable{

	private static final long serialVersionUID = -5048877420211885638L;
	private String fechaEmision;
	private Integer productorCod;
	private String productorDesc;
	private Integer monedaCod;
	private String monedaDesc;
	private String promocionCod;
	private String promocionDesc;
	private String modoCalculoCod;
	private String modoCalculoDesc;
	private String renovacionCod;
	private String renovacionDesc;
	private Integer codBroker;
	private String descBroker;
	
	public String getFechaEmision() {
		return fechaEmision;
	}
	public void setFechaEmision(String fechaEmision) {
		this.fechaEmision = fechaEmision;
	}
	public Integer getProductorCod() {
		return productorCod;
	}
	public void setProductorCod(Integer productorCod) {
		this.productorCod = productorCod;
	}
	public String getProductorDesc() {
		return productorDesc;
	}
	public void setProductorDesc(String productorDesc) {
		this.productorDesc = productorDesc;
	}
	public Integer getMonedaCod() {
		return monedaCod;
	}
	public void setMonedaCod(Integer monedaCod) {
		this.monedaCod = monedaCod;
	}
	public String getMonedaDesc() {
		return monedaDesc;
	}
	public void setMonedaDesc(String monedaDesc) {
		this.monedaDesc = monedaDesc;
	}
	public String getPromocionCod() {
		return promocionCod;
	}
	public void setPromocionCod(String promocionCod) {
		this.promocionCod = promocionCod;
	}
	public String getPromocionDesc() {
		return promocionDesc;
	}
	public void setPromocionDesc(String promocionDesc) {
		this.promocionDesc = promocionDesc;
	}
	public String getModoCalculoCod() {
		return modoCalculoCod;
	}
	public void setModoCalculoCod(String modoCalculoCod) {
		this.modoCalculoCod = modoCalculoCod;
	}
	public String getModoCalculoDesc() {
		return modoCalculoDesc;
	}
	public void setModoCalculoDesc(String modoCalculoDesc) {
		this.modoCalculoDesc = modoCalculoDesc;
	}
	public String getRenovacionCod() {
		return renovacionCod;
	}
	public void setRenovacionCod(String renovacionCod) {
		this.renovacionCod = renovacionCod;
	}
	public String getRenovacionDesc() {
		return renovacionDesc;
	}
	public void setRenovacionDesc(String renovacionDesc) {
		this.renovacionDesc = renovacionDesc;
	}
	public Integer getCodBroker() {
		return codBroker;
	}
	public void setCodBroker(Integer codBroker) {
		this.codBroker = codBroker;
	}
	public String getDescBroker() {
		return descBroker;
	}
	public void setDescBroker(String descBroker) {
		this.descBroker = descBroker;
	}
	
}
