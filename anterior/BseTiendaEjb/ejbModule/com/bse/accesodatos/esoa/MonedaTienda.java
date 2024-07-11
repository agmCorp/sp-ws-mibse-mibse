package com.bse.accesodatos.esoa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CART_MONEDAS")

public class MonedaTienda {

    @Id
    @Column(name = "CAMO_CD_MONEDA")
    private String codigo;

    @Column(name = "CAMO_DE_MONEDA")
    private String descripcion;

    @Column(name = "CAMO_SM_MONEDA")
    private String simbolo;

    public MonedaTienda() {
        this.codigo = "";
        this.descripcion = "";
        this.simbolo="";
    }

    public MonedaTienda(String codigo, String descripcion, String simbolo) {
        super();
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.simbolo = simbolo;
    }


    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }


    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public String getDescripcion() {
        return descripcion;
    }


    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }
    public String getSimbolo() {
        return simbolo;
    }


    @Override
    public String toString() {
        String auxCodigo;
        String auxDescripcion;
        String auxSimbolo;

        if (this.getCodigo() == null) { auxCodigo = "null";
        } else { auxCodigo = this.getCodigo(); }

        if (this.getDescripcion() == null) { auxDescripcion = "null";
        } else { auxDescripcion = this.getDescripcion(); }

        if (this.getSimbolo() == null) { auxSimbolo = "null";
        } else { auxSimbolo = this.getSimbolo(); }

        /*String logueo =   "Codigo=["      + auxCodigo
                      + "] Descripcion=[" + auxDescripcion
                      + "] Simbolo=["     + auxSimbolo
                      + "]";*/
        String logueo = auxCodigo + "-" + auxDescripcion + "(" + auxSimbolo + ")" ;

        return logueo;
    }

}
