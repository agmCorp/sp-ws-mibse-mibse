package com.bse.negocio.esoa;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import com.bse.accesodatos.comun.CretTablas;
import com.bse.accesodatos.esoa.CategoriaVehiculo;
import com.bse.accesodatos.esoa.CotizacionSoa;
import com.bse.accesodatos.esoa.CuotaPago;
import com.bse.accesodatos.esoa.MarcaVehiculo;
import com.bse.accesodatos.esoa.Moneda;
import com.bse.accesodatos.esoa.PlanCobertura;
import com.bse.accesodatos.esoa.PlanCuota;
import com.bse.accesodatos.esoa.PlanPago;
import com.bse.accesodatos.esoa.PolizaPortal;
import com.bse.accesodatos.esoa.PolizaPortalPK;
import com.bse.accesodatos.esoa.PolizaSoa;
import com.bse.accesodatos.esoa.Producto;
import com.bse.accesodatos.esoa.Ramo;
import com.bse.negocio.comun.BSEException;
import com.bse.negocio.comun.Codigos;
import com.bse.negocio.comun.CodigosError;


public class ESoaMgr implements IESoa{

	public ESoaMgr() {
    }
	
    public static IESoa getESoaMgr() {
        return new ESoaMgr();
    }

	@Override
	public List<MarcaVehiculo> consultaMarcasVehiculos(EntityManager em) throws Exception, BSEException {
		ArrayList<MarcaVehiculo> lista = new ArrayList<MarcaVehiculo>();
		
		Query query = em.createNamedQuery("CretTablas.findTabla");
		query.setParameter("codigoTabla", Integer.valueOf("140001"));

		List<CretTablas> result = query.getResultList();

		if (result != null && result.size() > 0) {
			for (int i = 0; i < result.size(); i++) {
				CretTablas tabla = (CretTablas) result.get(i);				
				MarcaVehiculo marca = new MarcaVehiculo(tabla.getDato1(), tabla.getDescripcion());
				lista.add(marca);
			}
		}
		
		return lista;
	}
	
	@Override
	public MarcaVehiculo consultaMarcaVehiculo(EntityManager em, String marca) throws Exception, BSEException {
		Query query = em.createNamedQuery("CretTablas.findByClave");
		query.setParameter("codigoTabla", Integer.valueOf("140001"));
		query.setParameter("codigoDato", marca);

		List<CretTablas> result = query.getResultList();

		if (result != null && result.size() > 0) {
			CretTablas tabla = (CretTablas) result.get(0);				
			MarcaVehiculo marcaObj = new MarcaVehiculo(tabla.getDato1(), tabla.getDescripcion());
			return marcaObj;
		}
		throw new BSEException(CodigosError.marca_vehiculo_invalida);
	}

	@Override
	public List<CategoriaVehiculo> consultaCategoriasVehiculos(EntityManager em) throws Exception, BSEException {
		ArrayList<CategoriaVehiculo> lista = new ArrayList<CategoriaVehiculo>();
		
		Query query = em.createNamedQuery("CretTablas.findTabla");
		query.setParameter("codigoTabla", Integer.valueOf("141021"));

		List<CretTablas> result = query.getResultList();

		if (result != null && result.size() > 0) {
			for (int i = 0; i < result.size(); i++) {
				CretTablas tabla = (CretTablas) result.get(i);				
				CategoriaVehiculo cat = new CategoriaVehiculo(tabla.getDato1(), tabla.getDescripcion());
				lista.add(cat);
			}
		}

		return lista;
	}

	@Override
	public CategoriaVehiculo consultaCategoriaVehiculo(EntityManager em, String categoria) throws Exception, BSEException {
		Query query = em.createNamedQuery("CretTablas.findByClave");
		query.setParameter("codigoTabla", Integer.valueOf("141021"));
		query.setParameter("codigoDato", categoria);

		List<CretTablas> result = query.getResultList();

		if (result != null && result.size() > 0) {
			CretTablas tabla = (CretTablas) result.get(0);				
			CategoriaVehiculo categoriaObj = new CategoriaVehiculo(tabla.getDato1(), tabla.getDescripcion());
			return categoriaObj;
		}
		throw new BSEException(CodigosError.categoria_vehiculo_invalida);
	}

