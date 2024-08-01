package uy.com.bse.reportes.ws;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import uy.com.bse.serviciosEJB.SeguridadServiciosRemote;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.excepcion.Values;
import uy.com.bse.utilitario.log.Logueo;
import uy.com.bse.utilitario.seguridad.ResultObtenerUsuario;

public class DownloadReporteRectorResolver implements DownloadResolver {

	private static Logger LOG = LogManager.getLogger(DownloadReporteRectorResolver.class);

	public void resolve(HttpServletRequest request, HttpServletResponse response) {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(DownloadReporteRectorResolver.class);
		logueo.setMetodo("resolve");
		logueo.setParametro("PATH", request.getPathInfo());
		Enumeration<?> e = request.getParameterNames();
		while (e.hasMoreElements()) {
			String object = (String) e.nextElement();
			logueo.setParametro(object, request.getParameter(object));
		}

		String token = request.getParameter("token");

		ParamGenerico param = new ParamGenerico();
		param.setClave(token);
		ResultObtenerUsuario resultado;

		try {
			resultado = getEJBSeguridad().obtenerUsuario(param);

			logueo.setParametro(Values.USUARIO, resultado.getUsuario());
			logueo.setParametro("Hay error", resultado.getHayError());
			LOG.info(logueo.getSoloParametros());

			if (!resultado.getHayError()) {
				new ReportesRectorWrapper().imprimir(request, response);
			} else {
				LOG.error(resultado.getError().getDescripcion());
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			}
		} catch (NamingException e1) {
			LOG.error("Error al Obtener el EJB de Seguridad", e1);
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		}
	}

	private static SeguridadServiciosRemote getEJBSeguridad() throws NamingException {
		final InitialContext ctx = new InitialContext();
		return (SeguridadServiciosRemote) ctx.lookup("java:global/SeguridadServicios/SeguridadEJB/SeguridadServicios!uy.com.bse.serviciosEJB.SeguridadServiciosRemote");

	}

	public void handleError(HttpServletRequest request, HttpServletResponse response) {
		try {
			
			PrintWriter writer = response.getWriter();
			response.setContentType("text/plain");
			response.setHeader("Content-Disposition", "attachment; filename=\"" + "Error.txt" + "\"");
			writer.append("No se gener\u00f3 el reporte a descargar. Intente nuevamente.");
			writer.flush();
			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
