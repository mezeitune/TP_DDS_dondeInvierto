package Condiciones.Predefinidas;

import java.util.LinkedList;
import java.util.List;

import Condiciones.Condicion;
import Condiciones.TipoCondicion;
import usuario.Empresa;
import usuario.Indicador;

public class MargenesCrecientes extends Condicion {

	
	public MargenesCrecientes(TipoCondicion tipo, Indicador indicador, int peso) {
		super(tipo, indicador, peso);
	}
	
	@Override
	public List<Empresa> evaluar(List<Empresa> listaAEvaluar,List<String> _periodos){
		List<String> periodos = new LinkedList<String>();
		periodos.add("2007");
		periodos.add("2008");
		periodos.add("2009");
		periodos.add("2010");
		periodos.add("2011");
		periodos.add("2012");
		periodos.add("2013");
		periodos.add("2014");
		periodos.add("2015");
		periodos.add("2016");
		periodos.add("2017");
		return this.tipo.evaluar(listaAEvaluar,periodos,this);
	}

}
