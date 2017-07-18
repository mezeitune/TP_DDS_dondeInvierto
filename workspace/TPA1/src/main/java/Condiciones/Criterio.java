package Condiciones;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import usuario.Empresa;

public class Criterio {

	private List<List<Empresa>> listasEmpresasEvaluadas = new LinkedList<>();
	
	
	public void evaluar(List<Empresa> empresas,List<Condicion> condiciones,List<String> periodos){
		
		this.listasEmpresasEvaluadas = condiciones.stream().map(condicion -> condicion.evaluar(empresas, periodos)).collect(Collectors.toList());

	}
	public List<Empresa> ordenarPorPuntaje(List<Empresa> empresasInvertibles,List<Condicion> condiciones){
		List<Empresa> empresasRankeadas = new LinkedList<>(empresasInvertibles);
		
		
		
		
		
		return empresasRankeadas;
	}

	
	
	public List<List<Empresa>> getListasEmpresasEvaluadas(){
		return this.listasEmpresasEvaluadas;
	}
	
}
