package com.bse.accesodatos.ebici;

import java.util.ArrayList;

import com.bse.accesodatos.esoa.CotizacionTienda;
import com.bse.accesodatos.esoa.PlanCuotaTienda;

public class CotizacionBiciTienda extends CotizacionTienda {

    private ArrayList<PlanCuotaTienda> planesDeCuotas;


    public CotizacionBiciTienda() {
    }


    public void setPlanesDeCuotas(ArrayList<PlanCuotaTienda> planesDeCuotas) {
        this.planesDeCuotas = planesDeCuotas;
    }
    public ArrayList<PlanCuotaTienda> getPlanesDeCuotas() {
        return planesDeCuotas;
    }


    @Override
    public String toString() {
        String auxPlanesDeCoutas;
        String auxExtends = getCotizacionTiendaString();

        String logueo = null;

        if        (this.getPlanesDeCuotas() == null)   { auxPlanesDeCoutas = "ListaPlanesCuotas=[ null ]";
        } else if (this.getPlanesDeCuotas().isEmpty()) { auxPlanesDeCoutas = "ListaPlanesCuotas=[ VACIO ]";
        } else { auxPlanesDeCoutas = "ListaPlanesCuotas=" + this.getPlanesDeCuotas().toString(); }

        logueo =  "CotizacionBici { " + auxExtends 
               + " " + auxPlanesDeCoutas + " }";

        return logueo;
    }

}
