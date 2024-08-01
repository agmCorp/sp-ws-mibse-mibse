package uy.com.bse.maestro.personas.interfaces;

import java.util.ArrayList;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultListaMotivosBaja extends ResultGenerico {
	
	private static final long serialVersionUID = 8211932592056381583L;
	private ArrayList<MotivoBaja> motivoBaja;
	
	public ResultListaMotivosBaja(){
		super();
		this.motivoBaja = new ArrayList<MotivoBaja>();
		
	}
	
	public ArrayList<MotivoBaja> getMotivoBaja() {
		return motivoBaja;
	}
	public void setMotivoBaja(ArrayList<MotivoBaja> motivoBaja) {
		this.motivoBaja = motivoBaja;
	}
	
	public void setUnMotivoBaja(MotivoBaja mot){
		this.motivoBaja.add(mot);
	}
}
