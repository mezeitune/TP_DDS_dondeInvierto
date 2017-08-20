package ui.vm;
import repository.EmpresasRepository;

import org.uqbar.commons.utils.Observable;

import excepciones.ArchivoNotFoundException;
import excepciones.PathNotExistsException;
import excepciones.TipoDeArchivoIncorrectoException;
import parserArchivos.CSVToEmpresas;

@Observable
public class SeleccionarArchivoViewModel {
	
	private String archivo ;


	public String getArchivo() {
		return archivo;
	}

	public void setArchivo(String archivo){
			this.archivo = archivo;
	}

	public void cargarArchivo() throws ArchivoNotFoundException, PathNotExistsException, TipoDeArchivoIncorrectoException {
		CSVToEmpresas parser = new CSVToEmpresas(archivo);
		if(archivo == null) throw new ArchivoNotFoundException();
		if(!parser.esArchivoExistente(archivo)) throw new PathNotExistsException();
		if(!parser.extensionValida(archivo))throw new TipoDeArchivoIncorrectoException();
		
		EmpresasRepository.setArchivo(archivo);
	}
}
