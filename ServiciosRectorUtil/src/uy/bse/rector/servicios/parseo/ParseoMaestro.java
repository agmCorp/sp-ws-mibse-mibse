package uy.bse.rector.servicios.parseo;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import uy.com.bse.clientes.consultas.ResultExportarClientes;
import uy.com.bse.clientes.consultas.ResultObtenerClientes;
import uy.com.bse.clientes.consultas.ResultObtenerDetalleConsultaClientes;
import uy.com.bse.clientes.consultas.ResultPolizasAsociadasClientes;
import uy.com.bse.cotizaciones.consultas.Cliente;
import uy.com.bse.maestro.personas.comunicaciones.ComunicacionEC;
import uy.com.bse.maestro.personas.consultas.ResultObtenerPersonasClientes;
import uy.com.bse.maestro.personas.consultas.ResultPolizasAsociadas;
import uy.com.bse.maestro.personas.domicilio.DireccionEC;
import uy.com.bse.maestro.personas.domicilio.InfoPoliza;
import uy.com.bse.maestro.personas.interfaces.ResultRadio;
import uy.com.bse.servicios.rector.interfaces.Postal;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.log.Logueo;
import uy.com.bse.utilitario.parseo.ParseoXmlGenerico;

public class ParseoMaestro extends ParseoXmlGenerico {
	

	public ParseoMaestro(String textoParsear) {
		super(textoParsear);
	}
	
