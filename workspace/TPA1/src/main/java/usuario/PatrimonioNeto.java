package usuario;

import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;

//indicador predefinido que extiende de indicador para que todos tengan el metodo calcular en su dominio

public class PatrimonioNeto extends Indicador{

	private int activo;
	private int pasivo;
	
	public int getActivo() {
		return activo;
	}

	public void setActivo(int activo) {
		this.activo = activo;
	}

	public int getPasivo() {
		return pasivo;
	}

	public void setPasivo(int pasivo) {
		this.pasivo = pasivo;
	}

	//Habria que buscar la forma de cambiar ese activo y pasivo usando la lista de enteros de Indicador
	
	
	@Override
	public int calcular() {//metodo que tiene que estar por ser Indicador usado de manera polimorfica
		return activo - pasivo;
		
	}
	
	

	
	
}
