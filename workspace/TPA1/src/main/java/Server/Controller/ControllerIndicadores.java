package Server.Controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import excepciones.AccountNotFoundException;
import excepciones.DatoRepetidoException;
import excepciones.FormulaIndicadorVacioError;
import excepciones.FormulaIndicadorNotValidException;
import excepciones.NombreIndicadorVacioError;
import model.Empresa;
import model.Indicador;
import model.Usuario;
import repositorios.RepositorioEmpresas;
import repositorios.RepositorioIndicadores;
import repositorios.RepositorioUsuarios;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class ControllerIndicadores {
	
	private static RepositorioUsuarios repositorio_usuarios=new RepositorioUsuarios();
	private static RepositorioIndicadores repositorio_indicadores=new RepositorioIndicadores();
	private static RepositorioEmpresas repositorio_empresas=new RepositorioEmpresas();
	private static String error;
	
	public static void setErrorMessage(String errorMessage){
		error = errorMessage;
	}
	
	
	
	public static ModelAndView consultarIndicadores(Request request,Response response) {
		List<Indicador> indicadores = getIndicadoresUsuarioActual(request);

		return new ModelAndView(indicadores,"indicadores.hbs");
	}

	
	public static ModelAndView agregarIndicador(Request request,Response response) {
		
		error = null;
		
		String nombreIndicador = request.queryParams("nombre");
		String formulaIndicador = request.queryParams("formula");
		String username=request.session().attribute("usuario");

		Indicador indicador;
		Map<String, Object> diccionario = new HashMap<String, Object>();
		
		Usuario user; 
		
		if(!(nombreIndicador == null && formulaIndicador == null )){
			user = repositorio_usuarios.obtenerUsuario(username);
			indicador = new Indicador(nombreIndicador,formulaIndicador,user);
		
		try {
			repositorio_indicadores.generarIndicador(indicador);
		} catch (NombreIndicadorVacioError  e) {
			setErrorMessage("Nombre de indicador ya existente");
			}
		catch (DatoRepetidoException e) {
			setErrorMessage("Nombre de indicador ya existente");
			}
		catch (FormulaIndicadorNotValidException | FormulaIndicadorVacioError e) {
			setErrorMessage("Formula invalida");
			}

		}
		List<Indicador> indicadores = getIndicadoresUsuarioActual(request);

		diccionario.put("indicadores", indicadores);
		diccionario.put("error",error);

		return new ModelAndView(diccionario,"agregarIndicador.hbs");
	}
	
	public static ModelAndView eliminarIndicador(Request request,Response response) {
		String nombreIndicador = request.queryParams("indicador");
		
		Usuario user = repositorio_usuarios.obtenerUsuario(request.session().attribute("usuario"));
		List<Indicador> indicadores = getIndicadoresUsuarioActual(request);
	
		if(nombreIndicador != null)	repositorio_indicadores.eliminarIndicador(nombreIndicador,user);
		
		indicadores = getIndicadoresUsuarioActual(request);

		return new ModelAndView(indicadores,"eliminarIndicador.hbs");
	}
	

	public static ModelAndView evaluarIndicador(Request request,Response response) {
		
		error = null;
		
		List<Indicador> indicadores = getIndicadoresUsuarioActual(request);
		
		Map<String, Object> diccionario = new HashMap<String, Object>();
		Map<String, Object> diccionarioPeriodos = repositorio_empresas.getHashMapPeriodos();
		
		int resultado = 0;
		
		String nombreIndicador = request.queryParams("indicador");
		String nombreEmpresa = request.queryParams("empresa");
		String periodo = request.queryParams("periodo");
		
		if(!(nombreIndicador == null || nombreEmpresa == null)){
			if(repositorio_indicadores.getIndicadorPrecalculado(nombreIndicador,nombreEmpresa,periodo) == null){
		
		
				Indicador indicador = repositorio_indicadores.getIndicador(nombreIndicador, request.session().attribute("usuario"));
				Empresa empresa = repositorio_empresas.getEmpresa(nombreEmpresa);
		
			
			
				try{
					indicador.construirOperadorRaiz(empresa,periodo);
					resultado = indicador.calcular();
				
					repositorio_indicadores.generarPrecalculado(indicador,empresa,periodo);
				
				
				} catch (NumberFormatException e) {
					setErrorMessage("Fallo calculo del indicador, Indicador en formula inexistente/eliminado");
				} catch (NullPointerException e){
					setErrorMessage("Debe seleccionar un indicador y una empresa");
				}
			
			} else resultado = repositorio_indicadores.getResultadoPrecalculado(nombreIndicador, nombreEmpresa, periodo);
		} 
		
		diccionario.put("indicadores", indicadores);
		diccionario.put("periodos", diccionarioPeriodos);
		diccionario.put("resultado", String.valueOf(resultado));
		diccionario.put("error",error);
		
		return new ModelAndView(diccionario,"evaluarIndicador.hbs");
	}


	private static List<Indicador> getIndicadoresUsuarioActual(Request request) {
		
		List<Indicador> indicadores = new LinkedList<Indicador>();
		indicadores = repositorio_indicadores.getIndicadoresPorUsuario(request.session().attribute("usuario"));
		return indicadores;
	}
	
	
	
	
	
}
