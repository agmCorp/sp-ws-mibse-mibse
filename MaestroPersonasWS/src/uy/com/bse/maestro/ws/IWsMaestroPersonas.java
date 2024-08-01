package uy.com.bse.maestro.ws;

import javax.jws.WebMethod;
import javax.jws.WebService;

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
import uy.com.bse.utilitario.dato.ResultCondicion;

@WebService
public interface IWsMaestroPersonas {
	
	
	/**
	 * Lista las profesiones existentes
	 * @param param
	 * @return Un objeto con una lista de profesiones
	 */
	@WebMethod	
	public ResultListaProfesiones listaProfesiones(ParamProfesiones param);
	
	/**
	 * Lista los estados civiles existentes
	 * @param param
	 * @return Un objeto con una lista de estados civil
	 */
	@WebMethod
	public ResultListaEstadoCivil listaEstadoCivil(ParamEstadoCivil param);
	
	/**
	 * Lista los tipos de comunicaciones existentes
	 * @param param
	 * @return Un objeto con una lista de tipos de comunicaciones
	 */
	@WebMethod
	public ResultListaTipoComunicaciones listaTipoComunicaciones(ParamTipoComunicaciones param);
	

	/**
	 * Lista los tipos de personeria existentes
	 * @param param
	 * @return Un objeto con una lista de tipos de personeria
	 */
	@WebMethod
	public ResultListaTipoPersoneria listaTipoPersoneria(ParamTipoPersoneria param);
	
	/**
	 * Lista los sexos disponibles
	 * @return
	 */
	@WebMethod
	public ResultListaSexo listaSexo(ParamListaSexo param);
	
	
	/**
	 * Lista los motivos de baja existentes
	 * @param param
	 * @return Un objeto con una lista de motivos de baja
	 */
	@WebMethod
	public ResultListaMotivosBaja listaMotivosBaja(ParamMotivosBaja param);
	
	

	/**
	 * Lista las direcciones existentes
	 * @param param
	 * @return Un objeto con una lista de direcciones
	 */
	@WebMethod
	public ResultListaTipoDireccion listaDirecciones(ParamTipoDireccion param);
	
	

	/**
	 * Da de alta una persona fisica
	 * @param param
	 * @return Un objeto personaFisica
	 */
	@WebMethod
	public ResultPersonaFisica altaPersonaFisica (ParamDatosPersonaFisica param);	
	
	/**
	 * Da de alta una persona juridica
	 * @param param
	 * @return Un objeto personaJuridica
	 */
	@WebMethod
	public ResultPersonaJuridica altaPersonaJuridica (ParamDatosPersonaJuridica param);
	
	/**
	 * Modifica una persona fisica
	 * @param param
	 * @return Un objeto personaFisica
	 */
	@WebMethod
	public ResultPersonaFisica modificarPersonaFisica (ParamDatosPersonaFisica param);
	
	/**
	 * Modifica una persona juridica
	 * @param param
	 * @return Un objeto personaJuridica
	 */
	@WebMethod
	public ResultPersonaJuridica modificarPersonaJuridica (ParamDatosPersonaJuridica param);
	
	/**
	 * Baja de una persona
	 * @param param
	 * @return Codigo de error y descripcion en caso de que sea necesario
	 */
	@WebMethod
	public ResultBajaPersona bajaPersona (ParamDatosBajaPersona param);
	
	/**
	 * Alta de una comunicacion
	 * @param param
	 * @return Una lista de comunicaciones
	 */
	@WebMethod
	public ResultComunicacion altaComunicaciones (ParamAltaComunicacion param);
	
	/**
	 * Modifica una o varias comunicaciones
	 * @param param
	 * @return una lista de comunicaciones
	 */
	@WebMethod
	public ResultComunicacion modificarComunicaciones (ParamModificarComunicacion param);
	
	/**
	 * Da de baja una o varias comunicaciones
	 * @param param
	 * @return Codigo de error y descripcion en caso de que sea necesario
	 */
	@WebMethod
	public ResultComunicacionEC bajaComunicaciones (ParamBajaComunicacion param);
	
	/**
	 * Modifica los datos de un rol cliente
	 * @param param
	 * @return Codigo de error y descripcion en caso de que sea necesario
	 */
	@WebMethod
	public ResultRolCliente modificarDatosRolCliente (ParamDatosRolCliente param);
	
	/**
	 * Da de alta una direccion
	 * @param param
	 * @return Un objeto con la direccion creada
	 */
	@WebMethod
	public ResultDireccion altaDireccion (ParamDatosDireccion param);
	
	/**
	 * Modifica una direccion
	 * @param param
	 * @return Un objeto con la direccion modificada
	 */
	@WebMethod
	public ResultDireccion modificarDireccion (ParamDatosDireccion param);
	
	/**
	 * Da de baja una direccion
	 * @param param
	 * @return Codigo de error y descripcion en caso de que sea necesario
	 */
	@WebMethod
	public ResultBajaDireccion bajaDireccion (ParamDatosBajaDireccion param);
	
