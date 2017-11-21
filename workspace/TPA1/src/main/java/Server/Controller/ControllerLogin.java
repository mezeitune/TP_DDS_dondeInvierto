package Server.Controller;


import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Empresa;
import repositorios.RepositorioUsuarios;


public class ControllerLogin {


	private static RepositorioUsuarios repositorio_usuarios=new RepositorioUsuarios();
	
	public static ModelAndView home(Request request,Response response) {
		return new ModelAndView(null, "home/home.hbs");
	}
	
	public static ModelAndView mostrarLogin(Request request, Response response) {
		Map<String, Object> parametros = new HashMap<String, Object>();
		
		String nada="";
		
		parametros.put("mensaje",nada);
		
		return new ModelAndView(parametros, "home/login.hbs");

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
		Map<String, Object> parametros = new HashMap<String, Object>();
		
		parametros.put("mensaje", "El usuario y la contrasena ingresados no coinciden");
	
		
		return new ModelAndView(parametros, "home/login.hbs");
	}
	
	public static ModelAndView eliminarSessionDeLogin(Request request,Response response) {
		request.session().attribute("usuario", null);
		request.session().removeAttribute("usuario");
        response.redirect("/");
		return null;
		
	}
	

}
