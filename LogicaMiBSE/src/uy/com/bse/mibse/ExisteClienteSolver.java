package uy.com.bse.mibse;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import uy.com.bse.guc.GUCRemote;
import uy.com.bse.guc.interfaces.ParamValidar;
import uy.com.bse.mibse.persistencia.ServiciosMiBsePersist;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultCondicion;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ExisteClienteSolver extends AbstractSolver {
	
	private static final Logger LOG = LogManager.getLogger(ExisteClienteSolver.class);

	@Override
	public ResultGenerico procesoLogica(ParamGenerico param) {
		// Devuelve existe en true si es usuario de MiBSE
		ResultExisteCliente resultado = null;
		resultado = new ResultExisteCliente();
		resultado.setExiste(false);
		ParamExisteCliente pexiste = (ParamExisteCliente) param;
		ResultCondicion registrado = clienteFueRegistrado(pexiste.getTipoDocumento(), pexiste.getNumDocumento());
		if (registrado.getResultado().booleanValue()) {
			if (registrado.getError().getCodigo().equals(1400))
				resultado.setExiste(true);
			resultado.setError(registrado.getError());
			resultado.setHayError(Boolean.TRUE);
		}
		return checkNull(resultado);
	}

	private ResultCondicion clienteFueRegistrado(String tipoDocumento, String numDocumento) {
		ParamValidar param = new ParamValidar();
		param.setUsuario(crearEstructuraUsuario(tipoDocumento, numDocumento));
		return new ServiciosMiBsePersist().verificaDuplicidadUsuario(param);
	}

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultExisteCliente();
	}

	private String crearEstructuraUsuario(String tipoDocumento, String numDocumento) {
		StringBuffer sb = new StringBuffer();
		sb.append(tipoDocumento);
		sb.append("#");
		sb.append(numDocumento);
		return sb.toString();
	}

	public static GUCRemote getEJBManager() throws NamingException {
		final InitialContext ctx = new InitialContext();
		return (GUCRemote) ctx.lookup("global/GUC/GUCEJB/GUC!uy.com.bse.guc.GUCRemote");
	}

}
