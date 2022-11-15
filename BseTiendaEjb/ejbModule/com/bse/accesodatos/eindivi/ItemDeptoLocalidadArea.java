package com.bse.accesodatos.eindivi;

import com.bse.accesodatos.comun.ItemCodiguera;

public class ItemDeptoLocalidadArea extends ItemCodiguera {

    private String areaCiculacion;


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * CONSTRUCTOR
     */
    public ItemDeptoLocalidadArea() {
        super();
        this.areaCiculacion = null;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * CONSTRUCTOR
     * @param codigo
     * @param nombre
     * @param areaCiculacion
     */
    public ItemDeptoLocalidadArea(String codigo, String nombre, String areaCiculacion) {
        super(codigo, nombre);
        this.areaCiculacion = areaCiculacion;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     *
     */
    @Override
    public String toString() {
      String logueo = null;
      String auxCodigo;
      String auxNombre;
      String auxAreaCirculacion;

      if (this.getCodigo() == null) { auxCodigo = "null";
      } else { auxCodigo = this.getCodigo(); }

      if (this.getNombre() == null) { auxNombre = "null";
      } else { auxNombre = this.getNombre(); }

      if (this.getAreaCiculacion() == null) { auxAreaCirculacion = "null";
      } else { auxAreaCirculacion = this.getAreaCiculacion(); }

        logueo =   "Codigo=["          + auxCodigo
               + "] Nombre=["          + auxNombre
               + "] AreaCirculacion=[" + auxAreaCirculacion
               + "]";

        return logueo;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * @return the areaCiculacion
     */
    public String getAreaCiculacion() {
        return areaCiculacion;
    }
    /**
     * @param areaCiculacion the areaCiculacion to set
     */
    public void setAreaCiculacion(String areaCiculacion) {
        this.areaCiculacion = areaCiculacion;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
