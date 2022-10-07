package com.bse.servicios.eindivi;


import java.util.List;

import com.bse.servicios.seguridad.dt.DTSesionTienda;
import com.bse.accesodatos.comun.ItemCodiguera;
import com.bse.accesodatos.eindivi.CotizacionIndiviTienda;
import com.bse.accesodatos.eindivi.DatosVariosIndivi;
import com.bse.accesodatos.eindivi.PolizaIndiviTienda;
import com.bse.negocio.comun.BSEExceptionTienda;
import javax.ejb.Local;

@Local
public interface IEmisionIndiviTiendaEJBLocal {

    public CotizacionIndiviTienda cotizarIndiviAnonimo       ( DTSesionTienda dtSesion,
                                                               String marcaVehiculo,
                                                               String anioVehiculo,
                                                               String tipoVehiculo,
                                                               String combustible,
                                                               String versionVehiculo,
                                                               String areaCirculacion )    throws Exception, BSEExceptionTienda;

    public PolizaIndiviTienda     emitirIndivi               ( DTSesionTienda dtSesion,
                                                               String tipoDocumento,
                                                               String documento,
                                                               long   nroCotizacion,
                                                               String matriculaVehiculo,
                                                               String padronVehiculo,
                                                               String motorVehiculo,
                                                               String chasisVehiculo,
                                                               String planCobertura,
                                                               String planPago,
                                                               String modalidad,
                                                               String consumoFinal )       throws Exception, BSEExceptionTienda;

    public List<ItemCodiguera>    consultaAreasCirculacion   ( DTSesionTienda dtSesion )   throws Exception, BSEExceptionTienda;

    public List<ItemCodiguera>    consultaTiposVehiculos     ( DTSesionTienda dtSesion )   throws Exception, BSEExceptionTienda;

    public List<ItemCodiguera>    consultaTiposCombustible   ( DTSesionTienda dtSesion )   throws Exception, BSEExceptionTienda;

    public List<ItemCodiguera>    consultaMarcasVehiculos    ( DTSesionTienda dtSesion )   throws Exception, BSEExceptionTienda;

    public List<ItemCodiguera>    consultaFamiliasVehiculos  ( DTSesionTienda dtSesion,
                                                               String marcaVehiculo,
                                                               String anioVehiculo,
                                                               String tipoVehiculo,
                                                               String combustible )        throws Exception, BSEExceptionTienda;

    public List<ItemCodiguera>    consultaVersionesVehiculos ( DTSesionTienda dtSesion,
                                                               String marcaVehiculo,
                                                               String anioVehiculo,
                                                               String tipoVehiculo,
                                                               String combustible,
                                                               String familiaVehiculo )    throws Exception, BSEExceptionTienda;

    public DatosVariosIndivi      consultaDatosVarios        ( DTSesionTienda dtSesion )   throws Exception, BSEExceptionTienda;

}
