package com.bse.servicios.ws.indivi;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import com.bse.accesodatos.comun.ItemCodiguera;
import com.bse.servicios.ws.comun.ErrorTiendaResp;

public class AreasCirculacionTiendaResp extends ErrorTiendaResp implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private List<ItemCodiguera> areasDeCirculacion;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * CONSTRUCTOR
     */
    public AreasCirculacionTiendaResp() {
        super();
        this.areasDeCirculacion = null;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * CONSTRUCTOR
     * @param areasDeCirculacion
     * @param codigoError
     * @param descripcionError
     */
    public AreasCirculacionTiendaResp(List<ItemCodiguera> areasDeCirculacion, String codigoError, String descripcionError) {
        super(codigoError, descripcionError);
        this.areasDeCirculacion = areasDeCirculacion;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * CONSTRUCTOR
     * @param codigoError
     * @param descripcionError
     */
    public AreasCirculacionTiendaResp(String codigoError, String descripcionError) {
        super(codigoError, descripcionError);
        this.areasDeCirculacion = null;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * @return the areasDeCirculacion
     */
    @XmlElementWrapper(name = "areasCirculacionList")
    @XmlElement(name="areaCirculacion")
    public List<ItemCodiguera> getAreasDeCirculacion() {
        return areasDeCirculacion;
    }
    /**
     * @param areasDeCirculacion the areasDeCirculacion to set
     */
    public void setAreasDeCirculacion(List<ItemCodiguera> areasDeCirculacion) {
        this.areasDeCirculacion = areasDeCirculacion;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     *
     */
    @Override
    public String toString() {
      String logueo = null;
      String auxAreasDeCirculacion = null;
      String auxCodError  = null;
      String auxDescError = null;;
      String auxError     = "null";

      if        (this.getAreasDeCirculacion() == null)   { auxAreasDeCirculacion = "[null]";
      } else if (this.getAreasDeCirculacion().isEmpty()) { auxAreasDeCirculacion = "[VACIA]";
      } else { auxAreasDeCirculacion = this.getAreasDeCirculacion().toString(); }

      if (this.getCodigoError() != null)      { auxCodError = this.getCodigoError(); }
      if (this.getDescripcionError() != null) { auxDescError = this.getDescripcionError(); }
      if ( (auxCodError != null) || (auxDescError != null) ) {
          auxError = "[" + auxCodError + "] / [" + auxDescError + "]";
      }

      logueo = "AreasDeCirculacion=" + auxAreasDeCirculacion + " - Error=[" + auxError + "]";

      return logueo;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
