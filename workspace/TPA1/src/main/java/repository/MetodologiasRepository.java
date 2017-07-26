package repository;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import Class.Main;
import Condiciones.Condicion;
import Condiciones.Predefinidas.MargenesCrecientes;
import metodologias.Predefinidas.MetodologiaPrueba;
import metodologias.Predefinidas.WarrenBuffet;
import parser.parserArchivos.ParserJsonAObjetosJava;
import usuario.Metodologia;

public class MetodologiasRepository {
	private static List<Metodologia> metodologias = new LinkedList<>();
	private static List<Condicion> condiciones = new LinkedList<>();
	
	
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
	
	
	public static List<Metodologia> getMetodologias() {
		
		return metodologias;
	}

	public static void setMetodologiasDefinidasPorElUsuario(List<Metodologia> list) {
		MetodologiasRepository.metodologias.addAll(list);
	}

	
	public static void addMetodologias(Metodologia unaMetodologia) {
		MetodologiasRepository.metodologias.add(unaMetodologia);
	}
	
	
	public static void cargarMetodologiasPredefinidos(){
		WarrenBuffet wb = WarrenBuffet.getInstance();
		MetodologiaPrueba mp = MetodologiaPrueba.getInstance();
	
		MetodologiasRepository.addMetodologias(wb);
		MetodologiasRepository.addMetodologias(mp);
	}

	public static void deleteCondicionesDefinidasPorElUsuario() {
		MetodologiasRepository.condiciones.removeAll(condiciones);
		
	}
	public static void deleteMetodologiasDefinidasPorElUsuario() {
		MetodologiasRepository.metodologias.removeAll(metodologias);
		MetodologiasRepository.cargarMetodologiasPredefinidos();
	}
}



