package uy.com.bse.maestro.personas.domicilio;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultDireccion extends ResultGenerico{
	
	private DireccionEC direccion;

	public ResultDireccion(){
		this.direccion = new DireccionEC();
	}
	
	public DireccionEC getDireccion() {
		return direccion;
	}

	public void setDireccion(DireccionEC direccion) {
		this.direccion = direccion;
	}
}