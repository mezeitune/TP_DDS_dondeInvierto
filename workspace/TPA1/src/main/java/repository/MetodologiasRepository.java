package repository;

import java.util.LinkedList;
import java.util.List;

import com.google.gson.Gson;

import metodologias.Predefinidas.MetodologiaPrueba;
import metodologias.Predefinidas.WarrenBuffet;
import parser.ParserJsonString;
import parserArchivos.ParserJsonAObjetosJava;
import usuario.Metodologia;

public class MetodologiasRepository {
	private static List<Metodologia> metodologias = new LinkedList<>();
	private static ParserJsonAObjetosJava parserMetodologias= new ParserJsonAObjetosJava("metodologias.json");
	
	
	
	public static List<Metodologia> getMetodologias(){
		if(metodologias.isEmpty()) MetodologiasRepository.cargarMetodologias();
		return metodologias;
	}
	
	public static List<Metodologia> getMetodologiasDefinidasPorElUsuario(){
		return parserMetodologias.getMetodologiasDelArchivo();
	}
	
	
	public static void cargarMetodologias() {
		MetodologiasRepository.getMetodologiasDefinidasPorElUsuario().stream().forEach(metodologiaDefinidaPorUsuario -> metodologias.add(metodologiaDefinidaPorUsuario));
		MetodologiasRepository.getMetodologiasPredefinidas().stream().forEach(metodologiaPredefinida -> metodologias.add(metodologiaPredefinida));
	}

	public static List<Metodologia> getMetodologiasPredefinidas(){
		List<Metodologia> metodologiasPredefinidas = new LinkedList<Metodologia>();
		metodologiasPredefinidas.add(WarrenBuffet.getInstance());
		metodologiasPredefinidas.add(MetodologiaPrueba.getInstance());
		return metodologiasPredefinidas;
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



