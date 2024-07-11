package com.bse.servicios.edepor;


import java.util.Date;
import java.util.List;

import com.bse.accesodatos.edepor.CoberturaRcTienda;
import com.bse.accesodatos.edepor.CotizacionEDeporTienda;
import com.bse.accesodatos.edepor.PolizaEDeporTienda;
import com.bse.accesodatos.edepor.TipoBuqueTienda;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import com.bse.servicios.seguridad.dt.DTSesionTienda;
import com.bse.negocio.FabricaNegocioTienda;
import com.bse.negocio.comun.BSEExceptionTienda;
import com.bse.negocio.edepor.IEDeporTienda;
import com.bse.servicios.seguridad.SeguridadTiendaws;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless(mappedName = "EmisionEDeporTiendaEJB")
@Interceptors({SeguridadTiendaws.class})
public class EmisionEDeporTiendaEJB implements IEmisionEDeporTiendaEJBLocal {

    @PersistenceContext(unitName = "bselatiendaws-pu")
    private EntityManager em;

    @Override
    public List<TipoBuqueTienda> consultaTiposBuques(DTSesionTienda dtSesion) throws Exception, BSEExceptionTienda {
        IEDeporTienda eDeporManager = FabricaNegocioTienda.getFabricaNegocio().getEDeporMgr();
        return eDeporManager.consultaTiposBuques(em);
    }

    @Override
    public CotizacionEDeporTienda cotizarEDeporAnonimo( DTSesionTienda dtSesion,
                                                        String planCobertura,
                                                        String tipoBuque,
                                                        Date   fechaDesde,
                                                        Date   fechaHasta,
                                                        double capital,
                                                        int    planPago ) throws Exception, BSEExceptionTienda {
        IEDeporTienda eDeporManager = FabricaNegocioTienda.getFabricaNegocio().getEDeporMgr();
        return eDeporManager.cotizarEDeporAnonimo( em,
                                                   planCobertura, tipoBuque, fechaDesde, fechaHasta, capital, planPago );
    }

    @Override
    public PolizaEDeporTienda emitirEDepor( DTSesionTienda dtSesion,
                                            String tipoDocumento,
                                            String documento,
                                            String matriculaBuque,
                                            String nombreBuque,
                                            long   nroCotizacion,
                                            String consumoFinal ) throws Exception, BSEExceptionTienda {
        IEDeporTienda eDeporManager = FabricaNegocioTienda.getFabricaNegocio().getEDeporMgr();
        return eDeporManager.emitirEDepor( em,
                                           tipoDocumento, documento, matriculaBuque, nombreBuque, nroCotizacion, consumoFinal );
    }

    @Override
    public List<CoberturaRcTienda> consultaCoberturasRc(DTSesionTienda dtSesion) throws Exception, BSEExceptionTienda {
        IEDeporTienda eDeporManager = FabricaNegocioTienda.getFabricaNegocio().getEDeporMgr();
        return eDeporManager.consultaCoberturasRc(em);
    }

}
