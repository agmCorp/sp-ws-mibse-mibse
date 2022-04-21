package com.bse.negocio.edepor;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import com.bse.accesodatos.edepor.CoberturaRc;
import com.bse.accesodatos.edepor.CotizacionEDepor;
import com.bse.accesodatos.edepor.PolizaEDepor;
import com.bse.accesodatos.edepor.TipoBuque;
import com.bse.negocio.comun.BSEException;

public interface IEDepor {

	public List<TipoBuque> consultaTiposBuques(EntityManager em) throws Exception, BSEException;
	
	public List<CoberturaRc> consultaCoberturasRc(EntityManager em) throws Exception, BSEException;

		
	public CotizacionEDepor cotizarEDeporAnonimo(EntityManager em, String planCobertura, 
			String tipoBuque, Date fechaDesde, Date fechaHasta, double capital, int planPago) throws Exception, BSEException;
	
	
	public PolizaEDepor emitirEDepor(EntityManager em, String tipoDocumento, String documento, String matriculaBuque, String nombreBuque, long nroCotizacion, 
											String consumoFinal) throws Exception, BSEException;

	public TipoBuque consultaTipoBuque(EntityManager em, String tipo) throws Exception, BSEException;

	
}
