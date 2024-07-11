package com.bse.negocio.esoa;

import java.util.List;

import javax.persistence.EntityManager;

import com.bse.accesodatos.esoa.CategoriaVehiculoTienda;
import com.bse.accesodatos.esoa.CotizacionSoaTienda;
import com.bse.accesodatos.esoa.MarcaVehiculoTienda;
import com.bse.accesodatos.esoa.PolizaSoaTienda;
import com.bse.negocio.comun.BSEExceptionTienda;

public interface IESoaTienda {

    public CotizacionSoaTienda           cotizarSoaAnonimo( EntityManager em,
                                                            String planCobertura, String marcaVehiculo,
                                                            String anioVehiculo , String categoriaVehiculo,
                                                            int    planPago )                                           throws Exception, BSEExceptionTienda;

    public PolizaSoaTienda               emitirSoa( EntityManager em,
                                                    String tipoDocumento    , String documento,
                                                    String matriculaVehiculo, String padronVehiculo,
                                                    String motorVehiculo    , String chasisVehiculo,
                                                    long   nroCotizacion    , String consumoFinal )                     throws Exception, BSEExceptionTienda;

    public List<MarcaVehiculoTienda>     consultaMarcasVehiculos(EntityManager em)                                      throws Exception, BSEExceptionTienda;

    public MarcaVehiculoTienda           consultaMarcaVehiculo( EntityManager em, String marca )                        throws Exception, BSEExceptionTienda;

    public List<CategoriaVehiculoTienda> consultaCategoriasVehiculos(EntityManager em)                                  throws Exception, BSEExceptionTienda;

    public CategoriaVehiculoTienda       consultaCategoriaVehiculo( EntityManager em, String categoria )                throws Exception, BSEExceptionTienda;


    public void alertarPagoRedes( EntityManager em, int sucursal, int ramo, String producto, long nroCotizacion)        throws Exception, BSEExceptionTienda;

}
