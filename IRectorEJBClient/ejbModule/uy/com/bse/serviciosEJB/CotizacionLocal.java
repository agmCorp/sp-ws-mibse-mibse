package uy.com.bse.serviciosEJB;

import javax.ejb.Local;

import uy.com.bse.cotizaciones.consultas.ParamBonificacionNS;
import uy.com.bse.cotizaciones.consultas.ParamCertificados;
import uy.com.bse.cotizaciones.consultas.ParamClientes;
import uy.com.bse.cotizaciones.consultas.ParamCotizaciones;
import uy.com.bse.cotizaciones.consultas.ParamDatoCotizacion;
import uy.com.bse.cotizaciones.consultas.ParamDatoParametrico;
import uy.com.bse.cotizaciones.consultas.ParamDatosCertificados;
import uy.com.bse.cotizaciones.consultas.ParamDatosModificacion;
import uy.com.bse.cotizaciones.consultas.ParamDetalleBusquedaCotizacion;
import uy.com.bse.cotizaciones.consultas.ParamDetalleTextoCotizacion;
import uy.com.bse.cotizaciones.consultas.ParamEncabezadoCotizacion;
import uy.com.bse.cotizaciones.consultas.ParamFueraPautas;
import uy.com.bse.cotizaciones.consultas.ParamListaPlanesPagoRen;
import uy.com.bse.cotizaciones.consultas.ParamObtenerAcreedoresXBien;
import uy.com.bse.cotizaciones.consultas.ParamObtenerAnexosCotizacion;
import uy.com.bse.cotizaciones.consultas.ParamObtenerBeneficiarios;
import uy.com.bse.cotizaciones.consultas.ParamObtenerBienes;
import uy.com.bse.cotizaciones.consultas.ParamObtenerCabezalCotizaciones;
import uy.com.bse.cotizaciones.consultas.ParamObtenerClientesSolicitante;
import uy.com.bse.cotizaciones.consultas.ParamObtenerCoberturasXBien;
import uy.com.bse.cotizaciones.consultas.ParamObtenerCoberturasXCertificado;
import uy.com.bse.cotizaciones.consultas.ParamObtenerComponentes;
import uy.com.bse.cotizaciones.consultas.ParamObtenerContratanteAsegurado;
import uy.com.bse.cotizaciones.consultas.ParamObtenerDatosBancarios;
import uy.com.bse.cotizaciones.consultas.ParamObtenerDatosBasicosCotiza;
import uy.com.bse.cotizaciones.consultas.ParamObtenerDatosPreviosEndoso;
import uy.com.bse.cotizaciones.consultas.ParamObtenerDatosRenovacion;
import uy.com.bse.cotizaciones.consultas.ParamObtenerDetalleCuota;
import uy.com.bse.cotizaciones.consultas.ParamObtenerDomiciliosCliente;
import uy.com.bse.cotizaciones.consultas.ParamObtenerFechaEmision;
import uy.com.bse.cotizaciones.consultas.ParamObtenerFranquicias;
import uy.com.bse.cotizaciones.consultas.ParamObtenerListaBienes;
import uy.com.bse.cotizaciones.consultas.ParamObtenerNotasPoliza;
import uy.com.bse.cotizaciones.consultas.ParamObtenerPlanesCobertura;
import uy.com.bse.cotizaciones.consultas.ParamObtenerPlanesPago;
import uy.com.bse.cotizaciones.consultas.ParamObtenerResumenPlanesCobertura;
import uy.com.bse.cotizaciones.consultas.ParamObtenerTextoClausula;
import uy.com.bse.cotizaciones.consultas.ParamObtenerUbicacionBien;
import uy.com.bse.cotizaciones.consultas.ParamParentesco;
import uy.com.bse.cotizaciones.consultas.ParamPersonaSeleccionada;
import uy.com.bse.cotizaciones.consultas.ParamTextosCotizacion;
import uy.com.bse.cotizaciones.consultas.ParamVigenciaTecnica;
import uy.com.bse.cotizaciones.consultas.ResultBonificacionNS;
import uy.com.bse.cotizaciones.consultas.ResultCertificados;
import uy.com.bse.cotizaciones.consultas.ResultClientes;
import uy.com.bse.cotizaciones.consultas.ResultCotizaciones;
import uy.com.bse.cotizaciones.consultas.ResultDatoParametrico;
import uy.com.bse.cotizaciones.consultas.ResultDatosCertificados;
import uy.com.bse.cotizaciones.consultas.ResultDatosCotizacion;
import uy.com.bse.cotizaciones.consultas.ResultDatosModificacion;
import uy.com.bse.cotizaciones.consultas.ResultDetalleTextoCotizacion;
import uy.com.bse.cotizaciones.consultas.ResultEncabezadoCotizacion;
import uy.com.bse.cotizaciones.consultas.ResultFueraPautas;
import uy.com.bse.cotizaciones.consultas.ResultListaPlanesPagoRen;
import uy.com.bse.cotizaciones.consultas.ResultObtenerAcreedoresXBien;
import uy.com.bse.cotizaciones.consultas.ResultObtenerAnexosCotizacion;
import uy.com.bse.cotizaciones.consultas.ResultObtenerBeneficiarios;
import uy.com.bse.cotizaciones.consultas.ResultObtenerBienes;
import uy.com.bse.cotizaciones.consultas.ResultObtenerCabezalCotizaciones;
import uy.com.bse.cotizaciones.consultas.ResultObtenerClientesSolicitante;
import uy.com.bse.cotizaciones.consultas.ResultObtenerCoberturasXBien;
import uy.com.bse.cotizaciones.consultas.ResultObtenerCoberturasXCertificado;
import uy.com.bse.cotizaciones.consultas.ResultObtenerComponentes;
import uy.com.bse.cotizaciones.consultas.ResultObtenerContratanteAsegurado;
import uy.com.bse.cotizaciones.consultas.ResultObtenerDatosBancarios;
import uy.com.bse.cotizaciones.consultas.ResultObtenerDatosBasicosCotiza;
import uy.com.bse.cotizaciones.consultas.ResultObtenerDatosPreviosEndoso;
import uy.com.bse.cotizaciones.consultas.ResultObtenerDatosRenovacion;
import uy.com.bse.cotizaciones.consultas.ResultObtenerDetalleBusquedaCotizacion;
import uy.com.bse.cotizaciones.consultas.ResultObtenerDetalleCuota;
import uy.com.bse.cotizaciones.consultas.ResultObtenerDomiciliosCliente;
import uy.com.bse.cotizaciones.consultas.ResultObtenerFechaEmision;
import uy.com.bse.cotizaciones.consultas.ResultObtenerFranquicias;
import uy.com.bse.cotizaciones.consultas.ResultObtenerListaBienes;
import uy.com.bse.cotizaciones.consultas.ResultObtenerNotasPoliza;
import uy.com.bse.cotizaciones.consultas.ResultObtenerPlanesCobertura;
import uy.com.bse.cotizaciones.consultas.ResultObtenerPlanesPago;
import uy.com.bse.cotizaciones.consultas.ResultObtenerResumenPlanesCobertura;
import uy.com.bse.cotizaciones.consultas.ResultObtenerTextoClausula;
import uy.com.bse.cotizaciones.consultas.ResultObtenerUbicacionBien;
import uy.com.bse.cotizaciones.consultas.ResultParentesco;
import uy.com.bse.cotizaciones.consultas.ResultPersonaSeleccionada;
import uy.com.bse.cotizaciones.consultas.ResultTextosCotizacion;
import uy.com.bse.cotizaciones.consultas.ResultVigenciaTecnica;
import uy.com.bse.cotizaciones.lovs.ParamAcreedor;
import uy.com.bse.cotizaciones.lovs.ParamAcreedorObjetos;
import uy.com.bse.cotizaciones.lovs.ParamAcreedoresFiltrados;
import uy.com.bse.cotizaciones.lovs.ParamDatos;
import uy.com.bse.cotizaciones.lovs.ParamDatosXTipoNota;
import uy.com.bse.cotizaciones.lovs.ParamEnPauta;
import uy.com.bse.cotizaciones.lovs.ParamListaBancos;
import uy.com.bse.cotizaciones.lovs.ParamListaGrupos;
import uy.com.bse.cotizaciones.lovs.ParamListaTipoNota;
import uy.com.bse.cotizaciones.lovs.ParamListaTodasSecciones;
import uy.com.bse.cotizaciones.lovs.ParamListaTodosProductos;
import uy.com.bse.cotizaciones.lovs.ParamListaValoresDato;
import uy.com.bse.cotizaciones.lovs.ParamListaValoresDatoVehiculo;
import uy.com.bse.cotizaciones.lovs.ParamMarcasObjeto;
import uy.com.bse.cotizaciones.lovs.ParamMedioPago;
import uy.com.bse.cotizaciones.lovs.ParamModificada;
import uy.com.bse.cotizaciones.lovs.ParamModoCalculo;
import uy.com.bse.cotizaciones.lovs.ParamMoneda;
import uy.com.bse.cotizaciones.lovs.ParamNivelesComision;
import uy.com.bse.cotizaciones.lovs.ParamNivelesComisionProd;

