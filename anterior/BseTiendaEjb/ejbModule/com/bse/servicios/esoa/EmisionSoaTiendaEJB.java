package com.bse.servicios.esoa;


import java.util.List;

import com.bse.accesodatos.esoa.CategoriaVehiculoTienda;
import com.bse.accesodatos.esoa.CotizacionSoaTienda;
import com.bse.accesodatos.esoa.MarcaVehiculoTienda;
import com.bse.accesodatos.esoa.PolizaSoaTienda;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import com.bse.servicios.seguridad.dt.DTSesionTienda;
import com.bse.negocio.FabricaNegocioTienda;
import com.bse.negocio.comun.BSEExceptionTienda;
import com.bse.negocio.esoa.IESoaTienda;
import com.bse.servicios.seguridad.SeguridadTiendaws;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless(mappedName = "EmisionSoaTiendaEJB")
@Interceptors({SeguridadTiendaws.class})
public class EmisionSoaTiendaEJB implements IEmisionSoaTiendaEJBLocal {

    @PersistenceContext(unitName = "bselatiendaws-pu")
    private EntityManager em;


    @Override
    public CotizacionSoaTienda cotizarSoaAnonimo( DTSesionTienda dtSesion,
                                                  String planCobertura,
                                                  String marcaVehiculo,
                                                  String anioVehiculo,
                                                  String categoriaVehiculo,
                                                  int    planPago ) throws Exception, BSEExceptionTienda {
        IESoaTienda eSoaManager = FabricaNegocioTienda.getFabricaNegocio().getESoaMgr();
        return eSoaManager.cotizarSoaAnonimo( em,
                                              planCobertura, marcaVehiculo, anioVehiculo, categoriaVehiculo, planPago );
    }

    @Override
    public PolizaSoaTienda emitirSoa( DTSesionTienda dtSesion,
                                      String tipoDocumento,
                                      String documento,
                                      String matriculaVehiculo,
                                      String padronVehiculo,
                                      String motorVehiculo,
                                      String chasisVehiculo,
                                      long   nroCotizacion,
                                      String consumoFinal ) throws Exception, BSEExceptionTienda {
        IESoaTienda eSoaManager = FabricaNegocioTienda.getFabricaNegocio().getESoaMgr();
        return eSoaManager.emitirSoa( em,
                                      tipoDocumento, documento, matriculaVehiculo, padronVehiculo,
                                      motorVehiculo, chasisVehiculo, nroCotizacion, consumoFinal );
    }

    @Override
    public List<MarcaVehiculoTienda> consultaMarcasVehiculos(DTSesionTienda dtSesion) throws Exception, BSEExceptionTienda {
        IESoaTienda eSoaManager = FabricaNegocioTienda.getFabricaNegocio().getESoaMgr();
        return eSoaManager.consultaMarcasVehiculos(em);
    }

    @Override
    public MarcaVehiculoTienda consultaMarcaVehiculo(DTSesionTienda dtSesion, String marca) throws Exception, BSEExceptionTienda {
        IESoaTienda eSoaManager = FabricaNegocioTienda.getFabricaNegocio().getESoaMgr();
        return eSoaManager.consultaMarcaVehiculo(em, marca);
    }

    @Override
    public CategoriaVehiculoTienda consultaCategoriaVehiculo(DTSesionTienda dtSesion, String categoria) throws Exception, BSEExceptionTienda {
        IESoaTienda eSoaManager = FabricaNegocioTienda.getFabricaNegocio().getESoaMgr();
        return eSoaManager.consultaCategoriaVehiculo(em, categoria);
    }

    @Override
    public List<CategoriaVehiculoTienda> consultaCategoriasVehiculos(DTSesionTienda dtSesion) throws Exception, BSEExceptionTienda {
        IESoaTienda eSoaManager = FabricaNegocioTienda.getFabricaNegocio().getESoaMgr();
        return eSoaManager.consultaCategoriasVehiculos(em);
    }

    @Override
    public void alertarPagoRedes(DTSesionTienda dtSesion, int sucursal, int ramo, String producto, long nroCotizacion) throws Exception, BSEExceptionTienda {
        IESoaTienda eSoaManager = FabricaNegocioTienda.getFabricaNegocio().getESoaMgr();
        eSoaManager.alertarPagoRedes(em, sucursal, ramo, producto, nroCotizacion);
    }

}
