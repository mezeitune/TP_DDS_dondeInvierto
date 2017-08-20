package ui.vm;

import repositorios.EmpresasRepository;

public class MenuViewModel {

	public boolean archivosCuentasCargados() {
		return EmpresasRepository.getArchivo() != null;
	}
}
