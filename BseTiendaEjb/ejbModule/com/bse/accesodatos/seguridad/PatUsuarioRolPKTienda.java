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
public class PatUsuarioRolPKTienda implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Basic(optional = false)
    @Column(name = "NOMBRE_ROL")
    private String nombreRol;
    @Basic(optional = false)
    @Column(name = "NOMBRE_USUARIO")
    private String nombreUsuario;

    public PatUsuarioRolPKTienda() {
    }

    public PatUsuarioRolPKTienda(String nombreRol, String nombreUsuario) {
        this.nombreRol = nombreRol;
        this.nombreUsuario = nombreUsuario;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nombreRol != null ? nombreRol.hashCode() : 0);
        hash += (nombreUsuario != null ? nombreUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PatUsuarioRolPKTienda)) {
            return false;
        }
        PatUsuarioRolPKTienda other = (PatUsuarioRolPKTienda) object;
        if ((this.nombreRol == null && other.nombreRol != null) || (this.nombreRol != null && !this.nombreRol.equals(other.nombreRol))) {
            return false;
        }
        if ((this.nombreUsuario == null && other.nombreUsuario != null) || (this.nombreUsuario != null && !this.nombreUsuario.equals(other.nombreUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bse.accesodatos.seguridad.PatUsuarioRolPK[ nombreRol=" + nombreRol + ", nombreUsuario=" + nombreUsuario + " ]";
    }
    
}
