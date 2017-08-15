package repository;

import java.util.LinkedList;
import java.util.List;

import indicadoresPredefinidos.Antiguedad;
import indicadoresPredefinidos.PatrimonioNeto;
import usuario.Indicador;

public class ArchivoEIndicadoresUsuarioRepository {
	
	private static String archivo;
	private static List<Indicador> indicadoresDefinidosPorElUsuario = new LinkedList<>(); 
	

	public static List<Indicador> getIndicadoresDefinidosPorElUsuario() {
	
		return indicadoresDefinidosPorElUsuario;
	}

	public static void setIndicadoresDefinidosPorElUsuario(List<Indicador> list) {
		ArchivoEIndicadoresUsuarioRepository.indicadoresDefinidosPorElUsuario = list;
	}

	
	public static void addIndicadoresDefinidosPorElUsuario(Indicador indicadoresDefinidosPorElUsuario) {
		ArchivoEIndicadoresUsuarioRepository.indicadoresDefinidosPorElUsuario.add(indicadoresDefinidosPorElUsuario);
	}

	public static void setArchivo(String archivo){
		 ArchivoEIndicadoresUsuarioRepository.archivo=archivo;
	}
	
	public static String getArchivo(){
		return archivo;
	}
	
	
	public static void  cargarIndicadoresPredefinidos() {
	    PatrimonioNeto patrimonioNeto = PatrimonioNeto.getInstance();
	    ArchivoEIndicadoresUsuarioRepository.addIndicadoresDefinidosPorElUsuario(patrimonioNeto);
	}

	
}
