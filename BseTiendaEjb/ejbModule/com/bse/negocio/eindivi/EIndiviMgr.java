package com.bse.negocio.eindivi;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jws.WebParam;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.bse.accesodatos.comun.CretTablas;
import com.bse.accesodatos.eindivi.AreaCirculacion;
import com.bse.accesodatos.eindivi.CotizacionIndivi;
import com.bse.accesodatos.eindivi.Modalidad;
import com.bse.accesodatos.eindivi.ModeloVehiculo;
import com.bse.accesodatos.eindivi.PolizaIndivi;
import com.bse.accesodatos.eindivi.TipoCombustible;
import com.bse.accesodatos.eindivi.TipoContratante;
import com.bse.accesodatos.eindivi.TipoVehiculo;
import com.bse.accesodatos.esoa.CuotaPago;
import com.bse.accesodatos.esoa.MarcaVehiculo;
import com.bse.accesodatos.esoa.Moneda;
import com.bse.accesodatos.esoa.PlanCobertura;
import com.bse.accesodatos.esoa.PlanPago;
import com.bse.accesodatos.esoa.Producto;
import com.bse.accesodatos.esoa.Ramo;
import com.bse.negocio.FabricaNegocio;
import com.bse.negocio.comun.BSEException;
import com.bse.negocio.comun.Codigos;
import com.bse.negocio.comun.CodigosError;
import com.bse.negocio.esoa.IESoa;


public class EIndiviMgr implements IEIndivi{

	public EIndiviMgr() {
    }
	
    public static IEIndivi getEIndiviMgr() {
        return new EIndiviMgr();
    }


