package com.bse.servicios.edepor;


import java.util.Date;
import java.util.List;

import com.bse.accesodatos.edepor.CoberturaRc;
import com.bse.accesodatos.edepor.CotizacionEDepor;
import com.bse.accesodatos.edepor.PolizaEDepor;
import com.bse.accesodatos.edepor.TipoBuque;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import com.bse.servicios.seguridad.dt.DTSesion;
import com.bse.negocio.FabricaNegocio;
import com.bse.negocio.comun.BSEException;
import com.bse.negocio.edepor.IEDepor;
import com.bse.servicios.seguridad.Seguridadbseonlinews;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless(mappedName = "EmisionDeporEJB")
@Interceptors({Seguridadbseonlinews.class})
public class EmisionEDeporEJB implements IEmisionEDeporEJBLocal {

    @PersistenceContext(unitName = "bseonlinews-pu")
    private EntityManager em;

	@Override
	public List<TipoBuque> consultaTiposBuques(DTSesion dtSesion) throws Exception, BSEException {
		IEDepor eDeporManager = FabricaNegocio.getFabricaNegocio().getEDeporMgr();
		return eDeporManager.consultaTiposBuques(em);
	}

	@Override
	public CotizacionEDepor cotizarEDeporAnonimo(DTSesion dtSesion, String planCobertura, String tipoBuque,
	        Date fechaDesde, Date fechaHasta, double capital, int planPago) throws Exception, BSEException {
		IEDepor eDeporManager = FabricaNegocio.getFabricaNegocio().getEDeporMgr();
		return eDeporManager.cotizarEDeporAnonimo(em, planCobertura, tipoBuque, fechaDesde, fechaHasta, capital, planPago);
	}

	@Override
	public PolizaEDepor emitirEDepor(DTSesion dtSesion, String tipoDocumento, String documento, String matriculaBuque, String nombreBuque, long nroCotizacion, String consumoFinal) throws Exception, BSEException {
		IEDepor eDeporManager = FabricaNegocio.getFabricaNegocio().getEDeporMgr();
		return eDeporManager.emitirEDepor(em, tipoDocumento, documento, matriculaBuque, nombreBuque, nroCotizacion, consumoFinal);
	}

	@Override
	public List<CoberturaRc> consultaCoberturasRc(DTSesion dtSesion) throws Exception, BSEException {
		IEDepor eDeporManager = FabricaNegocio.getFabricaNegocio().getEDeporMgr();
		return eDeporManager.consultaCoberturasRc(em);
	}

}
