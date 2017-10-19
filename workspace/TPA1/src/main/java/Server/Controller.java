package Server;


import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import model.Empresa;
import model.Metodologia;
import repositorios.MetodologiasRepository;

import repositorios.UsuariosRepository;


public class Controller {


	private static UsuariosRepository repositorio_usuarios=new UsuariosRepository();
	private static MetodologiasRepository repositorio_metodologias=new MetodologiasRepository();
	
	public static ModelAndView home(Request request,Response response) {
		
		return new ModelAndView(null, "home/home.hbs");

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
