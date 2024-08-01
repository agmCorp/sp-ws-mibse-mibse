package uy.com.bse.serviciosEJB;

import javax.ejb.Local;

import uy.com.bse.cotizaciones.operaciones.*;
import uy.com.bse.servicios.rector.emision.ParamEmision;
import uy.com.bse.servicios.rector.emision.ParamValidarCotizacion;
import uy.com.bse.servicios.rector.emision.ResultEmision;
import uy.com.bse.servicios.rector.emision.ResultValidarCotizacion;
import uy.com.bse.utilitario.dato.ResultGenerico;

@Local
public interface CotOperacionesLocal {

	ResultNuevaCotizacion nuevaCotizacion(ParamNuevaCotizacion param);

	/**
	 * Resuelve la funcionalidad del boton Copiar del form CREF10010
	 * 
	 * @param param
	 * @return
	 */
	ResultCopiarCotizaPoli copiarCotizaPoli(ParamCopiarCotizaPoli param);

	/**
	 * Resuelve la funcionalidad del boton Anular del CREF10030
	 * 
	 * @param param
	 * @return
	 */
	ResultAnularCertificado anularCertificado(ParamAnularCertificado param);

	/**
	 * Permite agregar un bien en una cotización siempre y cuando la
	 * configuracion del ramo-producto lo permita
	 * 
	 * @param param
	 * @return
	 */
	ResultAgregarBien agregarBien(ParamAgregarBien param);

	/**
	 * Resuelve la funcionalidad del boton Nuevo del CREF10010 cuando la
	 * cotizacion ya existe y se la esta modificando
	 * 
	 * @param param
	 * @return
	 */
	ResultAgregarCertificado agregarCertificado(ParamAgregarCertificado param);

	/**
	 * Permite quitar un bien en una cotizacion siempre y cuando la
	 * configuración del ramo-producto lo permita
	 * 
	 * @param param
	 * @return
	 */
	ResultQuitarBien quitarBien(ParamQuitarBien param);

	/**
	 * Permite anular una cotizacion
	 * 
	 * @param param
	 * @return
	 */
	ResultAnularCotizacion anularCotizacion(ParamAnularCotizacion param);

	/**
	 * Permite consultar si se puede agregar un bien
	 * 
	 * @param param
	 * @return
	 */
	ResultHabilitoAgregarBien habilitoAgregarBien(ParamHabilitoAgregarBien param);

	/**
	 * Permite consultar si se puede quitar un bien
	 * 
	 * @param param
	 * @return
	 */
	ResultHabilitoQuitarBien habilitoQuitarBien(ParamHabilitoQuitarBien param);

	/**
	 * Permite consultar si esta disponible el boton lista de bienes
	 * 
	 * @param param
	 * @return
	 */
	ResultHabilitoListaBienes habilitoListaBienes(ParamHabilitoListaBienes param);

	/**
	 * Determina si se puede habilitar la opción de Ubicación que permitirá
	 * ingresar una ubicación para un bien.
	 * 
	 * @param param
	 * @return
	 */
	ResultHabilitoUbicacion habilitoUbicacion(ParamHabilitoUbicacion param);

	/**
	 * Determina si se puede habilitar la opción de Beneficiarios que permitirá
	 * ingresar los datos de un beneficiario a un bien a un certificado de una
	 * cotización.
	 * 
	 * @param param
	 * @return
	 */
	ResultHabilitoBeneficiarios habilitoBeneficiarios(ParamHabilitoBeneficiarios param);

	/**
	 * Permite ingresar la ubicacion de un bien.
	 * 
	 * @param param
	 * @return
	 */
	ResultAltaUbicacionBien altaUbicacionBien(ParamAltaUbicacionBien param);

	/**
	 * Permite realizar la baja de la ubicación de un bien.
	 * 
	 * @param param
	 * @return
	 */
	ResultBajaUbicacionBien bajaUbicacionBien(ParamBajaUbicacionBien param);

	/**
	 * Permite modificar la ubicacion de un bien.
	 * 
	 * @param param
	 * @return
	 */
	ResultModificarUbicacionBien modificarUbicacionBien(ParamModificarUbicacionBien param);

	ResultValidarDatos validarDatos(ParamValidarDatos param);

	ResultActualizarDatos actualizarDatos(ParamActualizarDatos param);

	ResultCalcularCotizacion calcularCotizacion(ParamCalcularCotizacion param);

	ResultGuardarCoberturas guardarCoberturas(ParamGuardarCoberturas param);

	/**
	 * Determina si se debe habilitar la vigencia técnica
	 * 
	 * @param param
	 * @return
	 */
	ResultHabilitoVigenciaTecnica habilitoVigenciaTecnica(ParamHabilitoVigenciaTecnica param);

	/**
	 * Ingresa los datos del beneficiario
	 * 
	 * @param param
	 * @return
	 */
	ResultAltaBeneficiario altaBeneficiario(ParamAltaBeneficiario param);

	/**
	 * Realiza la modificación de los datos de un beneficiario
	 * 
	 * @param param
	 * @return
	 */
	ResultModificarBeneficiario modificarBeneficiario(ParamModificarBeneficiario param);

	/**
	 * Realiza la baja de un beneficiario
	 * 
	 * @param param
	 * @return
	 */
	ResultBajaBeneficiario bajaBeneficiario(ParamBajaBeneficiario param);

