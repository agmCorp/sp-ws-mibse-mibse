package com.bse.servicios.ws.soa;

import com.bse.accesodatos.esoa.MarcaVehiculoTienda;

import java.io.Serializable;
import java.util.List;

public class MarcasVehiculosTiendaResp implements Serializable {

    /**
   *
   */
  private static final long serialVersionUID = 1L;
    private String codigoError;
    private String descripcionError;
  private List<MarcaVehiculoTienda> marcas;

    public String getCodigoError() {
        return codigoError;
    }

    public void setCodigoError(String codigoError) {
        this.codigoError = codigoError;
    }

    public String getDescripcionError() {
        return descripcionError;
    }

    public void setDescripcionError(String descripcionError) {
        this.descripcionError = descripcionError;
    }

  public void setMarcasVehiculos(List<MarcaVehiculoTienda> marcas){
    this.marcas = marcas;
  }

  public List<MarcaVehiculoTienda> getMarcasVehiculos(){
    return this.marcas;
  }

}
