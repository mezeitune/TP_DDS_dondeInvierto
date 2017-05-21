package ui.vm;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.uqbar.commons.utils.Observable;


import repository.ArchivoEIndicadoresUsuarioRepository;
@Observable
public class SeleccionarArchivoViewModel {
	
	private String archivo ;


	public String getArchivo() {
		return archivo;
	}

	public void setArchivo(String archivo){
		
		this.archivo = archivo;
		ArchivoEIndicadoresUsuarioRepository.setArchivo(archivo);
		
	}
	
	
	
}