	/**
	 * 
	 * @param param
	 * @return Realiza el alta de un objeto en la lista de bienes de un bien
	 */
	ResultAltaListaBienes altaListaBienes(ParamAltaListaBienes param);

	/**
	 * 
	 * @param param
	 * @return Realiza la baja de un objeto en la lista de bienes de un bien
	 */
	ResultBajaListaBienes bajaListaBienes(ParamBajaListaBienes param);

	/**
	 * 
	 * @param param
	 * @return Realiza la modificacion de un objeto en la lista de bienes de un
	 *         bien
	 */
	ResultModificarListaBienes modificarListaBienes(ParamModificarListaBienes param);

	/**
	 * 
	 * @param param
	 * @return Realiza la renovación manual de una póliza
	 */
	ResultRenovacionManual renovacionManual(ParamRenovacionManual param);

	/**
	 * 
	 * @param param
	 * @return Permite ingresar una nota en una póliza
	 */
	ResultIngresarNotaPoliza ingresarNotaPoliza(ParamIngresarNotaPoliza param);

	/**
	 * 
	 * @param param
	 * @return Determina si se puede habilitar la opción de Beneficiarios, Lista
	 *         Bienes, Ubicación, Agregar Bien y Quitar Bien en una cotización
	 */
	ResultHabilitoBotones habilitoBotones(ParamHabilitoBotones param);

	/**
	 * 
	 * @param param
	 * @return Valida si la cotización acumula riesgos, en caso de acumular
	 *         devuelve el listado de cotizaciones que acumulan riesgos.
	 * */
	ResultValidarAcumulaRiesgos validarAcumulaRiesgos(ParamValidarAcumulaRiesgos param);

	/**
	 * 
	 * @param param
	 * @return Permite ingresar un texto a una cotización.
	 * */
	ResultAltaTextoCotizacion altaTextoCotizacion(ParamAltaTextoCotizacion param);

	/**
	 * 
	 * @param param
	 * @return Permite modificar un texto a una cotización.
	 * */
	ResultModificarTextoCotizacion modificarTextoCotizacion(ParamModificarTextoCotizacion param);

	/**
	 * 
	 * @param param
	 * @return Permite agregar un acreedor a un bien.
	 * */
	ResultAgregarAcreedorXBien agregarAcreedorXBien(ParamAgregarAcreedorXBien param);

	/**
	 * 
	 * @param param
	 * @return Permite modificar un acreedor a un bien.
	 * */
	ResultModificarAcreedorXBien modificarAcreedorXBien(ParamModificarAcreedorXBien param);

	/**
	 * 
	 * @param param
	 * @return Permite quitar un acreedor de un bien de una cotización.
	 * */
	ResultQuitarAcreedorXBien quitarAcreedorXBien(ParamQuitarAcreedorXBien param);

	/**
	 * 
	 * @param param
	 * @return Calcula y valida las fechas de vigencia y vigencia técnica para
	 *         una nueva cotización.
	 * */
	ResultCalcularValidarVigencia calcularValidarVigencia(ParamCalcularValidarVigencia param);

	/**
	 * 
	 * @param param
	 * @return Guarda los planes de cobertura y planes de pago de cada
	 *         certificado de una cotizacion.
	 * */
	ResultGuardarPlanes guardarPlanes(ParamGuardarPlanes param);

	/**
	 * 
	 * @param param
	 * @return Actualiza las direcciones de envío y cobro del contratante,
	 *         asociadas a la cotización.
	 * */
	ResultActualizarDirCotizacion actualizarDireccionesCotizacion(ParamActualizarDirCotizacion param);

	ResultActualizarCoberturaBien actualizarCoberturaBien(ParamActualizarCoberturaBien param);

	ResultCalcularCobertura calcularCobertura(ParamCalcularCobertura param);

	ResultCalcularCertificado calcularCertificado(ParamCalcularCertificado param);

	ResultGuardarAsegurado guardarAsegurado(ParamGuardarAsegurado param);

	ResultActualizarCertificadoCero actualizarCertificadoCero(ParamActualizarCertificadoCero param);

	ResultGenerico setearPlanCobertura(ParamSetearPlanCobertura param);

	ResultActualizarDatosBancarios actualizarDatosBancarios(ParamActualizarDatosBancarios param);

	ResultEnviarCotizacionFueraPauta enviarCotizacionFueraPauta(ParamEnviarCotizacionFueraPauta param);

	ResultModificarOpcionesFacturacion modificarOpcionesFacturacion(ParamModificarOpcionesFacturacion param);

	ResultValidarDatosVehiculo validarDatosVehiculo(ParamValidarDatosVehiculo param);

	ResultCalcularCuotasVehiculo calcularCuotasVehiculo(ParamCalcularCuotasVehiculo param);

	ResultNuevaCotizacionVehiculo nuevaCotizacionVehiculo(ParamNuevaCotizacionVehiculo param);

	ResultActualizarPromocionVehiculo actualizarPromocionVehiculo(ParamActualizarPromocionVehiculo param);

	ResultGenerico actualizarCertificadoCeroVehiculo(ParamActualizarCertificadoCeroVehiculo param);

	ResultConfigurarFechasPromocion configurarFechasPromocion(ParamConfigurarFechasPromocion param);

	ResultValidarCotizacion validarCotizacion(ParamValidarCotizacion param);

	ResultEmision emitirPoliza(ParamEmision param);

}
