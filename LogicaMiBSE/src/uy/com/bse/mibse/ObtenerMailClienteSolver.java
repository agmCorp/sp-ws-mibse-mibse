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

public class ObtenerMailClienteSolver extends AbstractSolver {

	private static final Logger LOG = LogManager.getLogger(ObtenerMailClienteSolver.class);

	@Override
	public ResultGenerico procesoLogica(ParamGenerico param) {
		ParamGenerico paramValidar = new ParamValidar();
		paramValidar.setUsuario(param.getUsuario());
		ResultGenerico res = null;
		try {
			ResultValidar rvalidado = getEJBManager().validar((ParamValidar) paramValidar);

			if (!rvalidado.getHayError().booleanValue() && rvalidado.getUser() != null) {
				res = checkNull(new ServiciosMiBsePersist().obtenerMailCliente((ParamObtenerMailCliente) param));
				if (!res.getHayError().booleanValue()) {
					((ResultObtenerMailCliente) res).setMail(getSeudoMail(((ResultObtenerMailCliente) res).getMail()));
				}
			} else {
				res = new ResultObtenerMailCliente();
				res.setError(new ServiciosError(80,"Usuario no registrado"));
				res.setHayError(Boolean.TRUE);
			}
		} catch (NamingException e) {
			LOG.error("Error al obtener el EJB del GUC", e);
		}

		return checkNull(res);
	}

	private String getSeudoMail(String mail) {
		String resultado = null;
		if (mail != null && !mail.isEmpty()) {
			String[] partes = mail.split("@");
			if (partes.length == 2) {
				StringBuffer sb = new StringBuffer(partes[0].length());
				char[] array = partes[0].toCharArray();
				for (int i = 0; i < array.length; i++) {
					if (i < 3) {
						sb.append(array[i]);
					} else {
						sb.append('x');
					}
				}
				sb.append('@');
				sb.append(partes[1]);
				resultado = sb.toString();
			}
		}
		return resultado;
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
