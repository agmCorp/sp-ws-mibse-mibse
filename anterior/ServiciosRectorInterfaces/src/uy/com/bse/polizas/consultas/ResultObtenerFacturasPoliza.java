package uy.com.bse.polizas.consultas;

import java.util.ArrayList;

import uy.com.bse.polizas.entidades.Factura;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerFacturasPoliza extends ResultGenerico{

	private static final long serialVersionUID = 6487791683852783368L;
	private EncabezadoCuota encabezado;
	private ArrayList<Factura> facturas = new ArrayList<Factura>();
	
	public EncabezadoCuota getEncabezado() {
		return encabezado;
	}
	public void setEncabezado(EncabezadoCuota encabezado) {
		this.encabezado = encabezado;
	}
	public ArrayList<Factura> getFacturas() {
		return facturas;
	}
	public void setFacturas(ArrayList<Factura> facturas) {
		this.facturas = facturas;
	}
	
	public void setUnaFactura(Factura item){
		this.facturas.add(item);
	}
}
