package uy.com.bse.maestro.ws;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.jws.WebService;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import uy.com.bse.clientes.consultas.ParamExportarClientes;
import uy.com.bse.clientes.consultas.ParamObtenerClientes;
import uy.com.bse.clientes.consultas.ParamObtenerDetalleConsultaClientes;
import uy.com.bse.clientes.consultas.ParamPolizasAsociadasClientes;
import uy.com.bse.clientes.consultas.ResultExportarClientes;
import uy.com.bse.clientes.consultas.ResultObtenerClientes;
import uy.com.bse.clientes.consultas.ResultObtenerDetalleConsultaClientes;
import uy.com.bse.clientes.consultas.ResultPolizasAsociadasClientes;
import uy.com.bse.maestro.personas.comunicaciones.ParamAltaComunicacion;
import uy.com.bse.maestro.personas.comunicaciones.ParamBajaComunicacion;
import uy.com.bse.maestro.personas.comunicaciones.ParamModificarComunicacion;
import uy.com.bse.maestro.personas.comunicaciones.ResultComunicacion;
import uy.com.bse.maestro.personas.comunicaciones.ResultComunicacionEC;
import uy.com.bse.maestro.personas.consultas.ParamObtenerPersonas;
import uy.com.bse.maestro.personas.consultas.ParamObtenerPersonasClientes;
import uy.com.bse.maestro.personas.consultas.ParamPersona;
import uy.com.bse.maestro.personas.consultas.ParamPolizasAsociadas;
import uy.com.bse.maestro.personas.consultas.ResultComunicaciones;
import uy.com.bse.maestro.personas.consultas.ResultObtenerPersonas;
import uy.com.bse.maestro.personas.consultas.ResultObtenerPersonasClientes;
import uy.com.bse.maestro.personas.consultas.ResultPolizasAsociadas;
import uy.com.bse.maestro.personas.consultas.ResultRoles;
import uy.com.bse.maestro.personas.datosbancarios.ParamBorrarDatoBancario;
import uy.com.bse.maestro.personas.datosbancarios.ParamListaBancosPersona;
import uy.com.bse.maestro.personas.datosbancarios.ParamModificarDatoBancario;
import uy.com.bse.maestro.personas.datosbancarios.ParamObtenerDatosBancariosPersona;
import uy.com.bse.maestro.personas.datosbancarios.ParamTienePermisoDatosBancarios;
import uy.com.bse.maestro.personas.datosbancarios.ResultDatoBancario;
import uy.com.bse.maestro.personas.datosbancarios.ResultListaBancosPersona;
import uy.com.bse.maestro.personas.datosbancarios.ResultObtenerDatosBancariosPersona;
import uy.com.bse.maestro.personas.domicilio.ParamDatosBajaDireccion;
import uy.com.bse.maestro.personas.domicilio.ParamDatosDireccion;
import uy.com.bse.maestro.personas.domicilio.ResultBajaDireccion;
import uy.com.bse.maestro.personas.domicilio.ResultDireccion;
import uy.com.bse.maestro.personas.interfaces.ParamEstadoCivil;
import uy.com.bse.maestro.personas.interfaces.ParamListaSexo;
import uy.com.bse.maestro.personas.interfaces.ParamMotivosBaja;
import uy.com.bse.maestro.personas.interfaces.ParamObtenerPersonasAlta;
import uy.com.bse.maestro.personas.interfaces.ParamPersonaCorredor;
import uy.com.bse.maestro.personas.interfaces.ParamProfesiones;
import uy.com.bse.maestro.personas.interfaces.ParamRadio;
import uy.com.bse.maestro.personas.interfaces.ParamTipoComunicaciones;
import uy.com.bse.maestro.personas.interfaces.ParamTipoDireccion;
import uy.com.bse.maestro.personas.interfaces.ParamTipoPersoneria;
import uy.com.bse.maestro.personas.interfaces.ResultListaEstadoCivil;
import uy.com.bse.maestro.personas.interfaces.ResultListaMotivosBaja;
import uy.com.bse.maestro.personas.interfaces.ResultListaProfesiones;
import uy.com.bse.maestro.personas.interfaces.ResultListaSexo;
import uy.com.bse.maestro.personas.interfaces.ResultListaTipoComunicaciones;
import uy.com.bse.maestro.personas.interfaces.ResultListaTipoDireccion;
import uy.com.bse.maestro.personas.interfaces.ResultListaTipoPersoneria;
import uy.com.bse.maestro.personas.interfaces.ResultObtenerPersonasAlta;
import uy.com.bse.maestro.personas.interfaces.ResultPersonaCorredor;
import uy.com.bse.maestro.personas.interfaces.ResultRadio;
import uy.com.bse.maestro.personas.personas.ParamAltaCliente;
import uy.com.bse.maestro.personas.personas.ParamAltaRolCliente;
import uy.com.bse.maestro.personas.personas.ParamDatosActivarPersona;
import uy.com.bse.maestro.personas.personas.ParamDatosBajaPersona;
import uy.com.bse.maestro.personas.personas.ParamDatosPersonaFisica;
import uy.com.bse.maestro.personas.personas.ParamDatosPersonaJuridica;
import uy.com.bse.maestro.personas.personas.ParamExistePersona;
import uy.com.bse.maestro.personas.personas.ParamObtenerDireccion;
import uy.com.bse.maestro.personas.personas.ResultActivarPersona;
import uy.com.bse.maestro.personas.personas.ResultAltaCliente;
import uy.com.bse.maestro.personas.personas.ResultAltaRolCliente;
import uy.com.bse.maestro.personas.personas.ResultBajaPersona;
import uy.com.bse.maestro.personas.personas.ResultExistePersona;
import uy.com.bse.maestro.personas.personas.ResultObtenerDirecciones;
import uy.com.bse.maestro.personas.personas.ResultPersonaFisica;
import uy.com.bse.maestro.personas.personas.ResultPersonaJuridica;
import uy.com.bse.maestro.personas.rol.ParamDatosRolCliente;
import uy.com.bse.maestro.personas.rol.ResultRolCliente;
import uy.com.bse.serviciosEJB.MaestroPersonasLocal;
import uy.com.bse.serviciosEJB.ServiciosRectorGenericosLocal;
import uy.com.bse.utilitario.dato.ResultCondicion;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.dato.ServiciosError;
import uy.com.bse.utilitario.excepcion.ErrorResolver;
import uy.com.bse.utilitario.excepcion.Values;
import uy.com.bse.utilitario.log.Logueo;
import uy.com.bse.utilitario.properties.EARPropertiesManager;
import uy.com.bse.utilitario.util.MailBuilder;

