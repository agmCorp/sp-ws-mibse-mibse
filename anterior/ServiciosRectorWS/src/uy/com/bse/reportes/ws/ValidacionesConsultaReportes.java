package uy.com.bse.reportes.ws;

import javax.naming.InitialContext;
import javax.naming.NamingException;

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
import uy.com.bse.serviciosEJB.SeguridadServiciosRemote;
import uy.com.bse.utilitario.dato.ServiciosError;
import uy.com.bse.utilitario.excepcion.Values;
import uy.com.bse.utilitario.seguridad.ResultValidar;
import uy.com.bse.utilitario.servicios.ValidacionesAbstract;
import uy.com.bse.utilitario.validacion.ValidadorMesAnio;
import uy.ibm.responseTimeLogger.RTimeLogger;

public class ValidacionesConsultaReportes extends ValidacionesAbstract {

	public static SeguridadServiciosRemote getEJB() throws NamingException {
		final InitialContext ctx = new InitialContext();
		return (SeguridadServiciosRemote) ctx.lookup("java:global/SeguridadServicios/SeguridadEJB/SeguridadServicios!uy.com.bse.serviciosEJB.SeguridadServiciosRemote");

	}

	protected boolean validarLogin(String usuario, String clave) {
		RTimeLogger.addCustomData("user", usuario);
		boolean salida = false;
		uy.com.bse.utilitario.seguridad.ParamValidar paramValidar = new uy.com.bse.utilitario.seguridad.ParamValidar();
		paramValidar.setClave(clave);
		paramValidar.setUsuario(usuario);
		uy.com.bse.utilitario.seguridad.ResultValidar result;
		try {
			result = (ResultValidar) getEJB().validar(paramValidar);
			salida = ((result != null) && (!result.getHayError()));
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return salida;
	}
	
	public ServiciosError validarObtenerFacturasDelProductorProximasAVencerEn12Dias(ParamObtenerFacturasDelProductorProximasAVencerEn12Dias param) {
		String claveDeError = null;
		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		}
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerFacturasDelProductorProximasAVencer(ParamObtenerFacturasDelProductorProximasAVencer param) {
		String claveDeError = null;
		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		}
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerPolizasEnCondicionesEstarRehabilitadasParaCorredores(ParamObtenerPolizasEnCondicionesEstarRehabilitadasParaCorredores param) {
		String claveDeError = null;
		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		}
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerListadoControlADTBPSParaProductor(ParamObtenerListadoControlADTBPSParaProductor param) {
		String claveDeError = null;
		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		}else if(param.getFecha()==null|| param.getFecha().equals("")){
			claveDeError = Values.VALNULL;
		}
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerIncentivoPlusTotales(ParamObtenerIncentivoPlusTotales param) {
		String claveDeError = null;
		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		}else if(param.getMesDesde()==null|| param.getMesDesde().equals("")){
			claveDeError = Values.VALNULL;
		}else if(param.getMesHasta()==null|| param.getMesHasta().equals("")){
			claveDeError = Values.VALNULL;
		}else if(new ValidadorMesAnio().validate(param.getMesDesde())!=null){
			claveDeError=Values.VALMESANNO;
		}else if(new ValidadorMesAnio().validate(param.getMesHasta())!=null){
			claveDeError=Values.VALMESANNO;
		}
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerPolizasSinMatriculasXProductores(ParamObtenerPolizasSinMatriculasXProductores param) {
		String claveDeError = null;
		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		}
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerClientesCorredores(ParamObtenerClientesCorredores param) {
		String claveDeError = null;
		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		}
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerIncentivoPlusDetalle(ParamObtenerIncentivoPlusDetalle param) {
		String claveDeError = null;
		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		}else if(param.getMesAnio()==null|| param.getMesAnio().equals("")){
			claveDeError = Values.VALNULL;
		}else if(new ValidadorMesAnio().validate(param.getMesAnio())!=null){
			claveDeError = Values.VALMESANNO;
		}
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerPolizasAdheridasDebitoAutomatico(ParamObtenerPolizasAdheridasDebitoAutomatico param) {
		String claveDeError = null;
		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		}
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerLogsUsuariosProductor(ParamObtenerLogsUsuariosProductor param) {
		String claveDeError = null;
		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		}else if(param.getFechaDesde()==null|| param.getFechaDesde().equals("")){
			claveDeError = Values.VALNULL;
		}else if(param.getFechaHasta()==null|| param.getFechaHasta().equals("")){
			claveDeError = Values.VALNULL;
		}
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerNombreFicheros(ParamObtenerNombreFicheros param) {
		String claveDeError = null;
		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		}
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerResumenEmision(ParamObtenerResumenEmision param) {
		String claveDeError = null;
		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		}else if(new ValidadorMesAnio().validate(param.getMesAnio())!=null){
			claveDeError = Values.VALMESANNO;
		}
		else if(param.getReporte()==null|| param.getReporte().equals("")){
			claveDeError = Values.VALNULL;
		}
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerTotalesResumenEmision(ParamObtenerTotalesResumenEmision param) {
		String claveDeError = null;
		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		}else if(new ValidadorMesAnio().validate(param.getMesAnio())!=null){
			claveDeError = Values.VALMESANNO;
		}
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerListaReportesResumenEmision(ParamListaReportesResumenEmision param) {
		String claveDeError = null;
		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		}
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerSeguroAutomovil(ParamObtenerSeguroAutomovil param) {
		String claveDeError = null;
		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		}else if(param.getFechaDesde()==null|| param.getFechaDesde().equals("")){
			claveDeError = Values.VALNULL;
		}else if(param.getFechaHasta()==null|| param.getFechaHasta().equals("")){
			claveDeError = Values.VALNULL;
		}
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerNuevaTarifaIncendioProductor(ParamObtenerNuevaTarifaIncendioProductor param) {
		String claveDeError = null;
		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		}else if((param.getMesAnio())==null){
			claveDeError = Values.VALMESANNO;
		}
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerConsultasExportables(ParamObtenerConsultasExportables param) {
		String claveDeError = null;
		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		}
		return getErrorsByClave(claveDeError);
	}

}