	@Override
	public CotizacionSoa cotizarSoaAnonimo(EntityManager em, String planCobertura, String marcaVehiculo, String anioVehiculo, String categoriaVehiculo, int planPago) throws Exception, BSEException {
		System.out.println("COTSOA 1.1 entrando a cotizar -------");
		//System.out.println(":::planCobertura:::"+planCobertura);

		if (marcaVehiculo == null || marcaVehiculo.equals(""))
			throw new BSEException(CodigosError.marca_vehiculo_invalida);
		if (anioVehiculo == null || anioVehiculo.equals(""))
			throw new BSEException(CodigosError.anio_vehiculo_invalido);
		if (categoriaVehiculo == null || categoriaVehiculo.equals(""))
			throw new BSEException(CodigosError.categoria_vehiculo_invalida);
		if (planCobertura == null || planCobertura.equals(""))
			throw new BSEException(CodigosError.plan_cobertura_invalido);
		
		String planesPago = Codigos.getCodigos().getPlanesPagoSoa(em);
		
		//System.out.println("COTSOA 1.2 planCobertura : " + planCobertura);
		
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
		
		System.out.println("COTSOA 1.3");

		int sucursal = Integer.parseInt(Codigos.getCodigos().getSucursalEmisionSoa(em));
		int ramoSoa = Integer.parseInt(Codigos.getCodigos().getRamoEmisionSoa(em));
		String productoSoa = Codigos.getCodigos().getProductoEmisionSoa(em);
		String productor = Codigos.getCodigos().getProductorEmisionSoa(em);
		String moneda = Codigos.getCodigos().getMonedaEmisionSoa(em);
    	String matriculaVehiculo = "AAA1000";
		String tipoDocumento = " ";
		String documento = " ";
		Date fechaDesde = new Date();
		String padronVehiculo = "0";
		String motorVehiculo = "0";
		String chasisVehiculo = "";

		String tipoCalculo = Codigos.getCodigos().getTipoCalculoEmisionSoa(em).trim();
		String formaPago = Codigos.getCodigos().getFormaPagoEmisionSoa(em).trim();
		String vigTecnica = Codigos.getCodigos().getVigTecnicaEmisionSoa(em).trim();
		String medioPago = Codigos.getCodigos().getMedioPagoEmisionSoa(em).trim();
		String origenPago = Codigos.getCodigos().getOrigenPagoEmisionSoa(em).trim();
		String tipoFact = Codigos.getCodigos().getTipoFactEmisionSoa(em).trim();
		String promocion = Codigos.getCodigos().getPromocionEmisionSoa(em).trim();
		String renovacion = Codigos.getCodigos().getRenovacionEmisionSoa(em).trim();
		String usuarioWeb = Codigos.getCodigos().getUsuarioWebEmisionSoa(em).trim();

		System.out.println("COTSOA 1.4");

		Query q = em.createNamedQuery("PRO_COTIZAR_SOA");
		q.setParameter("P_TP_DOCUMENTO", tipoDocumento);
		q.setParameter("P_NRO_DOCUMENTO", documento);
		q.setParameter("P_SUCURSAL", sucursal);
		q.setParameter("P_RAMO", String.valueOf(ramoSoa));
		q.setParameter("P_PRODUCTO", productoSoa);
		q.setParameter("P_PRODUCTOR", productor);
		q.setParameter("P_FECHA_DESDE", fechaDesde);
		q.setParameter("P_MARCA_VEHICULO", marcaVehiculo);
		q.setParameter("P_ANIO_VEHICULO", anioVehiculo);
		q.setParameter("P_CATEGORIA_VEHICULO", categoriaVehiculo);
		q.setParameter("P_MATRICULA_VEHICULO", matriculaVehiculo);
		q.setParameter("P_PADRON_VEHICULO", padronVehiculo);
		q.setParameter("P_MOTOR_VEHICULO", motorVehiculo);
		q.setParameter("P_CHASIS_VEHICULO", chasisVehiculo);
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
		
		List<Object[]> l = q.getResultList();
		Object[] r = l.get(0);

		for (int i = 0; i < r.length; i++){
			if (r[i] != null)
				System.out.println("COTSOA R" + i + "=" + r[i].toString());
			else
				System.out.println("COTSOA R" + i + "=null");
		}

		if (r[6] != null && !((String)r[6]).equals("0")){
			BSEException exc = new BSEException(CodigosError.error_cotizacion_rector, (String)r[6]);
			System.out.println("----------------------------ERRORPORTAL----------------------------");
			System.out.println("Error al cotizar RECTOR " + r[5] + " - " + (String)r[6]);
			System.out.println("-------------------------------------------------------------------");
			
			throw exc;
		}

		//System.out.println("cotizar 3-------");
		CotizacionSoa cotizacion = new CotizacionSoa();

		Ramo ramo = em.find(Ramo.class, Integer.valueOf(ramoSoa));
		cotizacion.setRamo(ramo);
		
		Producto productoObj = em.find(Producto.class, productoSoa);
		cotizacion.setProducto(productoObj);
		
		cotizacion.setSucursal(sucursal);
		
		cotizacion.setNroCotizacion(0);
		if (r[0] != null)
			cotizacion.setNroCotizacion(((Integer)r[0]).intValue());
		
		cotizacion.setTipoDocumento("");
		cotizacion.setNroDocumento("");
		cotizacion.setProductor(1);
		
		Moneda monedaObj = consultaMoneda(em, moneda);
		cotizacion.setMoneda(monedaObj);

		cotizacion.setPremio(0);
		if (r[1] != null)
			cotizacion.setPremio(((Double)r[1]).doubleValue());

		cotizacion.setPremioFacturar(0);
		if (r[2] != null)
			cotizacion.setPremioFacturar(((Double)r[2]).doubleValue());
		
		cotizacion.setFechaDesde(fechaDesde);
		cotizacion.setFechaHasta((Date)r[3]);
		
		PlanPago planPagoObj = consultaPlanPago(em, planPago);
		planPagoObj.setCodigo(planPago);
		cotizacion.setPlanPago(planPagoObj);
		
		MarcaVehiculo marca = this.consultaMarcaVehiculo(em, marcaVehiculo);
		cotizacion.setMarcaVehiculo(marca);
		
		CategoriaVehiculo categoria = consultaCategoriaVehiculo(em, categoriaVehiculo);
		categoria.setCodigo(categoriaVehiculo);
		cotizacion.setCategoriaVehiculo(categoria);
		
		PlanCobertura planCoberturaObj = consultaPlanCobertura(em, planCobertura);
		planCoberturaObj.setPlan(planCobertura);
		cotizacion.setPlanCobertura(planCoberturaObj);
		
		cotizacion.setMatriculaVehiculo(matriculaVehiculo);
		cotizacion.setPadronVehiculo(padronVehiculo);
		cotizacion.setChasisVehiculo(chasisVehiculo);
		cotizacion.setMotorVehiculo(motorVehiculo);
		cotizacion.setAnioVehiculo(anioVehiculo);

		String cuotasStr = (String)r[4]; 
		String[] vecCuotas = cuotasStr.split(";");
		
		String planesCuotasStr = (String)r[5]; 
		System.out.println("COTSOA 1.5 : " + planesCuotasStr);

		
		// *1;1;4742;1:4742#*2;2;4842;1:2421#2:2421#*3;3;4875;1:1625#2:1625#3:1625#*4;4;4912;1:1228#2:1228#3:1228#4:1228#*5;5;4945;1:989#2:989#3:989#4:989#5:989#*6;6;4980;1:830#2:830#3:830#4:830#5:830#6:830#		

// parte de planes de cuotas, comentado hasta nuevo aviso		
/*
		ArrayList<PlanCuota> planesDeCuotas = new ArrayList<PlanCuota>();
		
		String[] planes = planesCuotasStr.split("\\*".toString());
		for(int i = 0; i < planes.length; i++){
			String plan = planes[i];
			
			System.out.println("PLAN de " + i + " " + plan);
			
			if (plan == null || plan.equals(""))
				continue;
			
			String[] partes = plan.split(";"); // 1;1;4742;1:4742#

			System.out.println("PLAN " + plan);
			System.out.println(" PLAN="+partes[0]+" CUOTAS="+partes[1] + " PTF="+partes[2]);
			
			PlanCuota planCuota = new PlanCuota();
			PlanPago planDePago = consultaPlanPago(em, Integer.parseInt(partes[0]));
			//planPagoObj.setCodigo(planPago);
			
			planCuota.setPlanPago(planDePago);
			planCuota.setCantCuotas(Integer.parseInt(partes[1]));
			planCuota.setImporteTotal(Double.parseDouble(partes[2]));
			
			ArrayList<CuotaPago> cuotasDelPlan = new ArrayList<CuotaPago>();

			String[] cuotasVec = partes[3].split("#"); // 1:4742#
			for(int x = 0; x < cuotasVec.length; x++){
				String nroCuota = cuotasVec[x].split(":")[0];
				String importeCuota = cuotasVec[x].split(":")[1];
				System.out.println(" CUOTA " + nroCuota + " X $" + importeCuota);
				
				CuotaPago cuotaPago = new CuotaPago();
				cuotaPago.setNroCuota(Integer.parseInt(nroCuota));
				cuotaPago.setImporte(Double.parseDouble(importeCuota));
				cuotasDelPlan.add(cuotaPago);
			}
			planCuota.setCuotas(cuotasDelPlan);
			planesDeCuotas.add(planCuota);
		}
		
		for(int y = 0; y < planesDeCuotas.size(); y++){
			PlanCuota k = planesDeCuotas.get(y);
			System.out.println("Plan " + k.getPlanPago().getCodigo() + " - " + k.getPlanPago().getDescripcion() + " cuotas " + k.getCantCuotas() + " ptf " + k.getImporteTotal());
			ArrayList<CuotaPago> cuotasDelPlan = k.getCuotas();
			for(int z = 0; z < cuotasDelPlan.size(); z++){
				System.out.println("cuota nro " + cuotasDelPlan.get(z).getNroCuota() + " importe " + cuotasDelPlan.get(z).getImporte());
			}
		}
		
		ArrayList<CuotaPago> cuotas = new ArrayList<CuotaPago>();
		for(int i = 0; i < vecCuotas.length; i++){
			CuotaPago cuota = new CuotaPago();
			cuota.setNroCuota(Integer.parseInt(vecCuotas[i].split(":")[0]));
			cuota.setImporte(Double.parseDouble(vecCuotas[i].split(":")[1]));
			cuotas.add(cuota);
		}
		
		cotizacion.setCuotas(cuotas);
*/

		return cotizacion;
	}

