package ui.vm;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.uqbar.commons.model.ObservableUtils;
import org.uqbar.commons.utils.Observable;

import Mocks.EmpresasMock;
import parser.parserArchivos.CSVToEmpresas;
import parser.parserArchivos.ParserJsonAObjetosJava;
import parserFormulaInidicador.ParserFormulaToIndicador;
import repository.ArchivoEIndicadoresUsuarioRepository;
import repository.EmpresasAEvaluarRepository;
import repository.MetodologiasRepository;
import usuario.Empresa;
import usuario.Indicador;
import usuario.Metodologia;
@Observable
public class MetodologiasEmpresasViewModel {
	private List<Empresa> empresas;
	private String nombre;
	private Empresa empresa;
	private Boolean seleccionoTodasLasEmpresas;
	private List<Metodologia> metodologias;
	private Metodologia metodologia;

	
	private List<Empresa> empresasQueConvieneInvertir = new LinkedList<>();
	private List<Empresa> empresasQueNoConvieneInvertir = new LinkedList<>();

	private List<List<Empresa>> empresasAEvaluarMetodologia; 
	
	
	private List<String> periodos;	
	private List<Empresa> empresasAEvaluar = new LinkedList<Empresa>();
	private List<String> periodosAEvaluar = new LinkedList<String>();
	
	public MetodologiasEmpresasViewModel() {
		ParserJsonAObjetosJava parserEmpIndicador = new ParserJsonAObjetosJava("metodologias.json");
		
		MetodologiasRepository.deleteMetodologiasDefinidasPorElUsuario();
		MetodologiasRepository.setMetodologiasDefinidasPorElUsuario(parserEmpIndicador.getMetodologiasDelArchivo());
		MetodologiasRepository.cargarMetodologiasPredefinidos();
		
		
		try {
			
			this.setEmpresas();
			this.setMetodologias();
			this.setMetodologia(metodologias.get(0));
		} catch (IOException e) {
			e.printStackTrace();
		}
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
		empresasAEvaluar= metodologia.getConjuntoDeEmpresasAEvaluar();
		System.out.println(empresasAEvaluar);
		this.empresa= empresas.get(0);
		ParserFormulaToIndicador.setEmpresa(empresa);
		
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
		getMetodologia().setEmpresasAEvaluar(getEmpresasAEvaluar());
		
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
	
	public void setEmpresasAEvaluar(List<Empresa> empresas){
		this.empresasAEvaluar = empresas;
	}
	public List<Empresa> getEmpresasAEvaluar(){
		return EmpresasAEvaluarRepository.getEmpresasAEvaluar();
	}
	
	public void setPeriodos(){
		this.periodos = EmpresasAEvaluarRepository.getPeriodosAEvaluar();
		
	}
	public List<String> getPeriodos(){
		return EmpresasAEvaluarRepository.getPeriodosAEvaluar();
	}
	
	public void setSeleccionoTodasLasEmpresas(boolean seleccionoTodas){
		this.seleccionoTodasLasEmpresas=seleccionoTodas;
	}
	public Boolean getSeleccionoTodasLasEmpresas(){
		return this.seleccionoTodasLasEmpresas;
	}
	public void setEmpresas() throws IOException {
		CSVToEmpresas parser = new CSVToEmpresas(ArchivoEIndicadoresUsuarioRepository.getArchivo());
		this.empresas=parser.csvFileToEmpresas();
		
	}
	
	public String getNombre(){
		return empresa.getNombre();
	}
	
	
	public void setEmpresa(Empresa empresaSeleccionada){
		if (empresa==null){
			this.empresa=empresas.get(0);
			ParserFormulaToIndicador.setEmpresa(empresa);
		}
		
		this.empresa = empresaSeleccionada;
		//this.setPeriodo(null);
		ParserFormulaToIndicador.setEmpresa(empresaSeleccionada); /*Le setea al parser la empresa seleccionada. Lo necesita para reconocer cuentas*/
		ObservableUtils.firePropertyChanged(this, "periodos");
	}
	
	public Empresa getEmpresa(){
		return this.empresa;
	}
	
	public List<Empresa> getEmpresas(){
		return this.empresas;
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


	public List<String> getPeriodosAEvaluar() {
		return periodosAEvaluar;
	}


	public void setPeriodosAEvaluar(List<String> periodosAEvaluar) {
		this.periodosAEvaluar = periodosAEvaluar;
	}
	
	
	public void vaciarListaEmpresas(){
		EmpresasAEvaluarRepository.vaciarListaDeEmpresasAEvaluar();
		ObservableUtils.firePropertyChanged(this, "empresasAEvaluar");
	}
	
	
}
