package repository;

import java.util.LinkedList;
import java.util.List;

import indicadoresPredefinidos.PatrimonioNeto;
import usuario.Indicador;

public class IndicadoresRepository {
	
	private static List<Indicador> indicadoresDefinidosPorElUsuario = new LinkedList<>(); 
	

	public static List<Indicador> getIndicadoresDefinidosPorElUsuario() {
	
		return indicadoresDefinidosPorElUsuario;
	}

	public static void setIndicadoresDefinidosPorElUsuario(List<Indicador> list) {
		IndicadoresRepository.indicadoresDefinidosPorElUsuario = list;
	}

	
	public static void addIndicadoresDefinidosPorElUsuario(Indicador indicadoresDefinidosPorElUsuario) {
		IndicadoresRepository.indicadoresDefinidosPorElUsuario.add(indicadoresDefinidosPorElUsuario);
	}

	
	public static void  cargarIndicadoresPredefinidos() {
	    PatrimonioNeto patrimonioNeto = PatrimonioNeto.getInstance();
	    IndicadoresRepository.addIndicadoresDefinidosPorElUsuario(patrimonioNeto);
	}

	
}
