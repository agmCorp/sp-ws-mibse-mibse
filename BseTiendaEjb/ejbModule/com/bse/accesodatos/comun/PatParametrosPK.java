/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bse.accesodatos.comun;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author paranz
 */
@Embeddable
public class PatParametrosPK implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Basic(optional = false)
    @Column(name = "APLICACION")
    private String aplicacion;
    @Basic(optional = false)
    @Column(name = "CLAVE")
    private String clave;
    @Basic(optional = false)
    @Column(name = "SUBCLAVE")
    private String subclave;

    public PatParametrosPK() {
    }

    public PatParametrosPK(String aplicacion, String clave, String subclave) {
        this.aplicacion = aplicacion;
        this.clave = clave;
        this.subclave = subclave;
    }

    public String getAplicacion() {
        return aplicacion;
    }

    public void setAplicacion(String aplicacion) {
        this.aplicacion = aplicacion;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getSubclave() {
        return subclave;
    }

    public void setSubclave(String subclave) {
        this.subclave = subclave;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (aplicacion != null ? aplicacion.hashCode() : 0);
        hash += (clave != null ? clave.hashCode() : 0);
        hash += (subclave != null ? subclave.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PatParametrosPK)) {
            return false;
        }
        PatParametrosPK other = (PatParametrosPK) object;
        if ((this.aplicacion == null && other.aplicacion != null) || (this.aplicacion != null && !this.aplicacion.equals(other.aplicacion))) {
            return false;
        }
        if ((this.clave == null && other.clave != null) || (this.clave != null && !this.clave.equals(other.clave))) {
            return false;
        }
        if ((this.subclave == null && other.subclave != null) || (this.subclave != null && !this.subclave.equals(other.subclave))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bse.accesodatos.comun.PatParametrosPK[ aplicacion=" + aplicacion + ", clave=" + clave + ", subclave=" + subclave + " ]";
    }
    
}
