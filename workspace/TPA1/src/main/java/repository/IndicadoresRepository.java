package repository;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.Gson;

import indicadoresPredefinidos.PatrimonioNeto;
import parser.ParserJsonString;
import parserArchivos.ParserJsonAObjetosJava;
import usuario.Indicador;

public class IndicadoresRepository {
	
	private static ParserJsonAObjetosJava parser = new ParserJsonAObjetosJava("indicadores.json");

	
	public static List<Indicador> getIndicadores(){
		List<Indicador> indicadores = new LinkedList<Indicador>();
		IndicadoresRepository.cargarIndicadores(indicadores);
		return indicadores;
	}
	
	public static List<String> getNombreIndicadores(){
		return IndicadoresRepository.getIndicadores().stream().map(indicador -> indicador.getNombre())
													 .collect(Collectors.toList());
	}
	
	public static List<Indicador> getIndicadoresDefinidosPorElUsuario() {
		return parser.getIndicadoresDelArchivo();
	}
	
	public static List<Indicador>  getIndicadoresPredefinidos() {
		List<Indicador> indicadoresPredefinidos = new LinkedList<Indicador>();
		indicadoresPredefinidos.add(PatrimonioNeto.getInstance());
		return indicadoresPredefinidos;
	}
	
	public static void cargarIndicadores(List<Indicador> indicadores) {
		IndicadoresRepository.getIndicadoresPredefinidos().stream().forEach(indicadorPredefinido -> indicadores.add(indicadorPredefinido));
		IndicadoresRepository.getIndicadoresDefinidosPorElUsuario().stream().forEach(indicadorDefinidoPorUsuario -> indicadores.add(indicadorDefinidoPorUsuario));
	}
	public static void addIndicador(Indicador nuevoIndicador) {
		String jsonElement = new Gson().toJson(nuevoIndicador); 
		ParserJsonString.anidadoDeJsonAUnJsonArrayEnUnArchivo("indicadores",jsonElement );
	}

	
}
