package uy.com.bse.cotizaciones.lovs;

import java.util.ArrayList;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultListaProducto extends ResultGenerico{
	private ArrayList<Producto> producto;
	
	public ResultListaProducto() {		
		this.producto = new ArrayList<Producto>();
	}	
	
	public ArrayList<Producto> getProducto() {
		return producto;
	}

	public void setProducto(ArrayList<Producto> producto) {
		this.producto = producto;
	}

	public void setUnProducto(Producto item){
		this.producto.add(item);
	}
}
