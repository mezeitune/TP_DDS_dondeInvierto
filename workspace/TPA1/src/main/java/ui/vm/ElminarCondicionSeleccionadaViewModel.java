package ui.vm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.uqbar.commons.utils.Observable;

import Condiciones.Condicion;
import parserArchivos.ParserJsonAObjetosJava;
import repository.CriteriosSeleccionadosRepository;
import repository.MetodologiasRepository;
@Observable
public class ElminarCondicionSeleccionadaViewModel {
	private List<Condicion> criteriosSeleccionados = new LinkedList<Condicion>();
	private Condicion criterioSeleccionado;
	
	
	public List<Condicion> getCriteriosSeleccionados() {
		return criteriosSeleccionados;
	}
	public void setCriteriosSeleccionados() {
		
		this.criteriosSeleccionados = CriteriosSeleccionadosRepository.getCriteriosSeleccionados();
		System.out.println(criteriosSeleccionados);
	}

	public Condicion getCriterioSeleccionado() {
		return criterioSeleccionado;
	}
	public void setCriterioSeleccionado(Condicion criterioSeleccionado) {
		CriteriosSeleccionadosRepository.eliminarCriterioSeleccionado(criterioSeleccionado);
		this.criterioSeleccionado= criterioSeleccionado;
	}
}
