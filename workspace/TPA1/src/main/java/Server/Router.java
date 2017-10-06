package Server;

import spark.Spark;
import spark.TemplateEngine;


public class Router {

		public static void configure() {
			Spark.staticFiles.location("/public"); // Archivos estaticos en Public
			TemplateEngine engine = HandlebarsTemplateEngineBuilder.create().build();
			Spark.get("saludar", Controller::saludar );
			Spark.get("empresas", Controller::consultarEmpresas,engine);
			Spark.get("empresas/cuentas", Controller::mostrarCuentas,engine);
	}
}
