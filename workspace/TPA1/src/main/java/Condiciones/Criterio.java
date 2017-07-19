package Condiciones;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import usuario.Empresa;

public class Criterio {

<<<<<<< HEAD
	private List<List<Empresa>> listasEmpresasEvaluadas = new LinkedList<>();
	
	public void evaluar(List<Empresa> empresas,List<Condicion> condiciones,List<String> periodos){
		
		this.listasEmpresasEvaluadas = condiciones.stream().map(condicion -> condicion.evaluar(empresas, periodos)).collect(Collectors.toList());

=======
	private List<List<Empresa>> resultado = new LinkedList<>();
	private List<List<Empresa>> lista = new LinkedList<>();
	
	
	public void evaluar(List<Empresa> empresas,List<Condicion> condiciones,List<String> periodos){
		
		this.resultado = condiciones.stream().map(condicion -> condicion.evaluar(empresas, periodos)).collect(Collectors.toList());
		
		
	
>>>>>>> ffed7e88ffbd6f022e1dc8e7aa6dead86d84847a
	}
	public List<Empresa> ordenarPorPuntaje(List<Empresa> empresasInvertibles,List<Condicion> condiciones){
		List<Empresa> empresasRankeadas = new LinkedList<>(empresasInvertibles);

		empresasRankeadas.stream().sorted();

		return empresasRankeadas;
	}

	
	
	public List<List<Empresa>> getListasEmpresasEvaluadas(){
		return this.listasEmpresasEvaluadas;
	}
	
}
