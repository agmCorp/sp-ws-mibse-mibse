package uy.com.bse.cotizaciones.consultas;

import java.util.ArrayList;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultCertificados extends ResultGenerico{
	private ArrayList<BienCert> bien;
	
	public ResultCertificados(){
		this.bien = new ArrayList<BienCert>();
	}

	public ArrayList<BienCert> getBien() {
		return bien;
	}

	public void setBien(ArrayList<BienCert> bien) {
		this.bien = bien;
	}
	
	public void setUnElemento(BienCert item){
		this.bien.add(item);
	}
}
