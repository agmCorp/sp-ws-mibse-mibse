package com.bse.negocio.comun;

import java.util.List;

import javax.persistence.EntityManager;

import com.bse.accesodatos.esoa.MonedaTienda;
import com.bse.accesodatos.esoa.PlanCoberturaTienda;
import com.bse.accesodatos.esoa.PlanPagoTienda;

public interface IEComunTienda {

    public String                    clienteConDeuda( EntityManager em,
                                                      String  tipoDocumento, String  nroDocumento,
                                                      Integer nroCotizacion, Integer nroCertificado )                   throws Exception, BSEExceptionTienda;

    public MonedaTienda              consultaMoneda( EntityManager em, String codigoMoneda )                            throws Exception, BSEExceptionTienda;

    public List<PlanCoberturaTienda> consultaPlanesCobertura( EntityManager em, int ramo, String producto )             throws Exception, BSEExceptionTienda;

    public PlanCoberturaTienda       consultaPlanCobertura( EntityManager em, String plan )                             throws Exception, BSEExceptionTienda;

    public PlanCoberturaTienda       consultaPlanCoberturaRamo( EntityManager em, int ramo, String plan )               throws Exception, BSEExceptionTienda;

    public PlanCoberturaTienda       consultaPlanCoberturaRamoProducto( EntityManager em,
                                                                        int ramo, String producto, String plan )        throws Exception, BSEExceptionTienda;

    public List<PlanPagoTienda>      consultaPlanesPago(EntityManager em)                                               throws Exception, BSEExceptionTienda;

    public PlanPagoTienda            consultaPlanPago( EntityManager em, int plan )                                     throws Exception, BSEExceptionTienda;

    public boolean                   validarExistenciaCotizacion( EntityManager em, 
                                                                  int sucursal, long nroCotizacion, 
                                                                  int ramo    , String producto )                       throws Exception, BSEExceptionTienda;

}
