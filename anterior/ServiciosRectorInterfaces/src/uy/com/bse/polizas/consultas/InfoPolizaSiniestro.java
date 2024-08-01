package uy.com.bse.polizas.consultas;

import java.io.Serializable;
import java.util.ArrayList;

import uy.com.bse.polizas.entidades.DatosBasicosSiniestro;
import uy.com.bse.polizas.entidades.EncabezadoPoliza;

public class InfoPolizaSiniestro implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private EncabezadoPoliza datosPoliza;
	private ArrayList<DatosBasicosSiniestro> siniestros = new ArrayList<DatosBasicosSiniestro>();
	
	public EncabezadoPoliza getDatosPoliza() {
		return datosPoliza;
	}
	public void setDatosPoliza(EncabezadoPoliza datosPoliza) {
		this.datosPoliza = datosPoliza;
	}
	public ArrayList<DatosBasicosSiniestro> getSiniestros() {
		return siniestros;
	}
	public void setSiniestros(ArrayList<DatosBasicosSiniestro> siniestros) {
		this.siniestros = siniestros;
	}

	public void setUnDatoBasicoSiniestro(DatosBasicosSiniestro item){
		this.siniestros.add(item);
	}
}
