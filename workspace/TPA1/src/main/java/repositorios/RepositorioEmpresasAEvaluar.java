package repositorios;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import model.Empresa;

public class RepositorioEmpresasAEvaluar extends RepositorioDBRelational<Empresa> {
	
	public  List<Empresa> empresasAEvaluar = new ArrayList<>(); 
	public  List<String> periodosAEvaluar = new LinkedList<>(); 
	public RepositorioEmpresas repositorio_empresas = new RepositorioEmpresas();
	
	public RepositorioEmpresasAEvaluar(){
	}
	
	public  List<Empresa> getEmpresasAEvaluar() {
		return this.empresasAEvaluar;
	}

	public  void repositorio_empresas_evaluar(Empresa empresaAEvaluar) {
			this.empresasAEvaluar.add(empresaAEvaluar);
	}
	public  void vaciarListaDeEmpresasAEvaluar() {
		this.empresasAEvaluar.removeAll(empresasAEvaluar);
	}

	public  void eliminarEmpresaAEvaluar(Empresa unaEmpresa) {
		this.empresasAEvaluar.remove(unaEmpresa);
		
	}
	public void setPeriodosAEvaluar(){
		this.periodosAEvaluar.addAll(this.getPeriodosAEvaluar());
	}
	
	public List<String> getPeriodosAEvaluar() {
		return periodosAEvaluar;
	}

	public void agregarPeriodoAEvaluar(String periodoAEvaluar) {
		this.periodosAEvaluar.add(periodoAEvaluar);
	}
	public  void vaciarListaDePeriodosAEvaluar() {
		this.periodosAEvaluar.removeAll(this.periodosAEvaluar);
	}

	public  void eliminarPeriodoaAEvaluar(String unPeriodo) {
		this.periodosAEvaluar.remove(unPeriodo);
	}

	
	public  boolean esPeriodoRepetido(String periodo){
		return periodosAEvaluar.stream().anyMatch(unPeriodo -> unPeriodo.equals(periodo));
	}
	
	public  boolean esPeriodoInexistente(String periodo){
		return !periodosAEvaluar.stream().anyMatch(unPeriodo -> unPeriodo.equals(periodo));
	}

	public void setEmpresasAEvaluar(List<Empresa> empresas) {
		this.empresasAEvaluar = empresas;
	}

	public  boolean esEmpresaRepetida(Empresa empresa) {
		return empresasAEvaluar.stream().anyMatch(empresaCargada -> empresaCargada.getNombre().equals(empresa.getNombre()));
	}

	public void cargarTodasLasEmpresas() {
		this.setEmpresasAEvaluar(repositorio_empresas.getEmpresas());
	}

	public void agregarEmpresaAEvaluar(Empresa empresa) {
		this.empresasAEvaluar.add(empresa);
		
	}
	
}
