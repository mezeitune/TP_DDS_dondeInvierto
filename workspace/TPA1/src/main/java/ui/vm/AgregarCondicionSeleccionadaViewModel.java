package ui.vm;

import java.util.List;

import org.uqbar.commons.utils.Observable;

import Condiciones.Condicion;
import repository.CriteriosSeleccionadosRepository;
import repository.MetodologiasRepository;
@Observable
public class AgregarCondicionSeleccionadaViewModel {

	private static int codigoError;
	
	private  Condicion condicionSeleccionada;
	
	public List<Condicion> getCondiciones() {
		return MetodologiasRepository.getCondiciones();
	}
	
	public Condicion getCondicionSeleccionada() {
		return this.condicionSeleccionada;
	}
	
	public void setCondicion(Condicion condicion) {
	}
	
	public static int getCodigoError() {
		return codigoError;
	}
	public void setCodigoError(int codigoError) {
		this.codigoError = codigoError;
	}
	
	public void agregarCondicionALaLista() {
		if(CriteriosSeleccionadosRepository.criteriosSeleccionados.contains(this.condicionSeleccionada)==true){
			
			AgregarCondicionSeleccionadaViewModel.codigoError=1;
			System.out.println("criterio ya seleccionado, seleccione otro por favor");
			}else{
				
				//System.out.println(condicion);
				CriteriosSeleccionadosRepository.agregarCriterioSeleccionado(this.condicionSeleccionada);
				AgregarCondicionSeleccionadaViewModel.codigoError=0;
				
	}
	
	}
	
}
