package model;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.uqbar.commons.utils.Observable;

import condiciones.Condicion;

@Observable
@Entity
@Table(name="Metodologias")
public class Metodologia {

	@Id @GeneratedValue
	private Long id;
	@Transient
	private List<List<Empresa>> listasEmpresasEvaluadas = new LinkedList<List<Empresa>>();
	private String nombre;
	
	@ManyToMany(cascade=CascadeType.DETACH)  
    @JoinTable(name="metodologias_condiciones", joinColumns=@JoinColumn(name="metodologia_id"), inverseJoinColumns=@JoinColumn(name="condicion_id"))  
	private List<Condicion> condiciones = new LinkedList<Condicion>();

	
	public Metodologia(){
		
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
		listasEmpresasEvaluadas = condiciones.stream().map(condicion -> condicion.evaluar(empresas, periodos)).collect(Collectors.toList());
		
		List<Empresa> empresasInvertibles= this.ordenarPorPuntaje(this.obtenerEmpresasInvertibles(listasEmpresasEvaluadas));
		
		List<Empresa> empresasNoInvertibles = this.obtenerEmpresasNoInvertibles(empresas,empresasInvertibles);
		
		List<List<Empresa>> resultado = new LinkedList<>();
		
		resultado.add(empresasInvertibles);
		resultado.add(empresasNoInvertibles);
		
		return resultado;
	}
	
	public List<Empresa> ordenarPorPuntaje(List<Empresa> empresasInvertibles){
		List<Empresa> empresasRankeadas = new LinkedList<>(empresasInvertibles);
		Collections.sort(empresasRankeadas,(empresa1,empresa2)->this.tieneMejorPuntaje(empresa1, empresa2)? -1 : 1);
		return empresasRankeadas;
	}
	
	public boolean tieneMejorPuntaje(Empresa empresa1,Empresa empresa2){
		return this.obtenerPuntaje(empresa1) >= this.obtenerPuntaje(empresa2);
	}
	
	public int obtenerPuntaje(Empresa empresa){
		/*System.out.println("Puntaje Empresa:");
		System.out.println(empresa.getNombre());*/
		
		int puntaje=0;
		int i;
		List<Integer> posiciones = new LinkedList<Integer>();
		posiciones= listasEmpresasEvaluadas.stream().map(lista -> obtenerPosicionEmpresaEn(lista,empresa)).collect(Collectors.toList());
		
		for(i=0;i<posiciones.size();i++){
			/*System.out.println("Posicion");
			System.out.println(posiciones.get(i));
			System.out.println("Condicion");
			System.out.println(this.condiciones.get(i).getNombre());
			System.out.println("---------------");
			*/
			puntaje += posiciones.get(i) * this.condiciones.get(i).getPeso();
		}
		/*System.out.println("Puntaje total");
		System.out.println(puntaje);/**/
		return puntaje;
		
	}
	

	public int obtenerPosicionEmpresaEn(List<Empresa> listaEmpresas,Empresa empresa){
		int i;
		int posicion=listaEmpresas.size() - 1;
		for(i=0;i<listaEmpresas.size();i++){
			if(listaEmpresas.get(i).equals(empresa)) posicion = i + 1;
		}
		
		return listaEmpresas.size() - posicion + 1;
		//return posicion + 1;
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

	public List<Indicador> getIndicadores() {
		return this.getCondiciones().stream().map(condicion -> condicion.getIndicador()).collect(Collectors.toList());
	}
	
}
