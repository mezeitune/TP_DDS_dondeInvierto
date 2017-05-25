package ui.vm;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.io.FilenameUtils;
import org.uqbar.commons.utils.Observable;



import exceptions.ArchivoInexistenteException;
import exceptions.PathIncorrectoException;
import exceptions.TipoDeArchivoIncorrectoException;
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
