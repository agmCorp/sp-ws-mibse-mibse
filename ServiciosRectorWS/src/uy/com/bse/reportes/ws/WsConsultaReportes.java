package uy.com.bse.reportes.ws;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.jws.WebService;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
import uy.com.bse.serviciosEJB.ConsultaReportesLocal;
import uy.com.bse.utilitario.dato.ResultCodiguera;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.dato.ServiciosError;
import uy.com.bse.utilitario.excepcion.ErrorResolver;
import uy.com.bse.utilitario.excepcion.Values;
import uy.com.bse.utilitario.log.Logueo;
import uy.com.bse.utilitario.properties.EARPropertiesManager;
import uy.com.bse.utilitario.util.MailBuilder;

@WebService(endpointInterface = "uy.com.bse.reportes.ws.IWsConsultaReportes", serviceName = "WsConsultaReportes")
public class WsConsultaReportes implements IWsConsultaReportes {

	private static Logger LOG = LogManager.getLogger(WsConsultaReportes.class);

	public ResultObtenerFacturasDelProductorProximasAVencerEn12Dias obtenerFacturasDelProductorProximasAVencerEn12Dias(ParamObtenerFacturasDelProductorProximasAVencerEn12Dias param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsConsultaReportes.class);
		logueo.setMetodo("obtenerFacturasDelProductorProximasAVencerEn12Dias");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		ResultObtenerFacturasDelProductorProximasAVencerEn12Dias datos = new ResultObtenerFacturasDelProductorProximasAVencerEn12Dias();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesConsultaReportes validar = new ValidacionesConsultaReportes();
			ServiciosError error = validar.validarObtenerFacturasDelProductorProximasAVencerEn12Dias(param);

