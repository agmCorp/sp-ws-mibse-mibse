package uy.com.bse.cotizaciones.operaciones.ws;

import javax.jws.WebMethod;
import javax.jws.WebService;

import uy.com.bse.cotizaciones.operaciones.*;
import uy.com.bse.utilitario.dato.ResultGenerico;

@WebService
public interface IWsCotOperaciones {
	
	@WebMethod
	public ResultNuevaCotizacion nuevaCotizacion(ParamNuevaCotizacion param);
	
	/**
	 * Resuelve la funcionalidad del boton Copiar del form CREF10010
	 * @param param
	 * @return
	 */
	@WebMethod
	public ResultCopiarCotizaPoli copiarCotizaPoli (ParamCopiarCotizaPoli param);
	
	/**
	 * Resuelve la funcionalidad del boton Anular del CREF10030
	 * @param param
	 * @return
	 */
	@WebMethod
	public ResultAnularCertificado anularCertificado (ParamAnularCertificado param);
	
	/**
	 * Permite agregar un bien en una cotización siempre y cuando la configuracion del 
	 * ramo-producto lo permita
	 * @param param
	 * @return
	 */
	@WebMethod
	public ResultAgregarBien agregarBien(ParamAgregarBien param);
	
	/**
	 * Resuelve la funcionalidad del boton Nuevo del CREF10010 cuando la cotizacion ya existe y se la esta modificando
	 * @param param
	 * @return
	 */
	@WebMethod
	public ResultAgregarCertificado	agregarCertificado(ParamAgregarCertificado param);
	
	/**
	 * Permite quitar un bien en una cotizacion siempre y cuando la configuración del ramo-producto lo permita
	 * @param param
	 * @return
	 */
	@WebMethod
	public ResultQuitarBien quitarBien (ParamQuitarBien param);
	
	/**
	 * Permite anular una cotizacion
	 * @param param
	 * @return
	 */
	@WebMethod
	public ResultAnularCotizacion anularCotizacion(ParamAnularCotizacion param);
	
	/**
	 * Permite consultar si se puede agregar un bien
	 * @param param
	 * @return
	 */
	@WebMethod
	public ResultHabilitoAgregarBien habilitoAgregarBien(ParamHabilitoAgregarBien param);
	
	/**
	 * Permite consultar si se puede quitar un bien
	 * @param param
	 * @return
	 */
	@WebMethod
	public ResultHabilitoQuitarBien habilitoQuitarBien(ParamHabilitoQuitarBien param);
	
	/**
	 * Permite consultar si esta disponible el boton lista de bienes
	 * @param param
	 * @return
	 */
	@WebMethod
	public ResultHabilitoListaBienes habilitoListaBienes(ParamHabilitoListaBienes param);
	
	/**
	 * Determina si se puede habilitar la opción de Ubicación que permitirá ingresar una ubicación para un bien. 
	 * @param param
	 * @return
	 */
	@WebMethod
	public ResultHabilitoUbicacion habilitoUbicacion (ParamHabilitoUbicacion param);
	
	/**
	 * Determina si se puede habilitar la opción de Beneficiarios que permitirá ingresar los datos de un beneficiario 
	 * a un bien a un certificado de una cotización.
	 * @param param
	 * @return
	 */
	@WebMethod
	public ResultHabilitoBeneficiarios habilitoBeneficiarios (ParamHabilitoBeneficiarios param);
	
	/**
	 * Permite ingresar la ubicacion de un bien.
	 * @param param
	 * @return
	 */
	@WebMethod
	public ResultAltaUbicacionBien altaUbicacionBien (ParamAltaUbicacionBien param);
	
	/**
	 * Permite realizar la baja de la ubicación de un bien. 
	 * @param param
	 * @return
	 */
	@WebMethod
	public ResultBajaUbicacionBien bajaUbicacionBien(ParamBajaUbicacionBien param);
	
	/**
	 * Permite modificar la ubicacion de un bien.
	 * @param param
	 * @return
	 */
	
	@WebMethod
	public ResultModificarUbicacionBien modificarUbicacionBien (ParamModificarUbicacionBien param);


	@WebMethod
	public ResultValidarDatos validarDatos (ParamValidarDatos param);
	
	
	@WebMethod
	public ResultActualizarDatos actualizarDatos (ParamActualizarDatos param);
	
	
	@WebMethod
	public ResultCalcularCotizacion calcularCotizacion (ParamCalcularCotizacion param);
	
	
	@WebMethod
	public ResultGuardarCoberturas guardarCoberturas (ParamGuardarCoberturas param);
	
	/**
	 * Determina si se debe habilitar la vigencia técnica 
	 * @param param
	 * @return
	 */
	
	@WebMethod
	public ResultHabilitoVigenciaTecnica habilitoVigenciaTecnica(ParamHabilitoVigenciaTecnica param);
	
	/**
	 * Ingresa los datos del beneficiario 
	 * @param param
	 * @return
	 */
	
	@WebMethod	
	public ResultAltaBeneficiario altaBeneficiario (ParamAltaBeneficiario param);
	
	/**
	 * Realiza la modificación de los datos de un beneficiario
	 * @param param
	 * @return
	 */
	@WebMethod
	public ResultModificarBeneficiario modificarBeneficiario (ParamModificarBeneficiario param);
	
