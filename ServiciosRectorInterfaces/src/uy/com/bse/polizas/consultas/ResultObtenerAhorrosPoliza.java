package uy.com.bse.polizas.consultas;

import java.util.ArrayList;

import uy.com.bse.cotizaciones.entidades.Ahorro;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerAhorrosPoliza extends ResultGenerico{
	private EncabezadoDetallePoliza encabezadoDetallePoliza;
	private ArrayList<Ahorro> ahorros = new ArrayList<Ahorro>();
	private ArrayList<DatosCalculoRenta> datosCalculoRenta  = new ArrayList<DatosCalculoRenta>();
	
	public EncabezadoDetallePoliza getEncabezadoDetallePoliza() {
		return encabezadoDetallePoliza;
	}
	public void setEncabezadoDetallePoliza(
			EncabezadoDetallePoliza encabezadoDetallePoliza) {
		this.encabezadoDetallePoliza = encabezadoDetallePoliza;
	}
	public ArrayList<Ahorro> getAhorros() {
		return ahorros;
	}
	public void setAhorros(ArrayList<Ahorro> ahorros) {
		this.ahorros = ahorros;
	}
	public ArrayList<DatosCalculoRenta> getDatosCalculoRenta() {
		return datosCalculoRenta;
	}
	public void setDatosCalculoRenta(ArrayList<DatosCalculoRenta> datosCalculoRenta) {
		this.datosCalculoRenta = datosCalculoRenta;
	}
	
	public void setUnAhorro(Ahorro item){
		this.ahorros.add(item);
	}
	
	public void setUnDatoCalculoRenta(DatosCalculoRenta item){
		this.datosCalculoRenta.add(item);
	}

}
