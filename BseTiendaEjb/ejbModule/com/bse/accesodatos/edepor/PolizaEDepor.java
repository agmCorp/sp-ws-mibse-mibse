package com.bse.accesodatos.edepor;

import com.bse.accesodatos.esoa.Poliza;

public class PolizaEDepor extends Poliza{

private TipoBuque tipoBuque;
private String matriculaBuque;
private String nombreBuque;
private double capital;

public PolizaEDepor() {
}

public TipoBuque getTipoBuque() {
	return tipoBuque;
}

public void setTipoBuque(TipoBuque tipoBuque) {
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


}
