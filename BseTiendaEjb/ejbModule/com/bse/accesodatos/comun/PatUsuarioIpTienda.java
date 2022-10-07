/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bse.accesodatos.comun;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author paranz
 */
@Entity
@Table(name = "PAT_USUARIO_IP")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PatUsuarioIpTienda.findAll", query = "SELECT p FROM PatUsuarioIpTienda p"),
    @NamedQuery(name = "PatUsuarioIpTienda.findByNombreUsuario", query = "SELECT p FROM PatUsuarioIpTienda p WHERE p.patUsuarioIpPK.nombreUsuario = :nombreUsuario"),
    @NamedQuery(name = "PatUsuarioIpTienda.findByIp", query = "SELECT p FROM PatUsuarioIpTienda p WHERE p.patUsuarioIpPK.ip = :ip")})
public class PatUsuarioIpTienda implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PatUsuarioIpPKTienda patUsuarioIpPK;

    public PatUsuarioIpTienda() {
    }


    public PatUsuarioIpTienda(PatUsuarioIpPKTienda patUsuarioIpPK) {
        this.patUsuarioIpPK = patUsuarioIpPK;
    }
    public PatUsuarioIpTienda(String nombreUsuario, String ip) {
        this.patUsuarioIpPK = new PatUsuarioIpPKTienda(nombreUsuario, ip);
    }


    public PatUsuarioIpPKTienda getPatUsuarioIpPK() {
        return patUsuarioIpPK;
    }
    public void setPatUsuarioIpPK(PatUsuarioIpPKTienda patUsuarioIpPK) {
        this.patUsuarioIpPK = patUsuarioIpPK;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (patUsuarioIpPK != null ? patUsuarioIpPK.hashCode() : 0);
        return hash;
    }


    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PatUsuarioIpTienda)) {
            return false;
        }
        PatUsuarioIpTienda other = (PatUsuarioIpTienda) object;
        if ((this.patUsuarioIpPK == null && other.patUsuarioIpPK != null) || (this.patUsuarioIpPK != null && !this.patUsuarioIpPK.equals(other.patUsuarioIpPK))) {
            return false;
        }
        return true;
    }


    @Override
    public String toString() {
        return "com.bse.accesodatos.comun.PatUsuarioIp[ patUsuarioIpPK=" + patUsuarioIpPK + " ]";
    }

}
