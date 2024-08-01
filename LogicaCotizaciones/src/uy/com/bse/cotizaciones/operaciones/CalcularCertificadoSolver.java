package uy.com.bse.cotizaciones.operaciones;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import uy.bse.rector.servicios.parseo.ParseoCotOperaciones;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.dato.ResultXmlPL;
import uy.com.bse.utilitario.dato.ServiciosError;
import uy.com.bse.utilitario.excepcion.ErrorResolver;
import uy.com.bse.utilitario.excepcion.Values;
import uy.com.bse.utilitario.logica.XMLAbstractSolver;

public class CalcularCertificadoSolver extends XMLAbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultCalcularCertificado();
	}

	@Override
	protected ResultXmlPL getXmlResult(ParamGenerico param) {
		final CotOperaciones  persitencia=	new CotOperaciones();
		ResultXmlPL resultado=null;
		ServiciosError error = validarComisiones((ParamCalcularCertificado) param, persitencia);
		if (error!=null) {
			resultado = new ResultXmlPL();
			resultado.setError(error);
			resultado.setHayError(Boolean.TRUE);
		}else{
			resultado= persitencia.calcularCertificado((ParamCalcularCertificado) param);
		}
		return resultado;
	}

	private ServiciosError validarComisiones(ParamCalcularCertificado param, final CotOperaciones persitencia) {
		ServiciosError error=null;
		Integer codCorredor = persitencia.obtenerCodigoCorredor(param);
		if (codCorredor==null) {
			if (param.getCodNivelComisionProductor()!=null && param.getCodNivelComisionBroker()==null||"".equals(param.getCodNivelComisionBroker())) {
				error= ErrorResolver.getError(Values.VALNULL);
			}	
		}
		return error;
	}

	@Override
	protected ResultGenerico parseValues(ResultXmlPL xmlResult) {
		ParseoCotOperaciones unParseo = new ParseoCotOperaciones(xmlResult.getXml());
		if (unParseo.generarDoc().booleanValue()) {
			ResultCalcularCertificado result = unParseo.parsearCalcularCertificado();
			if (result != null) {
				ArrayList<Certificado> certificados = result.getListaCertificados();
				ArrayList<String> titulosDistintos = new ArrayList<String>();
				titulosDistintos.add("");
				Set<String> codigos = new HashSet<String>();
				if (certificados != null) {
					for (int j = 0; j < certificados.size(); j++) {
						ArrayList<PlanCobertura> planesCob = certificados.get(j).getListaPlanesCobertura();
						if (planesCob != null) {
							for (int k = 0; k < planesCob.size(); k++) {
								ArrayList<PlanPago> planesCert = planesCob.get(k).getPlanesPago();
								if (planesCert != null) {
									for (PlanPago planPago : planesCert) {
										if (codigos.add(planPago.getCodigo())) {
											titulosDistintos.add(planPago.getDescripcion());
										}
									}
								}
							}
						}
					}
				}
				result.setTitulos(titulosDistintos);
			}
			return result;
		}
		return null;
	}

}
