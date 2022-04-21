package com.bse.servicios.esoa;


import java.util.List;

import com.bse.servicios.seguridad.dt.DTSesion;
import com.bse.accesodatos.esoa.PlanPago;
import com.bse.accesodatos.esoa.CategoriaVehiculo;
import com.bse.accesodatos.esoa.CotizacionSoa;
import com.bse.accesodatos.esoa.MarcaVehiculo;
import com.bse.accesodatos.esoa.PlanCobertura;
import com.bse.accesodatos.esoa.PolizaSoa;
import com.bse.negocio.comun.BSEException;
import javax.ejb.Local;
import javax.persistence.EntityManager;

@Local
public interface IEmisionSoaEJBLocal {

	public MarcaVehiculo consultaMarcaVehiculo(DTSesion dtSesion, String marca) throws Exception, BSEException;

	public List<MarcaVehiculo> consultaMarcasVehiculos(DTSesion dtSesion) throws Exception, BSEException;
	
	public List<CategoriaVehiculo> consultaCategoriasVehiculos(DTSesion dtSesion) throws Exception, BSEException;
	
	public CotizacionSoa cotizarSoaAnonimo(DTSesion dtSesion, String planCobertura, String marcaVehiculo, String anioVehiculo, String categoriaVehiculo, int planPago) throws Exception, BSEException;

	public PolizaSoa emitirSoa(DTSesion dtSesion, String tipoDocumento, String documento, String matriculaVehiculo, String padronVehiculo,
			String motorVehiculo, String chasisVehiculo, long nroCotizacion, String consumoFinal) throws Exception, BSEException;

	public List<PlanCobertura> consultaPlanesCobertura(DTSesion dtSesion, int ramo, String producto) throws Exception, BSEException;

	public List<PlanPago> consultaPlanesPago(DTSesion dtSesion) throws Exception, BSEException;

	public PlanCobertura consultaPlanCobertura(DTSesion dtSesion, String plan) throws Exception, BSEException;

	public PlanPago consultaPlanPago(DTSesion dtSesion, int plan) throws Exception, BSEException;

	public CategoriaVehiculo consultaCategoriaVehiculo(DTSesion dtSesion, String categoria) throws Exception, BSEException;

	public void alertarPagoRedes(DTSesion dtSesion, int sucursal, int ramo, String producto, long nroCotizacion) throws Exception, BSEException;

}
