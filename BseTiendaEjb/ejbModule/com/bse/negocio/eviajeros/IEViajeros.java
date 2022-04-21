package com.bse.negocio.eviajeros;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.EntityManager;

import com.bse.accesodatos.eviajeros.CoberturaMuerte;
import com.bse.accesodatos.eviajeros.CoberturaPrexistentes;
import com.bse.accesodatos.eviajeros.CotizacionViajeros;
import com.bse.accesodatos.eviajeros.PolizaViajeros;
import com.bse.accesodatos.eviajeros.Viajero;
import com.bse.negocio.comun.BSEException;

public interface IEViajeros {

	public CotizacionViajeros cotizarViajerosAnonimo(EntityManager em, String planCobertura, int planPago, Date fechaDesde, Date fechaHasta,
			ArrayList<Viajero> listaPersonas, String extension, Date fechaSalidaPais) throws Exception, BSEException;

	public PolizaViajeros emitirViajeros(EntityManager em, String tipoDocumentoContratante, String documentoContratante, 
			ArrayList<Viajero> listaPersonas, long nroCotizacion, String consumoFinal, String extension, Date fechaSalidaPais) throws Exception, BSEException;

	public ArrayList<CoberturaPrexistentes> consultaCoberturasPrexistentes(EntityManager em) throws Exception, BSEException;

	public ArrayList<CoberturaMuerte> consultaCoberturasMuerte(EntityManager em) throws Exception, BSEException;
	
}