import uy.com.bse.cotizaciones.lovs.ParamObjetos;
import uy.com.bse.cotizaciones.lovs.ParamOrigenPago;
import uy.com.bse.cotizaciones.lovs.ParamProducto;
import uy.com.bse.cotizaciones.lovs.ParamPromocion;
import uy.com.bse.cotizaciones.lovs.ParamRenovacion;
import uy.com.bse.cotizaciones.lovs.ParamRenovacionXProducto;
import uy.com.bse.cotizaciones.lovs.ParamSecciones;
import uy.com.bse.cotizaciones.lovs.ParamTipo;
import uy.com.bse.cotizaciones.lovs.ParamTipoAcreedores;
import uy.com.bse.cotizaciones.lovs.ParamTipoTexto;
import uy.com.bse.cotizaciones.lovs.ParamTiposFacturacion;
import uy.com.bse.cotizaciones.lovs.ParamTiposNotaGenerica;
import uy.com.bse.cotizaciones.lovs.ParamValoresTipoNota;
import uy.com.bse.cotizaciones.lovs.ParamVigencia;
import uy.com.bse.cotizaciones.lovs.ResultAcreedor;
import uy.com.bse.cotizaciones.lovs.ResultAcreedorObjetos;
import uy.com.bse.cotizaciones.lovs.ResultDatos;
import uy.com.bse.cotizaciones.lovs.ResultDatosXTipoNota;
import uy.com.bse.cotizaciones.lovs.ResultListaBancos;
import uy.com.bse.cotizaciones.lovs.ResultListaEnPauta;
import uy.com.bse.cotizaciones.lovs.ResultListaGrupos;
import uy.com.bse.cotizaciones.lovs.ResultListaMarcasObjeto;
import uy.com.bse.cotizaciones.lovs.ResultListaMedioPago;
import uy.com.bse.cotizaciones.lovs.ResultListaModificada;
import uy.com.bse.cotizaciones.lovs.ResultListaModoCalculo;
import uy.com.bse.cotizaciones.lovs.ResultListaMoneda;
import uy.com.bse.cotizaciones.lovs.ResultListaObjetos;
import uy.com.bse.cotizaciones.lovs.ResultListaOrigenPago;
import uy.com.bse.cotizaciones.lovs.ResultListaProducto;
import uy.com.bse.cotizaciones.lovs.ResultListaPromocion;
import uy.com.bse.cotizaciones.lovs.ResultListaRenovacion;
import uy.com.bse.cotizaciones.lovs.ResultListaSecciones;
import uy.com.bse.cotizaciones.lovs.ResultListaTipo;
import uy.com.bse.cotizaciones.lovs.ResultListaTipoNota;
import uy.com.bse.cotizaciones.lovs.ResultListaTiposFacturacion;
import uy.com.bse.cotizaciones.lovs.ResultListaTodasSecciones;
import uy.com.bse.cotizaciones.lovs.ResultListaTodosProductos;
import uy.com.bse.cotizaciones.lovs.ResultListaValoresDato;
import uy.com.bse.cotizaciones.lovs.ResultListaVigencia;
import uy.com.bse.cotizaciones.lovs.ResultRenovacionXProducto;
import uy.com.bse.cotizaciones.lovs.ResultTipoAcreedores;
import uy.com.bse.cotizaciones.lovs.ResultTipoTexto;
import uy.com.bse.cotizaciones.lovs.ResultTiposNotaGenerica;
import uy.com.bse.cotizaciones.lovs.ResultValoresTipoNota;
import uy.com.bse.utilitario.dato.ResultCodiguera;

