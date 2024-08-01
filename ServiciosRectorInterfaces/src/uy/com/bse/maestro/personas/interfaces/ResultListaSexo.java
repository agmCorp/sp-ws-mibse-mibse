package uy.com.bse.maestro.personas.interfaces;

import java.util.ArrayList;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultListaSexo extends ResultGenerico{
	private ArrayList<Sexo> sexo;
	
	public ResultListaSexo(){
		this.sexo = new ArrayList<Sexo>();
	}
	
	public ArrayList<Sexo> getSexo() {
		return sexo;
	}

	public void setSexo(ArrayList<Sexo> sexo) {
		this.sexo = sexo;
	}

	public void setUnSexo(Sexo sex){
		this.sexo.add(sex);
	}
}
