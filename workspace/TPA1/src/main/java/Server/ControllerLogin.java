package Server;


import spark.ModelAndView;
import spark.Request;
import spark.Response;


import repositorios.UsuariosRepository;


public class ControllerLogin {


	private static UsuariosRepository repositorio_usuarios=new UsuariosRepository();
	
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
		
	}

}
