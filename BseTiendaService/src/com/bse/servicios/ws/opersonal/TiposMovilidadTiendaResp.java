package com.bse.servicios.ws.opersonal;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import com.bse.accesodatos.comun.ItemCodiguera;
import com.bse.servicios.ws.comun.ErrorTiendaResp;

public class TiposMovilidadTiendaResp extends ErrorTiendaResp implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<ItemCodiguera> tiposDeMovilidad;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * CONSTRUCTOR
     */
    public TiposMovilidadTiendaResp() {
        super();
        this.tiposDeMovilidad = null;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * CONSTRUCTOR
     * @param tiposDeMovilidad
     * @param codigoError
     * @param descripcionError
     */
    public TiposMovilidadTiendaResp(List<ItemCodiguera> tiposDeMovilidad, String codigoError, String descripcionError) {
        super(codigoError, descripcionError);
        this.tiposDeMovilidad = tiposDeMovilidad;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * CONSTRUCTOR
     * @param codigoError
     * @param descripcionError
     */
    public TiposMovilidadTiendaResp(String codigoError, String descripcionError) {
        super(codigoError, descripcionError);
        this.tiposDeMovilidad = null;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * @return the tiposDeMovilidad
     */
    @XmlElementWrapper(name = "tiposMovilidadList")
    @XmlElement(name="tipoMovilidad")
    public List<ItemCodiguera> getTiposDeMovilidad() {
        return tiposDeMovilidad;
    }
    /**
     * @param tiposDeMovilidad the tiposDeMovilidad to set
     */
    public void setTiposDeMovilidad(List<ItemCodiguera> tiposDeMovilidad) {
        this.tiposDeMovilidad = tiposDeMovilidad;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     *
     */
    @Override
    public String toString() {
      String logueo = null;
      String auxTiposDeMovilidad = null;
      String auxCodError  = null;
      String auxDescError = null;
      String auxError     = "null";

      if        (this.getTiposDeMovilidad() == null)   { auxTiposDeMovilidad = "[null]";
      } else if (this.getTiposDeMovilidad().isEmpty()) { auxTiposDeMovilidad = "[VACIA]";
      } else { auxTiposDeMovilidad = this.getTiposDeMovilidad().toString(); }

      if (this.getCodigoError() != null)      { auxCodError = this.getCodigoError(); }
      if (this.getDescripcionError() != null) { auxDescError = this.getDescripcionError(); }
      if ( (auxCodError != null) || (auxDescError != null) ) {
          auxError = "["     + ((auxCodError!=null)?auxCodError:"null")
                   + "] / [" + ((auxDescError!=null)?auxDescError:"null") + "]";
      }

      logueo = "TiposDeMovilidad=" + auxTiposDeMovilidad + " - Error=[ " + auxError + " ]";

      return logueo;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
