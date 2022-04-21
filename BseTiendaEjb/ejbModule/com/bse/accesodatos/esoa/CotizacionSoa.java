package com.bse.accesodatos.esoa;

public class CotizacionSoa extends Cotizacion{

private MarcaVehiculo marcaVehiculo;
private CategoriaVehiculo categoriaVehiculo;
private String matriculaVehiculo;
private String padronVehiculo;
private String chasisVehiculo;
private String motorVehiculo;
private String anioVehiculo;


public CotizacionSoa() {
}

public MarcaVehiculo getMarcaVehiculo() {
	return marcaVehiculo;
}

public void setMarcaVehiculo(MarcaVehiculo marcaVehiculo) {
	this.marcaVehiculo = marcaVehiculo;
}

public CategoriaVehiculo getCategoriaVehiculo() {
	return categoriaVehiculo;
}

public void setCategoriaVehiculo(CategoriaVehiculo categoriaVehiculo) {
	this.categoriaVehiculo = categoriaVehiculo;
}

public String getMatriculaVehiculo() {
	return matriculaVehiculo;
}

public void setMatriculaVehiculo(String matriculaVehiculo) {
	this.matriculaVehiculo = matriculaVehiculo;
}

public String getPadronVehiculo() {
	return padronVehiculo;
}

public void setPadronVehiculo(String padronVehiculo) {
	this.padronVehiculo = padronVehiculo;
}

public String getChasisVehiculo() {
	return chasisVehiculo;
}

public void setChasisVehiculo(String chasisVehiculo) {
	this.chasisVehiculo = chasisVehiculo;
}

public String getMotorVehiculo() {
	return motorVehiculo;
}

public void setMotorVehiculo(String motorVehiculo) {
	this.motorVehiculo = motorVehiculo;
}

public String getAnioVehiculo() {
	return anioVehiculo;
}

public void setAnioVehiculo(String anioVehiculo2) {
	this.anioVehiculo = anioVehiculo2;
}

}
