package uy.com.bse.cotizaciones.operaciones;

import java.util.ArrayList;

import uy.com.bse.cotizaciones.entidades.DatoBien;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultValidarDatosVehiculo extends ResultGenerico {

	private static final long serialVersionUID = 1L;
	
	private String errorValidacion;
    private ArrayList<DatoBien> datos = new ArrayList<DatoBien>();
	private ArrayList<Validacion> validaciones = new ArrayList<Validacion>();

	public ResultValidarDatosVehiculo() {
		this.validaciones = new ArrayList<Validacion>();
	}

	public String getErrorValidacion() {
		return errorValidacion;
	}

	public void setErrorValidacion(String errorValidacion) {
		this.errorValidacion = errorValidacion;
	}

	public ArrayList<Validacion> getValidaciones() {
		return validaciones;
	}

	public void setValidaciones(ArrayList<Validacion> validaciones) {
		this.validaciones = validaciones;
	}
	
	public void setUno(Validacion validacion){
		this.validaciones.add(validacion);
	}

	public ArrayList<DatoBien> getDatos() {
		return datos;
	}

	public void setDatos(ArrayList<DatoBien> datos) {
		this.datos = datos;
	}

	public void setUno(DatoBien dato){
		this.datos.add(dato);
	}
	
}
