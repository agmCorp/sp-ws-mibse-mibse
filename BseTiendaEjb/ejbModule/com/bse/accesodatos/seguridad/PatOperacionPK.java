/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bse.accesodatos.seguridad;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author paranz
 */
@Embeddable
public class PatOperacionPK implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Basic(optional = false)
    @Column(name = "INTERFAZ")
    private String interfaz;
    @Basic(optional = false)
    @Column(name = "METODO")
    private String metodo;

    public PatOperacionPK() {
    }

    public PatOperacionPK(String interfaz, String metodo) {
        this.interfaz = interfaz;
        this.metodo = metodo;
    }

    public String getInterfaz() {
        return interfaz;
    }

    public void setInterfaz(String interfaz) {
        this.interfaz = interfaz;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (interfaz != null ? interfaz.hashCode() : 0);
        hash += (metodo != null ? metodo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PatOperacionPK)) {
            return false;
        }
        PatOperacionPK other = (PatOperacionPK) object;
        if ((this.interfaz == null && other.interfaz != null) || (this.interfaz != null && !this.interfaz.equals(other.interfaz))) {
            return false;
        }
        if ((this.metodo == null && other.metodo != null) || (this.metodo != null && !this.metodo.equals(other.metodo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bse.accesodatos.seguridad.PatOperacionPK[ interfaz=" + interfaz + ", metodo=" + metodo + " ]";
    }
    
}
