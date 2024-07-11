package com.bse.servicios.ws.indivi;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import com.bse.accesodatos.comun.ItemCodiguera;
import com.bse.servicios.ws.comun.ErrorTiendaResp;

public class VersionesVehiculosTiendaResp extends ErrorTiendaResp implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<ItemCodiguera> versionesDeVehiculos;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * CONSTRUCTOR
     */
    public VersionesVehiculosTiendaResp() {
        super();
        this.versionesDeVehiculos = null;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * CONSTRUCTOR
     * @param versionesDeVehiculos
     * @param codigoError
     * @param descripcionError
     */
    public VersionesVehiculosTiendaResp(List<ItemCodiguera> versionesDeVehiculos, String codigoError, String descripcionError) {
        super(codigoError, descripcionError);
        this.versionesDeVehiculos = versionesDeVehiculos;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * CONSTRUCTOR
     * @param codigoError
     * @param descripcionError
     */
    public VersionesVehiculosTiendaResp(String codigoError, String descripcionError) {
        super(codigoError, descripcionError);
        this.versionesDeVehiculos = null;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * @return the versionesDeVehiculos
     */
    @XmlElementWrapper(name = "versionesVehiculosList")
    @XmlElement(name="versionVehiculo")
    public List<ItemCodiguera> getVersionesDeVehiculos() {
        return versionesDeVehiculos;
    }
    /**
     * @param versionesDeVehiculos the versionesDeVehiculos to set
     */
    public void setVersionesDeVehiculos(List<ItemCodiguera> versionesDeVehiculos) {
        this.versionesDeVehiculos = versionesDeVehiculos;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     *
     */
    @Override
    public String toString() {
      String logueo = null;
      String auxVersionesDeVehiculos = null;
      String auxCodError  = null;
      String auxDescError = null;;
      String auxError     = "null";

      if        (this.getVersionesDeVehiculos() == null)   { auxVersionesDeVehiculos = "[null]";
      } else if (this.getVersionesDeVehiculos().isEmpty()) { auxVersionesDeVehiculos = "[VACIA]";
      } else { auxVersionesDeVehiculos = this.getVersionesDeVehiculos().toString(); }

      if (this.getCodigoError() != null)      { auxCodError = this.getCodigoError(); }
      if (this.getDescripcionError() != null) { auxDescError = this.getDescripcionError(); }
      if ( (auxCodError != null) || (auxDescError != null) ) {
          auxError = "[" + auxCodError + "] / [" + auxDescError + "]";
      }

      logueo = "VersionesDeVehiculos=" + auxVersionesDeVehiculos + " - Error=[" + auxError + "]";

      return logueo;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
