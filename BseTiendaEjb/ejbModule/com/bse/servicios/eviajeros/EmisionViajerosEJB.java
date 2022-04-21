package com.bse.servicios.eviajeros;


import java.util.ArrayList;
import java.util.Date;

import com.bse.accesodatos.eviajeros.CoberturaMuerte;
import com.bse.accesodatos.eviajeros.CoberturaPrexistentes;
import com.bse.accesodatos.eviajeros.CotizacionViajeros;
import com.bse.accesodatos.eviajeros.PolizaViajeros;
import com.bse.accesodatos.eviajeros.Viajero;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import com.bse.servicios.seguridad.dt.DTSesion;
import com.bse.negocio.FabricaNegocio;
import com.bse.negocio.comun.BSEException;
import com.bse.negocio.eviajeros.IEViajeros;
import com.bse.servicios.seguridad.Seguridadbseonlinews;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless(mappedName = "EmisionViajerosEJB")
@Interceptors({Seguridadbseonlinews.class})
public class EmisionViajerosEJB implements IEmisionViajerosEJBLocal {

    @PersistenceContext(unitName = "bseonlinews-pu")
    private EntityManager em;

	@Override
	public CotizacionViajeros cotizarViajerosAnonimo(DTSesion dtSesion, String planCobertura, int planPago,
	        Date fechaDesde, Date fechaHasta, ArrayList<Viajero> listaPersonas, String extension, Date fechaSalidaPais) throws Exception, BSEException {

		IEViajeros eViajerosManager = FabricaNegocio.getFabricaNegocio().getEViajerosMgr();
		return eViajerosManager.cotizarViajerosAnonimo(em, planCobertura, planPago, fechaDesde, fechaHasta, 
				listaPersonas, extension, fechaSalidaPais);
	}

	@Override
	public PolizaViajeros emitirViajeros(DTSesion dtSesion, String tipoDocumentoContratante, String documentoContratante, 
	        ArrayList<Viajero> listaPersonas, long nroCotizacion, String consumoFinal, String extension, Date fechaSalidaPais) throws Exception, BSEException {

		IEViajeros eViajerosManager = FabricaNegocio.getFabricaNegocio().getEViajerosMgr();
		return eViajerosManager.emitirViajeros(em, tipoDocumentoContratante, documentoContratante, listaPersonas, 
				nroCotizacion, consumoFinal, extension, fechaSalidaPais);
	}

	@Override
	public ArrayList<CoberturaPrexistentes> consultaCoberturasPrexistentes(DTSesion dtSesion) throws Exception, BSEException {
		IEViajeros eViajerosManager = FabricaNegocio.getFabricaNegocio().getEViajerosMgr();
		return eViajerosManager.consultaCoberturasPrexistentes(em);
	}

	@Override
	public ArrayList<CoberturaMuerte> consultaCoberturasMuerte(DTSesion dtSesion) throws Exception, BSEException {
		IEViajeros eViajerosManager = FabricaNegocio.getFabricaNegocio().getEViajerosMgr();
		return eViajerosManager.consultaCoberturasMuerte(em);
	}

}
