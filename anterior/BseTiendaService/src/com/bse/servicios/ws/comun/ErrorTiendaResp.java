package com.bse.servicios.ws.comun;

import javax.xml.bind.annotation.XmlElement;

public class ErrorTiendaResp {

    private String codigoError;
    private String descripcionError;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * CONSTRUCTOR
     */
    public ErrorTiendaResp() {
        this.codigoError      = null;
        this.descripcionError = null;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * CONSTRUCTOR
     * @param codigoError
     * @param descripcionError
     */
    public ErrorTiendaResp(String codigoError, String descripcionError) {
        this.codigoError      = codigoError;
        this.descripcionError = descripcionError;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     *
     */
    @Override
    public String toString() {
      String logueo = null;
      String auxCodError;
      String auxDescError;

      if (this.getCodigoError() == null) { auxCodError = "null";
      } else { auxCodError = this.getCodigoError(); }

      if (this.getDescripcionError() == null) { auxDescError = "null";
      } else { auxDescError = this.getDescripcionError(); }


        logueo =   "Cod.Error=["  + auxCodError
               + "] Desc.Error=[" + auxDescError
               + "]";

        return logueo;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     *
     * @return
     */
    @XmlElement(name="errorCodigo")
    public String getCodigoError() {
        return codigoError;
    }
    /**
     *
     * @param codigoError
     */
    public void setCodigoError(String codigoError) {
        this.codigoError = codigoError;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     *
     * @return
     */
    @XmlElement(name="errorDescripcion")
    public String getDescripcionError() {
        return descripcionError;
    }
    /**
     *
     * @param descripcionError
     */
    public void setDescripcionError(String descripcionError) {
        this.descripcionError = descripcionError;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
