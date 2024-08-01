package uy.com.bse.serviciosEJB;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import uy.com.bse.LogicaRector;
import uy.com.bse.cotizaciones.operaciones.*;
import uy.com.bse.servicios.rector.emision.ParamEmision;
import uy.com.bse.servicios.rector.emision.ParamValidarCotizacion;
import uy.com.bse.servicios.rector.emision.ResultEmision;
import uy.com.bse.servicios.rector.emision.ResultValidarCotizacion;
import uy.com.bse.utilitario.dato.ResultGenerico;

@Stateless
@Interceptors(TransactionInterceptorRector.class)
public class CotOperaciones implements CotOperacionesLocal{
	
	private static Logger log = LogManager.getLogger(CotOperaciones.class);
  
    public CotOperaciones() {
       super();
    }
    @Interceptors(UserInterceptor.class)
    public ResultActualizarDirCotizacion actualizarDireccionesCotizacion(ParamActualizarDirCotizacion param){
		log.debug("actualizarDireccionesCotizacion start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("actualizarDireccionesCotizacion end");
		return (ResultActualizarDirCotizacion)result; 
    }

    @Interceptors(UserInterceptor.class)
	public ResultActualizarCoberturaBien actualizarCoberturaBien(ParamActualizarCoberturaBien param) {
		log.debug("actualizarCoberturaBien start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("actualizarCoberturaBien end");
		return (ResultActualizarCoberturaBien)result; 
	}

    @Interceptors(UserInterceptor.class)
	public ResultCalcularCobertura calcularCobertura(ParamCalcularCobertura param) {
		log.debug("calcularCobertura start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("calcularCobertura end");
		return (ResultCalcularCobertura)result; 
	}

    @Interceptors(UserInterceptor.class)
	public ResultCalcularCertificado calcularCertificado(ParamCalcularCertificado param) {
		log.debug("calcularCertificado start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("calcularCertificado end");
		return (ResultCalcularCertificado)result; 
	}

    @Interceptors(UserInterceptor.class)
	public ResultGuardarAsegurado guardarAsegurado(ParamGuardarAsegurado param) {
		log.debug("guardarAsegurado start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("guardarAsegurado end");
		return (ResultGuardarAsegurado)result; 
	}
	
    @Interceptors(UserInterceptor.class)
	public ResultActualizarCertificadoCero actualizarCertificadoCero(ParamActualizarCertificadoCero param) {
		log.debug("actualizarCertificadoCero start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("actualizarCertificadoCero end");
		return (ResultActualizarCertificadoCero)result; 
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultGenerico setearPlanCobertura(ParamSetearPlanCobertura param) {
		log.debug("setearPlanCobertura start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("setearPlanCobertura end");
		return result; 
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultActualizarDatosBancarios actualizarDatosBancarios(ParamActualizarDatosBancarios param) {
		log.debug("actualizarDatosBancarios start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("actualizarDatosBancarios end");
		return (ResultActualizarDatosBancarios)result; 
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultEnviarCotizacionFueraPauta enviarCotizacionFueraPauta(ParamEnviarCotizacionFueraPauta param) {
		log.debug("enviarCotizacionFueraPauta start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("enviarCotizacionFueraPauta end");
		return (ResultEnviarCotizacionFueraPauta)result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultNuevaCotizacion nuevaCotizacion(ParamNuevaCotizacion param) {
		log.debug("nuevaCotizacion start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("nuevaCotizacion end");
		return (ResultNuevaCotizacion)result; 
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultCopiarCotizaPoli copiarCotizaPoli(ParamCopiarCotizaPoli param) {
		log.debug("copiarCotizaPoli start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("copiarCotizaPoli end");
		return (ResultCopiarCotizaPoli)result; 
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultAnularCertificado anularCertificado(ParamAnularCertificado param) {
		log.debug("anularCertificado start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("anularCertificado end");
		return (ResultAnularCertificado)result; 
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultAgregarBien agregarBien(ParamAgregarBien param) {
		log.debug("agregarBien start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("agregarBien end");
		return (ResultAgregarBien)result; 
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultAgregarCertificado agregarCertificado(ParamAgregarCertificado param) {
		log.debug("agregarCertificado start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("agregarCertificado end");
		return (ResultAgregarCertificado)result; 
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultQuitarBien quitarBien(ParamQuitarBien param) {
		log.debug("quitarBien start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("quitarBien end");
		return (ResultQuitarBien)result; 
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultAnularCotizacion anularCotizacion(ParamAnularCotizacion param) {
		log.debug("anularCotizacion start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("anularCotizacion end");
		return (ResultAnularCotizacion)result; 
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultHabilitoAgregarBien habilitoAgregarBien(ParamHabilitoAgregarBien param) {
		log.debug("habilitoAgregarBien start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("habilitoAgregarBien end");
		return (ResultHabilitoAgregarBien)result; 
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultHabilitoQuitarBien habilitoQuitarBien(ParamHabilitoQuitarBien param) {
		log.debug("habilitoQuitarBien start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("habilitoQuitarBien end");
		return (ResultHabilitoQuitarBien)result; 
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultHabilitoListaBienes habilitoListaBienes(ParamHabilitoListaBienes param) {
		log.debug("habilitoListaBienes start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("habilitoListaBienes end");
		return (ResultHabilitoListaBienes)result; 
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultHabilitoUbicacion habilitoUbicacion(ParamHabilitoUbicacion param) {
		log.debug("habilitoUbicacion start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("habilitoUbicacion end");
		return (ResultHabilitoUbicacion)result; 
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultHabilitoBeneficiarios habilitoBeneficiarios(ParamHabilitoBeneficiarios param) {
		log.debug("habilitoBeneficiarios start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("habilitoBeneficiarios end");
		return (ResultHabilitoBeneficiarios)result; 
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultAltaUbicacionBien altaUbicacionBien(ParamAltaUbicacionBien param) {
		log.debug("altaUbicacionBien start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("altaUbicacionBien end");
		return (ResultAltaUbicacionBien)result; 
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultBajaUbicacionBien bajaUbicacionBien(ParamBajaUbicacionBien param) {
		log.debug("bajaUbicacionBien start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("bajaUbicacionBien end");
		return (ResultBajaUbicacionBien)result; 
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultModificarUbicacionBien modificarUbicacionBien(ParamModificarUbicacionBien param) {
		log.debug("modificarUbicacionBien start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("modificarUbicacionBien end");
		return (ResultModificarUbicacionBien)result; 
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultValidarDatos validarDatos(ParamValidarDatos param) {
		log.debug("validarDatos start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("validarDatos end");
		return (ResultValidarDatos)result; 
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultActualizarDatos actualizarDatos(ParamActualizarDatos param) {
		log.debug("actualizarDatos start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("actualizarDatos end");
		return (ResultActualizarDatos)result; 
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultCalcularCotizacion calcularCotizacion(ParamCalcularCotizacion param) {
		log.debug("calcularCotizacion start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("calcularCotizacion end");
		return (ResultCalcularCotizacion)result; 
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultGuardarCoberturas guardarCoberturas(ParamGuardarCoberturas param) {
		log.debug("guardarCoberturas start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("guardarCoberturas end");
		return (ResultGuardarCoberturas)result; 
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultHabilitoVigenciaTecnica habilitoVigenciaTecnica(ParamHabilitoVigenciaTecnica param) {
		log.debug("habilitoVigenciaTecnica start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("habilitoVigenciaTecnica end");
		return (ResultHabilitoVigenciaTecnica)result; 
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultAltaBeneficiario altaBeneficiario(ParamAltaBeneficiario param) {
		log.debug("altaBeneficiario start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("altaBeneficiario end");
		return (ResultAltaBeneficiario)result; 
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultModificarBeneficiario modificarBeneficiario(ParamModificarBeneficiario param) {
		log.debug("modificarBeneficiario start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("modificarBeneficiario end");
		return (ResultModificarBeneficiario)result; 
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultBajaBeneficiario bajaBeneficiario(ParamBajaBeneficiario param) {
		log.debug("bajaBeneficiario start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("bajaBeneficiario end");
		return (ResultBajaBeneficiario)result; 
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultAltaListaBienes altaListaBienes(ParamAltaListaBienes param) {
		log.debug("altaListaBienes start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("altaListaBienes end");
		return (ResultAltaListaBienes)result; 
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultBajaListaBienes bajaListaBienes(ParamBajaListaBienes param) {
		log.debug("bajaListaBienes start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("bajaListaBienes end");
		return (ResultBajaListaBienes)result; 
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultModificarListaBienes modificarListaBienes(ParamModificarListaBienes param) {
		log.debug("modificarListaBienes start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("modificarListaBienes end");
		return (ResultModificarListaBienes)result; 
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultRenovacionManual renovacionManual(ParamRenovacionManual param) {
		log.debug("renovacionManual start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("renovacionManual end");
		return (ResultRenovacionManual)result; 
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultIngresarNotaPoliza ingresarNotaPoliza(ParamIngresarNotaPoliza param) {
		log.debug("ingresarNotaPoliza start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("ingresarNotaPoliza end");
		return (ResultIngresarNotaPoliza)result; 
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultHabilitoBotones habilitoBotones(ParamHabilitoBotones param) {
		log.debug("habilitoBotones start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("habilitoBotones end");
		return (ResultHabilitoBotones)result; 
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultValidarAcumulaRiesgos validarAcumulaRiesgos(ParamValidarAcumulaRiesgos param) {
		log.debug("validarAcumulaRiesgos start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("validarAcumulaRiesgos end");
		return (ResultValidarAcumulaRiesgos)result; 
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultAltaTextoCotizacion altaTextoCotizacion(ParamAltaTextoCotizacion param) {
		log.debug("altaTextoCotizacion start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("altaTextoCotizacion end");
		return (ResultAltaTextoCotizacion)result; 
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultModificarTextoCotizacion modificarTextoCotizacion(ParamModificarTextoCotizacion param) {
		log.debug("modificarTextoCotizacion start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("modificarTextoCotizacion end");
		return (ResultModificarTextoCotizacion)result; 
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultAgregarAcreedorXBien agregarAcreedorXBien(ParamAgregarAcreedorXBien param) {
		log.debug("agregarAcreedorXBien start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("agregarAcreedorXBien end");
		return (ResultAgregarAcreedorXBien)result; 
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultModificarAcreedorXBien modificarAcreedorXBien(ParamModificarAcreedorXBien param) {
		log.debug("modificarAcreedorXBien start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("modificarAcreedorXBien end");
		return (ResultModificarAcreedorXBien)result; 
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultQuitarAcreedorXBien quitarAcreedorXBien(ParamQuitarAcreedorXBien param) {
		log.debug("quitarAcreedorXBien start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("quitarAcreedorXBien end");
		return (ResultQuitarAcreedorXBien)result; 
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultCalcularValidarVigencia calcularValidarVigencia(ParamCalcularValidarVigencia param) {
		log.debug("calcularValidarVigencia start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("calcularValidarVigencia end");
		return (ResultCalcularValidarVigencia)result; 
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultGuardarPlanes guardarPlanes(ParamGuardarPlanes param) {
		log.debug("guardarPlanes start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("guardarPlanes end");
		return (ResultGuardarPlanes)result; 
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultModificarOpcionesFacturacion modificarOpcionesFacturacion(
			ParamModificarOpcionesFacturacion param) {
		log.debug("modificarOpcionesFacturacion start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("modificarOpcionesFacturacion end");
		return (ResultModificarOpcionesFacturacion)result; 
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultValidarDatosVehiculo validarDatosVehiculo(ParamValidarDatosVehiculo param) {
			log.debug("validarDatosVehiculo start: " + param);
			ResultGenerico result = LogicaRector.solve(param);
			log.debug("validarDatosVehiculo end");
			return (ResultValidarDatosVehiculo)result; 
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultCalcularCuotasVehiculo calcularCuotasVehiculo(ParamCalcularCuotasVehiculo param) {
		log.debug("calcularCuotasVehiculo start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("calcularCuotasVehiculo end");
		return (ResultCalcularCuotasVehiculo)result; 
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultNuevaCotizacionVehiculo nuevaCotizacionVehiculo(ParamNuevaCotizacionVehiculo param) {
		log.debug("nuevaCotizacionVehiculo start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("nuevaCotizacionVehiculo end");
		return (ResultNuevaCotizacionVehiculo)result; 
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultActualizarPromocionVehiculo actualizarPromocionVehiculo(ParamActualizarPromocionVehiculo param) {
		log.debug("actualizarPromocionVehiculo start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("actualizarPromocionVehiculo end");
		return (ResultActualizarPromocionVehiculo)result; 
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultGenerico actualizarCertificadoCeroVehiculo(ParamActualizarCertificadoCeroVehiculo param) {
		log.debug("actualizarCertificadoCeroVehiculo start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("actualizarCertificadoCeroVehiculo end");
		return result; 
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultConfigurarFechasPromocion configurarFechasPromocion(ParamConfigurarFechasPromocion param) {
		log.debug("configurarFechasPromocion start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("configurarFechasPromocion end");
		return (ResultConfigurarFechasPromocion) result; 
	}
	
	@Override
	@Interceptors(UserInterceptor.class)
	public ResultValidarCotizacion validarCotizacion(ParamValidarCotizacion param) {
		log.debug("validarCotizacion start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("validarCotizacion end");
		return (ResultValidarCotizacion) result;
	}
	
	@Override
	@Interceptors(UserInterceptor.class)
	public ResultEmision emitirPoliza(ParamEmision param) {
		log.debug("emitirPoliza start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("emitirPoliza end");
		return (ResultEmision) result;
	}


}
