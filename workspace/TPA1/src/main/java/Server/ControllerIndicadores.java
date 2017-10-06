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
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import usuario.Empresa;
import usuario.Indicador;

public class ControllerIndicadores {
	public static ModelAndView consultarIndicadores(Request request,Response response) {
		List<Indicador> indicadores = new LinkedList<Indicador>();
		indicadores = IndicadoresRepository.getIndicadores();
		
		return new ModelAndView(indicadores,"indicadores.hbs");
	}

	
	public static ModelAndView agregarIndicador(Request request,Response response) {
		
		String nombreIndicador = request.queryParams("nombre");
		String formulaIndicador = request.queryParams("formula");
	
		try {
			IndicadoresRepository.generarIndicador(nombreIndicador, formulaIndicador);
		} catch (NombreIndicadorNotFound | DatoRepetidoException | FormulaIndicadorNotValidException | FormulaIndicadorNotFound e) {
			//TODO [Que hacer aca]
		}
		
		
		List<Indicador> indicadores = new LinkedList<Indicador>();
		indicadores = IndicadoresRepository.getIndicadores();
		
		return new ModelAndView(indicadores,"agregarIndicador.hbs");
	}
	
	public static ModelAndView eliminarIndicador(Request request,Response response) {
		String nombreIndicador = request.queryParams("nombre");
	
		if(nombreIndicador != null)	IndicadoresRepository.eliminarIndicadorDeLaBDD(nombreIndicador);
		
		List<Indicador> indicadores = new LinkedList<Indicador>();
		indicadores = IndicadoresRepository.getIndicadores();
		
		return new ModelAndView(indicadores,"eliminarIndicador.hbs");
	}
	

	public static ModelAndView evaluarIndicador(Request request,Response response) {
		
		List<Indicador> indicadores = new LinkedList<Indicador>();
		indicadores = IndicadoresRepository.getIndicadores();
		
		Map<String, Object> diccionario = new HashMap<String, Object>();
		Map<String, Object> diccionarioPeriodos = EmpresasRepository.getHashMapPeriodos();
		
		int resultado = 0;
		
		String nombreIndicador = request.queryParams("indicador");
		String nombreEmpresa = request.queryParams("empresa");
		String periodo = request.queryParams("periodo");
		
		
		if(nombreIndicador != null && nombreEmpresa != null){
		
			Indicador indicador = IndicadoresRepository.getIndicador(nombreIndicador);
			Empresa empresa = EmpresasRepository.getEmpresa(nombreEmpresa);
		
			indicador.construirOperadorRaiz(empresa,periodo);
			resultado = indicador.calcular();
		}

		diccionario.put("indicadores", indicadores);
		diccionario.put("periodos", diccionarioPeriodos);
		diccionario.put("resultado", String.valueOf(resultado));
		

		return new ModelAndView(diccionario,"evaluarIndicador.hbs");
	}
	
	
	
}