	@Override
	public PolizaSoa emitirSoa(EntityManager em, String tipoDocumento, String documento, String matriculaVehiculo, String padronVehiculo,
				String motorVehiculo, String chasisVehiculo, long nroCotizacion, String consumoFinal) throws Exception, BSEException{

		//System.out.println("EMISOA 1.1");

		if (tipoDocumento == null || tipoDocumento.equals(""))
			throw new BSEException(CodigosError.tipo_documento_invalido);
		if (documento == null || documento.equals(""))
			throw new BSEException(CodigosError.documento_invalido);
		if (nroCotizacion == 0)
			throw new BSEException(CodigosError.cotizacion_invalida);
		if (!consumoFinal.equals("S") && !consumoFinal.equals("N"))
			throw new BSEException(CodigosError.consumidor_final_invalido);
		if ( (matriculaVehiculo == null || matriculaVehiculo.equals("")) && 
					(chasisVehiculo == null || chasisVehiculo.equals("")) )
			throw new BSEException(CodigosError.matricula_vehiculo_invalida);

		int sucursal = Integer.parseInt(Codigos.getCodigos().getSucursalEmisionSoa(em));
		int ramoSoa = Integer.parseInt(Codigos.getCodigos().getRamoEmisionSoa(em));
		String productoSoa = Codigos.getCodigos().getProductoEmisionSoa(em);
		String productor = Codigos.getCodigos().getProductorEmisionSoa(em);
		String moneda = Codigos.getCodigos().getMonedaEmisionSoa(em);

		String sqlCot = "select CAZB_CAFR_CD_FRAGMENT, CAZB_CAFR_CD_FRAGMENT " +
			"from cart_cotiza_banco " +
			"where cazb_capj_cd_sucursal = ? and cazb_nu_cotizacion = ? and " +
				"cazb_nu_consecutivo = 1 and cazb_carp_cd_ramo = ? and cazb_capu_cd_producto = ?";
	
		Query queryCot = em.createNativeQuery(sqlCot);
		queryCot.setParameter(1, sucursal);
		queryCot.setParameter(2, nroCotizacion);
		queryCot.setParameter(3, ramoSoa);
		queryCot.setParameter(4, productoSoa);
		List<Object[]> resultCot = queryCot.getResultList();

		//System.out.println("EMISOA 1.2");

		if (resultCot == null || resultCot.size() == 0)
			throw new BSEException(CodigosError.cotizacion_invalida);

		int planPago = 0;
		for (int i = 0; i < resultCot.size(); i++) {
			Object[] row = (Object[]) resultCot.get(i);
			planPago = ((BigDecimal)row[0]).intValue();
		}

		Query q = em.createNamedQuery("PRO_EMITIR_SOA");
		q.setParameter("P_TP_DOCUMENTO", tipoDocumento);
		q.setParameter("P_NRO_DOCUMENTO", documento);
		q.setParameter("P_SUCURSAL", String.valueOf(sucursal));
		q.setParameter("P_RAMO", String.valueOf(ramoSoa));
		q.setParameter("P_PRODUCTO", productoSoa);
		q.setParameter("P_MATRICULA_VEHICULO", matriculaVehiculo);
		q.setParameter("P_PADRON_VEHICULO", padronVehiculo);
		q.setParameter("P_MOTOR_VEHICULO", motorVehiculo);
		q.setParameter("P_CHASIS_VEHICULO", chasisVehiculo);
		q.setParameter("P_COTIZACION_EMITIDA", nroCotizacion);
		q.setParameter("P_CONSUMO_FINAL", consumoFinal);

		List<Object[]> l = q.getResultList();
		Object[] r = l.get(0);

		for (int i = 0; i < r.length; i++){
			if (r[i] != null)
				System.out.println("EMISOA R" + i + "=" + r[i].toString());
			else
				System.out.println("EMISOA R" + i + "=null");
		}

		if (r[11] != null){
			BSEException exc = new BSEException(CodigosError.error_emision_rector, (String)r[12]);
			System.out.println("----------------------------ERRORPORTAL----------------------------");
			System.out.println("Error al emitir RECTOR Codigo " + r[11] + " Mensaje " + (String)r[12]);
			System.out.println("-------------------------------------------------------------------");
			throw exc;
		}

		//System.out.println("EMISOA 1.3");

		double premio = ((Double)r[1]).doubleValue();
		double premioFacturar = ((Double)r[2]).doubleValue();
		Date fechaDesde = (Date)r[6];
		Date fechaHasta = (Date)r[7];
		String marcaVehiculo = (String)r[3];
		String categoriaVehiculo = (String)r[5];
		String anioVehiculo = (String)r[4];
		int nroPoliza = ((Integer)r[0]).intValue();
		
		String planCobertura = (String)r[8]; 
	    //int planPago = ((Integer)r[9]).intValue();
		String cuotas = (String)r[10]; 
	    
		PolizaSoa polizaSoa = new PolizaSoa();

		Ramo ramoObj = em.find(Ramo.class, Integer.valueOf(ramoSoa));
		polizaSoa.setRamo(ramoObj);
		
		Producto productoObj = em.find(Producto.class, productoSoa);
		polizaSoa.setProducto(productoObj);
		
		polizaSoa.setSucursal(200);
		polizaSoa.setNroCotizacion(nroCotizacion);
		polizaSoa.setTipoDocumento(tipoDocumento);
		polizaSoa.setNroDocumento(documento);
		polizaSoa.setProductor(Integer.valueOf(productor));
		
		Moneda monedaObj = consultaMoneda(em, moneda);
		polizaSoa.setMoneda(monedaObj);
		
		polizaSoa.setPremio(premio);
		polizaSoa.setPremioFacturar(premioFacturar);

		polizaSoa.setFechaDesde(fechaDesde);
		polizaSoa.setFechaHasta(fechaHasta);
		
		PlanCobertura planCoberturaObj = consultaPlanCobertura(em, planCobertura);
		polizaSoa.setPlanCobertura(planCoberturaObj);
		
		PlanPago planPagoObj = consultaPlanPago(em, planPago);
		polizaSoa.setPlanPago(planPagoObj);
		
		MarcaVehiculo marcaObj = consultaMarcaVehiculo(em, marcaVehiculo);
		polizaSoa.setMarcaVehiculo(marcaObj);
		
		CategoriaVehiculo categoriaObj = consultaCategoriaVehiculo(em, categoriaVehiculo);
		polizaSoa.setCategoriaVehiculo(categoriaObj);
		
		polizaSoa.setMatriculaVehiculo(matriculaVehiculo);
		polizaSoa.setPadronVehiculo(padronVehiculo);
		polizaSoa.setChasisVehiculo(chasisVehiculo);
		polizaSoa.setMotorVehiculo(motorVehiculo);
		polizaSoa.setAnioVehiculo(anioVehiculo);
		polizaSoa.setNroPoliza(nroPoliza);
		
		cuotas=cuotas.replace(" ", "");
		cuotas=cuotas.replace("\\13", "");
		cuotas=cuotas.replace("\\10", "");
		//Pattern.quote(arg0)
		
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
		
		polizaSoa.setCuotas(listaCuotas);
		
		//System.out.println("EMISOA 1.4");

		return polizaSoa;
	}