	public ResultObtenerPersonasClientes parsearObtenerPersonasClientes (){
		final Logueo logueo = new Logueo();
		logueo.setEncabezado(ParseoMaestro.ENCABEZADO);
		logueo.setClase(ParseoCotizaciones.class);
		logueo.setMetodo("parsearObtenerPersonasClientes");		
		
		ResultObtenerPersonasClientes retorno = new ResultObtenerPersonasClientes();
		
		try{
			
			 NodeList elementos = this.doc.getElementsByTagName("cliente");
			 for(int i=0;i<elementos.getLength();i++){
				 Element item = (Element)elementos.item(i);	
				 Cliente cli = new Cliente();
				 
				 if(!"-1".equals(getParsedValueFromElement(item,"nu-persona"))){
					 cli.setNumPersona(getParsedValueFromElement(item,"nu-persona"));
				 }
				 cli.setApellidoRazon(getParsedValueFromElement(item,"apellido"));
				 
				 if(!" ".equals(getParsedValueFromElement(item,"nombre"))){
					 cli.setNombre(getParsedValueFromElement(item,"nombre"));
				 }
				 if(!" ".equals(getParsedValueFromElement(item,"rut"))){
					 cli.setRut(getParsedValueFromElement(item,"rut"));
				 }
				 
				 if(!"-1".equals(getParsedValueFromElement(item,"cod-tp_documento"))){
					 cli.setTipoDoc(getParsedValueFromElement(item,"cod-tp_documento"));
				 }
				 
				 if(!" ".equals(getParsedValueFromElement(item,"desc-tp-documento"))){
					 cli.setDescripTipoDoc(getParsedValueFromElement(item,"desc-tp-documento"));
				 }
				 cli.setNumDoc(getParsedValueFromElement(item,"nu-documento"));
				 
				 if(!"-1".equals(getParsedValueFromElement(item,"nu-cliente"))){
					 cli.setCodCliente(getParsedValueFromElement(item,"nu-cliente"));
				 }
				 
				 if(!"-1".equals(getParsedValueFromElement(item,"cod-tp-persona"))){
					 cli.setTipoPersona(getParsedValueFromElement(item,"cod-tp-persona"));
				 }
				 
				 cli.setDescripPersona(getParsedValueFromElement(item,"desc-tp-persona"));
				 				 
				 DireccionEC dir = new DireccionEC();
				 
				 if(getParsedValueFromElement(item,"cons-direccion")!=null && !getParsedValueFromElement(item,"cons-direccion").equals("-1") ){
					 dir.setCodDireccion(Integer.valueOf(getParsedValueFromElement(item,"cons-direccion")));
				 }				    
				 if(getParsedValueFromElement(item,"cod-postal")!= null && !getParsedValueFromElement(item,"cod-postal").equals("-1")){
					 dir.setNumRadio(Integer.valueOf(getParsedValueFromElement(item,"cod-postal")));
				 }
				 dir.setDescripRadio(getParsedValueFromElement(item,"localidad"));
				 dir.setDireccion(getParsedValueFromElement(item,"calle"));
				 
				 if(!"-1".equals(getParsedValueFromElement(item,"nu-direccion"))){
					 dir.setNumeroPuerta(getParsedValueFromElement(item,"nu-direccion"));
				 }
				 
				 if(!" ".equals(getParsedValueFromElement(item,"nu-departamento"))){
					 dir.setApto(getParsedValueFromElement(item,"nu-departamento"));
				 }
				 
				 if(!"-1".equals(getParsedValueFromElement(item,"nu-piso"))){
					 dir.setPiso(getParsedValueFromElement(item,"nu-piso"));
				 }
				 
				 if(!" ".equals(getParsedValueFromElement(item,"nu-padron-catastral"))){
					 dir.setPadron(getParsedValueFromElement(item,"nu-padron-catastral"));
				 }
				 
				 if(!" ".equals(getParsedValueFromElement(item,"aclaraciones"))){
					 dir.setAclaraciones(getParsedValueFromElement(item,"aclaraciones"));
				 }
				 dir.setDescripDepto(getParsedValueFromElement(item,"desc-departamento"));
				 if(getParsedValueFromElement(item,"cod-departamento")!= null && !getParsedValueFromElement(item,"cod-departamento").equals("-1")){
					 dir.setCodDepto(Integer.valueOf(getParsedValueFromElement(item,"cod-departamento")));
				 }
				 if(getParsedValueFromElement(item,"cod-municipio")!= null && !getParsedValueFromElement(item,"cod-municipio").equals("-1")){
					 dir.setCodLocalidad(Integer.valueOf(getParsedValueFromElement(item,"cod-municipio")));
				 }
				 dir.setDescripLocalidad(getParsedValueFromElement(item,"desc-municipio"));
				 dir.setDescripPais(getParsedValueFromElement(item,"desc-pais"));
				 
				 cli.setDireccionCompleta(dir);

				 ComunicacionEC comTel = new ComunicacionEC();
				 
				 if(getParsedValueFromElement(item,"cons-telefono")!=null && !getParsedValueFromElement(item,"cons-telefono").equals("-1") ){
					 comTel.setCodComunicacion(Integer.valueOf(getParsedValueFromElement(item,"cons-telefono")));
					 if(!" ".equals(getParsedValueFromElement(item,"telefono"))){
						 comTel.setValorComunicacion(getParsedValueFromElement(item,"telefono"));
					 }
					 comTel.setNota(getParsedValueFromElement(item, "nota-telefono"));
					 cli.setTel(comTel);
				 }
				 
				 ComunicacionEC comCel = new ComunicacionEC();
				 
				 if(getParsedValueFromElement(item,"cons-celular")!=null && !getParsedValueFromElement(item,"cons-celular").equals("-1")){
					 comCel.setCodComunicacion(Integer.valueOf(getParsedValueFromElement(item,"cons-celular")));
					 if(!" ".equals(getParsedValueFromElement(item,"celular"))){
						 comCel.setValorComunicacion(getParsedValueFromElement(item,"celular"));
					 }
					 comCel.setNota(getParsedValueFromElement(item, "nota-celular"));
					 cli.setCel(comCel);
				 }
				 
				 ComunicacionEC comMail = new ComunicacionEC();
				 
				 if(getParsedValueFromElement(item,"cons-mail")!=null && !getParsedValueFromElement(item,"cons-mail").equals("-1")){
					 comMail.setCodComunicacion(Integer.valueOf(getParsedValueFromElement(item,"cons-mail")));
					 if(!" ".equals(getParsedValueFromElement(item,"mail"))){
						 comMail.setValorComunicacion(getParsedValueFromElement(item,"mail"));
					 }
					 comMail.setNota(getParsedValueFromElement(item,"nota-mail"));
					 cli.setMail(comMail);
				 }
				 
				 cli.setEsCliente(getParsedValueFromElement(item,"es-cliente"));
				 cli.setEsPep(getParsedValueFromElement(item,"es-pep"));
				 cli.setFechaPep(getParsedValueFromElement(item,"fe-pep"));
				 retorno.setUnCliente(cli);
			 }
			 
		} catch (NumberFormatException ex) {
			claveError = catchNumberFormatException(logueo, ex);
		} catch (Exception ex) {
			claveError = catchException(logueo, ex);
		} finally {
			finallyBlock(claveError, retorno);
		}		
				
		
		return retorno;
	}
	public ResultObtenerClientes parsearObtenerClientes (){
		final Logueo logueo = new Logueo();
		logueo.setEncabezado(ParseoMaestro.ENCABEZADO);
		logueo.setClase(ParseoCotizaciones.class);
		logueo.setMetodo("parsearObtenerClientes");		
		
		ResultObtenerClientes retorno = new ResultObtenerClientes();
		
		try{
			
			 NodeList elementos = this.doc.getElementsByTagName("cliente");
			 for(int i=0;i<elementos.getLength();i++){
				 Element item = (Element)elementos.item(i);	
				 Cliente cli = new Cliente();
				 
				 if(!"-1".equals(getParsedValueFromElement(item,"nu-persona"))){
					 cli.setNumPersona(getParsedValueFromElement(item,"nu-persona"));
				 }
				 cli.setApellidoRazon(getParsedValueFromElement(item,"apellido"));
				 
				 if(!" ".equals(getParsedValueFromElement(item,"nombre"))){
					 cli.setNombre(getParsedValueFromElement(item,"nombre"));
				 }
				 if(!" ".equals(getParsedValueFromElement(item,"rut"))){
					 cli.setRut(getParsedValueFromElement(item,"rut"));
				 }
				 
				 if(!"-1".equals(getParsedValueFromElement(item,"cod-tp_documento"))){
					 cli.setTipoDoc(getParsedValueFromElement(item,"cod-tp_documento"));
				 }
				 
				 if(!" ".equals(getParsedValueFromElement(item,"desc-tp-documento"))){
					 cli.setDescripTipoDoc(getParsedValueFromElement(item,"desc-tp-documento"));
				 }
				 cli.setNumDoc(getParsedValueFromElement(item,"nu-documento"));
				 
				 if(!"-1".equals(getParsedValueFromElement(item,"nu-cliente"))){
					 cli.setCodCliente(getParsedValueFromElement(item,"nu-cliente"));
				 }
				 
				 if(!"-1".equals(getParsedValueFromElement(item,"cod-tp-persona"))){
					 cli.setTipoPersona(getParsedValueFromElement(item,"cod-tp-persona"));
				 }
				 
				 cli.setDescripPersona(getParsedValueFromElement(item,"desc-tp-persona"));
				 
				 		
				 ComunicacionEC comCel = new ComunicacionEC();
				 
				 if(getParsedValueFromElement(item,"celular")!=null && !getParsedValueFromElement(item,"celular").equals("-1")){
					 if(!" ".equals(getParsedValueFromElement(item,"celular"))){
						 comCel.setValorComunicacion(getParsedValueFromElement(item,"celular"));
					 }
					 
					 cli.setCel(comCel);
				 }
				 cli.setEsCliente(getParsedValueFromElement(item,"es-cliente"));
				 retorno.setUnCliente(cli);
			 }
			 
		} catch (NumberFormatException ex) {
			claveError = catchNumberFormatException(logueo, ex);
		} catch (Exception ex) {
			claveError = catchException(logueo, ex);
		} finally {
			finallyBlock(claveError, retorno);
		}		
				
		
		return retorno;
	}
	
	
	public ResultObtenerDetalleConsultaClientes parsearObtenerDetalleConsultaClientes (){
		final Logueo logueo = new Logueo();
		logueo.setEncabezado(ParseoMaestro.ENCABEZADO);
		logueo.setClase(ParseoCotizaciones.class);
		logueo.setMetodo("parsearObtenerDetalleConsultaClientes");		
		
		ResultObtenerDetalleConsultaClientes retorno = new ResultObtenerDetalleConsultaClientes();
		
		try{
			
			 NodeList elementos = this.doc.getElementsByTagName("cliente");
			 for(int i=0;i<elementos.getLength();i++){
				 Element item = (Element)elementos.item(i);	
				 Cliente cli = new Cliente();
				 
				 if(!"-1".equals(getParsedValueFromElement(item,"nu-persona"))){
					 cli.setNumPersona(getParsedValueFromElement(item,"nu-persona"));
				 }
				 cli.setApellidoRazon(getParsedValueFromElement(item,"apellido"));
				 
				 if(!" ".equals(getParsedValueFromElement(item,"nombre"))){
					 cli.setNombre(getParsedValueFromElement(item,"nombre"));
				 }
				 if(!" ".equals(getParsedValueFromElement(item,"rut"))){
					 cli.setRut(getParsedValueFromElement(item,"rut"));
				 }
				 
				 if(!"-1".equals(getParsedValueFromElement(item,"cod-tp_documento"))){
					 cli.setTipoDoc(getParsedValueFromElement(item,"cod-tp_documento"));
				 }
				 
				 if(!" ".equals(getParsedValueFromElement(item,"desc-tp-documento"))){
					 cli.setDescripTipoDoc(getParsedValueFromElement(item,"desc-tp-documento"));
				 }
				 cli.setNumDoc(getParsedValueFromElement(item,"nu-documento"));
				 
				 if(!"-1".equals(getParsedValueFromElement(item,"nu-cliente"))){
					 cli.setCodCliente(getParsedValueFromElement(item,"nu-cliente"));
				 }
				 
				 if(!"-1".equals(getParsedValueFromElement(item,"cod-tp-persona"))){
					 cli.setTipoPersona(getParsedValueFromElement(item,"cod-tp-persona"));
				 }
				 
				 cli.setDescripPersona(getParsedValueFromElement(item,"desc-tp-persona"));
				 				 
				 DireccionEC dir = new DireccionEC();
				 
				 //cd-tipo-dir
				 
				 if(getParsedValueFromElement(item,"cd-tipo-dir")!=null && !getParsedValueFromElement(item,"cd-tipo-dir").equals("-1") ){
					 dir.setTipoDireccion(Integer.valueOf(getParsedValueFromElement(item,"cd-tipo-dir")));
				 }
				 
				 if(getParsedValueFromElement(item,"cons-direccion")!=null && !getParsedValueFromElement(item,"cons-direccion").equals("-1") ){
					 dir.setCodDireccion(Integer.valueOf(getParsedValueFromElement(item,"cons-direccion")));
				 }				    
				 if(getParsedValueFromElement(item,"cod-postal")!= null && !getParsedValueFromElement(item,"cod-postal").equals("-1")){
					 dir.setNumRadio(Integer.valueOf(getParsedValueFromElement(item,"cod-postal")));
				 }
				 dir.setDescripRadio(getParsedValueFromElement(item,"localidad"));
				 dir.setDireccion(getParsedValueFromElement(item,"calle"));
				 
				 if(!"-1".equals(getParsedValueFromElement(item,"nu-direccion"))){
					 dir.setNumeroPuerta(getParsedValueFromElement(item,"nu-direccion"));
				 }
				 
				 if(!" ".equals(getParsedValueFromElement(item,"nu-departamento"))){
					 dir.setApto(getParsedValueFromElement(item,"nu-departamento"));
				 }
				 
				 if(!"-1".equals(getParsedValueFromElement(item,"nu-piso"))){
					 dir.setPiso(getParsedValueFromElement(item,"nu-piso"));
				 }
				 
				 if(!" ".equals(getParsedValueFromElement(item,"nu-padron-catastral"))){
					 dir.setPadron(getParsedValueFromElement(item,"nu-padron-catastral"));
				 }
				 
				 if(!" ".equals(getParsedValueFromElement(item,"aclaraciones"))){
					 dir.setAclaraciones(getParsedValueFromElement(item,"aclaraciones"));
				 }
				 dir.setDescripDepto(getParsedValueFromElement(item,"desc-departamento"));
				 if(getParsedValueFromElement(item,"cod-departamento")!= null && !getParsedValueFromElement(item,"cod-departamento").equals("-1")){
					 dir.setCodDepto(Integer.valueOf(getParsedValueFromElement(item,"cod-departamento")));
				 }
				 if(getParsedValueFromElement(item,"cod-municipio")!= null && !getParsedValueFromElement(item,"cod-municipio").equals("-1")){
					 dir.setCodLocalidad(Integer.valueOf(getParsedValueFromElement(item,"cod-municipio")));
				 }
				 dir.setDescripLocalidad(getParsedValueFromElement(item,"desc-municipio"));
				 dir.setDescripPais(getParsedValueFromElement(item,"desc-pais"));
				 
				 cli.setDireccionCompleta(dir);

				 ComunicacionEC comTel = new ComunicacionEC();
				 
				 if(getParsedValueFromElement(item,"cons-telefono")!=null && !getParsedValueFromElement(item,"cons-telefono").equals("-1") ){
					 comTel.setCodComunicacion(Integer.valueOf(getParsedValueFromElement(item,"cons-telefono")));
					 if(!" ".equals(getParsedValueFromElement(item,"telefono"))){
						 comTel.setValorComunicacion(getParsedValueFromElement(item,"telefono"));
					 }
					 comTel.setNota(getParsedValueFromElement(item, "nota-telefono"));
					 comTel.setValidado(getParsedValueFromElement(item,"telefono-valido"));
					 cli.setTel(comTel);
				 }
				 
				 ComunicacionEC comCel = new ComunicacionEC();
				 
				 if(getParsedValueFromElement(item,"cons-celular")!=null && !getParsedValueFromElement(item,"cons-celular").equals("-1")){
					 comCel.setCodComunicacion(Integer.valueOf(getParsedValueFromElement(item,"cons-celular")));
					 if(!" ".equals(getParsedValueFromElement(item,"celular"))){
						 comCel.setValorComunicacion(getParsedValueFromElement(item,"celular"));
					 }
					 comCel.setNota(getParsedValueFromElement(item, "nota-celular"));
					 comCel.setValidado(getParsedValueFromElement(item,"celular-valido"));
					 cli.setCel(comCel);
				 }
				 
				 ComunicacionEC comMail = new ComunicacionEC();
				 
				 if(getParsedValueFromElement(item,"cons-mail")!=null && !getParsedValueFromElement(item,"cons-mail").equals("-1")){
					 comMail.setCodComunicacion(Integer.valueOf(getParsedValueFromElement(item,"cons-mail")));
					 if(!" ".equals(getParsedValueFromElement(item,"mail"))){
						 comMail.setValorComunicacion(getParsedValueFromElement(item,"mail"));
					 }
					 comMail.setNota(getParsedValueFromElement(item,"nota-mail"));
					 comMail.setValidado(getParsedValueFromElement(item,"mail-valido"));
					 cli.setMail(comMail);
				 }
				 
				 //se agrega mail de factura en el detalle 
				 ComunicacionEC comMailFactura = new ComunicacionEC();
				 
				 if(getParsedValueFromElement(item,"cons-fact-mail")!=null && !getParsedValueFromElement(item,"cons-fact-mail").equals("-1")){
					 comMailFactura.setCodComunicacion(Integer.valueOf(getParsedValueFromElement(item,"cons-fact-mail")));
					 if(!" ".equals(getParsedValueFromElement(item,"fact-mail"))){
						 comMailFactura.setValorComunicacion(getParsedValueFromElement(item,"fact-mail"));
					 }
					 comMailFactura.setNota(getParsedValueFromElement(item,"nota-fact-mail"));
					 comMailFactura.setValidado(getParsedValueFromElement(item,"fact-mail-valido"));
					 cli.setMailFacturas(comMailFactura);
				 }
				 
				 cli.setEsCliente(getParsedValueFromElement(item,"es-cliente"));
				 cli.setEsPep(getParsedValueFromElement(item,"es-pep"));
				 cli.setFechaPep(getParsedValueFromElement(item,"fe-pep"));
				 retorno.setDetallelClientes(cli);
			 }
			 
		} catch (NumberFormatException ex) {
			claveError = catchNumberFormatException(logueo, ex);
		} catch (Exception ex) {
			claveError = catchException(logueo, ex);
		} finally {
			finallyBlock(claveError, retorno);
		}		
				
		
		return retorno;
	}
	public ResultRadio parsearListaRadio (){
		Logueo logueo = new Logueo();
		logueo.setEncabezado(ParseoMaestro.ENCABEZADO);
		logueo.setClase(ParseoCotizaciones.class);
		logueo.setMetodo("parsearListaRadio");		
		
		ResultRadio retorno = new ResultRadio();
		
		try{
			
			 NodeList elementos = this.doc.getElementsByTagName("codigo-postal");
			 for(int i=0;i<elementos.getLength();i++){
				 Element item = (Element)elementos.item(i);	
				 Postal pos = new Postal();
				 
				 pos.setNumRadio(getParsedValueFromElement(item,"cod-postal"));
				 pos.setCodCalle(getParsedValueFromElement(item,"cod-calle"));
				 pos.setDescCalle(getParsedValueFromElement(item,"desc-calle"));
				 pos.setNumInicio(getParsedValueFromElement(item,"nu-inicio"));
				 pos.setNumFinal(getParsedValueFromElement(item,"nu-final"));
				 pos.setCodDepartamento(getParsedValueFromElement(item,"cod-departamento"));
				 pos.setDescDepartamento(getParsedValueFromElement(item,"desc-departamento"));
				 pos.setCodLocalidad(getParsedValueFromElement(item,"cod-localidad"));
				 pos.setDescLocalidad(getParsedValueFromElement(item,"desc-localidad"));
				 pos.setNumPostal(getParsedValueFromElement(item,"nu-postal"));
				 pos.setLocalidad(getParsedValueFromElement(item,"localidad"));
				 pos.setParidad(getParsedValueFromElement(item,"paridad"));
				
				 retorno.setUnaPostal(pos);
			 }
		} catch (NumberFormatException ex) {
			claveError = catchNumberFormatException(logueo, ex);
		} catch (Exception ex) {
			claveError = catchException(logueo, ex);
		} finally {
			finallyBlock(claveError, retorno);
		}		
			
		
		return retorno;
	}

