package uy.com.bse.maestro.personas.logica;

import uy.com.bse.cotizaciones.entidades.DatosBanco;
import uy.com.bse.maestro.personas.comunicaciones.ComunicacionEC;
import uy.com.bse.maestro.personas.comunicaciones.ParamAltaComunicacion;
import uy.com.bse.maestro.personas.comunicaciones.ResultComunicacion;
import uy.com.bse.maestro.personas.datosbancarios.ParamModificarDatoBancario;
import uy.com.bse.maestro.personas.datosbancarios.ResultDatoBancario;
import uy.com.bse.maestro.personas.domicilio.DireccionEC;
import uy.com.bse.maestro.personas.domicilio.ParamDatosDireccion;
import uy.com.bse.maestro.personas.domicilio.ResultDireccion;
import uy.com.bse.maestro.personas.personas.ParamAltaCliente;
import uy.com.bse.maestro.personas.personas.ParamAltaRolCliente;
import uy.com.bse.maestro.personas.personas.ParamDatosPersonaFisica;
import uy.com.bse.maestro.personas.personas.ParamDatosPersonaJuridica;
import uy.com.bse.maestro.personas.personas.ResultAltaCliente;
import uy.com.bse.maestro.personas.personas.ResultAltaRolCliente;
import uy.com.bse.maestro.personas.personas.ResultPersonaFisica;
import uy.com.bse.maestro.personas.personas.ResultPersonaJuridica;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;
import uy.com.bse.utilitario.util.ValuesUtils;

public class AltaClienteSolver extends AbstractSolver {
	