	@Override
	public CotizacionIndivi cotizarIndiviAnonimo(EntityManager em, String planCobertura, String marcaVehiculo, String modeloVehiculo, String anioVehiculo, String tipoVehiculo, 
	        String combustible, String modalidad, String areaCirculacion, int planPago) throws Exception, BSEException {
		System.out.println("COTINDIVI 1.1 entrando a cotizar -------");
//		System.out.println(":::planCobertura:::"+planCobertura);

		if (marcaVehiculo == null || marcaVehiculo.equals(""))
			throw new BSEException(CodigosError.marca_vehiculo_invalida);
		if (anioVehiculo == null || anioVehiculo.equals(""))
			throw new BSEException(CodigosError.anio_vehiculo_invalido);
		if (tipoVehiculo == null || tipoVehiculo.equals(""))
			throw new BSEException(CodigosError.tipo_vehiculo_invalido);
		if (planCobertura == null || planCobertura.equals(""))
			throw new BSEException(CodigosError.plan_cobertura_invalido);
		
		String planesPago = Codigos.getCodigos().getPlanesPagoSoa(em);
		
		System.out.println("COTINDIVI 1.2");
		
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
		
		System.out.println("COTINDIVI 1.3");

		int sucursal = Integer.parseInt(Codigos.getCodigos().getSucursalEmisionIndivi(em));
		int ramo = Integer.parseInt(Codigos.getCodigos().getRamoEmisionIndivi(em));
		String producto = Codigos.getCodigos().getProductoEmisionIndivi(em);
		String productor = Codigos.getCodigos().getProductorEmisionIndivi(em);
		String moneda = Codigos.getCodigos().getMonedaEmisionIndivi(em);
    	String matriculaVehiculo = "AAA1000";
		String tipoDocumento = " ";
		String documento = " ";
		Date fechaDesde = new Date();
		String padronVehiculo = "0";
		String motorVehiculo = "0";
		String chasisVehiculo = "";

		String tipoCalculo = Codigos.getCodigos().getTipoCalculoEmisionIndivi(em).trim();
		String formaPago = Codigos.getCodigos().getFormaPagoEmisionIndivi(em).trim();
		String vigTecnica = Codigos.getCodigos().getVigTecnicaEmisionIndivi(em).trim();
		String medioPago = Codigos.getCodigos().getMedioPagoEmisionIndivi(em).trim();
		String origenPago = Codigos.getCodigos().getOrigenPagoEmisionIndivi(em).trim();
		String tipoFact = Codigos.getCodigos().getTipoFactEmisionIndivi(em).trim();
		String promocion = Codigos.getCodigos().getPromocionEmisionIndivi(em).trim();
		String renovacion = Codigos.getCodigos().getRenovacionEmisionIndivi(em).trim();
		String usuarioWeb = Codigos.getCodigos().getUsuarioWebEmisionIndivi(em).trim();

		System.out.println("COTINDIVI 1.4");

		Query q = em.createNamedQuery("PRO_COTIZAR_INDIVI");
		q.setParameter("P_TP_DOCUMENTO", tipoDocumento);
		q.setParameter("P_NRO_DOCUMENTO", documento);
		q.setParameter("P_SUCURSAL", sucursal);
		q.setParameter("P_RAMO", String.valueOf(ramo));
		q.setParameter("P_PRODUCTO", producto);
		q.setParameter("P_PRODUCTOR", productor);
		q.setParameter("P_FECHA_DESDE", fechaDesde);
		q.setParameter("P_MARCA_VEHICULO", marcaVehiculo);
		q.setParameter("P_ANIO_VEHICULO", anioVehiculo);
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
		
		q.setParameter("P_TIPO_VEHICULO", tipoVehiculo);
		q.setParameter("P_COMBUSTIBLE", combustible);
		q.setParameter("P_MODALIDAD", modalidad);
		q.setParameter("P_AREA_CIRCULACION", areaCirculacion);
		q.setParameter("P_MODELO_VEHICULO", modeloVehiculo);
				
		List<Object[]> l = q.getResultList();
		Object[] r = l.get(0);

		for (int i = 0; i < r.length; i++){
			if (r[i] != null)
				System.out.println("COTINDIVI R" + i + "=" + r[i].toString());
			else
				System.out.println("COTINDIVI R" + i + "=null");
		}

		if (r[6] != null && !((String)r[6]).equals("0")){
			BSEException exc = new BSEException(CodigosError.error_cotizacion_rector, "Error en Cotizacion Rector " + r[6] + " - " + (String)r[7]);
			System.out.println("----------------------------ERRORPORTAL----------------------------");
			System.out.println("Error al cotizar RECTOR " + r[6] + " - " + (String)r[7]);
			System.out.println("-------------------------------------------------------------------");
			
			throw exc;
		}
			
		//System.out.println("cotizar 3-------");
		CotizacionIndivi cotizacion = new CotizacionIndivi();

		Ramo ramoObj = em.find(Ramo.class, Integer.valueOf(ramo));
		cotizacion.setRamo(ramoObj);
		
		Producto productoObj = em.find(Producto.class, producto);
		cotizacion.setProducto(productoObj);
		
		cotizacion.setSucursal(sucursal);
		
		cotizacion.setNroCotizacion(0);
		if (r[0] != null)
			cotizacion.setNroCotizacion(((Integer)r[0]).intValue());
		
		cotizacion.setTipoDocumento("");
		cotizacion.setNroDocumento("");
		cotizacion.setProductor(1);
		
		IESoa eSoaManager = FabricaNegocio.getFabricaNegocio().getESoaMgr();

		Moneda monedaObj = eSoaManager.consultaMoneda(em, moneda);
		cotizacion.setMoneda(monedaObj);

		cotizacion.setPremio(0);
		if (r[1] != null)
			cotizacion.setPremio(((Double)r[1]).doubleValue());

		cotizacion.setPremioFacturar(0);
		if (r[2] != null)
			cotizacion.setPremioFacturar(((Double)r[2]).doubleValue());
		
		cotizacion.setFechaDesde(fechaDesde);
		cotizacion.setFechaHasta((Date)r[3]);
		
		PlanPago planPagoObj = eSoaManager.consultaPlanPago(em, planPago);
		planPagoObj.setCodigo(planPago);
		cotizacion.setPlanPago(planPagoObj);
		
		MarcaVehiculo marca = eSoaManager.consultaMarcaVehiculo(em, marcaVehiculo);
		cotizacion.setMarcaVehiculo(marca);
		
//		CategoriaVehiculo categoria = consultaCategoriaVehiculo(em, categoriaVehiculo);
//		categoria.setCodigo(categoriaVehiculo);
//		cotizacion.setCategoriaVehiculo(categoria);
		
		PlanCobertura planCoberturaObj = eSoaManager.consultaPlanCoberturaRamoProducto(em, ramo, producto, planCobertura);
		planCoberturaObj.setPlan(planCobertura);
		cotizacion.setPlanCobertura(planCoberturaObj);
		
		cotizacion.setMatriculaVehiculo(matriculaVehiculo);
		cotizacion.setPadronVehiculo(padronVehiculo);
		cotizacion.setChasisVehiculo(chasisVehiculo);
		cotizacion.setMotorVehiculo(motorVehiculo);
		cotizacion.setAnioVehiculo(anioVehiculo);

		String cuotasStr = (String)r[4]; 
		String[] vecCuotas = cuotasStr.split(";");
		
		System.out.println("COTINDIVI 1.5");

		ArrayList<CuotaPago> cuotas = new ArrayList<CuotaPago>();
		for(int i = 0; i < vecCuotas.length; i++){
			CuotaPago cuota = new CuotaPago();
			cuota.setNroCuota(Integer.parseInt(vecCuotas[i].split(":")[0]));
			cuota.setImporte(Double.parseDouble(vecCuotas[i].split(":")[1]));
			cuotas.add(cuota);
		}
		
		cotizacion.setCuotas(cuotas);

		System.out.println("COTINDIVI 1.6");
		return cotizacion;
	}

