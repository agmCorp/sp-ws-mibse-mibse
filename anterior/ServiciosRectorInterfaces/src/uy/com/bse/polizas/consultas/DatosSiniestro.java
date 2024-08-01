package uy.com.bse.polizas.consultas;

import java.io.Serializable;
import java.util.ArrayList;

import uy.com.bse.polizas.entidades.BienPoliza;

public class DatosSiniestro implements Serializable{

	private static final long serialVersionUID = 2904420691593625454L;
	private Integer anio;
	private Integer codRamo;
	private Integer numSiniestro; 
	private Integer numPoliza;
	private Integer numCertificado;
	private Integer numEndoso;
	private String descripCliente;
	private ArrayList <BienPoliza> bienes = new ArrayList<BienPoliza>();
	
	public Integer getAnio() {
		return anio;
	}
	public void setAnio(Integer anio) {
		this.anio = anio;
	}
	public Integer getCodRamo() {
		return codRamo;
	}
	public void setCodRamo(Integer codRamo) {
		this.codRamo = codRamo;
	}
	public Integer getNumSiniestro() {
		return numSiniestro;
	}
	public void setNumSiniestro(Integer numSiniestro) {
		this.numSiniestro = numSiniestro;
	}
	public Integer getNumPoliza() {
		return numPoliza;
	}
	public void setNumPoliza(Integer numPoliza) {
		this.numPoliza = numPoliza;
	}
	public Integer getNumCertificado() {
		return numCertificado;
	}
	public void setNumCertificado(Integer numCertificado) {
		this.numCertificado = numCertificado;
	}
	public Integer getNumEndoso() {
		return numEndoso;
	}
	public void setNumEndoso(Integer numEndoso) {
		this.numEndoso = numEndoso;
	}
	public String getDescripCliente() {
		return descripCliente;
	}
	public void setDescripCliente(String descripCliente) {
		this.descripCliente = descripCliente;
	}
	public ArrayList<BienPoliza> getBienes() {
		return bienes;
	}
	public void setBienes(ArrayList<BienPoliza> bienes) {
		this.bienes = bienes;
	}

	public void setUnBienPoliza(BienPoliza item){
		this.bienes.add(item);
	}
}
