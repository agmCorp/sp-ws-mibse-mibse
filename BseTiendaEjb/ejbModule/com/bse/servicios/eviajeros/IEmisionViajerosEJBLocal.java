package com.bse.servicios.eviajeros;


import java.util.ArrayList;
import java.util.Date;

import com.bse.servicios.seguridad.dt.DTSesion;
import com.bse.accesodatos.eviajeros.CoberturaMuerte;
import com.bse.accesodatos.eviajeros.CoberturaPrexistentes;
import com.bse.accesodatos.eviajeros.CotizacionViajeros;
import com.bse.accesodatos.eviajeros.PolizaViajeros;
import com.bse.accesodatos.eviajeros.Viajero;
import com.bse.negocio.comun.BSEException;
import javax.ejb.Local;

@Local
public interface IEmisionViajerosEJBLocal {

	public CotizacionViajeros cotizarViajerosAnonimo(DTSesion dtSesion, String planCobertura, int planPago, Date fechaDesde, 
			Date fechaHasta, ArrayList<Viajero> listaPersonas, String extension, Date fechaSalidaPais) throws Exception, BSEException;
	
	public PolizaViajeros emitirViajeros(DTSesion dtSesion, String tipoDocumentoContratante, String documentoContratante, 
	        ArrayList<Viajero> listaPersonas, long nroCotizacion, String consumoFinal, String extension, Date fechaSalidaPais) throws Exception, BSEException;

	public ArrayList<CoberturaPrexistentes> consultaCoberturasPrexistentes(DTSesion dtSesion) throws Exception, BSEException;

	public ArrayList<CoberturaMuerte> consultaCoberturasMuerte(DTSesion dtSesion) throws Exception, BSEException;
}
