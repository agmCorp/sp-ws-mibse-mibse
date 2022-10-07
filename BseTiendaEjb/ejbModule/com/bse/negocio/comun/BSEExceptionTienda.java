package com.bse.negocio.comun;

import java.io.Serializable;
import java.util.Locale;
import java.util.ResourceBundle;

public class BSEExceptionTienda extends Exception implements Serializable{

    private static final long serialVersionUID = 1L;
    private int codigoError;
    private String descripcion;

    public static String getDescripcionError(int codigoError) { 
        Locale idioma = new Locale("es", "UY");
        String desc = ResourceBundle.getBundle("BSEDescentralizado", idioma).getString("ex-" + codigoError);
        return desc;
    }

    public BSEExceptionTienda(int codigoError) {
        super("Error: codigoError=" + codigoError);
        this.codigoError = codigoError;
        this.descripcion = getDescripcionError(codigoError);
    }

    public BSEExceptionTienda(int codigoError, String descripcion) {
        super("Error: codigoError=" + codigoError);
        this.codigoError = codigoError;
        this.descripcion = descripcion;
    }

    public BSEExceptionTienda(int codigoError, Throwable throwable) {
        super("Error: codigoError=" + codigoError, throwable);
        this.codigoError = codigoError;
        this.descripcion = getDescripcionError(codigoError);
    }

    public int getCodigoError() {
        return codigoError;
    }

    public String getDescripcion() {
        return descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion=descripcion;
    }
}