	private static Integer PERSONA_FISICA=Integer.valueOf(1);
	private static Integer PERSONA_JURIDICA=Integer.valueOf(2);

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultAltaCliente();
	}

	@Override
	public ResultGenerico procesoLogica(ParamGenerico param) {
		return altaCliente((ParamAltaCliente) param);
	}

	private ResultAltaCliente altaCliente(ParamAltaCliente param) {
		ResultAltaCliente resultado = null;
		if (param.getTipoPersona().equals(PERSONA_FISICA)) {
			resultado = altaPersonaFisica(param);
		}
		if (param.getTipoPersona().equals(PERSONA_JURIDICA)) {
			resultado =altaPersonaJuridica(param);
		}
		return (ResultAltaCliente) checkNull(resultado);
	}

	private ResultAltaCliente altaPersonaJuridica(ParamAltaCliente param) {
		ResultAltaCliente resultado = new ResultAltaCliente();

		AltaPersonaJuridicaSolver solver = new AltaPersonaJuridicaSolver();
		ResultPersonaJuridica result = (ResultPersonaJuridica) solver.solve(toParamPersonaJuridica(param));
		if (result.getHayError()) {
			resultado.setHayError(result.getHayError());
			resultado.setError(result.getError());
		} else {
			resultado.setCodPersona(result.getPersona().getCodPersona());
			//Registrar Domicilos 
			if (param.getDomicilios() != null && !param.getDomicilios().isEmpty()) {
				ResultDireccion resultDir = registrarDirecciones(resultado.getCodPersona(), param);
				if (resultDir.getHayError()) {
					resultado.setHayError(resultDir.getHayError());
					resultado.setError(resultDir.getError());
					resultado.setTieneDireccion("N");
				} else {
					resultado.setTieneDireccion("S");
				}
			} else {
				resultado.setTieneDireccion("N");
			}

			//Registrar Tarjetas
			if (param.getTarjetas() != null && !param.getTarjetas().isEmpty()) {
				ResultDatoBancario resultDatoBancario = registrarDatosBancarios(resultado.getCodPersona(), param);
				if (resultDatoBancario.getHayError()) {
					resultado.setHayError(resultDatoBancario.getHayError());
					resultado.setError(resultDatoBancario.getError());
					resultado.setTieneTarjetas("N");
				}else{
					resultado.setTieneTarjetas("S");
				}
				resultado.setTieneTarjetas("N");
			}
			
			//Registrar Comunicaciones
			if (param.getComunicaciones() != null && !param.getComunicaciones().isEmpty()) {
				ResultComunicacion resultComunicacion = registrarComunicaciones(resultado.getCodPersona(), param);
				if (resultComunicacion.getHayError()) {
					resultado.setHayError(resultComunicacion.getHayError());
					resultado.setError(resultComunicacion.getError());
					resultado.setTieneComunicaciones("N");
				}else{
					resultado.setTieneComunicaciones("S");
				}
				resultado.setTieneComunicaciones("N");
			}
		}

		if (!resultado.getHayError()) {
			ResultAltaRolCliente raltaCliente = registrarAltaRolCliente(resultado.getCodPersona(), param);
			if (raltaCliente.getHayError()) {
				resultado.setHayError(result.getHayError());
			} else {
				resultado.setCodCliente(raltaCliente.getCodCliente());
			}
		}

		return resultado;
	}

	private ResultAltaCliente altaPersonaFisica(ParamAltaCliente param) {
		ResultAltaCliente resultado = new ResultAltaCliente();

		AltaPersonaFisicaSolver solver = new AltaPersonaFisicaSolver();
		ResultPersonaFisica result = (ResultPersonaFisica) solver.solve(toParamPersonaFisica(param));
		if (result.getHayError()) {
			resultado.setHayError(result.getHayError());
			resultado.setError(result.getError());
		} else {
			resultado.setCodPersona(result.getPersona().getCodPersona());
			//Registrar Domicilos 
			if (param.getDomicilios() != null && !param.getDomicilios().isEmpty()) {
				ResultDireccion resultDir = registrarDirecciones(resultado.getCodPersona(), param);
				if (resultDir.getHayError()) {
					resultado.setHayError(resultDir.getHayError());
					resultado.setError(resultDir.getError());
					resultado.setTieneDireccion("N");
				} else {
					resultado.setTieneDireccion("S");
				}
			} else {
				resultado.setTieneDireccion("N");
			}

			//Registrar Tarjetas
			if (param.getTarjetas() != null && !param.getTarjetas().isEmpty()) {
				ResultDatoBancario resultDatoBancario = registrarDatosBancarios(resultado.getCodPersona(), param);
				if (resultDatoBancario.getHayError()) {
					resultado.setHayError(resultDatoBancario.getHayError());
					resultado.setError(resultDatoBancario.getError());
					resultado.setTieneTarjetas("N");
				}else{
					resultado.setTieneTarjetas("S");
				}
				resultado.setTieneTarjetas("N");
			}
			
			//Registrar Comunicaciones
			if (param.getComunicaciones() != null && !param.getComunicaciones().isEmpty()) {
				ResultComunicacion resultComunicacion = registrarComunicaciones(resultado.getCodPersona(), param);
				if (resultComunicacion.getHayError()) {
					resultado.setHayError(resultComunicacion.getHayError());
					resultado.setError(resultComunicacion.getError());
					resultado.setTieneComunicaciones("N");
				}else{
					resultado.setTieneComunicaciones("S");
				}
				resultado.setTieneComunicaciones("N");
			}
		}

		if (!resultado.getHayError()) {
			ResultAltaRolCliente raltaCliente = registrarAltaRolCliente(resultado.getCodPersona(), param);
			if (raltaCliente.getHayError()) {
				resultado.setHayError(result.getHayError());
			} else {
				resultado.setCodCliente(raltaCliente.getCodCliente());
			}
		}

		return resultado;
	}

	private ResultComunicacion registrarComunicaciones(Integer codPersona, ParamAltaCliente param) {
		AltaComunicacionesSolver solver = new AltaComunicacionesSolver();
		ResultComunicacion resultado = new ResultComunicacion();
		for (ComunicacionEC comunicacion : param.getComunicaciones()) {
			ResultGenerico rtemp = solver.solve(toParamAltaComunicacion(param.getUsuario(), param.getClave(), codPersona, comunicacion));
			if (rtemp.getHayError()) {
				resultado.setHayError(rtemp.getHayError());
				resultado.setError(rtemp.getError());
				break;
			}
		}
		return resultado;
	}

	private ParamAltaComunicacion toParamAltaComunicacion(String usuario, String clave, Integer codPersona, ComunicacionEC comunicacion) {
		ParamAltaComunicacion param = new ParamAltaComunicacion();
		param.setClave(clave);
		param.setCodPersona(codPersona);
		param.setFechaActualizacion(comunicacion.getFechaActualizacion());
		param.setNota(comunicacion.getNota());
		param.setTipoComunicacion(comunicacion.getTipoComunicacion());
		param.setUsuario(usuario);
		param.setValorComunicacion(comunicacion.getValorComunicacion());
		return param;
	}

	private ResultAltaRolCliente registrarAltaRolCliente(Integer codPersona, ParamAltaCliente myParam) {
		ParamAltaRolCliente param = new ParamAltaRolCliente();
		param.setUsuario(myParam.getUsuario());
		param.setClave(myParam.getClave());
		param.setNumPersona(ValuesUtils.toString(codPersona));
		ResultAltaRolCliente resultado = (ResultAltaRolCliente) new AltaRolClienteSolver().solve(param);
		return resultado;

	}

	private ResultDatoBancario registrarDatosBancarios(Integer codPersona, ParamAltaCliente param) {
		ModificarDatosBancariosSolver solver = new ModificarDatosBancariosSolver();
		ResultDatoBancario resultado = new ResultDatoBancario();
		for (DatosBanco tarjeta : param.getTarjetas()) {
			ResultGenerico rtemp = solver.solve(toParamModificarDatoBancario(param.getUsuario(), param.getClave(), codPersona, tarjeta));
			if (rtemp.getHayError()) {
				resultado.setHayError(rtemp.getHayError());
				resultado.setError(rtemp.getError());
				break;
			}
		}
		return resultado;
	}

	private ParamModificarDatoBancario toParamModificarDatoBancario(String usuario, String clave, Integer codPersona, DatosBanco tarjeta) {
		ParamModificarDatoBancario salida = new ParamModificarDatoBancario();

		salida.setClave(clave);
		salida.setUsuario(usuario);
		salida.setCodBanco(tarjeta.getCodBanco());
		salida.setCodPersona(codPersona);
		salida.setNumcuenta(tarjeta.getNumCuenta());
		// tipo cuenta tarjeta de credito es 5 
		 salida.setTipoCuenta(5);
		 salida.setTipoTarjeta(ValuesUtils.toInteger(tarjeta.getTipoTarjeta()));
		String fecha = "01/" + tarjeta.getFechaVencimiento();

		salida.setFechaVencimiento(fecha);

		return salida;
	}

	private ResultDireccion registrarDirecciones(Integer codPersona, ParamAltaCliente param) {
		AltaDireccionSolver altaDireccion = new AltaDireccionSolver();
		ModificarDireccionSolver modDireccion = new ModificarDireccionSolver();
		
		ResultDireccion resultado = new ResultDireccion();
		ResultGenerico resultTemp;
		
		
		for (DireccionEC direccionEC : param.getDomicilios()) {
			
			if(direccionEC.getCodDireccion()== null){
				resultTemp = altaDireccion.solve(toParamDireccion(param.getUsuario(), param.getClave(), codPersona, direccionEC));
			}else{
				resultTemp = modDireccion.solve(toParamDireccion(param.getUsuario(), param.getClave(), codPersona, direccionEC));
				
			}
			
			if (resultTemp.getHayError()) {
				resultado .setHayError(resultTemp.getHayError());
				resultado.setError(resultTemp.getError());
				break;
			}
		}
		return resultado;
	}

	private ParamDatosDireccion toParamDireccion(String usuario, String clave, Integer codPersona, DireccionEC direccionEC) {
		ParamDatosDireccion salida = new ParamDatosDireccion();
		salida.setUsuario(usuario);
		salida.setClave(clave);
		salida.setAclaraciones(direccionEC.getAclaraciones());
		salida.setApto(direccionEC.getApto());
		salida.setCalle(direccionEC.getDireccion());
		salida.setCodDireccion(direccionEC.getCodDireccion());
		salida.setCodPersona(codPersona);
		salida.setNumeroPuerta(direccionEC.getNumeroPuerta());
		salida.setNumPostal(direccionEC.getNumPostal());
		salida.setPadron(direccionEC.getPadron());
		salida.setPiso(direccionEC.getPiso());
		salida.setTipoDireccion(direccionEC.getTipoDireccion());
		salida.setUnidad(direccionEC.getUnidad());
		return salida;
	}

	private ParamDatosPersonaFisica toParamPersonaFisica(ParamAltaCliente myParam) {
		ParamDatosPersonaFisica salida = new ParamDatosPersonaFisica();
		salida.setApellido(myParam.getApellido());
		salida.setClave(myParam.getClave());
		salida.setCodDireccion(myParam.getCodDireccion());
		salida.setCodEstadoCivil(myParam.getCodEstadoCivil());
		salida.setCodPersona(myParam.getCodPersona());
		salida.setCodProfesion(myParam.getCodProfesion());
		salida.setFechaNacimiento(myParam.getFechaNacimiento());
		salida.setNombre(myParam.getNombre());
		salida.setNumDocumento(myParam.getNumDocumento());
		salida.setRut(myParam.getRut());
		salida.setSexo(myParam.getSexo());
		salida.setTipoDocumento(myParam.getTipoDocumento());
		salida.setUsuario(myParam.getUsuario());
		return salida;
	}
	
	private ParamDatosPersonaJuridica toParamPersonaJuridica(ParamAltaCliente myParam) {
		ParamDatosPersonaJuridica salida = new ParamDatosPersonaJuridica();
		salida.setClave(myParam.getClave());
		salida.setUsuario(myParam.getUsuario());
		salida.setCodActividad(myParam.getCodActividad());
		salida.setCodDireccion(myParam.getCodDireccion());
		salida.setCodOrganismo(myParam.getCodOrganismo());
		salida.setCodPersona(myParam.getCodPersona());
		salida.setFechaInicio(myParam.getFechaInicio());
		salida.setRazonSocial(myParam.getRazonSocial());
		salida.setRuc(myParam.getRut());
		
		return salida;
	}
	
}
