package com.bse.servicios.ws.opersonal;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;

import com.bse.accesodatos.eoperson.CotizacionOPersonalTienda;
import com.bse.servicios.ws.comun.ErrorTiendaResp;

public class CotizacionOPersonalTiendaResp extends ErrorTiendaResp implements Serializable {

    private static final long serialVersionUID = 1L;

    private CotizacionOPersonalTienda cotizacionOPersonal;


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * CONSTRUCTOR
     */
    public CotizacionOPersonalTiendaResp() {
        super();
        this.cotizacionOPersonal = null;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * CONSTRUCTOR
     * @param cotizacion
     * @param codigoError
     * @param descripcionError
     */
    public CotizacionOPersonalTiendaResp(CotizacionOPersonalTienda cotizacion, String codigoError, String descripcionError) {
        super(codigoError, descripcionError);
        this.cotizacionOPersonal = cotizacion;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * CONSTRUCTOR
     * @param codigoError
     * @param descripcionError
     */
    public CotizacionOPersonalTiendaResp(String codigoError, String descripcionError) {
        super(codigoError, descripcionError);
        this.cotizacionOPersonal = null;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * @return the cotizacionOPersonal
     */
    @XmlElement(name="cotizacion")
    public CotizacionOPersonalTienda getCotizacionOPersonal() {
        return cotizacionOPersonal;
    }

    /**
     * @param cotizacionOPersonal the cotizacionOPersonal to set
     */
    public void setCotizacionOPersonal(CotizacionOPersonalTienda cotizacionOPersonal) {
        this.cotizacionOPersonal = cotizacionOPersonal;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     *
     */
    @Override
    public String toString() {
      String logueo;
      String auxCotizacionOPersonal;
      String auxCodError = null;
      String auxDescError = null;
      String auxError = "null";

      if (this.getCotizacionOPersonal() == null) { auxCotizacionOPersonal = "CotizacionOPersonal=[null]";
      } else                                     { auxCotizacionOPersonal = this.getCotizacionOPersonal().toString(); }

      if (this.getCodigoError() != null)      { auxCodError = this.getCodigoError(); }
      if (this.getDescripcionError() != null) { auxDescError = this.getDescripcionError(); }
      if ( (auxCodError != null) || (auxDescError != null) ) {
          auxError = "["     + ((auxCodError!=null)?auxCodError:"null")
                   + "] / [" + ((auxDescError!=null)?auxDescError:"null") + "]";
      }

      logueo = auxCotizacionOPersonal + " - Error=[ " + auxError + " ]";

      return logueo;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
