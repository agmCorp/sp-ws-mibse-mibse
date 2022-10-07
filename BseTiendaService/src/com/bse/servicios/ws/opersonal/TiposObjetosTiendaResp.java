package com.bse.servicios.ws.opersonal;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import com.bse.accesodatos.comun.ItemCodiguera;
import com.bse.servicios.ws.comun.ErrorTiendaResp;

public class TiposObjetosTiendaResp extends ErrorTiendaResp implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<ItemCodiguera> tiposDeObjetos;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * CONSTRUCTOR
     */
    public TiposObjetosTiendaResp() {
        super();
        this.tiposDeObjetos = null;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * CONSTRUCTOR
     * @param tiposDeObjetos
     * @param codigoError
     * @param descripcionError
     */
    public TiposObjetosTiendaResp(List<ItemCodiguera> tiposDeObjetos, String codigoError, String descripcionError) {
        super(codigoError, descripcionError);
        this.tiposDeObjetos = tiposDeObjetos;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * CONSTRUCTOR
     * @param codigoError
     * @param descripcionError
     */
    public TiposObjetosTiendaResp(String codigoError, String descripcionError) {
        super(codigoError, descripcionError);
        this.tiposDeObjetos = null;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * @return the tiposDeObjetos
     */
    @XmlElementWrapper(name = "tiposObjetosList")
    @XmlElement(name="tipoObjeto")
    public List<ItemCodiguera> getTiposDeObjetos() {
        return tiposDeObjetos;
    }
    /**
     * @param tiposDeObjetos the tiposDeObjetos to set
     */
    public void setTiposDeObjetos(List<ItemCodiguera> tiposDeObjetos) {
        this.tiposDeObjetos = tiposDeObjetos;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     *
     */
    @Override
    public String toString() {
      String logueo = null;
      String auxTiposDeObjetos = null;
      String auxCodError  = null;
      String auxDescError = null;
      String auxError     = "null";

      if        (this.getTiposDeObjetos() == null)   { auxTiposDeObjetos = "[null]";
      } else if (this.getTiposDeObjetos().isEmpty()) { auxTiposDeObjetos = "[VACIA]";
      } else { auxTiposDeObjetos = this.getTiposDeObjetos().toString(); }

      if (this.getCodigoError() != null)      { auxCodError = this.getCodigoError(); }
      if (this.getDescripcionError() != null) { auxDescError = this.getDescripcionError(); }
      if ( (auxCodError != null) || (auxDescError != null) ) {
          auxError = "["     + ((auxCodError!=null)?auxCodError:"null")
                   + "] / [" + ((auxDescError!=null)?auxDescError:"null") + "]";
      }

      logueo = "TiposDeObjetos=" + auxTiposDeObjetos + " - Error=[ " + auxError + " ]";

      return logueo;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
