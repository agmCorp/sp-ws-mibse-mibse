package uy.com.bse.cotizaciones.operaciones;

import java.io.Serializable;
import java.util.ArrayList;

import uy.com.bse.cotizaciones.consultas.Bien;
import uy.com.bse.cotizaciones.consultas.BienCert;
import uy.com.bse.cotizaciones.consultas.Cliente;
import uy.com.bse.cotizaciones.entidades.Anexo;
import uy.com.bse.maestro.personas.domicilio.DireccionEC;

public class Riesgo implements Serializable{
	
	private Integer numCertificado;
	private String descRiesgo;
	
	
	public Integer getNumCertificado() {
		return numCertificado;
	}
	public void setNumCertificado(Integer numCertificado) {
		this.numCertificado = numCertificado;
	}
	public String getDescRiesgo() {
		return descRiesgo;
	}
	public void setDescRiesgo(String descRiesgo) {
		this.descRiesgo = descRiesgo;
	}
	
	
	
	
	
}
