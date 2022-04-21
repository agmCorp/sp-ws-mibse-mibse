package com.bse.negocio.ebicis;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.Query;

import com.bse.accesodatos.comun.CretTablas;
import com.bse.accesodatos.ebici.CotizacionBici;
import com.bse.accesodatos.ebici.PolizaBici;
import com.bse.accesodatos.ebici.TipoBici;
import com.bse.accesodatos.esoa.CuotaPago;
import com.bse.accesodatos.esoa.Moneda;
import com.bse.accesodatos.esoa.PlanCobertura;
import com.bse.accesodatos.esoa.PlanCuota;
import com.bse.accesodatos.esoa.PlanPago;
import com.bse.accesodatos.esoa.Producto;
import com.bse.accesodatos.esoa.Ramo;
import com.bse.negocio.comun.BSEException;
import com.bse.negocio.comun.Codigos;
import com.bse.negocio.comun.CodigosError;


public class EBiciMgr implements IEBici{

	public EBiciMgr() {
    }
	
    public static IEBici getEBiciMgr() {
        return new EBiciMgr();
    }
	
	@Override
	public CotizacionBici cotizarBiciAnonimo(EntityManager em, String planCobertura, Double valorBicicleta, Integer vigenciaSeguro) throws Exception, BSEException {
		System.out.println("COTBICI 1.1 entrando a cotizar -------");
		//System.out.println(":::planCobertura:::"+planCobertura);

		String planesPago = Codigos.getCodigos().getPlanesPagoSoa(em);
		
		//System.out.println("COTSOA 1.2 planCobertura : " + planCobertura);
		
		System.out.println("COTBICI 1.3");

		int sucursal = Integer.parseInt(Codigos.getCodigos().getSucursalEmisionBici(em));
		int ramoBici = Integer.parseInt(Codigos.getCodigos().getRamoEmisionBici(em));
		String productoBici = Codigos.getCodigos().getProductoEmisionBici(em);
		String productor = Codigos.getCodigos().getProductorEmisionBici(em);
		String moneda = Codigos.getCodigos().getMonedaEmisionBici(em);
		String tipoDocumento = " ";
		String documento = " ";
		Date fechaDesde = new Date();

		String tipoCalculo = Codigos.getCodigos().getTipoCalculoEmisionBici(em).trim();
		String formaPago = Codigos.getCodigos().getFormaPagoEmisionBici(em).trim();
		String vigTecnica = Codigos.getCodigos().getVigTecnicaEmisionBici(em).trim();
		String medioPago = Codigos.getCodigos().getMedioPagoEmisionBici(em).trim();
		String origenPago = Codigos.getCodigos().getOrigenPagoEmisionBici(em).trim();
		String tipoFact = Codigos.getCodigos().getTipoFactEmisionBici(em).trim();
		String promocion = Codigos.getCodigos().getPromocionEmisionBici(em).trim();
		String renovacion = Codigos.getCodigos().getRenovacionEmisionBici(em).trim();
		String usuarioWeb = Codigos.getCodigos().getUsuarioWebEmisionBici(em).trim();

		System.out.println("COTBICI 1.4");

		Query q = em.createNamedQuery("PRO_COTIZAR_BICI");
		q.setParameter("P_TP_DOCUMENTO", tipoDocumento);
		q.setParameter("P_NRO_DOCUMENTO", documento);
		q.setParameter("P_SUCURSAL", sucursal);
		q.setParameter("P_RAMO", String.valueOf(ramoBici));
		q.setParameter("P_PRODUCTO", productoBici);
		q.setParameter("P_PRODUCTOR", productor);
		q.setParameter("P_FECHA_DESDE", fechaDesde);
		q.setParameter("P_VALOR_BICI", valorBicicleta);
		q.setParameter("P_VIGENCIA_SEGURO", vigenciaSeguro);
		q.setParameter("P_PLAN_PAGO", new Integer(0));
		q.setParameter("P_PLAN_COBERTURA", planCobertura);
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
				System.out.println("COTBICI R" + i + "=" + r[i].toString());
			else
				System.out.println("COTBICI R" + i + "=null");
		}

