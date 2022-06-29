package com.bse.servicios.esoa;


import java.util.List;

import com.bse.accesodatos.esoa.CategoriaVehiculo;
import com.bse.accesodatos.esoa.CotizacionSoa;
import com.bse.accesodatos.esoa.MarcaVehiculo;
import com.bse.accesodatos.esoa.PlanCobertura;
import com.bse.accesodatos.esoa.PlanPago;
import com.bse.accesodatos.esoa.PolizaSoa;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import com.bse.servicios.seguridad.dt.DTSesion;
import com.bse.negocio.FabricaNegocio;
import com.bse.negocio.comun.BSEException;
import com.bse.negocio.esoa.IESoa;
import com.bse.servicios.seguridad.Seguridadbseonlinews;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless(mappedName = "EmisionSoaEJB")
@Interceptors({Seguridadbseonlinews.class})
public class EmisionSoaEJB implements IEmisionSoaEJBLocal {

    @PersistenceContext(unitName = "bsetiendaws-pu")
    private EntityManager em;

	@Override
	public List<MarcaVehiculo> consultaMarcasVehiculos(DTSesion dtSesion) throws Exception, BSEException {
		IESoa eSoaManager = FabricaNegocio.getFabricaNegocio().getESoaMgr();
		return eSoaManager.consultaMarcasVehiculos(em);
	}

	@Override
	public MarcaVehiculo consultaMarcaVehiculo(DTSesion dtSesion, String marca) throws Exception, BSEException {
		IESoa eSoaManager = FabricaNegocio.getFabricaNegocio().getESoaMgr();
		return eSoaManager.consultaMarcaVehiculo(em, marca);
	}

	@Override
	public CategoriaVehiculo consultaCategoriaVehiculo(DTSesion dtSesion, String categoria) throws Exception, BSEException {
		IESoa eSoaManager = FabricaNegocio.getFabricaNegocio().getESoaMgr();
		return eSoaManager.consultaCategoriaVehiculo(em, categoria);
	}

	@Override
	public List<CategoriaVehiculo> consultaCategoriasVehiculos(DTSesion dtSesion)
			throws Exception, BSEException {
		IESoa eSoaManager = FabricaNegocio.getFabricaNegocio().getESoaMgr();
		return eSoaManager.consultaCategoriasVehiculos(em);
	}

	@Override
	public CotizacionSoa cotizarSoaAnonimo(DTSesion dtSesion, String planCobertura, String marcaVehiculo, String anioVehiculo, String categoriaVehiculo, int planPago) throws Exception, BSEException {
		IESoa eSoaManager = FabricaNegocio.getFabricaNegocio().getESoaMgr();
		return eSoaManager.cotizarSoaAnonimo(em, planCobertura, marcaVehiculo, anioVehiculo, categoriaVehiculo, planPago);
	}

	@Override
	public PolizaSoa emitirSoa(DTSesion dtSesion, String tipoDocumento, String documento, String matriculaVehiculo, String padronVehiculo,
			String motorVehiculo, String chasisVehiculo, long nroCotizacion, String consumoFinal) throws Exception, BSEException {
		IESoa eSoaManager = FabricaNegocio.getFabricaNegocio().getESoaMgr();
		return eSoaManager.emitirSoa(em, tipoDocumento, documento, matriculaVehiculo, padronVehiculo,
				motorVehiculo, chasisVehiculo, nroCotizacion, consumoFinal);
	}

	@Override
	public PlanCobertura consultaPlanCobertura(DTSesion dtSesion, String plan) throws Exception, BSEException{
		IESoa eSoaManager = FabricaNegocio.getFabricaNegocio().getESoaMgr();
		return eSoaManager.consultaPlanCobertura(em, plan) ;
	}

	@Override
	public List<PlanCobertura> consultaPlanesCobertura(DTSesion dtSesion, int ramo, String producto) throws Exception, BSEException{
		IESoa eSoaManager = FabricaNegocio.getFabricaNegocio().getESoaMgr();
		return eSoaManager.consultaPlanesCobertura(em, ramo, producto) ;
	}

	@Override
	public PlanPago consultaPlanPago(DTSesion dtSesion, int plan) throws Exception, BSEException {
		IESoa eSoaManager = FabricaNegocio.getFabricaNegocio().getESoaMgr();
		return eSoaManager.consultaPlanPago(em, plan);
	}

	@Override
	public List<PlanPago> consultaPlanesPago(DTSesion dtSesion) throws Exception, BSEException {
		IESoa eSoaManager = FabricaNegocio.getFabricaNegocio().getESoaMgr();
    	List<PlanPago> lista = eSoaManager.consultaPlanesPago(em);
		return lista;
	}

	@Override
	public void alertarPagoRedes(DTSesion dtSesion, int sucursal, int ramo, String producto, long nroCotizacion) throws Exception, BSEException {
		IESoa eSoaManager = FabricaNegocio.getFabricaNegocio().getESoaMgr();
    	eSoaManager.alertarPagoRedes(em, sucursal, ramo, producto, nroCotizacion);
	}

}
