package uy.com.bse.polizas.operaciones;

import java.util.ArrayList;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultAnularPoliza extends ResultGenerico{
	private static final long serialVersionUID = -2209690703410784890L;
	private Integer numEndoso;
	private String factElectronica;
	private ArrayList<String> numerosFacturas= new ArrayList<String>();

	public String getFactElectronica() {
		return factElectronica;
	}

	public void setFactElectronica(String factElectronica) {
		this.factElectronica = factElectronica;
	}

	public Integer getNumEndoso() {
		return numEndoso;
	}

	public void setNumEndoso(Integer numEndoso) {
		this.numEndoso = numEndoso;
	}

	public ArrayList<String> getNumerosFacturas() {
		return numerosFacturas;
	}

	public void setNumerosFacturas(ArrayList<String> numerosFacturas) {
		this.numerosFacturas = numerosFacturas;
	}
	
	public void setUnNumeroFactura(String numeroFactura){
		numerosFacturas.add(numeroFactura);
	}

}
