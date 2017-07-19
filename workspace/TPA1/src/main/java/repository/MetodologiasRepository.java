package repository;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import Class.Main;
import metodologias.Predefinidas.MetodologiaPrueba;
import metodologias.Predefinidas.WarrenBuffet;
import usuario.Metodologia;

public class MetodologiasRepository {
	private static List<Metodologia> metodologias = new LinkedList<>();
	
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
}



