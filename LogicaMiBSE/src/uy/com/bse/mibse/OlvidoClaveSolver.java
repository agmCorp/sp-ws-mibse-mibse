package uy.com.bse.mibse;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import uy.com.bse.guc.GUCRemote;
import uy.com.bse.guc.interfaces.ParamValidar;
import uy.com.bse.guc.interfaces.ResultOlvidoClave;
import uy.com.bse.guc.interfaces.ResultValidar;
import uy.com.bse.mibse.persistencia.ServiciosMiBsePersist;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.dato.ServiciosError;
import uy.com.bse.utilitario.logica.AbstractSolver;

public final class OlvidoClaveSolver extends AbstractSolver {

	private static final Logger LOG = LogManager.getLogger(OlvidoClaveSolver.class);

	ResultGenerico nuevaClave(ParamOlvidoClave param) {

		ResultGenerico resultado = null;
		try {
			ParamValidar paramValidar = new ParamValidar();
			paramValidar.setUsuario(param.getUsuario());
			ResultValidar rvalidado = getEJBManager().validar(paramValidar);
			if (!rvalidado.getHayError().booleanValue() && rvalidado.getUser() != null) {
				ParamObtenerMailCliente parammail = new ParamObtenerMailCliente();
				parammail.setClave(param.getClave());
				parammail.setUsuario(param.getUsuario());
				ResultObtenerMailCliente mailResult = new ServiciosMiBsePersist().obtenerMailCliente(parammail);
				if (!mailResult.getHayError().booleanValue()) {
					if (mailResult.getMail() != null && !mailResult.getMail().isEmpty()) {
						uy.com.bse.guc.interfaces.ParamOlvidoClave copy = new uy.com.bse.guc.interfaces.ParamOlvidoClave();
						copy.setClave(param.getClave());
						copy.setUsuario(param.getUsuario());
						copy.setEmail(mailResult.getMail());
						resultado = getEJBManager().olvidoClave(copy);
					} else {
						resultado = new ResultGenerico();
						resultado.setHayError(Boolean.TRUE);
						resultado.setError(new ServiciosError(81,"No se ha determinado el correo electr\u00f3nico del cliente."));
					}
				} else {
					resultado = new ResultOlvidoClave();
					resultado.setHayError(Boolean.TRUE);
					resultado.setError(mailResult.getError());
				}
			} else {
				resultado = new ResultObtenerMailCliente();
				resultado.setError(new ServiciosError(80,"Usuario no registrado"));
				resultado.setHayError(Boolean.TRUE);
			}
		} catch (NamingException e) {
			LOG.error("Error al obtener el EJB del GUC", e);
		}

		return checkNull(resultado);
	}

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultGenerico();
	}

	@Override
	protected ResultGenerico procesoLogica(ParamGenerico param) {
		return checkNull(nuevaClave((ParamOlvidoClave) param));
	}

	public static GUCRemote getEJBManager() throws NamingException {
		final InitialContext ctx = new InitialContext();
		return (GUCRemote) ctx.lookup("global/GUC/GUCEJB/GUC!uy.com.bse.guc.GUCRemote");
	}

}
