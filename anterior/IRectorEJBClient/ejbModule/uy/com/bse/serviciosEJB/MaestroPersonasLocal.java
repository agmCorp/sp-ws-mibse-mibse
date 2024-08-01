package uy.com.bse.serviciosEJB;



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

public interface MaestroPersonasLocal {
	
	
	/**
	 * Llama al método del mismo nombre de persistencia
	 * @param param
	 * @return Una lista de personas fisicas y una de personas juridicas
	 */
	public ResultObtenerPersonas obtenerPersonas(ParamObtenerPersonas param);
	
	/**
	 * Llama al método del mismo nombre de persistencia - Hace commit
	 * @param param
	 * @return Un objeto personaFisica
	 */
	public ResultPersonaFisica altaPersonaFisica (ParamDatosPersonaFisica param);	

	/**
	 * Llama al método del mismo nombre de persistencia - Hace commit
	 * @param param
	 * @return Un objeto personaJuridica
	 */
	public ResultPersonaJuridica altaPersonaJuridica (ParamDatosPersonaJuridica param);

	/**
	 * Llama al método del mismo nombre de persistencia - Hace commit
	 * @param param
	 * @return Un objeto personaFisica
	 */
	public ResultPersonaFisica modificarPersonaFisica (ParamDatosPersonaFisica param);

	/**
	 * Llama al método del mismo nombre de persistencia - Hace commit
	 * @param param
	 * @return Un objeto personaJuridica
	 */
	public ResultPersonaJuridica modificarPersonaJuridica (ParamDatosPersonaJuridica param);

	/**
	 * Llama al método del mismo nombre de persistencia - Hace commit
	 * @param param
	 * @return Codigo de error y descripcion en caso de que sea necesario
	 */
	public ResultBajaPersona bajaPersona (ParamDatosBajaPersona param);

	/**
	 * Llama al método del mismo nombre de persistencia - Hace commit
	 * @param param
	 * @return Una lista de comunicaciones
	 */
	public ResultComunicacion altaComunicaciones (ParamAltaComunicacion param);

	/**
	 * Llama al método del mismo nombre de persistencia - Hace commit
	 * @param param
	 * @return una lista de comunicaciones
	 */
	public ResultComunicacion modificarComunicaciones (ParamModificarComunicacion param);

	/**
	 * Llama al método del mismo nombre de persistencia - Hace commit
	 * @param param
	 * @return Codigo de error y descripcion en caso de que sea necesario
	 */
	public ResultComunicacionEC bajaComunicaciones (ParamBajaComunicacion param);

	/**
	 * Llama al método del mismo nombre de persistencia - Hace commit
	 * @param param
	 * @return Codigo de error y descripcion en caso de que sea necesario
	 */
	public ResultRolCliente modificarDatosRolCliente (ParamDatosRolCliente param);

	/**
	 * Llama al método del mismo nombre de persistencia - Hace commit
	 * @param param
	 * @return Un objeto con la direccion creada
	 */
	public ResultDireccion altaDireccion (ParamDatosDireccion param);

	/**
	 * Llama al método del mismo nombre de persistencia - Hace commit
	 * @param param
	 * @return Un objeto con la direccion modificada
	 */
	public ResultDireccion modificarDireccion (ParamDatosDireccion param);

	/**
	 * Llama al método del mismo nombre de persistencia - Hace commit
	 * @param param
	 * @return Codigo de error y descripcion en caso de que sea necesario
	 */
	public ResultBajaDireccion bajaDireccion (ParamDatosBajaDireccion param);

	/**
	 * Llama al método del mismo nombre de persistencia
	 * @param param
	 * @return Un objeto de persona fisica
	 */
	public ResultPersonaFisica obtenerDatosPersonaFisica(ParamPersona param);	
	
	/**
	 * Llama al método del mismo nombre de persistencia
	 * @param param
	 * @return Un objeto de persona juridica
	 */
	public ResultPersonaJuridica obtenerDatosPersonaJuridica(ParamPersona param);	
	
	/**
	 * Llama al método del mismo nombre de persistencia
	 * @param param
	 * @return Una lista de direcciones
	 */
	public ResultObtenerDirecciones obtenerDireccionesDeUnaPersona(ParamPersona param);	
	
	/**
	 * Llama al método del mismo nombre de persistencia
	 * @param param
	 * @return Retorna un objeto direccionEC
	 */
	public ResultDireccion obtenerDatosDeUnaDireccion(ParamObtenerDireccion param);	
	
	/**
	 * Llama al método del mismo nombre de persistencia
	 * @param param
	 * @return Una lista de roles
	 */
	public ResultRoles obtenerRolesDeUnaPersona(ParamPersona param);	
	
