package com.bse.accesodatos.edepor;

public class CoberturaRcTienda {

    private String codigo;
    private String nombre;

    public CoberturaRcTienda() {
    }

    public CoberturaRcTienda(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }


    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo= codigo;
    }


    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    @Override
    public String toString() {
        String auxCodigo;
        String auxNombre;

        if (this.getCodigo() == null) { auxCodigo = "null";
        } else { auxCodigo = this.getCodigo(); }

        if (this.getNombre() == null) { auxNombre = "null";
        } else { auxNombre = this.getNombre(); }

        String logueo =   "Codigo=[" + auxCodigo
                      + "] Nombre=[" + auxNombre
                      + "]";
        return logueo;
    }
}
