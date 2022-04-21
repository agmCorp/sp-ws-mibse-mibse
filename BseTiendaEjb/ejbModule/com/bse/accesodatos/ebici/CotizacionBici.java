package com.bse.accesodatos.ebici;

import java.util.ArrayList;

import com.bse.accesodatos.esoa.Cotizacion;
import com.bse.accesodatos.esoa.PlanCuota;

public class CotizacionBici extends Cotizacion{

private ArrayList<PlanCuota> planesDeCuotas;


public CotizacionBici() {
}


public void setPlanesDeCuotas(ArrayList<PlanCuota> planesDeCuotas) {
	this.planesDeCuotas = planesDeCuotas;
}

public ArrayList<PlanCuota> getPlanesDeCuotas() {
	return planesDeCuotas;
}


}
