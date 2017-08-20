package repositorios;

import java.util.LinkedList;
import java.util.List;

import condiciones.Condicion;

public class CondicionesSeleccionadasRepository {

	public static List<Condicion> condicionesSeleccionadas = new LinkedList<>(); 
	
	public static List<Condicion> getCondicionesSeleccionados() {
		return condicionesSeleccionadas;
	}

	public static void agregarCondicionSeleccionada(Condicion condicion) {
			condicionesSeleccionadas.add(condicion);
	}
	public static void vaciarListaDeCondicionesSeleccionadas() {
		condicionesSeleccionadas.removeAll(condicionesSeleccionadas);
	}

	public static void eliminarCondicionSeleccionada(Condicion unCriterio) {
		condicionesSeleccionadas.remove(unCriterio);
	}
	public static boolean esCondicionYaAgregada(Condicion condicionSeleccionada) {
		return condicionesSeleccionadas.contains(condicionSeleccionada);
	}

	public static boolean nuncaFueAgregada(Condicion condicionSeleccionada) {
		return !condicionesSeleccionadas.contains(condicionSeleccionada);
	}
	
}
