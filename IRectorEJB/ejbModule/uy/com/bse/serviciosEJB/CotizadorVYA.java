package uy.com.bse.serviciosEJB;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import uy.com.bse.LogicaRector;
import uy.com.bse.cotizadorvya.operaciones.ParamCalcularPlanes;
import uy.com.bse.cotizadorvya.operaciones.ParamMontosIniciales;
import uy.com.bse.cotizadorvya.operaciones.ParamObtenerCoberturas;
import uy.com.bse.cotizadorvya.operaciones.ParamPorcentajeAnticipo;
import uy.com.bse.cotizadorvya.operaciones.ParamRentaMaxima;
import uy.com.bse.cotizadorvya.operaciones.ResultCalcularPlanes;
import uy.com.bse.cotizadorvya.operaciones.ResultMontosIniciales;
import uy.com.bse.cotizadorvya.operaciones.ResultObtenerCoberturas;
import uy.com.bse.cotizadorvya.operaciones.ResultPorcentajeAnticipo;
import uy.com.bse.cotizadorvya.operaciones.ResultRentaMaxima;
import uy.com.bse.utilitario.dato.ResultGenerico;

@Stateless
public class CotizadorVYA implements CotizadorVYALocal {
	private static Logger log = LogManager.getLogger(Usuarios.class);

	public CotizadorVYA() {
		super();
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultCalcularPlanes calcularPlanes(ParamCalcularPlanes param) {
		log.debug("calcularPlanes start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("calcularPlanes end: " + param);
		return (ResultCalcularPlanes) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultObtenerCoberturas obtenerCoberturas(ParamObtenerCoberturas param) {
		log.debug("obtenerCoberturas start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerCoberturas end: " + param);
		return (ResultObtenerCoberturas) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultMontosIniciales montosIniciales(ParamMontosIniciales param) {
		log.debug("montosIniciales start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("montosIniciales end: " + param);
		return (ResultMontosIniciales) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultRentaMaxima obtenerRentaMaxima(ParamRentaMaxima param) {
		log.debug("obtenerRentaMaxima start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerRentaMaxima end: " + param);
		return (ResultRentaMaxima) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultPorcentajeAnticipo obtenerPorcentajeAnticipo(ParamPorcentajeAnticipo param) {
		log.debug("obtenerPorcentajeAnticipo start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerPorcentajeAnticipo end: " + param);
		return (ResultPorcentajeAnticipo) result;
	}
}
