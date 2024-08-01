package uy.com.bse.polizas.operaciones;

import java.util.ArrayList;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultNuevaFacturacionInteractiva extends ResultGenerico{
	private String mensaje;
	private ArrayList<String> numerosFacturas = new ArrayList<String>();
	private ArrayList<Long> numerosFacturasElectronica = new ArrayList<Long>();

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public ArrayList<String> getNumerosFacturas() {
		return numerosFacturas;
	}

	public void setNumerosFacturas(ArrayList<String> numerosFacturas) {
		this.numerosFacturas = numerosFacturas;
	}
	
	public void setUnNumeroFactura(String numero){
		numerosFacturas.add(numero);
	}

	public ArrayList<Long> getNumerosFacturasElectronica() {
		return numerosFacturasElectronica;
	}

	public void setNumerosFacturasElectronica(ArrayList<Long> numerosFacturasElectronica) {
		this.numerosFacturasElectronica = numerosFacturasElectronica;
	}
	
	public void setUnNumeroFacturaElectronica(Long numero){
		numerosFacturasElectronica.add(numero);
	}

}
