package usuario;

import org.json.simple.JSONArray;

public class ParserJsonString {

	
	public static JSONArray pasarDeObjetosAJSON(Object objetoAOtrosObjetos){
		JSONArray empresasEnJsonArray = (JSONArray) objetoAOtrosObjetos;
		return empresasEnJsonArray;
	}
	
	public static String pasarDeJSONArrayAString(JSONArray jsonArray){
		String empresasEnString=jsonArray.toString(); // fromJson(2) pide como primer parametro un String.
		return empresasEnString;
	}
	
	
	
}
