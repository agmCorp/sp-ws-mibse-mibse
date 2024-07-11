package com.bse.servicios.ws.indivi;

import com.bse.accesodatos.eindivi.PolizaIndiviTienda;
import com.bse.servicios.ws.comun.ErrorTiendaResp;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;

public class EmisionIndiviTiendaResp extends ErrorTiendaResp implements Serializable {

    private static final long serialVersionUID = 1L;

    private PolizaIndiviTienda polizaIndivi;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * CONSTRUCTOR
     */
    public EmisionIndiviTiendaResp() {
        super();
        this.polizaIndivi = null;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * CONSTRUCTOR
     * @param polizaIndivi
     * @param codigoError
     * @param descripcionError
     */
    public EmisionIndiviTiendaResp(PolizaIndiviTienda polizaIndivi, String codigoError, String descripcionError) {
        super(codigoError, descripcionError);
        this.polizaIndivi = polizaIndivi;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * CONSTRUCTOR
     * @param codigoError
     * @param descripcionError
     */
    public EmisionIndiviTiendaResp(String codigoError, String descripcionError) {
        super(codigoError, descripcionError);
        this.polizaIndivi = null;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * @return the polizaIndivi
     */
    @XmlElement(name="poliza")
    public PolizaIndiviTienda getPolizaIndivi() {
        return polizaIndivi;
    }
    /**
     * @param polizaIndivi the polizaIndivi to set
     */
    public void setPolizaIndivi(PolizaIndiviTienda polizaIndivi) {
        this.polizaIndivi = polizaIndivi;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     *
     */
    @Override
    public String toString() {
      String logueo = null;
      String auxPolizaIndivi = null;
      String auxCodError  = null;
      String auxDescError = null;;
      String auxError     = "null";

      if (this.getPolizaIndivi() == null) { auxPolizaIndivi = "PolizaIndivi=[null]";
      } else                              { auxPolizaIndivi = this.getPolizaIndivi().toString(); }

      if (this.getCodigoError() != null)      { auxCodError = this.getCodigoError(); }
      if (this.getDescripcionError() != null) { auxDescError = this.getDescripcionError(); }
      if ( (auxCodError != null) || (auxDescError != null) ) {
          auxError = "[" + auxCodError + "] / [" + auxDescError + "]";
      }

      logueo = auxPolizaIndivi + " - Error=[" + auxError + "]";

      return logueo;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
