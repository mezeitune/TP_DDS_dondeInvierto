package Server.Controller;


import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import model.Empresa;
import model.Metodologia;
import repositorios.RepositorioEmpresasAEvaluar;
import repositorios.RepositorioEmpresas;
import repositorios.RepositorioMetodologias;


public class ControllerMetodologias {

private static RepositorioMetodologias repositorio_metodologias=new RepositorioMetodologias();
private static RepositorioEmpresas repositorio_empresas=new RepositorioEmpresas();
private static RepositorioEmpresasAEvaluar repositorio_empresas_evaluar=new RepositorioEmpresasAEvaluar();
private static boolean periodoIngresado;
private static String error;
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
		
		if(nombreMetodologia != null ){
			
			Metodologia metodologia = repositorio_metodologias.getMetodologia(nombreMetodologia);
			
			List<List<Empresa>> resultado = metodologia.evaluar(RepositorioEmpresasAEvaluar.getEmpresasAEvaluar(),RepositorioEmpresasAEvaluar.getPeriodosAEvaluar());
			
			empresasInvertibles = resultado.get(0);
			empresasNoInvertibles = resultado.get(1);
		
		}
		System.out.println(nombreMetodologia+empresasInvertibles.size());
		diccionario.put("empresasInvertibles", empresasInvertibles);
		diccionario.put("empresasNoInvertibles", empresasNoInvertibles);
		return new ModelAndView(diccionario,"evaluarMetodologia.hbs");
	}
	
	public static ModelAndView setDatosParaEvaluar(Request request, Response responce){
	
		Map<String, Object> diccionarioPeriodos = repositorio_empresas.getHashMapPeriodos();
		
		Map<String, Object> diccionario = new HashMap<String, Object>();
		diccionario.put("metodologias",repositorio_metodologias.getMetodologias());
		diccionario.put("empresasAEvaluar",RepositorioEmpresasAEvaluar.getEmpresasAEvaluar());
		diccionario.put("empresas",repositorio_empresas.getEmpresas());
		diccionario.put("periodosAEvaluar",RepositorioEmpresasAEvaluar.getPeriodosAEvaluar());
		diccionario.put("periodos",diccionarioPeriodos);
		return new ModelAndView(diccionario,"setDatosParaEvaluarMetodologia.hbs");
		
	}
		
	public static ModelAndView guardarEmpresaParaEvaluar(Request request, Response responce){
		
		String nombreEmpresa= request.queryParams("empresa");
		Map<String, Object> diccionario = new HashMap<String, Object>();
		Map<String, Object> diccionarioPeriodos = new HashMap<String, Object>();
		if(nombreEmpresa!=null && (!(RepositorioEmpresasAEvaluar.esEmpresaRepetida(repositorio_empresas.getEmpresa(nombreEmpresa))))){
			 diccionarioPeriodos= repositorio_empresas.getHashMapPeriodos();
			
			RepositorioEmpresasAEvaluar.agregarEmpresaAEvaluar(repositorio_empresas.getEmpresa(nombreEmpresa));
		}else {
			error="Empresa Ya Ingresada";
		}
		
		
		diccionario.put("metodologias",repositorio_metodologias.getMetodologias());
		diccionario.put("empresasAEvaluar",RepositorioEmpresasAEvaluar.getEmpresasAEvaluar());
		diccionario.put("empresas",repositorio_empresas.getEmpresas());
		diccionario.put("periodosAEvaluar",RepositorioEmpresasAEvaluar.getPeriodosAEvaluar());
		diccionario.put("periodos",diccionarioPeriodos);
		diccionario.put("error",error);
		return new ModelAndView(diccionario,"setDatosParaEvaluarMetodologia.hbs");
	}
	public static ModelAndView guardarPeriodoParaEvaluar(Request request, Response responce){
		
	String periodo = request.queryParams("periodo");
	Map<String, Object> diccionarioPeriodos = repositorio_empresas.getHashMapPeriodos();
	Map<String, Object> diccionario = new HashMap<String, Object>();
	
	if( periodo!=null && periodoIngresado==false){
		RepositorioEmpresasAEvaluar.agregarPeriodoAEvaluar(periodo);	
		periodoIngresado=true;
	}else{
		error="Ya ha ingresado un periodo";
	}
	diccionario.put("metodologias",repositorio_metodologias.getMetodologias());
	diccionario.put("empresasAEvaluar",RepositorioEmpresasAEvaluar.getEmpresasAEvaluar());
	diccionario.put("empresas",repositorio_empresas.getEmpresas());
	diccionario.put("periodosAEvaluar",RepositorioEmpresasAEvaluar.getPeriodosAEvaluar());
	diccionario.put("periodos",diccionarioPeriodos);
	diccionario.put("error",error);
	return new ModelAndView(diccionario,"setDatosParaEvaluarMetodologia.hbs");
	}
}