	@Override
	public PolizaIndivi emitirIndivi(EntityManager em, String tipoDocumento, String documento, String matriculaVehiculo, String padronVehiculo,
				String motorVehiculo, String chasisVehiculo, String calidadContratante, 
				long nroCotizacion, String consumoFinal) throws Exception, BSEException{

		System.out.println("EMIINDIVI 1.1");

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

		int sucursal = Integer.parseInt(Codigos.getCodigos().getSucursalEmisionIndivi(em));
		int ramo = Integer.parseInt(Codigos.getCodigos().getRamoEmisionIndivi(em));
		String producto = Codigos.getCodigos().getProductoEmisionIndivi(em);
		String productor = Codigos.getCodigos().getProductorEmisionIndivi(em);
		String moneda = Codigos.getCodigos().getMonedaEmisionIndivi(em);

		String sqlCot = "select * " +
			"from cart_cotiza_banco " +
			"where cazb_capj_cd_sucursal = ? and cazb_nu_cotizacion = ? and " +
				"cazb_nu_consecutivo = 0 and cazb_carp_cd_ramo = ? and cazb_capu_cd_producto = ?";
	
		Query queryCot = em.createNativeQuery(sqlCot);
		queryCot.setParameter(1, sucursal);
		queryCot.setParameter(2, nroCotizacion);
		queryCot.setParameter(3, ramo);
		queryCot.setParameter(4, producto);
		List<Object[]> resultCot = queryCot.getResultList();

		System.out.println("EMIINDIVI 1.2");

		if (resultCot == null || resultCot.size() == 0)
			throw new BSEException(CodigosError.cotizacion_invalida);

		Query q = em.createNamedQuery("PRO_EMITIR_INDIVI");
		q.setParameter("P_TP_DOCUMENTO", tipoDocumento);
		q.setParameter("P_NRO_DOCUMENTO", documento);
		q.setParameter("P_SUCURSAL", String.valueOf(sucursal));
		q.setParameter("P_RAMO", String.valueOf(ramo));
		q.setParameter("P_PRODUCTO", producto);
		q.setParameter("P_MATRICULA_VEHICULO", matriculaVehiculo);
		q.setParameter("P_PADRON_VEHICULO", padronVehiculo);
		q.setParameter("P_MOTOR_VEHICULO", motorVehiculo);
		q.setParameter("P_CHASIS_VEHICULO", chasisVehiculo);
		q.setParameter("P_COTIZACION_EMITIDA", nroCotizacion);
		q.setParameter("P_CONSUMO_FINAL", consumoFinal);
		q.setParameter("P_CALIDAD_CONTRATANTE", calidadContratante);

		List<Object[]> l = q.getResultList();
		Object[] r = l.get(0);

		for (int i = 0; i < r.length; i++){
			if (r[i] != null)
				System.out.println("EMIINDIVI R" + i + "=" + r[i].toString());
			else
				System.out.println("EMIINDIVI R" + i + "=null");
		}

		if (r[10] != null){
			BSEException exc = new BSEException(CodigosError.error_emision_rector, "Error en Cotizacion Rector " + r[10] + " - " + (String)r[11]);
			System.out.println("----------------------------ERRORPORTAL----------------------------");
			System.out.println("Error al emitir RECTOR Codigo " + r[10] + " Mensaje " + (String)r[11]);
			System.out.println("-------------------------------------------------------------------");
			throw exc;
		}

		System.out.println("EMIINDIVI 1.3");

		double premio = ((Double)r[1]).doubleValue();
		double premioFacturar = ((Double)r[2]).doubleValue();
		Date fechaDesde = (Date)r[6];
		Date fechaHasta = (Date)r[7];
		String marcaVehiculo = (String)r[3];
		String categoriaVehiculo = (String)r[5];
		String anioVehiculo = (String)r[4];
		int nroPoliza = ((Integer)r[0]).intValue();
		
		String planCobertura = (String)r[8]; 
	    int planPago = ((Integer)r[9]).intValue();
		String cuotas = (String)r[10]; 
	    
		PolizaIndivi poliza = new PolizaIndivi();

		Ramo ramoObj = em.find(Ramo.class, Integer.valueOf(ramo));
		poliza.setRamo(ramoObj);
		
		Producto productoObj = em.find(Producto.class, producto);
		poliza.setProducto(productoObj);
		
		poliza.setSucursal(200);
		poliza.setNroCotizacion(nroCotizacion);
		poliza.setTipoDocumento(tipoDocumento);
		poliza.setNroDocumento(documento);
		poliza.setProductor(Integer.valueOf(productor));
/*
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
*/		
		poliza.setMatriculaVehiculo(matriculaVehiculo);
		poliza.setPadronVehiculo(padronVehiculo);
		poliza.setChasisVehiculo(chasisVehiculo);
		poliza.setMotorVehiculo(motorVehiculo);
		poliza.setAnioVehiculo(anioVehiculo);
		poliza.setNroPoliza(nroPoliza);
		
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
		
		poliza.setCuotas(listaCuotas);
		
		System.out.println("EMIINDIVI 1.4");

		return poliza;
	}

