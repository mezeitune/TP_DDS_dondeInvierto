package repository;

import java.util.LinkedList;
import java.util.List;

import com.google.gson.Gson;

import Comparadores.Comparador;
import Comparadores.ComparadorMayor;
import Comparadores.ComparadorMayorIgual;
import Comparadores.ComparadorMenor;
import Comparadores.ComparadorMenorIgual;
import Condiciones.Comparativa;
import Condiciones.Condicion;
import Condiciones.Mixta;
import Condiciones.Taxativa;
import Condiciones.TipoCondicion;
import Condiciones.Predefinidas.MargenesCrecientes;
import parser.ParserJsonString;
import parserArchivos.ParserJsonAObjetosJava;

public class CondicionesRepository {

	private static ParserJsonAObjetosJava parserCondiciones = new ParserJsonAObjetosJava("condiciones.json");
	
	public static List<Condicion> getCondiciones() {
		List<Condicion> condiciones = new LinkedList<Condicion>();
		CondicionesRepository.cargarCondiciones(condiciones);
		return condiciones;
	}
	
	private static List<Condicion> getCondicionesPredefinidas() {
		List<Condicion> condicionesPredefinidas = new LinkedList<Condicion>();
		condicionesPredefinidas.add(MargenesCrecientes.getInstance());
		return condicionesPredefinidas;
	}

	private static List<Condicion> getCondicionesDefinidasPorElUsuario() {
		return parserCondiciones.getCondicionesDelArchivo();
	}

	public static void addCondicion(Condicion condicion) {
		String jsonElement = new Gson().toJson(condicion); 
		ParserJsonString.anidadoDeJsonAUnJsonArrayEnUnArchivo("condiciones",jsonElement );
	}
	
	public static void cargarCondiciones(List<Condicion> condiciones){
		CondicionesRepository.getCondicionesDefinidasPorElUsuario().stream().forEach(condicionDefinidaPorUsuario -> condiciones.add(condicionDefinidaPorUsuario));
		CondicionesRepository.getCondicionesPredefinidas().stream().forEach(condicionPredefinida -> condiciones.add(condicionPredefinida));
	}
	
	public static List<TipoCondicion> getTipoCondiciones() {
		List<TipoCondicion> tipoCondiciones = new LinkedList<TipoCondicion>();
		tipoCondiciones.add(new Comparativa());
		tipoCondiciones.add(new Taxativa());
		tipoCondiciones.add(new Mixta());
		return tipoCondiciones;
	}

	public static List<Comparador> getComparadores() {
		List<Comparador> comparadores = new LinkedList<Comparador>();
		comparadores.add(new ComparadorMayor());
		comparadores.add(new ComparadorMayorIgual());
		comparadores.add(new ComparadorMenor());
		comparadores.add(new ComparadorMenorIgual());
		return comparadores;
	}
}
