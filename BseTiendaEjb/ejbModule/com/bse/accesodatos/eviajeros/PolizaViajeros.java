package com.bse.accesodatos.eviajeros;

import java.util.ArrayList;
import java.util.Date;

import com.bse.accesodatos.esoa.Poliza;

public class PolizaViajeros extends Poliza{

private ArrayList<Viajero> listaViajeros;
private String extension; 
private Date fechaSalidaPais;

public PolizaViajeros() {
}

public PolizaViajeros(ArrayList<Viajero> listaViajeros) {
	this.listaViajeros = listaViajeros;
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
