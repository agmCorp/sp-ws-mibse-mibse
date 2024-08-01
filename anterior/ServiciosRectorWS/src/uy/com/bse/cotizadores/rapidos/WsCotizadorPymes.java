package uy.com.bse.cotizadores.rapidos;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.jws.WebService;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import uy.com.bse.cotizadores.pymes.ParamCalcularPymes;
import uy.com.bse.cotizadores.pymes.ParamNuevaCotizacionPymes;
import uy.com.bse.cotizadores.pymes.ResultCalcularPymes;
import uy.com.bse.cotizadores.pymes.ResultNuevaCotizacionPymes;
import uy.com.bse.serviciosEJB.CotizadorPymesLocal;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.dato.ServiciosError;
import uy.com.bse.utilitario.excepcion.ErrorResolver;
import uy.com.bse.utilitario.excepcion.Values;
import uy.com.bse.utilitario.log.Logueo;
import uy.com.bse.utilitario.properties.EARPropertiesManager;
import uy.com.bse.utilitario.util.MailBuilder;

@WebService(endpointInterface = "uy.com.bse.cotizadores.rapidos.IWsCotizadorPymes", serviceName = "WsCotizadorPymes")
public class WsCotizadorPymes implements IWsCotizadorPymes {

	private static final Logger LOG = LogManager.getLogger(WsCotizadorPymes.class);

	public ResultNuevaCotizacionPymes nuevaCotizacionPymes(ParamNuevaCotizacionPymes param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotizadorPymes.class);
		logueo.setMetodo("nuevaCotizacionPymes");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		ResultNuevaCotizacionPymes datos = new ResultNuevaCotizacionPymes();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesCotizadorPymes validar = new ValidacionesCotizadorPymes();
			ServiciosError error = validar.validarNuevaCotizacionPymes(param);

			if (error == null) {
				datos = WsCotizadorPymes.getEJBOperacionesManager().nuevaCotizacionPymes(param);
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

	public ResultCalcularPymes calcularPymes(ParamCalcularPymes param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotizadorPymes.class);
		logueo.setMetodo("calcularPymes");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Cotizacion", param.getNumCotizacion());

		ResultCalcularPymes datos = new ResultCalcularPymes();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesCotizadorPymes validar = new ValidacionesCotizadorPymes();
			ServiciosError error = validar.validarCalcularPymes(param);

			if (error == null) {
				datos = WsCotizadorPymes.getEJBOperacionesManager().calcularPymes(param);
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




	public static CotizadorPymesLocal getEJBOperacionesManager() throws NamingException {
		final InitialContext ctx = new InitialContext();
		return (CotizadorPymesLocal) ctx.lookup("global/ServiciosRector/IRectorEJB/CotizadorPymes!uy.com.bse.serviciosEJB.CotizadorPymesLocal");
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


	private void enviarMail( Logueo logueo) {
		try {
			EARPropertiesManager manager= new EARPropertiesManager("configServiciosRector.properties");
			MailBuilder.create().sentToDefaultConfigureMail( manager.obtenerValor("serviciosRector.mail.soporte"),manager.obtenerValor("serviciosRector.ccmail.soporte"),logueo.getMensaje(), "SERVICIO COTIZADORVehiculo Error  BSE Ambiente: " + InetAddress.getLocalHost().getHostName());
		} catch (UnknownHostException e) {
			LOG.error("Error en m\u00e9todo enviarMail", e);
		}
	}

	
	

}