@WebService(endpointInterface = "uy.com.bse.maestro.ws.IWsMaestroPersonas", serviceName = "WsMaestroPersonas")
public class WsMaestroPersonas implements IWsMaestroPersonas {

	private static Logger LOG = LogManager.getLogger(WsMaestroPersonas.class);
	
	public ResultListaMotivosBaja listaMotivosBaja(ParamMotivosBaja param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsMaestroPersonas.class);
		logueo.setMetodo("listaMotivosBaja");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		ResultListaMotivosBaja datos = new ResultListaMotivosBaja();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesMaestroPersonas validar = new ValidacionesMaestroPersonas();
			ServiciosError error = validar.validarListaMotivosBaja(param);

			if (error == null) {
				datos = WsMaestroPersonas.getEJBManager().listaMotivosBaja(param);
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

	
	
	public ResultPersonaFisica altaPersonaFisica(ParamDatosPersonaFisica param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsMaestroPersonas.class);
		logueo.setMetodo("altaPersonaFisica");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("CodPersona", param.getCodPersona());
		logueo.setParametro("CodigoDireccion", param.getCodDireccion());
		logueo.setParametro("Nombre", param.getNombre());
		logueo.setParametro("Apellido", param.getApellido());
		logueo.setParametro("CodigoEstadoCivil", param.getCodEstadoCivil());
		logueo.setParametro("CodigoProfesion", param.getCodProfesion());
		logueo.setParametro("FechaNacimiento", param.getFechaNacimiento());
		logueo.setParametro("TipoDocumento", param.getTipoDocumento());
		logueo.setParametro("NumDocumento", param.getNumDocumento());
		logueo.setParametro("Sexo", param.getSexo());
		logueo.setParametro("RUT", param.getRut());

		ResultPersonaFisica datos = new ResultPersonaFisica();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesMaestroPersonas validar = new ValidacionesMaestroPersonas();
			ServiciosError error = validar.validarAltaPersonaFisica(param);

			if (error == null) {
				datos = WsMaestroPersonas.getEJBManager().altaPersonaFisica(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}

		}  catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}
		return datos;
	}

	public ResultPersonaJuridica altaPersonaJuridica(ParamDatosPersonaJuridica param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsMaestroPersonas.class);
		logueo.setMetodo("altaPersonaJuridica");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("CodigoDireccion", param.getCodDireccion());
		logueo.setParametro("CodActividad", param.getCodActividad());
		logueo.setParametro("CodOrganismo", param.getCodOrganismo());
		logueo.setParametro("RazonSocial", param.getRazonSocial());
		logueo.setParametro("Ruc", param.getRuc());
		logueo.setParametro("FechaInicio", param.getFechaInicio());

		ResultPersonaJuridica datos = new ResultPersonaJuridica();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesMaestroPersonas validar = new ValidacionesMaestroPersonas();
			ServiciosError error = validar.validarAltaPersonaJuridica(param);

			if (error == null) {
				datos = WsMaestroPersonas.getEJBManager().altaPersonaJuridica(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}

		}  catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}
		return datos;
	}

	public ResultPersonaFisica modificarPersonaFisica(ParamDatosPersonaFisica param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsMaestroPersonas.class);
		logueo.setMetodo("modificarPersonaFisica");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("CodigoDireccion", param.getCodDireccion());
		logueo.setParametro("CodPersona", param.getCodPersona());
		logueo.setParametro("CodigoEstadoCivil", param.getCodEstadoCivil());
		logueo.setParametro("CodigoProfesion", param.getCodProfesion());
		logueo.setParametro("FechaNacimiento", param.getFechaNacimiento());
		logueo.setParametro("Sexo", param.getSexo());

		ResultPersonaFisica datos = new ResultPersonaFisica();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesMaestroPersonas validar = new ValidacionesMaestroPersonas();
			ServiciosError error = validar.validarModificarPersonaFisica(param);

			if (error == null) {
				datos = WsMaestroPersonas.getEJBManager().modificarPersonaFisica(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}

		}  catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}
		return datos;
	}

	public ResultPersonaJuridica modificarPersonaJuridica(ParamDatosPersonaJuridica param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsMaestroPersonas.class);
		logueo.setMetodo("modificarPersonaFisica");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("CodigoDireccion", param.getCodDireccion());
		logueo.setParametro("CodActividad", param.getCodActividad());
		logueo.setParametro("CodOrganismo", param.getCodOrganismo());
		logueo.setParametro("RazonSocial", param.getRazonSocial());
		logueo.setParametro("Ruc", param.getRuc());
		logueo.setParametro("FechaInicio", param.getFechaInicio());

		ResultPersonaJuridica datos = new ResultPersonaJuridica();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesMaestroPersonas validar = new ValidacionesMaestroPersonas();
			ServiciosError error = validar.validarModificarPersonaJuridica(param);

			if (error == null) {
				datos = WsMaestroPersonas.getEJBManager().modificarPersonaJuridica(param);
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

	public ResultBajaPersona bajaPersona(ParamDatosBajaPersona param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsMaestroPersonas.class);
		logueo.setMetodo("bajaPersona");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("CodMotivoBaja", param.getCodMotivoBaja());
		logueo.setParametro("CodPersona", param.getCodPersona());
		logueo.setParametro("FechaBaja", param.getFechaBaja());
		logueo.setParametro("TipoOperacion", param.getTipoOperacion());

		ResultBajaPersona datos = new ResultBajaPersona();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesMaestroPersonas validar = new ValidacionesMaestroPersonas();
			ServiciosError error = validar.validarBajaPersona(param);

			if (error == null) {
				datos = WsMaestroPersonas.getEJBManager().bajaPersona(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}

		}catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}
		return datos;
	}

	public ResultComunicacion altaComunicaciones(ParamAltaComunicacion param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsMaestroPersonas.class);
		logueo.setMetodo("altaComunicaciones");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("CodPersona", param.getCodPersona());

		ResultComunicacion datos = new ResultComunicacion();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesMaestroPersonas validar = new ValidacionesMaestroPersonas();
			ServiciosError error = validar.validarAltaComunicaciones(param);

			if (error == null) {
				datos = WsMaestroPersonas.getEJBManager().altaComunicaciones(param);
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

	public ResultComunicacion modificarComunicaciones(ParamModificarComunicacion param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsMaestroPersonas.class);
		logueo.setMetodo("modificarComunicaciones");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("CodPersona", param.getCodPersona());

		ResultComunicacion datos = new ResultComunicacion();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesMaestroPersonas validar = new ValidacionesMaestroPersonas();
			ServiciosError error = validar.validarModificarComunicaciones(param);

			if (error == null) {
				datos = WsMaestroPersonas.getEJBManager().modificarComunicaciones(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}

		}catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}
		return datos;
	}

	public ResultComunicacionEC bajaComunicaciones(ParamBajaComunicacion param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsMaestroPersonas.class);
		logueo.setMetodo("bajaComunicaciones");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("CodPersona", param.getCodPersona());

		ResultComunicacionEC datos = new ResultComunicacionEC();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesMaestroPersonas validar = new ValidacionesMaestroPersonas();
			ServiciosError error = validar.validarBajaComunicaciones(param);

			if (error == null) {
				datos = WsMaestroPersonas.getEJBManager().bajaComunicaciones(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}

		}catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}
		return datos;
	}

	public ResultRolCliente modificarDatosRolCliente(ParamDatosRolCliente param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsMaestroPersonas.class);
		logueo.setMetodo("modificarDatosRolCliente");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("CodPersona", param.getCodPersona());
		logueo.setParametro("CodSucursalPago", param.getCodSucursalPago());

		ResultRolCliente datos = new ResultRolCliente();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesMaestroPersonas validar = new ValidacionesMaestroPersonas();
			ServiciosError error = validar.validarModificarDatosRolCliente(param);

			if (error == null) {
				datos = WsMaestroPersonas.getEJBManager().modificarDatosRolCliente(param);
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

	public ResultDireccion altaDireccion(ParamDatosDireccion param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsMaestroPersonas.class);
		logueo.setMetodo("altaDireccion");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("CodPersona", param.getCodPersona());
		logueo.setParametro("Aclaraciones", param.getAclaraciones());
		logueo.setParametro("Apto", param.getApto());
		logueo.setParametro("Calle", param.getCalle());
		logueo.setParametro("NumeroPuerta", param.getNumeroPuerta());
		logueo.setParametro("NumPostal", param.getNumPostal());
		logueo.setParametro("Padron", param.getPadron());
		logueo.setParametro("Piso", param.getPiso());
		logueo.setParametro("TipoDireccion", param.getTipoDireccion());
		logueo.setParametro("Unidad", param.getUnidad());

		ResultDireccion datos = new ResultDireccion();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesMaestroPersonas validar = new ValidacionesMaestroPersonas();
			ServiciosError error = validar.validarAltaDireccion(param);

			if (error == null) {
				datos = WsMaestroPersonas.getEJBManager().altaDireccion(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}

		}  catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}
		return datos;
	}

	public ResultDireccion modificarDireccion(ParamDatosDireccion param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsMaestroPersonas.class);
		logueo.setMetodo("modificarDireccion");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("CodPersona", param.getCodPersona());
		logueo.setParametro("Aclaraciones", param.getAclaraciones());
		logueo.setParametro("Apto", param.getApto());
		logueo.setParametro("Calle", param.getCalle());
		logueo.setParametro("NumeroPuerta", param.getNumeroPuerta());
		logueo.setParametro("NumPostal", param.getNumPostal());
		logueo.setParametro("Padron", param.getPadron());
		logueo.setParametro("Piso", param.getPiso());
		logueo.setParametro("TipoDireccion", param.getTipoDireccion());
		logueo.setParametro("Unidad", param.getUnidad());
		logueo.setParametro("CodDireccion", param.getCodDireccion());

		ResultDireccion datos = new ResultDireccion();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesMaestroPersonas validar = new ValidacionesMaestroPersonas();
			ServiciosError error = validar.validarModificarDireccion(param);

			if (error == null) {
				datos = WsMaestroPersonas.getEJBManager().modificarDireccion(param);
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

	public ResultBajaDireccion bajaDireccion(ParamDatosBajaDireccion param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsMaestroPersonas.class);
		logueo.setMetodo("bajaDireccion");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("CodPersona", param.getCodPersona());
		logueo.setParametro("CodDireccion", param.getCodDireccion());

		ResultBajaDireccion datos = new ResultBajaDireccion();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesMaestroPersonas validar = new ValidacionesMaestroPersonas();
			ServiciosError error = validar.validarBajaDireccion(param);

			if (error == null) {
				datos = WsMaestroPersonas.getEJBManager().bajaDireccion(param);
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

	public ResultObtenerPersonas obtenerPersonas(ParamObtenerPersonas param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsMaestroPersonas.class);
		logueo.setMetodo("obtenerPersonas");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("PrimerCriterio", param.getPrimerCriterio());
		logueo.setParametro("PrimerOperador", param.getPrimerOperador());
		logueo.setParametro("PrimerValor", param.getPrimerValor());
		logueo.setParametro("SegundoCriterio", param.getSegundoCriterio());
		logueo.setParametro("SegundoOperador", param.getSegundoOperador());
		logueo.setParametro("SegundoValor", param.getSegundoValor());
		logueo.setParametro("OperadorLogico", param.getOperadorLogico());

		ResultObtenerPersonas datos = new ResultObtenerPersonas();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesMaestroPersonas validar = new ValidacionesMaestroPersonas();
			ServiciosError error = validar.validarObtenerPersonas(param);

			if (error == null) {
				datos = WsMaestroPersonas.getEJBManager().obtenerPersonas(param);
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

	public ResultPersonaFisica obtenerDatosPersonaFisica(ParamPersona param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsMaestroPersonas.class);
		logueo.setMetodo("obtenerDatosPersonaFisica");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("CodPersona", param.getCodPersona());

		ResultPersonaFisica datos = new ResultPersonaFisica();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesMaestroPersonas validar = new ValidacionesMaestroPersonas();
			ServiciosError error = validar.validarObtenerDatosPersonaFisica(param);

			if (error == null) {
				datos = WsMaestroPersonas.getEJBManager().obtenerDatosPersonaFisica(param);
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

	public ResultPersonaJuridica obtenerDatosPersonaJuridica(ParamPersona param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsMaestroPersonas.class);
		logueo.setMetodo("obtenerDatosPersonaJurídica");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("PrimerCriterio", param.getCodPersona());

		ResultPersonaJuridica datos = new ResultPersonaJuridica();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesMaestroPersonas validar = new ValidacionesMaestroPersonas();
			ServiciosError error = validar.ValidarObtenerDatosPersonaJuridica(param);

			if (error == null) {
				datos = WsMaestroPersonas.getEJBManager().obtenerDatosPersonaJuridica(param);
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

	public ResultObtenerDirecciones obtenerDireccionesDeUnaPersona(ParamPersona param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsMaestroPersonas.class);
		logueo.setMetodo("obtenerDireccionesDeUnaPersona");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("PrimerCriterio", param.getCodPersona());

		ResultObtenerDirecciones datos = new ResultObtenerDirecciones();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesMaestroPersonas validar = new ValidacionesMaestroPersonas();
			ServiciosError error = validar.validarObtenerDireccionesDeUnaPersona(param);

			if (error == null) {
				datos = WsMaestroPersonas.getEJBManager().obtenerDireccionesDeUnaPersona(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}

		}  catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}
		return datos;
	}

	public ResultDireccion obtenerDatosDeUnaDireccion(ParamObtenerDireccion param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsMaestroPersonas.class);
		logueo.setMetodo("obtenerDatosDeUnaDireccion");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("CodDireccion", param.getCodDireccion());
		logueo.setParametro("CodPersona", param.getCodPersona());

		ResultDireccion datos = new ResultDireccion();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesMaestroPersonas validar = new ValidacionesMaestroPersonas();
			ServiciosError error = validar.validarObtenerDatosDeUnaDirección(param);

			if (error == null) {
				datos = WsMaestroPersonas.getEJBManager().obtenerDatosDeUnaDireccion(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}

		}  catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}
		return datos;
	}

	public ResultRoles obtenerRolesDeUnaPersona(ParamPersona param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsMaestroPersonas.class);
		logueo.setMetodo("obtenerRolesDeUnaPersona");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("CodPersona", param.getCodPersona());

		ResultRoles datos = new ResultRoles();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesMaestroPersonas validar = new ValidacionesMaestroPersonas();
			ServiciosError error = validar.validarObtenerRolesDeUnaPersona(param);

			if (error == null) {
				datos = WsMaestroPersonas.getEJBManager().obtenerRolesDeUnaPersona(param);
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

	public ResultComunicaciones obtenerComunicacionesDeUnaPersona(ParamPersona param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsMaestroPersonas.class);
		logueo.setMetodo("obtenerComunicacionesDeUnaPersona");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("PrimerCriterio", param.getCodPersona());

		ResultComunicaciones datos = new ResultComunicaciones();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesMaestroPersonas validar = new ValidacionesMaestroPersonas();
			ServiciosError error = validar.validarObtenerComunicacionesDeUnaPersona(param);

			if (error == null) {
				datos = WsMaestroPersonas.getEJBManager().obtenerComunicacionesDeUnaPersona(param);
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

	public ResultActivarPersona activarPersona(ParamDatosActivarPersona param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsMaestroPersonas.class);
		logueo.setMetodo("activarPersona");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("CodPersona", param.getCodPersona());

		ResultActivarPersona datos = new ResultActivarPersona();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesMaestroPersonas validar = new ValidacionesMaestroPersonas();
			ServiciosError error = validar.validarActivarPersona(param);

			if (error == null) {
				datos = WsMaestroPersonas.getEJBManager().activarPersona(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}

		}catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}
		return datos;
	}

	
	
	
	public ResultObtenerPersonasClientes obtenerPersonasClientes(ParamObtenerPersonasClientes param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Logueo.ENCABEZADOWS);
		logueo.setClase(WsMaestroPersonas.class);
		logueo.setMetodo("obtenerPersonasClientes");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Apellido o razon", param.getApellidoRazon());
		logueo.setParametro("Nombre", param.getNombre());
		logueo.setParametro("Codigo cliente", param.getCodCliente());
		logueo.setParametro("RUT", param.getRut());
		logueo.setParametro("Tipo documento", param.getTipoDoc());
		logueo.setParametro("Numero documento", param.getNumDocumento());
		logueo.setParametro("Tipo persona", param.getTipoPersona());
		logueo.setParametro("Todos los clientes", param.getTodosLosClientes());
		logueo.setParametro("Telefono", param.getTel());
		logueo.setParametro("Orden", param.getOrden());

		ResultObtenerPersonasClientes datos = new ResultObtenerPersonasClientes();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesMaestroPersonas validar = new ValidacionesMaestroPersonas();
			ServiciosError error = validar.validarObtenerPersonasClientes(param);

			if (error == null) {
				datos = WsMaestroPersonas.getEJBManager().obtenerPersonasClientes(param);
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

	public ResultAltaRolCliente altaRolCliente(ParamAltaRolCliente param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Logueo.ENCABEZADOWS);
		logueo.setClase(WsMaestroPersonas.class);
		logueo.setMetodo("altaRolCliente");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero persona", param.getNumPersona());

		ResultAltaRolCliente datos = new ResultAltaRolCliente();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesMaestroPersonas validar = new ValidacionesMaestroPersonas();
			ServiciosError error = validar.validarAltaRolCliente(param);

			if (error == null) {
				datos = WsMaestroPersonas.getEJBManager().altaRolCliente(param);
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

	public ResultObtenerPersonasAlta obtenerPersonasAlta(ParamObtenerPersonasAlta param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Logueo.ENCABEZADOWS);
		logueo.setClase(WsMaestroPersonas.class);
		logueo.setMetodo("obtenerPersonasAlta");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Apellido/Razon", param.getApellidoRazon());
		logueo.setParametro("Nombre", param.getNombre());
		logueo.setParametro("RUT", param.getRut());
		logueo.setParametro("Tipo documento", param.getTipoDoc());
		logueo.setParametro("Numero documento", param.getNumDocumento());
		logueo.setParametro("Tipo persona", param.getTipoPersona());

		ResultObtenerPersonasAlta datos = new ResultObtenerPersonasAlta();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesMaestroPersonas validar = new ValidacionesMaestroPersonas();
			ServiciosError error = validar.validarObtenerPersonasAlta(param);

			if (error == null) {
				datos = WsMaestroPersonas.getEJBManager().obtenerPersonasAlta(param);
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

	

	public ResultPersonaCorredor obtenerPersonaCorredor(ParamPersonaCorredor param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Logueo.ENCABEZADOWS);
		logueo.setClase(WsMaestroPersonas.class);
		logueo.setMetodo("obtenerPersonaCorredor");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Id corredor", param.getIdCorredor());
		
		ResultPersonaCorredor datos = new ResultPersonaCorredor();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesMaestroPersonas validar = new ValidacionesMaestroPersonas();
			ServiciosError error = validar.validarObtenerPersonaCorredor(param);

			if (error == null) {
				datos = WsMaestroPersonas.getEJBManager().obtenerPersonaCorredor(param);
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

	public ResultPolizasAsociadasClientes obtenerPolizasAsociadasClientes(ParamPolizasAsociadasClientes param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Logueo.ENCABEZADOWS);
		logueo.setClase(WsMaestroPersonas.class);
		logueo.setMetodo("obtenerPolizasAsociadasClientes");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo cliente", param.getCodCliente());
		logueo.setParametro("Codigo direccion", param.getCodDireccion());

		ResultPolizasAsociadasClientes datos = new ResultPolizasAsociadasClientes();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesMaestroPersonas validar = new ValidacionesMaestroPersonas();
			ServiciosError error = validar.validarObtenerPolizasAsociadasClientes(param);

			if (error == null) {
				datos = WsMaestroPersonas.getEJBManager().obtenerPolizasAsociadasClientes(param);
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
	 
	
	
	public ResultPolizasAsociadas obtenerPolizasAsociadas(ParamPolizasAsociadas param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Logueo.ENCABEZADOWS);
		logueo.setClase(WsMaestroPersonas.class);
		logueo.setMetodo("obtenerPolizasAsociadas");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo cliente", param.getCodCliente());
		logueo.setParametro("Codigo direccion", param.getCodDireccion());

		ResultPolizasAsociadas datos = new ResultPolizasAsociadas();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesMaestroPersonas validar = new ValidacionesMaestroPersonas();
			ServiciosError error = validar.validarObtenerPolizasAsociadas(param);

			if (error == null) {
				datos = WsMaestroPersonas.getEJBManager().obtenerPolizasAsociadas(param);
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
	public ResultDatoBancario modificarDatosBancarios(ParamModificarDatoBancario param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Logueo.ENCABEZADOWS);
		logueo.setClase(WsMaestroPersonas.class);
		logueo.setMetodo("modificarDatosBancarios");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		
		logueo.setParametro("Numero consecutivo", param.getNumCons());
		logueo.setParametro("Numero de persona",  param.getCodPersona());
		logueo.setParametro("Codigo de banco", param.getCodBanco());
		logueo.setParametro("Tipo de cuenta", param.getTipoCuenta());
		logueo.setParametro("Tipo de tarjeta", param.getTipoTarjeta());
		logueo.setParametro("Numero de cuenta", param.getNumcuenta());
		logueo.setParametro("Fecha de vencimiento", param.getFechaVencimiento());
		
		ResultDatoBancario datos = new ResultDatoBancario();
		LOG.info(logueo.getSoloParametros());
		
		try {
			ValidacionesMaestroPersonas validar = new ValidacionesMaestroPersonas();
			ServiciosError error = validar.validarDatosBancarios(param);

			if (error == null) {
				datos = WsMaestroPersonas.getEJBManager().modificarDatosBancarios(param);
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
	
	
	public ResultDatoBancario borrarDatosBancarios(ParamBorrarDatoBancario param){
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Logueo.ENCABEZADOWS);
		logueo.setClase(WsMaestroPersonas.class);
		logueo.setMetodo("modificarDatosBancarios");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		
		logueo.setParametro("Numero consecutivo", param.getNumCons());
		logueo.setParametro("Numero de persona",  param.getCodPersona());
		
		ResultDatoBancario datos = new ResultDatoBancario();
		LOG.info(logueo.getSoloParametros());
		
		try {
			ValidacionesMaestroPersonas validar = new ValidacionesMaestroPersonas();
			ServiciosError error = validar.validarBorrarDatosBancarios(param);

			if (error == null) {
				datos = WsMaestroPersonas.getEJBManager().borrarDatosBancarios(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}

		}  catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}
		return datos;
		
		
		
		
	}
	
	public ResultObtenerDatosBancariosPersona obtenerDatosBancariosPersona(ParamObtenerDatosBancariosPersona param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsMaestroPersonas.class);
		logueo.setMetodo("obtenerDatosBancariosPersona");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo de persona", param.getCodCliente());

		ResultObtenerDatosBancariosPersona datos = new ResultObtenerDatosBancariosPersona();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesMaestroPersonas validar = new ValidacionesMaestroPersonas();
			ServiciosError error = validar.validarObtenerDatosBancariosPersona(param);

			if (error == null) {
				datos = WsMaestroPersonas.getEJBManager().obtenerDatosBancariosPersona(param);
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
	
	public ResultListaBancosPersona listaBancosPersona(ParamListaBancosPersona param){
		
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsMaestroPersonas.class);
		logueo.setMetodo("listaBancosPersona");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		ResultListaBancosPersona datos = new ResultListaBancosPersona();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesMaestroPersonas validar = new ValidacionesMaestroPersonas();
			ServiciosError error = validar.validarListaBancosPersona(param);

			if (error == null) {
				datos = WsMaestroPersonas.getEJBManager().listaBancosPersona(param);
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
	

	public ResultListaProfesiones listaProfesiones(ParamProfesiones param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsMaestroPersonas.class);
		logueo.setMetodo("listaProfesiones");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		ResultListaProfesiones datos = new ResultListaProfesiones();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesMaestroPersonas validar = new ValidacionesMaestroPersonas();
			ServiciosError error = validar.validarListaProfesiones(param);

			if (error == null) {
				datos = WsMaestroPersonas.getEJBManager().listaProfesiones(param);
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
	
	
	public ResultListaEstadoCivil listaEstadoCivil(ParamEstadoCivil param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsMaestroPersonas.class);
		logueo.setMetodo("listaEstadoCivil()");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		
		ResultListaEstadoCivil datos = new ResultListaEstadoCivil();
		LOG.info(logueo.getSoloParametros());
		try {
			
			ValidacionesMaestroPersonas validar = new ValidacionesMaestroPersonas();
			ServiciosError error = validar.validarListaEstadoCivil(param);

			if (error == null) {
				datos = WsMaestroPersonas.getEJBManager().listaEstadoCivil(param);
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
	
	public ResultListaTipoDireccion listaDirecciones(ParamTipoDireccion param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsMaestroPersonas.class);
		logueo.setMetodo("listaDirecciones");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		ResultListaTipoDireccion datos = new ResultListaTipoDireccion();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesMaestroPersonas validar = new ValidacionesMaestroPersonas();
			ServiciosError error = validar.validarListaDirecciones(param);

			if (error == null) {
				datos = WsMaestroPersonas.getEJBManager().listaTipoDireccion(param);
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

	public ResultListaSexo listaSexo(ParamListaSexo param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Logueo.ENCABEZADOWS);
		logueo.setClase(WsMaestroPersonas.class);
		logueo.setMetodo("listaSexo");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		ResultListaSexo datos = new ResultListaSexo();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesMaestroPersonas validar = new ValidacionesMaestroPersonas();
			ServiciosError error = validar.validarListaSexo(param);

			if (error == null) {
				datos = WsMaestroPersonas.getEJBManager().listaSexo(param);
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
	
	public ResultListaTipoComunicaciones listaTipoComunicaciones(ParamTipoComunicaciones param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsMaestroPersonas.class);
		logueo.setMetodo("listaTipoComunicaciones");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		ResultListaTipoComunicaciones datos = new ResultListaTipoComunicaciones();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesMaestroPersonas validar = new ValidacionesMaestroPersonas();
			ServiciosError error = validar.validarListaTipoComunicaciones(param);

			if (error == null) {
				datos = WsMaestroPersonas.getEJBManager().listaTipoComunicaciones(param);
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
	
	public ResultListaTipoPersoneria listaTipoPersoneria(ParamTipoPersoneria param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsMaestroPersonas.class);
		logueo.setMetodo("listaTipoPersoneria");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		ResultListaTipoPersoneria datos = new ResultListaTipoPersoneria();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesMaestroPersonas validar = new ValidacionesMaestroPersonas();
			ServiciosError error = validar.validarListaTipoPersoneria(param);

			if (error == null) {
				datos = WsMaestroPersonas.getEJBManager().listaTipoPersoneria(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}

		}  catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}
		return datos;
	}
	
	
	
public ResultCondicion tienePermisoDatosBancarios(ParamTienePermisoDatosBancarios param){
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsMaestroPersonas.class);
		logueo.setMetodo("tienePermisoDatosBancarios");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		ResultCondicion datos = new ResultCondicion();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesMaestroPersonas validar = new ValidacionesMaestroPersonas();
			ServiciosError error = validar.validarTienePermisoDatosBancarios(param);

			if (error == null) {
				datos = WsMaestroPersonas.getEJBManager().tienePermisoDatosBancarios(param);
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
	public ResultExistePersona existePersona(ParamExistePersona param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Logueo.ENCABEZADOWS);
		logueo.setClase(WsMaestroPersonas.class);
		logueo.setMetodo("existePersona");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Rut", param.getRut());
		logueo.setParametro("Numero de documento", param.getNumDocumento());
		logueo.setParametro("Tipo de documento", param.getTipoDoc());
		
		ResultExistePersona datos = new ResultExistePersona();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesMaestroPersonas validar = new ValidacionesMaestroPersonas();
			ServiciosError error = validar.validarExistePersona(param);

			if (error == null) {
				datos = WsMaestroPersonas.getEJBManager().existePersona(param);
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
	
	public ResultRadio listaRadio(ParamRadio param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Logueo.ENCABEZADOWS);
		logueo.setClase(WsMaestroPersonas.class);
		logueo.setMetodo("listaRadio");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo pais", param.getCodPais());
		logueo.setParametro("Codigo departamento", param.getCodDepartamento());
		logueo.setParametro("Codigo localidad", param.getCodLocalidad());
		logueo.setParametro("Numero postal", param.getNumPostal());
		logueo.setParametro("Numero radio", param.getNumRadio());
		logueo.setParametro("Codigo calle", param.getCodCalle());
		logueo.setParametro("Descripcion calle", param.getDescripCalle());
		logueo.setParametro("Numero puerta", param.getNumPuerta());
		logueo.setParametro("Descripcion departamento", param.getDescripDepartamento());
		logueo.setParametro("Descripcion localidad", param.getDescripLocalidad());

		ResultRadio datos = new ResultRadio();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesMaestroPersonas validar = new ValidacionesMaestroPersonas();
			ServiciosError error = validar.validarListaRadio(param);

			if (error == null) {
				datos = WsMaestroPersonas.getEJBManagerRector().listaRadio(param);
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
	
	public ResultAltaCliente altaCliente(ParamAltaCliente param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Logueo.ENCABEZADOWS);
		logueo.setClase(WsMaestroPersonas.class);
		logueo.setMetodo("altaCliente");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Tipo Persona", param.getTipoPersona());
		logueo.setParametro("CodPersona", param.getCodPersona());
		logueo.setParametro("CodigoDireccion", param.getCodDireccion());
		logueo.setParametro("Nombre", param.getNombre());
		logueo.setParametro("Apellido", param.getApellido());
		logueo.setParametro("CodigoEstadoCivil", param.getCodEstadoCivil());
		logueo.setParametro("CodigoProfesion", param.getCodProfesion());
		logueo.setParametro("FechaNacimiento", param.getFechaNacimiento());
		logueo.setParametro("TipoDocumento", param.getTipoDocumento());
		logueo.setParametro("NumDocumento", param.getNumDocumento());
		logueo.setParametro("Sexo", param.getSexo());
		logueo.setParametro("RUT", param.getRut());
		logueo.setParametro("Domicilio pais", param.getDomicilios().get(0).getCodPais());
		logueo.setParametro("Domicilio radio", param.getDomicilios().get(0).getDescripRadio());
		logueo.setParametro("CodigoDireccion", param.getCodDireccion());
		logueo.setParametro("CodActividad", param.getCodActividad());
		logueo.setParametro("CodOrganismo", param.getCodOrganismo());
		logueo.setParametro("RazonSocial", param.getRazonSocial());
		logueo.setParametro("FechaInicio", param.getFechaInicio());
		logueo.setParametro("tipo de comunicacion", param.getComunicaciones().get(0).getTipoComunicacion());
		
		ResultAltaCliente datos = new ResultAltaCliente();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesMaestroPersonas validar = new ValidacionesMaestroPersonas();
			ServiciosError error = validar.validarAltaCliente(param);

			if (error == null) {
				datos = WsMaestroPersonas.getEJBManager().altaCliente(param);
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
/*
 *  Consulta nueva de clientes devuelve solo el cabezal de los clientes 
 * 
 * 
 */
	public ResultObtenerClientes obtenerClientes(ParamObtenerClientes param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Logueo.ENCABEZADOWS);
		logueo.setClase(WsMaestroPersonas.class);
		logueo.setMetodo("obtenerClientes");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Apellido o razon", param.getApellidoRazon());
		logueo.setParametro("Nombre", param.getNombre());
		logueo.setParametro("Codigo cliente", param.getCodCliente());
		logueo.setParametro("RUT", param.getRut());
		logueo.setParametro("Tipo documento", param.getTipoDoc());
		logueo.setParametro("Numero documento", param.getNumDocumento());
		logueo.setParametro("Tipo persona", param.getTipoPersona());
		logueo.setParametro("Todos los clientes", param.getTodosLosClientes());
		logueo.setParametro("Telefono", param.getTel());
		logueo.setParametro("Orden", param.getOrden());

		ResultObtenerClientes datos = new ResultObtenerClientes();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesMaestroPersonas validar = new ValidacionesMaestroPersonas();
			ServiciosError error = validar.validarObtenerClientes(param);

			if (error == null) {
				datos = WsMaestroPersonas.getEJBManager().obtenerClientes(param);
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

	
	//obtenerDetalleConsultaClientes
	
	
	
	
	public ResultObtenerDetalleConsultaClientes obtenerDetalleConsultaClientes(ParamObtenerDetalleConsultaClientes param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Logueo.ENCABEZADOWS);
		logueo.setClase(WsMaestroPersonas.class);
		logueo.setMetodo("obtenerDetalleConsultaClientes");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero de persona : ",param.getNumPersona());
		logueo.setParametro("Numero de cliente : ",param.getCodCliente());
		logueo.setParametro("Todos los clientes : ",param.getTodosLosClientes());
		

		ResultObtenerDetalleConsultaClientes datos = new ResultObtenerDetalleConsultaClientes();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesMaestroPersonas validar = new ValidacionesMaestroPersonas();
			ServiciosError error = validar.validarObtenerDetalleConsultaClientes(param);

			if (error == null) {
				datos = WsMaestroPersonas.getEJBManager().obtenerDetalleConsultaClientes(param);
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
	
	public ResultExportarClientes exportarClientes(ParamExportarClientes param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Logueo.ENCABEZADOWS);
		logueo.setClase(WsMaestroPersonas.class);
		logueo.setMetodo("exportarClientes");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numeros de personas : ",param.getListaNumerosPersonas());

		

		ResultExportarClientes datos = new ResultExportarClientes();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesMaestroPersonas validar = new ValidacionesMaestroPersonas();
			ServiciosError error = validar.exportarClientes(param);

			if (error == null) {
				datos = WsMaestroPersonas.getEJBManager().exportarClientes(param);
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
	
	public static MaestroPersonasLocal getEJBManager() throws NamingException {
		final InitialContext ctx = new InitialContext();
		return (MaestroPersonasLocal) ctx.lookup("global/ServiciosRector/IRectorEJB/MaestroPersonas!uy.com.bse.serviciosEJB.MaestroPersonasLocal");
	}
	
	public static ServiciosRectorGenericosLocal getEJBManagerRector() throws NamingException {
		final InitialContext ctx = new InitialContext();
		return (ServiciosRectorGenericosLocal) ctx.lookup("global/ServiciosRector/IRectorEJB/ServiciosRectorGenericos!uy.com.bse.serviciosEJB.ServiciosRectorGenericosLocal");
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

	
	private void enviarMail(Logueo logueo) {
		try {
			EARPropertiesManager manager= new EARPropertiesManager("configServiciosRector.properties");
			MailBuilder.create().sentToDefaultConfigureMail(manager.obtenerValor("serviciosRector.mail.soporte"),manager.obtenerValor("serviciosRector.ccmail.soporte"),logueo.getMensaje(), "SERVICIO MAESTROPERSONAS Error  BSE Ambiente: " + InetAddress.getLocalHost().getHostName());
		} catch (UnknownHostException e) {
			LOG.debug("No se encontro el nombre de Host", e);
		}

	}



	

	

}
