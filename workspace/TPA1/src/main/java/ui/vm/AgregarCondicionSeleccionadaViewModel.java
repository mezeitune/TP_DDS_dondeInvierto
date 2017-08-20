package ui.vm;

import java.util.List;

import org.uqbar.commons.utils.Observable;

import condiciones.Condicion;
import excepciones.CondicionRepetidaException;
import repositorios.CondicionesRepository;
import repositorios.CondicionesSeleccionadasRepository;
@Observable
public class AgregarCondicionSeleccionadaViewModel {

	private  Condicion condicionSeleccionada;
	
	public List<Condicion> getCondiciones() {
		return CondicionesRepository.getCondiciones();
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
