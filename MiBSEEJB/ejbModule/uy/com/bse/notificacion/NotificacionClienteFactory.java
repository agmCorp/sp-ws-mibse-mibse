package uy.com.bse.notificacion;

import uy.com.bse.mibse.ClientePagoBici;
import uy.com.bse.mibse.ClientePagoEDeportivas;
import uy.com.bse.mibse.ClientePagoIndivi;
import uy.com.bse.mibse.ClientePagoOPersonal;
import uy.com.bse.mibse.ClientePagoSOA;
import uy.com.bse.mibse.ClientePagoViajero;
import uy.com.bse.mibse.ParamInformarPagoTienda;
import uy.com.bse.mibse.ResultClientePago;
import uy.com.bse.mibse.persistencia.ServiciosMiBsePersist;
import uy.com.bse.notificacion.excepciones.NotificacionException;

public class NotificacionClienteFactory {
	private static final int COD_RAMO_SOA = 4;
	private static final String COD_PRODUCTO_SOA = "SOA";
	private static final int COD_RAMO_VIAJERO = 12;
	private static final String COD_PRODUCTO_VIAJERO = "VIAJPL";
	private static final int COD_RAMO_EDEPORTIVAS = 5;
	private static final String COD_PRODUCTO_EDEPORTIVAS = "RCYATE";
	private static final int COD_RAMO_BICI = 7;
	private static final String COD_PRODUCTO_BICI = "RD20";
	private static final int COD_RAMO_INDIVI = 4;
	private static final String COD_PRODUCTO_INDIVI = "INDIVI";
	private static final int COD_RAMO_OPERSONAL = 7;
	private static final String COD_PRODUCTO_OPERSONAL = "RD04";
		
	public static INotificacionCliente obtenerNotificacionCliente(ParamInformarPagoTienda param) throws NotificacionException {
		INotificacionCliente notificacionCliente = null;
		ServiciosMiBsePersist serviciosMiBsePersist = new ServiciosMiBsePersist();

		// Condición factory
		if (param.getCodRamo() == COD_RAMO_SOA && param.getCodProducto().equals(COD_PRODUCTO_SOA)) {
			notificacionCliente = obtenerNotificacionClienteSOA(serviciosMiBsePersist, param);
		} else if (param.getCodRamo() == COD_RAMO_VIAJERO && param.getCodProducto().equals(COD_PRODUCTO_VIAJERO)) {
			notificacionCliente = obtenerNotificacionClienteViajero(serviciosMiBsePersist, param);
		} else if (param.getCodRamo() == COD_RAMO_EDEPORTIVAS && param.getCodProducto().equals(COD_PRODUCTO_EDEPORTIVAS)) {
			notificacionCliente = obtenerNotificacionClienteEDeportivas(serviciosMiBsePersist, param);
		} else if (param.getCodRamo() == COD_RAMO_BICI && param.getCodProducto().equals(COD_PRODUCTO_BICI)) {
			notificacionCliente = obtenerNotificacionClienteBici(serviciosMiBsePersist, param);
		} else if (param.getCodRamo() == COD_RAMO_INDIVI && param.getCodProducto().equals(COD_PRODUCTO_INDIVI)) {
			notificacionCliente = obtenerNotificacionClienteIndivi(serviciosMiBsePersist, param);
		} else if (param.getCodRamo() == COD_RAMO_OPERSONAL && param.getCodProducto().equals(COD_PRODUCTO_OPERSONAL)) {
			notificacionCliente = obtenerNotificacionClienteOPersonal(serviciosMiBsePersist, param);
		} else {
			throw new NotificacionException("No se puede construir INotificacionCliente");
		}
		return notificacionCliente;
	}
	
