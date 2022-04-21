/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bse.accesodatos.seguridad;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author paranz
 */
@Entity
@Table(name = "PAT_ROL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PatRol.findAll", query = "SELECT p FROM PatRol p"),
    @NamedQuery(name = "PatRol.findByNombreRol", query = "SELECT p FROM PatRol p WHERE p.nombreRol = :nombreRol")})
public class PatRol implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "NOMBRE_ROL")
    private String nombreRol;
    @JoinTable(name = "PAT_USUARIO_ROL", joinColumns = {
        @JoinColumn(name = "NOMBRE_ROL", referencedColumnName = "NOMBRE_ROL")}, inverseJoinColumns = {
        @JoinColumn(name = "NOMBRE_USUARIO", referencedColumnName = "NOMBRE_USUARIO")})
    @ManyToMany(fetch = FetchType.LAZY)
    private List<PatUsuario> patUsuarioList;
    @ManyToMany(mappedBy = "patRolList", fetch = FetchType.LAZY)
    private List<PatOperacion> patOperacionList;

    public PatRol() {
    }

    public PatRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    @XmlTransient
    public List<PatUsuario> getPatUsuarioList() {
        return patUsuarioList;
    }

    public void setPatUsuarioList(List<PatUsuario> patUsuarioList) {
        this.patUsuarioList = patUsuarioList;
    }

    @XmlTransient
    public List<PatOperacion> getPatOperacionList() {
        return patOperacionList;
    }

    public void setPatOperacionList(List<PatOperacion> patOperacionList) {
        this.patOperacionList = patOperacionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nombreRol != null ? nombreRol.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PatRol)) {
            return false;
        }
        PatRol other = (PatRol) object;
        if ((this.nombreRol == null && other.nombreRol != null) || (this.nombreRol != null && !this.nombreRol.equals(other.nombreRol))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bse.accesodatos.seguridad.PatRol[ nombreRol=" + nombreRol + " ]";
    }
    
}
