package uy.com.bse.polizas.consultas;

import java.util.ArrayList;

import uy.com.bse.polizas.entidades.Preliquidacion;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerPreliqPendientesPoliza extends ResultGenerico{
	private EncabezadoCuota encabezado;
	private ArrayList<Preliquidacion> preliqPendientes = new ArrayList<Preliquidacion>();
	
	public EncabezadoCuota getEncabezado() {
		return encabezado;
	}
	public void setEncabezado(EncabezadoCuota encabezado) {
		this.encabezado = encabezado;
	}
	public ArrayList<Preliquidacion> getPreliqPendientes() {
		return preliqPendientes;
	}
	public void setPreliqPendientes(ArrayList<Preliquidacion> preliqPendientes) {
		this.preliqPendientes = preliqPendientes;
	}
	
	public void setUnaPreliq(Preliquidacion item){
		this.preliqPendientes.add(item);
	}
}
