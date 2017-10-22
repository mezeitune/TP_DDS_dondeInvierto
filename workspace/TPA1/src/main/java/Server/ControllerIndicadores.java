package Server;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

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
	
	public static ModelAndView consultarIndicadores(Request request,Response response) {
		List<Indicador> indicadores = getIndicadoresUsuarioActual(request);

		return new ModelAndView(indicadores,"indicadores.hbs");
	}

	
	public static ModelAndView agregarIndicador(Request request,Response response) {
		
		String nombreIndicador = request.queryParams("nombre");
		String formulaIndicador = request.queryParams("formula");
		String username=request.session().attribute("usuario");

		
		Usuario user = repositorio_usuarios.obtenerUsuario(username);
		Indicador indicador = new Indicador(nombreIndicador,formulaIndicador,user);
		Map<String, Object> diccionario = new HashMap<String, Object>();
		

		try {
			repositorio_indicadores.generarIndicador(indicador/*nombreIndicador, formulaIndicador,user*/);
		} catch (NombreIndicadorVacioError  e) {diccionario.put("error","Nombre invalido, debe ingresar uno");}
		catch (DatoRepetidoException e) {diccionario.put("error","Nombre de indicador ya existente");
		} catch (FormulaIndicadorNotValidException | FormulaIndicadorVacioError e) {diccionario.put("error","Formula invalida");}

		List<Indicador> indicadores = getIndicadoresUsuarioActual(request);

		
		
		diccionario.put("indicadores", indicadores);
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
		
		List<Indicador> indicadores = getIndicadoresUsuarioActual(request);
		
		Map<String, Object> diccionario = new HashMap<String, Object>();
		Map<String, Object> diccionarioPeriodos = repositorio_empresas.getHashMapPeriodos();
		
		int resultado = 0;
		
		String nombreIndicador = request.queryParams("indicador");
		String nombreEmpresa = request.queryParams("empresa");
		String periodo = request.queryParams("periodo");
		
		
		if(nombreIndicador != null && nombreEmpresa != null){
		
			Indicador indicador = repositorio_indicadores.getIndicador(nombreIndicador, request.session().attribute("usuario"));
			Empresa empresa = repositorio_empresas.getEmpresa(nombreEmpresa);
		
			indicador.construirOperadorRaiz(empresa,periodo);
			resultado = indicador.calcular();
		}

		diccionario.put("indicadores", indicadores);
		diccionario.put("periodos", diccionarioPeriodos);
		diccionario.put("resultado", String.valueOf(resultado));
		

		return new ModelAndView(diccionario,"evaluarIndicador.hbs");
	}


	private static List<Indicador> getIndicadoresUsuarioActual(Request request) {
		
		List<Indicador> indicadores = new LinkedList<Indicador>();
		indicadores = repositorio_indicadores.getIndicadoresPorUsuario(request.session().attribute("usuario"));
		return indicadores;
	}
	
	
	
}
