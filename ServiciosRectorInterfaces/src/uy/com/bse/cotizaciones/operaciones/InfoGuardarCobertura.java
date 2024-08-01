package uy.com.bse.cotizaciones.operaciones;

import java.io.Serializable;
import java.util.ArrayList;

public class InfoGuardarCobertura implements Serializable{
	
	private ArrayList <PlanCobertura> planesCobertura = new ArrayList<PlanCobertura>();
	private String tieneFranquicia;
	private ArrayList<BienCobertura> bienes = new ArrayList<BienCobertura>();
	private String habilitoInsertar;
	
	
	
	public ArrayList<PlanCobertura> getPlanesCobertura() {
		return planesCobertura;
	}
	public void setPlanesCobertura(ArrayList<PlanCobertura> planesCobertura) {
		this.planesCobertura = planesCobertura;
	}
	public String getTieneFranquicia() {
		return tieneFranquicia;
	}
	public void setTieneFranquicia(String tieneFranquicia) {
		this.tieneFranquicia = tieneFranquicia;
	}
	public ArrayList<BienCobertura> getBienes() {
		return bienes;
	}
	public void setBienes(ArrayList<BienCobertura> bienes) {
		this.bienes = bienes;
	}
	
	public void setUnElemento(PlanCobertura item){
		this.planesCobertura.add(item);
	}
	public void setUnElemento(BienCobertura item){
		this.bienes.add(item);
	}
	public String getHabilitoInsertar() {
		return habilitoInsertar;
	}
	public void setHabilitoInsertar(String habilitoInsertar) {
		this.habilitoInsertar = habilitoInsertar;
	}
	
}
