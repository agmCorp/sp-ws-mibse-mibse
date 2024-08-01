package uy.com.bse.cotizaciones.operaciones;

import java.io.Serializable;
import java.util.ArrayList;

public class PlanPagoCotizacion implements Serializable{
	
	private Integer diaVto;
	private String descMoneda;
	private String simboloMoneda;
	private Integer codNivelComision;
	private String descNivelComision;
	private Integer codNivelComisionBroker;
	private String descNivelComisionBroker;
	private String codigo;
	private String descripcion;
	private Double montoCuota;
	private Double montoTotal;
	
	private ArrayList<Certificado> certificados = new ArrayList<Certificado>();

	public Integer getDiaVto() {
		return diaVto;
	}

	public void setDiaVto(Integer diaVto) {
		this.diaVto = diaVto;
	}

	public String getDescMoneda() {
		return descMoneda;
	}

	public void setDescMoneda(String descMoneda) {
		this.descMoneda = descMoneda;
	}

	public String getSimboloMoneda() {
		return simboloMoneda;
	}

	public void setSimboloMoneda(String simboloMoneda) {
		this.simboloMoneda = simboloMoneda;
	}

	public Integer getCodNivelComision() {
		return codNivelComision;
	}

	public void setCodNivelComision(Integer codNivelComision) {
		this.codNivelComision = codNivelComision;
	}

	public String getDescNivelComision() {
		return descNivelComision;
	}

	public void setDescNivelComision(String descNivelComision) {
		this.descNivelComision = descNivelComision;
	}

	public ArrayList<Certificado> getCertificados() {
		return certificados;
	}

	public void setCertificados(ArrayList<Certificado> certificados) {
		this.certificados = certificados;
	}
	public void setUnCertificado(Certificado item){
		this.certificados.add(item);
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Double getMontoCuota() {
		return montoCuota;
	}

	public void setMontoCuota(Double montoCuota) {
		this.montoCuota = montoCuota;
	}

	public Double getMontoTotal() {
		return montoTotal;
	}

	public void setMontoTotal(Double montoTotal) {
		this.montoTotal = montoTotal;
	}

	public Integer getCodNivelComisionBroker() {
		return codNivelComisionBroker;
	}

	public void setCodNivelComisionBroker(Integer codNivelComisionBroker) {
		this.codNivelComisionBroker = codNivelComisionBroker;
	}

	public String getDescNivelComisionBroker() {
		return descNivelComisionBroker;
	}

	public void setDescNivelComisionBroker(String descNivelComisionBroker) {
		this.descNivelComisionBroker = descNivelComisionBroker;
	}
	
	

}
