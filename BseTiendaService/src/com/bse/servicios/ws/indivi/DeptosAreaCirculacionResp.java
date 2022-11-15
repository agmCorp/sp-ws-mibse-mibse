package com.bse.servicios.ws.indivi;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import com.bse.accesodatos.eindivi.ItemDeptoLocalidadArea;
import com.bse.servicios.ws.comun.ErrorTiendaResp;

public class DeptosAreaCirculacionResp extends ErrorTiendaResp implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<ItemDeptoLocalidadArea> deptosAreaCirculacion;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * CONSTRUCTOR
     */
    public DeptosAreaCirculacionResp() {
        super();
        this.deptosAreaCirculacion = null;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * CONSTRUCTOR
     * @param deptosAreaCirculacion
     * @param codigoError
     * @param descripcionError
     */
    public DeptosAreaCirculacionResp( List<ItemDeptoLocalidadArea> deptosAreaCirculacion,
                                      String codigoError, String descripcionError) {
        super(codigoError, descripcionError);
        this.deptosAreaCirculacion = deptosAreaCirculacion;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * CONSTRUCTOR
     * @param codigoError
     * @param descripcionError
     */
    public DeptosAreaCirculacionResp(String codigoError, String descripcionError) {
        super(codigoError, descripcionError);
        this.deptosAreaCirculacion = null;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * @return the deptosAreaCirculacion
     */
    @XmlElementWrapper(name = "departamentosAreaList")
    @XmlElement(name="departamentoArea")
    public List<ItemDeptoLocalidadArea> getDeptosAreaCirculacion() {
        return deptosAreaCirculacion;
    }
    /**
     * @param deptosAreaCirculacion the deptosAreaCirculacion to set
     */
    public void setDeptosAreaCirculacion(
            List<ItemDeptoLocalidadArea> deptosAreaCirculacion) {
        this.deptosAreaCirculacion = deptosAreaCirculacion;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     *
     */
    @Override
    public String toString() {
      String logueo = null;
      String auxDeptosAreaCirculacion = null;
      String auxCodError  = null;
      String auxDescError = null;;
      String auxError     = "null";

      if        (this.getDeptosAreaCirculacion() == null)   { auxDeptosAreaCirculacion = "[null]";
      } else if (this.getDeptosAreaCirculacion().isEmpty()) { auxDeptosAreaCirculacion = "[VACIA]";
      } else { auxDeptosAreaCirculacion = this.getDeptosAreaCirculacion().toString(); }

      if (this.getCodigoError() != null)      { auxCodError = this.getCodigoError(); }
      if (this.getDescripcionError() != null) { auxDescError = this.getDescripcionError(); }
      if ( (auxCodError != null) || (auxDescError != null) ) {
          auxError = "[" + auxCodError + "] / [" + auxDescError + "]";
      }

      logueo = "DeptosAreaCirculacion=" + auxDeptosAreaCirculacion + " - Error=[" + auxError + "]";

      return logueo;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
