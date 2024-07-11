/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bse.accesodatos.comun;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "CRET_TABLAS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CretTablasTienda.findAll", query = "SELECT c FROM CretTablasTienda c"),
    @NamedQuery(name = "CretTablasTienda.findTabla", query = "SELECT c FROM CretTablasTienda c WHERE c.cretTablasPK.tabla = :codigoTabla"),
    @NamedQuery(name = "CretTablasTienda.findByClave", query = "SELECT c FROM CretTablasTienda c WHERE c.cretTablasPK.tabla = :codigoTabla and c.dato1 = :codigoDato"),
})

public class CretTablasTienda implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    protected CretTablasPKTienda cretTablasPK;

    @Column(name = "CRTB_DE_DATO")
    private String descripcion;

    @Column(name = "CRTB_DATO1")
    private String dato1;

    @Column(name = "CRTB_DATO2")
    private String dato2;

    @Column(name = "CRTB_DATO3")
    private String dato3;

    @Column(name = "CRTB_CAUS_CD_USUARIO")
    private String usuario;


    public CretTablasTienda() {
    }


    public CretTablasPKTienda getCretTablasPK() {
        return cretTablasPK;
    }
    public void setCretTablasPK(CretTablasPKTienda cretTablasPK) {
        this.cretTablasPK = cretTablasPK;
    }

    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


    public String getDato1() {
        return dato1;
    }
    public void setDato1(String dato1) {
        this.dato1 = dato1;
    }


    public String getDato2() {
        return dato2;
    }
    public void setDato2(String dato2) {
        this.dato2 = dato2;
    }


    public String getDato3() {
        return dato3;
    }
    public void setDato3(String dato3) {
        this.dato3 = dato3;
    }


    public String getUsuario() {
        return usuario;
    }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

}
