package uy.com.bse.serviciosEJB;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import uy.com.bse.LogicaRector;
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
import uy.com.bse.utilitario.dato.ResultCondicion;
import uy.com.bse.utilitario.dato.ResultGenerico;

@Stateless
public class MaestroPersonas implements MaestroPersonasLocal {
	private static Logger log = LogManager.getLogger(MaestroPersonas.class);
	
	
	@Override
	@Interceptors(UserInterceptor.class)
	public ResultListaProfesiones listaProfesiones(ParamProfesiones param) {
		log.debug("listaProfesiones start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("listaProfesiones end: " + param);
		return (ResultListaProfesiones) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultListaMotivosBaja listaMotivosBaja(ParamMotivosBaja param) {
		log.debug("listaMotivosBaja start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("listaMotivosBaja end: " + param);
		return (ResultListaMotivosBaja) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultListaEstadoCivil listaEstadoCivil(ParamEstadoCivil param) {
		log.debug("listaEstadoCivil start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("listaEstadoCivil end: " + param);
		return (ResultListaEstadoCivil) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultListaTipoDireccion listaTipoDireccion(ParamTipoDireccion param) {
		log.debug("listaTipoDireccion start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("listaTipoDireccion end: " + param);
		return (ResultListaTipoDireccion) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultListaTipoComunicaciones listaTipoComunicaciones(ParamTipoComunicaciones param) {
		log.debug("listaTipoComunicaciones start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("listaTipoComunicaciones end: " + param);
		return (ResultListaTipoComunicaciones) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultListaTipoPersoneria listaTipoPersoneria(ParamTipoPersoneria param) {
		log.debug("listaTipoPersoneria start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("listaTipoPersoneria end: " + param);
		return (ResultListaTipoPersoneria) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultListaSexo listaSexo(ParamListaSexo param) {
		log.debug("listaSexo start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("listaSexo end: " + param);
		return (ResultListaSexo) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultObtenerPersonas obtenerPersonas(ParamObtenerPersonas param) {
		log.debug("obtenerPersonas start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerPersonas end");
		return (ResultObtenerPersonas) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultPersonaFisica obtenerDatosPersonaFisica(ParamPersona param) {
		log.debug("obtenerDatosPersonaFisica start: " + param);
		ResultGenerico result = LogicaRector.solve(param,"obtenerDatosPersonaFisica");
		log.debug("obtenerDatosPersonaFisica end");
		return (ResultPersonaFisica) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultPersonaJuridica obtenerDatosPersonaJuridica(ParamPersona param) {
		log.debug("obtenerDatosPersonaJuridica start: " + param);
		ResultGenerico result = LogicaRector.solve(param,"obtenerDatosPersonaJuridica");
		log.debug("obtenerDatosPersonaJuridica end");
		return (ResultPersonaJuridica) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultObtenerDirecciones obtenerDireccionesDeUnaPersona(ParamPersona param) {
		log.debug("obtenerDireccionesDeUnaPersona start: " + param);
		ResultGenerico result = LogicaRector.solve(param,"obtenerDireccionesDeUnaPersona");
		log.debug("obtenerDireccionesDeUnaPersona end");
		return (ResultObtenerDirecciones) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultDireccion obtenerDatosDeUnaDireccion(ParamObtenerDireccion param) {
		log.debug("obtenerDatosDeUnaDireccion start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerDatosDeUnaDireccion end");
		return (ResultDireccion) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultRoles obtenerRolesDeUnaPersona(ParamPersona param) {
		log.debug("obtenerRolesDeUnaPersona start: " + param);
		ResultGenerico result = LogicaRector.solve(param,"obtenerRolesDeUnaPersona");
		log.debug("obtenerRolesDeUnaPersona end");
		return (ResultRoles) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultComunicaciones obtenerComunicacionesDeUnaPersona(ParamPersona param) {
		log.debug("obtenerComunicacionesDeUnaPersona start: " + param);
		ResultGenerico result = LogicaRector.solve(param,"obtenerComunicacionesDeUnaPersona");
		log.debug("obtenerComunicacionesDeUnaPersona end");
		return (ResultComunicaciones) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultCondicion tienePermisoDatosBancarios(ParamTienePermisoDatosBancarios param) {
		log.debug("tienePermisoDatosBancarios start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("tienePermisoDatosBancarios end");
		return (ResultCondicion) result;

	}

	@Override
	@Interceptors(TransactionInterceptorRector.class)
	public ResultPersonaFisica altaPersonaFisica(ParamDatosPersonaFisica param) {
		log.debug("altaPersonaFisica start: " + param);
		ResultGenerico result = LogicaRector.solve(param,"altaPersonaFisica");
		log.debug("altaPersonaFisica end");
		return (ResultPersonaFisica) result;
	}

	@Override
	@Interceptors(TransactionInterceptorRector.class)
	public ResultPersonaJuridica altaPersonaJuridica(ParamDatosPersonaJuridica param) {
		log.debug("altaPersonaJuridica start: " + param);
		ResultGenerico result = LogicaRector.solve(param,"altaPersonaJuridica");
		log.debug("altaPersonaJuridica end");
		return (ResultPersonaJuridica) result;
	}

	@Override
	@Interceptors(TransactionInterceptorRector.class)
	public ResultPersonaFisica modificarPersonaFisica(ParamDatosPersonaFisica param) {
		log.debug("ResultPersonaJuridica start: " + param);
		ResultGenerico result = LogicaRector.solve(param,"modificarPersonaFisica");
		log.debug("ResultPersonaJuridica end");
		return (ResultPersonaFisica) result;
	}

	@Override
	@Interceptors(TransactionInterceptorRector.class)
	public ResultPersonaJuridica modificarPersonaJuridica(ParamDatosPersonaJuridica param) {
		log.debug("modificarPersonaJuridica start: " + param);
		ResultGenerico result = LogicaRector.solve(param,"modificarPersonaJuridica");
		log.debug("modificarPersonaJuridica end");
		return (ResultPersonaJuridica) result;
	}

	@Override
	@Interceptors(TransactionInterceptorRector.class)
	public ResultBajaPersona bajaPersona(ParamDatosBajaPersona param) {
		log.debug("bajaPersona start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("bajaPersona end");
		return (ResultBajaPersona) result;
	}

	@Override
	@Interceptors(TransactionInterceptorRector.class)
	public ResultComunicacion altaComunicaciones(ParamAltaComunicacion param) {
		log.debug("altaComunicaciones start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("altaComunicaciones end");
		return (ResultComunicacion) result;
	}

	@Override
	@Interceptors(TransactionInterceptorRector.class)
	public ResultComunicacion modificarComunicaciones(ParamModificarComunicacion param) {
		log.debug("modificarComunicaciones start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("modificarComunicaciones end");
		return (ResultComunicacion) result;
	}

	@Override
	@Interceptors(TransactionInterceptorRector.class)
	public ResultComunicacionEC bajaComunicaciones(ParamBajaComunicacion param) {
		log.debug("bajaComunicaciones start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("bajaComunicaciones end");
		return (ResultComunicacionEC) result;
	}

	@Override
	@Interceptors(TransactionInterceptorRector.class)
	public ResultRolCliente modificarDatosRolCliente(ParamDatosRolCliente param) {
		log.debug("modificarDatosRolCliente start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("modificarDatosRolCliente end");
		return (ResultRolCliente) result;
	}

	@Override
	@Interceptors(TransactionInterceptorRector.class)
	public ResultDireccion altaDireccion(ParamDatosDireccion param) {
		log.debug("altaDireccion start: " + param);
		ResultGenerico result = LogicaRector.solve(param,"altaDireccion");
		log.debug("altaDireccion end");
		return (ResultDireccion) result;
	}

	@Override
	@Interceptors(TransactionInterceptorRector.class)
	public ResultDireccion modificarDireccion(ParamDatosDireccion param) {
		log.debug("modificarDireccion start: " + param);
		ResultGenerico result = LogicaRector.solve(param,"modificarDireccion");
		log.debug("modificarDireccion end");
		return (ResultDireccion) result;
	}

	@Override
	@Interceptors(TransactionInterceptorRector.class)
	public ResultBajaDireccion bajaDireccion(ParamDatosBajaDireccion param) {
		log.debug("bajaDireccion start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("bajaDireccion end");
		return (ResultBajaDireccion) result;
	}

	@Override
	@Interceptors(TransactionInterceptorRector.class)
	public ResultActivarPersona activarPersona(ParamDatosActivarPersona param) {
		log.debug("activarPersona start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("activarPersona end");
		return (ResultActivarPersona) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultObtenerPersonasClientes obtenerPersonasClientes(ParamObtenerPersonasClientes param) {
		log.debug("obtenerPersonasClientes start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerPersonasClientes end");
		return (ResultObtenerPersonasClientes) result;
	}

	@Override
	@Interceptors(TransactionInterceptorRector.class)
	public ResultAltaRolCliente altaRolCliente(ParamAltaRolCliente param) {
		log.debug("altaRolCliente start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("altaRolCliente end");
		return (ResultAltaRolCliente) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultObtenerPersonasAlta obtenerPersonasAlta(ParamObtenerPersonasAlta param) {
		log.debug("obtenerPersonasAlta start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerPersonasAlta end");
		return (ResultObtenerPersonasAlta) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultPersonaCorredor obtenerPersonaCorredor(ParamPersonaCorredor param) {
		log.debug("obtenerPersonaCorredor start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerPersonaCorredor end");
		return (ResultPersonaCorredor) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultPolizasAsociadas obtenerPolizasAsociadas(ParamPolizasAsociadas param) {
		log.debug("obtenerPolizasAsociadas start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerPolizasAsociadas end");
		return (ResultPolizasAsociadas) result;
	}

	
	@Override
	@Interceptors(UserInterceptor.class)
	public ResultPolizasAsociadasClientes obtenerPolizasAsociadasClientes(ParamPolizasAsociadasClientes param) {
		log.debug("obtenerPolizasAsociadasClientes start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerPolizasAsociadasClientes end");
		return (ResultPolizasAsociadasClientes) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultExistePersona existePersona(ParamExistePersona param) {
		log.debug("existePersona start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("existePersona end");
		return (ResultExistePersona) result;
	}

	@Override
	@Interceptors(TransactionInterceptorRector.class)
	public ResultDatoBancario modificarDatosBancarios(ParamModificarDatoBancario param) {
		log.debug("modificarDatosBancarios start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("modificarDatosBancarios end");
		return (ResultDatoBancario) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultObtenerDatosBancariosPersona obtenerDatosBancariosPersona(ParamObtenerDatosBancariosPersona param) {
		log.debug("obtenerDatosBancariosPersona start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerDatosBancariosPersona end");
		return (ResultObtenerDatosBancariosPersona) result;
	}

	@Override
	@Interceptors(TransactionInterceptorRector.class)
	public ResultDatoBancario borrarDatosBancarios(ParamBorrarDatoBancario param) {
		log.debug("borrarDatosBancarios start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("borrarDatosBancarios end");
		return (ResultDatoBancario) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultListaBancosPersona listaBancosPersona(ParamListaBancosPersona param) {
		log.debug("listaBancosPersona start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("listaBancosPersona end");
		return (ResultListaBancosPersona) result;
	}

	@Override
	@Interceptors(TransactionInterceptorRector.class)
	public ResultAltaCliente altaCliente(ParamAltaCliente param) {
		log.debug("altaCliente start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("altaCliente end");
		return (ResultAltaCliente) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultObtenerClientes obtenerClientes(ParamObtenerClientes param) {
		log.debug("obtenerClientes start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerClientes end");
		return (ResultObtenerClientes) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultObtenerDetalleConsultaClientes obtenerDetalleConsultaClientes(ParamObtenerDetalleConsultaClientes param) {
		log.debug("obtenerDetalleConsultaClientes start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerDetalleConsultaClientes end");
		return (ResultObtenerDetalleConsultaClientes) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultExportarClientes exportarClientes(ParamExportarClientes param) {
		log.debug("exportarClientes start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("exportarClientes end");
		return (ResultExportarClientes) result;
	}
}
