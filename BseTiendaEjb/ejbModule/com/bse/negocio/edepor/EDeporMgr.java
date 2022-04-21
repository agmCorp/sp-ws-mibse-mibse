package com.bse.negocio.edepor;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.bse.accesodatos.comun.CretTablas;
import com.bse.accesodatos.edepor.CoberturaRc;
import com.bse.accesodatos.edepor.CotizacionEDepor;
import com.bse.accesodatos.edepor.PolizaEDepor;
import com.bse.accesodatos.edepor.TipoBuque;
import com.bse.accesodatos.esoa.CuotaPago;
import com.bse.accesodatos.esoa.Moneda;
import com.bse.accesodatos.esoa.PlanCobertura;
import com.bse.accesodatos.esoa.PlanPago;
import com.bse.accesodatos.esoa.Producto;
import com.bse.accesodatos.esoa.Ramo;
import com.bse.negocio.FabricaNegocio;
import com.bse.negocio.comun.BSEException;
import com.bse.negocio.comun.Codigos;
import com.bse.negocio.comun.CodigosError;


public class EDeporMgr implements IEDepor{

	public EDeporMgr() {
    }
	
    public static IEDepor getEDeporMgr() {
        return new EDeporMgr();
    }

	@Override
	public List<TipoBuque> consultaTiposBuques(EntityManager em) throws Exception, BSEException {
		ArrayList<TipoBuque> lista = new ArrayList<TipoBuque>();
		
		Query query = em.createNamedQuery("CretTablas.findTabla");
		query.setParameter("codigoTabla", Integer.valueOf("150196"));

		List<CretTablas> result = query.getResultList();

		if (result != null && result.size() > 0) {
			for (int i = 0; i < result.size(); i++) {
				CretTablas tabla = (CretTablas) result.get(i);				
				TipoBuque o = new TipoBuque(tabla.getDato1(), tabla.getDescripcion());
				lista.add(o);
			}
		}
		
		return lista;
	}

	@Override
	public TipoBuque consultaTipoBuque(EntityManager em, String tipo) throws Exception, BSEException {
		ArrayList<TipoBuque> lista = new ArrayList<TipoBuque>();
		
		Query query = em.createNamedQuery("CretTablas.findByClave");
		query.setParameter("codigoTabla", Integer.valueOf("150196"));
		query.setParameter("codigoDato", tipo);
		
		CretTablas tabla = (CretTablas)query.getSingleResult();
		TipoBuque o = new TipoBuque(tabla.getDato1(), tabla.getDescripcion());
		
		return o;
	}

	@Override
	public List<CoberturaRc> consultaCoberturasRc(EntityManager em)
			throws Exception, BSEException {
		ArrayList<CoberturaRc> lista = new ArrayList<CoberturaRc>();
		
		Query query = em.createNamedQuery("CretTablas.findTabla");
		query.setParameter("codigoTabla", Integer.valueOf("150145"));

		List<CretTablas> result = query.getResultList();

		if (result != null && result.size() > 0) {
			for (int i = 0; i < result.size(); i++) {
				CretTablas tabla = (CretTablas) result.get(i);				
				CoberturaRc o = new CoberturaRc(tabla.getDato1(), tabla.getDescripcion());
				lista.add(o);
			}
		}
		
		return lista;
	}

