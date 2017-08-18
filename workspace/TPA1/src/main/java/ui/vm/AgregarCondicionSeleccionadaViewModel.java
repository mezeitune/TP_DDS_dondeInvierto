package ui.vm;

import java.util.List;

import org.uqbar.commons.utils.Observable;

import Condiciones.Condicion;
import excepciones.CondicionRepetidaException;
import repository.CondicionesSeleccionadasRepository;
import repository.MetodologiasRepository;
@Observable
public class AgregarCondicionSeleccionadaViewModel {

	private  Condicion condicionSeleccionada;
	
	public List<Condicion> getCondiciones() {
		return MetodologiasRepository.getCondiciones();
	}
	
	public Condicion getCondicionSeleccionada() {
		return this.condicionSeleccionada;
	}
	
	public void setCondicionSeleccionada(Condicion condicion) {
		this.condicionSeleccionada = condicion;
	}
	
	public void agregarCondicionALaLista() throws CondicionRepetidaException {
		if(CondicionesSeleccionadasRepository.esCondicionYaAgregada(condicionSeleccionada)) throw new CondicionRepetidaException();
		else CondicionesSeleccionadasRepository.agregarCondicionSeleccionada(this.condicionSeleccionada);
	}
	
}
