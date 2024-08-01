package uy.com.bse.maestro.ws;

import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import uy.com.bse.clientes.consultas.ParamExportarClientes;
import uy.com.bse.clientes.consultas.ParamObtenerClientes;
import uy.com.bse.clientes.consultas.ParamObtenerDetalleConsultaClientes;
import uy.com.bse.clientes.consultas.ParamPolizasAsociadasClientes;
import uy.com.bse.maestro.personas.comunicaciones.ParamAltaComunicacion;
import uy.com.bse.maestro.personas.comunicaciones.ParamBajaComunicacion;
import uy.com.bse.maestro.personas.comunicaciones.ParamModificarComunicacion;
import uy.com.bse.maestro.personas.consultas.ParamObtenerPersonas;
import uy.com.bse.maestro.personas.consultas.ParamObtenerPersonasClientes;
import uy.com.bse.maestro.personas.consultas.ParamPersona;
import uy.com.bse.maestro.personas.consultas.ParamPolizasAsociadas;
import uy.com.bse.maestro.personas.datosbancarios.ParamBorrarDatoBancario;
import uy.com.bse.maestro.personas.datosbancarios.ParamListaBancosPersona;
import uy.com.bse.maestro.personas.datosbancarios.ParamModificarDatoBancario;
import uy.com.bse.maestro.personas.datosbancarios.ParamObtenerDatosBancariosPersona;
import uy.com.bse.maestro.personas.datosbancarios.ParamTienePermisoDatosBancarios;
import uy.com.bse.maestro.personas.domicilio.ParamDatosBajaDireccion;
import uy.com.bse.maestro.personas.domicilio.ParamDatosDireccion;
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
import uy.com.bse.maestro.personas.personas.ParamAltaCliente;
import uy.com.bse.maestro.personas.personas.ParamAltaRolCliente;
import uy.com.bse.maestro.personas.personas.ParamDatosActivarPersona;
import uy.com.bse.maestro.personas.personas.ParamDatosBajaPersona;
import uy.com.bse.maestro.personas.personas.ParamDatosPersonaFisica;
import uy.com.bse.maestro.personas.personas.ParamDatosPersonaJuridica;
import uy.com.bse.maestro.personas.personas.ParamExistePersona;
import uy.com.bse.maestro.personas.personas.ParamObtenerDireccion;
import uy.com.bse.maestro.personas.rol.ParamDatosRolCliente;
import uy.com.bse.serviciosEJB.SeguridadServiciosRemote;
import uy.com.bse.utilitario.dato.ServiciosError;
import uy.com.bse.utilitario.excepcion.Values;
import uy.com.bse.utilitario.properties.PropertiesManager;
import uy.com.bse.utilitario.seguridad.ResultValidar;
import uy.com.bse.utilitario.servicios.ValidacionesAbstract;
import uy.ibm.responseTimeLogger.RTimeLogger;

