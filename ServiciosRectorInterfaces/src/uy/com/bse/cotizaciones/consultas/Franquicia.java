package uy.com.bse.cotizaciones.consultas;

import java.io.Serializable;

public class Franquicia implements Serializable{
	private Integer ramoCod;
	private Integer coberturaCod;
	private String coberturaDesc;
	private Double franquiciaPorc;
	private Double franquiciaMonto;
	private Double deduciblePorc;
	private Double deducibleMonto;
	private Integer bienNum;
	private String bienDesc;
	private Integer codFranquicia;
	private String descripcionFranquicia;
	
	public Integer getCodFranquicia() {
		return codFranquicia;
	}
	public void setCodFranquicia(Integer codFranquicia) {
		this.codFranquicia = codFranquicia;
	}
	public String getDescripcionFranquicia() {
		return descripcionFranquicia;
	}
	public void setDescripcionFranquicia(String descripcionFranquicia) {
		this.descripcionFranquicia = descripcionFranquicia;
	}
	public Integer getRamoCod() {
		return ramoCod;
	}
	public void setRamoCod(Integer ramoCod) {
		this.ramoCod = ramoCod;
	}
	public Integer getCoberturaCod() {
		return coberturaCod;
	}
	public void setCoberturaCod(Integer coberturaCod) {
		this.coberturaCod = coberturaCod;
	}
	public String getCoberturaDesc() {
		return coberturaDesc;
	}
	public void setCoberturaDesc(String coberturaDesc) {
		this.coberturaDesc = coberturaDesc;
	}
	public Double getFranquiciaPorc() {
		return franquiciaPorc;
	}
	public void setFranquiciaPorc(Double franquiciaPorc) {
		this.franquiciaPorc = franquiciaPorc;
	}
	public Double getFranquiciaMonto() {
		return franquiciaMonto;
	}
	public void setFranquiciaMonto(Double franquiciaMonto) {
		this.franquiciaMonto = franquiciaMonto;
	}
	public Double getDeduciblePorc() {
		return deduciblePorc;
	}
	public void setDeduciblePorc(Double deduciblePorc) {
		this.deduciblePorc = deduciblePorc;
	}
	public Double getDeducibleMonto() {
		return deducibleMonto;
	}
	public void setDeducibleMonto(Double deducibleMonto) {
		this.deducibleMonto = deducibleMonto;
	}
	public Integer getBienNum() {
		return bienNum;
	}
	public void setBienNum(Integer bienNum) {
		this.bienNum = bienNum;
	}
	public String getBienDesc() {
		return bienDesc;
	}
	public void setBienDesc(String bienDesc) {
		this.bienDesc = bienDesc;
	}
}