	@Override
	public CotizacionEDepor cotizarEDeporAnonimo(EntityManager em, String planCobertura, 
			String tipoBuque, Date fechaDesde, Date fechaHasta, double capital, int planPago) throws Exception, BSEException{		
//		System.out.println("COTDEPOR 1.1 entrando a cotizar -------");

		if (tipoBuque == null || tipoBuque.equals(""))
			throw new BSEException(CodigosError.tipo_buque_invalido);
		if (planCobertura == null || planCobertura.equals(""))
			throw new BSEException(CodigosError.plan_cobertura_invalido);
		String planesPago = Codigos.getCodigos().getPlanesPagoEDepor(em);
		
//		System.out.println("COTDEPOR 1.2 : " + planesPago);
		
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
		if (fechaDesde == null || fechaHasta == null)
			throw new BSEException(CodigosError.fechas_invalidas);
		if (fechaHasta.before(fechaDesde))
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
		
		daysBetween++;
		if (daysBetween > 366)
			throw new BSEException(CodigosError.fechas_invalidas);		
//		System.out.println("COTDEPOR 1.3");

		TipoBuque tipoBuqueObj = null;
		try {
			tipoBuqueObj = consultaTipoBuque(em, tipoBuque);
		} catch (Exception e) {
			tipoBuqueObj = null;
			throw new BSEException(CodigosError.tipo_buque_invalido);		
		}

		int sucursal = Integer.parseInt(Codigos.getCodigos().getSucursalEmisionEDepor(em));
		int ramoEDepor = Integer.parseInt(Codigos.getCodigos().getRamoEmisionEDepor(em));
		String productoEDepor = Codigos.getCodigos().getProductoEmisionEDepor(em);
		String productor = Codigos.getCodigos().getProductorEmisionEDepor(em);
		String moneda = Codigos.getCodigos().getMonedaEmisionEDepor(em);
		String tipoDocumento = " ";
		String documento = " ";

		String tipoCalculo = Codigos.getCodigos().getTipoCalculoEmisionEDepor(em).trim();
		String formaPago = Codigos.getCodigos().getFormaPagoEmisionEDepor(em).trim();
		String vigTecnica = Codigos.getCodigos().getVigTecnicaEmisionEDepor(em).trim();
		String medioPago = Codigos.getCodigos().getMedioPagoEmisionEDepor(em).trim();
		String origenPago = Codigos.getCodigos().getOrigenPagoEmisionEDepor(em).trim();
		String tipoFact = Codigos.getCodigos().getTipoFactEmisionEDepor(em).trim();
		String promocion = Codigos.getCodigos().getPromocionEmisionEDepor(em).trim();
		String renovacion = Codigos.getCodigos().getRenovacionEmisionEDepor(em).trim();
		String usuarioWeb = Codigos.getCodigos().getUsuarioWebEmisionEDepor(em).trim();

//		System.out.println("COTDEPOR 1.4");

		Query q = em.createNamedQuery("PRO_COTIZAR_EDEPOR");
		q.setParameter("P_TP_DOCUMENTO", tipoDocumento);
		q.setParameter("P_NRO_DOCUMENTO", documento);
		q.setParameter("P_SUCURSAL", sucursal);
		q.setParameter("P_RAMO", String.valueOf(ramoEDepor));
		q.setParameter("P_PRODUCTO", productoEDepor);
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
		q.setParameter("P_CONSUMIDOR_FINAL", "N");
		q.setParameter("P_FACT_MAIL", "N");
		q.setParameter("P_COASEGURO", "N");
		q.setParameter("P_EMITIR", "N");
		q.setParameter("P_TIPO_EMBARCACION", tipoBuque);
		q.setParameter("P_CAPITAL_RC", capital);
		
		List<Object[]> l = q.getResultList();
		Object[] r = l.get(0);

//		System.out.println("--------------------------------------------------------");
//		for (int i = 0; i < r.length; i++){
//			if (r[i] != null)
//				System.out.println("COTDEPOR R" + i + "=" + r[i].toString());
//			else
//				System.out.println("COTDEPOR R" + i + "=null");
//		}
//		System.out.println("--------------------------------------------------------");

		if (r[4] != null && !((String)r[4]).equals("0")){
			BSEException exc = new BSEException(CodigosError.error_cotizacion_rector, (String)r[5]);
			System.out.println("----------------------------ERRORPORTAL----------------------------");
			System.out.println("Error al cotizar RECTOR " + r[4] + " - " + (String)r[5]);
			System.out.println("-------------------------------------------------------------------");
			
			throw exc;
		}
			
		//System.out.println("cotizar 3-------");
		CotizacionEDepor cotizacion = new CotizacionEDepor();

		Ramo ramo = em.find(Ramo.class, Integer.valueOf(ramoEDepor));
		cotizacion.setRamo(ramo);
		
		Producto productoObj = em.find(Producto.class, productoEDepor);
		cotizacion.setProducto(productoObj);
		cotizacion.setSucursal(sucursal);
		
		cotizacion.setNroCotizacion(0);
		if (r[0] != null)
			cotizacion.setNroCotizacion(((Integer)r[0]).intValue());
		
		cotizacion.setTipoDocumento("");
		cotizacion.setNroDocumento("");
		cotizacion.setProductor(1);
		
		Moneda monedaObj = FabricaNegocio.getFabricaNegocio().getESoaMgr().consultaMoneda(em, moneda);
		cotizacion.setMoneda(monedaObj);

		cotizacion.setPremio(0);
		if (r[1] != null)
			cotizacion.setPremio(((Double)r[1]).doubleValue());

		cotizacion.setPremioFacturar(0);
		if (r[2] != null)
			cotizacion.setPremioFacturar(((Double)r[2]).doubleValue());
		
		cotizacion.setFechaDesde(fechaDesde);
		cotizacion.setFechaHasta(fechaHasta);
		
		PlanPago planPagoObj = FabricaNegocio.getFabricaNegocio().getESoaMgr().consultaPlanPago(em, planPago);
		planPagoObj.setCodigo(planPago);
		cotizacion.setPlanPago(planPagoObj);
		
		PlanCobertura planCoberturaObj = FabricaNegocio.getFabricaNegocio().getESoaMgr().consultaPlanCoberturaRamo(em, ramo.getRamo(), planCobertura);
		planCoberturaObj.setPlan(planCobertura);
		planCoberturaObj.setProducto(productoObj);
		cotizacion.setPlanCobertura(planCoberturaObj);
		
		String cuotasStr = (String)r[3]; 
		String[] vecCuotas = cuotasStr.split(";");
		
		System.out.println("COTDEPOR 1.5");

		ArrayList<CuotaPago> cuotas = new ArrayList<CuotaPago>();
		for(int i = 0; i < vecCuotas.length; i++){
			CuotaPago cuota = new CuotaPago();
			cuota.setNroCuota(Integer.parseInt(vecCuotas[i].split(":")[0]));
			cuota.setImporte(Double.parseDouble(vecCuotas[i].split(":")[1]));
			cuotas.add(cuota);
		}
		
		cotizacion.setCuotas(cuotas);
		cotizacion.setTipoBuque(tipoBuqueObj);
		cotizacion.setCapital(capital);

		System.out.println("COTDEPOR 1.6");
		return cotizacion;
	}

