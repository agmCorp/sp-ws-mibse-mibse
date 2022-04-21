package com.bse.accesodatos.ebici;

import java.util.Date;

import com.bse.accesodatos.esoa.Poliza;

public class PolizaBici extends Poliza{

private Date fechaFactura;
private String tipoBicicleta;
private Date fechaNacimientoCliente;
private String marca;
private String serie;
private String modelo;

public PolizaBici() {
}

public Date getFechaFactura() {
	return fechaFactura;
}

public void setFechaFactura(Date fechaFactura) {
	this.fechaFactura = fechaFactura;
}

public String getTipoBicicleta() {
	return tipoBicicleta;
}

public void setTipoBicicleta(String tipoBicicleta) {
	this.tipoBicicleta = tipoBicicleta;
}

public Date getFechaNacimientoCliente() {
	return fechaNacimientoCliente;
}

public void setFechaNacimientoCliente(Date fechaNacimientoCliente) {
	this.fechaNacimientoCliente = fechaNacimientoCliente;
}

public String getMarca() {
	return marca;
}

public void setMarca(String marca) {
	this.marca = marca;
}

public String getSerie() {
	return serie;
}

public void setSerie(String serie) {
	this.serie = serie;
}

public String getModelo() {
	return modelo;
}

public void setModelo(String modelo) {
	this.modelo = modelo;
}


}
