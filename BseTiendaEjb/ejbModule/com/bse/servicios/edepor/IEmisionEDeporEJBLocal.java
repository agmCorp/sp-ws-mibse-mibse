package com.bse.servicios.edepor;


import java.util.Date;
import java.util.List;

import com.bse.servicios.seguridad.dt.DTSesion;
import com.bse.accesodatos.edepor.CoberturaRc;
import com.bse.accesodatos.edepor.CotizacionEDepor;
import com.bse.accesodatos.edepor.TipoBuque;
import com.bse.accesodatos.edepor.PolizaEDepor;
import com.bse.negocio.comun.BSEException;
import javax.ejb.Local;

@Local
public interface IEmisionEDeporEJBLocal {

	public List<TipoBuque> consultaTiposBuques(DTSesion dtSesion) throws Exception, BSEException;

	public List<CoberturaRc> consultaCoberturasRc(DTSesion dtSesion) throws Exception, BSEException;

	public CotizacionEDepor cotizarEDeporAnonimo(DTSesion dtSesion, String planCobertura, String tipoBuque,
	        Date fechaDesde, Date fechaHasta, double capital, int planPago) throws Exception, BSEException;

	public PolizaEDepor emitirEDepor(DTSesion dtSesion, String tipoDocumento, String documento, String matriculaBuque, String nombreBuque, long nroCotizacion, String consumoFinal) throws Exception, BSEException;

}
