package com.bse.negocio.eindivi;

import java.util.List;

import javax.jws.WebParam;
import javax.persistence.EntityManager;

import com.bse.accesodatos.eindivi.AreaCirculacion;
import com.bse.accesodatos.eindivi.CotizacionIndivi;
import com.bse.accesodatos.eindivi.Modalidad;
import com.bse.accesodatos.eindivi.ModeloVehiculo;
import com.bse.accesodatos.eindivi.PolizaIndivi;
import com.bse.accesodatos.eindivi.TipoCombustible;
import com.bse.accesodatos.eindivi.TipoContratante;
import com.bse.accesodatos.eindivi.TipoVehiculo;
import com.bse.negocio.comun.BSEException;

public interface IEIndivi {

	public CotizacionIndivi cotizarIndiviAnonimo(EntityManager em, String planCobertura, String marcaVehiculo, String modeloVehiculo, String anioVehiculo, 
			String tipoVehiculo, String combustible, String modalidad, String areaCirculacion, int planPago) throws Exception, BSEException;
	
	public PolizaIndivi emitirIndivi(EntityManager em, String tipoDocumento, String documento, String matriculaVehiculo, String padronVehiculo,
											String motorVehiculo, String chasisVehiculo, String calidadContratante, 
											long nroCotizacion, String consumoFinal) throws Exception, BSEException;

	public List<TipoCombustible> consultaTiposCombustible(EntityManager em) throws Exception, BSEException;

	public List<TipoVehiculo> consultaTiposVehiculos(EntityManager em) throws Exception, BSEException;

	public List<TipoContratante> consultaTiposContratante(EntityManager em) throws Exception, BSEException;

	public List<Modalidad> consultaModalidades(EntityManager em) throws Exception, BSEException;

	public List<AreaCirculacion> consultaAreasCirculacion(EntityManager em) throws Exception, BSEException;

	public List<ModeloVehiculo> consultaModelosVehiculos(EntityManager em, String marcaVehiculo) throws Exception, BSEException;

}
