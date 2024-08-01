package uy.com.bse.reportes.ws;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger LOG = LogManager.getLogger(DownloadServlet.class);
	private Map<String, DownloadResolver> resolvers = new HashMap<String, DownloadResolver>();

	public DownloadServlet() {
		super();
		resolvers.put("/reporte", new DownloadReporteRectorResolver());
		resolvers.put("/fichero", new DownloadFileResolver());

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOG.debug("DownloadServlet doGet stars");
		DownloadResolver resolver = resolvers.get(request.getPathInfo());
		if (resolver != null) {
			resolver.resolve(request, response);
		} else {
			LOG.debug("Bad request. No resolver for " + request.getPathInfo());
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
		LOG.debug("DownloadServlet doGet ends");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOG.debug("DownloadServlet doPost stars");
		DownloadResolver resolver = resolvers.get(request.getPathInfo());
		if (resolver != null) {
			resolver.resolve(request, response);
		} else {
			LOG.debug("Bad request. No resolver for " + request.getPathInfo());
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
		LOG.debug("DownloadServlet doPost ends");
	}
}
