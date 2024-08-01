package uy.bse.rector.servicios.parseo;

import java.util.ArrayList;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import uy.com.bse.usuarios.entidades.TipoDeAccion;
import uy.com.bse.usuarios.entidades.UsuarioConsultaAuditoria;
import uy.com.bse.usuarios.operaciones.ResultConsultaAuditoria;
import uy.com.bse.utilitario.log.Logueo;
import uy.com.bse.utilitario.parseo.ParseoXmlGenerico;

public class ParseoConsultaAuditoria extends ParseoXmlGenerico {

	public ParseoConsultaAuditoria(String textoParsear) {
		super(textoParsear);
	}

	private TipoDeAccion procesarTipoDeAccion(Element item) {
		TipoDeAccion tipoDeAccion = new TipoDeAccion();
		tipoDeAccion.setNombre(getParsedValueFromElement(item, "tp-accion"));
		tipoDeAccion.setTotal(Integer.valueOf(getParsedValueFromElement(item, "total")));
		return tipoDeAccion;
	}

	private UsuarioConsultaAuditoria procesarUsuario(Element item) {
		UsuarioConsultaAuditoria usuario = new UsuarioConsultaAuditoria();
		usuario.setCedula(getParsedValueFromElement(item, "nu-documento"));
		usuario.setMail(getParsedValueFromElement(item, "desc-mail"));
		usuario.setNombreCompleto(getParsedValueFromElement(item, "desc-nombre"));
		usuario.setTelefono(getParsedValueFromElement(item, "nu-telefono"));
		usuario.setUsuarioProductor(getParsedValueFromElement(item, "cd-productor"));
		usuario.setAlcance(getParsedValueFromElement(item, "cd-departamento"));

		ArrayList<TipoDeAccion> tiposDeAccion = new ArrayList<TipoDeAccion>();
		TipoDeAccion tipoDeAccion;
		Element itemTipoDeAccion;
		NodeList elementos = item.getElementsByTagName("resumen");
		for (int i = 0; i < elementos.getLength(); i++) {
			itemTipoDeAccion = (Element) elementos.item(i);
			tipoDeAccion = procesarTipoDeAccion(itemTipoDeAccion);
			tiposDeAccion.add(tipoDeAccion);
		}
		usuario.setTipoDeAccion(tiposDeAccion);

		return usuario;
	}

	public ResultConsultaAuditoria parsearConsultaAuditoria() {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(ParseoConsultaAuditoria.ENCABEZADO);
		logueo.setClase(ParseoConsultaAuditoria.class);
		logueo.setMetodo("parsearConsultaAuditoria()");

		ResultConsultaAuditoria resultado = new ResultConsultaAuditoria();

		try {
			Element item;
			ArrayList<UsuarioConsultaAuditoria> usuarios = new ArrayList<UsuarioConsultaAuditoria>();
			UsuarioConsultaAuditoria usuario;
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
