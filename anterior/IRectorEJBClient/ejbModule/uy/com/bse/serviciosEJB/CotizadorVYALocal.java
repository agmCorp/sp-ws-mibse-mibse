package uy.com.bse.serviciosEJB;

import uy.com.bse.cotizadorvya.operaciones.ParamCalcularPlanes;
import uy.com.bse.cotizadorvya.operaciones.ParamMontosIniciales;
import uy.com.bse.cotizadorvya.operaciones.ParamObtenerCoberturas;
import uy.com.bse.cotizadorvya.operaciones.ParamPorcentajeAnticipo;
import uy.com.bse.cotizadorvya.operaciones.ParamRentaMaxima;
import uy.com.bse.cotizadorvya.operaciones.ResultCalcularPlanes;
import uy.com.bse.cotizadorvya.operaciones.ResultMontosIniciales;
import uy.com.bse.cotizadorvya.operaciones.ResultObtenerCoberturas;
import uy.com.bse.cotizadorvya.operaciones.ResultPorcentajeAnticipo;
import uy.com.bse.cotizadorvya.operaciones.ResultRentaMaxima;

public interface CotizadorVYALocal {
	public ResultCalcularPlanes calcularPlanes(ParamCalcularPlanes param);
	public ResultObtenerCoberturas obtenerCoberturas(ParamObtenerCoberturas param);
	public ResultMontosIniciales montosIniciales(ParamMontosIniciales param);
	public ResultRentaMaxima obtenerRentaMaxima(ParamRentaMaxima param);
	public ResultPorcentajeAnticipo obtenerPorcentajeAnticipo(ParamPorcentajeAnticipo param);
}
