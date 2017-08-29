package condiciones;

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
import javax.persistence.Transient;

import usuario.Empresa;
@Entity
public class Criterio {

	@Id @GeneratedValue
	private Long id;
	
	@Transient
	private List<List<Empresa>> listasEmpresasEvaluadas = new LinkedList<List<Empresa>>();
	@ManyToMany(cascade=CascadeType.ALL)  
    @JoinTable(name="criterio_condicion", joinColumns=@JoinColumn(name="criterio_id"), inverseJoinColumns=@JoinColumn(name="condicion_id"))  
	private List<Condicion> condiciones = new LinkedList<Condicion>();
	
	public Criterio(List<Condicion> condiciones){
		this.condiciones = condiciones;
	}
	
	public Criterio() {
		// TODO Auto-generated constructor stub
	}

	public void evaluar(List<Empresa> empresas,List<String> periodos){
		this.listasEmpresasEvaluadas = this.condiciones.stream().map(condicion -> condicion.evaluar(empresas, periodos)).collect(Collectors.toList());
	}
	public List<Empresa> ordenarPorPuntaje(List<Empresa> empresasInvertibles){
		List<Empresa> empresasRankeadas = new LinkedList<>(empresasInvertibles);
		Collections.sort(empresasRankeadas,(empresa1,empresa2)->this.tieneMejorPuntaje(empresa1, empresa2)? -1 : 1);
		return empresasRankeadas;
	}

	
	
	public List<List<Empresa>> getListasEmpresasEvaluadas(){
		return this.listasEmpresasEvaluadas;
	}
	
	public boolean tieneMejorPuntaje(Empresa empresa1,Empresa empresa2){
		return this.obtenerPuntaje(empresa1) > this.obtenerPuntaje(empresa2);
	}
	
	public int obtenerPuntaje(Empresa empresa){
		//System.out.println("Puntaje Empresa:");
		//System.out.println(empresa.getNombre());
		
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
		//System.out.println("Puntaje total");
		//System.out.println(puntaje);
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
	
	public void imprimirResultadosCondiciones(List<Condicion> condiciones){
		int i,j;
		
		System.out.println("Condiciones");
		for(i=0;i<condiciones.size();i++){
			System.out.println(condiciones.get(i).getNombre());
		}
		System.out.println("----------------------------------------------");
		
		System.out.println(this.listasEmpresasEvaluadas.size());
		
		for(i=0;i<this.listasEmpresasEvaluadas.size();i++){
			System.out.println("***************");
			for(j=0;j<listasEmpresasEvaluadas.get(i).size();j++){
				System.out.println(listasEmpresasEvaluadas.get(i).get(j).getNombre());
			}
		}
		System.out.println("***************");
	}
	
}
