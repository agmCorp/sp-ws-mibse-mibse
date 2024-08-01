package uy.com.bse.mibse;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import uy.com.bse.guc.GUCRemote;
import uy.com.bse.guc.interfaces.GUCValues;
import uy.com.bse.guc.interfaces.ParamAltaUsuario;
import uy.com.bse.guc.interfaces.ParamValidar;
import uy.com.bse.guc.interfaces.ResultAltaUsuario;
import uy.com.bse.mibse.persistencia.ServiciosMiBsePersist;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultCondicion;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.dato.ServiciosError;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class RegistrarClienteSolver extends AbstractSolver {
	
	private static final Logger LOG = LogManager.getLogger(RegistrarClienteSolver.class);

	@Override
	public ResultGenerico procesoLogica(ParamGenerico param) {
		ResultRegistrarCliente resultado = null;
		try {
			ServiciosMiBsePersist persistencia = new ServiciosMiBsePersist();
			ParamRegistrarCliente pregistrar = (ParamRegistrarCliente) param;
			ParamObtenerNumeroCliente paramObtenerNumero = new ParamObtenerNumeroCliente();
			paramObtenerNumero.setClave(pregistrar.getClave());
			paramObtenerNumero.setUsuario(crearEstructuraUsuario(pregistrar.getTipoDocumento(), pregistrar.getNumDocumento()));
			ResultCondicion registrado = clienteFueRegistrado(pregistrar.getTipoDocumento(), pregistrar.getNumDocumento());
			if (!registrado.getResultado().booleanValue()) {
				ParamValidarCodigoAdhesion pcodParam = new ParamValidarCodigoAdhesion();
				pcodParam.setClave(param.getClave());
				pcodParam.setUsuario(crearEstructuraUsuario(pregistrar.getTipoDocumento(), pregistrar.getNumDocumento()));
				pcodParam.setCodAdhesion(pregistrar.getCodigoAdhesion());
				ResultValidarCodigoAdhesion rvalidar = persistencia.validarCodigoAdhesion(pcodParam);
				if (!rvalidar.getHayError().booleanValue()) {
					if ("S".equalsIgnoreCase(rvalidar.getValidado())) {
						ParamAltaUsuario paramAltaUsuario = new ParamAltaUsuario();
						paramAltaUsuario.setUsuario(crearEstructuraUsuario(pregistrar.getTipoDocumento(), pregistrar.getNumDocumento()));
						paramAltaUsuario.setTipoClave(GUCValues.CLAVEUSUARIO);
						paramAltaUsuario.setClaveUsuario(pregistrar.getContrasena());

						ResultAltaUsuario resAlta = getEJBManager().altaUsuario(paramAltaUsuario);

						if (!resAlta.getHayError().booleanValue()) {
							resultado = new ResultRegistrarCliente();
							resultado.setHayError(resAlta.getHayError());
							resultado.setUser(resAlta.getUser());
						} else {
							resultado = new ResultRegistrarCliente();
							resultado.setError(resAlta.getError());
							resultado.setHayError(resAlta.getHayError());
						}
					} else {
						resultado = new ResultRegistrarCliente();
						resultado.setError(new ServiciosError(350, "El c\u00f3digo de adhesi\u00f3n no es v\u00e1lido"));
						resultado.setHayError(Boolean.TRUE);
					}
				} else {
					resultado = new ResultRegistrarCliente();
					resultado.setError(rvalidar.getError());
					resultado.setHayError(rvalidar.getHayError());
				}
			} else {
				resultado = new ResultRegistrarCliente();
				resultado.setError(registrado.getError());
				resultado.setHayError(Boolean.TRUE);
			}
		} catch (NamingException e) {
			LOG.error("Error al obtener el EJB del GUC", e);
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
		return new ResultRegistrarCliente();
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
