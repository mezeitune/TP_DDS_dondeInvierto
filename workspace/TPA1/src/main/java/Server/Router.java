package Server;

import spark.Spark;
import spark.TemplateEngine;

public class Router {

		public static void configure() {
			Spark.staticFiles.location("/public"); // Archivos estaticos en Public

	}
}
