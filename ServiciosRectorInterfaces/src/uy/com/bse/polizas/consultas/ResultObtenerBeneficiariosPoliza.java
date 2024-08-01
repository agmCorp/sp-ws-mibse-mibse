package uy.com.bse.polizas.consultas;

import java.util.ArrayList;

import uy.com.bse.cotizaciones.entidades.Beneficiario;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerBeneficiariosPoliza extends ResultGenerico{
	private EncabezadoDetallePoliza encabezadoDetallePoliza;
	private ArrayList<Beneficiario> beneficiarios = new ArrayList<Beneficiario>();
	
	public EncabezadoDetallePoliza getEncabezadoDetallePoliza() {
		return encabezadoDetallePoliza;
	}
	public void setEncabezadoDetallePoliza(
			EncabezadoDetallePoliza encabezadoDetallePoliza) {
		this.encabezadoDetallePoliza = encabezadoDetallePoliza;
	}
	public ArrayList<Beneficiario> getBeneficiarios() {
		return beneficiarios;
	}
	public void setBeneficiarios(ArrayList<Beneficiario> beneficiarios) {
		this.beneficiarios = beneficiarios;
	}
	
	public void setUnBeneficiario(Beneficiario item){
		this.beneficiarios.add(item);
	}

}
