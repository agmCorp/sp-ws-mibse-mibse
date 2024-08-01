package uy.com.bse.mibse;

import javax.jms.DeliveryMode;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.naming.InitialContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.dato.ServiciosError;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class InformarPagoRedesSolver extends AbstractSolver {
	
	private static Logger log = LogManager.getLogger(InformarPagoRedesSolver.class);
	
	@Override
	public ResultGenerico procesoLogica(ParamGenerico param) {
		ResultInformarPagoRedes result = new ResultInformarPagoRedes();
		ParamInformarPagoRedes pagoRedes = (ParamInformarPagoRedes) param;
		QueueConnection connection = null;
		
		try {
			InitialContext ctx = new InitialContext();
			QueueConnectionFactory queueConnectionFactory = (QueueConnectionFactory) ctx.lookup("java:/JmsXA");
			connection = queueConnectionFactory.createQueueConnection();
			connection.start();

			QueueSession session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			Queue tiendaInfPagRedes = (Queue) ctx.lookup("java:/jms/queue/TiendaInformarPagoBancario");
			QueueSender sender = session.createSender(tiendaInfPagRedes);
			sender.setDeliveryMode(DeliveryMode.PERSISTENT);

			ObjectMessage msg = session.createObjectMessage();
			msg.setObject(param);
			sender.send(msg);

			connection.close();
			
			log.debug("Se agrega a la cola 'TiendaInformarPagoBancario' idTransaccion: " + pagoRedes.getIdTransaccion());
		} catch (Exception e) {
			String msgErr = "Error al agregar a la cola 'TiendaInformarPagoBancario' idTransaccion: " + pagoRedes.getIdTransaccion();
			result.setError(new ServiciosError(msgErr));
			result.setHayError(Boolean.TRUE);
			log.error(msgErr);
			log.error(e);
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (Exception e) {
					log.error(e);
				}
			}
		}
		
		return result;
	}

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultInformarPagoRedes();
	}
}
