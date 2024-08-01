package uy.com.bse.serviciosEJB;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import uy.com.bse.LogicaRector;
import uy.com.bse.auditoria.ParamAuditarConsulta;
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
import uy.com.bse.reportes.ParamObtenerRutaDescargaUsuario;
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
import uy.com.bse.reportes.ResultObtenerRutaDescargaUsuario;
import uy.com.bse.reportes.ResultObtenerSeguroAutomovil;
import uy.com.bse.reportes.ResultObtenerTotalesResumenEmision;
import uy.com.bse.utilitario.dato.ResultCodiguera;
import uy.com.bse.utilitario.dato.ResultGenerico;

@Stateless
public class ConsultaReportes implements ConsultaReportesLocal {
	private static Logger log = LogManager.getLogger(ConsultaReportes.class);

	@EJB
	private AuditoriaLocal auditoria;

	public ConsultaReportes() {
		super();
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultObtenerFacturasDelProductorProximasAVencerEn12Dias obtenerFacturasDelProductorProximasAVencerEn12Dias(ParamObtenerFacturasDelProductorProximasAVencerEn12Dias param) {
		log.debug("obtenerFacturasDelProductorProximasAVencerEn12Dias start: " + param);
		long inicio = System.currentTimeMillis();
		ResultGenerico result = LogicaRector.solve(param);
		long fin = System.currentTimeMillis();
		auditoria.auditarConsulta(buildParam(param.getUsuario(), param.getClave(), getDetalle(result, "obtenerFacturasDelProductorProximasAVencerEn12Dias", fin - inicio)));
		log.debug("obtenerFacturasDelProductorProximasAVencerEn12Dias end");
		return (ResultObtenerFacturasDelProductorProximasAVencerEn12Dias) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultObtenerFacturasDelProductorProximasAVencer obtenerFacturasDelProductorProximasAVencer(ParamObtenerFacturasDelProductorProximasAVencer param) {
		log.debug("obtenerFacturasDelProductorProximasAVencer start: " + param);
		long inicio = System.currentTimeMillis();
		ResultGenerico result = LogicaRector.solve(param);
		long fin = System.currentTimeMillis();
		auditoria.auditarConsulta(buildParam(param.getUsuario(), param.getClave(), getDetalle(result, "obtenerFacturasDelProductorProximasAVencer", fin - inicio)));
		log.debug("obtenerFacturasDelProductorProximasAVencer end");
		return (ResultObtenerFacturasDelProductorProximasAVencer) result;
	}

	@Override
	public ResultObtenerPolizasEnCondicionesEstarRehabilitadasParaCorredores obtenerPolizasEnCondicionesEstarRehabilitadasParaCorredores(
			ParamObtenerPolizasEnCondicionesEstarRehabilitadasParaCorredores param) {
		log.debug("obtenerPolizasEnCondicionesEstarRehabilitadasParaCorredores start: " + param);
		long inicio = System.currentTimeMillis();
		ResultGenerico result = LogicaRector.solve(param);
		long fin = System.currentTimeMillis();
		auditoria.auditarConsulta(buildParam(param.getUsuario(), param.getClave(), getDetalle(result, "obtenerPolizasEnCondicionesEstarRehabilitadasParaCorredores", fin - inicio)));
		log.debug("obtenerPolizasEnCondicionesEstarRehabilitadasParaCorredores end");
		return (ResultObtenerPolizasEnCondicionesEstarRehabilitadasParaCorredores) result;
	}

	@Override
	public ResultObtenerListadoControlADTBPSParaProductor obtenerListadoControlADTBPSParaProductor(ParamObtenerListadoControlADTBPSParaProductor param) {
		log.debug("obtenerListadoControlADTBPSParaProductor start: " + param);
		long inicio = System.currentTimeMillis();
		ResultGenerico result = LogicaRector.solve(param);
		long fin = System.currentTimeMillis();
		auditoria.auditarConsulta(buildParam(param.getUsuario(), param.getClave(), getDetalle(result, "obtenerListadoControlADTBPSParaProductor", fin - inicio)));
		log.debug("obtenerListadoControlADTBPSParaProductor end");
		return (ResultObtenerListadoControlADTBPSParaProductor) result;
	}

	@Override
	public ResultObtenerIncentivoPlusTotales obtenerIncentivoPlusTotales(ParamObtenerIncentivoPlusTotales param) {
		log.debug("obtenerIncentivoPlusTotales start: " + param);
		long inicio = System.currentTimeMillis();
		ResultGenerico result = LogicaRector.solve(param);
		long fin = System.currentTimeMillis();
		auditoria.auditarConsulta(buildParam(param.getUsuario(), param.getClave(), getDetalle(result, "obtenerIncentivoPlusTotales", fin - inicio)));
		log.debug("obtenerIncentivoPlusTotales end");
		return (ResultObtenerIncentivoPlusTotales) result;
	}

	@Override
	public ResultObtenerPolizasSinMatriculasXProductores obtenerPolizasSinMatriculasXProductores(ParamObtenerPolizasSinMatriculasXProductores param) {
		log.debug("obtenerPolizasSinMatriculasXProductores start: " + param);
		long inicio = System.currentTimeMillis();
		ResultGenerico result = LogicaRector.solve(param);
		long fin = System.currentTimeMillis();
		auditoria.auditarConsulta(buildParam(param.getUsuario(), param.getClave(), getDetalle(result, "obtenerPolizasSinMatriculasXProductores", fin - inicio)));
		log.debug("obtenerPolizasSinMatriculasXProductores end");
		return (ResultObtenerPolizasSinMatriculasXProductores) result;
	}

	@Override
	public ResultObtenerClientesCorredores obtenerClientesCorredores(ParamObtenerClientesCorredores param) {
		log.debug("obtenerClientesCorredores start: " + param);
		long inicio = System.currentTimeMillis();
		ResultGenerico result = LogicaRector.solve(param);
		long fin = System.currentTimeMillis();
		auditoria.auditarConsulta(buildParam(param.getUsuario(), param.getClave(), getDetalle(result, "obtenerClientesCorredores", fin - inicio)));
		log.debug("obtenerClientesCorredores end");
		return (ResultObtenerClientesCorredores) result;
	}

	@Override
	public ResultObtenerIncentivoPlusDetalle obtenerIncentivoPlusDetalle(ParamObtenerIncentivoPlusDetalle param) {
		log.debug("obtenerIncentivoPlusDetalle start: " + param);
		long inicio = System.currentTimeMillis();
		ResultGenerico result = LogicaRector.solve(param);
		long fin = System.currentTimeMillis();
		auditoria.auditarConsulta(buildParam(param.getUsuario(), param.getClave(), getDetalle(result, "obtenerIncentivoPlusDetalle", fin - inicio)));
		log.debug("obtenerIncentivoPlusDetalle end");
		return (ResultObtenerIncentivoPlusDetalle) result;
	}
	
	@Override
	public ResultObtenerNuevaTarifaIncendioProductor obtenerNuevaTarifaIncendioProductor(ParamObtenerNuevaTarifaIncendioProductor param) {
		log.debug("obtenerNuevaTarifaIncendioProductor start: " + param);
		long inicio = System.currentTimeMillis();
		ResultGenerico result = LogicaRector.solve(param);
		long fin = System.currentTimeMillis();
		auditoria.auditarConsulta(buildParam(param.getUsuario(), param.getClave(), getDetalle(result, "obtenerNuevaTarifaIncendioProductor", fin - inicio)));
		log.debug("obtenerNuevaTarifaIncendioProductor end");
		return (ResultObtenerNuevaTarifaIncendioProductor) result;
	}

	@Override
	public ResultObtenerPolizasAdheridasDebitoAutomatico obtenerPolizasAdheridasDebitoAutomatico(ParamObtenerPolizasAdheridasDebitoAutomatico param) {
		log.debug("obtenerPolizasAdheridasDebitoAutomatico start: " + param);
		long inicio = System.currentTimeMillis();
		ResultGenerico result = LogicaRector.solve(param);
		long fin = System.currentTimeMillis();
		auditoria.auditarConsulta(buildParam(param.getUsuario(), param.getClave(), getDetalle(result, "obtenerPolizasAdheridasDebitoAutomatico", fin - inicio)));
		log.debug("obtenerPolizasAdheridasDebitoAutomatico end");
		return (ResultObtenerPolizasAdheridasDebitoAutomatico) result;
	}

	@Override
	public ResultObtenerLogsUsuariosProductor obtenerLogsUsuariosProductor(ParamObtenerLogsUsuariosProductor param) {
		log.debug("obtenerLogsUsuariosProductor start: " + param);
		long inicio = System.currentTimeMillis();
		ResultGenerico result = LogicaRector.solve(param);
		long fin = System.currentTimeMillis();
		auditoria.auditarConsulta(buildParam(param.getUsuario(), param.getClave(), getDetalle(result, "obtenerLogsUsuariosProductor", fin - inicio)));
		log.debug("obtenerLogsUsuariosProductor end");
		return (ResultObtenerLogsUsuariosProductor) result;
	}
	
	@Override
	public ResultObtenerSeguroAutomovil obtenerSeguroAutomovil(ParamObtenerSeguroAutomovil param) {
		log.debug("obtenerSeguroAutomovil start: " + param);
		long inicio = System.currentTimeMillis();
		ResultGenerico result = LogicaRector.solve(param);
		long fin = System.currentTimeMillis();
		auditoria.auditarConsulta(buildParam(param.getUsuario(), param.getClave(), getDetalle(result, "obtenerSeguroAutomovil", fin - inicio)));
		log.debug("obtenerSeguroAutomovil end");
		return (ResultObtenerSeguroAutomovil) result;
	}

	@Override
	public ResultObtenerNombreFicheros obtenerNombreFicheros(ParamObtenerNombreFicheros param) {
		log.debug("obtenerNombreFicheros start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerNombreFicheros end");
		return (ResultObtenerNombreFicheros) result;
	}

	@Override
	public ResultObtenerRutaDescargaUsuario obtenerRutaDescargaUsuario(ParamObtenerRutaDescargaUsuario param) {
		log.debug("obtenerRutaDescargaUsuario start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerRutaDescargaUsuario end");
		return (ResultObtenerRutaDescargaUsuario) result;
	}

	@Override
	public ResultObtenerResumenEmision obtenerResumenEmision(ParamObtenerResumenEmision param) {
		log.debug("obtenerResumenEmision start: " + param);
		long inicio = System.currentTimeMillis();
		ResultGenerico result = LogicaRector.solve(param);
		long fin = System.currentTimeMillis();
		auditoria.auditarConsulta(buildParam(param.getUsuario(), param.getClave(), getDetalle(result, "obtenerResumenEmision", fin - inicio)));
		log.debug("obtenerResumenEmision end");
		return (ResultObtenerResumenEmision) result;
		
		
		
		
		
		
	}

	@Override
	public ResultObtenerTotalesResumenEmision obtenerTotalesResumenEmision(ParamObtenerTotalesResumenEmision param) {
		log.debug("obtenerTotalesResumenEmision start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerTotalesResumenEmision end");
		return (ResultObtenerTotalesResumenEmision) result;
	}

	@Override
	public ResultCodiguera obtenerListaReportesResumenEmision(ParamListaReportesResumenEmision param) {
		log.debug("obtenerListaReportesResumenEmision start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerListaReportesResumenEmision end");
		return (ResultCodiguera) result;
	}

	@Override
	public ResultObtenerConsultasExportables obtenerConsultasExportables(ParamObtenerConsultasExportables param){
		log.debug("obtenerConsultasExportables start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerConsultasExportables end");
		return (ResultObtenerConsultasExportables) result; 
		 
	}
	 
	 
	private ParamAuditarConsulta buildParam(String usuario, String clave, String detalle) {
		ParamAuditarConsulta param = new ParamAuditarConsulta(usuario, clave, "CONS_EXPORTABLE", detalle);
		return param;
	}

	private String getDetalle(ResultGenerico result, String method, long tiempoMilisegundo) {
		StringBuffer detalleAuditoria = new StringBuffer();
		detalleAuditoria.append(result.getHayError() ? "ERROR:" : "OK:");
		detalleAuditoria.append(" ");
		detalleAuditoria.append(method);
		detalleAuditoria.append(" ");
		detalleAuditoria.append(tiempoMilisegundo);
		detalleAuditoria.append(" milisegundos ");
		detalleAuditoria.append(result.getHayError() ? result.getError().getDescripcion() : "");
		return detalleAuditoria.toString();
	}

	

	

}
