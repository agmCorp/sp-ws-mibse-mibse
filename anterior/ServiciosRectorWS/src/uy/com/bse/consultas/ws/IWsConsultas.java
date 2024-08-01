package uy.com.bse.consultas.ws;

import javax.jws.WebMethod;
import javax.jws.WebService;

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

@WebService
public interface IWsConsultas {

	@WebMethod
	public ResultObtenerCabezalNomina obtenerCabezalNomina(final ParamObtenerCabezalNomina param);

	@WebMethod
	public ResultObtenerCoberturasIntegranteNomina obtenerCoberturasIntegranteNomina(final ParamObtenerCoberturasIntegranteNomina param);

	@WebMethod
	public ResultCodiguera listaTipoIntegranteNomina(final ParamTipoIntegranteNomina param);

	@WebMethod
	public ResultObtenerIntegrantesNomina obtenerIntegrantesNomina(final ParamObtenerIntegrantesNomina param);

	@WebMethod
	public ResultObtenerNominasVida obtenerNominasVida(final ParamObtenerNominasVida param);

	@WebMethod
	public ResultObtenerPolizasFlotantes obtenerPolizasFlotantes(final ParamObtenerPolizasFlotantes param);

	@WebMethod
	public ResultObtenerDetallePolizaFlotante obtenerDetallePolizaFlotante(final ParamObtenerDetallePolizaFlotante param);
	
	@WebMethod
	public ResultGenerico actualizarPolizaFlotante(final ParamActualizarPolizaFlotante param);
	
	@WebMethod
	public ResultObtenerMovimientosComision obtenerMovimientosComision(ParamObtenerMovimientosComision param);
	
	@WebMethod
	public ResultObtenerIntegrantesNominaAccidenteTrabajo obtenerIntegrantesNominaAccidenteTrabajo(ParamObtenerIntegrantesNominaAccidenteTrabajo param);

}