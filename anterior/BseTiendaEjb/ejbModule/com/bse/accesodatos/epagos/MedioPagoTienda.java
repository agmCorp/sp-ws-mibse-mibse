package com.bse.accesodatos.epagos;

public class MedioPagoTienda{

private String codigoMedio;
private String nombre;

public MedioPagoTienda() {
}

public MedioPagoTienda(String codigoMedio, String nombre) {
	this.codigoMedio = codigoMedio;
	this.nombre = nombre;
}

public String getCodigoMedio() {
	return codigoMedio;
}

public void setCodigoMedio(String codigoMedio) {
	this.codigoMedio = codigoMedio;
}

public String getNombre() {
	return nombre;
}

public void setNombre(String nombre) {
	this.nombre = nombre;
}

}
