package com.bse.accesodatos.epagos;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CobtPagosBancariosPKTienda {

@Column(name = "CPB_ENTIDAD")
private int entidad;

@Column(name = "CPB_ID_TRANSACCION")
private String idTransaccion;


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


}
