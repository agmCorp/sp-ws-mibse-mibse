package com.bse.servicios.ebicis;


import java.util.Date;
import java.util.List;

import com.bse.accesodatos.ebici.CotizacionBiciTienda;
import com.bse.accesodatos.ebici.PolizaBiciTienda;
import com.bse.accesodatos.ebici.TipoBiciTienda;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import com.bse.servicios.seguridad.dt.DTSesionTienda;
import com.bse.negocio.FabricaNegocioTienda;
import com.bse.negocio.comun.BSEExceptionTienda;
import com.bse.negocio.ebicis.IEBiciTienda;
import com.bse.servicios.seguridad.SeguridadTiendaws;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless(mappedName = "EmisionBiciTiendaEJB")
@Interceptors({SeguridadTiendaws.class})
public class EmisionBiciTiendaEJB implements IEmisionBiciTiendaEJBLocal {

    @PersistenceContext(unitName = "bselatiendaws-pu")
    private EntityManager em;


    @Override
    public CotizacionBiciTienda cotizarBiciAnonimo( DTSesionTienda dtSesion,
                                                    String  planCobertura,
                                                    Double  valorBicicleta,
                                                    Integer vigenciaSeguro ) throws Exception, BSEExceptionTienda {
        IEBiciTienda eBiciManager = FabricaNegocioTienda.getFabricaNegocio().getEBiciMgr();
        return eBiciManager.cotizarBiciAnonimo(em, planCobertura, valorBicicleta, vigenciaSeguro);
    }

    @Override
    public PolizaBiciTienda emitirBici( DTSesionTienda dtSesion,
                                        String planPago,
                                        String tipoDocumento,
                                        String documento,
                                        Date   fechaFactura,
                                        String tipoBicicleta,
                                        Date   fechaNacimientoCliente,
                                        String marca,
                                        String serie,
                                        String modelo,
                                        long   nroCotizacion,
                                        String consumoFinal ) throws Exception, BSEExceptionTienda {

        IEBiciTienda eBiciManager = FabricaNegocioTienda.getFabricaNegocio().getEBiciMgr();
        return eBiciManager.emitirBici( em,
                                        planPago, tipoDocumento, documento, fechaFactura, tipoBicicleta,
                                        fechaNacimientoCliente, marca, serie, modelo, nroCotizacion, consumoFinal);
    }

    @Override
    public String clienteConDeuda( DTSesionTienda dtSesion,
                                   String  tipoDocumento,
                                   String  nroDocumento,
                                   Integer nroCotizacion,
                                   Integer nroCertificado ) throws Exception, BSEExceptionTienda{
        IEBiciTienda eBiciManager = FabricaNegocioTienda.getFabricaNegocio().getEBiciMgr();
        return eBiciManager.clienteConDeuda( em,
                                             tipoDocumento, nroDocumento, nroCotizacion, nroCertificado );
    }

    @Override
    public List<TipoBiciTienda> consultaTiposBicicletas(DTSesionTienda dtSesion) throws Exception, BSEExceptionTienda {
        IEBiciTienda eBiciManager = FabricaNegocioTienda.getFabricaNegocio().getEBiciMgr();
        return eBiciManager.consultaTiposBicicletas(em);
    }

}
