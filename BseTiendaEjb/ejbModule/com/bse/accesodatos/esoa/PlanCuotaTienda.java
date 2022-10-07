package com.bse.accesodatos.esoa;

import java.util.ArrayList;

public class PlanCuotaTienda {
    
    private PlanPagoTienda planPago;
    private int cantCuotas;
    private double importeTotal;
    private ArrayList<CuotaPagoTienda> cuotas;

    public PlanCuotaTienda() {
    }

    public PlanPagoTienda getPlanPago() {
        return planPago;
    }

    public void setPlanPago(PlanPagoTienda planPago) {
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

    public ArrayList<CuotaPagoTienda> getCuotas() {
        return cuotas;
    }

    public void setCuotas(ArrayList<CuotaPagoTienda> cuotas) {
        this.cuotas = cuotas;
    }


    @Override
    public String toString() {
        String auxPlanPago;
        String auxCantCuotas = Integer.toString(this.getCantCuotas());
        String auxImporteTotal = Double.toString(this.getImporteTotal());
        String auxCuotas;

        if (this.getPlanPago() == null) { auxPlanPago = "null";
        } else { auxPlanPago = this.getPlanPago().toString(); }

        if        (this.getCuotas() == null)   { auxCuotas = "ListaCuotas=[ null ]";
        } else if (this.getCuotas().isEmpty()) { auxCuotas = "ListaCuotas=[ VACIO ]";
        } else { auxCuotas = "ListaCuotas=" + this.getCuotas().toString(); }

        String logueo = "PlanCuota={ "   
                      +   "PlanPago=["     + auxPlanPago
                      + "] CantCuotas=["   + auxCantCuotas
                      + "] ImporteTotal=[" + auxImporteTotal
                      + "] "               + auxCuotas 
                      + " }";
        return logueo;
    }

}