	/**
	 * Llama al método del mismo nombre de persistencia
	 * @param param
	 * @return Una lista de datos de comunicaciones
	 */
	public ResultComunicaciones obtenerComunicacionesDeUnaPersona(ParamPersona param);
	
	/**
	 * Llama al método del mismo nombre de persistencia - Hace commit
	 * @param param
	 * @return  Codigo de error y descripcion en caso de que sea necesario
	 */
	public ResultActivarPersona activarPersona(ParamDatosActivarPersona param);
	
	
	/**
	 * Obtiene todas las personas que son clientes
	 * @param param
	 * @return
	 */
	public ResultObtenerPersonasClientes obtenerPersonasClientes(ParamObtenerPersonasClientes param);
	
	/**
	 *Da de alta a una persona como cliente en el sistema
	 * @param param
	 * @return
	 */
	public ResultAltaRolCliente altaRolCliente (ParamAltaRolCliente param);
	
	/**
	 *Devuelve codigo cliente/numero de persona dependiendo de los datos de entrada
	 * @param param
	 * @return
	 */	
	public ResultObtenerPersonasAlta obtenerPersonasAlta (ParamObtenerPersonasAlta param);
	
	
	public ResultPersonaCorredor obtenerPersonaCorredor (ParamPersonaCorredor param);
	
	/**
	 *Devuelve las pólizas asociadas a una dirección
	 * @param param
	 * @return
	 */	
	public ResultPolizasAsociadas obtenerPolizasAsociadas (ParamPolizasAsociadas param);
	
	/**Busca la persona en el sistema, si existe como cliente devuelve su código de cliente y número de persona, 
	 * si existe como persona devuelve número de persona, si no, no devuelve nada.
	 * @param param
	 * @return
	 */
	public ResultExistePersona existePersona (ParamExistePersona param);

	public ResultDatoBancario modificarDatosBancarios(ParamModificarDatoBancario param);

	public ResultObtenerDatosBancariosPersona obtenerDatosBancariosPersona(ParamObtenerDatosBancariosPersona param);

	public ResultDatoBancario borrarDatosBancarios(ParamBorrarDatoBancario param);

	public ResultListaBancosPersona listaBancosPersona(ParamListaBancosPersona param);
	
	public ResultCondicion tienePermisoDatosBancarios(ParamTienePermisoDatosBancarios param);
	
	
	/**
	 * Pasarela para consultar la lista de profesiones
	 * 
	 * @param param
	 * @return Un objeto con una lista de profesiones
	 */
	public ResultListaProfesiones listaProfesiones(ParamProfesiones param);

	/**
	 * Pasarela para consultar la lista de motivos de baja
	 * 
	 * @param param
	 * @return Un objeto con una lista de motivos de baja
	 */
	public ResultListaMotivosBaja listaMotivosBaja(ParamMotivosBaja param);

	/**
	 * Pasarela para consultar la lista de estados civiles
	 * 
	 * @param param
	 * @return Un objeto con una lista de estados civil
	 */
	public ResultListaEstadoCivil listaEstadoCivil(ParamEstadoCivil param);

	/**
	 * Pasarela para consultar la lista de sexos
	 * 
	 * @param param
	 * @return
	 */
	public ResultListaSexo listaSexo(ParamListaSexo param);

	/**
	 * Pasarela para consultar la lista de tipo de direccion
	 * 
	 * @param param
	 * @return Un objeto con una lista de direcciones
	 */
	public ResultListaTipoDireccion listaTipoDireccion(ParamTipoDireccion param);

	/**
	 * Pasarela para consultar la lista de tipos de comunicacion
	 * 
	 * @param param
	 * @return Un objeto con una lista de tipos de comunicaciones
	 */
	public ResultListaTipoComunicaciones listaTipoComunicaciones(ParamTipoComunicaciones param);

	/**
	 * Pasarela para consultar la lista de tipo de personeria
	 * 
	 * @param param
	 * @return Un objeto con una lista de tipos de personeria
	 */
	public ResultListaTipoPersoneria listaTipoPersoneria(ParamTipoPersoneria param);
	
	public ResultAltaCliente altaCliente(ParamAltaCliente param);

	/*Consulta nueva de clientes devuelve solo el cabezal de los clientes
	 **/
	public ResultObtenerClientes obtenerClientes(ParamObtenerClientes param);

	public ResultObtenerDetalleConsultaClientes obtenerDetalleConsultaClientes(ParamObtenerDetalleConsultaClientes param);

	public ResultPolizasAsociadasClientes obtenerPolizasAsociadasClientes(ParamPolizasAsociadasClientes param);

	public ResultExportarClientes exportarClientes(ParamExportarClientes param);
}
