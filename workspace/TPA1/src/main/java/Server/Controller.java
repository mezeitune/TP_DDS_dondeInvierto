package Server;


import spark.ModelAndView;
import spark.Request;
import spark.Response;
import ui.vm.CargarMetodologiaViewModel;
import usuario.Cuenta;
import usuario.Empresa;
import usuario.Indicador;
import usuario.Metodologia;
import utilities.JPAUtility;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.uqbar.commons.model.ObservableUtils;

import com.github.jknack.handlebars.Handlebars;

import condiciones.Condicion;
import excepciones.DatoRepetidoException;
import excepciones.FormulaIndicadorNotFound;
import excepciones.FormulaIndicadorNotValidException;
import excepciones.NombreIndicadorNotFound;
import mocks.EmpresasMock;
import parserIndicadores.ParserFormulaIndicador;
import repositorios.CondicionesSeleccionadasRepository;
import repositorios.EmpresasRepository;
import repositorios.IndicadoresRepository;
import repositorios.MetodologiasRepository;

import repositorios.UsuariosRepository;

import utilities.JPAUtility;


public class Controller {

	private static final String SESSION_NAME = "username";
	private static JPAUtility jpa1=JPAUtility.getInstance();
	private static EntityManager entityManager1 = jpa1.getEntityManager();
	private static UsuariosRepository usRepo=new UsuariosRepository(entityManager1);
	
	//----------------------------------------------------------------------------------------------------
	private JPAUtility jpa=JPAUtility.getInstance();
	private EntityManager entityManager = this.jpa1.getEntityManager();
	private IndicadoresRepository repo = new IndicadoresRepository(this.entityManager1);
	
	
	public static ModelAndView consultarIndicadores(Request request,Response response) {
		
		Controller controlador = new Controller();//para poder usar referencias no estaticas
	
		List<Indicador> indicadores = new LinkedList<Indicador>();
		indicadores = controlador.getIndicadores();
		
		return new ModelAndView(indicadores,"indicadores.hbs");
	}
	
	public List<Indicador> getIndicadores(){
		return repo.getIndicadores();
	}
	
	
	public static ModelAndView agregarIndicador(Request request,Response response) {
		
		Controller controlador = new Controller();//para poder usar referencias no estaticas
	
		
		String nombreIndicador = request.queryParams("nombre");
		String formulaIndicador = request.queryParams("formula");
	
		try {
			controlador.repo.generarIndicador(nombreIndicador, formulaIndicador);
		} catch (NombreIndicadorNotFound | DatoRepetidoException | FormulaIndicadorNotValidException | FormulaIndicadorNotFound e) {
			//TODO [Que hacer aca]
		}
		
		
		List<Indicador> indicadores = new LinkedList<Indicador>();
		indicadores = controlador.getIndicadores();
		
		return new ModelAndView(indicadores,"agregarIndicador.hbs");
	}
	
	public static ModelAndView eliminarIndicador(Request request,Response response) {
		
		Controller controlador = new Controller();//para poder usar referencias no estaticas
	
		
		String nombreIndicador = request.queryParams("nombre");
	
		if(nombreIndicador != null)	controlador.repo.eliminarIndicadorDeLaBDD(nombreIndicador);
		
		List<Indicador> indicadores = new LinkedList<Indicador>();
		indicadores = controlador.getIndicadores();
		
		return new ModelAndView(indicadores,"eliminarIndicador.hbs");
	}
	

	public static ModelAndView evaluarIndicador(Request request,Response response) {
		
		Controller controlador = new Controller();//para poder usar referencias no estaticas
		
		List<Indicador> indicadores = new LinkedList<Indicador>();
		indicadores = controlador.getIndicadores();
		
		Map<String, Object> diccionario = new HashMap<String, Object>();
		Map<String, Object> diccionarioPeriodos = EmpresasRepository.getHashMapPeriodos();
		
		int resultado = 0;
		
		String nombreIndicador = request.queryParams("indicador");
		String nombreEmpresa = request.queryParams("empresa");
		String periodo = request.queryParams("periodo");
		
		
		if(nombreIndicador != null && nombreEmpresa != null){
		
			Indicador indicador = controlador.repo.getIndicador(nombreIndicador);
			Empresa empresa = EmpresasRepository.getEmpresa(nombreEmpresa);
		
			indicador.construirOperadorRaiz(empresa,periodo);
			resultado = indicador.calcular();
		}

		diccionario.put("indicadores", indicadores);
		diccionario.put("periodos", diccionarioPeriodos);
		diccionario.put("resultado", String.valueOf(resultado));
		

		return new ModelAndView(diccionario,"evaluarIndicador.hbs");
	}
	
	
	
	

	//----------------------------------------------------------------------------------------------------
	public static ModelAndView crearSessionDeLogin(Request request,Response response) {
		
		Controller controlador = new Controller();//para poder usar referencias no estaticas
	
		
		
		String username = request.queryParams("usuario");
		String password = request.queryParams("contrasena");
        if (username != null && usRepo.usuarioExistente(username)) {
        	if(usRepo.logeoCorrecto(username, password)){
        		request.session().attribute(SESSION_NAME, username);
        		response.redirect("/empresas");
        	}
        }
        
        response.redirect("/login.html");//Ver si login realmente deberia ser estatico , por que hay que mandar un mensaje de error
        
        
        
		
		return null;

		
	}
	
	public static ModelAndView eliminarSessionDeLogin(Request request,Response response) {
		
		Controller controlador = new Controller();//para poder usar referencias no estaticas
	
        request.session().removeAttribute(SESSION_NAME);
        response.redirect("/dondeInvierto.html");
		
		return null;
		

		//--------------------------------------------------------------------------------------------------
		
	}
	public static ModelAndView consultarMetodologias(Request request,Response response) {
		
		Controller controlador = new Controller();//para poder usar referencias no estaticas
	
		List<Metodologia> metodologias = new LinkedList<Metodologia>();
		metodologias= controlador.getMetodologias();
		
		
		return new ModelAndView(metodologias,"metodologias.hbs");
	}
	private MetodologiasRepository repoMetodologias=new MetodologiasRepository(this.entityManager);
	
	public List<Metodologia> getMetodologias(){
		
		return repoMetodologias.getMetodologias();
		
	}
		
	public static ModelAndView evaluarMetodologia(Request request, Response responce){
		Controller controlador = new Controller();//para poder usar referencias no estaticas
		
		Map<String, Object> parametros = new HashMap<String, Object>();

		String metSeleccionadaNombre = request.queryParams("metodologia");
		
		List<Empresa> empresas = new LinkedList<Empresa>();
		
		List <Metodologia> metodologias = controlador.getMetodologias();
		
		Metodologia metodologia = metodologias.stream().filter(e -> e.getNombre().equals(metSeleccionadaNombre)).collect(Collectors.toList()).get(0);
		//parametros.put("metSeleccionada", metodologia);
		
		
		ModelAndView mv=new ModelAndView(metodologia, "evaluarMetodologia.hbs");
		
		return mv;
		
		
	}
	

}
