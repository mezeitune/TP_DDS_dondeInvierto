package parser;

import java.io.IOException;
import java.util.stream.IntStream;

import javax.print.DocFlavor.CHAR_ARRAY;

import org.mockito.internal.matchers.Equals;
import org.omg.CORBA.UserException;

import java.util.List;

import repository.ArchivoEIndicadoresUsuarioRepository;
import usuario.Cuenta;
import usuario.Empresa;
import usuario.Indicador;
import usuario.IndicadorCustom;

public class ParserFormulaToIndicador {

	private static List<IndicadorCustom> indicadores;
	private static List<Cuenta> cuentasPorPeriodo;
	private static Empresa empresa;
	private static String periodo;
	
	public ParserFormulaToIndicador() throws UserException{
		ParserJsonAEmpresaAdapter parserEmpIndicador = new ParserJsonAEmpresaAdapter("indicadores.json");
		ArchivoEIndicadoresUsuarioRepository.setIndicadoresDefinidosPorElUsuario(parserEmpIndicador.getIndicadoresDelArchivo());
		
	}
	
	public static void setEmpresa(Empresa unaEmpresa){
		empresa= unaEmpresa;
	}
	
	public static void setPeriodo(String unPeriodo){
		periodo=unPeriodo;
	}
	
	public static int getCalculoIndicador(String formula) throws UserException{
		
		if(formula.matches("(.*)[+](.*)")){
			System.out.println(formula);
			return getSuma(formula);
		}else if(formula.matches("(.*)[-](.*)")){
			return getResta(formula);
	
		}else if(formula.matches("(.*)[*](.*)")){
			return getMultiplicacion(formula);
	
		}else if(formula.matches("(.*)[/](.*)")){
			return getDivision(formula);
		}
		return 0;
	}
	
	
	
	private static int getDivision(String formula) throws UserException {
		String operador = "[/]";
		
		String [] operandos = ParserFormulaToIndicador.elementosToOperandos(formula.split(operador));

		
		int[] numerosAOperar= parsearOperandosAInt(operandos);
		int div = numerosAOperar[0];
		for(int i=1; i< numerosAOperar.length; i++){
			div = div / numerosAOperar[i];
		}
		return div;
	}

	private static int getMultiplicacion(String formula) throws UserException {
		String operador = "[*]";
		
		String [] operandos = ParserFormulaToIndicador.elementosToOperandos(formula.split(operador));

		
		int[] numerosAOperar= parsearOperandosAInt(operandos);
		int mult= numerosAOperar[0];
		for(int i=1; i< numerosAOperar.length; i++){
			mult = mult * numerosAOperar[i];
		}
		
		return mult;
	}

	private static int getResta(String formula) throws UserException {
		String operador = "[-]";
		
		String [] operandos = ParserFormulaToIndicador.elementosToOperandos(formula.split(operador));

		int[] numerosAOperar= parsearOperandosAInt(operandos);
		int resta = numerosAOperar[0];
		for(int i=1; i< numerosAOperar.length; i++){
			resta = resta - numerosAOperar[i];
		}
	
		return resta;
	}

	
	
	public static int getSuma(String formula) throws UserException{
		
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
	public static String[] elementosToOperandos(String[] operandos) throws UserException{
		int i,j;
		indicadores = ArchivoEIndicadoresUsuarioRepository.getIndicadoresDefinidosPorElUsuario();
		cuentasPorPeriodo=empresa.getCuentasPorPeriodo(periodo);
		
		for (i = 0; i < operandos.length; i++) {
			
			/* Si matchea con un indicador*/
			for (j = 0; j < indicadores.size(); j++) {
				if(indicadores.get(j).getNombre().equals(operandos[i])){
					operandos[i] = String.valueOf(indicadores.get(j).calcular());
				}
			}
			/*Si matchea con una cuenta*/
			for (j= 0; j<cuentasPorPeriodo.size();j++){
				if(cuentasPorPeriodo.get(j).getNombre().equals(operandos[i])){
					operandos[i]= String.valueOf(cuentasPorPeriodo.get(j).getValor());
				}
			}
			/*Si no matchea ni cuenta ni indicador, entonces es un operador basico y no hay modificaciones en el buffer*/
		}
		return operandos;
	}
	
}

	