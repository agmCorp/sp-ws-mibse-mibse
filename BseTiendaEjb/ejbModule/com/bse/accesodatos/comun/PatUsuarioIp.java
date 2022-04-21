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
    @NamedQuery(name = "PatUsuarioIp.findAll", query = "SELECT p FROM PatUsuarioIp p"),
    @NamedQuery(name = "PatUsuarioIp.findByNombreUsuario", query = "SELECT p FROM PatUsuarioIp p WHERE p.patUsuarioIpPK.nombreUsuario = :nombreUsuario"),
    @NamedQuery(name = "PatUsuarioIp.findByIp", query = "SELECT p FROM PatUsuarioIp p WHERE p.patUsuarioIpPK.ip = :ip")})
public class PatUsuarioIp implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PatUsuarioIpPK patUsuarioIpPK;

    public PatUsuarioIp() {
    }

    public PatUsuarioIp(PatUsuarioIpPK patUsuarioIpPK) {
        this.patUsuarioIpPK = patUsuarioIpPK;
    }

    public PatUsuarioIp(String nombreUsuario, String ip) {
        this.patUsuarioIpPK = new PatUsuarioIpPK(nombreUsuario, ip);
    }

    public PatUsuarioIpPK getPatUsuarioIpPK() {
        return patUsuarioIpPK;
    }

    public void setPatUsuarioIpPK(PatUsuarioIpPK patUsuarioIpPK) {
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
        if (!(object instanceof PatUsuarioIp)) {
            return false;
        }
        PatUsuarioIp other = (PatUsuarioIp) object;
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
