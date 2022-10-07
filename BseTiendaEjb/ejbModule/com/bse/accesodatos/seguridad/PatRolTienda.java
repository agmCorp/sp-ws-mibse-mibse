/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bse.accesodatos.seguridad;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
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
    @NamedQuery(name = "PatRolTienda.findAll", query = "SELECT p FROM PatRolTienda p"),
    @NamedQuery(name = "PatRolTienda.findByNombreRol", query = "SELECT p FROM PatRolTienda p WHERE p.nombreRol = :nombreRol")})
public class PatRolTienda implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "NOMBRE_ROL")
    private String nombreRol;
    @JoinTable(name = "PAT_USUARIO_ROL", joinColumns = {
        @JoinColumn(name = "NOMBRE_ROL", referencedColumnName = "NOMBRE_ROL")}, inverseJoinColumns = {
        @JoinColumn(name = "NOMBRE_USUARIO", referencedColumnName = "NOMBRE_USUARIO")})
    @ManyToMany(fetch = FetchType.LAZY)
    private List<PatUsuarioTienda> patUsuarioList;
    @ManyToMany(mappedBy = "patRolList", fetch = FetchType.LAZY)
    private List<PatOperacionTienda> patOperacionList;

    public PatRolTienda() {
    }

    public PatRolTienda(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    @XmlTransient
    public List<PatUsuarioTienda> getPatUsuarioList() {
        return patUsuarioList;
    }

    public void setPatUsuarioList(List<PatUsuarioTienda> patUsuarioList) {
        this.patUsuarioList = patUsuarioList;
    }

    @XmlTransient
    public List<PatOperacionTienda> getPatOperacionList() {
        return patOperacionList;
    }

    public void setPatOperacionList(List<PatOperacionTienda> patOperacionList) {
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
        if (!(object instanceof PatRolTienda)) {
            return false;
        }
        PatRolTienda other = (PatRolTienda) object;
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