	private static NotificacionClienteSOA obtenerNotificacionClienteSOA(ServiciosMiBsePersist serviciosMiBsePersist, ParamInformarPagoTienda param) {
		NotificacionClienteSOA notificacionClienteSOA = null;
		ResultClientePago result = serviciosMiBsePersist.obtenerClientePagoSOA(param);
		if (!result.getHayError()) {
			ClientePagoSOA clientePagoSOA = (ClientePagoSOA) result.getClientePago();
			if (clientePagoSOA != null) {
				notificacionClienteSOA = new NotificacionClienteSOA(clientePagoSOA);
			}
		}
		return notificacionClienteSOA;
	}

	private static NotificacionClienteViajero obtenerNotificacionClienteViajero(ServiciosMiBsePersist serviciosMiBsePersist, ParamInformarPagoTienda param) {
		NotificacionClienteViajero notificacionClienteViajero = null;
		ResultClientePago result = serviciosMiBsePersist.obtenerClientePagoViajero(param);
		if (!result.getHayError()) {
			ClientePagoViajero clientePagoViajero = (ClientePagoViajero) result.getClientePago();
			if (clientePagoViajero != null) {
				notificacionClienteViajero = new NotificacionClienteViajero(clientePagoViajero);
			}
		}
		return notificacionClienteViajero;
	}
	
	private static NotificacionClienteEDeportivas obtenerNotificacionClienteEDeportivas(ServiciosMiBsePersist serviciosMiBsePersist, ParamInformarPagoTienda param) {
		NotificacionClienteEDeportivas notificacionClienteEDeportivas = null;
		ResultClientePago result = serviciosMiBsePersist.obtenerClientePagoEDeportivas(param);
		if (!result.getHayError()) {
			ClientePagoEDeportivas clientePagoEDeportivas = (ClientePagoEDeportivas) result.getClientePago();
			if (clientePagoEDeportivas != null) {
				notificacionClienteEDeportivas = new NotificacionClienteEDeportivas(clientePagoEDeportivas);
			}
		}
		return notificacionClienteEDeportivas;
	}
	
	private static NotificacionClienteBici obtenerNotificacionClienteBici(ServiciosMiBsePersist serviciosMiBsePersist, ParamInformarPagoTienda param) {
		NotificacionClienteBici notificacionClienteBici = null;
		ResultClientePago result = serviciosMiBsePersist.obtenerClientePagoBici(param);
		if (!result.getHayError()) {
			ClientePagoBici clientePagoBici = (ClientePagoBici) result.getClientePago();
			if (clientePagoBici != null) {
				notificacionClienteBici = new NotificacionClienteBici(clientePagoBici);
			}
		}
		return notificacionClienteBici;
	}
	
	private static NotificacionClienteIndivi obtenerNotificacionClienteIndivi(ServiciosMiBsePersist serviciosMiBsePersist, ParamInformarPagoTienda param) {
		NotificacionClienteIndivi notificacionClienteIndivi = null;
		ResultClientePago result = serviciosMiBsePersist.obtenerClientePagoIndivi(param);
		if (!result.getHayError()) {
			ClientePagoIndivi clientePagoIndivi = (ClientePagoIndivi) result.getClientePago();
			if (clientePagoIndivi != null) {
				notificacionClienteIndivi = new NotificacionClienteIndivi(clientePagoIndivi);
			}
		}
		return notificacionClienteIndivi;
	}
	
	private static NotificacionClienteOPersonal obtenerNotificacionClienteOPersonal(ServiciosMiBsePersist serviciosMiBsePersist, ParamInformarPagoTienda param) {
		NotificacionClienteOPersonal notificacionClienteOPersonal = null;
		ResultClientePago result = serviciosMiBsePersist.obtenerClientePagoOPersonal(param);
		if (!result.getHayError()) {
			ClientePagoOPersonal clientePagoOPersonal = (ClientePagoOPersonal) result.getClientePago();
			if (clientePagoOPersonal != null) {
				notificacionClienteOPersonal = new NotificacionClienteOPersonal(clientePagoOPersonal);
			}
		}
		return notificacionClienteOPersonal;
	}
	
}
