package uy.com.bse.mibse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import uy.com.bse.mibse.persistencia.ServiciosMiBsePersist;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class SubirArchivoSolver extends AbstractSolver {
	private static Logger logger = LogManager.getLogger(ServiciosMiBsePersist.class);

	@Override
	public ResultGenerico procesoLogica(ParamGenerico param) {
		ParamSubirArchivo myParam = (ParamSubirArchivo) param;
		ResultGenerico res = checkNull(new ServiciosMiBsePersist().subirArchivo(myParam));
	
		// AGM 21/03/2022 - Envío por mail
		/*
		InputStream is = null;
		try {
			Adjuntos adjuntos = new Adjuntos();
			Adjunto adjunto = new Adjunto();
			is = myParam.getArchivo().getInputStream();
			adjunto.setArchivo(IOUtils.toByteArray(is));
			adjunto.setNombre(myParam.getNombreArchivo());
			adjuntos.getAdjunto().add(adjunto);

			ApiMailWSBuilder.create().withRemitente("noreply@bse.com.uy").withNombreRemitente("bse")
					.withDestinatario("amorales@bse.com.uy,leperez@bse.com.uy,gmaquiel@bse.com.uy,fgonzalez@bse.com.uy").withAsunto("Archivo recibido - Tienda BSE")
					.withCuerpo("<html><body><h1>Archivo recibido</h1></body></html>")
					.withAdjuntos(adjuntos).build().send();
					
		} catch (Exception e) {
			logger.error(e);
		} finally {
			IOUtils.closeQuietly(is);
		}
		*/
		
		return res;
	}

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultSubirArchivo();
	}
}
