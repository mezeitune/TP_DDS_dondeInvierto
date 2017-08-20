package ui.vm;

import repository.EmpresasRepository;

public class MenuViewModel {

	public boolean archivosCuentasCargados() {
		return EmpresasRepository.getArchivo() != null;
	}
}
