package com.bse.servicios.ws.opersonal;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;

import com.bse.accesodatos.eoperson.PolizaOPersonalTienda;
import com.bse.servicios.ws.comun.ErrorTiendaResp;

public class EmisionOPersonalTiendaResp extends ErrorTiendaResp implements Serializable {

    private static final long serialVersionUID = 1L;

    private PolizaOPersonalTienda polizaOPersonal;


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * CONSTRUCTOR
     */
    public EmisionOPersonalTiendaResp() {
        super();
        this.polizaOPersonal = null;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * CONSTRUCTOR
     * @param poliza
     * @param codigoError
     * @param descripcionError
     */
    public EmisionOPersonalTiendaResp(PolizaOPersonalTienda poliza, String codigoError, String descripcionError) {
        super(codigoError, descripcionError);
        this.polizaOPersonal = poliza;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * CONSTRUCTOR
     * @param codigoError
     * @param descripcionError
     */
    public EmisionOPersonalTiendaResp(String codigoError, String descripcionError) {
        super(codigoError, descripcionError);
        this.polizaOPersonal = null;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * @return the polizaOPersonal
     */
    @XmlElement(name="poliza")
    public PolizaOPersonalTienda getPolizaOPersonal() {
        return polizaOPersonal;
    }
    /**
     * @param polizaOPersonal the polizaOPersonal to set
     */
    public void setPolizaOPersonal(PolizaOPersonalTienda polizaOPersonal) {
        this.polizaOPersonal = polizaOPersonal;
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
      String auxCodError = null;
      String auxDescError = null;
      String auxError = "null";

      if (this.getPolizaOPersonal() == null) { auxPolizaIndivi = "PolizaOPersonal=[null]";
      } else                                 { auxPolizaIndivi = this.getPolizaOPersonal().toString(); }

      if (this.getCodigoError() != null)      { auxCodError = this.getCodigoError(); }
      if (this.getDescripcionError() != null) { auxDescError = this.getDescripcionError(); }
      if ( (auxCodError != null) || (auxDescError != null) ) {
          auxError = "["     + ((auxCodError!=null)?auxCodError:"null")
                   + "] / [" + ((auxDescError!=null)?auxDescError:"null") + "]";
      }

      logueo = auxPolizaIndivi + " - Error=[ " + auxError + " ]";

      return logueo;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
