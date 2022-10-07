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
public class PatUsuarioIpPKTienda implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "NOMBRE_USUARIO")
    private String nombreUsuario;
    @Basic(optional = false)
    @Column(name = "IP")
    private String ip;

    public PatUsuarioIpPKTienda() {
    }

    public PatUsuarioIpPKTienda(String nombreUsuario, String ip) {
        this.nombreUsuario = nombreUsuario;
        this.ip = ip;
    }


    public String getNombreUsuario() {
        return nombreUsuario;
    }
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }


    public String getIp() {
        return ip;
    }
    public void setIp(String ip) {
        this.ip = ip;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nombreUsuario != null ? nombreUsuario.hashCode() : 0);
        hash += (ip != null ? ip.hashCode() : 0);
        return hash;
    }


    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PatUsuarioIpPKTienda)) {
            return false;
        }
        PatUsuarioIpPKTienda other = (PatUsuarioIpPKTienda) object;
        if ((this.nombreUsuario == null && other.nombreUsuario != null) || (this.nombreUsuario != null && !this.nombreUsuario.equals(other.nombreUsuario))) {
            return false;
        }
        if ((this.ip == null && other.ip != null) || (this.ip != null && !this.ip.equals(other.ip))) {
            return false;
        }
        return true;
    }


    @Override
    public String toString() {
        return "com.bse.accesodatos.comun.PatUsuarioIpPK[ nombreUsuario=" + nombreUsuario + ", ip=" + ip + " ]";
    }

}
