package metodologias;

import java.util.List;

import Comparadores.Comparador;
import usuario.Empresa;

public interface EstadoCondicion {

	//int evaluar(Empresa empresa1, Empresa empresa2,Condicion condicion);
	List<Empresa> evaluar(List<Empresa> empresa,List<String> periodos, Condicion condicion);

	void setComparador(Comparador comparador);
	
	void setComparador(Comparador comparadorTaxativo,Comparador comparadorCompetitivo);

	void setPeso(int pesoRoe);



	
	
	
}
