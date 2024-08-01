package uy.com.bse.cotizaciones.consultas;

import java.util.ArrayList;

import uy.com.bse.cotizaciones.entidades.Beneficiario;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerBeneficiarios extends ResultGenerico {
	private BienCert bien;
	private ArrayList<Beneficiario> beneficiarios= new ArrayList<Beneficiario>();
	
	public BienCert getBien() {
		return bien;
	}
	public void setBien(BienCert bien) {
		this.bien = bien;
	}
	public ArrayList<Beneficiario> getBeneficiarios() {
		return beneficiarios;
	}
	public void setBeneficiarios(ArrayList<Beneficiario> beneficiarios) {
		this.beneficiarios = beneficiarios;
	}
	
	public void setUnElemento(Beneficiario item){
		this.beneficiarios.add(item);
	}
}
