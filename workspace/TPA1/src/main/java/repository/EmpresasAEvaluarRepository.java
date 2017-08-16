package repository;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import usuario.Empresa;

public class EmpresasAEvaluarRepository {
	
	public static List<Empresa> empresasAEvaluar = new ArrayList<>(); 
	public static List<String> periodosAEvaluar = new LinkedList<>(); 
	
	public EmpresasAEvaluarRepository(){
		
	}
	
	public static List<Empresa> getEmpresasAEvaluar() {
		return empresasAEvaluar;
	}

	public static void agregarEmpresaAEvaluar(Empresa empresaAEvaluar) {
			EmpresasAEvaluarRepository.empresasAEvaluar.add(empresaAEvaluar);
	}
	public static void vaciarListaDeEmpresasAEvaluar() {
		EmpresasAEvaluarRepository.empresasAEvaluar.removeAll(empresasAEvaluar);
	}

	public static void eliminarEmpresaAEvaluar(Empresa unaEmpresa) {
		EmpresasAEvaluarRepository.empresasAEvaluar.remove(unaEmpresa);
		
	}
	
	
	public static List<String> getPeriodosAEvaluar() {
		return periodosAEvaluar;
	}

	public static void agregarPeriodoAEvaluar(String periodoAEvaluar) {
		EmpresasAEvaluarRepository.periodosAEvaluar.add(periodoAEvaluar);
	}
	public static void vaciarListaDePeriodosAEvaluar() {
		EmpresasAEvaluarRepository.periodosAEvaluar.removeAll(periodosAEvaluar);
	}

	public static void eliminarEmpresaAEvaluar(String unPeriodo) {
		EmpresasAEvaluarRepository.periodosAEvaluar.remove(unPeriodo);
		
	}

	public static void llenarListaDeEmpresasAEvaluar() {
		
		
	}
	
	public static boolean esPeriodoRepetido(String periodo){
		return periodosAEvaluar.stream().anyMatch(unPeriodo -> unPeriodo.equals(periodo));
	}
	
}
