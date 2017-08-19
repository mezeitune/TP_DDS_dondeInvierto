package ui.vm;

import java.util.List;

import org.uqbar.commons.model.ObservableUtils;
import org.uqbar.commons.utils.Observable;

import Condiciones.Condicion;
import excepciones.CondicionesNotFoundException;
import excepciones.MetodologiaRepetidaException;
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
		return MetodologiasRepository.getMetodologias();
	}
	
	public String getNombreMetodologia() {
		return nombreMetodologia;
	}
	public void setNombreMetodologia(String nombre) {
		CargarMetodologiaViewModel.nombreMetodologia = nombre;
	}
	
	public  void generarMetodologia() throws NombreMetodologiaNotFoundException, CondicionesNotFoundException, MetodologiaRepetidaException {
		
		if(nombreMetodologia == null) throw new NombreMetodologiaNotFoundException();
		if(MetodologiasRepository.esMetodologiaRepetida(nombreMetodologia)) throw new MetodologiaRepetidaException();
		if(this.getCondiciones().isEmpty()) throw new CondicionesNotFoundException();
		
		Metodologia nuevaMetodologia = new Metodologia();
		nuevaMetodologia.setNombre(nombreMetodologia);
		nuevaMetodologia.setCondiciones(this.getCondiciones());
	
		MetodologiasRepository.addMetodologia(nuevaMetodologia);
		
		ObservableUtils.firePropertyChanged(this, "metodologias");
	}

	
	public void reset(){
		nombreMetodologia = null;
		CondicionesSeleccionadasRepository.vaciarListaDeCondicionesSeleccionadas();
		ObservableUtils.firePropertyChanged(this, "condiciones");
	}

	public void refresh() {
		ObservableUtils.firePropertyChanged(this, "condiciones");
	}
	
	
}
