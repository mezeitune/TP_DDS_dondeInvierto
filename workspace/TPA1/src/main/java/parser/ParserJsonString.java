package parser;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import org.json.simple.JSONArray;

import excepciones.CSVInexistenteException;

public class ParserJsonString {

	
	public static JSONArray pasarDeObjetosAJSON(Object objetoAOtrosObjetos){
		JSONArray empresasEnJsonArray = (JSONArray) objetoAOtrosObjetos;
		return empresasEnJsonArray;
	}
	
	public static String pasarDeJSONArrayAString(JSONArray jsonArray){
		String empresasEnString=jsonArray.toString();
		return empresasEnString;
	}
	
	
	public static void anidadoDeJsonAUnJsonArrayEnUnArchivo(String nombreArchivo, String jsonElement ){
		
		
		try {
			RandomAccessFile randomAccessFile = new RandomAccessFile(nombreArchivo+".json", "rw");
			
			long pos = randomAccessFile.length();
			while (randomAccessFile.length() > 0) {
			    pos--;
			    randomAccessFile.seek(pos);
			    if (randomAccessFile.readByte() == ']') {
			        randomAccessFile.seek(pos);
			        break;
			    }
			}
		
			
			
			randomAccessFile.writeBytes("," + jsonElement + "]");
			
			randomAccessFile.close();
			
			
		} catch (CSVInexistenteException ex) {

			throw new CSVInexistenteException();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			throw new CSVInexistenteException();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new CSVInexistenteException();
		}
	}
	
	
}
