package com.bse.servicios.esoa;


import java.util.List;

import com.bse.servicios.seguridad.dt.DTSesionTienda;
import com.bse.accesodatos.esoa.CategoriaVehiculoTienda;
import com.bse.accesodatos.esoa.CotizacionSoaTienda;
import com.bse.accesodatos.esoa.MarcaVehiculoTienda;
import com.bse.accesodatos.esoa.PolizaSoaTienda;
import com.bse.negocio.comun.BSEExceptionTienda;
import javax.ejb.Local;

@Local
public interface IEmisionSoaTiendaEJBLocal {

    public CotizacionSoaTienda           cotizarSoaAnonimo( DTSesionTienda dtSesion,
                                                            String planCobertura, String marcaVehiculo,
                                                            String anioVehiculo , String categoriaVehiculo,
                                                            int    planPago )                                           throws Exception, BSEExceptionTienda;

    public PolizaSoaTienda               emitirSoa( DTSesionTienda dtSesion,
                                                    String tipoDocumento    , String documento,
                                                    String matriculaVehiculo, String padronVehiculo,
                                                    String motorVehiculo    , String chasisVehiculo,
                                                    long   nroCotizacion    , String consumoFinal )                     throws Exception, BSEExceptionTienda;

    public List<MarcaVehiculoTienda>     consultaMarcasVehiculos( DTSesionTienda dtSesion )                             throws Exception, BSEExceptionTienda;

    public MarcaVehiculoTienda           consultaMarcaVehiculo( DTSesionTienda dtSesion, String marca )                 throws Exception, BSEExceptionTienda;

    public List<CategoriaVehiculoTienda> consultaCategoriasVehiculos( DTSesionTienda dtSesion )                         throws Exception, BSEExceptionTienda;

    public CategoriaVehiculoTienda       consultaCategoriaVehiculo( DTSesionTienda dtSesion, String categoria )         throws Exception, BSEExceptionTienda;

    public void alertarPagoRedes(DTSesionTienda dtSesion, int sucursal, int ramo, String producto, long nroCotizacion)  throws Exception, BSEExceptionTienda;

}
