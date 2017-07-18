package repository;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import Class.Main;
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
		
		System.out.println("Nombre Buffet " + wb.getNombre());
		
		MetodologiasRepository.addMetodologias(wb);
	}
	
	
	public static void main(String[] args){
		List<Metodologia> metodologias2;
		MetodologiasRepository.cargarMetodologiasPredefinidos();
		metodologias2 = MetodologiasRepository.getMetodologias();
		
		for (int i = 0; i < metodologias2.size(); i++) {
			System.out.println("Nombre Metodologias " + metodologias2.get(i).getNombre());
			
		}
		
		//new Main().start();
	}
	
	
}



