package Server.Controller;

import spark.Request;

public class Controller {

	public static boolean contieneQueryParam(String query,Request request)
	{
		
		return request.queryMap().toMap().containsKey("periodo");
	}
}
