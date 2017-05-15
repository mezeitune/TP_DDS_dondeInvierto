package repository;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;

import usuario.Cuenta;
import usuario.Indicador;

public class ArchivoEIndicadoresUsuarioRepository {
	
	private static String archivo;
	private static List<Indicador> indicadoresDefinidosPorElUsuario = new LinkedList<>(); 
	

	public static List<Indicador> getIndicadoresDefinidosPorElUsuario() {
		return indicadoresDefinidosPorElUsuario;
	}

	public static void setIndicadoresDefinidosPorElUsuario(List<Indicador> indicadoresDefinidosPorElUsuario) {
		ArchivoEIndicadoresUsuarioRepository.indicadoresDefinidosPorElUsuario = indicadoresDefinidosPorElUsuario;
	}

	public static void setArchivo(String archivo){
		 ArchivoEIndicadoresUsuarioRepository.archivo=archivo;
	}
	
	public static String getArchivo(){
		return archivo;
	}
	
}
