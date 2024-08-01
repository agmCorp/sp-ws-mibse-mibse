package uy.com.bse.polizas.consultas;

import java.util.ArrayList;

import uy.com.bse.polizas.entidades.Remesa;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerRemesasPoliza extends ResultGenerico{
	private EncabezadoCuota encabezado;
	private ArrayList<Remesa> remesas = new ArrayList<Remesa>();
	
	public EncabezadoCuota getEncabezado() {
		return encabezado;
	}
	public void setEncabezado(EncabezadoCuota encabezado) {
		this.encabezado = encabezado;
	}
	public ArrayList<Remesa> getRemesas() {
		return remesas;
	}
	public void setRemesas(ArrayList<Remesa> remesas) {
		this.remesas = remesas;
	}
	
	public void setUnaRemesa(Remesa item){
		this.remesas.add(item);
	}
}
