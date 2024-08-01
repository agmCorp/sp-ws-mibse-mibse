package uy.com.bse.genericos.ws;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import uy.com.bse.servicios.rector.emision.ParamEmision;
import uy.com.bse.servicios.rector.emision.ParamValidarCotizacion;
import uy.com.bse.servicios.rector.interfaces.ParamCodigoPostal;
import uy.com.bse.servicios.rector.interfaces.ParamContactoWeb;
import uy.com.bse.servicios.rector.interfaces.ParamListaProductoresAsignados;
import uy.com.bse.servicios.rector.interfaces.ParamListaProductoresAsignadosVigentes;
import uy.com.bse.servicios.rector.interfaces.ParamListaTipoDoc;
import uy.com.bse.servicios.rector.interfaces.ParamLocalidades;
import uy.com.bse.servicios.rector.interfaces.ParamTipoUsuario;
import uy.com.bse.serviciosEJB.SeguridadServiciosRemote;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ServiciosError;
import uy.com.bse.utilitario.excepcion.Values;
import uy.com.bse.utilitario.seguridad.ParamLogin;
import uy.com.bse.utilitario.seguridad.ResultValidar;
import uy.com.bse.utilitario.servicios.ValidacionesAbstract;
import uy.ibm.responseTimeLogger.RTimeLogger;

public class ValidacionesServiciosRector extends ValidacionesAbstract {

	public ServiciosError validarTipoDoc(ParamListaTipoDoc param) {
		String claveDeError = null;
		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getAccion() == null || param.getAccion().equals("")) {
			claveDeError = Values.NULL;
		}
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarOrganismos(ParamGenerico param) {
		String claveDeError = null;
		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		}
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarActividades(ParamGenerico param) {
		String claveDeError = null;
		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		}
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarPaises(ParamGenerico param) {
		String claveDeError = null;
		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		}
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarDepartamentos(ParamGenerico param) {
		String claveDeError = null;
		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		}
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarCodigoPostal(ParamCodigoPostal param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		}

		if (param.getCodPais() == null || !this.isNumeric(param.getCodPais())) {
			claveDeError = Values.VALNUMERICO;
		} else if (param.getCodDepartamento() == null || !this.isNumeric(param.getCodDepartamento())) {
			claveDeError = Values.VALNUMERICO;
		} else if (param.getLocalidad() != null && !this.isNumeric(param.getLocalidad())) {
			claveDeError = Values.NULL;
		} else if (param.getNumPostal() != null && !this.isNumeric(param.getNumPostal())) {
			claveDeError = Values.NULL;
		} else if (param.getUsuario() == null || param.getUsuario().equals("null") || param.getUsuario().equals("")) {
			claveDeError = Values.NULL;
		} else if (param.getClave() == null || param.getClave().equals("null") || param.getClave().equals("")) {
			claveDeError = Values.NULL;
		}
		return getErrorsByClave(claveDeError);

	}

	public ServiciosError validarLocalidades(ParamLocalidades param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		}

		if (param.getDepartamento() != null && !param.getDepartamento().equals("") && !this.isNumeric(param.getDepartamento())) {
			claveDeError = Values.VALNUMERICO;
		} else if (param.getPais() != null && !param.getPais().equals("") && !this.isNumeric(param.getPais())) {
			claveDeError = Values.VALNUMERICO;
		} else if (param.getUsuario() == null || param.getUsuario().equals("null") || param.getUsuario().equals("")) {
			claveDeError = Values.NULL;
		} else if (param.getClave() == null || param.getClave().equals("null") || param.getClave().equals("")) {
			claveDeError = Values.NULL;
		}
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarSucursales(ParamGenerico param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		}

		if (param.getUsuario() == null || param.getUsuario().equals("null") || param.getUsuario().equals("")) {
			claveDeError = Values.NULL;
		} else if (param.getClave() == null || param.getClave().equals("null") || param.getClave().equals("")) {
			claveDeError = Values.NULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarAltaContactoWeb(ParamContactoWeb param) {
		String claveDeError = null;
		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getApellido() == null || param.getApellido().equals("")) {
			claveDeError = Values.NULL;
		} else if (param.getEmail() == null || param.getEmail().equals("")) {
			claveDeError = Values.NULL;
		} else if (param.getNombre() == null || param.getNombre().equals("")) {
			claveDeError = Values.NULL;
		} else if (param.getNumCotizacion() == null ) {
			claveDeError = Values.NULL;
		} else if (param.getNumDocumento() == null || param.getNumDocumento().equals("")) {
			claveDeError = Values.NULL;
		} else if (param.getUsuario() == null || param.getUsuario().equals("null") || param.getUsuario().equals("")) {
			claveDeError = Values.NULL;
		} else if (param.getClave() == null || param.getClave().equals("null") || param.getClave().equals("")) {
			claveDeError = Values.NULL;
		}

		if (claveDeError == null) {
			for (int i = 0; i < param.getNoticia().size(); i++) {
				if (param.getNoticia().get(i) == null) {
					param.getNoticia().remove(i);
					i--;
				}
			}
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarValidarCotizacion(ParamValidarCotizacion param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getNumCotizacion() == null) {
			claveDeError = Values.NULL;
		}
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarEmitirPoliza(ParamEmision param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getNumCotizacion() == null) {
			claveDeError = Values.NULL;
		}
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarLogin(ParamLogin param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		}
		return getErrorsByClave(claveDeError);
	}

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
			salida = ((result!=null) && (!result.getHayError()));
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return salida;
	}

	public ServiciosError validarListaProductoresAsignados(
			ParamListaProductoresAsignados param) {
		// TODO Auto-generated method stub
		return null;
	}

	public ServiciosError validarListaProductoresAsignadosVigentes(
			ParamListaProductoresAsignadosVigentes param) {
		// TODO Auto-generated method stub
		return null;
	}

	public ServiciosError validarTipoUsuario(ParamTipoUsuario param) {
		// TODO Auto-generated method stub
		return null;
	}
}
