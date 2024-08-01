package uy.com.bse.polizas.consultas;

import java.util.ArrayList;

import uy.com.bse.polizas.entidades.DatosBasicosEndoso;
import uy.com.bse.polizas.entidades.EncabezadoPoliza;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerEndososPoliza extends ResultGenerico{
	private EncabezadoPoliza encabezado;
	private ArrayList<DatosBasicosEndoso> endosos = new ArrayList<DatosBasicosEndoso>();
	
	public EncabezadoPoliza getEncabezado() {
		return encabezado;
	}
	public void setEncabezado(EncabezadoPoliza encabezado) {
		this.encabezado = encabezado;
	}
	public ArrayList<DatosBasicosEndoso> getEndosos() {
		return endosos;
	}
	public void setEndosos(ArrayList<DatosBasicosEndoso> endosos) {
		this.endosos = endosos;
	}
	
	public void setUnDatoBasicoEndoso(DatosBasicosEndoso item){
		this.endosos.add(item);
	}
}
