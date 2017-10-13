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

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import repositorios.EmpresasRepository;
import repositorios.MetodologiasRepository;

import repositorios.UsuariosRepository;


public class Controller {


	private static UsuariosRepository repositorio_usuarios=new UsuariosRepository(JPAUtility.getInstance().getEntityManager());
	private static EmpresasRepository repositorio_empresas=new EmpresasRepository(JPAUtility.getInstance().getEntityManager());
	private static MetodologiasRepository repositorio_metodologias=new MetodologiasRepository(JPAUtility.getInstance().getEntityManager());
	
	public static ModelAndView home(Request request,Response response) {
		
		return new ModelAndView(null, "home/home.hbs");

	}

	public static ModelAndView consultarMetodologias(Request request,Response response) {
		
	
		List<Metodologia> metodologias = new LinkedList<Metodologia>();
		metodologias= repositorio_metodologias.getMetodologias();
		
		
		return new ModelAndView(metodologias,"metodologias.hbs");
	}

		
	public static ModelAndView evaluarMetodologia(Request request, Response responce){
		
		Map<String, Object> parametros = new HashMap<String, Object>();

		String metSeleccionadaNombre = request.queryParams("metodologia");
		
		List<Empresa> empresas = new LinkedList<Empresa>();
		
		List <Metodologia> metodologias = repositorio_metodologias.getMetodologias();
		
		Metodologia metodologia = metodologias.stream().filter(e -> e.getNombre().equals(metSeleccionadaNombre)).collect(Collectors.toList()).get(0);
		//parametros.put("metSeleccionada", metodologia);
		
		
		ModelAndView mv=new ModelAndView(metodologia, "evaluarMetodologia.hbs");
		
		return mv;
		
		
	}
	
	
	public static ModelAndView crearSessionDeLogin(Request request,Response response) {
		
		String username = request.queryParams("usuario");
		String password = request.queryParams("contrasena");
        if (username != null && repositorio_usuarios.usuarioExistente(username)) {
        	if(repositorio_usuarios.logeoCorrecto(username, password)){
        		request.session().attribute("usuario", username);
        		response.redirect("/empresas");
        	}
        }
        
        response.redirect("/login.html");//Ver si login realmente deberia ser estatico , por que hay que mandar un mensaje de error
        
		return null;

		
	}
	
	public static ModelAndView eliminarSessionDeLogin(Request request,Response response) {
        request.session().removeAttribute("usuario");
        response.redirect("/dondeInvierto.html");
		
		return null;
		

		//--------------------------------------------------------------------------------------------------
		
	}

}
