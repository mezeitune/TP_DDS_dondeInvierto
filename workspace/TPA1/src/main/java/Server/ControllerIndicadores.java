package Server;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import excepciones.DatoRepetidoException;
import excepciones.FormulaIndicadorVacioError;
import excepciones.FormulaIndicadorNotValidException;
import excepciones.NombreIndicadorVacioError;
import model.Empresa;
import model.Indicador;
import model.Usuario;
import repositorios.EmpresasRepository;
import repositorios.IndicadoresRepository;
import repositorios.UsuariosRepository;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class ControllerIndicadores {
	
	private static UsuariosRepository repositorio_usuarios=new UsuariosRepository();
	private static IndicadoresRepository repositorio_indicadores=new IndicadoresRepository();
	private static EmpresasRepository repositorio_empresas=new EmpresasRepository();
	
	public static ModelAndView consultarIndicadores(Request request,Response response) {
		List<Indicador> indicadores = new LinkedList<Indicador>();
		indicadores = repositorio_indicadores.getIndicadoresPorUsuario(request.session().attribute("usuario"));
		
		return new ModelAndView(indicadores,"indicadores.hbs");
	}

	
	public static ModelAndView agregarIndicador(Request request,Response response) {
		
		String nombreIndicador = request.queryParams("nombre");
		String formulaIndicador = request.queryParams("formula");
		String username=request.session().attribute("usuario");
	
		Usuario user=repositorio_usuarios.obtenerUsuario(username);
		try {
			repositorio_indicadores.generarIndicador(nombreIndicador, formulaIndicador,user);
		} catch (NombreIndicadorVacioError | DatoRepetidoException | FormulaIndicadorNotValidException | FormulaIndicadorVacioError e) {
			//TODO Subir las excepeciones a UI.
		}
		
		
		List<Indicador> indicadores = new LinkedList<Indicador>();
		indicadores = repositorio_indicadores.getIndicadoresPorUsuario(request.session().attribute("usuario"));
		
		return new ModelAndView(indicadores,"agregarIndicador.hbs");
	}
	
	public static ModelAndView eliminarIndicador(Request request,Response response) {
		String nombreIndicador = request.queryParams("nombre");
	
		if(nombreIndicador != null)	repositorio_indicadores.eliminarIndicador(nombreIndicador);
		
		List<Indicador> indicadores = new LinkedList<Indicador>();
		indicadores = repositorio_indicadores.getIndicadoresPorUsuario(request.session().attribute("usuario"));
		
		return new ModelAndView(indicadores,"eliminarIndicador.hbs");
	}
	

	public static ModelAndView evaluarIndicador(Request request,Response response) {
		
		List<Indicador> indicadores = new LinkedList<Indicador>();
		indicadores = repositorio_indicadores.getIndicadoresPorUsuario(request.session().attribute("usuario"));
		
		Map<String, Object> diccionario = new HashMap<String, Object>();
		Map<String, Object> diccionarioPeriodos = repositorio_empresas.getHashMapPeriodos();
		
		int resultado = 0;
		
		String nombreIndicador = request.queryParams("indicador");
		String nombreEmpresa = request.queryParams("empresa");
		String periodo = request.queryParams("periodo");
		
		
		if(nombreIndicador != null && nombreEmpresa != null){
		
			Indicador indicador = repositorio_indicadores.getIndicador(nombreIndicador);
			Empresa empresa = repositorio_empresas.getEmpresa(nombreEmpresa);
		
			indicador.construirOperadorRaiz(empresa,periodo);
			resultado = indicador.calcular();
		}

		diccionario.put("indicadores", indicadores);
		diccionario.put("periodos", diccionarioPeriodos);
		diccionario.put("resultado", String.valueOf(resultado));
		

		return new ModelAndView(diccionario,"evaluarIndicador.hbs");
	}
	
	
	
}
