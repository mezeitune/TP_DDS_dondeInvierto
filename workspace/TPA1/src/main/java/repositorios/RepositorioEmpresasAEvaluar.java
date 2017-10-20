package repositorios;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.Empresa;
import utilities.JPAUtility;

public class RepositorioEmpresasAEvaluar extends RepositorioDBRelational<Empresa> {
	
	public static List<Empresa> empresasAEvaluar = new ArrayList<>(); 
	public static List<String> periodosAEvaluar = new LinkedList<>(); 
	public RepositorioEmpresas repositorio_empresas = new RepositorioEmpresas();
	
	public RepositorioEmpresasAEvaluar(){
		
		Query query = entityManager().createQuery("from Empresa");
		
		empresasAEvaluar = query.getResultList();
	}
	
	public static List<Empresa> getEmpresasAEvaluar() {
		return empresasAEvaluar;
	}

	public static void agregarEmpresaAEvaluar(Empresa empresaAEvaluar) {
			RepositorioEmpresasAEvaluar.empresasAEvaluar.add(empresaAEvaluar);
	}
	public static void vaciarListaDeEmpresasAEvaluar() {
		RepositorioEmpresasAEvaluar.empresasAEvaluar.removeAll(empresasAEvaluar);
	}

	public static void eliminarEmpresaAEvaluar(Empresa unaEmpresa) {
		RepositorioEmpresasAEvaluar.empresasAEvaluar.remove(unaEmpresa);
		
	}
	public static void setPeriodosAEvaluar(){
		RepositorioEmpresasAEvaluar.periodosAEvaluar.addAll(getPeriodosAEvaluar());
	}
	
	public static List<String> getPeriodosAEvaluar() {
		return periodosAEvaluar;
	}

	public static void agregarPeriodoAEvaluar(String periodoAEvaluar) {
		RepositorioEmpresasAEvaluar.periodosAEvaluar.add(periodoAEvaluar);
	}
	public static void vaciarListaDePeriodosAEvaluar() {
		RepositorioEmpresasAEvaluar.periodosAEvaluar.removeAll(periodosAEvaluar);
	}

	public static void eliminarPeriodoaAEvaluar(String unPeriodo) {
		RepositorioEmpresasAEvaluar.periodosAEvaluar.remove(unPeriodo);
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