@Local
public interface CotizacionLocal {

	/**
	 * 
	 * @param param
	 * @return una lista de secciones
	 */
	ResultListaSecciones listaSecciones(ParamSecciones param);

	/**
	 * 
	 * @param param
	 * @return una lista de productos
	 */
	ResultListaProducto listaProducto(ParamProducto param);

	/**
	 * 
	 * @param param
	 * @return una lista de tipos
	 */
	ResultListaTipo listaTipo(ParamTipo param);

	/**
	 * 
	 * @param param
	 * @return una lista de modificadas
	 */
	ResultListaModificada listaModificada(ParamModificada param);

	/**
	 * 
	 * @param param
	 * @return una lista de en pauta
	 */
	ResultListaEnPauta listaEnPauta(ParamEnPauta param);

	/**
	 * 
	 * @param param
	 * @return una lista de tipos de facturacion
	 */
	ResultListaTiposFacturacion listaTiposFacturacion(ParamTiposFacturacion param);

	/**
	 * 
	 * @param param
	 * @return una lista de medios de pago
	 */
	ResultListaMedioPago listaMedioPago(ParamMedioPago param);

	/**
	 * 
	 * @param param
	 * @return una lista de origenes de pago
	 */
	ResultListaOrigenPago listaOrigenPago(ParamOrigenPago param);

