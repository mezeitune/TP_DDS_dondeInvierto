package ui.vm;
import org.uqbar.commons.utils.Observable;


import repository.ArchivoRepository;
@Observable
public class CargaArchivoEmpresaViewModel {
	
	private String archivo ;


	public String getArchivo() {
		return archivo;
	}

	public void setArchivo(String archivo) {
		this.archivo = archivo;
		ArchivoRepository.setArchivo(archivo);
		
	}
	
	
	
}
