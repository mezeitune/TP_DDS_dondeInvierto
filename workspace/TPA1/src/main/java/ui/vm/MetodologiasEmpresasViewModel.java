package ui.vm;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.uqbar.commons.model.ObservableUtils;
import org.uqbar.commons.utils.Observable;

import parserArchivos.CSVToEmpresas;
import parserArchivos.ParserJsonAObjetosJava;
import parserIndicadores.ParserFormulaToIndicador;
import repository.EmpresasAEvaluarRepository;
import repository.EmpresasRepository;
import repository.MetodologiasRepository;
import usuario.Empresa;
import usuario.Metodologia;
@Observable
public class MetodologiasEmpresasViewModel {
	
	private List<Metodologia> metodologias;
	private Metodologia metodologia;

	
	private List<Empresa> empresasQueConvieneInvertir = new LinkedList<>();
	private List<Empresa> empresasQueNoConvieneInvertir = new LinkedList<>();

	private List<List<Empresa>> empresasAEvaluarMetodologia; 
	
	
	private List<Empresa> empresas = new LinkedList<Empresa>();
	private List<String> periodos = new LinkedList<String>();
	
	public MetodologiasEmpresasViewModel() {
		ParserJsonAObjetosJava parserEmpIndicador = new ParserJsonAObjetosJava("metodologias.json");
		
		MetodologiasRepository.deleteMetodologiasDefinidasPorElUsuario();
		MetodologiasRepository.setMetodologiasDefinidasPorElUsuario(parserEmpIndicador.getMetodologiasDelArchivo());
		MetodologiasRepository.cargarMetodologiasPredefinidos();
		
		
			
			this.setMetodologias();
			this.setMetodologia(metodologias.get(0));

			List<Empresa> conjuntoDeEmpresasAEvaluar = new LinkedList<Empresa>();
		Empresa empresa1 = new Empresa("Facebook");
		Empresa empresa2 = new Empresa("Apple");
		Empresa empresa3 = new Empresa("Oracle");
		Empresa empresa4 = new Empresa("Genius");
		Empresa empresa5 = new Empresa("IBM");
		conjuntoDeEmpresasAEvaluar.add(empresa1);
		conjuntoDeEmpresasAEvaluar.add(empresa2);
		conjuntoDeEmpresasAEvaluar.add(empresa3);
		conjuntoDeEmpresasAEvaluar.add(empresa4);
		conjuntoDeEmpresasAEvaluar.add(empresa5);
		metodologia.setEmpresasAEvaluar(conjuntoDeEmpresasAEvaluar);
		empresas= metodologia.getConjuntoDeEmpresasAEvaluar();
		System.out.println(empresas);
		
	}
	
	
	public void setMetodologias(){
		metodologias = MetodologiasRepository.getMetodologias();
	}

	public List<Metodologia> getMetodologias(){
		return this.metodologias;
	}
	public void setMetodologia(Metodologia metodologiaSeleccionada){
		if (metodologia==null){
			this.metodologia=metodologias.get(0);
			
		}
		
		this.metodologia = metodologiaSeleccionada;
		getMetodologia().setEmpresasAEvaluar(getEmpresas());
		
		List<String> periodosHardcodeado = new LinkedList<String>();
		periodosHardcodeado.add("2016");
		
		empresasQueConvieneInvertir = getMetodologia().evaluar(periodosHardcodeado).get(0);
		empresasQueNoConvieneInvertir = getMetodologia().evaluar(periodosHardcodeado).get(1);
		//this.empresasRankeadas = this.metodologia.evaluar(this.getPeriodos());
		ObservableUtils.firePropertyChanged(this, "empresasQueConvieneInvertir");
		//this.setPeriodo(null);
	
	}
	
	public void evaluar(){
		
	}
	
	public Metodologia getMetodologia(){
		return this.metodologia;
	}
	
	public void setEmpresasAEvaluarMetodologia(){
		empresasAEvaluarMetodologia = this.metodologia.evaluar(periodos);
	}
	
	public void setEmpresas(List<Empresa> empresas){
		this.empresas = empresas;
	}
	public List<Empresa> getEmpresas(){
		return EmpresasAEvaluarRepository.getEmpresasAEvaluar();
	}
	
	public void setPeriodos(){
		this.periodos = EmpresasAEvaluarRepository.getPeriodosAEvaluar();
		
	}
	public List<String> getPeriodos(){
		return EmpresasAEvaluarRepository.getPeriodosAEvaluar();
	}
	
	
	public List<Empresa> getEmpresasQueConvieneInvertir() {
		return empresasQueConvieneInvertir;
	}
	public void setEmpresasQueConvieneInvertir(){
		empresasQueConvieneInvertir= this.metodologia.obtenerEmpresasInvertibles(empresasAEvaluarMetodologia);
		System.out.println(empresasQueConvieneInvertir);
	}
	
	public List<Empresa> getEmpresasQueNoConvieneInvertir() {
		return empresasQueNoConvieneInvertir;
		
	}
	public void setEmpresasQueNoConvieneInvertir(){
		empresasQueNoConvieneInvertir= this.metodologia.obtenerEmpresasNoInvertibles(empresasQueConvieneInvertir);
		System.out.println(empresasQueNoConvieneInvertir);
	}

	public void vaciarListaEmpresas(){
		EmpresasAEvaluarRepository.vaciarListaDeEmpresasAEvaluar();
		ObservableUtils.firePropertyChanged(this, "empresas");
	}
	
	public void autocompletarListaEmpresasAEvaluar(){
		CSVToEmpresas parser = new CSVToEmpresas(EmpresasRepository.getArchivo());
		EmpresasAEvaluarRepository.setEmpresasAEvaluar(parser.csvFileToEmpresas());
		ObservableUtils.firePropertyChanged(this, "empresas");	
	}
	
	
}
