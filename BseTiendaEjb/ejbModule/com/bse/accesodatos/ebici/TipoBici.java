package com.bse.accesodatos.ebici;

public class TipoBici{

private String codigo;
private String nombre;

public TipoBici() {
}

public TipoBici(String codigo, String nombre) {
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
