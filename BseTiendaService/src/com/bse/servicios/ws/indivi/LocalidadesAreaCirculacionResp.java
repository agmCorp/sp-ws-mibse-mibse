package com.bse.servicios.ws.indivi;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import com.bse.accesodatos.eindivi.ItemDeptoLocalidadArea;
import com.bse.servicios.ws.comun.ErrorTiendaResp;

public class LocalidadesAreaCirculacionResp extends ErrorTiendaResp implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<ItemDeptoLocalidadArea> localidadesAreaCirculacion;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * CONSTRUCTOR
     */
    public LocalidadesAreaCirculacionResp() {
        super();
        this.localidadesAreaCirculacion = null;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * CONSTRUCTOR
     * @param localidadesAreaCirculacion
     * @param codigoError
     * @param descripcionError
     */
    public LocalidadesAreaCirculacionResp( List<ItemDeptoLocalidadArea> localidadesAreaCirculacion,
                                           String codigoError, String descripcionError) {
        super(codigoError, descripcionError);
        this.localidadesAreaCirculacion = localidadesAreaCirculacion;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * CONSTRUCTOR
     * @param codigoError
     * @param descripcionError
     */
    public LocalidadesAreaCirculacionResp(String codigoError, String descripcionError) {
        super(codigoError, descripcionError);
        this.localidadesAreaCirculacion = null;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * @return the localidadesAreaCirculacion
     */
    @XmlElementWrapper(name = "localidadesAreaList")
    @XmlElement(name="localidadArea")
    public List<ItemDeptoLocalidadArea> getLocalidadesAreaCirculacion() {
        return localidadesAreaCirculacion;
    }
    /**
     * @param localidadesAreaCirculacion the localidadesAreaCirculacion to set
     */
    public void setLocalidadesAreaCirculacion(
            List<ItemDeptoLocalidadArea> localidadesAreaCirculacion) {
        this.localidadesAreaCirculacion = localidadesAreaCirculacion;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     *
     */
    @Override
    public String toString() {
      String logueo = null;
      String auxLocalidadesAreaCirculacion = null;
      String auxCodError  = null;
      String auxDescError = null;;
      String auxError     = "null";

      if        (this.getLocalidadesAreaCirculacion() == null)   { auxLocalidadesAreaCirculacion = "[null]";
      } else if (this.getLocalidadesAreaCirculacion().isEmpty()) { auxLocalidadesAreaCirculacion = "[VACIA]";
      } else { auxLocalidadesAreaCirculacion = this.getLocalidadesAreaCirculacion().toString(); }

      if (this.getCodigoError() != null)      { auxCodError = this.getCodigoError(); }
      if (this.getDescripcionError() != null) { auxDescError = this.getDescripcionError(); }
      if ( (auxCodError != null) || (auxDescError != null) ) {
          auxError = "[" + auxCodError + "] / [" + auxDescError + "]";
      }

      logueo = "LocalidadesAreaCirculacion=" + auxLocalidadesAreaCirculacion + " - Error=[" + auxError + "]";

      return logueo;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