	@Override
	public List<PlanCobertura> consultaPlanesCobertura(EntityManager em, int ramo, String producto) throws Exception, BSEException {
		ArrayList<PlanCobertura> lista = new ArrayList<PlanCobertura>();
		
		Ramo ramoObj = em.find(Ramo.class, Integer.valueOf(ramo));
		Producto productoObj = em.find(Producto.class, producto);

		Query query = em.createNamedQuery("PlanCobertura.findByRamoyProducto");
		query.setParameter("ramo", ramoObj);
		query.setParameter("producto", productoObj);

		List<PlanCobertura> result = query.getResultList();

		if (result != null && result.size() > 0) {
			for (int i = 0; i < result.size(); i++) {
				PlanCobertura pc = (PlanCobertura)result.get(i);
				lista.add(pc);
			}
		}
		return lista;
	}

	@Override
	public PlanCobertura consultaPlanCobertura(EntityManager em, String plan) throws Exception, BSEException {
		try {
			PlanCobertura planObj = em.find(PlanCobertura.class, plan);
			return planObj;
		} catch (Exception e) {
			e.printStackTrace();
			throw new BSEException(CodigosError.plan_cobertura_invalido);
		}
	}

	public PlanCobertura consultaPlanCoberturaRamoProducto(EntityManager em, int ramo, String producto, String plan) throws Exception, BSEException{
		Ramo ramoObj = em.find(Ramo.class, Integer.valueOf(ramo));
		Producto productoObj = em.find(Producto.class, producto);

		Query query = em.createNamedQuery("PlanCobertura.findByRamoProductoPlan");
		query.setParameter("ramo", ramoObj);
		query.setParameter("producto", productoObj);
		query.setParameter("plan", plan);

		List<PlanCobertura> result = query.getResultList();

		if (result != null && result.size() > 0) {
			PlanCobertura pc = (PlanCobertura)result.get(0);
			return pc;
		}
		return null;
	}

