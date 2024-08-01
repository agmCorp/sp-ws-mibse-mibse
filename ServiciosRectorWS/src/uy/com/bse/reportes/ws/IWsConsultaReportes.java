package uy.com.bse.reportes.ws;

import javax.jws.WebMethod;
import javax.jws.WebService;

import uy.com.bse.reportes.ParamListaReportesResumenEmision;
import uy.com.bse.reportes.ParamObtenerClientesCorredores;
import uy.com.bse.reportes.ParamObtenerConsultasExportables;
import uy.com.bse.reportes.ParamObtenerFacturasDelProductorProximasAVencer;
import uy.com.bse.reportes.ParamObtenerFacturasDelProductorProximasAVencerEn12Dias;
import uy.com.bse.reportes.ParamObtenerIncentivoPlusDetalle;
import uy.com.bse.reportes.ParamObtenerIncentivoPlusTotales;
import uy.com.bse.reportes.ParamObtenerListadoControlADTBPSParaProductor;
import uy.com.bse.reportes.ParamObtenerLogsUsuariosProductor;
import uy.com.bse.reportes.ParamObtenerNombreFicheros;
import uy.com.bse.reportes.ParamObtenerNuevaTarifaIncendioProductor;
import uy.com.bse.reportes.ParamObtenerPolizasAdheridasDebitoAutomatico;
import uy.com.bse.reportes.ParamObtenerPolizasEnCondicionesEstarRehabilitadasParaCorredores;
import uy.com.bse.reportes.ParamObtenerPolizasSinMatriculasXProductores;
import uy.com.bse.reportes.ParamObtenerResumenEmision;
import uy.com.bse.reportes.ParamObtenerSeguroAutomovil;
import uy.com.bse.reportes.ParamObtenerTotalesResumenEmision;
import uy.com.bse.reportes.ResultObtenerClientesCorredores;
import uy.com.bse.reportes.ResultObtenerConsultasExportables;
import uy.com.bse.reportes.ResultObtenerFacturasDelProductorProximasAVencer;
import uy.com.bse.reportes.ResultObtenerFacturasDelProductorProximasAVencerEn12Dias;
import uy.com.bse.reportes.ResultObtenerIncentivoPlusDetalle;
import uy.com.bse.reportes.ResultObtenerIncentivoPlusTotales;
import uy.com.bse.reportes.ResultObtenerListadoControlADTBPSParaProductor;
import uy.com.bse.reportes.ResultObtenerLogsUsuariosProductor;
import uy.com.bse.reportes.ResultObtenerNombreFicheros;
import uy.com.bse.reportes.ResultObtenerNuevaTarifaIncendioProductor;
import uy.com.bse.reportes.ResultObtenerPolizasAdheridasDebitoAutomatico;
import uy.com.bse.reportes.ResultObtenerPolizasEnCondicionesEstarRehabilitadasParaCorredores;
import uy.com.bse.reportes.ResultObtenerPolizasSinMatriculasXProductores;
import uy.com.bse.reportes.ResultObtenerResumenEmision;
import uy.com.bse.reportes.ResultObtenerSeguroAutomovil;
import uy.com.bse.reportes.ResultObtenerTotalesResumenEmision;
import uy.com.bse.utilitario.dato.ResultCodiguera;
@WebService
public interface IWsConsultaReportes {

	@WebMethod
	public ResultObtenerFacturasDelProductorProximasAVencerEn12Dias obtenerFacturasDelProductorProximasAVencerEn12Dias(final ParamObtenerFacturasDelProductorProximasAVencerEn12Dias param);

	@WebMethod
	public ResultObtenerFacturasDelProductorProximasAVencer obtenerFacturasDelProductorProximasAVencer(final ParamObtenerFacturasDelProductorProximasAVencer param);

	@WebMethod
	public ResultObtenerPolizasEnCondicionesEstarRehabilitadasParaCorredores obtenerPolizasEnCondicionesEstarRehabilitadasParaCorredores(
			final ParamObtenerPolizasEnCondicionesEstarRehabilitadasParaCorredores param);

	@WebMethod
	public ResultObtenerListadoControlADTBPSParaProductor obtenerListadoControlADTBPSParaProductor(final ParamObtenerListadoControlADTBPSParaProductor param);

	@WebMethod
	public ResultObtenerIncentivoPlusTotales obtenerIncentivoPlusTotales(final ParamObtenerIncentivoPlusTotales param);

	@WebMethod
	public ResultObtenerPolizasSinMatriculasXProductores obtenerPolizasSinMatriculasXProductores(final ParamObtenerPolizasSinMatriculasXProductores param);

	@WebMethod
	public ResultObtenerClientesCorredores obtenerClientesCorredores(final ParamObtenerClientesCorredores param);

	@WebMethod
	public ResultObtenerIncentivoPlusDetalle obtenerIncentivoPlusDetalle(final ParamObtenerIncentivoPlusDetalle param);

	@WebMethod
	public ResultObtenerPolizasAdheridasDebitoAutomatico obtenerPolizasAdheridasDebitoAutomatico(final ParamObtenerPolizasAdheridasDebitoAutomatico param);

	@WebMethod
	public ResultObtenerLogsUsuariosProductor obtenerLogsUsuariosProductor(final ParamObtenerLogsUsuariosProductor param);
	
	@WebMethod
	public ResultObtenerNombreFicheros obtenerNombreFicheros(final ParamObtenerNombreFicheros param);
	
	@WebMethod
	public  ResultObtenerResumenEmision obtenerResumenEmision(final ParamObtenerResumenEmision param);
	
	@WebMethod
	public ResultObtenerTotalesResumenEmision obtenerTotalesResumenEmision(final ParamObtenerTotalesResumenEmision param);
	 
	@WebMethod
	 public ResultCodiguera obtenerListaReportesResumenEmision(final ParamListaReportesResumenEmision param);
	
	@WebMethod
	public ResultObtenerSeguroAutomovil obtenerSeguroAutomovil(final ParamObtenerSeguroAutomovil param);
	
	@WebMethod
	public ResultObtenerNuevaTarifaIncendioProductor obtenerNuevaTarifaIncendioProductor(ParamObtenerNuevaTarifaIncendioProductor param);
	
	@WebMethod
	public ResultObtenerConsultasExportables obtenerConsultasExportables(ParamObtenerConsultasExportables param);
}