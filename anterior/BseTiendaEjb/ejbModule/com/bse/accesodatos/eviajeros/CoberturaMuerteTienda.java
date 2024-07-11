package com.bse.accesodatos.eviajeros;

public class CoberturaMuerteTienda {

    private float cobertura;
    private String descCobertura;


    public float getCobertura() {
        return cobertura;
    }
    public void setCobertura(float cobertura) {
        this.cobertura = cobertura;
    }


    public String getDescCobertura() {
        return descCobertura;
    }
    public void setDescCobertura(String descCobertura) {
        this.descCobertura = descCobertura;
    }


    @Override
    public String toString() {
        String auxDescripcion;
        if (this.getDescCobertura() == null) { auxDescripcion = "null";
        } else                               { auxDescripcion = this.getDescCobertura(); }

        return ("Cobertura=[" + Float.toString(this.getCobertura()) + "] Descripcion=[" + auxDescripcion + "]");
    }

}
