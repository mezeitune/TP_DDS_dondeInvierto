package ui.vm;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Collection;

import org.uqbar.commons.utils.Observable;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.gson.Gson;

import parser.ParserJsonString;
import repository.ArchivoEIndicadoresUsuarioRepository;
import usuario.Indicador;
@Observable
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
		
		Indicador obj = new Indicador();
		obj.setNombre(nombreIndicador);
		obj.setFormula(formulaIndicador);
		
		
		Gson gson= new Gson();
		String jsonElement = gson.toJson(obj);
		ParserJsonString.anidadoDeJsonAUnJsonArrayEnUnFile("indicadores",jsonElement );
			
			
		
	}

	
	
	
}