	public ResultPolizasAsociadas parsearObtenerPolizasAsociadas (){
		Logueo logueo = new Logueo();
		logueo.setEncabezado(ParseoMaestro.ENCABEZADO);
		logueo.setClase(ParseoCotizaciones.class);
		logueo.setMetodo("parsearObtenerPolizasAsociadas");		
		
		ResultPolizasAsociadas retorno = new ResultPolizasAsociadas();
		
		try{
			
			 NodeList elementos = this.doc.getElementsByTagName("poliza");
			 for(int i=0;i<elementos.getLength();i++){
				 Element item = (Element)elementos.item(i);	
				 InfoPoliza pol = new InfoPoliza();
				 
				 if(getParsedValueFromElement(item,"cod-ramo")!=null){
					 pol.setCodRamo(Integer.valueOf(getParsedValueFromElement(item,"cod-ramo")));
				 }
				 pol.setDescripRamo(getParsedValueFromElement(item,"desc-ramo"));
				 
				 if(getParsedValueFromElement(item,"numero")!=null){
					 pol.setNumPoliza(Integer.valueOf(getParsedValueFromElement(item,"numero")));
				 }
				 				 
				 pol.setCodProducto(getParsedValueFromElement(item,"cod-producto"));				 
				 pol.setDescripProducto(getParsedValueFromElement(item,"desc-producto"));
				
				
				 retorno.setUnaPolizaAsociada(pol);
			 }
			 
		} catch (NumberFormatException ex) {
			claveError = catchNumberFormatException(logueo, ex);
		} catch (Exception ex) {
			claveError = catchException(logueo, ex);
		} finally {
			finallyBlock(claveError, retorno);
		}		
				
		return retorno;
	}
	
	
	public ResultPolizasAsociadasClientes parsearObtenerPolizasAsociadasClientes (){
		Logueo logueo = new Logueo();
		logueo.setEncabezado(ParseoMaestro.ENCABEZADO);
		logueo.setClase(ParseoCotizaciones.class);
		logueo.setMetodo("parsearObtenerPolizasAsociadas");		
		
		ResultPolizasAsociadasClientes retorno = new ResultPolizasAsociadasClientes();
		
		try{
			
			 NodeList elementos = this.doc.getElementsByTagName("poliza");
			 for(int i=0;i<elementos.getLength();i++){
				 Element item = (Element)elementos.item(i);	
				 InfoPoliza pol = new InfoPoliza();
				 
				 if(getParsedValueFromElement(item,"cod-ramo")!=null){
					 pol.setCodRamo(Integer.valueOf(getParsedValueFromElement(item,"cod-ramo")));
				 }
				 pol.setDescripRamo(getParsedValueFromElement(item,"desc-ramo"));
				 
				 if(getParsedValueFromElement(item,"numero")!=null){
					 pol.setNumPoliza(Integer.valueOf(getParsedValueFromElement(item,"numero")));
				 }
				 				 
				 pol.setCodProducto(getParsedValueFromElement(item,"cod-producto"));				 
				 pol.setDescripProducto(getParsedValueFromElement(item,"desc-producto"));
				 
				 pol.setDescEstado(getParsedValueFromElement(item,"desc-estado"));
				//desc-estado
				
				 retorno.setUnaPolizaAsociada(pol);
			 }
			 
		} catch (NumberFormatException ex) {
			claveError = catchNumberFormatException(logueo, ex);
		} catch (Exception ex) {
			claveError = catchException(logueo, ex);
		} finally {
			finallyBlock(claveError, retorno);
		}		
				
		return retorno;
	}

