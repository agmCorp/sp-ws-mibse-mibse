package uy.com.bse.serviciosEJB;

import javax.ejb.Local;

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

@Local
public interface ConsultasLocal {

	public ResultObtenerCabezalNomina obtenerCabezalNomina(final ParamObtenerCabezalNomina param);

	public ResultObtenerCoberturasIntegranteNomina obtenerCoberturasIntegranteNomina(final ParamObtenerCoberturasIntegranteNomina param);

	public ResultCodiguera listaTipoIntegranteNomina(final ParamTipoIntegranteNomina param);

	public ResultObtenerIntegrantesNomina obtenerIntegrantesNomina(final ParamObtenerIntegrantesNomina param);

	public ResultObtenerNominasVida obtenerNominasVida(final ParamObtenerNominasVida param);

	public ResultObtenerPolizasFlotantes obtenerPolizasFlotantes(final ParamObtenerPolizasFlotantes param);

	public ResultObtenerDetallePolizaFlotante obtenerDetallePolizaFlotante(final ParamObtenerDetallePolizaFlotante param);

	public ResultGenerico actualizarPolizaFlotante(final ParamActualizarPolizaFlotante param);

	public ResultObtenerMovimientosComision obtenerMovimientosComision(ParamObtenerMovimientosComision param);
	
	public ResultObtenerIntegrantesNominaAccidenteTrabajo obtenerIntegrantesNominaAccidenteTrabajo(final ParamObtenerIntegrantesNominaAccidenteTrabajo param);

}
