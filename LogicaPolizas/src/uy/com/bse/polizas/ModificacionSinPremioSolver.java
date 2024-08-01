package uy.com.bse.polizas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import uy.com.bse.cotizaciones.consultas.AcreedorBien;
import uy.com.bse.cotizaciones.consultas.BienCert;
import uy.com.bse.cotizaciones.consultas.DatosBienCert;
import uy.com.bse.polizas.operaciones.ParamActualizarDatosParticulares;
import uy.com.bse.polizas.operaciones.ParamGenerarEndoso;
import uy.com.bse.polizas.persistencia.ServiciosPolizasPersist;
import uy.com.bse.recuotificacion.ParamAcreedorEndoso;
import uy.com.bse.recuotificacion.ParamActualizarDatosBancariosEndoso;
import uy.com.bse.recuotificacion.ParamModificacionSinPremio;
import uy.com.bse.recuotificacion.ResultModificacionSinPremio;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.dato.ServiciosError;
import uy.com.bse.utilitario.logica.AbstractSolver;
import uy.com.bse.utilitario.util.ValuesUtils;

public class ModificacionSinPremioSolver extends AbstractSolver {


	private static final class AcreedorComparator implements Comparator<AcreedorBien> {
		@Override
		public int compare(AcreedorBien arg0, AcreedorBien arg1) {
			int resultado = 0;
			if ((arg0.getFechaExclusion() == null && arg1.getFechaExclusion() == null) || (arg0.getFechaExclusion() != null && arg1.getFechaExclusion() != null)) {
				resultado = arg1.getPorcentajeParticipacion().compareTo(arg0.getPorcentajeParticipacion());
			} else {
				if (arg0.getFechaExclusion() != null) {
					return -1;
				} else {
					return 1;
				}
			}
			return resultado;
		}
	}

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultModificacionSinPremio();
	}

	private ResultModificacionSinPremio generarEndoso(ParamModificacionSinPremio param) {
		ResultModificacionSinPremio result = new ResultModificacionSinPremio();

		GenerarEndosoSolver generarEndosoSolver = new GenerarEndosoSolver();

		ResultGenerico endoso = generarEndosoSolver.procesoLogica(buidParam(param));
		if (endoso.getHayError()) {
			result.setHayError(endoso.getHayError());
			result.setError(endoso.getError());
		} else {
			if (param.getNumCertificado().equalsIgnoreCase("0")) {
				ActualizarDatosBancariosEndosoSolver actB = new ActualizarDatosBancariosEndosoSolver();
				ParamActualizarDatosBancariosEndoso pa = new ParamActualizarDatosBancariosEndoso();
				pa.setClave(param.getClave());
				pa.setUsuario(param.getUsuario());
				pa.setCodOrigenPago(param.getCodOrigenPago());
				pa.setCodMedioPago(param.getCodMedioPago());
				pa.setNumEndoso(Integer.valueOf(param.getNumEndoso()));
				pa.setNumPoliza(Integer.valueOf(param.getNumPoliza()));
				pa.setNumRamo(Integer.valueOf(param.getNumRamo()));
				if (new ServiciosPolizasPersist().validarRequiereDomicilioBancario(param.getCodMedioPago())) {
					if (param.getDatosBancarios() != null && param.getDatosBancarios().getNumDomicilio() != null) {
						pa.setNumDomicilioBanco(param.getDatosBancarios().getNumDomicilio());
						ResultGenerico rgb = actB.procesoLogica(pa);
						if (rgb.getHayError().booleanValue()) {
							result.setHayError(rgb.getHayError());
							result.setError(rgb.getError());
						}
					} else {
						result.setHayError(Boolean.TRUE);
						result.setError(new ServiciosError(32,"Debe especificar el domicilio bancario"));
					}
				} else {
					if (param.getDatosBancarios() != null) {
						pa.setNumDomicilioBanco(param.getDatosBancarios().getNumDomicilio());
					} else {
						pa.setNumDomicilioBanco(null);
					}

					ResultGenerico rgb = actB.procesoLogica(pa);
					if (rgb.getHayError().booleanValue()) {
						result.setHayError(rgb.getHayError());
						result.setError(rgb.getError());
					}
				}

			} else {
				ActualizarDatosParticularesSolver actSolver = new ActualizarDatosParticularesSolver();
				ActualizarAcreedorSolver actAcreedor = new ActualizarAcreedorSolver();
				ArrayList<BienCert> lista = param.getBienes();
				for (BienCert bienCert : lista) {

					for (DatosBienCert dato : bienCert.getDatos()) {
						ResultGenerico rg = actSolver.procesoLogica(buidParam(dato, param, bienCert));
						if (rg.getHayError().booleanValue()) {
							result.setHayError(rg.getHayError());
							result.setError(rg.getError());
							break;
						}
					}
					if (result.getHayError().booleanValue()) {
						break;
					}

					Collections.sort(bienCert.getAcreedoresBien(), new AcreedorComparator());

					for (AcreedorBien acreedor : bienCert.getAcreedoresBien()) {
						ResultGenerico rg = actAcreedor.procesoLogica(buidParam(acreedor, param, bienCert));
						if (rg.getHayError().booleanValue()) {
							result.setHayError(rg.getHayError());
							result.setError(rg.getError());
							break;
						}
					}
					if (result.getHayError().booleanValue()) {
						break;
					}
				}
			}

		}

		return result;
	}

	private ParamGenerico buidParam(AcreedorBien acreedor, ParamModificacionSinPremio ent, BienCert bienCert) {
		ParamAcreedorEndoso param = new ParamAcreedorEndoso();
		param.setClave(ent.getClave());
		param.setUsuario(ent.getUsuario());
		param.setCodAcreedor(acreedor.getCodAcreedor());
		param.setCodObjeto(acreedor.getCodObjeto());
		param.setCodRamo(Integer.valueOf(ent.getNumRamo()));
		param.setNumPoliza(Integer.valueOf(ent.getNumPoliza()));
		param.setCodTipoAcreedor(acreedor.getCodTipoAcreedor());
		param.setConsecutivoBien(bienCert.getConsecutivo());
		param.setFechaExclusion(acreedor.getFechaExclusion());
		param.setNumCertificado(Integer.valueOf(ent.getNumCertificado()));
		param.setNumEndoso(Integer.valueOf(ent.getNumEndoso()));
		param.setPorcentajeParticipacion(acreedor.getPorcentajeParticipacion());
		param.setIdentificador(acreedor.getIdentificador());
		return param;
	}

	private ParamGenerarEndoso buidParam(ParamModificacionSinPremio ent) {
		ParamGenerarEndoso param = new ParamGenerarEndoso();
		param.setClave(ent.getClave());
		param.setUsuario(ent.getUsuario());
		param.setNumPoliza(ent.getNumPoliza());
		param.setNumRamo(ent.getNumRamo());
		param.setNumCertificado(ent.getNumCertificado());
		param.setNumEndoso(ent.getNumEndoso());
		param.setCodOrigen(ent.getCodOrigenEndoso());
		param.setFechaDesde(ent.getFechaDesde());
		return param;
	}

	private ParamGenerico buidParam(DatosBienCert dato, ParamModificacionSinPremio ent, BienCert bienCert) {
		ParamActualizarDatosParticulares param = new ParamActualizarDatosParticulares();
		param.setClave(ent.getClave());
		param.setUsuario(ent.getUsuario());
		param.setNumPoliza(ent.getNumPoliza());
		param.setNumRamo(ent.getNumRamo());
		param.setCodBien(ValuesUtils.toString(bienCert.getConsecutivo()));
		param.setCodDato(ValuesUtils.toString(dato.getDatoCod()));
		param.setCodValor(ValuesUtils.toString(dato.getValorCod()));
		param.setNumCertificado(ent.getNumCertificado());
		param.setNumEndoso(ent.getNumEndoso());
		return param;
	}

	@Override
	protected ResultGenerico procesoLogica(ParamGenerico param) {
		return generarEndoso((ParamModificacionSinPremio) param);
	}

}
