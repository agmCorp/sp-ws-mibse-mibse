package uy.com.bse.cotizaciones.operaciones;

import java.util.ArrayList;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultGuardarCoberturas extends ResultGenerico {
	
	private ArrayList<InfoGuardarCobertura> guardarCoberturas = new ArrayList<InfoGuardarCobertura>();
	private String xml;
	
	
	public ArrayList<InfoGuardarCobertura> getGuardarCoberturas() {
		return guardarCoberturas;
	}

	public void setGuardarCoberturas(
			ArrayList<InfoGuardarCobertura> guardarCoberturas) {
		this.guardarCoberturas = guardarCoberturas;
	}

	public void setUnElemento(InfoGuardarCobertura item){
		this.guardarCoberturas.add(item);
	}

	public String getXml() {
		return xml;
	}

	public void setXml(String xml) {
		this.xml = xml;
	}

	
	
}
