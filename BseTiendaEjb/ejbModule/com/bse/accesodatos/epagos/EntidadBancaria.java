package com.bse.accesodatos.epagos;

public class EntidadBancaria{

private Integer codEntidad;
private Integer codEntidadBCU;
private String nombre;

public EntidadBancaria() {
}

public EntidadBancaria(int codEntidad, int codEntidadBCU, String nombre) {
	this.codEntidad = codEntidad;
	this.codEntidadBCU = codEntidadBCU;
	this.nombre = nombre;
}

public Integer getCodEntidad() {
	return codEntidad;
}

public void setCodEntidad(Integer codEntidad) {
	this.codEntidad = codEntidad;
}

public Integer getCodEntidadBCU() {
	return codEntidadBCU;
}

public void setCodEntidadBCU(Integer codEntidadBCU) {
	this.codEntidadBCU = codEntidadBCU;
}

public String getNombre() {
	return nombre;
}

public void setNombre(String nombre) {
	this.nombre = nombre;
}

}
