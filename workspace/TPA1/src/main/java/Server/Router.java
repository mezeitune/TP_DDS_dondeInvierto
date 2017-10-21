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
		    EntityManagerFilter entityFilter = new EntityManagerFilter();
		    Spark.before((req,res)->{
		    	  authentication.isAuthorized(req, res);
		    	   
		    });
		    
		    Spark.after((req,res)->{
		    	  entityFilter.restart(req, res);
		    });
			
		    Spark.get("/", ControllerLogin::home,engine);
			
			Spark.post("login/entry", ControllerLogin::crearSessionDeLogin,engine);
			Spark.get("login/clear", ControllerLogin::eliminarSessionDeLogin,engine);
			

			Spark.get("empresas", ControllerCuentas::consultarEmpresas,engine);
			Spark.get("empresas/cuentas", ControllerCuentas::mostrarCuentas,engine);
			
			Spark.get("indicadores", ControllerIndicadores::consultarIndicadores,engine);
			Spark.post("indicadores/add", ControllerIndicadores::agregarIndicador,engine);
			Spark.post("indicadores/delete", ControllerIndicadores::eliminarIndicador,engine);
			Spark.get("indicadores/eval", ControllerIndicadores::evaluarIndicador,engine);
			
						
			Spark.get("metodologias",ControllerMetodologias::consultarMetodologias,engine);
			Spark.get("metodologias/setDatosParaEvaluar",ControllerMetodologias::setDatosParaEvaluar,engine);
			Spark.get("metodologias/evaluar",ControllerMetodologias::evaluarMetodologia,engine);
			Spark.post("metodologias/guardarDatosParaEvaluar",ControllerMetodologias::guardarDatosParaEvaluar, engine);
	}
}
