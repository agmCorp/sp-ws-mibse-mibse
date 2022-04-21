package com.bse.accesodatos.esoa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "CART_PRODPLANES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PlanCobertura.findAll", query = "SELECT p FROM PlanCobertura p"),
    @NamedQuery(name = "PlanCobertura.findByRamoPlan", query = "SELECT p FROM PlanCobertura p WHERE p.ramo = :ramo and p.plan = :plan"),
    @NamedQuery(name = "PlanCobertura.findByRamoyProducto", query = "SELECT p FROM PlanCobertura p WHERE p.ramo = :ramo and p.producto = :producto"),
    @NamedQuery(name = "PlanCobertura.findByRamoProductoPlan", query = "SELECT p FROM PlanCobertura p WHERE p.ramo = :ramo and p.producto = :producto and p.plan = :plan"),
    @NamedQuery(name = "PlanCobertura.findByClave", query = "SELECT p FROM PlanCobertura p WHERE p.plan = :plan")
})

public class PlanCobertura{

@JoinColumn(name = "CAPB_CARP_CD_RAMO", referencedColumnName = "CARP_CD_RAMO", insertable = false, updatable = false)
@ManyToOne(optional = false, fetch = FetchType.EAGER)
private Ramo ramo;

@JoinColumn(name = "CAPB_CAPU_CD_PRODUCTO", referencedColumnName = "CAPU_CD_PRODUCTO", insertable = false, updatable = false)
@ManyToOne(optional = false, fetch = FetchType.EAGER)
private Producto producto;

@Id
@Column(name = "CAPB_CD_PLAN")
private String plan;

@Column(name = "CAPB_DE_PLAN")
private String descripcion;

public PlanCobertura() {
}

public PlanCobertura(Ramo ramo, Producto producto, String plan,
		String descripcion) {
	super();
	this.ramo = ramo;
	this.producto = producto;
	this.plan = plan;
	this.descripcion = descripcion;
}

public Ramo getRamo() {
	return ramo;
}

public void setRamo(Ramo ramo) {
	this.ramo = ramo;
}

public Producto getProducto() {
	return producto;
}

public void setProducto(Producto producto) {
	this.producto = producto;
}

public String getPlan() {
	return plan;
}

public void setPlan(String plan) {
	this.plan = plan;
}

public String getDescripcion() {
	return descripcion;
}

public void setDescripcion(String descripcion) {
	this.descripcion = descripcion;
}

}
