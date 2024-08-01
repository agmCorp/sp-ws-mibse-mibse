package uy.com.bse.cotizaciones.operaciones;

import java.util.ArrayList;

import uy.com.bse.cotizaciones.entidades.DatoDependiente;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.dato.ResultXmlPL;

public class ResultActualizarDatos extends ResultXmlPL {
	
	String estadoActualizacion;
	String valorDatoActualizado;

	ArrayList<DatoDependiente> dependientes = new ArrayList<DatoDependiente>();
	
	public String getEstadoActualizacion() {
		return estadoActualizacion;
	}
	public void setEstadoActualizacion(String estadoActualizacion) {
		this.estadoActualizacion = estadoActualizacion;
	}
	public String getValorDatoActualizado() {
		return valorDatoActualizado;
	}
	public void setValorDatoActualizado(String valorDatoActualizado) {
		this.valorDatoActualizado = valorDatoActualizado;
	}
	
	public ArrayList<DatoDependiente> getDependientes() {
		return dependientes;
	}
	public void setDependientes(ArrayList<DatoDependiente> dependientes) {
		this.dependientes = dependientes;
	}
	public void setUnDependiente(DatoDependiente item){
		this.dependientes.add(item);
	}

}
