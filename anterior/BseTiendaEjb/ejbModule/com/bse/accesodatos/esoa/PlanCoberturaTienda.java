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
    @NamedQuery(name = "PlanCoberturaTienda.findAll", query = "SELECT p FROM PlanCoberturaTienda p"),
    @NamedQuery(name = "PlanCoberturaTienda.findByRamoPlan", query = "SELECT p FROM PlanCoberturaTienda p WHERE p.ramo = :ramo and p.plan = :plan"),
    @NamedQuery(name = "PlanCoberturaTienda.findByRamoyProducto", query = "SELECT p FROM PlanCoberturaTienda p WHERE p.ramo = :ramo and p.producto = :producto"),
    @NamedQuery(name = "PlanCoberturaTienda.findByRamoProductoPlan", query = "SELECT p FROM PlanCoberturaTienda p WHERE p.ramo = :ramo and p.producto = :producto and p.plan = :plan"),
    @NamedQuery(name = "PlanCoberturaTienda.findByClave", query = "SELECT p FROM PlanCoberturaTienda p WHERE p.plan = :plan")
})

public class PlanCoberturaTienda {

    @JoinColumn(name = "CAPB_CARP_CD_RAMO", referencedColumnName = "CARP_CD_RAMO", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private RamoTienda ramo;

    @JoinColumn(name = "CAPB_CAPU_CD_PRODUCTO", referencedColumnName = "CAPU_CD_PRODUCTO", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private ProductoTienda producto;

    @Id
    @Column(name = "CAPB_CD_PLAN")
    private String plan;

    @Column(name = "CAPB_DE_PLAN")
    private String descripcion;

    public PlanCoberturaTienda() {
    }

    public PlanCoberturaTienda(RamoTienda ramo, ProductoTienda producto, String plan, String descripcion) {
        super();
        this.ramo = ramo;
        this.producto = producto;
        this.plan = plan;
        this.descripcion = descripcion;
    }


    public RamoTienda getRamo() {
        return ramo;
    }
    public void setRamo(RamoTienda ramo) {
        this.ramo = ramo;
    }


    public ProductoTienda getProducto() {
        return producto;
    }
    public void setProducto(ProductoTienda producto) {
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


    @Override
    public String toString() {
        String auxPlan;
        String auxDescripcion;
        String auxRamo;
        String auxProducto;

        if (this.getPlan() == null) { auxPlan = "null";
        } else { auxPlan = this.getPlan(); }

        if (this.getDescripcion() == null) { auxDescripcion = "null";
        } else { auxDescripcion = this.getDescripcion(); }

        if (this.getRamo() == null) { auxRamo = "null";
        } else { auxRamo = this.getRamo().toString(); }

        if (this.getProducto() == null) { auxProducto = "null";
        } else { auxProducto = this.getProducto().toString(); }

        String logueo =   "Codigo=["      + auxPlan
                      + "] Descripcion=[" + auxDescripcion
                      + "] Ramo=["        + auxRamo
                      + "] Producto=["    + auxProducto
                      + "]";
        return logueo;
    }

}
