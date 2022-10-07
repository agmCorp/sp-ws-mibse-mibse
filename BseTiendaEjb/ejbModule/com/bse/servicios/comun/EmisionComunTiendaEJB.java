package com.bse.servicios.comun;

import java.util.List;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.bse.accesodatos.esoa.PlanCoberturaTienda;
import com.bse.accesodatos.esoa.PlanPagoTienda;
import com.bse.negocio.FabricaNegocioTienda;
import com.bse.negocio.comun.BSEExceptionTienda;
import com.bse.negocio.comun.IEComunTienda;
import com.bse.servicios.seguridad.SeguridadTiendaws;
import com.bse.servicios.seguridad.dt.DTSesionTienda;


@Stateless(mappedName = "EmisionComunTiendaEJB")
@Interceptors({SeguridadTiendaws.class})
public class EmisionComunTiendaEJB implements IEmisionComunTiendaEJBLocal {

    @PersistenceContext(unitName = "bselatiendaws-pu")
    private EntityManager em;

    @Override
    public String clienteConDeuda( DTSesionTienda dtSesion, 
                                   String  tipoDocumento,
                                   String  nroDocumento, 
                                   Integer nroCotizacion, 
                                   Integer nroCertificado ) throws Exception, BSEExceptionTienda {
        IEComunTienda eComunManager = FabricaNegocioTienda.getFabricaNegocio().getEComunMgr();
        return eComunManager.clienteConDeuda(em, tipoDocumento, nroDocumento, nroCotizacion, nroCertificado);
    }

    @Override
    public List<PlanCoberturaTienda> consultaPlanesCobertura(DTSesionTienda dtSesion, int ramo, String producto) throws Exception, BSEExceptionTienda{
        IEComunTienda eComunManager = FabricaNegocioTienda.getFabricaNegocio().getEComunMgr();
        return eComunManager.consultaPlanesCobertura(em, ramo, producto);
    }

    @Override
    public PlanCoberturaTienda consultaPlanCobertura(DTSesionTienda dtSesion, String plan) throws Exception, BSEExceptionTienda{
        IEComunTienda eComunManager = FabricaNegocioTienda.getFabricaNegocio().getEComunMgr();
        return eComunManager.consultaPlanCobertura(em, plan);
    }

    @Override
    public PlanCoberturaTienda consultaPlanCoberturaRamo( DTSesionTienda dtSesion, int ramo, String plan) throws Exception, BSEExceptionTienda {
        IEComunTienda eComunManager = FabricaNegocioTienda.getFabricaNegocio().getEComunMgr();
        return eComunManager.consultaPlanCoberturaRamo(em, ramo, plan);
    }

    @Override
    public PlanCoberturaTienda consultaPlanCoberturaRamoProducto(DTSesionTienda dtSesion, int ramo, String producto, String plan) throws Exception, BSEExceptionTienda {
        IEComunTienda eComunManager = FabricaNegocioTienda.getFabricaNegocio().getEComunMgr();
        return eComunManager.consultaPlanCoberturaRamoProducto(em, ramo, producto, plan);
    }

    @Override
    public List<PlanPagoTienda> consultaPlanesPago(DTSesionTienda dtSesion) throws Exception, BSEExceptionTienda {
        IEComunTienda eComunManager = FabricaNegocioTienda.getFabricaNegocio().getEComunMgr();
        List<PlanPagoTienda> lista = eComunManager.consultaPlanesPago(em);
        return lista;
    }

    @Override
    public PlanPagoTienda consultaPlanPago(DTSesionTienda dtSesion, int plan) throws Exception, BSEExceptionTienda {
        IEComunTienda eComunManager = FabricaNegocioTienda.getFabricaNegocio().getEComunMgr();
        return eComunManager.consultaPlanPago(em, plan);
    }
    
}
