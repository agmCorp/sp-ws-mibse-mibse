/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bse.accesodatos.seguridad;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author paranz
 */
@Entity
@Table(name = "PAT_SESION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PatSesionTienda.findAll", query = "SELECT p FROM PatSesionTienda p"),
    @NamedQuery(name = "PatSesionTienda.findByIdSesion", query = "SELECT p FROM PatSesionTienda p WHERE p.idSesion = :idSesion"),
    @NamedQuery(name = "PatSesionTienda.findByNombreUsuario", query = "SELECT p FROM PatSesionTienda p WHERE p.nombreUsuario = :nombreUsuario"),
    @NamedQuery(name = "PatSesionTienda.findByDireccionIp", query = "SELECT p FROM PatSesionTienda p WHERE p.direccionIp = :direccionIp"),
    @NamedQuery(name = "PatSesionTienda.findByFechaLogueo", query = "SELECT p FROM PatSesionTienda p WHERE p.fechaLogueo = :fechaLogueo"),
    @NamedQuery(name = "PatSesionTienda.findByResultado", query = "SELECT p FROM PatSesionTienda p WHERE p.resultado = :resultado"),
    @NamedQuery(name = "PatSesionTienda.findByParametros", query = "SELECT p FROM PatSesionTienda p WHERE p.parametros = :parametros")})
public class PatSesionTienda implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "SEQSESION")
    @SequenceGenerator(name="SEQSESION", sequenceName = "SEQ_PAT_SESION", allocationSize = 1)     
    @Column(name = "ID_SESION")
    private Long idSesion;
    @Column(name = "NOMBRE_USUARIO")
    private String nombreUsuario;
    @Basic(optional = false)
    @Column(name = "DIRECCION_IP")
    private String direccionIp;
    @Basic(optional = false)
    @Column(name = "FECHA_LOGUEO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaLogueo;
    @Basic(optional = false)
    @Column(name = "RESULTADO")
    private short resultado;
    @Column(name = "PARAMETROS")
    private String parametros;
    @JoinColumns({
        @JoinColumn(name = "INTERFAZ", referencedColumnName = "INTERFAZ"),
        @JoinColumn(name = "METODO", referencedColumnName = "METODO")})
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private PatOperacionTienda patOperacion;


	public PatSesionTienda() {
    }

    public PatSesionTienda(Long idSesion) {
        this.idSesion = idSesion;
    }

    public PatSesionTienda(Long idSesion, String direccionIp, Date fechaLogueo, short resultado) {
        this.idSesion = idSesion;
        this.direccionIp = direccionIp;
        this.fechaLogueo = fechaLogueo;
        this.resultado = resultado;
    }

    public Long getIdSesion() {
        return idSesion;
    }

    public void setIdSesion(Long idSesion) {
        this.idSesion = idSesion;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getDireccionIp() {
        return direccionIp;
    }

    public void setDireccionIp(String direccionIp) {
        this.direccionIp = direccionIp;
    }

    public Date getFechaLogueo() {
        return fechaLogueo;
    }

    public void setFechaLogueo(Date fechaLogueo) {
        this.fechaLogueo = fechaLogueo;
    }

    public short getResultado() {
        return resultado;
    }

    public void setResultado(short resultado) {
        this.resultado = resultado;
    }

    public String getParametros() {
        return parametros;
    }

    public void setParametros(String parametros) {
        this.parametros = parametros;
    }

    public PatOperacionTienda getPatOperacion() {
    	if (patOperacion == null)
    		patOperacion = new PatOperacionTienda();
		return patOperacion;
	}

	public void setPatOperacion(PatOperacionTienda patOperacion) {
		this.patOperacion = patOperacion;
	}

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSesion != null ? idSesion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PatSesionTienda)) {
            return false;
        }
        PatSesionTienda other = (PatSesionTienda) object;
        if ((this.idSesion == null && other.idSesion != null) || (this.idSesion != null && !this.idSesion.equals(other.idSesion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bse.accesodatos.seguridad.PatSesion[ idSesion=" + idSesion + " ]";
    }
    
}
