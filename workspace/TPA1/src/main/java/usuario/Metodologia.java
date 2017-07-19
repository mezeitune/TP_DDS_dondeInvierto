package usuario;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import org.uqbar.commons.utils.Observable;
import Condiciones.Condicion;
import Condiciones.Criterio;
import repository.EmpresasAEvaluarRepository;

@Observable
public class Metodologia {

	private List<Empresa> empresasAEvaluar = new LinkedList<>();
	private List<Condicion> condiciones = new LinkedList<>();
	private Criterio criterio = new Criterio();
	private String nombre;
	
	public Metodologia(){
		this.criterio = new Criterio();
	}
	
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
		return empresasAEvaluar;
	}

	public void setEmpresasAEvaluar(List<Empresa> conjuntoDeEmpresasAEvaluar) {
		this.empresasAEvaluar = conjuntoDeEmpresasAEvaluar;
	}

	
	
	public List<List<Empresa>> evaluar(List<String> periodos){ 
	
		//this.setEmpresasAEvaluar(EmpresasAEvaluarRepository.getEmpresasAEvaluar());

		this.criterio.evaluar(this.empresasAEvaluar, this.condiciones, periodos);
		
		List<List<Empresa>> listasEmpresasEvaluadas = this.criterio.getListasEmpresasEvaluadas();

		List<Empresa> empresasInvertibles = this.obtenerEmpresasInvertibles(listasEmpresasEvaluadas);
		
		this.criterio.ordenarPorPuntaje(empresasInvertibles,this.condiciones); // TODO: Falta implementar
		
		List<Empresa> empresasNoInvertibles = this.obtenerEmpresasNoInvertibles(empresasInvertibles);
		
		
		List<List<Empresa>> resultado = new LinkedList<>();
		
		resultado.add(empresasInvertibles);
		resultado.add(empresasNoInvertibles);
		
		return resultado;
	}
	
	public List<Empresa> obtenerEmpresasInvertibles(List<List<Empresa>> listasEmpresasEvaluadas){
		
		List<Empresa> interseccionAEvaluar = new LinkedList<>(listasEmpresasEvaluadas.remove(0));
		List<Empresa> interseccion = new LinkedList<>();
		int i=0;
		for(i=0;i<listasEmpresasEvaluadas.size();i++){
			
			interseccion = interseccionAEvaluar.stream().filter(listasEmpresasEvaluadas.get(i) :: contains).collect(Collectors.toList());
			interseccionAEvaluar = interseccion;
		}
		return interseccion;
	}
	
	public List<Empresa> obtenerEmpresasNoInvertibles (List<Empresa> empresasInvertibles){
		List <Empresa> empresasNoInvertibles = new LinkedList<Empresa>(this.empresasAEvaluar);
		empresasNoInvertibles.removeAll(empresasInvertibles);
		return empresasNoInvertibles;
	}
	
}
