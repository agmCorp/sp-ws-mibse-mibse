package uy.com.bse.mibse;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import uy.com.bse.guc.GUCRemote;
import uy.com.bse.guc.interfaces.ParamValidar;
import uy.com.bse.guc.interfaces.ResultValidar;
import uy.com.bse.mibse.persistencia.ServiciosMiBsePersist;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.dato.ServiciosError;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ProCarta2Solver extends AbstractSolver {

	private static final Logger LOG = LogManager.getLogger(ProCarta2Solver.class);

	@Override
	public ResultGenerico procesoLogica(ParamGenerico param) {
		ParamGenerico paramValidar = new ParamValidar();
		paramValidar.setUsuario(param.getUsuario());
		ResultGenerico res = null;
		ResultProCarta resultado = new ResultProCarta();
		try {
			ResultValidar rvalidado = getEJBManager().validar((ParamValidar) paramValidar);
			if (!rvalidado.getHayError().booleanValue() && rvalidado.getUser() != null) {
				res = checkNull(new ServiciosMiBsePersist()
						.proCarta2((ParamProCarta2) param));
				if (!res.getHayError().booleanValue()) {
					resultado = (ResultProCarta) res;
				} else {
					resultado.setError(res.getError());
					resultado.setHayError(res.getHayError());
				}
			} else {

				resultado.setError(new ServiciosError(80, "Usuario no registrado"));
				resultado.setHayError(Boolean.TRUE);
			}
		} catch (NamingException e) {
			LOG.error("Error al obtener el EJB del GUC", e);
		}

		return checkNull(resultado);
	}

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultObtenerMailCliente();
	}

	public static GUCRemote getEJBManager() throws NamingException {
		final InitialContext ctx = new InitialContext();
		return (GUCRemote) ctx.lookup("global/GUC/GUCEJB/GUC!uy.com.bse.guc.GUCRemote");
	}

}
