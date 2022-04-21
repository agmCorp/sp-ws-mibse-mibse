package com.bse.accesodatos.esoa;

import java.util.ArrayList;

public class PlanCuota{

private PlanPago planPago;
private int cantCuotas;
private double importeTotal;
private ArrayList<CuotaPago> cuotas;

public PlanCuota() {
}

public PlanPago getPlanPago() {
	return planPago;
}

public void setPlanPago(PlanPago planPago) {
	this.planPago = planPago;
}

public int getCantCuotas() {
	return cantCuotas;
}

public void setCantCuotas(int cantCuotas) {
	this.cantCuotas = cantCuotas;
}

public double getImporteTotal() {
	return importeTotal;
}

public void setImporteTotal(double importeTotal) {
	this.importeTotal = importeTotal;
}

public ArrayList<CuotaPago> getCuotas() {
	return cuotas;
}

public void setCuotas(ArrayList<CuotaPago> cuotas) {
	this.cuotas = cuotas;
}


}
