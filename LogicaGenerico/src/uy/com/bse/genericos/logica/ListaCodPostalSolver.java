package uy.com.bse.genericos.logica;

import uy.com.bse.maestro.persistencia.ServiciosRectorPersist;
import uy.com.bse.servicios.rector.interfaces.ParamCodigoPostal;
import uy.com.bse.servicios.rector.interfaces.ResultListaCodPostal;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.dato.ServiciosError;
import uy.com.bse.utilitario.excepcion.Values;
import uy.com.bse.utilitario.logica.AbstractSolver;
import uy.com.bse.utilitario.properties.PropertiesManager;
import uy.com.bse.utilitario.util.Herramientas;

public class ListaCodPostalSolver extends AbstractSolver {
	private final String archivoProperties = "maestroPersonas.properties";

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultListaCodPostal();
	}

	@Override
	public ResultGenerico procesoLogica(ParamGenerico param) {
		return listaCodPostal((ParamCodigoPostal) param);
	}

	private ResultListaCodPostal listaCodPostal(ParamCodigoPostal param) {

		ResultListaCodPostal resultado = new ResultListaCodPostal();

		PropertiesManager properties = new PropertiesManager();
		properties.setNombreArchivo(this.archivoProperties);
		String Uruguay = properties.obtenerValor("Uruguay");
		String Montevideo = properties.obtenerValor("Montevideo");
		String NoGeografico = properties.obtenerValor("NoGeografico");

		ServiciosRectorPersist persist = new ServiciosRectorPersist();

		if (param.getCodPais().equals(Uruguay) && param.getCodDepartamento().equals(Montevideo)) {
			resultado = persist.postalUruguayMontevideo(param);
		} else if (param.getCodPais().equals(Uruguay) && param.getCodDepartamento().equals(NoGeografico)) {
			resultado = persist.postalUruguayNoGeografico(param);
		} else if (param.getCodPais().equals(Uruguay) && !param.getCodDepartamento().equals(NoGeografico) && !param.getCodDepartamento().equals(Montevideo)) {
			resultado = persist.postalUruguayInterior(param);
		} else if (!param.getCodPais().equals(Uruguay)) {
			resultado = persist.postalCualquierPais(param);
		} else {

			String valorObtenido = properties.obtenerValor("negocioPostalParam-codigo");
			Herramientas tools = new Herramientas();
			Integer codigo = tools.checkValorPropertiesNumerico(Values.CODDEFECTO, valorObtenido);

			String descObtenida = properties.obtenerValor("negocioPostalParam-descripcion");
			String descripcion = tools.checkValorPropertiesString(Values.DESDEFECTO, descObtenida);

			ServiciosError retornarError = new ServiciosError();
			retornarError.setCodigo(codigo);
			retornarError.setDescripcion(descripcion);
			resultado.setError(retornarError);
			resultado.setHayError(Boolean.TRUE);
		}
		return resultado;

	}
}
