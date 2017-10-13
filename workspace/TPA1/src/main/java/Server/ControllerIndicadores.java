package Server;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import excepciones.DatoRepetidoException;
import excepciones.FormulaIndicadorNotFound;
import excepciones.FormulaIndicadorNotValidException;
import excepciones.NombreIndicadorNotFound;
import repositorios.EmpresasRepository;
import repositorios.IndicadoresRepository;
import repositorios.MetodologiasRepository;
import repositorios.UsuariosRepository;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import usuario.Empresa;
import usuario.Indicador;
import utilities.JPAUtility;

public class ControllerIndicadores {
	
	private static UsuariosRepository repositorio_usuarios=new UsuariosRepository(JPAUtility.getInstance().getEntityManager());
	private static IndicadoresRepository repositorio_indicadores=new IndicadoresRepository(JPAUtility.getInstance().getEntityManager());
	private static EmpresasRepository repositorio_empresas=new EmpresasRepository(JPAUtility.getInstance().getEntityManager());
	
	public static ModelAndView consultarIndicadores(Request request,Response response) {
		List<Indicador> indicadores = new LinkedList<Indicador>();
		indicadores = repositorio_usuarios.getIndicadoresPorUsuario(request.session().attribute("usuario"));
		
		return new ModelAndView(indicadores,"indicadores.hbs");
	}

	
	public static ModelAndView agregarIndicador(Request request,Response response) {
		
		String nombreIndicador = request.queryParams("nombre");
		String formulaIndicador = request.queryParams("formula");
	
		try {
			repositorio_indicadores.generarIndicador(nombreIndicador, formulaIndicador);
		} catch (NombreIndicadorNotFound | DatoRepetidoException | FormulaIndicadorNotValidException | FormulaIndicadorNotFound e) {
			//TODO [Que hacer aca]
		}
		
		
		List<Indicador> indicadores = new LinkedList<Indicador>();
		indicadores = repositorio_indicadores.getIndicadores();
		
		return new ModelAndView(indicadores,"agregarIndicador.hbs");
	}
	
	public static ModelAndView eliminarIndicador(Request request,Response response) {
		String nombreIndicador = request.queryParams("nombre");
	
		if(nombreIndicador != null)	repositorio_indicadores.eliminarIndicadorDeLaBDD(nombreIndicador);
		
		List<Indicador> indicadores = new LinkedList<Indicador>();
		indicadores = repositorio_indicadores.getIndicadores();
		
		return new ModelAndView(indicadores,"eliminarIndicador.hbs");
	}
	

	public static ModelAndView evaluarIndicador(Request request,Response response) {
		
		List<Indicador> indicadores = new LinkedList<Indicador>();
		indicadores = repositorio_indicadores.getIndicadores();
		
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
