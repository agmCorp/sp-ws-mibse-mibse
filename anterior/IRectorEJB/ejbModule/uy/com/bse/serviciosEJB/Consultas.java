package uy.com.bse.serviciosEJB;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import uy.com.bse.LogicaRector;
import uy.com.bse.consultas.operaciones.ParamActualizarPolizaFlotante;
import uy.com.bse.consultas.operaciones.ParamObtenerCabezalNomina;
import uy.com.bse.consultas.operaciones.ParamObtenerCoberturasIntegranteNomina;
import uy.com.bse.consultas.operaciones.ParamObtenerDetallePolizaFlotante;
import uy.com.bse.consultas.operaciones.ParamObtenerIntegrantesNomina;
import uy.com.bse.consultas.operaciones.ParamObtenerIntegrantesNominaAccidenteTrabajo;
import uy.com.bse.consultas.operaciones.ParamObtenerMovimientosComision;
import uy.com.bse.consultas.operaciones.ParamObtenerNominasVida;
import uy.com.bse.consultas.operaciones.ParamObtenerPolizasFlotantes;
import uy.com.bse.consultas.operaciones.ParamTipoIntegranteNomina;
import uy.com.bse.consultas.operaciones.ResultObtenerCabezalNomina;
import uy.com.bse.consultas.operaciones.ResultObtenerCoberturasIntegranteNomina;
import uy.com.bse.consultas.operaciones.ResultObtenerDetallePolizaFlotante;
import uy.com.bse.consultas.operaciones.ResultObtenerIntegrantesNomina;
import uy.com.bse.consultas.operaciones.ResultObtenerIntegrantesNominaAccidenteTrabajo;
import uy.com.bse.consultas.operaciones.ResultObtenerMovimientosComision;
import uy.com.bse.consultas.operaciones.ResultObtenerNominasVida;
import uy.com.bse.consultas.operaciones.ResultObtenerPolizasFlotantes;
import uy.com.bse.utilitario.dato.ResultCodiguera;
import uy.com.bse.utilitario.dato.ResultGenerico;

@Stateless
public class Consultas implements ConsultasLocal {
	private static Logger log = LogManager.getLogger(Consultas.class);

	public Consultas() {
		super();
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultObtenerCabezalNomina obtenerCabezalNomina(ParamObtenerCabezalNomina param) {
		log.debug("obtenerCabezalNomina start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerCabezalNomina end");
		return (ResultObtenerCabezalNomina) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultObtenerCoberturasIntegranteNomina obtenerCoberturasIntegranteNomina(ParamObtenerCoberturasIntegranteNomina param) {
		log.debug("obtenerCoberturasIntegranteNomina start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerCoberturasIntegranteNomina end");
		return (ResultObtenerCoberturasIntegranteNomina) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultCodiguera listaTipoIntegranteNomina(ParamTipoIntegranteNomina param) {
		log.debug("listaTipoIntegranteNomina start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("listaTipoIntegranteNomina end");
		return (ResultCodiguera) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultObtenerIntegrantesNomina obtenerIntegrantesNomina(ParamObtenerIntegrantesNomina param) {
		log.debug("obtenerIntegrantesNomina start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerIntegrantesNomina end");
		return (ResultObtenerIntegrantesNomina) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultObtenerNominasVida obtenerNominasVida(ParamObtenerNominasVida param) {
		log.debug("obtenerNominasVida start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerNominasVida end");
		return (ResultObtenerNominasVida) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultObtenerPolizasFlotantes obtenerPolizasFlotantes(ParamObtenerPolizasFlotantes param) {
		log.debug("obtenerPolizasFlotantes start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerPolizasFlotantes end");
		return (ResultObtenerPolizasFlotantes) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultObtenerDetallePolizaFlotante obtenerDetallePolizaFlotante(ParamObtenerDetallePolizaFlotante param) {
		log.debug("obtenerDetallePolizaFlotante start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerDetallePolizaFlotante end");
		return (ResultObtenerDetallePolizaFlotante) result;
	}

	@Override
	@Interceptors(TransactionInterceptorRector.class)
	public ResultGenerico actualizarPolizaFlotante(ParamActualizarPolizaFlotante param) {
		log.debug("actualizarPolizaFlotante start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("actualizarPolizaFlotante end");
		return (ResultGenerico) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultObtenerMovimientosComision obtenerMovimientosComision(ParamObtenerMovimientosComision param) {
		log.debug("obtenerMovimientosComision start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerMovimientosComision end");
		return (ResultObtenerMovimientosComision) result;
	}

	@Override
	public ResultObtenerIntegrantesNominaAccidenteTrabajo obtenerIntegrantesNominaAccidenteTrabajo(ParamObtenerIntegrantesNominaAccidenteTrabajo param) {
		log.debug("obtenerIntegrantesNominaAccidenteTrabajo start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerIntegrantesNominaAccidenteTrabajo end");
		return (ResultObtenerIntegrantesNominaAccidenteTrabajo) result;
	}

}
