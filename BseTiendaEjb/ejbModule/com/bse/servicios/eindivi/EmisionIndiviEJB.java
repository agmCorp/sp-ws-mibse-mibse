package com.bse.servicios.eindivi;


import java.util.List;

import com.bse.accesodatos.eindivi.AreaCirculacion;
import com.bse.accesodatos.eindivi.CotizacionIndivi;
import com.bse.accesodatos.eindivi.Modalidad;
import com.bse.accesodatos.eindivi.ModeloVehiculo;
import com.bse.accesodatos.eindivi.PolizaIndivi;
import com.bse.accesodatos.eindivi.TipoCombustible;
import com.bse.accesodatos.eindivi.TipoContratante;
import com.bse.accesodatos.eindivi.TipoVehiculo;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.jws.WebParam;

import com.bse.servicios.seguridad.dt.DTSesion;
import com.bse.negocio.FabricaNegocio;
import com.bse.negocio.comun.BSEException;
import com.bse.negocio.eindivi.IEIndivi;
import com.bse.servicios.seguridad.Seguridadbseonlinews;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless(mappedName = "EmisionSoaEJB")
@Interceptors({Seguridadbseonlinews.class})
public class EmisionIndiviEJB implements IEmisionIndiviEJBLocal {

    @PersistenceContext(unitName = "bsetiendaws-pu")
    private EntityManager em;

//	@Override
//	public List<MarcaVehiculo> consultaMarcasVehiculos(DTSesion dtSesion) throws Exception, BSEException {
//		IESoa eSoaManager = FabricaNegocio.getFabricaNegocio().getESoaMgr();
//		return eSoaManager.consultaMarcasVehiculos(em);
//	}
//
//	@Override
//	public MarcaVehiculo consultaMarcaVehiculo(DTSesion dtSesion, String marca) throws Exception, BSEException {
//		IESoa eSoaManager = FabricaNegocio.getFabricaNegocio().getESoaMgr();
//		return eSoaManager.consultaMarcaVehiculo(em, marca);
//	}
//
//	@Override
//	public CategoriaVehiculo consultaCategoriaVehiculo(DTSesion dtSesion, String categoria) throws Exception, BSEException {
//		IESoa eSoaManager = FabricaNegocio.getFabricaNegocio().getESoaMgr();
//		return eSoaManager.consultaCategoriaVehiculo(em, categoria);
//	}
//
//	@Override
//	public List<CategoriaVehiculo> consultaCategoriasVehiculos(DTSesion dtSesion)
//			throws Exception, BSEException {
//		IESoa eSoaManager = FabricaNegocio.getFabricaNegocio().getESoaMgr();
//		return eSoaManager.consultaCategoriasVehiculos(em);
//	}
 
	@Override
	public CotizacionIndivi cotizarIndiviAnonimo(DTSesion dtSesion, String planCobertura, String marcaVehiculo, String modeloVehiculo, String anioVehiculo, String tipoVehiculo,
	        String combustible, String modalidad, String areaCirculacion, int planPago) throws Exception, BSEException {
		IEIndivi eIndiviManager = FabricaNegocio.getFabricaNegocio().getEIndiviMgr();
		return eIndiviManager.cotizarIndiviAnonimo(em, planCobertura, marcaVehiculo, modeloVehiculo, anioVehiculo, tipoVehiculo, combustible, 
				modalidad, areaCirculacion, planPago);
	}

	@Override
	public PolizaIndivi emitirIndivi(DTSesion dtSesion, String tipoDocumento, String documento, String matriculaVehiculo, String padronVehiculo,
			String motorVehiculo, String chasisVehiculo, String calidadContratante, long nroCotizacion, String consumoFinal) throws Exception, BSEException {
		IEIndivi eIndiviManager = FabricaNegocio.getFabricaNegocio().getEIndiviMgr();
		return eIndiviManager.emitirIndivi(em, tipoDocumento, documento, matriculaVehiculo, padronVehiculo,
				motorVehiculo, chasisVehiculo, calidadContratante, nroCotizacion, consumoFinal);
	}

	@Override
	public List<TipoCombustible> consultaTiposCombustible(DTSesion dtSesion) throws Exception, BSEException {
		IEIndivi eIndiviManager = FabricaNegocio.getFabricaNegocio().getEIndiviMgr();
		return eIndiviManager.consultaTiposCombustible(em);
	}

	@Override
	public List<TipoVehiculo> consultaTiposVehiculos(DTSesion dtSesion) throws Exception, BSEException {
		IEIndivi eIndiviManager = FabricaNegocio.getFabricaNegocio().getEIndiviMgr();
		return eIndiviManager.consultaTiposVehiculos(em);
	}

	@Override
	public List<TipoContratante> consultaTiposContratante(DTSesion dtSesion) throws Exception, BSEException {
		IEIndivi eIndiviManager = FabricaNegocio.getFabricaNegocio().getEIndiviMgr();
		return eIndiviManager.consultaTiposContratante(em);
	}

	@Override
	public List<Modalidad> consultaModalidades(DTSesion dtSesion) throws Exception, BSEException {
		IEIndivi eIndiviManager = FabricaNegocio.getFabricaNegocio().getEIndiviMgr();
		return eIndiviManager.consultaModalidades(em);
	}

	@Override
	public List<AreaCirculacion> consultaAreasCirculacion(DTSesion dtSesion) throws Exception, BSEException {
		IEIndivi eIndiviManager = FabricaNegocio.getFabricaNegocio().getEIndiviMgr();
		return eIndiviManager.consultaAreasCirculacion(em);
	}

	@Override
	public List<ModeloVehiculo> consultaModelosVehiculos(DTSesion dtSesion, String marcaVehiculo) throws Exception, BSEException {
		IEIndivi eIndiviManager = FabricaNegocio.getFabricaNegocio().getEIndiviMgr();
		return eIndiviManager.consultaModelosVehiculos(em, marcaVehiculo);
	}


}
