package ui.vm;

import java.util.List;
import java.util.stream.Collectors;

import org.uqbar.commons.model.ObservableUtils;
import org.uqbar.commons.utils.Observable;

import Condiciones.Condicion;
import excepciones.CondicionesNotFoundException;
import excepciones.NombreMetodologiaNotFoundException;
import repository.CondicionesSeleccionadasRepository;
import repository.MetodologiasRepository;
import usuario.Metodologia;


@Observable
public class CargarMetodologiaViewModel {
	private static String nombreMetodologia ;
	
	public List<Condicion> getCondiciones(){
		return CondicionesSeleccionadasRepository.getCondicionesSeleccionados();
	}
	
	public List<Metodologia> getMetodologias() {
		System.out.println(MetodologiasRepository.getMetodologias().stream().map(m -> m.getNombre()).collect(Collectors.toList()));
		return MetodologiasRepository.getMetodologias();
	}
	
	public String getNombreMetodologia() {
		return nombreMetodologia;
	}
	public void setNombreMetodologia(String nombre) {
		CargarMetodologiaViewModel.nombreMetodologia = nombre;
	}
	
	public  void generarMetodologia() throws NombreMetodologiaNotFoundException, CondicionesNotFoundException {
		
		if(nombreMetodologia == null) throw new NombreMetodologiaNotFoundException();
		if(this.getCondiciones().isEmpty()) throw new CondicionesNotFoundException();
		
		Metodologia nuevaMetodologia = new Metodologia();
		nuevaMetodologia.setNombre(nombreMetodologia);
		nuevaMetodologia.setCondiciones(this.getCondiciones());
	
		MetodologiasRepository.addMetodologia(nuevaMetodologia);
	}

	public void refresh() {
		ObservableUtils.firePropertyChanged(this, "condiciones");
		ObservableUtils.firePropertyChanged(this, "metodologias");
	}
	
	
	
}