	public PlanCobertura consultaPlanCoberturaRamo(EntityManager em, int ramo, String plan) throws Exception, BSEException{
		Ramo ramoObj = em.find(Ramo.class, Integer.valueOf(ramo));
		//Producto productoObj = em.find(Producto.class, producto);

		Query query = em.createNamedQuery("PlanCobertura.findByRamoPlan");
		query.setParameter("ramo", ramoObj);
		query.setParameter("plan", plan);

		List<PlanCobertura> result = query.getResultList();

		if (result != null && result.size() > 0) {
			PlanCobertura pc = (PlanCobertura)result.get(0);
			return pc;
		}
		return null;
	}

	@Override
	public PlanPago consultaPlanPago(EntityManager em, int plan) throws Exception, BSEException {
		try {
			PlanPago planObj = em.find(PlanPago.class, Integer.valueOf(plan));
			return planObj;
		} catch (Exception e) {
			throw new BSEException(CodigosError.plan_pago_invalido, e);
		}
	}

	@Override
	public Moneda consultaMoneda(EntityManager em, String codigoMoneda) throws Exception, BSEException {
		try {
			Moneda monedaObj = em.find(Moneda.class, codigoMoneda);
			return monedaObj;
		} catch (Exception e) {
			throw new BSEException(CodigosError.plan_pago_invalido, e);
		}
	}
		
