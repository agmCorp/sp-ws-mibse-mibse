package uy.com.bse.cotizadorvya.operaciones;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import uy.com.bse.cotizadorvya.entidades.Cobertura;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerCoberturas extends ResultGenerico {
	private static final long serialVersionUID = 1237402835428786094L;
	
	private ArrayList<Cobertura> coberturas= new ArrayList<>();

	@XmlElementWrapper(name="Coberturas") 
    @XmlElement(name="Cobertura")	
	public ArrayList<Cobertura> getCoberturas() {
		return coberturas;
	}

	public void setCoberturas(ArrayList<Cobertura> coberturas) {
		this.coberturas = coberturas;
	}
}
