package com.bse.accesodatos.eoperson;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import com.bse.accesodatos.esoa.CotizacionTienda;
import com.bse.accesodatos.esoa.PlanCuotaTienda;

public class CotizacionOPersonalTienda extends CotizacionTienda{

    private ArrayList<PlanCuotaTienda> planesDeCuotas;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * CONSTRUCTOR
     */
    public CotizacionOPersonalTienda() {
        super();
        this.planesDeCuotas = null;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * @return the planesDeCuotas
     */
    @XmlElementWrapper(name = "planesCuotas")
    @XmlElement(name="planCuota")
    public ArrayList<PlanCuotaTienda> getPlanesDeCuotas() {
        return planesDeCuotas;
    }
    /**
     * @param planesDeCuotas the planesDeCuotas to set
     */
    public void setPlanesDeCuotas(ArrayList<PlanCuotaTienda> planesDeCuotas) {
        this.planesDeCuotas = planesDeCuotas;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public String toString() {
        String auxPlanesDeCoutas;
        String auxExtends = getCotizacionTiendaString();

        String logueo = null;

        if        (this.getPlanesDeCuotas() == null)   { auxPlanesDeCoutas = "ListaPlanesCuotas=[ null ]";
        } else if (this.getPlanesDeCuotas().isEmpty()) { auxPlanesDeCoutas = "ListaPlanesCuotas=[ VACIO ]";
        } else { auxPlanesDeCoutas = "ListaPlanesCuotas=" + this.getPlanesDeCuotas().toString(); }

        logueo =  "CotizacionOPersonal { " + auxExtends 
               + " " + auxPlanesDeCoutas + " }";

        return logueo;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
