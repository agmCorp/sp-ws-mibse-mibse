package uy.com.bse.cotizadores.rapidos;

import javax.jws.WebMethod;
import javax.jws.WebService;

import uy.com.bse.cotizadores.pymes.ParamCalcularPymes;
import uy.com.bse.cotizadores.pymes.ParamNuevaCotizacionPymes;
import uy.com.bse.cotizadores.pymes.ResultCalcularPymes;
import uy.com.bse.cotizadores.pymes.ResultNuevaCotizacionPymes;

@WebService
public interface IWsCotizadorPymes {
	
	@WebMethod
	ResultNuevaCotizacionPymes nuevaCotizacionPymes(ParamNuevaCotizacionPymes param);
	
	@WebMethod
	ResultCalcularPymes calcularPymes(ParamCalcularPymes param);
	
	

}