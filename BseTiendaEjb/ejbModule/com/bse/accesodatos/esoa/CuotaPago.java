package com.bse.accesodatos.esoa;

public class CuotaPago{

private int nroCuota;
private double importe;

public CuotaPago() {
}

public CuotaPago(int nroCuota, double importe) {
	super();
	this.nroCuota = nroCuota;
	this.importe = importe;
}

public int getNroCuota() {
	return nroCuota;
}

public void setNroCuota(int nroCuota) {
	this.nroCuota = nroCuota;
}

public double getImporte() {
	return importe;
}

public void setImporte(double importe) {
	this.importe = importe;
}

}
