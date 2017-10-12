package Server;

import spark.Request;
import spark.Response;

public class AuthenticationFilter {

	public void isAuthorized(Request req, Response res){
		boolean authenticated =
				req.session().attribute("usuario") !=null;
		if(!isPublic(req.pathInfo()) && !authenticated){
			res.redirect("/login.html");
		}
				
	}
	
	private boolean isPublic(String pathInfo){
		return pathInfo.equals("/login.html");
	}
	
}