	/**
	 * Realiza la baja de un beneficiario
	 * @param param
	 * @return
	 */
	@WebMethod
	public ResultBajaBeneficiario bajaBeneficiario (ParamBajaBeneficiario param);
	
	/**
	 * 
	 * @param param
	 * @return Realiza el alta de un objeto en la lista de bienes de un bien
	 */
	@WebMethod
	public ResultAltaListaBienes altaListaBienes (ParamAltaListaBienes param);
	
	/**
	 * 
	 * @param param
	 * @return Realiza la baja de un objeto en la lista de bienes de un bien
	 */
	@WebMethod
	public ResultBajaListaBienes bajaListaBienes (ParamBajaListaBienes param);
	
	/**
	 * 
	 * @param param
	 * @return Realiza la modificacion de un objeto en la lista de bienes de un bien
	 */
	@WebMethod
	public ResultModificarListaBienes modificarListaBienes (ParamModificarListaBienes param);
	
	/**
	 * 
	 * @param param
	 * @return Realiza la renovación manual de una póliza
	 */
	@WebMethod	
	public ResultRenovacionManual renovacionManual (ParamRenovacionManual param);
	
	/**
	 * 
	 * @param param
	 * @return Permite ingresar una nota en una póliza
	 */	
	@WebMethod
	public ResultIngresarNotaPoliza ingresarNotaPoliza (ParamIngresarNotaPoliza param);
	
	/**
	 * 
	 * @param param
	 * @return Determina si se puede habilitar la opción de Beneficiarios, Lista Bienes, Ubicación, Agregar Bien y Quitar Bien en una cotización
	 */	
	@WebMethod
	public ResultHabilitoBotones habilitoBotones (ParamHabilitoBotones param);
	
	/**
	 * 
	 * @param param
	 * @return Valida si la cotización acumula riesgos, en caso de acumular devuelve el listado de cotizaciones que acumulan riesgos. 
	 * */
	@WebMethod
	public ResultValidarAcumulaRiesgos validarAcumulaRiesgos (ParamValidarAcumulaRiesgos param);
	
	/**
	 * 
	 * @param param
	 * @return	Permite ingresar un texto a una cotización.
	 * */
	@WebMethod
	public ResultAltaTextoCotizacion altaTextoCotizacion (ParamAltaTextoCotizacion param);
	
	/**
	 * 
	 * @param param
	 * @return	Permite modificar un texto a una cotización.
	 * */
	@WebMethod
	public ResultModificarTextoCotizacion modificarTextoCotizacion (ParamModificarTextoCotizacion param);
	
	/**
	 * 
	 * @param param
	 * @return	Permite agregar un acreedor a un bien.
	 * */
	@WebMethod
	public ResultAgregarAcreedorXBien agregarAcreedorXBien (ParamAgregarAcreedorXBien param);
	
	/**
	 * 
	 * @param param
	 * @return	Permite modificar un acreedor a un bien.
	 * */
	@WebMethod
	public ResultModificarAcreedorXBien modificarAcreedorXBien (ParamModificarAcreedorXBien param);
	
	/**
	 * 
	 * @param param
	 * @return Permite quitar un acreedor de un bien de una cotización.
	 * */
	@WebMethod
	public ResultQuitarAcreedorXBien quitarAcreedorXBien (ParamQuitarAcreedorXBien param);
	
	/**
	 * 
	 * @param param
	 * @return Calcula y valida las fechas de vigencia y vigencia técnica para una nueva cotización.
	 * */
	@WebMethod
	public ResultCalcularValidarVigencia calcularValidarVigencia (ParamCalcularValidarVigencia param);
	
	/**
	 * 
	 * @param param
	 * @return Guarda los planes de cobertura y planes de pago de cada certificado de una cotizacion.
	 * */
	@WebMethod
	public ResultGuardarPlanes guardarPlanes (ParamGuardarPlanes param);
	
	/**
	 * 
	 * @param param
	 * @return Actualiza las direcciones de envío y cobro del contratante, asociadas a la cotización. 
	 * */
	@WebMethod
	public ResultActualizarDirCotizacion actualizarDireccionesCotizacion (ParamActualizarDirCotizacion param);
	
	@WebMethod
	public ResultActualizarCoberturaBien actualizarCoberturaBien(ParamActualizarCoberturaBien param);
	
	@WebMethod
	public ResultCalcularCobertura calcularCobertura(ParamCalcularCobertura param);
	
	@WebMethod
	public ResultCalcularCertificado calcularCertificado(ParamCalcularCertificado param);
	
	@WebMethod
	public ResultGuardarAsegurado guardarAsegurado (ParamGuardarAsegurado param);
	
	@WebMethod
	public ResultActualizarCertificadoCero actualizarCertificadoCero (ParamActualizarCertificadoCero param);
	
	@WebMethod
	public ResultGenerico setearPlanCobertura(ParamSetearPlanCobertura param);
	
	@WebMethod
	public ResultActualizarDatosBancarios actualizarDatosBancarios (ParamActualizarDatosBancarios param);
	
	@WebMethod
	public ResultEnviarCotizacionFueraPauta enviarCotizacionFueraPauta (ParamEnviarCotizacionFueraPauta param) ;
	
	@WebMethod
	public ResultModificarOpcionesFacturacion modificarOpcionesFacturacion(ParamModificarOpcionesFacturacion param);
	
	@WebMethod
	public ResultConfigurarFechasPromocion configurarFechasPromocion(ParamConfigurarFechasPromocion param);
	
}