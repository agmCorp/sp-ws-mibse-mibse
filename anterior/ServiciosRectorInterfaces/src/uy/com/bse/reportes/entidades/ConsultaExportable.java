package uy.com.bse.reportes.entidades;

import java.io.Serializable;

public class ConsultaExportable implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1246562830935316515L;
	private Integer numeroConsulta; 
	private Integer idConsulta;
	private String nomConsulta;
	
	
	public String getNomConsulta() {
		return nomConsulta;
	}
	public void setNomConsulta(String nomConsulta) {
		this.nomConsulta = nomConsulta;
	}
	public Integer getNumeroConsulta() {
		return numeroConsulta;
	}
	public void setNumeroConsulta(Integer numeroConsulta) {
		this.numeroConsulta = numeroConsulta;
	}
	public Integer getIdConsulta() {
		return idConsulta;
	}
	public void setIdConsulta(Integer idConsulta) {
		this.idConsulta = idConsulta;
	}

}
