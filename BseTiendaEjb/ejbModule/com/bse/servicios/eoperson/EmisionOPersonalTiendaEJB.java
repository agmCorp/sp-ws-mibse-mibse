package com.bse.servicios.eoperson;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.bse.accesodatos.comun.ItemCodiguera;
import com.bse.accesodatos.eoperson.CotizacionOPersonalTienda;
import com.bse.accesodatos.eoperson.PolizaOPersonalTienda;
import com.bse.accesodatos.esoa.PlanCoberturaTienda;
import com.bse.negocio.FabricaNegocioTienda;
import com.bse.negocio.comun.BSEExceptionTienda;
import com.bse.negocio.eoperson.IEOPersonalTienda;
import com.bse.servicios.seguridad.SeguridadTiendaws;
import com.bse.servicios.seguridad.dt.DTSesionTienda;


@Stateless(mappedName = "EmisionOPersonalTiendaEJB")
@Interceptors({SeguridadTiendaws.class})
public class EmisionOPersonalTiendaEJB implements IEmisionOPersonalTiendaEJBLocal {

    @PersistenceContext(unitName = "bselatiendaws-pu")
    private EntityManager em;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public CotizacionOPersonalTienda cotizarOPersonalAnonimo( DTSesionTienda dtSesion,
                                                              String planCobertura,
                                                              String tipoObjeto,
                                                              Double valorObjeto,
                                                              String movilidad ) throws Exception, BSEExceptionTienda {
        IEOPersonalTienda eOPersonalManager = FabricaNegocioTienda.getFabricaNegocio().getEOPersonalMgr();
        return eOPersonalManager.cotizarOPersonalAnonimo( em,
                                                          planCobertura,
                                                          tipoObjeto,
                                                          valorObjeto,
                                                          movilidad );
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public PolizaOPersonalTienda emitirOPersonal( DTSesionTienda dtSesion,
                                                  String tipoDocumento,
                                                  String documento,
                                                  String marca,
                                                  String serie,
                                                  String modelo,
                                                  long   nroCotizacion,
                                                  String planPago,
                                                  Date   fechaFactura,
                                                  String consumoFinal ) throws Exception, BSEExceptionTienda {
        IEOPersonalTienda eOPersonalManager = FabricaNegocioTienda.getFabricaNegocio().getEOPersonalMgr();
        return eOPersonalManager.emitirOPersonal( em,
                                                  tipoDocumento, documento, marca, serie, modelo,
                                                  nroCotizacion, planPago, fechaFactura, consumoFinal);
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public List<PlanCoberturaTienda> consultaPlanesCobertura(DTSesionTienda dtSesion) throws Exception, BSEExceptionTienda {
        IEOPersonalTienda eOPersonalManager = FabricaNegocioTienda.getFabricaNegocio().getEOPersonalMgr();
        return eOPersonalManager.consultaPlanesCobertura(em);
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public List<ItemCodiguera> consultaTiposObjetos(DTSesionTienda dtSesion) throws Exception, BSEExceptionTienda {
        IEOPersonalTienda eOPersonalManager = FabricaNegocioTienda.getFabricaNegocio().getEOPersonalMgr();
        return eOPersonalManager.consultaTiposObjetos(em);
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public List<ItemCodiguera> consultaTiposMovilidad(DTSesionTienda dtSesion) throws Exception, BSEExceptionTienda {
        IEOPersonalTienda eOPersonalManager = FabricaNegocioTienda.getFabricaNegocio().getEOPersonalMgr();
        return eOPersonalManager.consultaTiposMovilidad(em);
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
