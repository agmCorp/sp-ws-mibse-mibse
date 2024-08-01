package uy.com.bse.serviciosEJB;


import uy.com.bse.maestro.personas.interfaces.ParamRadio;
import uy.com.bse.maestro.personas.interfaces.ResultRadio;
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

public interface ServiciosRectorGenericosLocal {

	/**
	 * Pasarela para consultar los tipos de documentos
	 * 
	 * @return Retorna una lista con los documentos disponibles
	 */
	public ResultListaTipoDoc listaTipoDoc(ParamListaTipoDoc param);

	/**
	 * Pasarela para consultar los organismos
	 * 
	 * @return Retorna una lista con los organismos disponibles
	 */
	public ResultListaOrganismos listaOrganismos(ParamListaOrganismos param);

	/**
	 * Pasarela para consultar las actividades
	 * 
	 * @return Retorna una lista con las actividades disponibles
	 */
	public ResultListaActividades listaActividades(ParamListaActividades param);

	/**
	 * Pasarela para consultar los paises
	 * 
	 * @return Retorna una lista con los paises disponibles
	 */
	public ResultListaPaises listaPaises(ParamListaPaises param);

	/**
	 * Pasarela para consultar los departamentos
	 * 
	 * @return Retorna una lista con los departamentos disponibles
	 */
	public ResultListaDepartamentos listaDepartamentos(ParamListaDepartamentos param);

	/**
	 * Pasarela para consultar las localidades
	 * 
	 * @param param
	 * @return Una lista de localidades
	 */
	public ResultListaLocalidades listaLocalidades(ParamLocalidades param);

	/**
	 * Pasarela para consultar los codigos postales
	 * 
	 * @param param
	 * @return Una lista de codigos postales
	 */
	public ResultListaCodPostal listaCodPostal(ParamCodigoPostal param);

	/**
	 * Pasarela para consultar las sucursales
	 * 
	 * @return Retorna una lista con las sucursales disponibles
	 */
	public ResultListaSucursales listaSucursales(ParamListaSucursales param);

	/**
	 * Pasarela para insertar los datos de contacto
	 * 
	 * @param param
	 * @return El resultado de inserta los datos de contacto
	 */
	public ResultContactoWeb altaContactoWeb(ParamContactoWeb param);

	
	public ResultRadio listaRadio(ParamRadio param);

	
	ResultCodiguera listaProductoresAsignados(ParamListaProductoresAsignados param);

	ResultCodiguera listaProductoresAsignadosVigentes(ParamListaProductoresAsignadosVigentes param);

	ResultTipoUsuario tipoUsuario(ParamTipoUsuario param);
	
	


}
