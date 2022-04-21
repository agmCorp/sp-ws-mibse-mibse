package com.bse.negocio.esoa;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.EntityManager;

import com.bse.accesodatos.esoa.CategoriaVehiculo;
import com.bse.accesodatos.esoa.CotizacionSoa;
import com.bse.accesodatos.esoa.MarcaVehiculo;
import com.bse.accesodatos.esoa.Moneda;
import com.bse.accesodatos.esoa.PlanCobertura;
import com.bse.accesodatos.esoa.PlanPago;
import com.bse.accesodatos.esoa.PolizaSoa;
import com.bse.negocio.comun.BSEException;

public interface IESoa {

	public List<MarcaVehiculo> consultaMarcasVehiculos(EntityManager em) throws Exception, BSEException;
	
	public List<CategoriaVehiculo> consultaCategoriasVehiculos(EntityManager em) throws Exception, BSEException;
	
	public CotizacionSoa cotizarSoaAnonimo(EntityManager em, String planCobertura, String marcaVehiculo, String anioVehiculo, String categoriaVehiculo, int planPago) throws Exception, BSEException;
	
	public PolizaSoa emitirSoa(EntityManager em, String tipoDocumento, String documento, String matriculaVehiculo, String padronVehiculo,
											String motorVehiculo, String chasisVehiculo, long nroCotizacion, 
											String consumoFinal) throws Exception, BSEException;

	public List<PlanCobertura> consultaPlanesCobertura(EntityManager em, int ramo, String producto) throws Exception, BSEException;

	public List<PlanPago> consultaPlanesPago(EntityManager em) throws Exception, BSEException;

	public PlanCobertura consultaPlanCobertura(EntityManager em, String plan) throws Exception, BSEException;

	public PlanCobertura consultaPlanCoberturaRamo(EntityManager em, int ramo, String plan) throws Exception, BSEException;

	public PlanCobertura consultaPlanCoberturaRamoProducto(EntityManager em, int ramo, String producto, String plan) throws Exception, BSEException;

	public PlanPago consultaPlanPago(EntityManager em, int plan) throws Exception, BSEException;
	
	public MarcaVehiculo consultaMarcaVehiculo(EntityManager em, String marca) throws Exception, BSEException;

	public CategoriaVehiculo consultaCategoriaVehiculo(EntityManager em, String categoria) throws Exception, BSEException;

	public Moneda consultaMoneda(EntityManager em, String codigoMoneda) throws Exception, BSEException;

	public void alertarPagoRedes(EntityManager em, int sucursal, int ramo, String producto, long nroCotizacion) throws Exception, BSEException;
	
}
