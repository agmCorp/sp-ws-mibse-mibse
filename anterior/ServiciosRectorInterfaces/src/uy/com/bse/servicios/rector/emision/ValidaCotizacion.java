package uy.com.bse.servicios.rector.emision;

import java.io.Serializable;
import java.util.ArrayList;

public class ValidaCotizacion implements Serializable{
	private ArrayList <String> fueraDePauta;
	private Boolean cotizacionValida;
	private Integer codError;
	private String descError;
	private Integer planPago;	
	
	public Boolean getCotizacionValida() {
		return cotizacionValida;
	}

	public void setCotizacionValida(Boolean cotizacionValida) {
		this.cotizacionValida = cotizacionValida;
	}

	

	

	public Integer getCodError() {
		return codError;
	}

	public void setCodError(Integer codError) {
		this.codError = codError;
	}

	public String getDescripcion() {
		return descError;
	}

	public void setDescripcion(String descripcion) {
		this.descError = descripcion;
	}

	public Integer getPlanPago() {
		return planPago;
	}

	public void setPlanPago(Integer planPago) {
		this.planPago = planPago;
	}

	public ValidaCotizacion(){
		this.fueraDePauta = new ArrayList <String>();
	}
	
	public ArrayList<String> getFueraDePauta() {
		return fueraDePauta;
	}

	public void setFueraDePauta(ArrayList<String> fueraDePauta) {
		this.fueraDePauta = fueraDePauta;
	}
	
	public void setUnFueraDePauta(String fuera){
		this.fueraDePauta.add(fuera);
	}
}