	/**
	 * Consulta de personas
	 * @param param
	 * @return Una lista de personas fisicas y una de personas juridicas
	 */
	@WebMethod
	public ResultObtenerPersonas obtenerPersonas(ParamObtenerPersonas param);
	
	/**
	 * Consulta de persona fisica
	 * @param param
	 * @return Un objeto de persona fisica
	 */
	@WebMethod
	public ResultPersonaFisica obtenerDatosPersonaFisica(ParamPersona param);
	
	/**
	 * Consulta de persona juridica
	 * @param param
	 * @return Un objeto de persona juridica
	 */
	@WebMethod
	public ResultPersonaJuridica obtenerDatosPersonaJuridica(ParamPersona param);
	
	/**
	 * Consulta las direcciones de una persona
	 * @param param
	 * @return Una lista de direcciones
	 */
	@WebMethod
	public ResultObtenerDirecciones obtenerDireccionesDeUnaPersona(ParamPersona param);
	
	/**
	 * Consulta de una direccion
	 * @param param
	 * @return Retorna un objeto direccionEC
	 */
	@WebMethod
	public ResultDireccion obtenerDatosDeUnaDireccion(ParamObtenerDireccion param);
	
	/**
	 * Consulta los roles de una persona
	 * @param param
	 * @return Una lista de roles
	 */
	@WebMethod
	public ResultRoles obtenerRolesDeUnaPersona(ParamPersona param);
	
	/**
	 * Consulta las comunicaciones de una persona
	 * @param param
	 * @return Una lista de datos de comunicaciones
	 */
	@WebMethod
	public ResultComunicaciones obtenerComunicacionesDeUnaPersona(ParamPersona param);
	
	/**
	 * Activa una persona
	 * @param param
	 * @return Codigo de error y descripcion en caso de que sea necesario
	 */
	@WebMethod
	public ResultActivarPersona activarPersona(ParamDatosActivarPersona param);
	
	
	

	/**
	 * Devuelve los clientes de acuerdo a los filtros.
	 * @return
	 */
	@WebMethod
	public ResultObtenerPersonasClientes obtenerPersonasClientes (ParamObtenerPersonasClientes param);
	
	/**
	 * Da de alta a una persona como cliente en el sistema.
	 * @return
	 */
	@WebMethod
	public ResultAltaRolCliente altaRolCliente (ParamAltaRolCliente param);
	
	/**
	 * Devuelve codigo cliente/numero de persona dependiendo de los datos de entrada.
	 * @return
	 */
	@WebMethod	
	public ResultObtenerPersonasAlta obtenerPersonasAlta (ParamObtenerPersonasAlta param);
	
	
	
	/**
	 * Devuelve codigo de persona correspondiente al Id de corredor.
	 * @return
	 */
	@WebMethod	
	public ResultPersonaCorredor obtenerPersonaCorredor (ParamPersonaCorredor param);
	
	/**
	 * Devuelve las polizas asociadas a una direccion.
	 * @return
	 */
	@WebMethod	
	public ResultPolizasAsociadas obtenerPolizasAsociadas (ParamPolizasAsociadas param);
	
	@WebMethod
	public ResultPolizasAsociadasClientes obtenerPolizasAsociadasClientes(ParamPolizasAsociadasClientes param);
	/**
	 * Busca la persona en el sistema, si existe como cliente devuelve su código de cliente y número de persona, 
	 * si existe como persona devuelve número de persona, si no, no devuelve nada.
	 * @return
	 */
	@WebMethod	
	public ResultExistePersona existePersona (ParamExistePersona param);
	
	/**
	 * 
	 * @param param
	 * @return
	 */
	@WebMethod
	public ResultDatoBancario modificarDatosBancarios(ParamModificarDatoBancario param);
	
	/**
	 * 
	 * @param param
	 * @return
	 */
	@WebMethod
	public ResultDatoBancario borrarDatosBancarios(ParamBorrarDatoBancario param);
	
	/**
	 * 
	 * @param param
	 * @return
	 */
	@WebMethod
	public ResultObtenerDatosBancariosPersona obtenerDatosBancariosPersona(ParamObtenerDatosBancariosPersona param);
	
	/**
	 * 
	 * @param param
	 * @return
	 */
	@WebMethod
	public ResultListaBancosPersona listaBancosPersona(ParamListaBancosPersona param);
	
	/**
	 * 
	 * @param param
	 * @return
	 */
	@WebMethod
	public ResultCondicion tienePermisoDatosBancarios(ParamTienePermisoDatosBancarios param);
	
	/**
	 * Devuelve los distintos radios (códigos postales), según la localidad, el departamento y el país
	 * @return
	 */
	@WebMethod	
	public ResultRadio listaRadio (ParamRadio param);
	
	@WebMethod
	public ResultAltaCliente altaCliente(ParamAltaCliente param);
	
	@WebMethod
	public ResultObtenerClientes obtenerClientes(ParamObtenerClientes param);
	
	@WebMethod
	public ResultObtenerDetalleConsultaClientes obtenerDetalleConsultaClientes(ParamObtenerDetalleConsultaClientes param);
	
	@WebMethod
	public ResultExportarClientes exportarClientes(ParamExportarClientes param);

}
