package Server;

import spark.Spark;

public class Router {

		public static void configure() {
			Spark.staticFiles.location("/public"); // Archivos estaticos en Public
			Spark.get("dondeInvierto", Controller::saludar );
	}
}
