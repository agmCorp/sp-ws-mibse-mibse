package uy.com.bse.servicios.rector.interfaces;

import java.util.ArrayList;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultListaSucursales extends ResultGenerico{

	ArrayList<Sucursal> sucursal = new ArrayList<Sucursal>();
	
	public ArrayList<Sucursal> getSucursal() {
		return sucursal;
	}
	public void setSucursal(ArrayList<Sucursal> sucursal) {
		this.sucursal = sucursal;
	}
	
	public void setUnaSucursal(Sucursal suc){
		this.sucursal.add(suc);
	}
}