	@Override
	public List<TipoCombustible> consultaTiposCombustible(EntityManager em) throws Exception, BSEException {
		ArrayList<TipoCombustible> lista = new ArrayList<TipoCombustible>();
		
		Query query = em.createNamedQuery("CretTablas.findTabla");
		query.setParameter("codigoTabla", Integer.valueOf("142001"));

		List<CretTablas> result = query.getResultList();

		if (result != null && result.size() > 0) {
			for (int i = 0; i < result.size(); i++) {
				CretTablas tabla = (CretTablas) result.get(i);				
				TipoCombustible tipo = new TipoCombustible(tabla.getDato1(), tabla.getDescripcion());
				lista.add(tipo);
			}
		}
		
		return lista;
	}

	@Override
	public List<TipoVehiculo> consultaTiposVehiculos(EntityManager em) throws Exception, BSEException {
		ArrayList<TipoVehiculo> lista = new ArrayList<TipoVehiculo>();
		
		Query query = em.createNamedQuery("CretTablas.findTabla");
		query.setParameter("codigoTabla", Integer.valueOf("140004"));

		List<CretTablas> result = query.getResultList();

		if (result != null && result.size() > 0) {
			for (int i = 0; i < result.size(); i++) {
				CretTablas tabla = (CretTablas) result.get(i);				
				TipoVehiculo tipo = new TipoVehiculo(tabla.getDato1(), tabla.getDescripcion());
				lista.add(tipo);
			}
		}
		return lista;
	}

