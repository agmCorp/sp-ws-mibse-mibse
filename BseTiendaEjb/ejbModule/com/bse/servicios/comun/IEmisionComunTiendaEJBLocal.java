package com.bse.servicios.comun;


import java.util.List;

import javax.ejb.Local;

import com.bse.accesodatos.esoa.PlanCoberturaTienda;
import com.bse.accesodatos.esoa.PlanPagoTienda;
import com.bse.negocio.comun.BSEExceptionTienda;
import com.bse.servicios.seguridad.dt.DTSesionTienda;

@Local
public interface IEmisionComunTiendaEJBLocal {

    public String                    clienteConDeuda( DTSesionTienda dtSesion, 
                                                      String  tipoDocumento, String  nroDocumento, 
                                                      Integer nroCotizacion, Integer nroCertificado )                   throws Exception, BSEExceptionTienda;

    public List<PlanCoberturaTienda> consultaPlanesCobertura( DTSesionTienda dtSesion, int ramo, String producto )      throws Exception, BSEExceptionTienda;
    
    public PlanCoberturaTienda       consultaPlanCobertura( DTSesionTienda dtSesion, String plan )                      throws Exception, BSEExceptionTienda;

    public PlanCoberturaTienda       consultaPlanCoberturaRamo( DTSesionTienda dtSesion, int ramo, String plan )        throws Exception, BSEExceptionTienda;
    
    public PlanCoberturaTienda       consultaPlanCoberturaRamoProducto( DTSesionTienda dtSesion, 
                                                                        int ramo, String producto, String plan )        throws Exception, BSEExceptionTienda;
    
    public List<PlanPagoTienda>      consultaPlanesPago( DTSesionTienda dtSesion )                                      throws Exception, BSEExceptionTienda;

    public PlanPagoTienda            consultaPlanPago( DTSesionTienda dtSesion, int plan )                              throws Exception, BSEExceptionTienda;
    
}
