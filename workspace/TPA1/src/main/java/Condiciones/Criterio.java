package Condiciones;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import usuario.Empresa;

public class Criterio {

	private List<List<Empresa>> listasEmpresasEvaluadas = new LinkedList<>();
	
	public void evaluar(List<Empresa> empresas,List<Condicion> condiciones,List<String> periodos){
		
		this.listasEmpresasEvaluadas = condiciones.stream().map(condicion -> condicion.evaluar(empresas, periodos)).collect(Collectors.toList());
	
	}
	public List<Empresa> ordenarPorPuntaje(List<Empresa> empresasInvertibles,List<Condicion> condiciones){
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
		return listasEmpresasEvaluadas.stream().mapToInt(lista -> obtenerPosicionEmpresaEn(lista,empresa)).sum();
	}
	
	public int obtenerPosicionEmpresaEn(List<Empresa> listaEmpresas,Empresa empresa){
		int i;
		int posicion=-1;
		for(i=0;i<listaEmpresas.size();i++){
			if(listaEmpresas.get(i).equals(empresa)) posicion = i;
		}
		return posicion;
	}
	
}