	/**
	 * 
	 * @param param
	 * @return una lista de promociones
	 */
	ResultListaPromocion listaPromocion(ParamPromocion param);

	/**
	 * 
	 * @param param
	 * @return una lista de renovaciones
	 */
	ResultListaRenovacion listaRenovacion(ParamRenovacion param);

	/**
	 * 
	 * @param param
	 * @returnuna lista de monedas
	 */
	ResultListaMoneda listaMoneda(ParamMoneda param);

	/**
	 * 
	 * @param param
	 * @return una lista de modo de calculo
	 */
	ResultListaModoCalculo listaModoCalculo(ParamModoCalculo param);

	/**
	 * 
	 * @param param
	 * @return una lista de vigencia
	 */
	ResultListaVigencia listaVigencia(ParamVigencia param);

	/**
	 * 
	 * @param param
	 * @return Lista los posibles valores que puede tener un dato paramétrico de
	 *         un bien de una cotización
	 */
	ResultListaValoresDato listaValoresDatoParametrico(ParamListaValoresDato param);

	/**
	 * 
	 * @param param
	 * @return Una lista de los datos correspondientes
	 */
	ResultDatos listaDatos(ParamDatos param);

	/**
	 * 
	 * @return una lista de clientes
	 */
	ResultClientes obtenerClientes(ParamClientes param);

	/**
	 * 
	 * @return una lista de fuera de pautas
	 */
	ResultFueraPautas obtenerFueraPautas(ParamFueraPautas param);

	/**
	 * 
	 * @return una lista de bonificaciones
	 */
	ResultBonificacionNS obtenerBonificacionNS(ParamBonificacionNS param);

	/**
	 * 
	 * @param param
	 * @return una lista de cotizaciones filtradas por los parametros ingresados
	 */
	ResultCotizaciones obtenerCotizaciones(ParamCotizaciones param);

	/**
	 * 
	 * @param param
	 * @return Los datos correspondientes a una cotizacion
	 */
	ResultDatosCotizacion obtenerDatosCotizacion(ParamDatoCotizacion param);

	/**
	 * 
	 * @param param
	 * @return Los bienes de una cotizacion
	 */
	ResultCertificados obtenerCertificados(ParamCertificados param);

	/**
	 * 
	 * @param param
	 * @return Los bienes de una cotizacion que contienen el dato parametrico
	 */
	ResultDatoParametrico obtenerDatoParametrico(ParamDatoParametrico param);

	/**
	 * 
	 * @param param
	 * @return Los datos del certificado seleccionado
	 */
	ResultDatosCertificados obtenerDatosCertificados(ParamDatosCertificados param);

	/**
	 * 
	 * @param param
	 * @return
	 */
	ResultObtenerBienes obtenerBienesParaAgregar(ParamObtenerBienes param);

	/**
	 * 
	 * @param param
	 * @return Dado un ramo y un producto, devuelve los datos básicos para el
	 *         certificado 0 de la cotización.
	 */
	ResultObtenerDatosBasicosCotiza obtenerDatosBasicosCotizacion(ParamObtenerDatosBasicosCotiza param);

	ResultObtenerUbicacionBien obtenerUbicacionBien(ParamObtenerUbicacionBien param);

	ResultVigenciaTecnica listaVigenciaTecnica(ParamVigenciaTecnica param);

	ResultParentesco listaParentesco(ParamParentesco param);

	ResultObtenerBeneficiarios obtenerBeneficiarios(ParamObtenerBeneficiarios param);

	/**
	 * 
	 * @param param
	 * @return Devuelve una lista de objetos
	 */
	ResultListaObjetos listaObjetos(ParamObjetos param);

