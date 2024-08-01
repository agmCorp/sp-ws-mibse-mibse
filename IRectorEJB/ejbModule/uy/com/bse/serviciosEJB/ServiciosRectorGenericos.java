package uy.com.bse.serviciosEJB;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import uy.com.bse.LogicaRector;
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
import uy.com.bse.utilitario.dato.ResultGenerico;

@Stateless
public class ServiciosRectorGenericos implements ServiciosRectorGenericosLocal {

	private static Logger log = LogManager.getLogger(ServiciosRectorGenericos.class);

	public ServiciosRectorGenericos() {
		super();
	}


	@Override
	@Interceptors(UserInterceptor.class)
	public ResultListaTipoDoc listaTipoDoc(ParamListaTipoDoc param) {
		log.debug("listaTipoDoc start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("listaTipoDoc end: " + param);
		return (ResultListaTipoDoc) result;
	}
	

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultListaOrganismos listaOrganismos(ParamListaOrganismos param) {
		log.debug("listaOrganismos start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("listaOrganismos end: " + param);
		return (ResultListaOrganismos) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultListaActividades listaActividades(ParamListaActividades param) {
		log.debug("listaActividades start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("listaActividades end: " + param);
		return (ResultListaActividades) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultListaPaises listaPaises(ParamListaPaises param) {
		log.debug("listaPaises start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("listaPaises end: " + param);
		return (ResultListaPaises) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultListaDepartamentos listaDepartamentos(ParamListaDepartamentos param) {
		log.debug("listaDepartamentos start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("listaDepartamentos end: " + param);
		return (ResultListaDepartamentos) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultListaLocalidades listaLocalidades(ParamLocalidades param) {
		log.debug("listaLocalidades start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("listaLocalidades end: " + param);
		return (ResultListaLocalidades) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultListaSucursales listaSucursales(ParamListaSucursales param) {
		log.debug("listaSucursales start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("listaSucursales end: " + param);
		return (ResultListaSucursales) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultListaCodPostal listaCodPostal(ParamCodigoPostal param) {
		log.debug("listaCodPostal start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("listaCodPostal end: " + param);
		return (ResultListaCodPostal) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultContactoWeb altaContactoWeb(ParamContactoWeb param) {
		log.debug("altaContactoWeb start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("altaContactoWeb end: " + param);
		return (ResultContactoWeb) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultRadio listaRadio(ParamRadio param) {
		log.debug("ParamRadio start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("ParamRadio end");
		return (ResultRadio) result;
	}
	
	@Override
	@Interceptors(UserInterceptor.class)
	public ResultCodiguera listaProductoresAsignados(ParamListaProductoresAsignados param) {
		log.debug("listaProductoresAsignados start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("listaProductoresAsignados end: " + param);
		return (ResultCodiguera) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultCodiguera listaProductoresAsignadosVigentes(ParamListaProductoresAsignadosVigentes param) {
		log.debug("listaProductoresAsignadosVigentes start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("listaProductoresAsignadosVigentes end: " + param);
		return (ResultCodiguera) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultTipoUsuario tipoUsuario(ParamTipoUsuario param) {
		log.debug("tipoUsuario start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("tipoUsuario end: " + param);
		return (ResultTipoUsuario) result;
	}
	
}
