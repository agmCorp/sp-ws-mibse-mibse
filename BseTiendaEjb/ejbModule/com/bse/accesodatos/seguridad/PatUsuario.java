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
    @NamedQuery(name = "PatUsuario.findAll", query = "SELECT p FROM PatUsuario p"),
    @NamedQuery(name = "PatUsuario.findByNombreUsuario", query = "SELECT p FROM PatUsuario p WHERE p.nombreUsuario = :nombreUsuario"),
    @NamedQuery(name = "PatUsuario.findByContrasena", query = "SELECT p FROM PatUsuario p WHERE p.contrasena = :contrasena"),
    @NamedQuery(name = "PatUsuario.findByIdEntidadExterna", query = "SELECT p FROM PatUsuario p WHERE p.idEntidadExterna = :idEntidadExterna"),
    @NamedQuery(name = "PatUsuario.findByFechaCreacion", query = "SELECT p FROM PatUsuario p WHERE p.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "PatUsuario.findByFechaFin", query = "SELECT p FROM PatUsuario p WHERE p.fechaFin = :fechaFin"),
    @NamedQuery(name = "PatUsuario.findByValidarIp", query = "SELECT p FROM PatUsuario p WHERE p.validarIp = :validarIp"),
    @NamedQuery(name = "PatUsuario.findByContador", query = "SELECT p FROM PatUsuario p WHERE p.contador = :contador"),
    @NamedQuery(name = "PatUsuario.findByCambiarContrasena", query = "SELECT p FROM PatUsuario p WHERE p.cambiarContrasena = :cambiarContrasena")})
public class PatUsuario implements Serializable {
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
    private List<PatRol> patRolList;    
    @Basic(optional = false)
    @Column(name = "CAMBIAR_CONTRASENA")
    private short cambiarContrasena;

    public PatUsuario() {
    }

    public PatUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public PatUsuario(String nombreUsuario, String contrasena, short idEntidadExterna, Date fechaCreacion, short validarIp, short contador, short cambiarContrasena) {
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
    public List<PatRol> getPatRolList() {
        return patRolList;
    }

    public void setPatRolList(List<PatRol> patRolList) {
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
        if (!(object instanceof PatUsuario)) {
            return false;
        }
        PatUsuario other = (PatUsuario) object;
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
