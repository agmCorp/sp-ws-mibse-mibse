package uy.com.bse.genericos.ws;

import javax.jws.WebMethod;
import javax.jws.WebService;

import uy.com.bse.servicios.rector.emision.ParamEmision;
import uy.com.bse.servicios.rector.emision.ParamValidarCotizacion;
import uy.com.bse.servicios.rector.emision.ResultEmision;
import uy.com.bse.servicios.rector.emision.ResultValidarCotizacion;
import uy.com.bse.servicios.rector.interfaces.ParamCodigoPostal;
import uy.com.bse.servicios.rector.interfaces.ParamContactoWeb;
import uy.com.bse.servicios.rector.interfaces.ParamListaActividades;
import uy.com.bse.servicios.rector.interfaces.ParamListaDepartamentos;
import uy.com.bse.servicios.rector.interfaces.ParamListaOrganismos;
import uy.com.bse.servicios.rector.interfaces.ParamListaPaises;
import uy.com.bse.servicios.rector.interfaces.ParamListaProductoresAsignados;
import uy.com.bse.servicios.rector.interfaces.ParamListaProductoresAsignadosVigentes;
import uy.com.bse.servicios.rector.interfaces.ParamListaSucursales;
import uy.com.bse.servicios.rector.interfaces.ParamListaTipoDoc;
import uy.com.bse.servicios.rector.interfaces.ParamLocalidades;
import uy.com.bse.servicios.rector.interfaces.ParamTipoUsuario;
import uy.com.bse.servicios.rector.interfaces.ResultContactoWeb;
import uy.com.bse.servicios.rector.interfaces.ResultListaActividades;
import uy.com.bse.servicios.rector.interfaces.ResultListaCodPostal;
import uy.com.bse.servicios.rector.interfaces.ResultListaDepartamentos;
import uy.com.bse.servicios.rector.interfaces.ResultListaLocalidades;
import uy.com.bse.servicios.rector.interfaces.ResultListaOrganismos;
import uy.com.bse.servicios.rector.interfaces.ResultListaPaises;
import uy.com.bse.servicios.rector.interfaces.ResultListaSucursales;
import uy.com.bse.servicios.rector.interfaces.ResultListaTipoDoc;
import uy.com.bse.servicios.rector.interfaces.ResultTipoUsuario;
import uy.com.bse.utilitario.dato.ResultCodiguera;
import uy.com.bse.utilitario.seguridad.ParamLogout;
import uy.com.bse.utilitario.seguridad.ResultLogout;

@WebService
public interface IWsServiciosRector {
	
	/**
	 * 
	 * @return Un objeto con una lista de documentos
	 */
	@WebMethod
	public ResultListaTipoDoc listaTipoDoc(ParamListaTipoDoc param);
	
	/**
	 * 
	 * @return Un objeto con una lista de organismos
	 */
	@WebMethod
	public ResultListaOrganismos listaOrganismos(ParamListaOrganismos param);
	
	/**
	 * 
	 * @return Un objeto con una lista de actividades
	 */
	@WebMethod
	public ResultListaActividades listaActividades(ParamListaActividades param);
	
	/**
	 * 
	 * @return Un objeto con una lista de paises
	 */
	@WebMethod
	public ResultListaPaises listaPaises(ParamListaPaises param);
	
	/**
	 * 
	 * @return Un objeto con una lista de departamentos
	 */
	@WebMethod
	public ResultListaDepartamentos listaDepartamentos(ParamListaDepartamentos param);
	
	/**
	 * 
	 * @param param
	 * @return Un objeto con una lista de codigos postales
	 */
	@WebMethod
	public ResultListaCodPostal listaCodPostal(ParamCodigoPostal param);
	
	/**
	 * 
	 * @param param
	 * @return Un objeto con una lista de localidades
	 */
	@WebMethod
	public ResultListaLocalidades listaLocalidades(ParamLocalidades param);
	
	/**
	 * 
	 * @return Un objeto con una lista de sucursales
	 */
	@WebMethod
	public ResultListaSucursales listaSucursales(ParamListaSucursales param);
	
	/**
	 * 
	 * @param param
	 * @return El resultado de insertar los datos de contacto
	 */
	@WebMethod
	public ResultContactoWeb altaContactoWeb(ParamContactoWeb param); 
	
	@WebMethod
	public ResultCodiguera listaProductoresAsignados(ParamListaProductoresAsignados param);
	
	@WebMethod
	public ResultCodiguera listaProductoresAsignadosVigentes(ParamListaProductoresAsignadosVigentes param);
	
	@WebMethod
	public ResultTipoUsuario tipoUsuario(ParamTipoUsuario param);
	
		@WebMethod
	public ResultValidarCotizacion validarCotizacion(ParamValidarCotizacion param);
	
	@WebMethod
	public ResultEmision emitirPoliza (ParamEmision param);

}
