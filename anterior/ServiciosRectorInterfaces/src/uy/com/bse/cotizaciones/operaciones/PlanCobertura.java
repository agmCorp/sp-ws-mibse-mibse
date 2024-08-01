package uy.com.bse.cotizaciones.operaciones;

import java.io.Serializable;
import java.util.ArrayList;

public class PlanCobertura implements Serializable{
	private String codigo;
	private String descripcion;
	private Double montoPrima;
	private Double montoPremio;
	private String errorPlanCob;
	private Double premioFacturacion;
	private String excluido;
	private Double montoDeducible;
	
	private ArrayList <PlanPago> planesPago = new ArrayList<PlanPago>();
	
	
	public Double getMontoPrima() {
		return montoPrima;
	}
	public void setMontoPrima(Double montoPrima) {
		this.montoPrima = montoPrima;
	}
	public Double getMontoPremio() {
		return montoPremio;
	}
	public void setMontoPremio(Double montoPremio) {
		this.montoPremio = montoPremio;
	}
	public String getErrorPlanCob() {
		return errorPlanCob;
	}
	public void setErrorPlanCob(String errorPlanCob) {
		this.errorPlanCob = errorPlanCob;
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
	
	public void setUnPlanPago(PlanPago planPago){
		planesPago.add(planPago);	
	}
	public Double getPremioFacturacion() {
		return premioFacturacion;
	}
	public void setPremioFacturacion(Double premioFacturacion) {
		this.premioFacturacion = premioFacturacion;
	}
	public String getExcluido() {
		return excluido;
	}
	public void setExcluido(String excluido) {
		this.excluido = excluido;
	}
	public ArrayList<PlanPago> getPlanesPago() {
		return planesPago;
	}
	public void setPlanesPago(ArrayList<PlanPago> planesPago) {
		this.planesPago = planesPago;
	}
	public Double getMontoDeducible() {
		return montoDeducible;
	}
	public void setMontoDeducible(Double montoDeducible) {
		this.montoDeducible = montoDeducible;
	}
		
}
