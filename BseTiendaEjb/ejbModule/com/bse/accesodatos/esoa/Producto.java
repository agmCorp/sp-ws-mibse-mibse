package com.bse.accesodatos.esoa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CART_PRODUCTOS")
public class Producto{

	
@JoinColumn(name = "CAPU_CARP_CD_RAMO", referencedColumnName = "CARP_CD_RAMO", insertable = false, updatable = false)
@ManyToOne(optional = false, fetch = FetchType.EAGER)
private Ramo ramo;

@Id
@Column(name = "CAPU_CD_PRODUCTO")
private String producto;

@Column(name = "CAPU_DE_PRODUCTO")
private String descripcion;

public Producto() {
}

public Producto(Ramo ramo, String producto, String descripcion) {
	super();
	this.ramo = ramo;
	this.producto = producto;
	this.descripcion = descripcion;
}

public Ramo getRamo() {
	return ramo;
}

public void setRamo(Ramo ramo) {
	this.ramo = ramo;
}

public String getProducto() {
	return producto;
}

public void setProducto(String producto) {
	this.producto = producto;
}

public String getDescripcion() {
	return descripcion;
}

public void setDescripcion(String descripcion) {
	this.descripcion = descripcion;
}

}
