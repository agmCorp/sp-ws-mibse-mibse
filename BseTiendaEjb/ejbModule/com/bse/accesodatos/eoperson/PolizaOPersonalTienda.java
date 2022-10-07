package com.bse.accesodatos.eoperson;

import java.util.Date;

import com.bse.accesodatos.esoa.PolizaTienda;

public class PolizaOPersonalTienda extends PolizaTienda {

    private String marca;
    private String serie;
    private String modelo;
    private Date   fechaFactura;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * CONSTRUCTOR
     */
    public PolizaOPersonalTienda() {
        super();
        this.marca  = null;
        this.serie  = null;
        this.modelo = null;
        this.fechaFactura = null;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * @return the marca
     */
    public String getMarca() {
        return marca;
    }
    /**
     * @param marca the marca to set
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * @return the serie
     */
    public String getSerie() {
        return serie;
    }
    /**
     * @param serie the serie to set
     */
    public void setSerie(String serie) {
        this.serie = serie;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * @return the modelo
     */
    public String getModelo() {
        return modelo;
    }
    /**
     * @param modelo the modelo to set
     */
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * @return the fechaFactura
     */
    public Date getFechaFactura() {
        return fechaFactura;
    }
    /**
     * @param fechaFactura the fechaFactura to set
     */
    public void setFechaFactura(Date fechaFactura) {
        this.fechaFactura = fechaFactura;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public String toString() {

        String auxMarca;
        String auxSerie;
        String auxModelo;
        String auxFechaFactura;
        String auxExtends = getPolizaTiendaString();

        String logueo = null;

        if (this.getMarca() == null) { auxMarca = "null";
        } else { auxMarca = this.getMarca(); }

        if (this.getSerie() == null) { auxSerie = "null";
        } else { auxSerie = this.getSerie(); }

        if (this.getModelo() == null) { auxModelo = "null";
        } else { auxModelo = this.getModelo(); }

        if (this.getFechaFactura() == null) { auxFechaFactura = "null";
        } else { auxFechaFactura = this.getFechaFactura().toString(); }

        logueo = "PolizaOPersonal { "  + auxExtends 
                 + " Marca=["          + auxMarca
                + "] Serie=["          + auxSerie
                + "] Modelo=["         + auxModelo
                + "] FechaFactura=["   + auxFechaFactura
                + "] }";

        return logueo;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