	/**
	 * 
	 * @param param
	 * @return Devuelve una lista de marcas de objetos
	 */
	ResultListaMarcasObjeto listaMarcasObjeto(ParamMarcasObjeto param);

	/**
	 * 
	 * @param param
	 * @return Devuelve una lista de bienes
	 */
	ResultObtenerListaBienes obtenerListaBienes(ParamObtenerListaBienes param);

	/**
	 * 
	 * @param param
	 * @return Devuelve una lista de grupos
	 */
	ResultListaGrupos listaGrupos(ParamListaGrupos param);

	/**
	 * 
	 * @param param
	 * @return Devuelve una lista de tipos de nota
	 */
	ResultListaTipoNota listaTipoNota(ParamListaTipoNota param);

	/**
	 * 
	 * @param param
	 * @return Devuelve los anexos de una cotizacion
	 */
	ResultObtenerAnexosCotizacion obtenerAnexosCotizacion(ParamObtenerAnexosCotizacion param);

	/**
	 * 
	 * @param param
	 * @return Devuelve la lista de los bancos
	 */
	ResultListaBancos listaBancos(ParamListaBancos param);

	/**
	 * 
	 * @param param
	 * @return Devuelve las componentes de una cotizacion luego de calculada
	 */
	ResultObtenerComponentes obtenerComponentes(ParamObtenerComponentes param);

	/**
	 * 
	 * @param param
	 * @return Devuelve las franquicias de una cotizacion
	 */
	ResultObtenerFranquicias obtenerFranquicias(ParamObtenerFranquicias param);

	/**
	 * 
	 * @param param
	 * @return Devuelve el texto de una cláusula de una nota de una póliza
	 */
	ResultObtenerTextoClausula obtenerTextoClausula(ParamObtenerTextoClausula param);

	/**
	 * 
	 * @param param
	 * @return Dependiendo del tipo de nota que se le ingrese devuelve el lov
	 *         asociado a esa nota
	 */
	ResultTiposNotaGenerica listaGenericaXTiposNota(ParamTiposNotaGenerica param);

	/**
	 * 
	 * @param param
	 * @return Obtiene las notas de renovación de una póliza dado el enlace de
	 *         la misma
	 */
	ResultObtenerNotasPoliza obtenerNotasPoliza(ParamObtenerNotasPoliza param);

	/**
	 * 
	 * @param param
	 * @return Obtiene los datos de la renovación e internamente genera la
	 *         cotización
	 */
	ResultObtenerDatosRenovacion obtenerDatosRenovacion(ParamObtenerDatosRenovacion param);

	/**
	 * 
	 * @param param
	 * @return Obtiene los clientes de la solapa Solicitante del CREF10010.
	 */
	ResultObtenerClientesSolicitante obtenerClientesSolicitante(ParamObtenerClientesSolicitante param);

	/**
	 * 
	 * @param param
	 * @return Obtiene los planes de pago válidos para una cotización y un
	 *         certificado
	 */
	ResultObtenerPlanesPago obtenerPlanesPago(ParamObtenerPlanesPago param);

	/**
	 * 
	 * @param param
	 * @return Obtiene datos paramétricos que se pueden modificar de un bien o
	 *         de todos los bienes de un certificado.
	 */
	ResultDatosModificacion obtenerDatosModificacion(ParamDatosModificacion param);

	/**
	 * 
	 * @param param
	 * @return Lista tipos de texto que se pueden ingresar en una cotización,
	 *         según el ramo de la misma
	 */
	ResultTipoTexto listaTipoTexto(ParamTipoTexto param);

	/**
	 * 
	 * @param param
	 * @return Permite obtener los textos de una cotización.
	 */
	ResultTextosCotizacion obtenerTextosCotizacion(ParamTextosCotizacion param);

	/**
	 * 
	 * @param param
	 * @return Permite obtener el detalle de un texto de una cotización.
	 */
	ResultDetalleTextoCotizacion obtenerDetalleTextoCotizacion(ParamDetalleTextoCotizacion param);

	/**
	 * 
	 * @param param
	 * @return Permite obtener el encabezado de una cotización.
	 */
	ResultEncabezadoCotizacion obtenerEncabezadoCotizacion(ParamEncabezadoCotizacion param);

	/**
	 * 
	 * @param param
	 * @return Lista los acreedores que se pueden ingresar en una cotización.
	 */
	ResultAcreedor listaAcreedores(ParamAcreedor param);

