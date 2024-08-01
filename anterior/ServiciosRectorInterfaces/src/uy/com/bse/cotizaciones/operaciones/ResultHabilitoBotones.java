package uy.com.bse.cotizaciones.operaciones;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultHabilitoBotones extends ResultGenerico{
	private String habilitarAgregarBien;
	private String habilitarQuitarBien;
	private String habilitarListaBienes;
	private String habilitarUbicacion;
	private String habilitarBeneficiarios;
	private String habilitoAcreedores;           
	
	public String getHabilitoAcreedores() {
		return habilitoAcreedores;
	}
	public void setHabilitoAcreedores(String habilitoAcreedores) {
		this.habilitoAcreedores = habilitoAcreedores;
	}
	public String getHabilitarAgregarBien() {
		return habilitarAgregarBien;
	}
	public void setHabilitarAgregarBien(String habilitarAgregarBien) {
		this.habilitarAgregarBien = habilitarAgregarBien;
	}
	public String getHabilitarQuitarBien() {
		return habilitarQuitarBien;
	}
	public void setHabilitarQuitarBien(String habilitarQuitarBien) {
		this.habilitarQuitarBien = habilitarQuitarBien;
	}
	public String getHabilitarListaBienes() {
		return habilitarListaBienes;
	}
	public void setHabilitarListaBienes(String habilitarListaBienes) {
		this.habilitarListaBienes = habilitarListaBienes;
	}
	public String getHabilitarUbicacion() {
		return habilitarUbicacion;
	}
	public void setHabilitarUbicacion(String habilitarUbicacion) {
		this.habilitarUbicacion = habilitarUbicacion;
	}
	public String getHabilitarBeneficiarios() {
		return habilitarBeneficiarios;
	}
	public void setHabilitarBeneficiarios(String habilitarBeneficiarios) {
		this.habilitarBeneficiarios = habilitarBeneficiarios;
	}	

}
