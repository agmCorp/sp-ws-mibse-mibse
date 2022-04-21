package com.bse.servicios.ebicis;


import java.util.Date;
import java.util.List;

import com.bse.accesodatos.ebici.CotizacionBici;
import com.bse.accesodatos.ebici.PolizaBici;
import com.bse.accesodatos.ebici.TipoBici;
import com.bse.accesodatos.esoa.PolizaSoa;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import com.bse.servicios.seguridad.dt.DTSesion;
import com.bse.negocio.FabricaNegocio;
import com.bse.negocio.comun.BSEException;
import com.bse.negocio.ebicis.IEBici;
import com.bse.servicios.seguridad.Seguridadbseonlinews;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless(mappedName = "EmisionSoaEJB")
@Interceptors({Seguridadbseonlinews.class})
public class EmisionBiciEJB implements IEmisionBiciEJBLocal {

    @PersistenceContext(unitName = "bseonlinews-pu")
    private EntityManager em;

    
	@Override
	public CotizacionBici cotizarBiciAnonimo(DTSesion dtSesion, String planCobertura, Double valorBicicleta, Integer vigenciaSeguro) throws Exception, BSEException {
		IEBici eBiciManager = FabricaNegocio.getFabricaNegocio().getEBiciMgr();
		return eBiciManager.cotizarBiciAnonimo(em, planCobertura, valorBicicleta, vigenciaSeguro);
	}

	@Override
	public PolizaBici emitirBici(DTSesion dtSesion, String planPago, 
			String tipoDocumento,
			String documento, Date fechaFactura, String tipoBicicleta,
			Date fechaNacimientoCliente, String marca, String serie,
			String modelo, long nroCotizacion, String consumoFinal) throws Exception, BSEException {
		
		IEBici eBiciManager = FabricaNegocio.getFabricaNegocio().getEBiciMgr();
		return eBiciManager.emitirBici(em, planPago, tipoDocumento,
				documento, fechaFactura, tipoBicicleta,
				fechaNacimientoCliente, marca, serie,
				modelo, nroCotizacion, consumoFinal);
	}

	@Override
	public String clienteConDeuda(DTSesion dtSesion, String tipoDocumento, String nroDocumento, Integer nroCotizacion, Integer nroCertificado) throws Exception, BSEException{
		IEBici eBiciManager = FabricaNegocio.getFabricaNegocio().getEBiciMgr();
		return eBiciManager.clienteConDeuda(em, tipoDocumento, nroDocumento, nroCotizacion, nroCertificado);
	}

	@Override
	public List<TipoBici> consultaTiposBicicletas(DTSesion dtSesion) throws Exception, BSEException {
		IEBici eBiciManager = FabricaNegocio.getFabricaNegocio().getEBiciMgr();
		return eBiciManager.consultaTiposBicicletas(em);
	}

}
