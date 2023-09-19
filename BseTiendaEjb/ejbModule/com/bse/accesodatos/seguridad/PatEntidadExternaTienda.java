/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bse.accesodatos.seguridad;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author paranz
 */
@Entity
@Table(name = "PAT_ENTIDAD_EXTERNA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PatEntidadExternaTienda.findAll", query = "SELECT p FROM PatEntidadExternaTienda p"),
    @NamedQuery(name = "PatEntidadExternaTienda.findByIdEntidadExterna", query = "SELECT p FROM PatEntidadExternaTienda p WHERE p.idEntidadExterna = :idEntidadExterna"),
    @NamedQuery(name = "PatEntidadExternaTienda.findByNombre", query = "SELECT p FROM PatEntidadExternaTienda p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "PatEntidadExternaTienda.findByDireccionIp", query = "SELECT p FROM PatEntidadExternaTienda p WHERE p.direccionIp = :direccionIp"),
    @NamedQuery(name = "PatEntidadExternaTienda.findByValidarIp", query = "SELECT p FROM PatEntidadExternaTienda p WHERE p.validarIp = :validarIp"),
    @NamedQuery(name = "PatEntidadExternaTienda.findByVendorId", query = "SELECT p FROM PatEntidadExternaTienda p WHERE p.vendorId = :vendorId"),
    @NamedQuery(name = "PatEntidadExternaTienda.findByVendorSiteId", query = "SELECT p FROM PatEntidadExternaTienda p WHERE p.vendorSiteId = :vendorSiteId")})
public class PatEntidadExternaTienda implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_ENTIDAD_EXTERNA")
    private Short idEntidadExterna;
    @Basic(optional = false)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "DIRECCION_IP")
    private String direccionIp;
    @Basic(optional = false)
    @Column(name = "VALIDAR_IP")
    private short validarIp;
    @Basic(optional = false)
    @Column(name = "VENDOR_ID")
    private BigInteger vendorId;
    @Basic(optional = false)
    @Column(name = "VENDOR_SITE_ID")
    private BigInteger vendorSiteId;

    public PatEntidadExternaTienda() {
    }

    public PatEntidadExternaTienda(Short idEntidadExterna) {
        this.idEntidadExterna = idEntidadExterna;
    }

    public PatEntidadExternaTienda(Short idEntidadExterna, String nombre, String direccionIp, short validarIp, BigInteger vendorId, BigInteger vendorSiteId) {
        this.idEntidadExterna = idEntidadExterna;
        this.nombre = nombre;
        this.direccionIp = direccionIp;
        this.validarIp = validarIp;
        this.vendorId = vendorId;
        this.vendorSiteId = vendorSiteId;
    }

    public Short getIdEntidadExterna() {
        return idEntidadExterna;
    }

    public void setIdEntidadExterna(Short idEntidadExterna) {
        this.idEntidadExterna = idEntidadExterna;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccionIp() {
        return direccionIp;
    }

    public void setDireccionIp(String direccionIp) {
        this.direccionIp = direccionIp;
    }

    public short getValidarIp() {
        return validarIp;
    }

    public void setValidarIp(short validarIp) {
        this.validarIp = validarIp;
    }

    public BigInteger getVendorId() {
        return vendorId;
    }

    public void setVendorId(BigInteger vendorId) {
        this.vendorId = vendorId;
    }

    public BigInteger getVendorSiteId() {
        return vendorSiteId;
    }

    public void setVendorSiteId(BigInteger vendorSiteId) {
        this.vendorSiteId = vendorSiteId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEntidadExterna != null ? idEntidadExterna.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PatEntidadExternaTienda)) {
            return false;
        }
        PatEntidadExternaTienda other = (PatEntidadExternaTienda) object;
        if ((this.idEntidadExterna == null && other.idEntidadExterna != null) || (this.idEntidadExterna != null && !this.idEntidadExterna.equals(other.idEntidadExterna))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bse.accesodatos.seguridad.PatEntidadExterna[ idEntidadExterna=" + idEntidadExterna + " ]";
    }
    
}
