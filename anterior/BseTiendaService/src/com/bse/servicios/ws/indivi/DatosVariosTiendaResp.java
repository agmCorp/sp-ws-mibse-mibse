package com.bse.servicios.ws.indivi;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;

import com.bse.accesodatos.eindivi.DatosVariosIndivi;
import com.bse.servicios.ws.comun.ErrorTiendaResp;

public class DatosVariosTiendaResp  extends ErrorTiendaResp implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private DatosVariosIndivi datosVarios;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * CONSTRUCTOR
     */
    public DatosVariosTiendaResp() {
        super();
        this.datosVarios = null;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * CONSTRUCTOR
     * @param datosVarios
     * @param codigoError
     * @param descripcionError
     */
    public DatosVariosTiendaResp(DatosVariosIndivi datosVarios, String codigoError, String descripcionError) {
        super(codigoError, descripcionError);
        this.datosVarios = datosVarios;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * CONSTRUCTOR
     * @param codigoError
     * @param descripcionError
     */
    public DatosVariosTiendaResp(String codigoError, String descripcionError) {
        super(codigoError, descripcionError);
        this.datosVarios = null;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * @return the datosVarios
     */
    @XmlElement(name="datosVarios")
    public DatosVariosIndivi getDatosVarios() {
        return datosVarios;
    }
    /**
     * @param datosVarios the datosVarios to set
     */
    public void setDatosVarios(DatosVariosIndivi datosVarios) {
        this.datosVarios = datosVarios;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     *
     */
    @Override
    public String toString() {
      String logueo = null;
      String auxDatosVarios = null;
      String auxCodError  = null;
      String auxDescError = null;;
      String auxError     = "null";

      if        (this.getDatosVarios() == null)   { auxDatosVarios = "DatosVarios {null}";
      } else { auxDatosVarios = this.getDatosVarios().toString(); }

      if (this.getCodigoError() != null)      { auxCodError = this.getCodigoError(); }
      if (this.getDescripcionError() != null) { auxDescError = this.getDescripcionError(); }
      if ( (auxCodError != null) || (auxDescError != null) ) {
          auxError = "[" + auxCodError + "] / [" + auxDescError + "]";
      }

      logueo = auxDatosVarios + " - Error=[" + auxError + "]";

      return logueo;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
