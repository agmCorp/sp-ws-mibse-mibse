package com.bse.accesodatos.edepor;

import com.bse.accesodatos.esoa.PolizaTienda;

public class PolizaEDeporTienda extends PolizaTienda{

    private TipoBuqueTienda tipoBuque;
    private String matriculaBuque;
    private String nombreBuque;
    private double capital;

    public PolizaEDeporTienda() {
    }

    public TipoBuqueTienda getTipoBuque() {
        return tipoBuque;
    }
    public void setTipoBuque(TipoBuqueTienda tipoBuque) {
        this.tipoBuque = tipoBuque;
    }


    public String getMatriculaBuque() {
        return matriculaBuque;
    }
    public void setMatriculaBuque(String matriculaBuque) {
        this.matriculaBuque = matriculaBuque;
    }


    public String getNombreBuque() {
        return nombreBuque;
    }
    public void setNombreBuque(String nombreBuque) {
        this.nombreBuque = nombreBuque;
    }


    public double getCapital() {
        return capital;
    }
    public void setCapital(double capital) {
        this.capital = capital;
    }

    @Override
    public String toString() {
        String auxTipoBuque;
        String auxMatriculaBuque;
        String auxNombreBuque;
        String auxCapital;
        String auxExtends = getPolizaTiendaString();

        if (this.getTipoBuque() == null) { auxTipoBuque = "null";
        } else { auxTipoBuque = this.getTipoBuque().toString(); }

        if (this.getMatriculaBuque() == null) { auxMatriculaBuque = "null";
        } else { auxMatriculaBuque = this.getMatriculaBuque(); }

        if (this.getNombreBuque() == null) { auxNombreBuque = "null";
        } else { auxNombreBuque = this.getNombreBuque(); }

        auxCapital = Double.toString(this.getCapital());

        String logueo = "PolizaEDepor { " + auxExtends
                      +  " TipoBuque=["      + auxTipoBuque
                      + "] MatriculaBuque=[" + auxMatriculaBuque
                      + "] NombreBuque=["    + auxNombreBuque
                      + "] Capital=["        + auxCapital
                      + "] }";
        return logueo;
    }

}
