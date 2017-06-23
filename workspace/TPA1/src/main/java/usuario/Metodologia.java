package usuario;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import metodologias.Condicion;

public class Metodologia {

	private List<Empresa> conjuntoDeEmpresasAEvaluar = new LinkedList<>();
	private List<Empresa> conjuntoDeEmpresasEvaluadas = new LinkedList<>();
	private List<Condicion> condiciones = new LinkedList<>();
	private String nombre;
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Condicion> getCondiciones() {
		return condiciones;
	}

	public void setCondiciones(List<Condicion> condiciones) {
		this.condiciones = condiciones;
	}

	public List<Empresa> getConjuntoDeEmpresasAEvaluar() {
		return conjuntoDeEmpresasAEvaluar;
	}

	public void setConjuntoDeEmpresasAEvaluar(List<Empresa> conjuntoDeEmpresasAEvaluar) {
		this.conjuntoDeEmpresasAEvaluar = conjuntoDeEmpresasAEvaluar;
	}

	public List<Empresa> getConjuntoDeEmpresasEvaluadas() {
		return conjuntoDeEmpresasEvaluadas;
	}

	public void setConjuntoDeEmpresasEvaluadas(List<Empresa> conjuntoDeEmpresasEvaluadas) {
		this.conjuntoDeEmpresasEvaluadas = conjuntoDeEmpresasEvaluadas;
	} 
	
	
	
	public void evaluar(){
		//conjuntoDeEmpresasAEvaluar.stream().forEach(empresa -> evaluarUnaEmpresaATodasLasCondiciones(empresa));
		for (int i = 0; i < conjuntoDeEmpresasAEvaluar.size(); i++) {
			  for (int j = i+1; j < conjuntoDeEmpresasAEvaluar.size(); j++) {
				  evaluarUnaEmpresaATodasLasCondiciones(conjuntoDeEmpresasAEvaluar.get(i),conjuntoDeEmpresasAEvaluar.get(j));
			  }
			}
	}
	
	public void evaluarUnaEmpresaATodasLasCondiciones(Empresa empresa1,Empresa empresa2){
		condiciones.stream().forEach(condicion -> condicion.evaluar(empresa1,empresa2));
	}
	
	
	
}
