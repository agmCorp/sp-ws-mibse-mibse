package com.bse.accesodatos.comun;

public class ItemCodigueraIndex extends ItemCodiguera {
    
    private long indice;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * CONSTRUCTOR
     */
    public ItemCodigueraIndex() {
        super();
        this.indice = -1;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * CONSTRUCTOR
     * @param indice
     * @param codigo
     * @param nombre
     */
    public ItemCodigueraIndex(long indice, String codigo, String nombre) {
        super(codigo, nombre);
        this.indice = indice;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     *
     */
    @Override
    public String toString() {
      String logueo = null;
      String auxIndice = Long.toString(this.indice);
      String auxCodigo;
      String auxNombre;

      if (this.getCodigo() == null) { auxCodigo = "null";
      } else { auxCodigo = this.getCodigo(); }

      if (this.getNombre() == null) { auxNombre = "null";
      } else { auxNombre = this.getNombre(); }

        logueo =   "Indice=[" + auxIndice
               + "] Codigo=[" + auxCodigo
               + "] Nombre=[" + auxNombre
               + "]";

        return logueo;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * @return the indice
     */
    public long getIndice() {
        return indice;
    }
    /**
     * @param indice the indice to set
     */
    public void setIndice(long indice) {
        this.indice = indice;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
