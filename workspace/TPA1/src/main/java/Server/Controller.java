package Server;

import java.util.HashMap;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class Controller {

public static String saludar(Request request, Response response) {
		
		String nombre = request.params("nombre");
		return "Hola" + nombre;
		
	}

}
