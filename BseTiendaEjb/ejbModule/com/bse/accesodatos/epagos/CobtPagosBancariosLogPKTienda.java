package com.bse.accesodatos.epagos;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class CobtPagosBancariosLogPKTienda {

@Column(name = "CPBL_ENTIDAD")
private int entidad;

@Column(name = "CPBL_ID_TRANSACCION")
private String idTransaccion;

@Temporal(TemporalType.TIMESTAMP)
@Column(name = "CPBL_FECHA")
private Date fecha;

public int getEntidad() {
	return entidad;
}

public void setEntidad(int entidad) {
	this.entidad = entidad;
}

public String getIdTransaccion() {
	return idTransaccion;
}

public void setIdTransaccion(String idTransaccion) {
	this.idTransaccion = idTransaccion;
}

public Date getFecha() {
	return fecha;
}

public void setFecha(Date fecha) {
	this.fecha = fecha;
}


}