public class ValidacionesMaestroPersonas extends ValidacionesAbstract {
	private final String propertiesMaestropersonas = "maestroPersonas.properties";
	
	
	public ServiciosError validarListaEstadoCivil(ParamEstadoCivil param) {
		String claveDeError = null;
		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		}
		return getErrorsByClave(claveDeError);
	}
	

	public ServiciosError validarListaDirecciones(ParamTipoDireccion param) {
		String claveDeError = null;
		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		}
		return getErrorsByClave(claveDeError);
	}
	
	public ServiciosError validarListaSexo(ParamListaSexo param) {
		String claveDeError = null;
		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		}
		return getErrorsByClave(claveDeError);
	}
	
	public ServiciosError validarListaTipoComunicaciones(ParamTipoComunicaciones param) {
		String claveDeError = null;
		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		}
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarListaTipoPersoneria(ParamTipoPersoneria param) {
		String claveDeError = null;
		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		}
		return getErrorsByClave(claveDeError);
	}
	
	public ServiciosError validarListaBancosPersona(ParamListaBancosPersona param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		}
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarListaMotivosBaja(ParamMotivosBaja param) {
		String claveDeError = null;
		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		}
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarListaProfesiones(ParamProfesiones param) {
		String claveDeError = null;
		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		}
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarAltaPersonaFisica(ParamDatosPersonaFisica param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getTipoDocumento() == null || param.getTipoDocumento().equals("") || param.getTipoDocumento().equals("null")) {
			claveDeError = Values.NULL;
		} else if (param.getNumDocumento() == null || param.getNumDocumento().equals("") || param.getNumDocumento().equals("null")) {
			claveDeError = Values.NULL;
		} else if (param.getApellido() == null || param.getApellido().equals("") || param.getApellido().equals("null")) {
			claveDeError = Values.NULL;
		} else if (param.getNombre() == null || param.getNombre().equals("") || param.getNombre().equals("null")) {
			claveDeError = Values.NULL;
		} else if (param.getSexo() == null || param.getSexo().equals("") || param.getSexo().equals("null")) {
			claveDeError = Values.NULL;
		}
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarAltaPersonaJuridica(ParamDatosPersonaJuridica param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getRuc() == null || param.getRuc().equals("") || param.getRuc().equals("null")) {
			claveDeError = Values.NULL;
		} else if (param.getRazonSocial() == null || param.getRazonSocial().equals("") || param.getRazonSocial().equals("null")) {
			claveDeError = Values.NULL;
		}
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarModificarPersonaFisica(ParamDatosPersonaFisica param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getSexo() == null || (param.getSexo().equals(""))) {
			claveDeError = Values.NULL;
		} else if (param.getFechaNacimiento() == null || param.getFechaNacimiento().equals("")) {
			claveDeError = Values.NULL;
		} else if (param.getCodPersona() == null) {
			claveDeError = Values.NULL;
		} else if (param.getApellido() == null || (param.getApellido().equals(""))) {
			claveDeError = Values.NULL;
	
		} else if (param.getNombre() == null || (param.getNombre().equals(""))) {
			claveDeError = Values.NULL;
		} else if (param.getNumDocumento() == null || (param.getNumDocumento().equals(""))) {
			claveDeError = Values.NULL;
		} else if (param.getTipoDocumento() == null || (param.getTipoDocumento().equals(""))) {
			claveDeError = Values.NULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarModificarPersonaJuridica(ParamDatosPersonaJuridica param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getRuc() == null || (param.getRuc().equals(""))) {
			claveDeError = Values.NULL;
		} else if (param.getRazonSocial() == null || (param.getRazonSocial().equals(""))) {
			claveDeError = Values.NULL;
		} else if (param.getCodPersona() == null ) {
			claveDeError = Values.NULL;
		} else if (param.getCodDireccion() == null) {
			claveDeError = Values.NULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarBajaPersona(ParamDatosBajaPersona param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getTipoOperacion() == null || param.getTipoOperacion().equals("") || param.getTipoOperacion().equals("null")) {
			claveDeError = Values.NULL;
		} else if (param.getCodPersona() == null) {
			claveDeError = Values.NULL;
		} else {
			// FIXME OIGRES, que HAGO CON ESTO
			PropertiesManager properties = new PropertiesManager();
			properties.setNombreArchivo(this.propertiesMaestropersonas);
			String baja = properties.obtenerValor("bajaPersona");
			String modificacion = properties.obtenerValor("modifPersona");

			if (param.getTipoOperacion().equals(baja)) {
				if (param.getFechaBaja() != null && (param.getFechaBaja().equals("") || param.getFechaBaja().equals("null"))) {
					claveDeError = Values.NULL;
				} else if (param.getCodMotivoBaja() == null) {
					claveDeError = Values.NULL;
				}
			} else if (param.getTipoOperacion().equals(modificacion)) {
				if (param.getFechaBaja() == null && param.getCodMotivoBaja() == null) {
					claveDeError = Values.NULL;
				}
			} else {
				claveDeError = Values.NULL;
			}
		}
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarAltaComunicaciones(ParamAltaComunicacion param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getCodPersona() == null) {
			claveDeError = Values.NULL;
		} else if (param.getFechaActualizacion() == null) {
			claveDeError = Values.NULL;
		} else if (param.getTipoComunicacion() == null) {
			claveDeError = Values.NULL;
		} else if (param.getValorComunicacion() == null || param.getValorComunicacion().equals("")) {
			claveDeError = Values.NULL;
		}

		return getErrorsByClave(claveDeError);
	}

	
	public ServiciosError validarModificarComunicaciones(ParamModificarComunicacion param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getCodPersona() == null) {
			claveDeError = Values.NULL;
		} else if (param.getCodComunicacion() == null) {
			claveDeError = Values.NULL;
		} else if (param.getFechaActualizacion() == null) {
			claveDeError = Values.NULL;
		} else if (param.getTipoComunicacion() == null) {
			claveDeError = Values.NULL;
		} else if (param.getValorComunicacion() == null|| param.getValorComunicacion().equals("")) {
			claveDeError = Values.NULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarBajaComunicaciones(ParamBajaComunicacion param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getCodPersona() == null) {
			claveDeError = Values.NULL;
		} else if (param.getCodComunicacion() == null) {
			claveDeError = Values.NULL;
		}
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarModificarDatosRolCliente(ParamDatosRolCliente param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getCodPersona() == null) {
			claveDeError = Values.NULL;
		} else if (param.getCodCliente() == null) {
			claveDeError = Values.NULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarAltaDireccion(ParamDatosDireccion param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getCalle() == null || param.getCalle().equals("") || param.getCalle().equals("null")) {
			claveDeError = Values.NULL;
		} else if (param.getCodPersona() == null) {
			claveDeError = Values.NULL;
		} else if (param.getTipoDireccion() == null) {
			claveDeError = Values.NULL;
		} else if (param.getNumPostal() == null) {
			claveDeError = Values.NULL;
		}
		 //FIXME OIGRES, TEMA QUE EL NUMERO DE PUERTA ES OBLIGATORIO SOLO PARA MONTEVIDEO... Y NO TENEMOS LA INFO ACA EN EL PARAM
		/* else if (param.getNumeroPuerta() == null) {
			claveDeError = Values.NULL;
		}*/

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarModificarDireccion(ParamDatosDireccion param) {
		String claveDeError = null;

		/*
		 * if(param.getCalle() == null || param.getCalle().equals("") ||
		 * param.getCalle().equals("null")){ claveDeError = Values.NULL; }else
		 * if(param.getPiso() != null && (param.getPiso().equals("") ||
		 * param.getPiso().equals("null"))){ claveDeError = Values.NULL; }else
		 * if(param.getUnidad() != null && (param.getUnidad().equals("") ||
		 * param.getUnidad().equals("null"))){ claveDeError = Values.NULL; }else
		 * if(param.getApto() != null && (param.getApto().equals("") ||
		 * param.getApto().equals("null"))){ claveDeError = Values.NULL; }else
		 * if(param.getPadron() != null && (param.getPadron().equals("") ||
		 * param.getPadron().equals("null"))){ claveDeError = Values.NULL; }else
		 * if(param.getAclaraciones() != null &&
		 * (param.getAclaraciones().equals("") ||
		 * param.getAclaraciones().equals("null"))){ claveDeError = Values.NULL;
		 * }else
		 */if (param.getCodPersona() == null) {
			claveDeError = Values.NULL;
		} else if (param.getTipoDireccion() == null) {
			claveDeError = Values.NULL;
		} else if (param.getNumPostal() == null) {
			claveDeError = Values.NULL;
		} else if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getCodDireccion() == null) {
			claveDeError = Values.NULL;
		} else if (param.getCalle() == null) {
			claveDeError = Values.NULL;
		} 
		 //FIXME OIGRES, TEMA QUE EL NUMERO DE PUERTA ES OBLIGATORIO SOLO PARA MONTEVIDEO... Y NO TENEMOS LA INFO ACA EN EL PARAM
		/*else if (param.getNumeroPuerta() == null) {
			claveDeError = Values.NULL;
		}*/

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarBajaDireccion(ParamDatosBajaDireccion param) {
		String claveDeError = null;
		if (param.getCodDireccion() == null) {
			claveDeError = Values.NULL;
		} else if (param.getCodPersona() == null) {
			claveDeError = Values.NULL;
		} else if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		}
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerPersonas(ParamObtenerPersonas param) {
		String claveDeError = null;
		PropertiesManager properties = new PropertiesManager();
		properties.setNombreArchivo(this.propertiesMaestropersonas);
		if (param.getPrimerCriterio() == null || param.getPrimerCriterio().equals("") || param.getPrimerCriterio().equals("null")) {
			claveDeError = Values.NULL;
		} else if (param.getPrimerOperador() == null || param.getPrimerOperador().equals("") || param.getPrimerOperador().equals("null")) {
			claveDeError = Values.NULL;
		} else if (param.getPrimerValor() == null || param.getPrimerValor().equals("") || param.getPrimerValor().equals("null") || param.getPrimerValor().length() > 60) {
			claveDeError = Values.NULL;
		} else if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else {
			if (!this.validarCriterio(properties, param.getPrimerCriterio(), "criterios")) {
				claveDeError = "criterioNoExiste";
			} else if (!this.validarOperador(properties, param.getPrimerOperador())) {
				claveDeError = "operadorNoExiste";
			}
		}

		if (claveDeError == null && param.getSegundoCriterio() != null) {
			if (param.getSegundoCriterio().equals("") || param.getSegundoCriterio().equals("null")) {
				claveDeError = Values.NULL;
			} else if (param.getSegundoOperador() == null || param.getSegundoOperador().equals("") || param.getSegundoOperador().equals("null")) {
				claveDeError = Values.NULL;
			} else if (param.getSegundoValor() == null || param.getSegundoValor().equals("") || param.getSegundoValor().equals("null") || param.getSegundoValor().length() > 60) {
				claveDeError = Values.NULL;
			} else if (param.getOperadorLogico() == null || param.getSegundoValor().equals("") || param.getSegundoValor().equals("null")) {
				claveDeError = Values.NULL;
			} else {
				// FIXME OIGRES... ESTOS ERRORES
				if (!this.validarCriterio(properties, param.getSegundoCriterio(), "criterioDos")) {
					claveDeError = "criterioNoExiste";
				} else if (!this.validarOperador(properties, param.getSegundoOperador())) {
					claveDeError = "operadorNoExiste";
				} else if (!this.validarOperadorLogico(properties, param.getOperadorLogico())) {
					claveDeError = "operadorLogicoNoExiste";
				}
			}
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerDatosPersonaFisica(ParamPersona param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getCodPersona() == null) {
			claveDeError = Values.NULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError ValidarObtenerDatosPersonaJuridica(ParamPersona param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getCodPersona() == null) {
			claveDeError = Values.NULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerDireccionesDeUnaPersona(ParamPersona param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getCodPersona() == null) {
			claveDeError = Values.NULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerDatosDeUnaDirección(ParamObtenerDireccion param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getCodPersona() == null) {
			claveDeError = Values.NULL;
		} else if (param.getCodDireccion() == null) {
			claveDeError = Values.NULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerRolesDeUnaPersona(ParamPersona param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getCodPersona() == null) {
			claveDeError = Values.NULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerComunicacionesDeUnaPersona(ParamPersona param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getCodPersona() == null) {
			claveDeError = Values.NULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarActivarPersona(ParamDatosActivarPersona param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getCodPersona() == null) {
			claveDeError = Values.NULL;
		}

		return getErrorsByClave(claveDeError);
	}


	public ServiciosError validarObtenerPersonasClientes(ParamObtenerPersonasClientes param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		}

		return getErrorsByClave(claveDeError);
	}
	public ServiciosError validarObtenerDetalleConsultaClientes(ParamObtenerDetalleConsultaClientes param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		}

		return getErrorsByClave(claveDeError);
	}
	
	public ServiciosError validarObtenerClientes(ParamObtenerClientes param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarAltaRolCliente(ParamAltaRolCliente param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getNumPersona() == null || param.getNumPersona().equals("")) {
			claveDeError = Values.VALNULL;
		}
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerPersonasAlta(ParamObtenerPersonasAlta param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getTipoPersona() == null || param.getTipoPersona().equals("")) {
			claveDeError = Values.NULL;
		} else if (param.getTipoPersona().equals("1")) {
			if (param.getNumDocumento() == null || param.getTipoDoc() == null || param.getNumDocumento().equals("") || param.getTipoDoc().equals("")) {
				claveDeError = Values.NULL;
			}
		} else if (param.getTipoPersona().equals("2")) {
			if (param.getRut() == null || param.getRut().equals("")) {
				claveDeError = Values.NULL;
			}
		}

		return getErrorsByClave(claveDeError);
	}

	private boolean validarCriterio(PropertiesManager prop, String criterio, String numCriterio) {
		int cantidadCriterios = 0;

		String ret = prop.obtenerValor("Cantidad" + numCriterio);
		if (this.isNumeric(ret))
			cantidadCriterios = new Integer(ret).intValue();
		ret = prop.obtenerValor("cantidadOperadores");

		ArrayList<String> criterios = new ArrayList<String>();

		for (int i = 0; i < cantidadCriterios; i++)
			criterios.add(prop.obtenerValor(numCriterio + "-" + i + "-descripcion"));

		return criterios.contains(criterio);
	}

	private boolean validarOperador(PropertiesManager prop, String operador) {
		int cantidadOperadores = 0;
		String ret = prop.obtenerValor("cantidadOperadores");
		if (this.isNumeric(ret))
			cantidadOperadores = new Integer(ret).intValue();

		ArrayList<String> operadores = new ArrayList<String>();

		for (int i = 0; i < cantidadOperadores; i++)
			operadores.add(prop.obtenerValor("operador-" + i + "-descripcion"));

		return operadores.contains(operador);
	}

	private boolean validarOperadorLogico(PropertiesManager prop, String operador) {
		String and = prop.obtenerValor("operadorAnd");
		String or = prop.obtenerValor("operadorOr");

		return operador.equals(and) || operador.equals(or);
	}

	public ServiciosError validarObtenerPersonaCorredor(ParamPersonaCorredor param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getIdCorredor() == null || param.getIdCorredor().equals("")) {
			claveDeError = Values.NULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerPolizasAsociadas(ParamPolizasAsociadas param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getCodCliente() == null || param.getCodCliente().equals("")) {
			claveDeError = Values.NULL;
		} else if (param.getCodDireccion() == null ) {
			claveDeError = Values.NULL;
		}
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerPolizasAsociadasClientes(ParamPolizasAsociadasClientes param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getCodCliente() == null || param.getCodCliente().equals("")) {
			claveDeError = Values.NULL;
		} else if (param.getCodDireccion() == null ) {
			claveDeError = Values.NULL;
		}
		return getErrorsByClave(claveDeError);
	}
	public ServiciosError validarExistePersona(ParamExistePersona param) {
		String claveDeError = null;
		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getTipoDoc() != null) {
			if (param.getNumDocumento() == null) {
				claveDeError = Values.NULL;
			}
		}else if(param.getRut()!=null &&  param.getRut().length() == 11){ //PDP-1442
			param.setRut("0"+param.getRut());
		}
		return getErrorsByClave(claveDeError);

	}

	
	public ServiciosError validarDatosBancarios(ParamModificarDatoBancario param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getCodPersona() == null) {
			claveDeError = Values.NULL;
		}else if (param.getCodBanco() == null) {
			claveDeError = Values.NULL;
		}else if (param.getTipoCuenta() == null) {
			claveDeError = Values.NULL;
		}else if (param.getTipoTarjeta() == null) {
			claveDeError = Values.NULL;
		}else if (param.getNumcuenta() == null|| param.getNumcuenta().equals("")) {
			claveDeError = Values.NULL;
		}else if (param.getFechaVencimiento() == null|| param.getFechaVencimiento().equals("")) {
			claveDeError = Values.NULL;
		}
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarBorrarDatosBancarios(ParamBorrarDatoBancario param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getNumCons() == null) {
			claveDeError = Values.NULL;
		}else if (param.getCodPersona() == null) {
			claveDeError = Values.NULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerDatosBancariosPersona(ParamObtenerDatosBancariosPersona param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getCodCliente()==null) {
			claveDeError = Values.NULL;
		}

		return getErrorsByClave(claveDeError);
	}
	public ServiciosError validarTienePermisoDatosBancarios(
			ParamTienePermisoDatosBancarios param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getCodPermiso() == null) {
			claveDeError = Values.NULL;
		}

		return getErrorsByClave(claveDeError);
	}
	
	public ServiciosError validarListaRadio(ParamRadio param) {
		String claveDeError = null;
		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getCodPais() == null) {
			claveDeError = Values.NULL;
		} else if (param.getCodDepartamento() == null) {
			claveDeError = Values.NULL;
		}else if (param.getNumPostal()!=null && !isNumeric(param.getNumPostal())) {
			claveDeError = Values.VALNUMERICO;
		}else if (param.getCodCalle()!=null && !isNumeric(param.getCodCalle())) {
			claveDeError = Values.VALNUMERICO;
		}else if (param.getNumRadio()!=null && !isNumeric(param.getNumRadio())) {
			claveDeError = Values.VALNUMERICO;
		}else if (param.getNumPuerta()!=null && !isNumeric(param.getNumPuerta()) ) {
			claveDeError = Values.VALNUMERICO;
		}

		return getErrorsByClave(claveDeError);
	}
	
	//UTILES
	
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


	public ServiciosError validarAltaCliente(ParamAltaCliente param) {
		String claveDeError = null;
		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		}else{
			if (param.getTipoPersona()==null) {
				claveDeError = Values.NULL;
			} else {
				//FISICA
				if (param.getTipoPersona().equals(Integer.valueOf(1))) {
					if (param.getTipoDocumento() == null || param.getTipoDocumento().equals("") || param.getTipoDocumento().equals("null")) {
						claveDeError = Values.NULL;
					} else if (param.getNumDocumento() == null || param.getNumDocumento().equals("") || param.getNumDocumento().equals("null")) {
						claveDeError = Values.NULL;
					} else if (param.getApellido() == null || param.getApellido().equals("") || param.getApellido().equals("null")) {
						claveDeError = Values.NULL;
					} else if (param.getSexo() == null || param.getSexo().equals("") || param.getSexo().equals("null")) {
						claveDeError = Values.NULL;
					}
				} 
				//JURIDICA
				if (param.getTipoPersona().equals(Integer.valueOf(2))) {
					if (param.getRut() == null || param.getRut().equals("") || param.getRut().equals("null")) {
						claveDeError = Values.NULL;
					} else if (param.getRazonSocial() == null || param.getRazonSocial().equals("") || param.getRazonSocial().equals("null")) {
						claveDeError = Values.NULL;
					}
				}

			}
		}
		
		return getErrorsByClave(claveDeError);
	}


	public ServiciosError exportarClientes(ParamExportarClientes param) {
		
		String claveDeError = null;
		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} 
		return getErrorsByClave(claveDeError);
	}


}
