package com.bse.accesodatos.eindivi;

public class TipoVehiculo{

private String codigo;
private String nombre;

public TipoVehiculo() {
}

public TipoVehiculo(String codigo, String nombre) {
	this.codigo = codigo;
	this.nombre = nombre;
}

public String getCodigo() {
	return codigo;
}

public void setCodigo(String codigo) {
	this.codigo = codigo;
}

public String getNombre() {
	return nombre;
}

public void setNombre(String nombre) {
	this.nombre = nombre;
}

}