		if (r[6] != null && !((String)r[6]).equals("0")){
			BSEException exc = new BSEException(CodigosError.error_cotizacion_rector, (String)r[7]);
			System.out.println("----------------------------ERRORPORTAL----------------------------");
			System.out.println("Error al cotizar RECTOR " + r[6] + " - " + (String)r[7]);
			System.out.println("-------------------------------------------------------------------");
			
			throw exc;
		}

		//System.out.println("cotizar 3-------");
		CotizacionBici cotizacion = new CotizacionBici();

		Ramo ramo = em.find(Ramo.class, Integer.valueOf(ramoBici));
		cotizacion.setRamo(ramo);
		
		Producto productoObj = em.find(Producto.class, productoBici);
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
		
		String cuotasStr = (String)r[4]; 
		String[] vecCuotas = cuotasStr.split(";");
		
		String planesCuotasStr = "";
		if (r[5] != null)
			planesCuotasStr = (String)r[5]; 
		System.out.println("COTBICI 1.5 : " + planesCuotasStr);

		
		// *1;1;4742;1:4742#*2;2;4842;1:2421#2:2421#*3;3;4875;1:1625#2:1625#3:1625#*4;4;4912;1:1228#2:1228#3:1228#4:1228#*5;5;4945;1:989#2:989#3:989#4:989#5:989#*6;6;4980;1:830#2:830#3:830#4:830#5:830#6:830#		

// parte de planes de cuotas, comentado hasta nuevo aviso		

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
		
		cotizacion.setPlanesDeCuotas(planesDeCuotas);

		PlanCobertura planCoberturaObj = null;
		Query query = em.createNamedQuery("PlanCobertura.findByRamoProductoPlan");
		query.setParameter("ramo", ramo);
		query.setParameter("producto", productoObj /*productoBici*/);
		query.setParameter("plan", planCobertura);
		List<PlanCobertura> result = query.getResultList();
		if (result != null && result.size() > 0) {
			planCoberturaObj = (PlanCobertura)result.get(0);
		}
		cotizacion.setPlanCobertura(planCoberturaObj);

