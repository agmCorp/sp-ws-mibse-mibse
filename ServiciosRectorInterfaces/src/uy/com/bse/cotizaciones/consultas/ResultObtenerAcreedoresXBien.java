package uy.com.bse.cotizaciones.consultas;

import java.util.ArrayList;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerAcreedoresXBien extends ResultGenerico{
	private ArrayList<AcreedorBien> infoAcreedorBien = new ArrayList<AcreedorBien>();

	public ArrayList<AcreedorBien> getInfoAcreedorBien() {
		return infoAcreedorBien;
	}

	public void setInfoAcreedorBien(ArrayList<AcreedorBien> infoAcreedorBien) {
		this.infoAcreedorBien = infoAcreedorBien;
	}
	
	public void setUnAcreedor(AcreedorBien item){
		this.infoAcreedorBien.add(item);
	}

}