			if (error == null) {
				datos = WsConsultaReportes.getEJBManager().obtenerFacturasDelProductorProximasAVencerEn12Dias(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}

		} catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}
		return datos;
	}

	public ResultObtenerFacturasDelProductorProximasAVencer obtenerFacturasDelProductorProximasAVencer(ParamObtenerFacturasDelProductorProximasAVencer param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsConsultaReportes.class);
		logueo.setMetodo("obtenerFacturasDelProductorProximasAVencer");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		ResultObtenerFacturasDelProductorProximasAVencer datos = new ResultObtenerFacturasDelProductorProximasAVencer();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesConsultaReportes validar = new ValidacionesConsultaReportes();
			ServiciosError error = validar.validarObtenerFacturasDelProductorProximasAVencer(param);

			if (error == null) {
				datos = WsConsultaReportes.getEJBManager().obtenerFacturasDelProductorProximasAVencer(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}

		} catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}
		return datos;
	}

	public ResultObtenerPolizasEnCondicionesEstarRehabilitadasParaCorredores obtenerPolizasEnCondicionesEstarRehabilitadasParaCorredores(
			ParamObtenerPolizasEnCondicionesEstarRehabilitadasParaCorredores param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsConsultaReportes.class);
		logueo.setMetodo("obtenerPolizasEnCondicionesEstarRehabilitadasParaCorredores");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		ResultObtenerPolizasEnCondicionesEstarRehabilitadasParaCorredores datos = new ResultObtenerPolizasEnCondicionesEstarRehabilitadasParaCorredores();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesConsultaReportes validar = new ValidacionesConsultaReportes();
			ServiciosError error = validar.validarObtenerPolizasEnCondicionesEstarRehabilitadasParaCorredores(param);

			if (error == null) {
				datos = WsConsultaReportes.getEJBManager().obtenerPolizasEnCondicionesEstarRehabilitadasParaCorredores(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}

		} catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}
		return datos;
	}

	public ResultObtenerListadoControlADTBPSParaProductor obtenerListadoControlADTBPSParaProductor(ParamObtenerListadoControlADTBPSParaProductor param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsConsultaReportes.class);
		logueo.setMetodo("obtenerListadoControlADTBPSParaProductor");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		ResultObtenerListadoControlADTBPSParaProductor datos = new ResultObtenerListadoControlADTBPSParaProductor();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesConsultaReportes validar = new ValidacionesConsultaReportes();
			ServiciosError error = validar.validarObtenerListadoControlADTBPSParaProductor(param);

			if (error == null) {
				datos = WsConsultaReportes.getEJBManager().obtenerListadoControlADTBPSParaProductor(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}

		} catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}
		return datos;
	}

	public ResultObtenerIncentivoPlusTotales obtenerIncentivoPlusTotales(ParamObtenerIncentivoPlusTotales param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsConsultaReportes.class);
		logueo.setMetodo("obtenerIncentivoPlusTotales");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		ResultObtenerIncentivoPlusTotales datos = new ResultObtenerIncentivoPlusTotales();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesConsultaReportes validar = new ValidacionesConsultaReportes();
			ServiciosError error = validar.validarObtenerIncentivoPlusTotales(param);

			if (error == null) {
				datos = WsConsultaReportes.getEJBManager().obtenerIncentivoPlusTotales(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}

		} catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}
		return datos;
	}

	public ResultObtenerPolizasSinMatriculasXProductores obtenerPolizasSinMatriculasXProductores(ParamObtenerPolizasSinMatriculasXProductores param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsConsultaReportes.class);
		logueo.setMetodo("obtenerPolizasSinMatriculasXProductores");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		ResultObtenerPolizasSinMatriculasXProductores datos = new ResultObtenerPolizasSinMatriculasXProductores();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesConsultaReportes validar = new ValidacionesConsultaReportes();
			ServiciosError error = validar.validarObtenerPolizasSinMatriculasXProductores(param);

			if (error == null) {
				datos = WsConsultaReportes.getEJBManager().obtenerPolizasSinMatriculasXProductores(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}

		} catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}
		return datos;
	}

	public ResultObtenerClientesCorredores obtenerClientesCorredores(ParamObtenerClientesCorredores param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsConsultaReportes.class);
		logueo.setMetodo("obtenerClientesCorredores");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		ResultObtenerClientesCorredores datos = new ResultObtenerClientesCorredores();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesConsultaReportes validar = new ValidacionesConsultaReportes();
			ServiciosError error = validar.validarObtenerClientesCorredores(param);

			if (error == null) {
				datos = WsConsultaReportes.getEJBManager().obtenerClientesCorredores(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}

		} catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}
		return datos;
	}

	public ResultObtenerIncentivoPlusDetalle obtenerIncentivoPlusDetalle(ParamObtenerIncentivoPlusDetalle param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsConsultaReportes.class);
		logueo.setMetodo("obtenerIncentivoPlusDetalle");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		ResultObtenerIncentivoPlusDetalle datos = new ResultObtenerIncentivoPlusDetalle();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesConsultaReportes validar = new ValidacionesConsultaReportes();
			ServiciosError error = validar.validarObtenerIncentivoPlusDetalle(param);

			if (error == null) {
				datos = WsConsultaReportes.getEJBManager().obtenerIncentivoPlusDetalle(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}

		} catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}
		return datos;
	}

	public ResultObtenerPolizasAdheridasDebitoAutomatico obtenerPolizasAdheridasDebitoAutomatico(ParamObtenerPolizasAdheridasDebitoAutomatico param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsConsultaReportes.class);
		logueo.setMetodo("obtenerPolizasAdheridasDebitoAutomatico");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		ResultObtenerPolizasAdheridasDebitoAutomatico datos = new ResultObtenerPolizasAdheridasDebitoAutomatico();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesConsultaReportes validar = new ValidacionesConsultaReportes();
			ServiciosError error = validar.validarObtenerPolizasAdheridasDebitoAutomatico(param);

			if (error == null) {
				datos = WsConsultaReportes.getEJBManager().obtenerPolizasAdheridasDebitoAutomatico(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}

		} catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}
		return datos;
	}
	
	public ResultObtenerNuevaTarifaIncendioProductor obtenerNuevaTarifaIncendioProductor(ParamObtenerNuevaTarifaIncendioProductor param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsConsultaReportes.class);
		logueo.setMetodo("obtenerNuevaTarifaIncendioProductor");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		ResultObtenerNuevaTarifaIncendioProductor datos = new ResultObtenerNuevaTarifaIncendioProductor();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesConsultaReportes validar = new ValidacionesConsultaReportes();
			ServiciosError error = validar.validarObtenerNuevaTarifaIncendioProductor(param);

			if (error == null) {
				datos = WsConsultaReportes.getEJBManager().obtenerNuevaTarifaIncendioProductor(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}

		} catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}
		return datos;
	}
	
	

	public ResultObtenerLogsUsuariosProductor obtenerLogsUsuariosProductor(ParamObtenerLogsUsuariosProductor param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsConsultaReportes.class);
		logueo.setMetodo("obtenerLogsUsuariosProductor");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		ResultObtenerLogsUsuariosProductor datos = new ResultObtenerLogsUsuariosProductor();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesConsultaReportes validar = new ValidacionesConsultaReportes();
			ServiciosError error = validar.validarObtenerLogsUsuariosProductor(param);

			if (error == null) {
				datos = WsConsultaReportes.getEJBManager().obtenerLogsUsuariosProductor(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}

		} catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}
		return datos;
	}

	public ResultObtenerNombreFicheros obtenerNombreFicheros(ParamObtenerNombreFicheros param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsConsultaReportes.class);
		logueo.setMetodo("obtenerNombreFicheros");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		ResultObtenerNombreFicheros datos = new ResultObtenerNombreFicheros();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesConsultaReportes validar = new ValidacionesConsultaReportes();
			ServiciosError error = validar.validarObtenerNombreFicheros(param);
			
			if (error == null) {
				datos = WsConsultaReportes.getEJBManager().obtenerNombreFicheros(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}

		} catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}
		return datos;
	}
	
	
	public ResultObtenerResumenEmision obtenerResumenEmision(ParamObtenerResumenEmision param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsConsultaReportes.class);
		logueo.setMetodo("obtenerResumenEmision");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("MesAnio", param.getMesAnio());
		logueo.setParametro("Codigo ramo", param.getCodRamo());
		logueo.setParametro("Codigo producto", param.getCodProducto());
		logueo.setParametro("Reporte", param.getReporte());

		ResultObtenerResumenEmision datos = new ResultObtenerResumenEmision();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesConsultaReportes validar = new ValidacionesConsultaReportes();
			ServiciosError error = validar.validarObtenerResumenEmision(param);
			
			if (error == null) {
				datos = WsConsultaReportes.getEJBManager().obtenerResumenEmision(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}

		} catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}
		return datos;
	}
	
	
	public ResultObtenerTotalesResumenEmision obtenerTotalesResumenEmision(ParamObtenerTotalesResumenEmision param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsConsultaReportes.class);
		logueo.setMetodo("obtenerTotalesResumenEmision");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("MesAnio", param.getMesAnio());
		logueo.setParametro("Codigo ramo", param.getCodRamo());
		logueo.setParametro("Codigo producto", param.getCodProducto());
		

		ResultObtenerTotalesResumenEmision datos = new ResultObtenerTotalesResumenEmision();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesConsultaReportes validar = new ValidacionesConsultaReportes();
			ServiciosError error = validar.validarObtenerTotalesResumenEmision(param);
			
			if (error == null) {
				datos = WsConsultaReportes.getEJBManager().obtenerTotalesResumenEmision(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}

		} catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}
		return datos;
	}

	public ResultCodiguera obtenerListaReportesResumenEmision(ParamListaReportesResumenEmision param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsConsultaReportes.class);
		logueo.setMetodo("obtenerListaReportesResumenEmision");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
	

		ResultCodiguera datos = new ResultCodiguera();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesConsultaReportes validar = new ValidacionesConsultaReportes();
			ServiciosError error = validar.validarObtenerListaReportesResumenEmision(param);
			
			if (error == null) {
				datos = WsConsultaReportes.getEJBManager().obtenerListaReportesResumenEmision(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}

		} catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}
		return datos;
	}
	
	public ResultObtenerSeguroAutomovil obtenerSeguroAutomovil(ParamObtenerSeguroAutomovil param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsConsultaReportes.class);
		logueo.setMetodo("obtenerSeguroAutomovil");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
	

		ResultObtenerSeguroAutomovil datos = new ResultObtenerSeguroAutomovil();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesConsultaReportes validar = new ValidacionesConsultaReportes();
			ServiciosError error = validar.validarObtenerSeguroAutomovil(param);
			
			if (error == null) {
				datos = WsConsultaReportes.getEJBManager().obtenerSeguroAutomovil(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}

		} catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}
		return datos;
	}
	

	public ResultObtenerConsultasExportables obtenerConsultasExportables(ParamObtenerConsultasExportables param){
		
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsConsultaReportes.class);
		logueo.setMetodo("obtenerConsultasExportables");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
	

		ResultObtenerConsultasExportables datos = new ResultObtenerConsultasExportables();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesConsultaReportes validar = new ValidacionesConsultaReportes();
			ServiciosError error = validar.validarObtenerConsultasExportables(param);
			
			if (error == null) {
				datos = WsConsultaReportes.getEJBManager().obtenerConsultasExportables(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}

		} catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}
		return datos;
		
	}

	private String catchException(Logueo logueo, Exception e) {
		String claveError = Values.CLWEBSERVEXCP;
		logueo.setException(Values.EXCEPTION);
		logueo.setError(e.getMessage());
		LOG.error(logueo.getMensaje(),e);
		enviarMail( logueo);
		return claveError;
	}

	private void finallyBlock(String claveError, ResultGenerico datos) {
		if (claveError != null) {
			datos.setHayError(Boolean.TRUE);
			datos.setError(ErrorResolver.getError(claveError));
		}
	}

	public static ConsultaReportesLocal getEJBManager() throws NamingException {
		final InitialContext ctx = new InitialContext();
		return (ConsultaReportesLocal) ctx.lookup("global/ServiciosRector/IRectorEJB/ConsultaReportes!uy.com.bse.serviciosEJB.ConsultaReportesLocal");
	}

	
	private void enviarMail(Logueo logueo) {
		try {
			EARPropertiesManager manager= new EARPropertiesManager("configServiciosRector.properties");
			MailBuilder.create().sentToDefaultConfigureMail(manager.obtenerValor("serviciosRector.mail.soporte"),manager.obtenerValor("serviciosRector.ccmail.soporte"),logueo.getMensaje(), "SERVICIO CONSULTAS REPORTES Error  BSE Ambiente: " + InetAddress.getLocalHost().getHostName());
		} catch (UnknownHostException e) {
			LOG.error("Error en m\u00e9todo enviarMail", e);
		}
	}

	

}
