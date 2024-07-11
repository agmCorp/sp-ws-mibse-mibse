package com.bse.servicios.eviajeros;


import java.util.ArrayList;
import java.util.Date;

import com.bse.accesodatos.eviajeros.CoberturaMuerteTienda;
import com.bse.accesodatos.eviajeros.CoberturaPrexistentesTienda;
import com.bse.accesodatos.eviajeros.CotizacionViajerosTienda;
import com.bse.accesodatos.eviajeros.PolizaViajerosTienda;
import com.bse.accesodatos.eviajeros.ViajeroTienda;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import com.bse.servicios.seguridad.dt.DTSesionTienda;
import com.bse.negocio.FabricaNegocioTienda;
import com.bse.negocio.comun.BSEExceptionTienda;
import com.bse.negocio.eviajeros.IEViajerosTienda;
import com.bse.servicios.seguridad.SeguridadTiendaws;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless(mappedName = "EmisionViajerosTiendaEJB")
@Interceptors({SeguridadTiendaws.class})
public class EmisionViajerosTiendaEJB implements IEmisionViajerosTiendaEJBLocal {

    @PersistenceContext(unitName = "bselatiendaws-pu")
    private EntityManager em;

    @Override
    public CotizacionViajerosTienda cotizarViajerosAnonimo( DTSesionTienda dtSesion, 
                                                            String planCobertura, 
                                                            int    planPago,
                                                            Date   fechaDesde, 
                                                            Date   fechaHasta, 
                                                            ArrayList<ViajeroTienda> listaPersonas, 
                                                            String extension, 
                                                            Date   fechaSalidaPais ) throws Exception, BSEExceptionTienda {

        IEViajerosTienda eViajerosManager = FabricaNegocioTienda.getFabricaNegocio().getEViajerosMgr();
        return eViajerosManager.cotizarViajerosAnonimo( em, 
                                                        planCobertura, planPago, fechaDesde, fechaHasta,
                                                        listaPersonas, extension, fechaSalidaPais );
    }

    @Override
    public PolizaViajerosTienda emitirViajeros( DTSesionTienda dtSesion, 
                                                String tipoDocumentoContratante, 
                                                String documentoContratante,
                                                ArrayList<ViajeroTienda> listaPersonas, 
                                                long   nroCotizacion, 
                                                String consumoFinal, 
                                                String extension, 
                                                Date   fechaSalidaPais ) throws Exception, BSEExceptionTienda {

        IEViajerosTienda eViajerosManager = FabricaNegocioTienda.getFabricaNegocio().getEViajerosMgr();
        return eViajerosManager.emitirViajeros( em, 
                                                tipoDocumentoContratante, documentoContratante, listaPersonas,
                                                nroCotizacion, consumoFinal, extension, fechaSalidaPais );
    }

    @Override
    public ArrayList<CoberturaPrexistentesTienda> consultaCoberturasPrexistentes(DTSesionTienda dtSesion) throws Exception, BSEExceptionTienda {
        IEViajerosTienda eViajerosManager = FabricaNegocioTienda.getFabricaNegocio().getEViajerosMgr();
        return eViajerosManager.consultaCoberturasPrexistentes(em);
    }

    @Override
    public ArrayList<CoberturaMuerteTienda> consultaCoberturasMuerte(DTSesionTienda dtSesion) throws Exception, BSEExceptionTienda {
        IEViajerosTienda eViajerosManager = FabricaNegocioTienda.getFabricaNegocio().getEViajerosMgr();
        return eViajerosManager.consultaCoberturasMuerte(em);
    }

}
