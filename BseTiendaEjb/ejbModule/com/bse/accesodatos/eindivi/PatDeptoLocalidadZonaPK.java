package com.bse.accesodatos.eindivi;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class PatDeptoLocalidadZonaPK implements Serializable {

    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    @Column(name = "DEPTO_ID")
    private Integer deptoId;

    @Basic(optional = false)
    @Column(name = "LOCALIDAD_ID")
    private Integer localidadId;


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * CONSTRUCTOR
     */
    public PatDeptoLocalidadZonaPK() {

    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * CONSTRUCTOR
     * @param deptoId
     * @param localidadId
     */
    public PatDeptoLocalidadZonaPK(Integer deptoId, String deptoNombre, Integer localidadId) {
        this.deptoId = deptoId;
        this.localidadId = localidadId;

    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.deptoId != null ? this.deptoId.hashCode() : 0);
        hash += (this.localidadId != null ? this.localidadId.hashCode() : 0);
        return hash;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof PatDeptoLocalidadZonaPK)) {
            return false;
        }
        PatDeptoLocalidadZonaPK other = (PatDeptoLocalidadZonaPK) object;
        if ((this.deptoId == null && other.deptoId != null) || (this.deptoId != null && (this.deptoId.intValue() != other.deptoId.intValue()))) {
            return false;
        }
        if ((this.localidadId == null && other.localidadId != null) || (this.localidadId != null && (this.localidadId.intValue() != other.localidadId.intValue()))) {
            return false;
        }
        return true;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * @return the deptoId
     */
    public Integer getDeptoId() {
        return deptoId;
    }
    /**
     * @param deptoId the deptoId to set
     */
    public void setDeptoId(Integer deptoId) {
        this.deptoId = deptoId;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * @return the localidadId
     */
    public Integer getLocalidadId() {
        return localidadId;
    }
    /**
     * @param localidadId the localidadId to set
     */
    public void setLocalidadId(Integer localidadId) {
        this.localidadId = localidadId;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
