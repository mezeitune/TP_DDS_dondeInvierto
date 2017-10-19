package Server;


import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import excepciones.EmpresasIsEmptyException;
import excepciones.MetodologiaNotFoundException;
import excepciones.PeriodosIsEmptyException;
import model.Empresa;
import model.Indicador;
import model.Metodologia;
import repositorios.EmpresasAEvaluarRepository;
import repositorios.EmpresasRepository;
import repositorios.MetodologiasRepository;

import repositorios.UsuariosRepository;

public class ControllerMetodologias {

private static MetodologiasRepository repositorio_metodologias=new MetodologiasRepository();
private static EmpresasRepository repositorio_empresas=new EmpresasRepository();
private static EmpresasAEvaluarRepository repositorio_empresas_evaluar=new EmpresasAEvaluarRepository();


	public static ModelAndView home(Request request,Response response) {
		
		return new ModelAndView(null, "home/home.hbs");

	}

	public static ModelAndView consultarMetodologias(Request request,Response response) {
		
	
		List<Metodologia> metodologias = new LinkedList<Metodologia>();
		metodologias= repositorio_metodologias.getMetodologias();
		
		
		return new ModelAndView(metodologias,"metodologias.hbs");
	}

		
	public static ModelAndView evaluarMetodologia(Request request, Response responce){
		List <Empresa> empresasInvertibles = new LinkedList<>();
		List <Empresa> empresasNoInvertibles = new LinkedList<>();
	
		
		Map<String, Object> diccionario = new HashMap<String, Object>();
		
		String nombreMetodologia = request.queryParams("metodologia");
		String nombreEmpresa= request.queryParams("empresas");
		String periodo = request.queryParams("periodos");
		
		
		
		if(nombreMetodologia != null && nombreEmpresa!=null && periodo!=null){
			
			Metodologia metodologia = repositorio_metodologias.getMetodologia(nombreMetodologia);
			
			List<List<Empresa>> resultado = metodologia.evaluar(EmpresasAEvaluarRepository.getEmpresasAEvaluar(),EmpresasAEvaluarRepository.getPeriodosAEvaluar());
			
			empresasInvertibles = resultado.get(0);
			empresasNoInvertibles =resultado.get(1);
		
		}

		diccionario.put("empresasInvertibles", empresasInvertibles);
		diccionario.put("empresasNoInvertibles", empresasNoInvertibles);
		return new ModelAndView(diccionario,"evaluarMetodologia.hbs");
	}
	
	
	
	
	public static ModelAndView setDatosParaEvaluar(Request request, Response responce){
		List<Metodologia> metodologias = new LinkedList<Metodologia>();
		List<Empresa> empresas =  repositorio_empresas.getEmpresas();
		
		metodologias = repositorio_metodologias.getMetodologias();
		
		Map<String, Object> diccionario = new HashMap<String, Object>();
		Map<String, Object> diccionarioPeriodos = repositorio_empresas.getHashMapPeriodos();
		
		
		
		String nombreMetodologia = request.queryParams("metodologia");
		String nombreEmpresa= request.queryParams("empresas");
		String periodo = request.queryParams("periodos");
		
		
		if(nombreEmpresa!=null){
			EmpresasAEvaluarRepository.agregarEmpresaAEvaluar(repositorio_empresas.getEmpresa(nombreEmpresa));
			
		}
		if( periodo!=null){
			EmpresasAEvaluarRepository.agregarPeriodoAEvaluar(periodo);	
			
		}
		diccionario.put("empresasAEvaluar", EmpresasAEvaluarRepository.getEmpresasAEvaluar());
		diccionario.put("metodologias",metodologias);
		diccionario.put("periodos", diccionarioPeriodos);
		return new ModelAndView(diccionario,"setDatosParaEvaluarMetodologia.hbs");
	}
		
		
		
	
}
