package Server;


import spark.Spark;
import spark.debug.DebugScreen;

public class DondeInviertoServer {
	public static void main(String[] args) {
		Spark.port(7000);
		DebugScreen.enableDebugScreen();
		Router.configure();
	}

}