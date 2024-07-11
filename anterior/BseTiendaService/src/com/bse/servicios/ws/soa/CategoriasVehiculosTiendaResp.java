package com.bse.servicios.ws.soa;

import com.bse.accesodatos.esoa.CategoriaVehiculoTienda;
import java.io.Serializable;
import java.util.List;

public class CategoriasVehiculosTiendaResp implements Serializable {

    /**
   *
   */
  private static final long serialVersionUID = 1L;
    private String codigoError;
    private String descripcionError;
  private List<CategoriaVehiculoTienda> categorias;

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

  public void setCategoriasVehiculos(List<CategoriaVehiculoTienda> categorias){
    this.categorias = categorias;
  }

  public List<CategoriaVehiculoTienda> getCategoriasVehiculos(){
    return this.categorias;
  }

}
