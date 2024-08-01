package uy.bse.rector.servicios.parseo;

import java.util.ArrayList;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import uy.com.bse.usuarios.entidades.UsuarioConsultaAuditoriaDetalle;
import uy.com.bse.usuarios.operaciones.ResultConsultaAuditoriaDetalle;
import uy.com.bse.utilitario.log.Logueo;
import uy.com.bse.utilitario.parseo.ParseoXmlGenerico;

public class ParseoConsultaAuditoriaDetalle extends ParseoXmlGenerico {

	public ParseoConsultaAuditoriaDetalle(String textoParsear) {
		super(textoParsear);
	}

	private UsuarioConsultaAuditoriaDetalle procesarUsuario(Element item) {
		UsuarioConsultaAuditoriaDetalle usuario = new UsuarioConsultaAuditoriaDetalle();

		usuario.setPrograma(getParsedValueFromElement(item, "cd-programa"));
		usuario.setUsuarioEmpleado(getParsedValueFromElement(item, "cd-caup-usuario"));
		usuario.setFechaLog(getParsedValueFromElement(item, "fe-log"));
		usuario.setTipoDeAccion(getParsedValueFromElement(item, "tp-accion"));
		usuario.setRamo(getParsedValueFromElement(item, "cd-ramo"));
		usuario.setPoliza(getParsedValueFromElement(item, "nu-poliza"));
		usuario.setCertificado(getParsedValueFromElement(item, "nu-certificado"));
		usuario.setEndoso(getParsedValueFromElement(item, "nu-endoso"));
		usuario.setPersona(getParsedValueFromElement(item, "nu-persona"));
		usuario.setDatosAmpliados(getParsedValueFromElement(item, "dt-ampliados"));
		usuario.setCotizacion(getParsedValueFromElement(item, "nu-cotizacion"));
		usuario.setConsecutivo(getParsedValueFromElement(item, "nu-consecutivo"));
		usuario.setUsuarioProductor(getParsedValueFromElement(item, "cd-caus_usuario"));
		usuario.setAlcance(getParsedValueFromElement(item, "cd-departamento"));

		return usuario;
	}

	public ResultConsultaAuditoriaDetalle parsearConsultaAuditoriaDetalle() {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(ParseoConsultaAuditoriaDetalle.ENCABEZADO);
		logueo.setClase(ParseoConsultaAuditoriaDetalle.class);
		logueo.setMetodo("parsearConsultaAuditoriaDetalle");

		ResultConsultaAuditoriaDetalle resultado = new ResultConsultaAuditoriaDetalle();

		try {
			Element item;
			ArrayList<UsuarioConsultaAuditoriaDetalle> usuarios = new ArrayList<UsuarioConsultaAuditoriaDetalle>();
			UsuarioConsultaAuditoriaDetalle usuario;
			NodeList elementos = this.doc.getElementsByTagName("usuario");
			for (int i = 0; i < elementos.getLength(); i++) {
				item = (Element) elementos.item(i);
				usuario = procesarUsuario(item);
				usuarios.add(usuario);
			}
			resultado.setUsuarios(usuarios);

		} catch (NumberFormatException ex) {
			claveError = catchNumberFormatException(logueo, ex);
		} catch (Exception ex) {
			claveError = catchException(logueo, ex);
		} finally {
			finallyBlock(claveError, resultado);
		}

		return resultado;
	}
}
