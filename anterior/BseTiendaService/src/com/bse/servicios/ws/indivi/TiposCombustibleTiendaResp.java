package com.bse.servicios.ws.indivi;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import com.bse.accesodatos.comun.ItemCodiguera;
import com.bse.servicios.ws.comun.ErrorTiendaResp;

public class TiposCombustibleTiendaResp extends ErrorTiendaResp implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<ItemCodiguera> tiposDeCombustibles;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * CONSTRUCTOR
     */
    public TiposCombustibleTiendaResp() {
        super();
        this.tiposDeCombustibles = null;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * CONSTRUCTOR
     * @param tiposDeCombustibles
     * @param codigoError
     * @param descripcionError
     */
    public TiposCombustibleTiendaResp(List<ItemCodiguera> tiposDeCombustibles, String codigoError, String descripcionError) {
        super(codigoError, descripcionError);
        this.tiposDeCombustibles = tiposDeCombustibles;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * CONSTRUCTOR
     * @param codigoError
     * @param descripcionError
     */
    public TiposCombustibleTiendaResp(String codigoError, String descripcionError) {
        super(codigoError, descripcionError);
        this.tiposDeCombustibles = null;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * @return the tipoCombustible
     */
    @XmlElementWrapper(name = "tiposCombustiblesList")
    @XmlElement(name="tipoCombustible")
    /**
     * @return the tiposDeCombustibles
     */
    public List<ItemCodiguera> getTiposDeCombustibles() {
        return tiposDeCombustibles;
    }
    /**
     * @param tiposDeCombustibles the tiposDeCombustibles to set
     */
    public void setTiposDeCombustibles(List<ItemCodiguera> tiposDeCombustibles) {
        this.tiposDeCombustibles = tiposDeCombustibles;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     *
     */
    @Override
    public String toString() {
      String logueo = null;
      String auxTiposDeCombustibles = null;
      String auxCodError  = null;
      String auxDescError = null;;
      String auxError     = "null";

      if        (this.getTiposDeCombustibles() == null)   { auxTiposDeCombustibles = "[null]";
      } else if (this.getTiposDeCombustibles().isEmpty()) { auxTiposDeCombustibles = "[VACIA]";
      } else { auxTiposDeCombustibles = this.getTiposDeCombustibles().toString(); }

      if (this.getCodigoError() != null)      { auxCodError = this.getCodigoError(); }
      if (this.getDescripcionError() != null) { auxDescError = this.getDescripcionError(); }
      if ( (auxCodError != null) || (auxDescError != null) ) {
          auxError = "[" + auxCodError + "] / [" + auxDescError + "]";
      }

      logueo = "TiposDeCombustibles=" + auxTiposDeCombustibles + " - Error=[" + auxError + "]";

      return logueo;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
