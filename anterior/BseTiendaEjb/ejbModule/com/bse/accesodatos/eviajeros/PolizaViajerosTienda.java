package com.bse.accesodatos.eviajeros;

import java.util.ArrayList;
import java.util.Date;

import com.bse.accesodatos.esoa.PolizaTienda;

public class PolizaViajerosTienda extends PolizaTienda {

    private ArrayList<ViajeroTienda> listaViajeros;
    private String extension;
    private Date fechaSalidaPais;

    public PolizaViajerosTienda() {
    }

    public PolizaViajerosTienda(ArrayList<ViajeroTienda> listaViajeros) {
        this.listaViajeros = listaViajeros;
    }


    public ArrayList<ViajeroTienda> getListaViajeros() {
        return listaViajeros;
    }
    public void setListaViajeros(ArrayList<ViajeroTienda> listaViajeros) {
        this.listaViajeros = listaViajeros;
    }


    public String getExtension() {
        return extension;
    }
    public void setExtension(String extension) {
        this.extension = extension;
    }


    public Date getFechaSalidaPais() {
        return fechaSalidaPais;
    }
    public void setFechaSalidaPais(Date fechaSalidaPais) {
        this.fechaSalidaPais = fechaSalidaPais;
    }


    @Override
    public String toString() {

        String auxListaViajeros;
        String auxExtension;
        String auxFechaSalida;
        String auxExtends = getPolizaTiendaString();

        String logueo = null;

        if        (this.getListaViajeros() == null)   { auxListaViajeros = "ListaViajeros=[ null ]";
        } else if (this.getListaViajeros().isEmpty()) { auxListaViajeros = "ListaViajeros=[ VACIO ]";
        } else { auxListaViajeros = "ListaViajeros=" + this.getListaViajeros().toString(); }

        if (this.getExtension() == null) { auxExtension = "null";
        } else { auxExtension = this.getExtension(); }

        if (this.getFechaSalidaPais() == null) { auxFechaSalida = "null";
        } else { auxFechaSalida = this.getFechaSalidaPais().toString(); }

        logueo = "PolizaViajeros { "   + auxExtends 
               +   " "                 + auxListaViajeros
               +  " Extension=["       + auxExtension
               + "] FechaSalidaPais=[" + auxFechaSalida
               + "] }";

        return logueo;
    }

}
