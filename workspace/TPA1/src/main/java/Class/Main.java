package Class;
import usuario.*;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.util.Scanner;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.uqbar.commons.utils.Observable;

import com.google.gson.Gson;
import com.ibm.icu.util.BytesTrie.Iterator;

import sun.misc.IOUtils;


@Observable
public class Main {

	
	public static void main(String[] args){

		   JSONParser parser = new JSONParser();
		   
	        try {
	 
	            Object obj = parser.parse(new FileReader(
	                    "empresas.json"));
	 
	            JSONObject jsonObject = (JSONObject) obj;
	            String jsonObjectt=jsonObject.toString();

	            
	            ObjetoPrueba json= new Gson().fromJson(jsonObjectt,ObjetoPrueba.class);
	            
	            
	            ObjetoPrueba escribir=new ObjetoPrueba();
	            escribir.setAuthor("Puta");
	            escribir.setName("Trola");
	            
	            String escribirEnArchivo=new Gson().toJson(escribir,ObjetoPrueba.class);
	            
	    		try (FileWriter file = new FileWriter("empresas.json")) {
	    			file.write(escribirEnArchivo.toString());
	    			System.out.println("Successfully Copied JSON Object to File...");
	    			System.out.println("\nJSON Object: " + obj);
	    		}
	            
	            System.out.println(json.Author);
	            
	 
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		
	}
	
	
	
}

//"C:\\arch.txt"