package repository;

import java.util.LinkedList;
import java.util.List;

import indicadoresPredefinidos.PatrimonioNeto;
import parserArchivos.ParserJsonAObjetosJava;
import usuario.Indicador;

public class IndicadoresRepository {
	
	private static ParserJsonAObjetosJava parser = new ParserJsonAObjetosJava("indicadores.json");

	public static List<Indicador> getIndicadoresDefinidosPorElUsuario() {
		return parser.getIndicadoresDelArchivo();
	}
	
	public static List<Indicador>  getIndicadoresPredefinidos() {
		List<Indicador> indicadoresPredefinidos = new LinkedList<Indicador>();
	    indicadoresPredefinidos.add(PatrimonioNeto.getInstance());
	    return indicadoresPredefinidos;
	}

	public static List<Indicador> getIndicadores() {
		List<Indicador> indicadores = new LinkedList<Indicador>();
		IndicadoresRepository.getIndicadoresPredefinidos().stream().forEach(indicadorPredefinido -> indicadores.add(indicadorPredefinido));
		IndicadoresRepository.getIndicadoresDefinidosPorElUsuario().stream().forEach(indicadorDefinidoPorUsuario -> indicadores.add(indicadorDefinidoPorUsuario));
		return indicadores;
	}

	
}