		return cotizacion;
	}

	@Override
	public PolizaBici emitirBici(EntityManager em, String planPago, 
			String tipoDocumento,
			String documento, Date fechaFactura, String tipoBicicleta,
			Date fechaNacimientoCliente, String marca, String serie,
			String modelo, long nroCotizacion, String consumoFinal) throws Exception, BSEException{

		//System.out.println("EMISOA 1.1");

		if (tipoDocumento == null || tipoDocumento.equals(""))
			throw new BSEException(CodigosError.tipo_documento_invalido);
		if (documento == null || documento.equals(""))
			throw new BSEException(CodigosError.documento_invalido);
		if (nroCotizacion == 0)
			throw new BSEException(CodigosError.cotizacion_invalida);
		if (!consumoFinal.equals("S") && !consumoFinal.equals("N"))
			throw new BSEException(CodigosError.consumidor_final_invalido);
		if ( (marca == null || marca.equals("") || modelo == null || modelo.equals("") || 
				serie == null || serie.equals("") || tipoBicicleta == null || tipoBicicleta.equals("")) )
			throw new BSEException(CodigosError.faltan_datos_bicicleta);

		Date ahora = new Date();
		/*
		long edadEnDias = (ahora.getTime() - fechaNacimientoCliente.getTime()) / 1000 / 60 / 60 / 24;
		int anos = Double.valueOf(edadEnDias / 365.25d).intValue();
		if (anos < 18 || anos > 65)
			throw new BSEException(CodigosError.edad_invalida);
			*/

		long edadFacturaEnDias = (ahora.getTime() - fechaFactura.getTime()) / 1000 / 60 / 60 / 24;
		int anosFactura = Double.valueOf(edadFacturaEnDias / 365.25d).intValue();
		if (anosFactura > 2 || edadFacturaEnDias < 0)
			throw new BSEException(CodigosError.fecha_factura_invalida);

		int sucursal = Integer.parseInt(Codigos.getCodigos().getSucursalEmisionBici(em));
		int ramo = Integer.parseInt(Codigos.getCodigos().getRamoEmisionBici(em));
		String producto = Codigos.getCodigos().getProductoEmisionBici(em);
		String productor = Codigos.getCodigos().getProductorEmisionBici(em);
		String moneda = Codigos.getCodigos().getMonedaEmisionBici(em);
		
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

		//System.out.println("EMISOA 1.2");

		if (resultCot == null || resultCot.size() == 0)
			throw new BSEException(CodigosError.cotizacion_invalida);
		
//		System.out.println("fechaFactura :: " + fechaFactura + " es " + fechaFactura.getClass().getName());
//		System.out.println("fechaNacimientoCliente :: " + fechaNacimientoCliente + " es " + fechaNacimientoCliente.getClass().getName());
		
		Query q = em.createNamedQuery("PRO_EMITIR_BICI");
		q.setParameter("P_TP_DOCUMENTO", tipoDocumento);
		q.setParameter("P_NRO_DOCUMENTO", documento);
		q.setParameter("P_SUCURSAL", String.valueOf(sucursal));
		q.setParameter("P_RAMO", String.valueOf(ramo));
		q.setParameter("P_PRODUCTO", producto);
		q.setParameter("P_MARCA", marca);
		q.setParameter("P_MODELO", modelo);
		q.setParameter("P_SERIE", serie);
		q.setParameter("P_TIPO_VEHICULO", tipoBicicleta);
		q.setParameter("P_COTIZACION_EMITIDA", Long.valueOf(nroCotizacion));
		q.setParameter("P_CONSUMO_FINAL", consumoFinal);
		q.setParameter("P_PLAN_PAGO", Integer.valueOf(planPago));
		q.setParameter("P_FECHA_FACTURA", fechaFactura);
		q.setParameter("P_FECHA_NAC_CLI", fechaNacimientoCliente);
		
//		@StoredProcedureParameter(queryParameter="P_FECHA_FACTURA",name="P_FECHA_FACTURA",direction=Direction.IN_OUT,type=Date.class),
//		@StoredProcedureParameter(queryParameter="P_FECHA_NACIMIENTO_CLIENTE",name="P_FECHA_NACIMIENTO_CLIENTE",direction=Direction.IN_OUT,type=Date.class),

		List<Object[]> l = q.getResultList();
		Object[] r = l.get(0);

		for (int i = 0; i < r.length; i++){
			if (r[i] != null)
				System.out.println("EMIBICI R" + i + "=" + r[i].toString());
			else
				System.out.println("EMIBICI R" + i + "=null");
		}

		if (r[8] != null){
			BSEException exc = new BSEException(CodigosError.error_emision_rector, (String)r[9]);
			System.out.println("----------------------------ERRORPORTAL----------------------------");
			System.out.println("Error al emitir RECTOR Codigo " + r[8] + " Mensaje " + (String)r[9]);
			System.out.println("-------------------------------------------------------------------");
			throw exc;
		}

		//System.out.println("EMISOA 1.3");

		int nroPoliza = ((Integer)r[0]).intValue();
		double premio = ((Double)r[1]).doubleValue();
		double premioFacturar = ((Double)r[2]).doubleValue();
		Date fechaDesde = (Date)r[3];
		Date fechaHasta = (Date)r[4];
		
		String planCobertura = (String)r[5]; 
//	    int planPago2 = ((Integer)r[9]).intValue();
		String cuotas = (String)r[7]; 
	    
		PolizaBici polizaBici = new PolizaBici();

		Ramo ramoObj = em.find(Ramo.class, Integer.valueOf(ramo));
		polizaBici.setRamo(ramoObj);
		
		Producto productoObj = em.find(Producto.class, producto);
		polizaBici.setProducto(productoObj);
		
		polizaBici.setSucursal(200);
		polizaBici.setNroCotizacion(nroCotizacion);
		polizaBici.setTipoDocumento(tipoDocumento);
		polizaBici.setNroDocumento(documento);
		polizaBici.setProductor(Integer.valueOf(productor));
		
		Moneda monedaObj = consultaMoneda(em, moneda);
		polizaBici.setMoneda(monedaObj);
		
		polizaBici.setPremio(premio);
		polizaBici.setPremioFacturar(premioFacturar);

		polizaBici.setFechaDesde(fechaDesde);
		polizaBici.setFechaHasta(fechaHasta);
		
		PlanCobertura planCoberturaObj = null;
		Query query = em.createNamedQuery("PlanCobertura.findByRamoPlan");
		query.setParameter("ramo", ramoObj);
		query.setParameter("plan", planCobertura);
		List<PlanCobertura> result = query.getResultList();
		if (result != null && result.size() > 0) {
			planCoberturaObj = (PlanCobertura)result.get(0);
		}
		polizaBici.setPlanCobertura(planCoberturaObj);
		
		PlanPago planPagoObj = em.find(PlanPago.class, Integer.valueOf(planPago));
		polizaBici.setPlanPago(planPagoObj);
		
		polizaBici.setNroPoliza(nroPoliza);
		polizaBici.setFechaFactura(fechaFactura);
		polizaBici.setTipoBicicleta(tipoBicicleta);
		polizaBici.setFechaNacimientoCliente(fechaNacimientoCliente);
		polizaBici.setMarca(marca);
		polizaBici.setSerie(serie);
		polizaBici.setModelo(modelo);

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
		
		polizaBici.setCuotas(listaCuotas);
		
		//System.out.println("EMISOA 1.4");

		return polizaBici;
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
	public String clienteConDeuda(EntityManager em, String tipoDocumento, String nroDocumento, Integer nroCotizacion, Integer nroCertificado) throws Exception, BSEException {
		if (tipoDocumento == null || tipoDocumento.equals(""))
			throw new BSEException(CodigosError.tipo_documento_invalido);
		if (nroDocumento == null || nroDocumento.equals(""))
			throw new BSEException(CodigosError.documento_invalido);
		if (nroCotizacion == 0)
			throw new BSEException(CodigosError.cotizacion_invalida);

		Query q = em.createNamedQuery("PRO_CONTROLAR_CLIENTE_DEUDA");
		q.setParameter("P_TP_DOCUMENTO", tipoDocumento);
		q.setParameter("P_NRO_DOCUMENTO", nroDocumento);
		q.setParameter("P_NU_COTIZACION", nroCotizacion);
		q.setParameter("P_NU_CERTIFICADO", nroCertificado);
		
		List<Object[]> l = q.getResultList();
		
		//System.out.println("-------------a ver " + l.get(0));
		//String ret = l.get(0).toString();
		
		Object[] r = l.get(0);

		if (r[4] == null)
			throw new BSEException(CodigosError.no_existe_cliente);

		String noTieneDeuda = r[4].toString();
		
		System.out.println("-------------a ver " + noTieneDeuda);
		
		if (noTieneDeuda.equals("F") || noTieneDeuda.equals("N")){
			return "S";
		}else{
			if (!noTieneDeuda.equals("S"))
				throw new BSEException(CodigosError.error_emision_rector);
		}
		return "N";
	}
	
	@Override
	public List<TipoBici> consultaTiposBicicletas(EntityManager em) throws Exception, BSEException {
		ArrayList<TipoBici> lista = new ArrayList<TipoBici>();
		
		Query query = em.createNamedQuery("CretTablas.findTabla");
		query.setParameter("codigoTabla", Integer.valueOf("170092"));

		List<CretTablas> result = query.getResultList();

		if (result != null && result.size() > 0) {
			for (int i = 0; i < result.size(); i++) {
				CretTablas tabla = (CretTablas) result.get(i);				
				TipoBici tb = new TipoBici(tabla.getDato1(), tabla.getDescripcion());
				lista.add(tb);
			}
		}
		
		return lista;
	}
}
