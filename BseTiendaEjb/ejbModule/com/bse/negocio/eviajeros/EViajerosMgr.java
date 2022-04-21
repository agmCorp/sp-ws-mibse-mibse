package com.bse.negocio.eviajeros;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.bse.accesodatos.esoa.Moneda;
import com.bse.accesodatos.esoa.PlanCobertura;
import com.bse.accesodatos.esoa.PlanPago;
import com.bse.accesodatos.esoa.Producto;
import com.bse.accesodatos.esoa.Ramo;
import com.bse.accesodatos.eviajeros.CoberturaMuerte;
import com.bse.accesodatos.eviajeros.CoberturaPrexistentes;
import com.bse.accesodatos.eviajeros.CotizacionViajeros;
import com.bse.accesodatos.eviajeros.PolizaViajeros;
import com.bse.accesodatos.eviajeros.Viajero;
import com.bse.negocio.FabricaNegocio;
import com.bse.negocio.comun.BSEException;
import com.bse.negocio.comun.Codigos;
import com.bse.negocio.comun.CodigosError;
import com.bse.negocio.esoa.ESoaMgr;
import com.bse.negocio.esoa.IESoa;
import com.bse.accesodatos.esoa.CuotaPago;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class EViajerosMgr implements IEViajeros{


	private EViajerosMgr() {
    }
    public static IEViajeros getEViajerosMgr() {
        return new EViajerosMgr();
    }

	@Override
	public CotizacionViajeros cotizarViajerosAnonimo(EntityManager em, String planCobertura, int planPago, Date fechaDesde, Date fechaHasta,
			ArrayList<Viajero> listaPersonas, String extension, Date fechaSalidaPais) throws Exception, BSEException {

		// valido plan de pagos permitido
		String planesPago = Codigos.getCodigos().getPlanesPagoViajeros(em);
		String[] planesVec = planesPago.split(",");
		boolean planValido = false;
		for(int i = 0; i < planesVec.length; i++){
			if (planesVec[i].equals(String.valueOf(planPago))){
				planValido = true;
				i = planesVec.length;
			}		
		}
		
		if (!planValido)
			throw new BSEException(CodigosError.plan_pago_invalido);
		if (planCobertura == null || planCobertura.trim().equals(""))  
			throw new BSEException(CodigosError.plan_cobertura_invalido);
		if (fechaDesde == null || fechaHasta == null)
			throw new BSEException(CodigosError.fechas_invalidas);
		if (!fechaHasta.after(fechaDesde))
			throw new BSEException(CodigosError.fechas_invalidas);
		
		
		// valido fechas
		Date ayer = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(ayer);
		calendar.add(Calendar.DAY_OF_YEAR, -1);
		ayer = calendar.getTime();
		if (!fechaDesde.after(ayer))
			throw new BSEException(CodigosError.fechas_invalidas);

		Calendar cDesde = Calendar.getInstance();
		Calendar cHasta = Calendar.getInstance(); 
		cDesde.setTime(fechaDesde);
		cHasta.setTime(fechaHasta);
		int daysBetween = (int) ((fechaHasta.getTime() - fechaDesde.getTime()) / (1000 * 60 * 60 * 24)); 
		
//	    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//		cDesde.set(Calendar.HOUR_OF_DAY, 0);
//		cDesde.set(Calendar.MINUTE, 0);
//		cDesde.set(Calendar.MILLISECOND, 0);
//		fechaDesde = cDesde.getTime();
//	    System.out.println("Fecha 1 ::::::::::::: " + dateFormat.format(fechaDesde));
//	    System.out.println("Fecha 2 ::::::::::::: " + dateFormat.format(fechaHasta));
//		System.out.println(":::::daysBetween::::::"+daysBetween);

		daysBetween++;
		if (daysBetween > 366)
			throw new BSEException(CodigosError.fechas_invalidas);
		
		if (listaPersonas == null || listaPersonas.size() == 0)
			throw new BSEException(CodigosError.lista_personas_invalida);

		if (extension == null || extension.trim().equals(""))
			extension = "N";
		if (extension.equals("N"))
			fechaSalidaPais = fechaDesde;
		
		// valido plan de pago
		IESoa eSoaManager = FabricaNegocio.getFabricaNegocio().getESoaMgr();
		PlanPago planPagoObj = null;
		try {
			planPagoObj = eSoaManager.consultaPlanPago(em, planPago);
		} catch (Exception e) {
			throw new BSEException(CodigosError.plan_pago_invalido);
		}

		int sucursal = Integer.parseInt(Codigos.getCodigos().getSucursalEmisionViajeros(em));
		int ramoViajeros = Integer.parseInt(Codigos.getCodigos().getRamoEmisionViajeros(em));
		String productoViajeros = Codigos.getCodigos().getProductoEmisionViajeros(em);
		String productor = Codigos.getCodigos().getProductorEmisionViajeros(em);
		String moneda = Codigos.getCodigos().getMonedaEmisionViajeros(em);
		String tipoDocumento = " ";
		String documento = " ";
		
		String tipoCalculo = Codigos.getCodigos().getTipoCalculoEmisionViajeros(em).trim();
		String formaPago = Codigos.getCodigos().getFormaPagoEmisionViajeros(em).trim();
		String vigTecnica = Codigos.getCodigos().getVigTecnicaEmisionViajeros(em).trim();
		String medioPago = Codigos.getCodigos().getMedioPagoEmisionViajeros(em).trim();
		String origenPago = Codigos.getCodigos().getOrigenPagoEmisionViajeros(em).trim();
		String tipoFact = Codigos.getCodigos().getTipoFactEmisionViajeros(em).trim();
		String promocion = Codigos.getCodigos().getPromocionEmisionViajeros(em).trim();
		String renovacion = Codigos.getCodigos().getRenovacionEmisionViajeros(em).trim();
		String usuarioWeb = Codigos.getCodigos().getUsuarioWebEmisionViajeros(em).trim();

		// valido lista de viajeros
		String slistaPersonas = ""; //"43,gmuller2004@gmail.com,20000; 20,pedro@gmail.com,20000;";
		for(int i = 0; i < listaPersonas.size(); i++){
			if (listaPersonas.get(i).getCapitalMuerte() == null || listaPersonas.get(i).getCapitalMuerte().floatValue() <= 0)
				throw new BSEException(CodigosError.capital_muerte_invalido);
			if (listaPersonas.get(i).getCoberturaPrexistentes() == null || listaPersonas.get(i).getCoberturaPrexistentes().floatValue() <= 0)
				throw new BSEException(CodigosError.capital_cobertura_prexistentes_invalido);
		    
			String coberturaCovid = "N,N";
			if (listaPersonas.get(i).getCoberturaCovid() != null){
				if (listaPersonas.get(i).getCoberturaCovid().equals(Viajero.COVID_COBERTURA_PLUS))
					coberturaCovid = "N,S";
				if (listaPersonas.get(i).getCoberturaCovid().equals(Viajero.COVID_COBERTURA_BASICA))
					coberturaCovid = "S,N";
			}
			
			slistaPersonas += "20" + "," + "pp@gmail.com" + "," + // edad, email, cap muerte, cob preexistentes, covid, covid plus  
				listaPersonas.get(i).getCapitalMuerte() + "," + listaPersonas.get(i).getCoberturaPrexistentes() 
				 + "," + coberturaCovid + ";"; 
		}

		// valido plan de cobertura
		boolean planOk = false;
    	IESoa soaMgr = ESoaMgr.getESoaMgr();
    	List<PlanCobertura> planesCobertura = soaMgr.consultaPlanesCobertura(em, ramoViajeros, productoViajeros);
		if (planesCobertura != null && planesCobertura.size() > 0) {
			for (int i = 0; i < planesCobertura.size(); i++) {
				PlanCobertura pc = (PlanCobertura)planesCobertura.get(i);
				if (pc.getPlan().equals(planCobertura)){
					planOk = true;
				}
			}
		}
		if (!planOk)
			throw new BSEException(CodigosError.plan_cobertura_invalido);

		System.out.println(":::::::::::::::::::::::::::::::");
		System.out.println("slistaPersonas::::"+slistaPersonas);
		System.out.println(":::::::::::::::::::::::::::::::");

		// cotizo en rector
		Query q = em.createNamedQuery("PRO_COTIZAR_VIAJEROS");
		q.setParameter("P_TP_DOCUMENTO", tipoDocumento);
		q.setParameter("P_NRO_DOCUMENTO", documento);
		q.setParameter("P_SUCURSAL", sucursal);
		q.setParameter("P_RAMO", ramoViajeros);
		q.setParameter("P_PRODUCTO", productoViajeros);
		q.setParameter("P_PRODUCTOR", productor);
		q.setParameter("P_FECHA_DESDE", fechaDesde);
		q.setParameter("P_FECHA_HASTA", fechaHasta);
		q.setParameter("P_PLAN_COBERTURA", planCobertura);
		q.setParameter("P_PLAN_PAGO", planPago);
		q.setParameter("P_MONEDA", moneda);
		q.setParameter("P_TIPO_CALCULO", tipoCalculo);
		q.setParameter("P_FORMA_PAGO", formaPago);
		q.setParameter("P_VIG_TECNICA", vigTecnica);
		q.setParameter("P_MEDIO_PAGO", medioPago);
		q.setParameter("P_ORIGEN_PAGO", origenPago);
		q.setParameter("P_TIPO_FACT", tipoFact);
		q.setParameter("P_PROMOCION", promocion);
		q.setParameter("P_RENOVACION", renovacion);
		q.setParameter("P_USUARIO_WEB", usuarioWeb);
		q.setParameter("P_CONSUMIDOR_FINAL", "S"); 
		q.setParameter("P_FACT_MAIL", "N"); 
		q.setParameter("P_COASEGURO", "N"); 
		q.setParameter("P_EMITIR", "N");
		q.setParameter("P_LISTA_PERSONAS", slistaPersonas);
		q.setParameter("P_EXTENSION", extension);
		q.setParameter("P_FECHA_SALIDA_PAIS", fechaSalidaPais);

		
//		System.out.println("PRO_COTIZAR_VIAJEROS");
//		System.out.println("P_TP_DOCUMENTO => " + tipoDocumento);
//		System.out.println("P_NRO_DOCUMENTO => " + documento);
//		System.out.println("P_SUCURSAL => " + sucursal);
//		System.out.println("P_RAMO => " + ramoViajeros);
//		System.out.println("P_PRODUCTO => " + productoViajeros);
//		System.out.println("P_PRODUCTOR => " + productor);
//		System.out.println("P_FECHA_DESDE => " + fechaDesde);
//		System.out.println("P_FECHA_HASTA => " + fechaHasta);
//		System.out.println("P_PLAN_COBERTURA => " + planCobertura);
//		System.out.println("P_PLAN_PAGO => " + planPago);
//		System.out.println("P_MONEDA => " + moneda);
//		System.out.println("P_TIPO_CALCULO => " + tipoCalculo);
//		System.out.println("P_FORMA_PAGO => " + formaPago);
//		System.out.println("P_VIG_TECNICA => " + vigTecnica);
//		System.out.println("P_MEDIO_PAGO => " + medioPago);
//		System.out.println("P_ORIGEN_PAGO => " + origenPago);
//		System.out.println("P_TIPO_FACT => " + tipoFact);
//		System.out.println("P_PROMOCION => " + promocion);
//		System.out.println("P_RENOVACION => " + renovacion);
//		System.out.println("P_USUARIO_WEB => " + usuarioWeb);
//		System.out.println("P_CONSUMIDOR_FINAL => " + "S"); 
//		System.out.println("P_FACT_MAIL => " + "N"); 
//		System.out.println("P_COASEGURO => " + "N"); 
//		System.out.println("P_EMITIR => " + "N");
//		System.out.println("P_LISTA_PERSONAS => " + slistaPersonas);
		
		List<Object[]> l = q.getResultList();
		Object[] r = l.get(0);

		System.out.println("--------------------");
		for (int i = 0; i < r.length; i++){
			if (r[i] != null)
				System.out.println("R" + i + "=" + r[i].toString());
			else
				System.out.println("R" + i + "=null");
		}

		if (r[5] != null && !((String)r[5]).equals("0")){
			BSEException exc = new BSEException(CodigosError.error_cotizacion_rector, (String)r[6]);
			System.out.println("----------------------------ERRORPORTAL----------------------------");
			System.out.println("Error al cotizar RECTOR Codigo " + r[5] + " Mensaje " + (String)r[6]);
			System.out.println("-------------------------------------------------------------------");
			throw exc;
		}
			
		// armo retorno
		CotizacionViajeros cotizacion = new CotizacionViajeros();

		Ramo ramo = em.find(Ramo.class, Integer.valueOf(ramoViajeros));
		cotizacion.setRamo(ramo);
		
		Producto productoObj = em.find(Producto.class, productoViajeros);
		cotizacion.setProducto(productoObj);
		
		cotizacion.setSucursal(sucursal);
		
		cotizacion.setNroCotizacion(0);
		if (r[0] != null)
			cotizacion.setNroCotizacion(((Integer)r[0]).intValue());
		
		cotizacion.setTipoDocumento("");
		cotizacion.setNroDocumento("");
		cotizacion.setProductor(1);
		
		Moneda monedaObj = eSoaManager.consultaMoneda(em, moneda);
		cotizacion.setMoneda(monedaObj);

		cotizacion.setPremio(0);
		if (r[1] != null)
			cotizacion.setPremio(((Double)r[1]).doubleValue());

		cotizacion.setPremioFacturar(0);
		if (r[2] != null)
			cotizacion.setPremioFacturar(((Double)r[2]).doubleValue());
		
		planPagoObj = eSoaManager.consultaPlanPago(em, planPago); // .setCodigo(planPago);
		cotizacion.setPlanPago(planPagoObj);
		
		PlanCobertura planCoberturaObj = eSoaManager.consultaPlanCobertura(em, planCobertura);
		cotizacion.setPlanCobertura(planCoberturaObj);
		
		String cuotasStr = (String)r[3]; 
		String[] vecCuotas = cuotasStr.split(";");
		
		ArrayList<CuotaPago> cuotas = new ArrayList<CuotaPago>();
		for(int i = 0; i < vecCuotas.length; i++){
			CuotaPago cuota = new CuotaPago();
			cuota.setNroCuota(Integer.parseInt(vecCuotas[i].split(":")[0]));
			cuota.setImporte(Double.parseDouble(vecCuotas[i].split(":")[1]));
			cuotas.add(cuota);
		}
		
		cotizacion.setCuotas(cuotas);
		
		cotizacion.setFechaDesde(fechaDesde); 
		cotizacion.setFechaHasta(fechaHasta);
		
		Float tasaMsp = Float.valueOf(Codigos.getCodigos().getTasaMsp(em)); // 1.02F; 
		
		String sql = "select CAZB_NU_CONSECUTIVO, CAZB_MT_PREMIO " +
			"from cart_cotiza_planes " +
			"where cazb_capj_cd_sucursal = ? and cazb_nu_cotizacion = ? and cazb_nu_consecutivo > 0";

		Query query = em.createNativeQuery(sql);
		query.setParameter(1, Codigos.getCodigos().getSucursalEmisionViajeros(em));
		query.setParameter(2, cotizacion.getNroCotizacion());
		List<Object[]> resultPlanes = query.getResultList();

		ArrayList<Viajero> viajeros = new ArrayList<Viajero>(); 
		for(int i = 0; i < listaPersonas.size(); i++){
			Viajero viajero2 = new Viajero();
			
			viajero2.setCapitalMuerte(listaPersonas.get(i).getCapitalMuerte());
			viajero2.setCoberturaPrexistentes(listaPersonas.get(i).getCoberturaPrexistentes());
			viajero2.setCoberturaCovid(listaPersonas.get(i).getCoberturaCovid());
			viajero2.setMoneda(monedaObj);

			for (int x = 0; x < resultPlanes.size(); x++) {
				Object[] rPlanes = resultPlanes.get(x);

				int consecutivo = ((BigDecimal)rPlanes[0]).intValue();
				Float premio = ((BigDecimal)rPlanes[1]).floatValue();
				
				if (consecutivo == (i+1))
					viajero2.setPremio(premio * tasaMsp);
			}

			viajeros.add(viajero2);
		}
		cotizacion.setListaViajeros(viajeros);
		cotizacion.setExtension(extension);
		cotizacion.setFechaSalidaPais(fechaSalidaPais);
		
		return cotizacion;
	}
	
	@Override
	public PolizaViajeros emitirViajeros(EntityManager em, String tipoDocumentoContratante, String documentoContratante, 
			ArrayList<Viajero> listaPersonas, long nroCotizacion, String consumoFinal, String extension, Date fechaSalidaPais) throws Exception, BSEException{

		IESoa eSoaManager = FabricaNegocio.getFabricaNegocio().getESoaMgr();

		int sucursal = Integer.parseInt(Codigos.getCodigos().getSucursalEmisionViajeros(em));
		int ramoViajeros = Integer.parseInt(Codigos.getCodigos().getRamoEmisionViajeros(em));
		String productoViajeros = Codigos.getCodigos().getProductoEmisionViajeros(em);
		String productor = Codigos.getCodigos().getProductorEmisionViajeros(em);
		String moneda = Codigos.getCodigos().getMonedaEmisionViajeros(em);

		if (nroCotizacion == 0)
			throw new BSEException(CodigosError.cotizacion_invalida);

		String sqlCot = "select * " +
			"from cart_cotiza_banco " +
			"where cazb_capj_cd_sucursal = ? and cazb_nu_cotizacion = ? and " +
				"cazb_nu_consecutivo = 0 and cazb_carp_cd_ramo = ? and cazb_capu_cd_producto = ? and cazb_capo_nu_poliza is null";
	
		Query queryCot = em.createNativeQuery(sqlCot);
		queryCot.setParameter(1, sucursal);
		queryCot.setParameter(2, nroCotizacion);
		queryCot.setParameter(3, ramoViajeros);
		queryCot.setParameter(4, productoViajeros);
		List<Object[]> resultCot = queryCot.getResultList();

		if (resultCot == null || resultCot.size() == 0)
			throw new BSEException(CodigosError.cotizacion_invalida);

		if (listaPersonas == null || listaPersonas.size() == 0)
			throw new BSEException(CodigosError.lista_personas_invalida);

		if (consumoFinal == null || (!consumoFinal.trim().equals("S") && !consumoFinal.trim().equals("N")))
			throw new BSEException(CodigosError.consumidor_final_invalido);
		
		if (extension == null || extension.trim().equals(""))
			extension = "N";
		if (extension.equals("N") && fechaSalidaPais  == null)
			fechaSalidaPais = new Date();
		
		String sqlCot2 = "select max(cazb_nu_consecutivo) from cart_cotiza_banco " +
			"where cazb_capj_cd_sucursal = ? and cazb_nu_cotizacion = ? and " +
			"cazb_carp_cd_ramo = ? and cazb_capu_cd_producto = ? and cazb_capo_nu_poliza is null";
		Query queryCot2 = em.createNativeQuery(sqlCot2);
		queryCot2.setParameter(1, sucursal);
		queryCot2.setParameter(2, nroCotizacion);
		queryCot2.setParameter(3, ramoViajeros);
		queryCot2.setParameter(4, productoViajeros);
		Object res = queryCot2.getSingleResult();
		BigDecimal qty = (BigDecimal)res;
		//System.out.println(":::::::::::::::::::::::::::LA QTY : " + qty.intValue());
		if (listaPersonas.size() != qty.intValue())
			throw new BSEException(CodigosError.lista_personas_invalida);
			
		String regexEmail = "^(.+)@(.+)$";
		Pattern pattern = Pattern.compile(regexEmail);

		String slistaPersonas = "";
		Hashtable<String,String> control = new Hashtable<String,String>();
		
		for(int i = 0; i < listaPersonas.size(); i++){
			Viajero viajero = listaPersonas.get(i);
			
			if ((viajero.getEdad() < 0) || (viajero.getEdad() > 140))
				throw new BSEException(CodigosError.edad_invalida);

			if (viajero.getEmail() == null || viajero.getEmail().trim().equals(""))
				throw new BSEException(CodigosError.email_invalido);
			
			Matcher matcher = pattern.matcher(viajero.getEmail());
			if (!matcher.matches())
				throw new BSEException(CodigosError.email_invalido);
			
			if (viajero.getTipoDocumento() == null || viajero.getTipoDocumento().trim().equals(""))
				throw new BSEException(CodigosError.tipo_documento_invalido);
			if (viajero.getDocumento() == null || viajero.getDocumento().trim().equals(""))
				throw new BSEException(CodigosError.documento_invalido);
			if (viajero.getCapitalMuerte() == null || viajero.getCapitalMuerte().floatValue() <= 0)
				throw new BSEException(CodigosError.capital_muerte_invalido);
			if (viajero.getCoberturaPrexistentes() == null || viajero.getCoberturaPrexistentes().floatValue() <= 0)
				throw new BSEException(CodigosError.capital_cobertura_prexistentes_invalido);

			String key = viajero.getTipoDocumento() + "-" + viajero.getDocumento();
			if (!control.containsKey(key))
				control.put(key, key);
			else
				throw new BSEException(CodigosError.lista_personas_invalida);

			String nombre = " ";
			if (viajero.getNombre() != null && !viajero.getNombre().equals(""))
				nombre = viajero.getNombre();
			
			String apellido = " ";
			if (viajero.getApellido() != null && !viajero.getApellido().equals(""))
				apellido = viajero.getApellido();
			
			String coberturaCovid = "N,N";
			if (listaPersonas.get(i).getCoberturaCovid() != null){
				if (listaPersonas.get(i).getCoberturaCovid().equals(Viajero.COVID_COBERTURA_PLUS))
					coberturaCovid = "N,S";
				if (listaPersonas.get(i).getCoberturaCovid().equals(Viajero.COVID_COBERTURA_BASICA))
					coberturaCovid = "S,N";
			}

			slistaPersonas += viajero.getEdad() + "," + viajero.getEmail() + "," + viajero.getCapitalMuerte() + "," + 
				viajero.getCoberturaPrexistentes() + "," + viajero.getTipoDocumento() + "," + viajero.getDocumento() + "," + 
				nombre + "," + apellido + "," + coberturaCovid + ";";
		}

		Query q = em.createNamedQuery("PRO_EMITIR_VIAJEROS");
		q.setParameter("P_TP_DOCUMENTO_CONTRATANTE", tipoDocumentoContratante);
		q.setParameter("P_NRO_DOCUMENTO_CONTRATANTE", documentoContratante);
		q.setParameter("P_SUCURSAL", String.valueOf(sucursal));
		q.setParameter("P_RAMO", String.valueOf(ramoViajeros));
		q.setParameter("P_PRODUCTO", productoViajeros);
		q.setParameter("P_COTIZACION_EMITIDA", nroCotizacion);
		q.setParameter("P_CONSUMIDOR_FINAL", consumoFinal);
		q.setParameter("P_LISTA_PERSONAS", slistaPersonas);
		q.setParameter("P_EXTENSION", extension);
		q.setParameter("P_FECHA_SALIDA_PAIS", fechaSalidaPais);

//		System.out.println("PRO_EMITIR_VIAJEROS");
//		System.out.println("P_TP_DOCUMENTO_CONTRATANTE => " + tipoDocumentoContratante);
//		System.out.println("P_NRO_DOCUMENTO_CONTRATANTE => " + documentoContratante);
//		System.out.println("P_SUCURSAL => " + String.valueOf(sucursal));
//		System.out.println("P_RAMO => " + String.valueOf(ramoViajeros));
//		System.out.println("P_PRODUCTO => " + productoViajeros);
//		System.out.println("P_COTIZACION_EMITIDA => " + nroCotizacion);
//		System.out.println("P_CONSUMIDOR_FINAL => " + consumoFinal);
//		System.out.println("P_LISTA_PERSONAS => " + slistaPersonas);

		
		List<Object[]> l = q.getResultList();
		Object[] r = l.get(0);

		for (int i = 0; i < r.length; i++){
			if (r[i] != null)
				System.out.println("R" + i + "=" + r[i].toString());
			else
				System.out.println("R" + i + "=null");
		}

		if (r[8] != null){
			BSEException exc = new BSEException(CodigosError.error_emision_rector, (String)r[9]);
			System.out.println("----------------------------ERRORPORTAL----------------------------");
			System.out.println("Error al emitir RECTOR Codigo " + r[8] + " Mensaje " + (String)r[9]);
			System.out.println("-------------------------------------------------------------------");
			throw exc;
		}

		PolizaViajeros ret = new PolizaViajeros();

		Ramo ramo = em.find(Ramo.class, Integer.valueOf(ramoViajeros));
		ret.setRamo(ramo);
		
		Producto productoObj = em.find(Producto.class, productoViajeros);
		ret.setProducto(productoObj);
		ret.setSucursal(sucursal);
		ret.setNroCotizacion(nroCotizacion);
		
		ret.setTipoDocumento(listaPersonas.get(0).getTipoDocumento());
		ret.setNroDocumento(listaPersonas.get(0).getDocumento());
		ret.setProductor(Integer.parseInt(productor));
		
		Moneda monedaObj = eSoaManager.consultaMoneda(em, moneda);
		ret.setMoneda(monedaObj);
		
		double premio = ((Double)r[1]).doubleValue();
		double premioFacturar = ((Double)r[2]).doubleValue();

		ret.setTipoDocumento(tipoDocumentoContratante);
		ret.setNroDocumento(documentoContratante);
		ret.setPremio(premio);
		ret.setPremioFacturar(premioFacturar);
		ret.setFechaDesde((Date)r[3]);
		ret.setFechaHasta((Date)r[4]);
		ret.setNroPoliza(Integer.parseInt(r[0].toString()));
		
		int planPago = ((Integer)r[6]).intValue();
		PlanPago planPagoObj = eSoaManager.consultaPlanPago(em, planPago);
		ret.setPlanPago(planPagoObj);
		
		String planCobertura =  (String)r[5];
		PlanCobertura planCoberturaObj = eSoaManager.consultaPlanCobertura(em, planCobertura);
		ret.setPlanCobertura(planCoberturaObj);
		ret.setListaViajeros(listaPersonas);
		
		Float tasaMsp = Float.valueOf(Codigos.getCodigos().getTasaMsp(em)); // 1.02F; 

		String sql = "select CAZB_NU_CONSECUTIVO, CAZB_MT_PREMIO " +
			"from cart_cotiza_planes " +
			"where cazb_capj_cd_sucursal = ? and cazb_nu_cotizacion = ? and cazb_nu_consecutivo > 0";

		Query query = em.createNativeQuery(sql);
		query.setParameter(1, Codigos.getCodigos().getSucursalEmisionViajeros(em));
		query.setParameter(2, nroCotizacion);
		List<Object[]> resultPlanes = query.getResultList();

		for(int i = 0; i < listaPersonas.size(); i++){
			Viajero viajero2 = listaPersonas.get(i);
			
			for (int x = 0; x < resultPlanes.size(); x++) {
				Object[] rPlanes = resultPlanes.get(x);
	
				int consecutivoViajero = ((BigDecimal)rPlanes[0]).intValue();
				Float premioViajero = ((BigDecimal)rPlanes[1]).floatValue();
				
				if (consecutivoViajero == (i+1))
					viajero2.setPremio(premioViajero * tasaMsp);
			}
		}

		String cuotas = (String)r[7];
		cuotas=cuotas.replace(" ", "");
		cuotas=cuotas.replace("\\13", "");
		cuotas=cuotas.replace("\\10", "");
		
		String regex = "\b<cuota>\b";
		cuotas = cuotas.replace("<cuota>", "%");
		String[] vecCuotas = cuotas.split("%");
		
		ArrayList<CuotaPago> listaCuotas = new ArrayList<CuotaPago>();
		for(int z = 0; z < vecCuotas.length; z++){
			if (vecCuotas[z] != null && !vecCuotas[z].trim().equals("")){
				CuotaPago cuota = new CuotaPago();
				
				int inicio = vecCuotas[z].indexOf("<nrocuota>")+"<nrocuota>".length();
				int fin = vecCuotas[z].indexOf("</nrocuota>");
				
				int nroCuota = Integer.parseInt(vecCuotas[z].substring(inicio, fin));
				cuota.setNroCuota(nroCuota);
				
				inicio = vecCuotas[z].indexOf("<premiofacturacion>")+"<premiofacturacion>".length();
				fin = vecCuotas[z].indexOf("</premiofacturacion>");
				double importeCuota = Double.parseDouble(vecCuotas[z].substring(inicio, fin));
				
				cuota.setImporte(importeCuota);
				
				listaCuotas.add(cuota);
			}
		}
		
		ret.setCuotas(listaCuotas);
		ret.setExtension(extension);
		ret.setFechaSalidaPais(fechaSalidaPais);

		return ret;
	}
	
	@Override
	public ArrayList<CoberturaPrexistentes> consultaCoberturasPrexistentes(EntityManager em) throws Exception, BSEException {
		/* METODO VIEJO
		ArrayList<CoberturaPrexistentes> lista = new ArrayList<CoberturaPrexistentes>();
		
		String par = "B";

		System.out.println("-----------------------------------------------");

		String sql;
		Query query;
		try {
			Date fecha = new Date();
			Query q = em.createNamedQuery("PRO_OBT_PAR_BUSQUEDA");
			q.setParameter("P_FECHA_DESDE", fecha);
			
			par = (String)q.getSingleResult();
			sql = "";
			query = null;
			
			System.out.println("PAR1 = " + par);
			System.out.println("fecha = " + fecha);
			
			
			if (par == null || par.trim().equals("")){
				sql = "select max(crtb_dato1) from cret_tablas where crtb_cd_tabla = '220217'";
				query = em.createNativeQuery(sql);
				par = (String)q.getSingleResult();
				System.out.println("PAR2 = " + par);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sql = "select crtb_dato2, crtb_de_dato " +
			"from cret_tablas " +  
			"where crtb_cd_tabla = '220217' and crtb_dato1 = ? " + 
			"order by to_number(crtb_dato2)";

		query = em.createNativeQuery(sql);
		query.setParameter(1, par);
		List<Object[]> result = query.getResultList();

		CoberturaPrexistentes obj = new CoberturaPrexistentes();
		obj.setCobertura(500); // parametrizar esto!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		obj.setDescCobertura("U$S 500 BÁSICO");
		lista.add(obj);

		System.out.println("result.size() = " + result.size());

		for (int x = 0; x < result.size(); x++) {
			Object[] r = result.get(x);

			float cobertura = Float.parseFloat((String)r[0]);
			String descCobertura = (String)r[1];
			
			obj = new CoberturaPrexistentes();
			obj.setCobertura(cobertura);
			obj.setDescCobertura(descCobertura);

			System.out.println("cobertura = " + cobertura + " - " + descCobertura);

			lista.add(obj);
		}
		
		System.out.println("-----------------------------------------------");

		return lista;
		*/
		ArrayList<CoberturaPrexistentes> lista = new ArrayList<CoberturaPrexistentes>();
		
		String par = "B";

		System.out.println("---------Comienzo Cob Prex---------------------");

		
		String sql = "select crtb_dato2, crtb_de_dato " +
			"from cret_tablas " +  
			"where crtb_cd_tabla = 220217 and crtb_dato1 = ? " + 
			"order by to_number(trim(crtb_dato2))";

		Query query = em.createNativeQuery(sql);
		query.setParameter(1, par);
		List<Object[]> result = query.getResultList();

		CoberturaPrexistentes obj = new CoberturaPrexistentes();
		obj.setCobertura(500); // parametrizar esto!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		obj.setDescCobertura("U$S 500 BÁSICO");
		lista.add(obj);

		System.out.println("result.size() = " + result.size());

		for (int x = 0; x < result.size(); x++) {
			Object[] r = result.get(x);

			float cobertura = Float.parseFloat((String)r[0]);
			String descCobertura = (String)r[1];
			
			obj = new CoberturaPrexistentes();
			obj.setCobertura(cobertura);
			obj.setDescCobertura(descCobertura);

			System.out.println("cobertura = " + cobertura + " - " + descCobertura);

			lista.add(obj);
		}
		
		System.out.println("---------------Fin Cob Prex--------------------");

		return lista;
		
	}
	
	@Override
	public ArrayList<CoberturaMuerte> consultaCoberturasMuerte(EntityManager em) throws Exception, BSEException {
		ArrayList<CoberturaMuerte> lista = new ArrayList<CoberturaMuerte>();
		
		String sql = "select crtb_dato1, crtb_de_dato " +
			"from cret_tablas " +  
			"where crtb_cd_tabla = '220195'";

		Query query = em.createNativeQuery(sql);
		List<Object[]> result = query.getResultList();

		for (int x = 0; x < result.size(); x++) {
			Object[] r = result.get(x);

			float cobertura = Float.parseFloat((String)r[0]);
			String descCobertura = (String)r[1];
			
			CoberturaMuerte obj = new CoberturaMuerte();
			obj.setCobertura(cobertura);
			obj.setDescCobertura(descCobertura);
			lista.add(obj);
		}
		
		return lista;
	}
	
	
	

}
