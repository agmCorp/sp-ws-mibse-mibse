package uy.bse.rector.servicios.persistencia;

import java.util.ArrayList;

import org.w3c.dom.NodeList;

import uy.com.bse.utilitario.log.Logueo;
import uy.com.bse.utilitario.parseo.ParseoXmlGenerico;

public class ParseoXml extends ParseoXmlGenerico{

	public ParseoXml(String textoParsear) {
		super(textoParsear);
		
	}
	
	public ArrayList<String> parsearValidarCotizacion (){
		Logueo errorGenerado = new Logueo();
		errorGenerado.setClase(ParseoXml.class);
		errorGenerado.setMetodo("parsearValidarCotizacion");
		errorGenerado.setEncabezado("Error al parsear elementos");		
		
		ArrayList<String> resultado = new ArrayList<String>();
		
		NodeList elementos = this.doc.getElementsByTagName("fuera-pauta");
		int cantidadCoberturas = elementos.getLength();
		
		for(int i = 0; i<cantidadCoberturas; i++){
			String valor = elementos.item(i).getFirstChild().getNodeValue();
			resultado.add(valor);
		}
		
		return resultado;
	}

	public ArrayList<String> parsearEmitirCotizacion (){
		Logueo errorGenerado = new Logueo();
		errorGenerado.setClase(ParseoXml.class);
		errorGenerado.setMetodo("parsearValidarCotizacion");
		errorGenerado.setEncabezado("Error al parsear elementos");		
		
		ArrayList<String> resultado = new ArrayList<String>();
		
		NodeList elementos = this.doc.getElementsByTagName("fuera-pauta");
		int cantidadCoberturas = elementos.getLength();
		
		for(int i = 0; i<cantidadCoberturas; i++){
			String valor = elementos.item(i).getFirstChild().getNodeValue();
			resultado.add(valor);
		}
		
		return resultado;
	}
}