	@Override
	public List<TipoContratante> consultaTiposContratante(EntityManager em) throws Exception, BSEException {
		ArrayList<TipoContratante> lista = new ArrayList<TipoContratante>();
		
		Query query = em.createNamedQuery("CretTablas.findTabla");
		query.setParameter("codigoTabla", Integer.valueOf("990002"));

		List<CretTablas> result = query.getResultList();

		if (result != null && result.size() > 0) {
			for (int i = 0; i < result.size(); i++) {
				CretTablas tabla = (CretTablas) result.get(i);				
				TipoContratante tipo = new TipoContratante(tabla.getDato1(), tabla.getDescripcion());
				lista.add(tipo);
			}
		}
		return lista;
	}

	@Override
	public List<Modalidad> consultaModalidades(EntityManager em) throws Exception, BSEException {
		ArrayList<Modalidad> lista = new ArrayList<Modalidad>();
		
		Query query = em.createNamedQuery("CretTablas.findTabla");
		query.setParameter("codigoTabla", Integer.valueOf("140019"));

		List<CretTablas> result = query.getResultList();

		if (result != null && result.size() > 0) {
			for (int i = 0; i < result.size(); i++) {
				CretTablas tabla = (CretTablas) result.get(i);				
				Modalidad tipo = new Modalidad(tabla.getDato1(), tabla.getDescripcion());
				lista.add(tipo);
			}
		}
		return lista;
	}

	@Override
	public List<AreaCirculacion> consultaAreasCirculacion(EntityManager em) throws Exception, BSEException {
		ArrayList<AreaCirculacion> lista = new ArrayList<AreaCirculacion>();
		
		Query query = em.createNamedQuery("CretTablas.findTabla");
		query.setParameter("codigoTabla", Integer.valueOf("140007"));

		List<CretTablas> result = query.getResultList();

		if (result != null && result.size() > 0) {
			for (int i = 0; i < result.size(); i++) {
				CretTablas tabla = (CretTablas) result.get(i);				
				AreaCirculacion tipo = new AreaCirculacion(tabla.getDato1(), tabla.getDescripcion());
				lista.add(tipo);
			}
		}
		return lista;
	}

	public List<ModeloVehiculo> consultaModelosVehiculos(EntityManager em, String marcaVehiculo) throws Exception, BSEException{
		ArrayList<ModeloVehiculo> lista = new ArrayList<ModeloVehiculo>();
		
		Query query = em.createNamedQuery("CretTablas.findByClave");
		query.setParameter("codigoTabla", Integer.valueOf("140002"));
		query.setParameter("codigoDato", marcaVehiculo);

		List<CretTablas> result = query.getResultList();

		if (result != null && result.size() > 0) {
			for (int i = 0; i < result.size(); i++) {
				CretTablas tabla = (CretTablas) result.get(i);
				if (tabla.getDato1().equals(marcaVehiculo)){
					ModeloVehiculo o = new ModeloVehiculo(tabla.getDato3(), tabla.getDescripcion());
					lista.add(o);
				}
			}
		}
		return lista;
	}


	
}
