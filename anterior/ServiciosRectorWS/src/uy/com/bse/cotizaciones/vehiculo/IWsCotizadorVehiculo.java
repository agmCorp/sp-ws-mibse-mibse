package uy.com.bse.cotizaciones.vehiculo;

import javax.jws.WebMethod;
import javax.jws.WebService;

import uy.com.bse.cotizaciones.lovs.ParamListaValoresDatoVehiculo;
import uy.com.bse.cotizaciones.lovs.ResultListaValoresDato;
import uy.com.bse.cotizaciones.operaciones.ParamActualizarCertificadoCeroVehiculo;
import uy.com.bse.cotizaciones.operaciones.ParamActualizarPromocionVehiculo;
import uy.com.bse.cotizaciones.operaciones.ParamCalcularCuotasVehiculo;
import uy.com.bse.cotizaciones.operaciones.ParamNuevaCotizacionVehiculo;
import uy.com.bse.cotizaciones.operaciones.ParamValidarDatosVehiculo;
import uy.com.bse.cotizaciones.operaciones.ResultActualizarPromocionVehiculo;
import uy.com.bse.cotizaciones.operaciones.ResultCalcularCuotasVehiculo;
import uy.com.bse.cotizaciones.operaciones.ResultNuevaCotizacionVehiculo;
import uy.com.bse.cotizaciones.operaciones.ResultValidarDatosVehiculo;
import uy.com.bse.utilitario.dato.ResultGenerico;

@WebService
public interface IWsCotizadorVehiculo {
	
	@WebMethod
	public ResultValidarDatosVehiculo validarDatosVehiculo(ParamValidarDatosVehiculo param);
	
	@WebMethod
	public ResultCalcularCuotasVehiculo calcularCuotasVehiculo(ParamCalcularCuotasVehiculo param);
	
	@WebMethod
	public ResultNuevaCotizacionVehiculo nuevaCotizacionVehiculo(ParamNuevaCotizacionVehiculo param);
	
	@WebMethod
	public ResultActualizarPromocionVehiculo actualizarPromocionVehiculo(ParamActualizarPromocionVehiculo param); 

	@WebMethod
	public ResultListaValoresDato listaValoresDatoParametricoVehiculo(ParamListaValoresDatoVehiculo param);
	
	@WebMethod
	public ResultGenerico actualizarCertificadoCeroVehiculo(ParamActualizarCertificadoCeroVehiculo param);

}