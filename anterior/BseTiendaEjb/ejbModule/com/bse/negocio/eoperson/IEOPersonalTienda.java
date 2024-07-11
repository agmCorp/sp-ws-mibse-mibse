package com.bse.negocio.eoperson;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import com.bse.accesodatos.comun.ItemCodiguera;
import com.bse.accesodatos.eoperson.CotizacionOPersonalTienda;
import com.bse.accesodatos.eoperson.PolizaOPersonalTienda;
import com.bse.accesodatos.esoa.PlanCoberturaTienda;
import com.bse.negocio.comun.BSEExceptionTienda;

public interface IEOPersonalTienda {

    public CotizacionOPersonalTienda cotizarOPersonalAnonimo ( EntityManager em,
                                                               String planCobertura,
                                                               String tipoObjeto,
                                                               Double valorObjeto,
                                                               String movilidad )    throws Exception, BSEExceptionTienda;

    public PolizaOPersonalTienda     emitirOPersonal         ( EntityManager em,
                                                               String tipoDocumento,
                                                               String documento,
                                                               String marca,
                                                               String serie,
                                                               String modelo,
                                                               long   nroCotizacion,
                                                               String planPago,
                                                               Date   fechaFactura,
                                                               String consumoFinal ) throws Exception, BSEExceptionTienda;

    public List<PlanCoberturaTienda> consultaPlanesCobertura ( EntityManager em)     throws Exception, BSEExceptionTienda;

    public List<ItemCodiguera>       consultaTiposObjetos    ( EntityManager em )    throws Exception, BSEExceptionTienda;

    public List<ItemCodiguera>       consultaTiposMovilidad  ( EntityManager em )    throws Exception, BSEExceptionTienda;
}
