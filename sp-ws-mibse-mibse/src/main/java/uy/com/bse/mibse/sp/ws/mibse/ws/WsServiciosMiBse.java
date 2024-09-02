package uy.com.bse.mibse.sp.ws.mibse.ws;

import uy.com.bse.mibse.sp.ws.mibse.service.MiBSEService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import uy.com.bse.mibse.sp.ws.mibse.utilitario.dato.ServiciosError;
import uy.com.bse.mibse.sp.ws.mibse.model.dto.ResultObtenerComunicacionesCliente;
import uy.com.bse.mibse.sp.ws.mibse.utilitario.log.Logueo;
import uy.com.bse.mibse.sp.ws.mibse.utilitario.exception.Values;
import uy.com.bse.mibse.sp.ws.mibse.utilitario.exception.ErrorResolver;
import uy.com.bse.mibse.sp.ws.mibse.utilitario.dato.ResultGenerico;
import uy.com.bse.mibse.sp.ws.mibse.model.dto.ParamObtenerComunicacionesCliente;
//import uy.com.bse.mibse.sp.ws.mibse.utilitario.persistencia.Persistencia;


@Component
public class WsServiciosMiBse implements IWsServiciosMiBse {

	private final MiBSEService miBSEService;

    @Autowired
    public WsServiciosMiBse(MiBSEService miBSEService) {
        this.miBSEService = miBSEService;
    }

	private static Logger LOG = LogManager.getLogger(WsServiciosMiBse.class);

	public ResultObtenerComunicacionesCliente obtenerComunicacionesCliente(ParamObtenerComunicacionesCliente param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosMiBse.class);
		logueo.setMetodo("obtenerComunicacionesCliente");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		ResultObtenerComunicacionesCliente datos = new ResultObtenerComunicacionesCliente();
		LOG.info(logueo.getSoloParametros());
		try {
			// Valida user and pass :
			ValidacionesMiBse validar = new ValidacionesMiBse();
			// TODO FIXME decomentar:
			//ServiciosError error = validar.validarObtenerComunicacionesCliente(param);
			ServiciosError error = null;	
			// Si no hay errores en la validación, invoco al service 
			if (error == null) {
				datos = miBSEService.obtenerComunicacionesCliente(param);
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
		LOG.error(logueo.getMensaje(), e);
		// TODO implementar enviarMail
		//enviarMail(logueo);
		return claveError;
	}

	private void finallyBlock(String claveError, ResultGenerico datos) {
		if (claveError != null) {
			datos.setHayError(Boolean.TRUE);
			datos.setError(ErrorResolver.getError(claveError));
		}
	}
/*
	public static MiBseLocal getEJBManager() throws NamingException {
		final InitialContext ctx = new InitialContext();
		return (MiBseLocal) ctx.lookup("global/Mibse/MiBSEEJB/MiBse!uy.com.bse.MiBseLocal");
	}

	// FIXME OIGRES... ESTO POR PRUEBAS... NO PONER EN PRODUCCION
	private void enviarMail(Logueo logueo) {
		try {
			EARPropertiesManager manager = new EARPropertiesManager("configMibse.properties");
			MailBuilder.create().sentToDefaultConfigureMail(manager.obtenerValor("mibse.mail.soporte"),
					manager.obtenerValor("mibse.ccmail.soporte"), logueo.getMensaje(),
					"SERVICIO MIBSE Error  BSE Ambiente: " + InetAddress.getLocalHost().getHostName());
		} catch (UnknownHostException e) {
			LOG.debug("No se encontro el nombre de Host", e);
		}
	}
		 */

}