	@Override
	public List<PlanPago> consultaPlanesPago(EntityManager em) throws Exception, BSEException {
		ArrayList<PlanPago> lista = new ArrayList<PlanPago>();

		String planesAutorizados = Codigos.getCodigos().getPlanesPagoSoa(em);
		String[] planesAutorizadosVec = planesAutorizados.split(",");
		
		Query query = em.createNamedQuery("PlanPago.findAll");

		List<PlanPago> result = query.getResultList();

		if (result != null && result.size() > 0) {
			for (int i = 0; i < result.size(); i++) {
				PlanPago pc = (PlanPago)result.get(i);

				boolean autorizado = false;
				for (int x = 0; x < planesAutorizadosVec.length; x++){
					if (pc.getCodigo() == Integer.parseInt(planesAutorizadosVec[x])){
						autorizado = true;
						x = planesAutorizadosVec.length;
					}
				}
					
				if (autorizado){
					lista.add(pc);
				}					
			}
		}
		
		return lista;
	}

	@Override
	public void alertarPagoRedes(EntityManager em, int sucursal, int ramo, String producto, long nroCotizacion) throws Exception, BSEException {
		try {
			if (sucursal == 0 || ramo == 0 || producto == null || producto.trim().equals("") || nroCotizacion == 0)
				throw new BSEException(CodigosError.datos_de_consulta_vacios);

			PolizaPortalPK pk = new PolizaPortalPK();
			pk.setSucursal(sucursal);
			pk.setRamo(ramo);
			pk.setProducto(producto);
			pk.setNroCotizacion(nroCotizacion);
			PolizaPortal polizaPortalObj = em.find(PolizaPortal.class, pk);
			
			if (polizaPortalObj.getEstado() != PolizaPortal.ESTADO_POLIZA_EMITIDA)
				throw new BSEException(CodigosError.cotizacion_invalida);
			
			polizaPortalObj.setFechaMod(new Date());
			polizaPortalObj.setPagoRedes("S");
			em.merge(polizaPortalObj);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BSEException(CodigosError.cotizacion_invalida);
		}
	}

}
