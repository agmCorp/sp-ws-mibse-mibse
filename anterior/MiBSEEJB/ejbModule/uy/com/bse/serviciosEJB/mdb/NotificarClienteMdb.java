package uy.com.bse.serviciosEJB.mdb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import uy.com.bse.mibse.ParamInformarPagoTienda;
import uy.com.bse.notificacion.INotificacionCliente;
import uy.com.bse.notificacion.NotificacionClienteFactory;
import uy.com.bse.utilitario.dato.ResultGenerico;

@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "connectionFactoryLookup", propertyValue = "java:/JmsXA"),
		@ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:/jms/queue/TiendaInformarPagoBancario"),
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue") })
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class NotificarClienteMdb implements MessageListener {

	private static Logger log = LogManager.getLogger(NotificarClienteMdb.class);

	@Override
	public void onMessage(Message message) {
		ParamInformarPagoTienda paramInformarPagoTienda = null;
		String msgErr = "Error en el método onMessage";

		try {
			if (message instanceof ObjectMessage) {
				paramInformarPagoTienda = (ParamInformarPagoTienda) ((ObjectMessage) message).getObject();
				String idTransaccion = paramInformarPagoTienda.getIdTransaccion();
				msgErr = "Error en método onMessage idTransaccion: " + idTransaccion;

				INotificacionCliente notificacionCliente = NotificacionClienteFactory.obtenerNotificacionCliente(paramInformarPagoTienda);
				ResultGenerico result = notificacionCliente.notificar();
				if (result.getHayError()) {
					log.error(msgErr);
					log.error(result.getError().getDescripcion());
				} else {
					log.info("Se ha notificado idTransaccion: " + idTransaccion);
				}
			}
		} catch (Exception e) {
			log.error(msgErr, e);
		}
	}
}
