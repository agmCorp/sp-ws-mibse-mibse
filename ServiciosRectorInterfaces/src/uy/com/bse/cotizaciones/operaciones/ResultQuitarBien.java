package uy.com.bse.cotizaciones.operaciones;

import java.util.ArrayList;

import uy.com.bse.cotizaciones.consultas.BienCert;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultQuitarBien extends ResultGenerico{
	private ArrayList<BienCert> datosQuitarBien;
	private String habilitoInsertar;
	
	public ResultQuitarBien(){
		this.datosQuitarBien = new ArrayList<BienCert>();
	}

	public ArrayList<BienCert> getDatosQuitarBien() {
		return datosQuitarBien;
	}

	public void setDatosQuitarBien(ArrayList<BienCert> datosQuitarBien) {
		this.datosQuitarBien = datosQuitarBien;
	}
	
	public void setUnBienCert(BienCert bien){
		this.datosQuitarBien.add(bien);
	}

	public String getHabilitoInsertar() {
		return habilitoInsertar;
	}

	public void setHabilitoInsertar(String habilitoInsertar) {
		this.habilitoInsertar = habilitoInsertar;
	}
	
}
