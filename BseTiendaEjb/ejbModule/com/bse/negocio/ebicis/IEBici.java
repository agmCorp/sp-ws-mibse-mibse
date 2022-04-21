package com.bse.negocio.ebicis;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import com.bse.accesodatos.ebici.CotizacionBici;
import com.bse.accesodatos.ebici.PolizaBici;
import com.bse.accesodatos.ebici.TipoBici;
import com.bse.accesodatos.esoa.Moneda;
import com.bse.accesodatos.esoa.PlanPago;
import com.bse.negocio.comun.BSEException;

public interface IEBici {

	public CotizacionBici cotizarBiciAnonimo(EntityManager em, String planCobertura, Double valorBicicleta, Integer vigenciaSeguro) throws Exception, BSEException;
	
	public List<PlanPago> consultaPlanesPago(EntityManager em) throws Exception, BSEException;

	public PlanPago consultaPlanPago(EntityManager em, int plan) throws Exception, BSEException;
	
	public Moneda consultaMoneda(EntityManager em, String codigoMoneda) throws Exception, BSEException;

	public String clienteConDeuda(EntityManager em, String tipoDocumento, String nroDocumento, Integer nroCotizacion, Integer nroCertificado) throws Exception, BSEException;

	public PolizaBici emitirBici(EntityManager em, String planPago, String tipoDocumento,
			String documento, Date fechaFactura, String tipoBicicleta,
			Date fechaNacimientoCliente, String marca, String serie,
			String modelo, long nroCotizacion, String consumoFinal) throws Exception, BSEException;

	public List<TipoBici> consultaTiposBicicletas(EntityManager em) throws Exception, BSEException;
	
}
