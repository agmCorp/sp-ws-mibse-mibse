/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bse.accesodatos.comun;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.persistence.Cacheable;

/**
 *
 * @author paranz
 */
@Entity
@Cacheable(false)
@Table(name = "PAT_PARAMETROS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PatParametros.findAll", query = "SELECT p FROM PatParametros p"),
    @NamedQuery(name = "PatParametros.findByAplicacion", query = "SELECT p FROM PatParametros p WHERE p.patParametrosPK.aplicacion = :aplicacion"),
    @NamedQuery(name = "PatParametros.findByClave", query = "SELECT p FROM PatParametros p WHERE p.patParametrosPK.clave = :clave"),
    @NamedQuery(name = "PatParametros.findBySubclave", query = "SELECT p FROM PatParametros p WHERE p.patParametrosPK.subclave = :subclave"),
    @NamedQuery(name = "PatParametros.findByValor", query = "SELECT p FROM PatParametros p WHERE p.valor = :valor")})
public class PatParametros implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PatParametrosPK patParametrosPK;
    @Basic(optional = false)
    @Column(name = "VALOR")
    private String valor;

    public PatParametros() {
    }

    public PatParametros(PatParametrosPK patParametrosPK) {
        this.patParametrosPK = patParametrosPK;
    }

    public PatParametros(PatParametrosPK patParametrosPK, String valor) {
        this.patParametrosPK = patParametrosPK;
        this.valor = valor;
    }

    public PatParametros(String aplicacion, String clave, String subclave) {
        this.patParametrosPK = new PatParametrosPK(aplicacion, clave, subclave);
    }

    public PatParametrosPK getPatParametrosPK() {
        return patParametrosPK;
    }

    public void setPatParametrosPK(PatParametrosPK patParametrosPK) {
        this.patParametrosPK = patParametrosPK;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (patParametrosPK != null ? patParametrosPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PatParametros)) {
            return false;
        }
        PatParametros other = (PatParametros) object;
        if ((this.patParametrosPK == null && other.patParametrosPK != null) || (this.patParametrosPK != null && !this.patParametrosPK.equals(other.patParametrosPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bse.accesodatos.comun.PatParametros[ patParametrosPK=" + patParametrosPK + " ]";
    }
    
}
