package ui.vm;

import java.util.List;

import org.uqbar.commons.utils.Observable;

import Condiciones.Condicion;
import excepciones.CondicionNotFoundException;
import repository.CondicionesSeleccionadasRepository;
@Observable
public class ElminarCondicionSeleccionadaViewModel {
	
	private Condicion condicionSeleccionada;
	
	public List<Condicion> getCondiciones() {
		return CondicionesSeleccionadasRepository.getCondicionesSeleccionados();
	}

	public Condicion getCondicionSeleccionada() {
		return condicionSeleccionada;
	}
	public void setCondicionSeleccionada(Condicion condicionSeleccionada) {
		this.condicionSeleccionada= condicionSeleccionada;
	}
	
	public void eliminarCondicion() throws CondicionNotFoundException{
		if(CondicionesSeleccionadasRepository.nuncaFueAgregada(this.condicionSeleccionada)) throw new CondicionNotFoundException();
		CondicionesSeleccionadasRepository.eliminarCondicionSeleccionada(this.condicionSeleccionada);
	}
}
