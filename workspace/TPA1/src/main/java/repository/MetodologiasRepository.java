package repository;

import java.util.LinkedList;
import java.util.List;

import Condiciones.Condicion;
import Condiciones.Predefinidas.MargenesCrecientes;
import metodologias.Predefinidas.MetodologiaPrueba;
import metodologias.Predefinidas.WarrenBuffet;
import parserArchivos.ParserJsonAObjetosJava;
import usuario.Metodologia;

public class MetodologiasRepository {
	private static List<Metodologia> metodologias = new LinkedList<>();
	private static List<Condicion> condiciones = new LinkedList<>();
	
	private static ParserJsonAObjetosJava parser= new ParserJsonAObjetosJava("metodologias.json");
	
	public MetodologiasRepository(){
		MetodologiasRepository.cargarMetodologias();
	}
	
	public static List<Condicion> getCondiciones() {
		
		return condiciones;
	}
	
	public static void setCondicionesDefinidasPorElUsuario(List<Condicion> list) {
		MetodologiasRepository.condiciones.addAll(list);
	}
	
	public static void addCondicion(Condicion unaCondicion) {
		MetodologiasRepository.condiciones.add(unaCondicion);
	}
	
	public static void cargarCondicionesPredefinidos(){
		MargenesCrecientes mc = MargenesCrecientes.getInstance();
		MetodologiasRepository.addCondicion(mc);
		
	}
	
	
	public static List<Metodologia> getMetodologias(){
		if(metodologias.isEmpty()) MetodologiasRepository.cargarMetodologias();
		return metodologias;
	}
	
	public static List<Metodologia> getMetodologiasDefinidasPorElUsuario(){
		return parser.getMetodologiasDelArchivo();
	}
	
	
	public static void cargarMetodologias() {
		List<Metodologia> metodologiasACargar = new LinkedList<Metodologia>();
		MetodologiasRepository.getMetodologiasDefinidasPorElUsuario().stream().forEach(metodologiaDefinidaPorUsuario -> metodologiasACargar.add(metodologiaDefinidaPorUsuario));
		MetodologiasRepository.getMetodologiasPredefinidas().stream().forEach(metodologiaPredefinida -> metodologiasACargar.add(metodologiaPredefinida));
		metodologias =  metodologiasACargar;
	}

	public static List<Metodologia> getMetodologiasPredefinidas(){
		List<Metodologia> metodologiasPredefinidas = new LinkedList<Metodologia>();
		metodologiasPredefinidas.add(WarrenBuffet.getInstance());
		metodologiasPredefinidas.add(MetodologiaPrueba.getInstance());
		return metodologiasPredefinidas;
	}

	public static void addMetodologias(Metodologia unaMetodologia) {
		MetodologiasRepository.metodologias.add(unaMetodologia);
	}
	
	
	public static void deleteCondicionesDefinidasPorElUsuario() {
		MetodologiasRepository.condiciones.removeAll(condiciones);
		
	}
	public static void deleteMetodologiasDefinidasPorElUsuario() {
		MetodologiasRepository.metodologias.removeAll(metodologias);
	}
}



