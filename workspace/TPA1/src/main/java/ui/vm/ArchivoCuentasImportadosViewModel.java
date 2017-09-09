package ui.vm;

import java.util.List;

import org.uqbar.commons.utils.Observable;

import parserArchivos.CsvFile;
import repositorios.ArchivosCuentasRepository;

@Observable
public class ArchivoCuentasImportadosViewModel {

	
	public List<CsvFile> getArchivosImportados(){
		return ArchivosCuentasRepository.getArchivosCuentas();
	}
	
}
