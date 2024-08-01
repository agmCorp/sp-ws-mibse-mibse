package uy.com.bse.polizas.entidades;

import java.io.Serializable;
import java.util.ArrayList;

public class BienPoliza implements Serializable{

	private static final long serialVersionUID = 7495859381501273547L;
	private Integer codBien;
	private String descripBien;
	private ArrayList<String> listaCesionario = new ArrayList<String>();
	private ArrayList<DatoParametricoPoliza> listaDatosParametrico = new ArrayList<DatoParametricoPoliza>();
	
	public Integer getCodBien() {
		return codBien;
	}
	public void setCodBien(Integer codBien) {
		this.codBien = codBien;
	}
	public String getDescripBien() {
		return descripBien;
	}
	public void setDescripBien(String descripBien) {
		this.descripBien = descripBien;
	}
	public ArrayList<String> getListaCesionario() {
		return listaCesionario;
	}
	public void setListaCesionario(ArrayList<String> listaCesionario) {
		this.listaCesionario = listaCesionario;
	}
	public ArrayList<DatoParametricoPoliza> getListaDatosParametrico() {
		return listaDatosParametrico;
	}
	public void setListaDatosParametrico(
			ArrayList<DatoParametricoPoliza> listaDatosParametrico) {
		this.listaDatosParametrico = listaDatosParametrico;
	}

	public void setUnString(String item){
		this.listaCesionario.add(item);
	}
	
	public void setUnDatoParametrico(DatoParametricoPoliza item){
		this.listaDatosParametrico.add(item);
	}
}