	/**
	 * 
	 * @param param
	 * @return Lista los objetos en los cuales el acreedor puede tener un
	 *         porcentaje de participación.
	 */
	ResultAcreedorObjetos listaAcreedorObjetos(ParamAcreedorObjetos param);

	/**
	 * 
	 * @param param
	 * @return Lista los tipos de acreedores que se pueden agregar a un bien.
	 */
	ResultTipoAcreedores listaTipoAcreedores(ParamTipoAcreedores param);

	/**
	 * 
	 * @param param
	 * @return Permite obtener los acreedores de un bien de un certificado de
	 *         una cotización.
	 */
	ResultObtenerAcreedoresXBien obtenerAcreedoresXBien(ParamObtenerAcreedoresXBien param);

	/**
	 * 
	 * @param param
	 * @return Obtiene los datos de la póliza, información del certificado 0 y
	 *         del contratante para la cotización de endoso.
	 */
	ResultObtenerDatosPreviosEndoso obtenerDatosPreviosEndoso(ParamObtenerDatosPreviosEndoso param);

	/**
	 * 
	 * @param param
	 * @return Permite consultar los domicilios del cliente, se utilizará para
	 *         poder elegir la dirección de envío y cobro del contratante en el
	 *         certificado 0 de la cotización
	 */
	ResultObtenerDomiciliosCliente obtenerDomiciliosCliente(ParamObtenerDomiciliosCliente param);

	/**
	 * 
	 * @param param
	 * @return Permite buscar un cliente por distintos filtros de búsqueda o
	 *         todos. Está búsqueda será utilizada para poder encontrar al
	 *         cliente que se desea tener como contratante o asegurado en una
	 *         cotización.
	 */
	ResultObtenerContratanteAsegurado obtenerContratanteAsegurado(ParamObtenerContratanteAsegurado param);

	/**
	 * 
	 * @param param
	 * @return Obtiene los datos de la persona seleccionada dependiendo si es
	 *         asegurado o contratante.
	 */
	ResultPersonaSeleccionada obtenerPersonaSeleccionada(ParamPersonaSeleccionada param);

	ResultDatosXTipoNota listaDatosXTipoNota(ParamDatosXTipoNota param);

	ResultValoresTipoNota listaValoresTipoNota(ParamValoresTipoNota param);

	ResultObtenerPlanesCobertura obtenerPlanesCobertura(ParamObtenerPlanesCobertura param);

	ResultObtenerDetalleCuota obtenerDetalleCuotas(ParamObtenerDetalleCuota param);

	ResultObtenerResumenPlanesCobertura obtenerResumenPlanesCobertura(ParamObtenerResumenPlanesCobertura param);

	ResultRenovacionXProducto obtenerRenovacionXProducto(ParamRenovacionXProducto param);

	/**
	 * 
	 * @param param
	 * @return una lista de secciones
	 */
	ResultListaTodasSecciones listaTodasSecciones(ParamListaTodasSecciones param);

	/**
	 * 
	 * @param param
	 * @return una lista de productos
	 */
	ResultListaTodosProductos listaTodosProductos(ParamListaTodosProductos param);

	ResultObtenerCoberturasXCertificado obtenerCoberturasXCertificado(ParamObtenerCoberturasXCertificado param);

	ResultObtenerDatosBancarios obtenerDatosBancarios(ParamObtenerDatosBancarios param);

	ResultObtenerFechaEmision obtenerFechaEmision(ParamObtenerFechaEmision param);
	
	ResultObtenerCoberturasXBien obtenerCoberturasXBien(ParamObtenerCoberturasXBien param);
	
	ResultListaValoresDato listaValoresDatoParametricoVehiculo(ParamListaValoresDatoVehiculo param);
	
	ResultAcreedor listaAcreedoresFiltrados(ParamAcreedoresFiltrados param);
	
	ResultObtenerCabezalCotizaciones obtenerCabezalCotizaciones(ParamObtenerCabezalCotizaciones param);
	
	ResultObtenerDetalleBusquedaCotizacion obtenerDetalleBusquedaCotizacion(ParamDetalleBusquedaCotizacion param);
	
	ResultListaPlanesPagoRen listaPlanesPagoRenovacion(ParamListaPlanesPagoRen param);
	
	 ResultCodiguera listaNivelesComision( ParamNivelesComision param);

	ResultCodiguera listaNivelesComisionProd(ParamNivelesComisionProd param);

}
