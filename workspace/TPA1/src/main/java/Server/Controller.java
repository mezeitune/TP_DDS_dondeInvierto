package Server;


import spark.ModelAndView;
import spark.Request;
import spark.Response;
import usuario.Cuenta;
import usuario.Empresa;
import usuario.Indicador;
import utilities.JPAUtility;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.uqbar.commons.model.ObservableUtils;

import excepciones.DatoRepetidoException;
import excepciones.FormulaIndicadorNotFound;
import excepciones.FormulaIndicadorNotValidException;
import excepciones.NombreIndicadorNotFound;
import mocks.EmpresasMock;
import parserIndicadores.ParserFormulaIndicador;
import repositorios.EmpresasRepository;
import repositorios.IndicadoresRepository;

public class Controller {

	 private static final String SESSION_NAME = "username";
		
	public static String saludar(Request request, Response response) {
			
			String nombre = request.params("nombre");
			return "Hola" + nombre;
			
		}
	
	public static ModelAndView consultarEmpresas(Request request, Response response) {
		List<Empresa> empresas = new LinkedList<Empresa>();
		//List<String> periodos = new LinkedList<String>();
		//empresas = EmpresasRepository.getEmpresas();
		//HashMap<Object,Object> viewModel = new HashMap <>();
		//empresas.forEach(empresa -> viewModel.put(empresa.getNombre(), Integer.toString(empresa.getCuentas().size())));
		//List<List> viewmodel = new LinkedList<List>();
		empresas = EmpresasRepository.getEmpresas();
		//periodos = EmpresasRepository.
		//viewmodel.add(empresas);
		//viewmodel.add
		
		return new ModelAndView(empresas, "empresas.hbs");
	}
	
	public static ModelAndView mostrarCuentas(Request request,Response response) {
		List<Cuenta> cuentas = new LinkedList<Cuenta>();
		List<Empresa> empresas = new LinkedList<Empresa>();
		List<List> viewModel = new LinkedList<List>();
		
		String nombreEmpresa = request.queryParams("empresa");
		String periodo = request.queryParams("periodo");
		
		empresas = EmpresasRepository.getEmpresas();
		Empresa empresa = empresas.stream().filter(e -> e.getNombre().equals(nombreEmpresa)).collect(Collectors.toList()).get(0);
		
		if(periodo.isEmpty()) cuentas = empresa.getCuentas();
		else cuentas = empresa.getCuentasPorPeriodo(periodo);
		
		viewModel.add(empresas);
		viewModel.add(cuentas);
		return new ModelAndView(viewModel,"cuentas.hbs");
	}
	
	private JPAUtility jpa=JPAUtility.getInstance();
	private EntityManager entityManager = this.jpa.getEntityManager();
	private IndicadoresRepository repo = new IndicadoresRepository(this.entityManager);
	
	
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
	public static ModelAndView crearSessionDeLogin(Request request,Response response) {
		
		Controller controlador = new Controller();//para poder usar referencias no estaticas
	
        String name = request.queryParams("usuario");
        if (name != null) {
            request.session().attribute(SESSION_NAME, name);
        }
        
        response.redirect("/dondeInvierto.html");
		
		return null;


		
	}
	
	public static void eliminarSessionDeLogin(Request request,Response response) {
		
		Controller controlador = new Controller();//para poder usar referencias no estaticas
	
        request.session().removeAttribute(SESSION_NAME);
        response.redirect("/dondeInvierto.html");


		
	}


}
