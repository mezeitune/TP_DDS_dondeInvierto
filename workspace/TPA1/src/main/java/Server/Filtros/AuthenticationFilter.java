package Server.Filtros;

import spark.Request;
import spark.Response;

public class AuthenticationFilter {

	public void isAuthorized(Request req, Response res){
		boolean authenticated =
				req.session().attribute("usuario") !=null;
		if(!isPublic(req.pathInfo()) && !authenticated){
			res.redirect("/login");
		}
				
	}
	
	private boolean isPublic(String pathInfo){
		return pathInfo.equals("/") || pathInfo.equals("/login/entry") || pathInfo.equals("/login") || pathInfo.equals("/login/clear");
	}
	
}
