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
    @NamedQuery(name = "PlanPagoTienda.findAll", query = "SELECT p FROM PlanPagoTienda p"),
    @NamedQuery(name = "PlanPagoTienda.findByClave", query = "SELECT p FROM PlanPagoTienda p WHERE p.codigo = :codigo")
})

public class PlanPagoTienda {

    @Id
    @Column(name = "CAFR_CD_FRAGMENT")
    private int codigo;

    @Column(name = "CAFR_DE_FRAGMENT")
    private String descripcion;

    @Column(name = "CAFR_NU_CUOTAS")
    private int nroCuotas;

    @Column(name = "CAFR_MT_CUOTA_MINIMA")
    private double cuotaMinima;


    public PlanPagoTienda() {
    }

    public PlanPagoTienda(int codigo, String descripcion, int nroCuotas, double cuotaMinima) {
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


    @Override
    public String toString() {
        String auxCodigo      = Integer.toString(this.getCodigo());
        String auxDescripcion;
        String auxNroCuotas   = Integer.toString(this.getNroCuotas());
        String auxCuotaMinima = Double.toString(this.getCuotaMinima());

        if (this.getDescripcion() == null) { auxDescripcion = "null";
        } else { auxDescripcion = this.getDescripcion(); }

        String logueo =   "Codigo=["      + auxCodigo
                      + "] Descripcion=[" + auxDescripcion
                      + "] Nro.Cuotas=["  + auxNroCuotas
                      + "] CuotaMinima=[" + auxCuotaMinima
                      + "]";
        return logueo;
    }

}
