package repository;

import java.util.LinkedList;
import java.util.List;

import com.google.gson.Gson;

import Condiciones.Condicion;
import Condiciones.Predefinidas.MargenesCrecientes;
import metodologias.Predefinidas.MetodologiaPrueba;
import metodologias.Predefinidas.WarrenBuffet;
import parser.ParserJsonString;
import parserArchivos.ParserJsonAObjetosJava;
import usuario.Metodologia;

public class MetodologiasRepository {
	private static List<Metodologia> metodologias = new LinkedList<>();
	private static List<Condicion> condiciones = new LinkedList<>();
	
	private static ParserJsonAObjetosJava parserMetodologias= new ParserJsonAObjetosJava("metodologias.json");
	private static ParserJsonAObjetosJava parserCondiciones = new ParserJsonAObjetosJava("condiciones.json");
	
	public static List<Condicion> getCondiciones() {
		if(condiciones.isEmpty()) MetodologiasRepository.cargarCondiciones();
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

	public static void addCondicion(Condicion unaCondicion) {
		MetodologiasRepository.condiciones.add(unaCondicion);
	}
	
	public static void cargarCondiciones(){
		List<Condicion> condicionesACargar = new LinkedList<Condicion>();
		MetodologiasRepository.getCondicionesDefinidasPorElUsuario().stream().forEach(condicionDefinidaPorUsuario -> condicionesACargar.add(condicionDefinidaPorUsuario));
		MetodologiasRepository.getCondicionesPredefinidas().stream().forEach(condicionPredefinida -> condicionesACargar.add(condicionPredefinida));
		condiciones = condicionesACargar;
	}
	
	public static void deleteCondicionesDefinidasPorElUsuario() {
		MetodologiasRepository.condiciones.removeAll(condiciones);
		
	}
	
	
	public static List<Metodologia> getMetodologias(){
		if(metodologias.isEmpty()) MetodologiasRepository.cargarMetodologias();
		return metodologias;
	}
	
	public static List<Metodologia> getMetodologiasDefinidasPorElUsuario(){
		return parserMetodologias.getMetodologiasDelArchivo();
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
	
	
	public static void deleteMetodologiasDefinidasPorElUsuario() {
		MetodologiasRepository.metodologias.removeAll(metodologias);
	}

	public static void addMetodologia(Metodologia nuevaMetodologia) {
		metodologias.add(nuevaMetodologia);
		
		String jsonElement = new Gson().toJson(nuevaMetodologia); 
		ParserJsonString.anidadoDeJsonAUnJsonArrayEnUnArchivo("metodologias",jsonElement );
	}
}