	@Override
	public PolizaEDepor emitirEDepor(EntityManager em, String tipoDocumento, String documento, String matriculaBuque, String nombreBuque, long nroCotizacion, 
				String consumoFinal) throws Exception, BSEException{
		System.out.println("EMIDEPOR 1.1");

		if (tipoDocumento == null || tipoDocumento.equals(""))
			throw new BSEException(CodigosError.tipo_documento_invalido);
		if (documento == null || documento.equals(""))
			throw new BSEException(CodigosError.documento_invalido);
		if (nroCotizacion == 0)
			throw new BSEException(CodigosError.cotizacion_invalida);
		if (!consumoFinal.equals("S") && !consumoFinal.equals("N"))
			throw new BSEException(CodigosError.consumidor_final_invalido);
		if (matriculaBuque == null || matriculaBuque.equals(""))
			throw new BSEException(CodigosError.matricula_vehiculo_invalida);
		if (nombreBuque == null || nombreBuque.equals(""))
			throw new BSEException(CodigosError.nombre_invalido);

		int sucursal = Integer.parseInt(Codigos.getCodigos().getSucursalEmisionEDepor(em));
		int ramoEDepor = Integer.parseInt(Codigos.getCodigos().getRamoEmisionEDepor(em));
		String productoEDepor = Codigos.getCodigos().getProductoEmisionEDepor(em);
		String productor = Codigos.getCodigos().getProductorEmisionEDepor(em);
		String moneda = Codigos.getCodigos().getMonedaEmisionEDepor(em);
		String usuarioWeb = Codigos.getCodigos().getUsuarioWebEmisionEDepor(em).trim();

		String sqlCot = "select * " +
			"from cart_cotiza_banco " +
			"where cazb_capj_cd_sucursal = ? and cazb_nu_cotizacion = ? and " +
				"cazb_nu_consecutivo = 0 and cazb_carp_cd_ramo = ? and cazb_capu_cd_producto = ?";
	
		Query queryCot = em.createNativeQuery(sqlCot);
		queryCot.setParameter(1, sucursal);
		queryCot.setParameter(2, nroCotizacion);
		queryCot.setParameter(3, ramoEDepor);
		queryCot.setParameter(4, productoEDepor);
		List<Object[]> resultCot = queryCot.getResultList();

		System.out.println("EMIDEPOR 1.2 " + nroCotizacion);

		if (resultCot == null || resultCot.size() == 0)
			throw new BSEException(CodigosError.cotizacion_invalida);

		Query q = em.createNamedQuery("PRO_EMITIR_EDEPOR");
		q.setParameter("P_TP_DOCUMENTO_CONTRATANTE", tipoDocumento);
		q.setParameter("P_NRO_DOCUMENTO_CONTRATANTE", documento);
		q.setParameter("P_SUCURSAL", String.valueOf(sucursal));
		q.setParameter("P_RAMO", String.valueOf(ramoEDepor));
		q.setParameter("P_PRODUCTO", productoEDepor);
		q.setParameter("P_COTIZACION_EMITIDA", nroCotizacion);
		q.setParameter("P_CONSUMIDOR_FINAL", consumoFinal);
		q.setParameter("P_MATRICULA_BUQUE", matriculaBuque);
		q.setParameter("P_NOMBRE_BUQUE", nombreBuque);
		q.setParameter("P_USUARIO_WEB", usuarioWeb);

		List<Object[]> l = q.getResultList();
		Object[] r = l.get(0);

		for (int i = 0; i < r.length; i++){
			if (r[i] != null)
				System.out.println("EMIDEPOR R" + i + "=" + r[i].toString());
			else
				System.out.println("EMIDEPOR R" + i + "=null");
		}

		if (r[10] != null){
			BSEException exc = new BSEException(CodigosError.error_emision_rector, (String)r[11]);
			System.out.println("----------------------------ERRORPORTAL----------------------------");
			System.out.println("Error al emitir RECTOR Codigo " + r[10] + " Mensaje " + (String)r[11]);
			System.out.println("-------------------------------------------------------------------");
			throw exc;
		}

		System.out.println("EMIDEPOR 1.3");

		int nroPoliza = ((Integer)r[0]).intValue();
		double premio = ((Double)r[1]).doubleValue();
		double premioFacturar = ((Double)r[2]).doubleValue();
		Date fechaDesde = (Date)r[3];
		Date fechaHasta = (Date)r[4];
		String planCobertura = (String)r[5]; 
	    int planPago = ((Integer)r[6]).intValue();
		String tipoBuque = (String)r[7]; 
		double capital = ((Double)r[8]).doubleValue();
		String cuotas = (String)r[9]; 
	    
		PolizaEDepor polizaObj = new PolizaEDepor();

		Ramo ramoObj = em.find(Ramo.class, Integer.valueOf(ramoEDepor));
		polizaObj.setRamo(ramoObj);
		
		Producto productoObj = em.find(Producto.class, productoEDepor);
		polizaObj.setProducto(productoObj);
		
		polizaObj.setSucursal(200);
		polizaObj.setNroCotizacion(nroCotizacion);
		polizaObj.setTipoDocumento(tipoDocumento);
		polizaObj.setNroDocumento(documento);
		polizaObj.setProductor(Integer.valueOf(productor));
		
		Moneda monedaObj = FabricaNegocio.getFabricaNegocio().getESoaMgr().consultaMoneda(em, moneda);
		polizaObj.setMoneda(monedaObj);
		
		polizaObj.setPremio(premio);
		polizaObj.setPremioFacturar(premioFacturar);

		polizaObj.setFechaDesde(fechaDesde);
		polizaObj.setFechaHasta(fechaHasta);
		
		PlanCobertura planCoberturaObj = FabricaNegocio.getFabricaNegocio().getESoaMgr().consultaPlanCoberturaRamo(em, ramoObj.getRamo(), planCobertura);
		planCoberturaObj.setProducto(productoObj);
		polizaObj.setPlanCobertura(planCoberturaObj);
		
		PlanPago planPagoObj = FabricaNegocio.getFabricaNegocio().getESoaMgr().consultaPlanPago(em, planPago);
		polizaObj.setPlanPago(planPagoObj);
		
		polizaObj.setNroPoliza(nroPoliza);
		
		cuotas=cuotas.replace(" ", "");
		cuotas=cuotas.replace("\\13", "");
		cuotas=cuotas.replace("\\10", "");
		
		String regex = "\b<cuota>\b";
		cuotas = cuotas.replace("<cuota>", "%");
		String[] vecCuotas = cuotas.split("%");
		System.out.println("CUOTAS:::::::::::::::::::::::::::"+cuotas);
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
		
		polizaObj.setCuotas(listaCuotas);
		polizaObj.setCapital(capital);
		TipoBuque tipo = consultaTipoBuque(em, tipoBuque);
		polizaObj.setTipoBuque(tipo);
		polizaObj.setMatriculaBuque(matriculaBuque);
		polizaObj.setNombreBuque(nombreBuque);
		
		System.out.println("EMIDEPOR 1.4");

		return polizaObj;
	}



}
