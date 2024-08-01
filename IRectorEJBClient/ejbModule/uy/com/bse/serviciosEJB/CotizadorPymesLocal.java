package uy.com.bse.serviciosEJB;

import uy.com.bse.cotizadores.pymes.ParamCalcularPymes;
import uy.com.bse.cotizadores.pymes.ParamNuevaCotizacionPymes;
import uy.com.bse.cotizadores.pymes.ResultCalcularPymes;
import uy.com.bse.cotizadores.pymes.ResultNuevaCotizacionPymes;

public interface CotizadorPymesLocal {
	

	public ResultNuevaCotizacionPymes nuevaCotizacionPymes(ParamNuevaCotizacionPymes param);
	public ResultCalcularPymes calcularPymes(ParamCalcularPymes param);
}
