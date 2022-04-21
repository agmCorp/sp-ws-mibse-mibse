package com.bse.servicios.ebicis;


import java.util.Date;
import java.util.List;

import com.bse.servicios.seguridad.dt.DTSesion;
import com.bse.accesodatos.ebici.CotizacionBici;
import com.bse.accesodatos.ebici.PolizaBici;
import com.bse.accesodatos.ebici.TipoBici;
import com.bse.accesodatos.esoa.PolizaSoa;
import com.bse.negocio.comun.BSEException;
import javax.ejb.Local;
import javax.persistence.EntityManager;

@Local
public interface IEmisionBiciEJBLocal {

	public CotizacionBici cotizarBiciAnonimo(DTSesion dtSesion, String planCobertura, Double valorBicicleta, Integer vigenciaSeguro) throws Exception, BSEException;

	public String clienteConDeuda(DTSesion dtSesion, String tipoDocumento, String nroDocumento, Integer nroCotizacion, Integer nroCertificado) throws Exception, BSEException;

	public PolizaBici emitirBici(DTSesion dtSesion, String planPago, String tipoDocumento,
			String documento, Date fechaFactura, String tipoBicicleta,
			Date fechaNacimientoCliente, String marca, String serie,
			String modelo, long nroCotizacion, String consumoFinal) throws Exception, BSEException;

	public List<TipoBici> consultaTiposBicicletas(DTSesion dtSesion) throws Exception, BSEException;
}
