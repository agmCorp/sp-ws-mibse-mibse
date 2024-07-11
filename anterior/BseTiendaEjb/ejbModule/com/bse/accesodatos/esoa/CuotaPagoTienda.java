package com.bse.accesodatos.esoa;

public class CuotaPagoTienda {

    private int nroCuota;
    private double importe;

    public CuotaPagoTienda() {
    }

    public CuotaPagoTienda(int nroCuota, double importe) {
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


    @Override
    public String toString() {
        String logueo =   "Nro.Cuota=[" + Integer.toString(this.getNroCuota())
                      + "] Importe=["   + Double.toString(this.getImporte()) 
                      + "]";
        return logueo;
    }

}
