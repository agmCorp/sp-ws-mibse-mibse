package com.bse.accesodatos.eviajeros;

import java.util.ArrayList;
import java.util.Date;

import javax.jws.WebParam;

import com.bse.accesodatos.esoa.Cotizacion;

public class CotizacionViajeros extends Cotizacion{

private ArrayList<Viajero> listaViajeros;
private String extension; 
private Date fechaSalidaPais;


public CotizacionViajeros() {
}


public ArrayList<Viajero> getListaViajeros() {
	return listaViajeros;
}

public void setListaViajeros(ArrayList<Viajero> listaViajeros) {
	this.listaViajeros = listaViajeros;
}

public String getExtension() {
	return extension;
}


public void setExtension(String extension) {
	this.extension = extension;
}


public Date getFechaSalidaPais() {
	return fechaSalidaPais;
}


public void setFechaSalidaPais(Date fechaSalidaPais) {
	this.fechaSalidaPais = fechaSalidaPais;
}


}
