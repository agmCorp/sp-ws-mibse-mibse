package com.bse.servicios.ws.indivi;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;

import com.bse.accesodatos.eindivi.CotizacionIndiviTienda;
import com.bse.servicios.ws.comun.ErrorTiendaResp;

public class CotizacionIndiviTiendaResp extends ErrorTiendaResp implements Serializable {

    private static final long serialVersionUID = 1L;

    private CotizacionIndiviTienda cotizacionIndivi;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * CONSTRUCTOR
     */
    public CotizacionIndiviTiendaResp() {
        super();
        this.cotizacionIndivi = null;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * CONSTRUCTOR
     * @param cotizacionIndivi
     * @param codigoError
     * @param descripcionError
     */
    public CotizacionIndiviTiendaResp(CotizacionIndiviTienda cotizacionIndivi, String codigoError, String descripcionError) {
        super(codigoError, descripcionError);
        this.cotizacionIndivi = cotizacionIndivi;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * CONSTRUCTOR
     * @param codigoError
     * @param descripcionError
     */
    public CotizacionIndiviTiendaResp(String codigoError, String descripcionError) {
        super(codigoError, descripcionError);
        this.cotizacionIndivi = null;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     *
     * @return
     */
    @XmlElement(name="cotizacion")
    public CotizacionIndiviTienda getCotizacionIndivi(){
        return this.cotizacionIndivi;
    }
    /**
     *
     * @param cotizacionIndivi
     */
    public void setCotizacionIndivi(CotizacionIndiviTienda cotizacionIndivi){
        this.cotizacionIndivi = cotizacionIndivi;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     *
     */
    @Override
    public String toString() {
      String logueo = null;
      String auxCotizacionIndivi = null;
      String auxCodError  = null;
      String auxDescError = null;;
      String auxError     = "null";

      if (this.getCotizacionIndivi() == null) { auxCotizacionIndivi = "CotizacionIndivi=[null]";
      } else                                  { auxCotizacionIndivi = this.getCotizacionIndivi().toString(); }

      if (this.getCodigoError() != null)      { auxCodError = this.getCodigoError(); }
      if (this.getDescripcionError() != null) { auxDescError = this.getDescripcionError(); }
      if ( (auxCodError != null) || (auxDescError != null) ) {
          auxError = "[" + auxCodError + "] / [" + auxDescError + "]";
      }

      logueo = auxCotizacionIndivi + " - Error=[" + auxError + "]";

      return logueo;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
