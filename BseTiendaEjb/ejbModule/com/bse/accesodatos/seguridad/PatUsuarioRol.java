/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bse.accesodatos.seguridad;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author paranz
 */
@Entity
@Table(name = "PAT_USUARIO_ROL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PatUsuarioRol.findAll", query = "SELECT p FROM PatUsuarioRol p"),
    @NamedQuery(name = "PatUsuarioRol.findByNombreRol", query = "SELECT p FROM PatUsuarioRol p WHERE p.patUsuarioRolPK.nombreRol = :nombreRol"),
    @NamedQuery(name = "PatUsuarioRol.findByNombreUsuario", query = "SELECT p FROM PatUsuarioRol p WHERE p.patUsuarioRolPK.nombreUsuario = :nombreUsuario")})
public class PatUsuarioRol implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PatUsuarioRolPK patUsuarioRolPK;
    @JoinColumn(name = "NOMBRE_ROL", referencedColumnName = "NOMBRE_ROL", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private PatRol patRol;

    public PatUsuarioRol() {
    }

    public PatUsuarioRol(PatUsuarioRolPK patUsuarioRolPK) {
        this.patUsuarioRolPK = patUsuarioRolPK;
    }

    public PatUsuarioRol(String nombreRol, String nombreUsuario) {
        this.patUsuarioRolPK = new PatUsuarioRolPK(nombreRol, nombreUsuario);
    }

    public PatUsuarioRolPK getPatUsuarioRolPK() {
        return patUsuarioRolPK;
    }

    public void setPatUsuarioRolPK(PatUsuarioRolPK patUsuarioRolPK) {
        this.patUsuarioRolPK = patUsuarioRolPK;
    }

    public PatRol getPatRol() {
        return patRol;
    }

    public void setPatRol(PatRol patRol) {
        this.patRol = patRol;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (patUsuarioRolPK != null ? patUsuarioRolPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PatUsuarioRol)) {
            return false;
        }
        PatUsuarioRol other = (PatUsuarioRol) object;
        if ((this.patUsuarioRolPK == null && other.patUsuarioRolPK != null) || (this.patUsuarioRolPK != null && !this.patUsuarioRolPK.equals(other.patUsuarioRolPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bse.accesodatos.seguridad.PatUsuarioRol[ patUsuarioRolPK=" + patUsuarioRolPK + " ]";
    }
    
}
