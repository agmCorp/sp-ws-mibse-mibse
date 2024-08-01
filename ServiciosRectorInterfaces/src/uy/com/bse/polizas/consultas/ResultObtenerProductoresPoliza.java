package uy.com.bse.polizas.consultas;

import java.util.ArrayList;

import uy.com.bse.polizas.entidades.EncabezadoPoliza;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerProductoresPoliza extends ResultGenerico{
	private EncabezadoPoliza encabezado;
	private ArrayList<DatosProductor> datosProductor = new ArrayList<DatosProductor>();
	
	public EncabezadoPoliza getEncabezado() {
		return encabezado;
	}
	public void setEncabezado(EncabezadoPoliza encabezado) {
		this.encabezado = encabezado;
	}
	public ArrayList<DatosProductor> getDatosProductor() {
		return datosProductor;
	}
	public void setDatosProductor(ArrayList<DatosProductor> datosProductor) {
		this.datosProductor = datosProductor;
	}  
	
	public void setUnDatoProductor(DatosProductor item){
		this.datosProductor.add(item);
	}
}
