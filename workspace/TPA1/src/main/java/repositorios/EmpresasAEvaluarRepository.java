package repositorios;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import usuario.Empresa;
import utilities.JPAUtility;

public class EmpresasAEvaluarRepository extends DBRelacionalRepository<Empresa> {
	
	public static List<Empresa> empresasAEvaluar = new ArrayList<>(); 
	public static List<String> periodosAEvaluar = new LinkedList<>(); 
	public EmpresasRepository repositorio_empresas = new EmpresasRepository();
	
	public EmpresasAEvaluarRepository(){
		
		Query query = entityManager.createQuery("from Empresa");
		
		empresasAEvaluar = query.getResultList();
	}
	
	public EmpresasAEvaluarRepository(EntityManager entityManager) {
		super(entityManager);
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
	public static void setPeriodosAEvaluar(){
		EmpresasAEvaluarRepository.periodosAEvaluar.addAll(getPeriodosAEvaluar());
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

	public static void eliminarPeriodoaAEvaluar(String unPeriodo) {
		EmpresasAEvaluarRepository.periodosAEvaluar.remove(unPeriodo);
	}

	
	public static boolean esPeriodoRepetido(String periodo){
		return periodosAEvaluar.stream().anyMatch(unPeriodo -> unPeriodo.equals(periodo));
	}
	
	public static boolean esPeriodoInexistente(String periodo){
		return !periodosAEvaluar.stream().anyMatch(unPeriodo -> unPeriodo.equals(periodo));
	}

	public void setEmpresasAEvaluar(List<Empresa> empresas) {
		this.empresasAEvaluar = empresas;
	}

	public static boolean esEmpresaRepetida(Empresa empresa) {
		return empresasAEvaluar.stream().anyMatch(empresaCargada -> empresaCargada.equals(empresa));
	}

	public void cargarTodasLasEmpresas() {
		this.setEmpresasAEvaluar(repositorio_empresas.getEmpresas());
	}
	
}
