package uy.com.bse.cotizadorvya.ws;

import javax.jws.WebMethod;
import javax.jws.WebService;

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

@WebService
public interface IWsServiciosCotizadorVYA {
	@WebMethod
	public ResultCalcularPlanes calcularPlanes(ParamCalcularPlanes param);
	
	@WebMethod
	public ResultObtenerCoberturas obtenerCoberturas(ParamObtenerCoberturas param);
	
	@WebMethod
	public ResultMontosIniciales montosIniciales(ParamMontosIniciales param);
	
	@WebMethod
	public ResultRentaMaxima obtenerRentaMaxima(ParamRentaMaxima param);
	
	@WebMethod
	public ResultPorcentajeAnticipo obtenerPorcentajeAnticipo(ParamPorcentajeAnticipo param);
}
