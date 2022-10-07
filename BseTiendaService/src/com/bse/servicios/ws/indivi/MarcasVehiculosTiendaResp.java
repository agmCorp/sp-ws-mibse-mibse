package com.bse.servicios.ws.indivi;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import com.bse.accesodatos.comun.ItemCodiguera;
import com.bse.servicios.ws.comun.ErrorTiendaResp;

public class MarcasVehiculosTiendaResp  extends ErrorTiendaResp implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<ItemCodiguera> marcasDeVehiculos;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * CONSTRUCTOR
     */
    public MarcasVehiculosTiendaResp() {
        super();
        this.marcasDeVehiculos = null;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * CONSTRUCTOR
     * @param marcasDeVehiculos
     * @param codigoError
     * @param descripcionError
     */
    public MarcasVehiculosTiendaResp(List<ItemCodiguera> marcasDeVehiculos, String codigoError, String descripcionError) {
        super(codigoError, descripcionError);
        this.marcasDeVehiculos = marcasDeVehiculos;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * CONSTRUCTOR
     * @param codigoError
     * @param descripcionError
     */
    public MarcasVehiculosTiendaResp(String codigoError, String descripcionError) {
        super(codigoError, descripcionError);
        this.marcasDeVehiculos = null;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @XmlElementWrapper(name = "marcasVehiculosList")
    @XmlElement(name="marcaVehiculo")
    /**
     * @return the marcasDeVehiculos
     */
    public List<ItemCodiguera> getMarcasDeVehiculos() {
        return marcasDeVehiculos;
    }
    /**
     * @param marcasDeVehiculos the marcasDeVehiculos to set
     */
    public void setMarcasDeVehiculos(List<ItemCodiguera> marcasDeVehiculos) {
        this.marcasDeVehiculos = marcasDeVehiculos;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     *
     */
    @Override
    public String toString() {
      String logueo = null;
      String auxMarcasDeVehiculos = null;
      String auxCodError  = null;
      String auxDescError = null;;
      String auxError     = "null";

      if        (this.getMarcasDeVehiculos() == null)   { auxMarcasDeVehiculos = "[null]";
      } else if (this.getMarcasDeVehiculos().isEmpty()) { auxMarcasDeVehiculos = "[VACIA]";
      } else { auxMarcasDeVehiculos = this.getMarcasDeVehiculos().toString(); }

      if (this.getCodigoError() != null)      { auxCodError = this.getCodigoError(); }
      if (this.getDescripcionError() != null) { auxDescError = this.getDescripcionError(); }
      if ( (auxCodError != null) || (auxDescError != null) ) {
          auxError = "[" + auxCodError + "] / [" + auxDescError + "]";
      }

      logueo = "MarcasDeVehiculos=" + auxMarcasDeVehiculos + " - Error=[" + auxError + "]";

      return logueo;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
