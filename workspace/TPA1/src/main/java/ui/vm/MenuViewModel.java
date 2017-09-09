package ui.vm;

import repositorios.ArchivosCuentasRepository;

public class MenuViewModel {

	public boolean archivosCuentasCargados() {
		return ArchivosCuentasRepository.existenArchivosCargados();
	}
}
