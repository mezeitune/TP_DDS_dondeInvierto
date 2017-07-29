package repository;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import Condiciones.Condicion;
import usuario.Empresa;

public class CriteriosSeleccionadosRepository {

	public static List<Condicion> criteriosSeleccionados = new LinkedList<>(); 
	
	public static List<Condicion> getCriteriosSeleccionados() {
		return criteriosSeleccionados;
	}
	public static void setCriteriosSeleccionados(){
		
	}

	public static void agregarCriterioSeleccionado(Condicion unCriterio) {
			CriteriosSeleccionadosRepository.criteriosSeleccionados.add(unCriterio);
			System.out.println(unCriterio);
			System.out.println(CriteriosSeleccionadosRepository.criteriosSeleccionados);
	}
	public static void vaciarListaDeCriteriosSeleccionados() {
		CriteriosSeleccionadosRepository.criteriosSeleccionados.removeAll(criteriosSeleccionados);
	}

	public static void eliminarCriterioSeleccionado(Condicion unCriterio) {
		CriteriosSeleccionadosRepository.criteriosSeleccionados.remove(unCriterio);
		
	}
	
}
