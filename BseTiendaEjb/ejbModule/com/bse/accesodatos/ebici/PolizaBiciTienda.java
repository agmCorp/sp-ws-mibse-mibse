package com.bse.accesodatos.ebici;

import java.util.Date;

import com.bse.accesodatos.esoa.PolizaTienda;

public class PolizaBiciTienda extends PolizaTienda {

    private Date fechaFactura;
    private String tipoBicicleta;
    private Date fechaNacimientoCliente;
    private String marca;
    private String serie;
    private String modelo;


    public PolizaBiciTienda() {
    }


    public Date getFechaFactura() {
        return fechaFactura;
    }
    public void setFechaFactura(Date fechaFactura) {
        this.fechaFactura = fechaFactura;
    }


    public String getTipoBicicleta() {
        return tipoBicicleta;
    }
    public void setTipoBicicleta(String tipoBicicleta) {
        this.tipoBicicleta = tipoBicicleta;
    }


    public Date getFechaNacimientoCliente() {
        return fechaNacimientoCliente;
    }
    public void setFechaNacimientoCliente(Date fechaNacimientoCliente) {
        this.fechaNacimientoCliente = fechaNacimientoCliente;
    }


    public String getMarca() {
        return marca;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }


    public String getSerie() {
        return serie;
    }
    public void setSerie(String serie) {
        this.serie = serie;
    }


    public String getModelo() {
        return modelo;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }


    @Override
    public String toString() {

        String auxFechaFactura;
        String auxTipoBicicleta;
        String auxFechaNacCliente;
        String auxMarca;
        String auxSerie;
        String auxModelo;
        String auxExtends = getPolizaTiendaString();

        String logueo = null;

        if (this.getFechaFactura() == null) { auxFechaFactura = "null";
        } else { auxFechaFactura = this.getFechaFactura().toString(); }

        if (this.getTipoBicicleta() == null) { auxTipoBicicleta = "null";
        } else { auxTipoBicicleta = this.getTipoBicicleta(); }

        if (this.getFechaNacimientoCliente() == null) { auxFechaNacCliente = "null";
        } else { auxFechaNacCliente = this.getFechaNacimientoCliente().toString(); }

        if (this.getMarca() == null) { auxMarca = "null";
        } else { auxMarca = this.getMarca(); }

        if (this.getSerie() == null) { auxSerie = "null";
        } else { auxSerie = this.getSerie(); }

        if (this.getModelo() == null) { auxModelo = "null";
        } else { auxModelo = this.getModelo(); }

        logueo = "PolizaBici { " + auxExtends
               +  " FechaFactura=["           + auxFechaFactura
               + "] TipoBicicleta=["          + auxTipoBicicleta
               + "] FechaNacimientoCliente=[" + auxFechaNacCliente
               + "] Marca=["                  + auxMarca
               + "] Serie=["                  + auxSerie
               + "] Modelo=["                 + auxModelo
               + "] }";

        return logueo;
    }

}
