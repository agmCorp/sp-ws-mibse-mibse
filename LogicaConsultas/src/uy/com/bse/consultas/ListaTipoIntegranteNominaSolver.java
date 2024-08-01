package uy.com.bse.consultas;


import uy.com.bse.consultas.operaciones.ParamTipoIntegranteNomina;
import uy.com.bse.consultas.persistencia.ConsultasPersist;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultCodiguera;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ListaTipoIntegranteNominaSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultCodiguera();
	}
	
	@Override
	protected ResultGenerico procesoLogica(ParamGenerico param){
		return listaTipoIntegranteNomina((ParamTipoIntegranteNomina) param);
	}

	private ResultCodiguera  listaTipoIntegranteNomina(ParamTipoIntegranteNomina param) {
		return (ResultCodiguera ) checkNull(new ConsultasPersist().listaTipoIntegranteNomina(param));
	}
}
