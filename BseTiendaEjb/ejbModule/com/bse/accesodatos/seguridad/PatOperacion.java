/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bse.accesodatos.seguridad;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author paranz
 */
@Entity
@Table(name = "PAT_OPERACION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PatOperacion.findAll", query = "SELECT p FROM PatOperacion p"),
    @NamedQuery(name = "PatOperacion.findByInterfaz", query = "SELECT p FROM PatOperacion p WHERE p.patOperacionPK.interfaz = :interfaz"),
    @NamedQuery(name = "PatOperacion.findByMetodo", query = "SELECT p FROM PatOperacion p WHERE p.patOperacionPK.metodo = :metodo"),
    @NamedQuery(name = "PatOperacion.findByActivo", query = "SELECT p FROM PatOperacion p WHERE p.activo = :activo")})
public class PatOperacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PatOperacionPK patOperacionPK;
    @Basic(optional = false)
    @Column(name = "ACTIVO")
    private short activo;
    @JoinTable(name = "PAT_ROL_OPERACION", joinColumns = {
        @JoinColumn(name = "INTERFAZ", referencedColumnName = "INTERFAZ"),
        @JoinColumn(name = "METODO", referencedColumnName = "METODO")}, inverseJoinColumns = {
        @JoinColumn(name = "NOMBRE_ROL", referencedColumnName = "NOMBRE_ROL")})
    @ManyToMany(fetch = FetchType.LAZY)
    private List<PatRol> patRolList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "patOperacion", fetch = FetchType.LAZY)
    private List<PatSesion> patSesionList;

    public PatOperacion() {
    }

    public PatOperacion(PatOperacionPK patOperacionPK) {
        this.patOperacionPK = patOperacionPK;
    }

    public PatOperacion(PatOperacionPK patOperacionPK, short activo) {
        this.patOperacionPK = patOperacionPK;
        this.activo = activo;
    }

    public PatOperacion(String interfaz, String metodo) {
        this.patOperacionPK = new PatOperacionPK(interfaz, metodo);
    }

    public PatOperacionPK getPatOperacionPK() {
        return patOperacionPK;
    }

    public void setPatOperacionPK(PatOperacionPK patOperacionPK) {
        this.patOperacionPK = patOperacionPK;
    }

    public short getActivo() {
        return activo;
    }

    public void setActivo(short activo) {
        this.activo = activo;
    }

    @XmlTransient
    public List<PatRol> getPatRolList() {
        return patRolList;
    }

    public void setPatRolList(List<PatRol> patRolList) {
        this.patRolList = patRolList;
    }

    @XmlTransient
    public List<PatSesion> getPatSesionList() {
        return patSesionList;
    }

    public void setPatSesionList(List<PatSesion> patSesionList) {
        this.patSesionList = patSesionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (patOperacionPK != null ? patOperacionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PatOperacion)) {
            return false;
        }
        PatOperacion other = (PatOperacion) object;
        if ((this.patOperacionPK == null && other.patOperacionPK != null) || (this.patOperacionPK != null && !this.patOperacionPK.equals(other.patOperacionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bse.accesodatos.seguridad.PatOperacion[ patOperacionPK=" + patOperacionPK + " ]";
    }
    
}
