package uy.com.bse.polizas.consultas;

import java.util.ArrayList;

import uy.com.bse.polizas.entidades.Remesa;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerRemesasXCliente extends ResultGenerico{
	private ArrayList<Remesa> remesas = new ArrayList<Remesa>();

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
