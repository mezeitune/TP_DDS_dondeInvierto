package metodologias;

import java.util.List;

import usuario.Empresa;

public interface EstadoCondicion {

	//int evaluar(Empresa empresa1, Empresa empresa2,Condicion condicion);
	List<Empresa> evaluar(List<Empresa> empresa, Condicion condicion);
	
	
	
}
