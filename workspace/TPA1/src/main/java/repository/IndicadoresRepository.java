package repository;

import java.util.LinkedList;
import java.util.List;

import indicadoresPredefinidos.PatrimonioNeto;
import parserArchivos.ParserJsonAObjetosJava;
import usuario.Indicador;

public class IndicadoresRepository {
	
	private static List<Indicador> indicadores = new LinkedList<Indicador>();
	private static ParserJsonAObjetosJava parser = new ParserJsonAObjetosJava("indicadores.json");

	

	public static List<Indicador> getIndicadores(){
		if(indicadores.isEmpty()) IndicadoresRepository.cargarIndicadores();
		return indicadores;
	}
	public static List<Indicador> getIndicadoresDefinidosPorElUsuario() {
		return parser.getIndicadoresDelArchivo();
	}
	
	public static List<Indicador>  getIndicadoresPredefinidos() {
		List<Indicador> indicadoresPredefinidos = new LinkedList<Indicador>();
		indicadoresPredefinidos.add(PatrimonioNeto.getInstance());
		return indicadoresPredefinidos;
	}
	
	public static void cargarIndicadores() {
		List<Indicador> indicadoresACargar = new LinkedList<Indicador>();
		IndicadoresRepository.getIndicadoresPredefinidos().stream().forEach(indicadorPredefinido -> indicadoresACargar.add(indicadorPredefinido));
		IndicadoresRepository.getIndicadoresDefinidosPorElUsuario().stream().forEach(indicadorDefinidoPorUsuario -> indicadoresACargar.add(indicadorDefinidoPorUsuario));
		indicadores = indicadoresACargar;
	}

	
}
