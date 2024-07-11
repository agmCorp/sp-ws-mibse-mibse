package com.bse.accesodatos.edepor;

import com.bse.accesodatos.esoa.CotizacionTienda;

public class CotizacionEDeporTienda extends CotizacionTienda {

    private TipoBuqueTienda tipoBuque;
    private double capital;

    public CotizacionEDeporTienda() {
    }

    public TipoBuqueTienda getTipoBuque() {
        return tipoBuque;
    }
    public void setTipoBuque(TipoBuqueTienda tipoBuque) {
        this.tipoBuque = tipoBuque;
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
        String auxCapital;
        String auxExtends = getCotizacionTiendaString();

        if (this.getTipoBuque() == null) { auxTipoBuque = "null";
        } else { auxTipoBuque = this.getTipoBuque().toString(); }

        auxCapital = Double.toString(this.getCapital());

        String logueo = "CotizacionEDepor { " + auxExtends
                      +  " TipoBuque=[" + auxTipoBuque
                      + "] Capital=["   + auxCapital
                      + "] }";
        return logueo;
    }

}
