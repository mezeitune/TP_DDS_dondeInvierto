package Condiciones.Predefinidas;

import java.util.LinkedList;
import java.util.List;

import Condiciones.Condicion;
import Condiciones.TipoCondicion;
import metodologias.Predefinidas.WarrenBuffet;
import usuario.Empresa;
import usuario.Indicador;

public class MargenesCrecientes extends Condicion {

	private static MargenesCrecientes instance ;
	public MargenesCrecientes(String nombre,TipoCondicion tipo, Indicador indicador, int peso) {
		super(nombre,tipo, indicador, peso);
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

	@SuppressWarnings("null")
	public static MargenesCrecientes getInstance() {
		
	        if(instance == null){
	            instance = new MargenesCrecientes(null,null,null,(Integer) null);
	        }
	        return instance;
	
	}

}
