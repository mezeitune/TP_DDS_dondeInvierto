package Server;

import spark.Spark;
import spark.TemplateEngine;
import utilities.HandlebarsTemplateEngineBuilder;

public class Router {

		public static void configure() {
			
			//private static final String SESSION_NAME = "username";
			
			Spark.staticFiles.location("/public"); // Archivos estaticos en Public
			TemplateEngine engine = HandlebarsTemplateEngineBuilder.create().build();
			
			
		    AuthenticationFilter authentication = new AuthenticationFilter();
		    Spark.before((req,res)->{
		    	  authentication.isAuthorized(req, res);
		    	   
		    });
			
		    Spark.get("/", Controller::home,engine);
			
			Spark.post("login/entry", Controller::crearSessionDeLogin,engine);
			Spark.get("login/clear", Controller::eliminarSessionDeLogin,engine);
			

			Spark.get("empresas", ControllerCuentas::consultarEmpresas,engine);
			Spark.get("empresas/cuentas", ControllerCuentas::mostrarCuentas,engine);
			
			Spark.get("indicadores", ControllerIndicadores::consultarIndicadores,engine);
			Spark.get("indicadores/agregar", ControllerIndicadores::agregarIndicador,engine);
			Spark.get("indicadores/eliminar", ControllerIndicadores::eliminarIndicador,engine);
			Spark.get("indicadores/evaluar", ControllerIndicadores::evaluarIndicador,engine);
			
						
			Spark.get("metodologias",ControllerMetodologias::consultarMetodologias,engine);
			Spark.get("metodologias/evaluar",ControllerMetodologias::evaluarMetodologia,engine);
			
	}
}
