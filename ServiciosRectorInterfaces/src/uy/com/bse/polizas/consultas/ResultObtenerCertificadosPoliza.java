package uy.com.bse.polizas.consultas;

import java.util.ArrayList;

import uy.com.bse.polizas.entidades.CertificadoPolizaBasico;
import uy.com.bse.polizas.entidades.EncabezadoPoliza;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerCertificadosPoliza extends ResultGenerico{
	private EncabezadoPoliza encabezado;
	private ArrayList<CertificadoPolizaBasico> datosBasicosCertificado = new ArrayList<CertificadoPolizaBasico>();
	
	public EncabezadoPoliza getEncabezado() {
		return encabezado;
	}
	public void setEncabezado(EncabezadoPoliza encabezado) {
		this.encabezado = encabezado;
	}
	public ArrayList<CertificadoPolizaBasico> getDatosBasicosCertificado() {
		return datosBasicosCertificado;
	}
	public void setDatosBasicosCertificado(
			ArrayList<CertificadoPolizaBasico> datosBasicosCertificado) {
		this.datosBasicosCertificado = datosBasicosCertificado;
	}
	
	public void setUnCertificado(CertificadoPolizaBasico item){
		this.datosBasicosCertificado.add(item);
	}

}
