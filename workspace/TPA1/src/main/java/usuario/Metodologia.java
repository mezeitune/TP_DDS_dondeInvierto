package usuario;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.uqbar.commons.utils.Observable;

import metodologias.Condicion;
import metodologias.EmpresaCumplimiento;
import metodologias.EmpresaRank;
@Observable
public class Metodologia {

	private List<Empresa> conjuntoDeEmpresasAEvaluar = new LinkedList<>();
	private List<EmpresaCumplimiento> conjuntoDeEmpresaConRankSinOrdenar = new LinkedList<>();
	private List<EmpresaRank> conjuntoDeEmpresasEvaluadas = new LinkedList<>();
	private List<Condicion> condiciones = new LinkedList<>();
	private String nombre;
	
	public String getNombre() {
		return nombre;
	}

	public List<EmpresaCumplimiento> getConjuntoDeEmpresaConRankSinOrdenar() {
		return conjuntoDeEmpresaConRankSinOrdenar;
	}

	public void setConjuntoDeEmpresaConRankSinOrdenar(List<EmpresaCumplimiento> conjuntoDeEmpresaConRankSinOrdenar) {
		this.conjuntoDeEmpresaConRankSinOrdenar = conjuntoDeEmpresaConRankSinOrdenar;
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

	public List<EmpresaRank> getConjuntoDeEmpresasEvaluadas() {
		return conjuntoDeEmpresasEvaluadas;
	}

	public void setConjuntoDeEmpresasEvaluadas(List<EmpresaRank> conjuntoDeEmpresasEvaluadas) {
		this.conjuntoDeEmpresasEvaluadas = conjuntoDeEmpresasEvaluadas;
	} 
	
	
	public List<Empresa> evaluar(List<String> periodos){ /*Evaluar podria devolver la lista final rankeada*/
		int i;
		List<Empresa> listaActualizada = new LinkedList<>();
		List<Empresa> listaActualizadaAEvaluar = new LinkedList<> (conjuntoDeEmpresasAEvaluar);
		
		for(i=0;i<condiciones.size();i++){
			listaActualizada = condiciones.get(i).evaluar(listaActualizadaAEvaluar,periodos); //La condicion suma puntos a las empresas o las saca
			Collections.sort(listaActualizada,(empresa1,empresa2)->empresa1.getPeso() > empresa2.getPeso()? 1 : -1 ); //Ordeno la lista
			listaActualizadaAEvaluar = listaActualizada; 
		}
		return listaActualizadaAEvaluar;
	}
	
	/*public void evaluar(){
		//conjuntoDeEmpresasAEvaluar.stream().forEach(empresa -> evaluarUnaEmpresaATodasLasCondiciones(empresa));
		//evaluo todas contra todas
		List<EmpresaRank> empresasRank = new LinkedList<>();
		
		for (int i = 0; i < conjuntoDeEmpresasAEvaluar.size(); i++) {
			  for (int j = i+1; j < conjuntoDeEmpresasAEvaluar.size(); j++) {
				  evaluarUnaEmpresaATodasLasCondiciones(conjuntoDeEmpresasAEvaluar.get(i),conjuntoDeEmpresasAEvaluar.get(j));
			  }
		}
		int i;
		for (i = 0; i < conjuntoDeEmpresasAEvaluar.size(); i++) {
			List<EmpresaCumplimiento> aux = new LinkedList<>();
			int j=i;
			aux=conjuntoDeEmpresaConRankSinOrdenar.stream()
					.filter(line-> line.getEmpresa().getNombre().equals(conjuntoDeEmpresasAEvaluar.get(j).getNombre()))
					.collect(Collectors.toList());
			
			int rank=aux.stream()
					.mapToInt(line ->(line.getCumplio()*line.getPesoCondicion())).sum();
			
			EmpresaRank empresaConRank=new EmpresaRank(conjuntoDeEmpresasAEvaluar.get(i), rank);
			empresasRank.add(empresaConRank);
			
			
			conjuntoDeEmpresasEvaluadas = (List<EmpresaRank>) empresasRank.stream().sorted(Comparator.comparing(EmpresaRank::getRank)).collect(Collectors.toList());
			
		}
	}
	*/
	/*
	public void evaluarUnaEmpresaATodasLasCondiciones(Empresa empresa1,Empresa empresa2){
		
		condiciones.stream().forEach(condicion ->conjuntoDeEmpresaConRankSinOrdenar.add(condicion.evaluar(empresa1,empresa2)) );
	}
	
	*/
	
}