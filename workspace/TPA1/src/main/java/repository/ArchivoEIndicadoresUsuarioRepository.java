package repository;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;

import usuario.Cuenta;
import usuario.IndicadorCustom;
import usuario.Indicador;

public class ArchivoEIndicadoresUsuarioRepository {
	
	private static String archivo;
	private static List<IndicadorCustom> indicadoresDefinidosPorElUsuario = new LinkedList<>(); 
	

	public static List<IndicadorCustom> getIndicadoresDefinidosPorElUsuario() {
		return indicadoresDefinidosPorElUsuario;
	}

	public static void setIndicadoresDefinidosPorElUsuario(List<IndicadorCustom> indicadoresDefinidosPorElUsuario) {
		ArchivoEIndicadoresUsuarioRepository.indicadoresDefinidosPorElUsuario = indicadoresDefinidosPorElUsuario;
	}

	public static void setArchivo(String archivo){
		 ArchivoEIndicadoresUsuarioRepository.archivo=archivo;
	}
	
	public static String getArchivo(){
		return archivo;
	}

	
}
