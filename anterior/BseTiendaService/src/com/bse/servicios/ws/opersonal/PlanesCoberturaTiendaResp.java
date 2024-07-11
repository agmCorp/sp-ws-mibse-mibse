package com.bse.servicios.ws.opersonal;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import com.bse.accesodatos.esoa.PlanCoberturaTienda;
import com.bse.servicios.ws.comun.ErrorTiendaResp;

public class PlanesCoberturaTiendaResp extends ErrorTiendaResp implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<PlanCoberturaTienda> planesCobertura;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * CONSTRUCTOR
     */
    public PlanesCoberturaTiendaResp() {
        super();
        this.planesCobertura = null;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * CONSTRUCTOR
     * @param planesCobertura
     * @param codigoError
     * @param descripcionError
     */
    public PlanesCoberturaTiendaResp(List<PlanCoberturaTienda> planesCobertura, String codigoError, String descripcionError) {
        super(codigoError, descripcionError);
        this.planesCobertura = planesCobertura;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * CONSTRUCTOR
     * @param codigoError
     * @param descripcionError
     */
    public PlanesCoberturaTiendaResp(String codigoError, String descripcionError) {
        super(codigoError, descripcionError);
        this.planesCobertura = null;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * @return the planesCobertura
     */
    @XmlElementWrapper(name = "planesCoberturaList")
    @XmlElement(name="planCobertura")
    public List<PlanCoberturaTienda> getPlanesCobertura() {
        return planesCobertura;
    }
    /**
     * @param planesCobertura the planesCobertura to set
     */
    public void setPlanesCobertura(List<PlanCoberturaTienda> planesCobertura) {
        this.planesCobertura = planesCobertura;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     *
     */
    @Override
    public String toString() {
      String logueo = null;
      String auxPlanesCobertura = null;
      String auxCodError  = null;
      String auxDescError = null;
      String auxError     = "null";

      if        (this.getPlanesCobertura() == null)   { auxPlanesCobertura = "[null]";
      } else if (this.getPlanesCobertura().isEmpty()) { auxPlanesCobertura = "[VACIA]";
      } else { auxPlanesCobertura = this.getPlanesCobertura().toString(); }

      if (this.getCodigoError() != null)      { auxCodError = this.getCodigoError(); }
      if (this.getDescripcionError() != null) { auxDescError = this.getDescripcionError(); }
      if ( (auxCodError != null) || (auxDescError != null) ) {
          auxError = "["     + ((auxCodError!=null)?auxCodError:"null")
                   + "] / [" + ((auxDescError!=null)?auxDescError:"null") + "]";
      }

      logueo = "PlanesCobertura=" + auxPlanesCobertura + " - Error=[ " + auxError + " ]";

      return logueo;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
