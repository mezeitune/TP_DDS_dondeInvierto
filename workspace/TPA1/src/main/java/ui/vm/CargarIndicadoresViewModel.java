package ui.vm;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Collection;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.gson.Gson;

import repository.ArchivoRepository;
import usuario.Indicador;

public class CargarIndicadoresViewModel {

	
	
	private static String nombreIndicador;
	private static String formulaIndicador;
	
	public String getNombreIndicador() {
		return nombreIndicador;
	}
	public void setNombreIndicador(String nombreIndicador) {
		this.nombreIndicador = nombreIndicador;
	}
	public String getFormulaIndicador() {
		return formulaIndicador;
	}
	public void setFormulaIndicador(String formulaIndicador) {
		this.formulaIndicador = formulaIndicador;
	}

	
	
	public static void generarIndicador(){
		ObjectMapper mapper = new ObjectMapper();
		Indicador obj = new Indicador();
		obj.setNombre(nombreIndicador);
		obj.setFormula(formulaIndicador);
		
		//Object to JSON in file
		try {
			
			RandomAccessFile randomAccessFile = new RandomAccessFile("indicadores.json", "rw");
			
			long pos = randomAccessFile.length();
			while (randomAccessFile.length() > 0) {
			    pos--;
			    randomAccessFile.seek(pos);
			    if (randomAccessFile.readByte() == ']') {
			        randomAccessFile.seek(pos);
			        break;
			    }
			}
		
			
			Gson gson= new Gson();
			String jsonElement = gson.toJson(obj);
			randomAccessFile.writeBytes("," + jsonElement + "]");
			
			randomAccessFile.close();
			
			
			
			//ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
			//writer.writeValue(new File("indicadores.json"), obj);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//Object to JSON in String
		//String jsonInString = mapper.writeValueAsString(obj);
	}

	
	
	
}
