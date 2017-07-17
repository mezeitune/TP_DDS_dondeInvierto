package repository;

import java.util.LinkedList;
import java.util.List;

import Condiciones.Condicion;
import Condiciones.TipoCondicion;
import metodologias.Predefinidas.WarrenBuffet;
import parser.parserArchivos.ParserJsonAObjetosJava;
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
	MetodologiasUsuarioRepository.metodologiasDefinidasPorElUsuario=list;
	}

	
	public static void addMetodologiasDefinidasPorElUsuario(Metodologia unaMetodologiasDefinidasPorElUsuario) {
		MetodologiasUsuarioRepository.metodologiasDefinidasPorElUsuario.add(unaMetodologiasDefinidasPorElUsuario);
	}
	
	
	public static void  cargarMetodologiasPredefinidos(){
	    WarrenBuffet wb = WarrenBuffet.getInstance();
	    MetodologiasUsuarioRepository.addMetodologiasDefinidasPorElUsuario(wb);
	}
	
}
