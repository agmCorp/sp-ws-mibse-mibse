package com.bse.servicios.eindivi;


import java.util.List;

import com.bse.servicios.seguridad.dt.DTSesion;
import com.bse.accesodatos.eindivi.AreaCirculacion;
import com.bse.accesodatos.eindivi.CotizacionIndivi;
import com.bse.accesodatos.eindivi.Modalidad;
import com.bse.accesodatos.eindivi.ModeloVehiculo;
import com.bse.accesodatos.eindivi.PolizaIndivi;
import com.bse.accesodatos.eindivi.TipoCombustible;
import com.bse.accesodatos.eindivi.TipoContratante;
import com.bse.accesodatos.eindivi.TipoVehiculo;
import com.bse.negocio.comun.BSEException;
import javax.ejb.Local;
import javax.jws.WebParam;
import javax.persistence.EntityManager;

@Local
public interface IEmisionIndiviEJBLocal {

	public CotizacionIndivi cotizarIndiviAnonimo(DTSesion dtSesion, String planCobertura, String marcaVehiculo, String modeloVehiculo, String anioVehiculo, 
	        String tipoVehiculo, String combustible, String modalidad, String areaCirculacion, int planPago) throws Exception, BSEException;

	public PolizaIndivi emitirIndivi(DTSesion dtSesion, String tipoDocumento, String documento, String matriculaVehiculo, String padronVehiculo,
			String motorVehiculo, String chasisVehiculo, String calidadContratante, long nroCotizacion, String consumoFinal) throws Exception, BSEException;

	public List<TipoCombustible> consultaTiposCombustible(DTSesion dtSesion) throws Exception, BSEException;

	public List<TipoVehiculo> consultaTiposVehiculos(DTSesion dtSesion) throws Exception, BSEException;
	public List<TipoContratante> consultaTiposContratante(DTSesion dtSesion) throws Exception, BSEException;

	public List<Modalidad> consultaModalidades(DTSesion dtSesion) throws Exception, BSEException;

	public List<AreaCirculacion> consultaAreasCirculacion(DTSesion dtSesion) throws Exception, BSEException;
	
	public List<ModeloVehiculo> consultaModelosVehiculos(DTSesion dtSesion, String marcaVehiculo) throws Exception, BSEException;

}
