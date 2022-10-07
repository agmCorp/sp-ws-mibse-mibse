package com.bse.servicios.ws.indivi;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import com.bse.accesodatos.comun.ItemCodiguera;
import com.bse.servicios.ws.comun.ErrorTiendaResp;

public class TiposVehiculosTiendaResp  extends ErrorTiendaResp implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<ItemCodiguera> tiposDeVehiculos;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * CONSTRUCTOR
     */
    public TiposVehiculosTiendaResp() {
        super();
        this.tiposDeVehiculos = null;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * CONSTRUCTOR
     * @param tiposDeVehiculos
     * @param codigoError
     * @param descripcionError
     */
    public TiposVehiculosTiendaResp(List<ItemCodiguera> tiposDeVehiculos, String codigoError, String descripcionError) {
        super(codigoError, descripcionError);
        this.tiposDeVehiculos = tiposDeVehiculos;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * CONSTRUCTOR
     * @param codigoError
     * @param descripcionError
     */
    public TiposVehiculosTiendaResp(String codigoError, String descripcionError) {
        super(codigoError, descripcionError);
        this.tiposDeVehiculos = null;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @XmlElementWrapper(name = "tiposVehiculosList")
    @XmlElement(name="tipoVehiculo")
    /**
     * @return the tiposDeVehiculos
     */
    public List<ItemCodiguera> getTiposDeVehiculos() {
        return tiposDeVehiculos;
    }
    /**
     * @param tiposDeVehiculos the tiposDeVehiculos to set
     */
    public void setTiposDeVehiculos(List<ItemCodiguera> tiposDeVehiculos) {
        this.tiposDeVehiculos = tiposDeVehiculos;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     *
     */
    @Override
    public String toString() {
      String logueo = null;
      String auxTiposDeVehiculos = null;
      String auxCodError  = null;
      String auxDescError = null;;
      String auxError     = "null";

      if        (this.getTiposDeVehiculos() == null)   { auxTiposDeVehiculos = "[null]";
      } else if (this.getTiposDeVehiculos().isEmpty()) { auxTiposDeVehiculos = "[VACIA]";
      } else { auxTiposDeVehiculos = this.getTiposDeVehiculos().toString(); }

      if (this.getCodigoError() != null)      { auxCodError = this.getCodigoError(); }
      if (this.getDescripcionError() != null) { auxDescError = this.getDescripcionError(); }
      if ( (auxCodError != null) || (auxDescError != null) ) {
          auxError = "[" + auxCodError + "] / [" + auxDescError + "]";
      }

      logueo = "TiposDeVehiculos=" + auxTiposDeVehiculos + " - Error=[" + auxError + "]";

      return logueo;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
