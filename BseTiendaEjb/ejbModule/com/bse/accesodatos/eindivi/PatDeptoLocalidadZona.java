package com.bse.accesodatos.eindivi;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "PAT_DEPTO_LOCALIDAD_ZONA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PatDeptoLocalidadZona.findAll",              query = "SELECT c FROM PatDeptoLocalidadZona c"),
    @NamedQuery(name = "PatDeptoLocalidadZona.findDepartamentos",    query = "SELECT c FROM PatDeptoLocalidadZona c WHERE c.pk.localidadId=0 ORDER BY c.deptoNombre"),
    @NamedQuery(name = "PatDeptoLocalidadZona.findLocalidadesDepto", query = "SELECT c FROM PatDeptoLocalidadZona c WHERE c.pk.localidadId<>0 AND c.pk.deptoId = :codigoDepto ORDER BY c.localidadNombre"),
})
public class PatDeptoLocalidadZona implements Serializable {


    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private PatDeptoLocalidadZonaPK pk;

    @Column(name = "DEPTO_NOMBRE")
    private String deptoNombre;

    @Column(name = "LOCALIDAD_NOMBRE")
    private String localidadNombre;

    @Column(name = "ZONA_CIRCULACION")
    private Integer zona;


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * CONSTRUCTOR
     */
    public PatDeptoLocalidadZona() {

    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * @return the pk
     */
    public PatDeptoLocalidadZonaPK getPk() {
        return pk;
    }
    /**
     * @param pk the pk to set
     */
    public void setPk(PatDeptoLocalidadZonaPK pk) {
        this.pk = pk;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * @return the deptoNombre
     */
    public String getDeptoNombre() {
        return deptoNombre;
    }
    /**
     * @param deptoNombre the deptoNombre to set
     */
    public void setDeptoNombre(String deptoNombre) {
        this.deptoNombre = deptoNombre;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * @return the localidadNombre
     */
    public String getLocalidadNombre() {
        return localidadNombre;
    }
    /**
     * @param localidadNombre the localidadNombre to set
     */
    public void setLocalidadNombre(String localidadNombre) {
        this.localidadNombre = localidadNombre;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * @return the zona
     */
    public Integer getZona() {
        return zona;
    }
    /**
     * @param zona the zona to set
     */
    public void setZona(Integer zona) {
        this.zona = zona;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
