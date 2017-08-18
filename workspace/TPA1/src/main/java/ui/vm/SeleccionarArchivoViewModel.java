package ui.vm;
import repository.EmpresasRepository;
import org.uqbar.commons.utils.Observable;

import repository.IndicadoresRepository;
@Observable
public class SeleccionarArchivoViewModel {
	
	private String archivo ;


	public String getArchivo() {
		return archivo;
	}

	public void setArchivo(String archivo){
			this.archivo = archivo;
			EmpresasRepository.setArchivo(archivo);
	}
	
}
