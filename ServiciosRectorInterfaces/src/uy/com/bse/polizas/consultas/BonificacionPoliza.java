package uy.com.bse.polizas.consultas;

import java.io.Serializable;
import java.util.ArrayList;

public class BonificacionPoliza implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer numCertificado;
	private String clausula;
	private ArrayList<InfoPolizaSiniestro> polizasConSiniestros = new ArrayList<InfoPolizaSiniestro>();
	
	public Integer getNumCertificado() {
		return numCertificado;
	}
	public void setNumCertificado(Integer numCertificado) {
		this.numCertificado = numCertificado;
	}
	public String getClausula() {
		return clausula;
	}
	public void setClausula(String clausula) {
		this.clausula = clausula;
	}
	public ArrayList<InfoPolizaSiniestro> getPolizasConSiniestros() {
		return polizasConSiniestros;
	}
	public void setPolizasConSiniestros(
			ArrayList<InfoPolizaSiniestro> polizasConSiniestros) {
		this.polizasConSiniestros = polizasConSiniestros;
	}
	
	public void setUnaInfoPolizaSiniestro(InfoPolizaSiniestro item){
		this.polizasConSiniestros.add(item);
	}
}
