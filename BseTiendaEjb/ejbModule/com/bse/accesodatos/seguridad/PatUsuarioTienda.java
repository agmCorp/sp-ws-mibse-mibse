/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bse.accesodatos.seguridad;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author paranz
 */
@Entity
@Table(name = "PAT_USUARIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PatUsuarioTienda.findAll", query = "SELECT p FROM PatUsuarioTienda p"),
    @NamedQuery(name = "PatUsuarioTienda.findByNombreUsuario", query = "SELECT p FROM PatUsuarioTienda p WHERE p.nombreUsuario = :nombreUsuario"),
    @NamedQuery(name = "PatUsuarioTienda.findByContrasena", query = "SELECT p FROM PatUsuarioTienda p WHERE p.contrasena = :contrasena"),
    @NamedQuery(name = "PatUsuarioTienda.findByIdEntidadExterna", query = "SELECT p FROM PatUsuarioTienda p WHERE p.idEntidadExterna = :idEntidadExterna"),
    @NamedQuery(name = "PatUsuarioTienda.findByFechaCreacion", query = "SELECT p FROM PatUsuarioTienda p WHERE p.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "PatUsuarioTienda.findByFechaFin", query = "SELECT p FROM PatUsuarioTienda p WHERE p.fechaFin = :fechaFin"),
    @NamedQuery(name = "PatUsuarioTienda.findByValidarIp", query = "SELECT p FROM PatUsuarioTienda p WHERE p.validarIp = :validarIp"),
    @NamedQuery(name = "PatUsuarioTienda.findByContador", query = "SELECT p FROM PatUsuarioTienda p WHERE p.contador = :contador"),
    @NamedQuery(name = "PatUsuarioTienda.findByCambiarContrasena", query = "SELECT p FROM PatUsuarioTienda p WHERE p.cambiarContrasena = :cambiarContrasena")})
public class PatUsuarioTienda implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "NOMBRE_USUARIO")
    private String nombreUsuario;
    @Basic(optional = false)
    @Column(name = "CONTRASENA")
    private String contrasena;
    @Basic(optional = false)
    @Column(name = "ID_ENTIDAD_EXTERNA")
    private short idEntidadExterna;
    @Basic(optional = false)
    @Column(name = "FECHA_CREACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "FECHA_FIN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFin;
    @Basic(optional = false)
    @Column(name = "VALIDAR_IP")
    private short validarIp;
    @Basic(optional = false)
    @Column(name = "CONTADOR")
    private short contador;
    @ManyToMany(mappedBy = "patUsuarioList", fetch = FetchType.LAZY)
    private List<PatRolTienda> patRolList;    
    @Basic(optional = false)
    @Column(name = "CAMBIAR_CONTRASENA")
    private short cambiarContrasena;

    public PatUsuarioTienda() {
    }

    public PatUsuarioTienda(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public PatUsuarioTienda(String nombreUsuario, String contrasena, short idEntidadExterna, Date fechaCreacion, short validarIp, short contador, short cambiarContrasena) {
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.idEntidadExterna = idEntidadExterna;
        this.fechaCreacion = fechaCreacion;
        this.validarIp = validarIp;
        this.contador = contador;
        this.cambiarContrasena = cambiarContrasena;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public short getIdEntidadExterna() {
        return idEntidadExterna;
    }

    public void setIdEntidadExterna(short idEntidadExterna) {
        this.idEntidadExterna = idEntidadExterna;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public short getValidarIp() {
        return validarIp;
    }

    public void setValidarIp(short validarIp) {
        this.validarIp = validarIp;
    }

    public short getContador() {
        return contador;
    }

    public void setContador(short contador) {
        this.contador = contador;
    }

    @XmlTransient
    public List<PatRolTienda> getPatRolList() {
        return patRolList;
    }

    public void setPatRolList(List<PatRolTienda> patRolList) {
        this.patRolList = patRolList;
    }    
    
    public short getCambiarContrasena() {
        return cambiarContrasena;
    }

    public void setCambiarContrasena(short cambiarContrasena) {
        this.cambiarContrasena = cambiarContrasena;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nombreUsuario != null ? nombreUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PatUsuarioTienda)) {
            return false;
        }
        PatUsuarioTienda other = (PatUsuarioTienda) object;
        if ((this.nombreUsuario == null && other.nombreUsuario != null) || (this.nombreUsuario != null && !this.nombreUsuario.equals(other.nombreUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bse.accesodatos.seguridad.PatUsuario[ nombreUsuario=" + nombreUsuario + " ]";
    }
    
}