	public ResultExportarClientes parsearExportarClientes() {
		final Logueo logueo = new Logueo();
		logueo.setEncabezado(ParseoMaestro.ENCABEZADO);
		logueo.setClase(ParseoCotizaciones.class);
		logueo.setMetodo("parsearExportarClientes");		
		
		ResultExportarClientes retorno = new ResultExportarClientes();
		
		try{
			
			 NodeList elementos = this.doc.getElementsByTagName("cliente");
			 for(int i=0;i<elementos.getLength();i++){
				 Element item = (Element)elementos.item(i);	
				 Cliente cli = new Cliente();
				 
				 if(!"-1".equals(getParsedValueFromElement(item,"nu-persona"))){
					 cli.setNumPersona(getParsedValueFromElement(item,"nu-persona"));
				 }
				 cli.setApellidoRazon(getParsedValueFromElement(item,"apellido"));
				 
				 if(!" ".equals(getParsedValueFromElement(item,"nombre"))){
					 cli.setNombre(getParsedValueFromElement(item,"nombre"));
				 }
				 if(!" ".equals(getParsedValueFromElement(item,"rut"))){
					 cli.setRut(getParsedValueFromElement(item,"rut"));
				 }
				 
				 if(!"-1".equals(getParsedValueFromElement(item,"cod-tp_documento"))){
					 cli.setTipoDoc(getParsedValueFromElement(item,"cod-tp_documento"));
				 }
				 
				 if(!" ".equals(getParsedValueFromElement(item,"desc-tp-documento"))){
					 cli.setDescripTipoDoc(getParsedValueFromElement(item,"desc-tp-documento"));
				 }
				 cli.setNumDoc(getParsedValueFromElement(item,"nu-documento"));
				 
				 if(!"-1".equals(getParsedValueFromElement(item,"nu-cliente"))){
					 cli.setCodCliente(getParsedValueFromElement(item,"nu-cliente"));
				 }
				 
				 if(!"-1".equals(getParsedValueFromElement(item,"cod-tp-persona"))){
					 cli.setTipoPersona(getParsedValueFromElement(item,"cod-tp-persona"));
				 }
				 
				 cli.setDescripPersona(getParsedValueFromElement(item,"desc-tp-persona"));
				 
				 		
				 DireccionEC dir = new DireccionEC();
				 
				 //cd-tipo-dir
				 
				 
				 
				 if(getParsedValueFromElement(item,"cons-direccion")!=null && !getParsedValueFromElement(item,"cons-direccion").equals("-1") ){
					 dir.setCodDireccion(Integer.valueOf(getParsedValueFromElement(item,"cons-direccion")));
				 }				    
				 if(getParsedValueFromElement(item,"cod-postal")!= null && !getParsedValueFromElement(item,"cod-postal").equals("-1")){
					 dir.setNumRadio(Integer.valueOf(getParsedValueFromElement(item,"cod-postal")));
				 }
				 dir.setDescripRadio(getParsedValueFromElement(item,"localidad"));
				 dir.setDireccion(getParsedValueFromElement(item,"calle"));
				 
				 if(!"-1".equals(getParsedValueFromElement(item,"nu-direccion"))){
					 dir.setNumeroPuerta(getParsedValueFromElement(item,"nu-direccion"));
				 }
				 
				 if(!" ".equals(getParsedValueFromElement(item,"nu-departamento"))){
					 dir.setApto(getParsedValueFromElement(item,"nu-departamento"));
				 }
				 
				 if(!"-1".equals(getParsedValueFromElement(item,"nu-piso"))){
					 dir.setPiso(getParsedValueFromElement(item,"nu-piso"));
				 }
				 
				 if(!" ".equals(getParsedValueFromElement(item,"nu-padron-catastral"))){
					 dir.setPadron(getParsedValueFromElement(item,"nu-padron-catastral"));
				 }
				 
				 if(!" ".equals(getParsedValueFromElement(item,"aclaraciones"))){
					 dir.setAclaraciones(getParsedValueFromElement(item,"aclaraciones"));
				 }
				 dir.setDescripDepto(getParsedValueFromElement(item,"desc-departamento"));
				 if(getParsedValueFromElement(item,"cod-departamento")!= null && !getParsedValueFromElement(item,"cod-departamento").equals("-1")){
					 dir.setCodDepto(Integer.valueOf(getParsedValueFromElement(item,"cod-departamento")));
				 }
				 if(getParsedValueFromElement(item,"cod-municipio")!= null && !getParsedValueFromElement(item,"cod-municipio").equals("-1")){
					 dir.setCodLocalidad(Integer.valueOf(getParsedValueFromElement(item,"cod-municipio")));
				 }
				 dir.setDescripLocalidad(getParsedValueFromElement(item,"desc-municipio"));
				 dir.setDescripPais(getParsedValueFromElement(item,"desc-pais"));
				 
				 cli.setDireccionCompleta(dir);

				 ComunicacionEC comTel = new ComunicacionEC();
				 
				 if(getParsedValueFromElement(item,"cons-telefono")!=null && !getParsedValueFromElement(item,"cons-telefono").equals("-1") ){
					 comTel.setCodComunicacion(Integer.valueOf(getParsedValueFromElement(item,"cons-telefono")));
					 if(!" ".equals(getParsedValueFromElement(item,"telefono"))){
						 comTel.setValorComunicacion(getParsedValueFromElement(item,"telefono"));
					 }
					 comTel.setNota(getParsedValueFromElement(item, "nota-telefono"));
					 cli.setTel(comTel);
					 
				 }
				 
				 ComunicacionEC comCel = new ComunicacionEC();
				 
				 if(getParsedValueFromElement(item,"cons-celular")!=null && !getParsedValueFromElement(item,"cons-celular").equals("-1")){
					 comCel.setCodComunicacion(Integer.valueOf(getParsedValueFromElement(item,"cons-celular")));
					 if(!" ".equals(getParsedValueFromElement(item,"celular"))){
						 comCel.setValorComunicacion(getParsedValueFromElement(item,"celular"));
					 }
					 comCel.setNota(getParsedValueFromElement(item, "nota-celular"));
					
					 cli.setCel(comCel);
				 }
				 
				 ComunicacionEC comMail = new ComunicacionEC();
				 
				 if(getParsedValueFromElement(item,"cons-mail")!=null && !getParsedValueFromElement(item,"cons-mail").equals("-1")){
					 comMail.setCodComunicacion(Integer.valueOf(getParsedValueFromElement(item,"cons-mail")));
					 if(!" ".equals(getParsedValueFromElement(item,"mail"))){
						 comMail.setValorComunicacion(getParsedValueFromElement(item,"mail"));
					 }
					 comMail.setNota(getParsedValueFromElement(item,"nota-mail"));
					
					 cli.setMail(comMail);
				 }
				 
				 //se agrega mail de factura en el detalle 
				 ComunicacionEC comMailFactura = new ComunicacionEC();
				 
				 if(getParsedValueFromElement(item,"cons-fact-mail")!=null && !getParsedValueFromElement(item,"cons-fact-mail").equals("-1")){
					 comMailFactura.setCodComunicacion(Integer.valueOf(getParsedValueFromElement(item,"cons-fact-mail")));
					 if(!" ".equals(getParsedValueFromElement(item,"fact-mail"))){
						 comMailFactura.setValorComunicacion(getParsedValueFromElement(item,"fact-mail"));
					 }
					 comMailFactura.setNota(getParsedValueFromElement(item,"nota-fact-mail"));
					 
					 cli.setMailFacturas(comMailFactura);
				 }
				 
				 cli.setEsCliente(getParsedValueFromElement(item,"es-cliente"));
				 
				 retorno.setUnCliente(cli);
			 }
			 
		} catch (NumberFormatException ex) {
			claveError = catchNumberFormatException(logueo, ex);
		} catch (Exception ex) {
			claveError = catchException(logueo, ex);
		} finally {
			finallyBlock(claveError, retorno);
		}		
				
		
		return retorno;
	}
}
