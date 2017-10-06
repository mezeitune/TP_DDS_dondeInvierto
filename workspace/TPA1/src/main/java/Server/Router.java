package Server;

import spark.Spark;
import spark.TemplateEngine;


public class Router {

		public static void configure() {
			Spark.staticFiles.location("/public"); // Archivos estaticos en Public
			TemplateEngine engine = HandlebarsTemplateEngineBuilder.create().build();
			
			
			Spark.get("empresas", ControllerCuentas::consultarEmpresas,engine);
			Spark.get("empresas/cuentas", ControllerCuentas::mostrarCuentas,engine);

			Spark.post("login/entry", Controller::crearSessionDeLogin,engine);
			Spark.get("login/clear", Controller::eliminarSessionDeLogin,engine);
			
			Spark.get("indicadores", ControllerIndicadores::consultarIndicadores,engine);
			Spark.get("indicadores/agregar", ControllerIndicadores::agregarIndicador,engine);
			Spark.get("indicadores/eliminar", ControllerIndicadores::eliminarIndicador,engine);
			Spark.get("indicadores/evaluar", ControllerIndicadores::evaluarIndicador,engine);
			
						
			Spark.get("metodologias",Controller::consultarMetodologias,engine);
			Spark.get("metodologias/evaluar",Controller::evaluarMetodologia,engine);
			
	}
}
