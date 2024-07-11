package com.bse.servicios.ws.comun;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;


public class ClienteDeudaTiendaResp extends ErrorTiendaResp implements Serializable {

    private static final long serialVersionUID = 1L;

    private String clienteConDeuda;


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * CONSTRUCTOR
     */
    public ClienteDeudaTiendaResp() {
        super();
        this.clienteConDeuda = null;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * CONSTRUCTOR
     * @param clienteConDeuda
     * @param codigoError
     * @param descripcionError
     */
    public ClienteDeudaTiendaResp(String clienteConDeuda, String codigoError, String descripcionError) {
        super(codigoError, descripcionError);
        this.clienteConDeuda = clienteConDeuda;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * CONSTRUCTOR
     * @param codigoError
     * @param descripcionError
     */
    public ClienteDeudaTiendaResp(String codigoError, String descripcionError) {
        super(codigoError, descripcionError);
        this.clienteConDeuda = null;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * @return the clienteConDeuda
     */
    @XmlElement(name="clienteConDeuda")
    public String getClienteConDeuda() {
        return clienteConDeuda;
    }
    /**
     * @param clienteConDeuda the clienteConDeuda to set
     */
    public void setClienteConDeuda(String clienteConDeuda) {
        this.clienteConDeuda = clienteConDeuda;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     *
     */
    @Override
    public String toString() {
      String logueo = null;
      String auxClienteConDeuda = null;
      String auxCodError  = null;
      String auxDescError = null;;
      String auxError     = "null";

      auxClienteConDeuda = (this.getClienteConDeuda()!=null)?this.getClienteConDeuda():"null";

      if ( (this.getCodigoError()!= null) || (this.getDescripcionError() != null) ) {
          auxCodError  = (this.getCodigoError()!= null)?this.getCodigoError():"null";
          auxDescError = (this.getDescripcionError()!= null)?this.getDescripcionError():"null"; 
          auxError = "[" + auxCodError + "] / [" + auxDescError + "]";
      }

      logueo = "ClienteConDeuda=[" + auxClienteConDeuda + "] - Error=[ " + auxError + " ]";

      return logueo;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
