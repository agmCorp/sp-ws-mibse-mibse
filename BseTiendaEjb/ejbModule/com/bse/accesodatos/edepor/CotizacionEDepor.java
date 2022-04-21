package com.bse.accesodatos.edepor;

import com.bse.accesodatos.esoa.Cotizacion;

public class CotizacionEDepor extends Cotizacion{

private TipoBuque tipoBuque;
private double capital;


public CotizacionEDepor() {
}

public TipoBuque getTipoBuque() {
	return tipoBuque;
}

public void setTipoBuque(TipoBuque tipoBuque) {
	this.tipoBuque = tipoBuque;
}

public double getCapital() {
	return capital;
}

public void setCapital(double capital) {
	this.capital = capital;
}


}
