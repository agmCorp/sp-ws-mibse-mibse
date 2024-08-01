package uy.com.bse.cotizaciones.consultas;

import java.util.ArrayList;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultDatosCertificados extends ResultGenerico{
	private ArrayList<BienCert> bien;
	
	public ResultDatosCertificados(){
		this.bien = new ArrayList<BienCert>();
	}

	public ArrayList<BienCert> getBien() {
		return bien;
	}

	public void setBien(ArrayList<BienCert> bien) {
		this.bien = bien;
	}
	
	public void setUnBienCert(BienCert bien){
		this.bien.add(bien);
	}
}
