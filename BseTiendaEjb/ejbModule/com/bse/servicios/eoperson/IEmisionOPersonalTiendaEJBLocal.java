package com.bse.servicios.eoperson;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import com.bse.accesodatos.comun.ItemCodiguera;
import com.bse.accesodatos.eoperson.CotizacionOPersonalTienda;
import com.bse.accesodatos.eoperson.PolizaOPersonalTienda;
import com.bse.accesodatos.esoa.PlanCoberturaTienda;
import com.bse.negocio.comun.BSEExceptionTienda;
import com.bse.servicios.seguridad.dt.DTSesionTienda;

@Local
public interface IEmisionOPersonalTiendaEJBLocal {

    public CotizacionOPersonalTienda cotizarOPersonalAnonimo ( DTSesionTienda dtSesion,
                                                               String planCobertura,
                                                               String tipoObjeto,
                                                               Double valorObjeto,
                                                               String movilidad )        throws Exception, BSEExceptionTienda;

    public PolizaOPersonalTienda     emitirOPersonal         ( DTSesionTienda dtSesion,
                                                               String tipoDocumento,
                                                               String documento,
                                                               String marca,
                                                               String serie,
                                                               String modelo,
                                                               long   nroCotizacion,
                                                               String planPago,
                                                               Date   fechaFactura,
                                                               String consumoFinal )     throws Exception, BSEExceptionTienda;

    public List<PlanCoberturaTienda> consultaPlanesCobertura ( DTSesionTienda dtSesion ) throws Exception, BSEExceptionTienda;

    public List<ItemCodiguera>       consultaTiposObjetos    ( DTSesionTienda dtSesion ) throws Exception, BSEExceptionTienda;

    public List<ItemCodiguera>       consultaTiposMovilidad  ( DTSesionTienda dtSesion ) throws Exception, BSEExceptionTienda;
}
