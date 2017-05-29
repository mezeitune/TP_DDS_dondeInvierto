package parser;

import java.io.IOException;
import java.util.stream.IntStream;

import javax.print.DocFlavor.CHAR_ARRAY;

import org.mockito.internal.matchers.Equals;
import org.omg.CORBA.UserException;

import com.google.gson.Gson;

import java.util.List;

import repository.ArchivoEIndicadoresUsuarioRepository;
import usuario.Cuenta;
import usuario.Empresa;
import usuario.Indicador;
import usuario.Indicador;
import usuario.PatrimonioNeto;

public class ParserFormulaToIndicador {

	private static List<Indicador> indicadores;
	private static List<Cuenta> cuentasPorPeriodo;
	private static Empresa empresa ;
	private static String periodo;
	private static List<Empresa> empresas;
	
	public ParserFormulaToIndicador(){
		ParserJsonAEmpresaAdapter parserEmpIndicador = new ParserJsonAEmpresaAdapter("indicadores.json");
		ArchivoEIndicadoresUsuarioRepository.setIndicadoresDefinidosPorElUsuario(parserEmpIndicador.getIndicadoresDelArchivo());
		ArchivoEIndicadoresUsuarioRepository.cargarIndicadoresPredefinidos();
		

	    
		
	}
	
	public static void setEmpresa(Empresa unaEmpresa){
		
		empresa= unaEmpresa;
		
	}
	
	public static void setPeriodo(String unPeriodo){
		
		periodo=unPeriodo;
	
	}
	
	public static int getCalculoIndicador(String formula){
		
		try{
			Indicador indicador;
		
			if(formula.matches("(.*)[+](.*)")){
				return getSuma(formula);
			}else if(formula.matches("(.*)[-](.*)")){
				return getResta(formula);
				
			}else if(formula.matches("(.*)[*](.*)")){
				return getMultiplicacion(formula);
	
			}else if(formula.matches("(.*)[/](.*)")){
				return getDivision(formula);
				/*Esta para que la ventana de evaluacion indicadoresTexto pueda cargar un unico indicador*/	
			}else if(!ParserFormulaToIndicador.indicador(formula).equals(0)){
			
				indicador = ParserFormulaToIndicador.indicador(formula);
				return indicador.calcular();
			
			}
		}catch(NullPointerException e){
			/**Queda medio raro sin nada pero sino tira excepciones por consola y no se me ocurrio otra forma cuando no reconoce la formula*/
		}catch(NumberFormatException e){
			/**Queda medio raro sin nada pero sino tira excepciones por consola y no se me ocurrio otra forma cuando no reconoce la formula*/	
		}
		return 0;
	}
	
	
	
	private static Indicador indicador(String formula) {
		int j;
		indicadores = ArchivoEIndicadoresUsuarioRepository.getIndicadoresDefinidosPorElUsuario();
		for (j = 0; j < indicadores.size(); j++) {
			if(indicadores.get(j).getNombre().equals(formula)){
				return indicadores.get(j);
			}
		}
		
		return null;
	}

	private static int getDivision(String formula){
		String operador = "[/]";
		
		String [] operandos = ParserFormulaToIndicador.elementosToOperandos(formula.split(operador));

		
		int[] numerosAOperar= parsearOperandosAInt(operandos);
		int div = numerosAOperar[0];
		for(int i=1; i< numerosAOperar.length; i++){
			div = div / numerosAOperar[i];
		}
		return div;
	}

	private static int getMultiplicacion(String formula){
		String operador = "[*]";
		
		String [] operandos = ParserFormulaToIndicador.elementosToOperandos(formula.split(operador));

		
		int[] numerosAOperar= parsearOperandosAInt(operandos);
		int mult= numerosAOperar[0];
		for(int i=1; i< numerosAOperar.length; i++){
			mult = mult * numerosAOperar[i];
		}
		
		return mult;
	}

	private static int getResta(String formula){
		String operador = "[-]";
		
		String [] operandos = ParserFormulaToIndicador.elementosToOperandos(formula.split(operador));

		int[] numerosAOperar= parsearOperandosAInt(operandos);
		int resta = numerosAOperar[0];
		for(int i=1; i< numerosAOperar.length; i++){
			resta = resta - numerosAOperar[i];
		}
	
		return resta;
	}

	
	
	public static int getSuma(String formula){
		
		String operador = "[+]";

		String [] operandos = ParserFormulaToIndicador.elementosToOperandos(formula.split(operador));
		
		
		int sum = IntStream.of(parsearOperandosAInt(operandos)).sum();
		
		return sum;
	}
	
	public static int[] parsearOperandosAInt(String[] operandos){
		int[] numeros = new int[operandos.length];
		
		for(int i = 0;i < operandos.length;i++){
		   numeros[i] = Integer.parseInt(operandos[i]);
		}
		return numeros;
	}
	
	/*TODO: Testear que reconoce bien el valor de una cuenta y de un indicador*/
	public static String[] elementosToOperandos(String[] operandos) {
		int i,j;
		for (int j2 = 0; j2 < operandos.length; j2++) {
		}
		indicadores = ArchivoEIndicadoresUsuarioRepository.getIndicadoresDefinidosPorElUsuario();
		
		
		
		for (i = 0; i < operandos.length; i++) {
			for (j = 0; j < indicadores.size(); j++) {
				if(indicadores.get(j).getNombre().equals(operandos[i])){

						operandos[i] = String.valueOf(indicadores.get(j).calcular());

				}
			}
			/*Si matchea con una cuenta*/
			try{
				cuentasPorPeriodo=empresa.getCuentasPorPeriodo(periodo);
				for (j= 0; j<cuentasPorPeriodo.size();j++){
					if(cuentasPorPeriodo.get(j).getNombre().equals(operandos[i])){
						operandos[i]= String.valueOf(cuentasPorPeriodo.get(j).getValor());
					}
				}
			}catch(NullPointerException e){/**Queda medio raro sin nada pero sino rompe cuando no hay un archivo cargado*/	}
			
			/*Si no matchea ni cuenta ni indicador, entonces es un operador basico y no hay modificaciones en el buffer*/
		}
		return operandos;
	}
	
}

	