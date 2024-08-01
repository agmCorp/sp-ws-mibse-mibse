package uy.com.bse.cotizaciones.operaciones;

import java.util.ArrayList;

import uy.com.bse.cotizaciones.consultas.BienCert;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultAgregarBien extends ResultGenerico{
	private ArrayList<BienCert> datosAgregarBien;
	private String habilitoInsertar;
	
	public ResultAgregarBien(){
		this.datosAgregarBien = new ArrayList<BienCert>();
	}

	public ArrayList<BienCert> getDatosAgregarBien() {
		return datosAgregarBien;
	}

	public void setDatosAgregarBien(ArrayList<BienCert> datosAgregarBien) {
		this.datosAgregarBien = datosAgregarBien;
	}

	public void setUnBien(BienCert bien){
		this.datosAgregarBien.add(bien);
	}

	public String getHabilitoInsertar() {
		return habilitoInsertar;
	}

	public void setHabilitoInsertar(String habilitoInsertar) {
		this.habilitoInsertar = habilitoInsertar;
	}
	
}
