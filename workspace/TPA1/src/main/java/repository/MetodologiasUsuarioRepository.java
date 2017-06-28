package repository;

import java.util.LinkedList;
import java.util.List;

import metodologias.Condicion;
import metodologias.EstadoCondicion;
import parser.parserArchivos.ParserJsonAEmpresaAdapter;
import usuario.Empresa;
import usuario.Indicador;
import usuario.Metodologia;
import usuario.PatrimonioNeto;

public class MetodologiasUsuarioRepository {
	private static List<Metodologia> metodologiasDefinidasPorElUsuario = new LinkedList<>(); 
	
	
	public static List<Metodologia> getMetodologiasDefinidosPorElUsuario() {
	return metodologiasDefinidasPorElUsuario;
	}

	public static void setMetodologiasDefinidasPorElUsuario(List<Metodologia> list) {
	
	}

	
	public static void addMetodologiasDefinidasPorElUsuario(Metodologia metodologiasDefinidasPorElUsuario) {
		MetodologiasUsuarioRepository.metodologiasDefinidasPorElUsuario.add(metodologiasDefinidasPorElUsuario);
	}
	
	
}
