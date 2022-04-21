package com.bse.accesodatos.esoa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "CART_FRAGMENT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PlanPago.findAll", query = "SELECT p FROM PlanPago p"),
    @NamedQuery(name = "PlanPago.findByClave", query = "SELECT p FROM PlanPago p WHERE p.codigo = :codigo")
})

public class PlanPago{

@Id
@Column(name = "CAFR_CD_FRAGMENT")
private int codigo;

@Column(name = "CAFR_DE_FRAGMENT")
private String descripcion;

@Column(name = "CAFR_NU_CUOTAS")
private int nroCuotas;

@Column(name = "CAFR_MT_CUOTA_MINIMA")
private double cuotaMinima;


public PlanPago() {
}

public PlanPago(int codigo, String descripcion, int nroCuotas,
		double cuotaMinima) {
	super();
	this.codigo = codigo;
	this.descripcion = descripcion;
	this.nroCuotas = nroCuotas;
	this.cuotaMinima = cuotaMinima;
}

public int getCodigo() {
	return codigo;
}

public void setCodigo(int codigo) {
	this.codigo = codigo;
}

public int getNroCuotas() {
	return nroCuotas;
}

public void setNroCuotas(int nroCuotas) {
	this.nroCuotas = nroCuotas;
}

public double getCuotaMinima() {
	return cuotaMinima;
}

public void setCuotaMinima(double cuotaMinima) {
	this.cuotaMinima = cuotaMinima;
}

public String getDescripcion() {
	return descripcion;
}

public void setDescripcion(String descripcion) {
	this.descripcion = descripcion;
}

}
