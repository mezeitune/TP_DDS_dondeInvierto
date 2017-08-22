package usuario;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.uqbar.commons.utils.Observable;

import condiciones.Condicion;
import condiciones.Criterio;

@Observable
@Entity
public class Metodologia {

	@Id @GeneratedValue
	private Long id;
	@Transient
	private Criterio criterio;
	private String nombre;
	@Transient
	private List<Condicion> condiciones = new LinkedList<Condicion>();
	
	public Metodologia(){
		criterio = new Criterio();
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

	public List<List<Empresa>> evaluar(List<Empresa> empresas,List<String> periodos){
		this.criterio = new Criterio(this.condiciones);

		this.criterio.evaluar(empresas, periodos);
		
		List<List<Empresa>> listasEmpresasEvaluadas = new LinkedList<List<Empresa>>(this.criterio.getListasEmpresasEvaluadas());

		List<Empresa> empresasInvertibles= this.criterio.ordenarPorPuntaje(this.obtenerEmpresasInvertibles(listasEmpresasEvaluadas));
		
		List<Empresa> empresasNoInvertibles = this.obtenerEmpresasNoInvertibles(empresas,empresasInvertibles);
		
		List<List<Empresa>> resultado = new LinkedList<>();
		
		resultado.add(empresasInvertibles);
		resultado.add(empresasNoInvertibles);
		
		//this.imprimirResultado(resultado);
		
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
	
	public List<Empresa> obtenerEmpresasNoInvertibles (List<Empresa> empresas,List<Empresa> empresasInvertibles){
		List <Empresa> empresasNoInvertibles = new LinkedList<Empresa>(empresas);
		empresasNoInvertibles.removeAll(empresasInvertibles);
		return empresasNoInvertibles;
	}
	
	public void imprimirResultado(List<List<Empresa>> listasEmpresasEvaluadas){
		int j;
		
			System.out.println("Empresas invertibles");
				for(j=0;j<listasEmpresasEvaluadas.get(0).size();j++){
					System.out.println(listasEmpresasEvaluadas.get(0).get(j).getNombre());
				}
			System.out.println("Empresas No invertibles");
			for(j=0;j<listasEmpresasEvaluadas.get(1).size();j++){
				System.out.println(listasEmpresasEvaluadas.get(1).get(j).getNombre());
			}
	}
	
}
