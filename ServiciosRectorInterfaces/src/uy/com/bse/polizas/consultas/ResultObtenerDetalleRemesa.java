package uy.com.bse.polizas.consultas;

import java.util.ArrayList;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerDetalleRemesa extends ResultGenerico{
	private ArrayList<DetalleRemesa> listaDetalleRemesa = new ArrayList<DetalleRemesa>();

	public ArrayList<DetalleRemesa> getListaDetalleRemesa() {
		return listaDetalleRemesa;
	}

	public void setListaDetalleRemesa(ArrayList<DetalleRemesa> listaDetalleRemesa) {
		this.listaDetalleRemesa = listaDetalleRemesa;
	}
	
	public void setUnDetalle(DetalleRemesa item){
		this.listaDetalleRemesa.add(item);
	}
}
