package uy.com.bse.servicios.rector.interfaces;

import java.util.ArrayList;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultListaDepartamentos extends ResultGenerico{

	ArrayList<Departamento> departamento = new ArrayList<Departamento>();
	
	public ArrayList<Departamento> getDepartamento() {
		return departamento;
	}
	public void setDepartamento(ArrayList<Departamento> departamento) {
		this.departamento = departamento;
	}
	
	public void setUnDepartamento(Departamento dep){
		this.departamento.add(dep);
	}
}
