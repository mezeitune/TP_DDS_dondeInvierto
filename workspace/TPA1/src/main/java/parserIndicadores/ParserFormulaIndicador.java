package parserIndicadores;

import java.util.stream.Collectors;


import org.apache.commons.lang.math.NumberUtils;

import excepciones.AccountNotFoundException;
import model.Cuenta;
import model.Empresa;
import model.Indicador;
import repositorios.RepositorioEmpresas;
import repositorios.RepositorioIndicadores;

import java.util.LinkedList;
import java.util.List;


public class ParserFormulaIndicador {

	private static ParserFormulaIndicador parserInstance;
	
	private static List<String> nombreCuentas = new LinkedList<String> ();
	private static List<Indicador> indicadores = new LinkedList<Indicador>();
	
	private static List<Cuenta> cuentasPorPeriodo;
	
	
	private static RepositorioIndicadores repositorio_indicadores=new RepositorioIndicadores();
	private static RepositorioEmpresas repositorio_empresas=new RepositorioEmpresas();
	
	public static ParserFormulaIndicador getInstance() {
		if(parserInstance == null) return new ParserFormulaIndicador();
		return parserInstance;
	}
	
	public ParserFormulaIndicador() {
		nombreCuentas = repositorio_empresas.getNombreCuentas(); 
		indicadores = repositorio_indicadores.getIndicadores(); 
	}
	
	public ParserFormulaIndicador(List<Cuenta> cuentasMockeadas,List<Indicador> indicadoresMockeados) {
		nombreCuentas = cuentasMockeadas.stream().map(cuenta -> cuenta.getNombre()).collect(Collectors.toList());
		indicadores = indicadoresMockeados;
		cuentasPorPeriodo = cuentasMockeadas;
	}
	
	public static void mockearParserFormulaIndicador(List<Cuenta> cuentasMockeadas,List<Indicador> indicadoresMockeados) {
		parserInstance = new ParserFormulaIndicador(cuentasMockeadas,indicadoresMockeados);
	}
	
	/*Este conjunto de atributos {Empresa,Periodo} tiene que estar seteado antes de usar el parser
	*Ya que el parser calcula la formula de un indicador esepecificamente para una empresa en un periodo.
	 */
	private Empresa empresa ; 
	private String periodo; 
	
	private static String operadorSumaSplit = "(.*)[+](.*)";
	private static String operadorRestaSplit = "(.*)[-](.*)";
	private static String operadorMultiplicacionSplit = "(.*)[*](.*)";
	private static String operadorDivisionSplit = "(.*)[/](.*)";
	
	public void setEmpresa(Empresa unaEmpresa){
		empresa= unaEmpresa;
	}
	
	public  Empresa getEmpresa(){
		return this.empresa;
	}
	
	public void setPeriodo(String unPeriodo){
		periodo=unPeriodo;
		cuentasPorPeriodo = empresa.getCuentasPorPeriodo(periodo);
	}
	
	public String getPeriodo() {
		return this.periodo;
	}
	
	public int getCalculoIndicador(String formula) throws AccountNotFoundException,NumberFormatException{
			return this.construirArbolOperaciones(formula).calcular();
	}
	
	public Operacion construirArbolOperaciones(String operandos) throws AccountNotFoundException,NumberFormatException{
		
		if(operandos.matches(operadorSumaSplit)) return this.getOperacion(operandos.split("[+]"),new Suma());
		if(operandos.matches(operadorRestaSplit)) return this.getOperacion(operandos.split("[-]"),new Resta());
		if(operandos.matches(operadorMultiplicacionSplit)) return this.getOperacion(operandos.split("[*]"),new Multiplicacion());
		if(operandos.matches(operadorDivisionSplit)) return this.getOperacion(operandos.split("[/]"),new Division());
		
		return this.getConstante(operandos);
				
		}
	
	public  Operacion getOperacion(String [] formula,Operacion nuevaOperacion) throws AccountNotFoundException, NumberFormatException{
		List<String> operandos = new LinkedList<>();
		int i;
		for(i=0;i<formula.length;i++){
			operandos.add(i, formula[i]);
		}
		
		nuevaOperacion.setOperador1(this.construirArbolOperaciones(operandos.remove(0)));
		
		for(i=1;i<=operandos.size() && operandos.size()!= 1;i+=2){
			operandos.add(i,"/");
		}
		
		nuevaOperacion.setOperador2(this.construirArbolOperaciones(operandos.remove(0)));
		
		return nuevaOperacion;
	}
	
	
	public Operacion getConstante(String operador) throws AccountNotFoundException, NumberFormatException{
		if (ParserFormulaIndicador.esIndicador(operador)) return new Constante().setIndicador(this.buscarYObtenerIndicador(operador));
		if (ParserFormulaIndicador.esCuenta(operador)) return new Constante().setCuenta(this.buscarYObtenerCuenta(operador));
		return new Constante(Integer.parseInt(operador));
	}
	
	public static  boolean esIndicador(String operador){
		return indicadores.stream().anyMatch(indicador -> indicador.getNombre().equals(operador));
	}
	
	public static  boolean esCuenta(String operador){
		return nombreCuentas.stream().anyMatch(cuenta -> cuenta.equals(operador));
	}
	
	public  Indicador buscarYObtenerIndicador(String operador){
		return indicadores.stream().filter(indicador -> indicador.getNombre().equals(operador)).collect(Collectors.toList()).get(0);
	}
	
	public Cuenta buscarYObtenerCuenta(String operador) throws AccountNotFoundException{
		if(!cuentasPorPeriodo.stream().anyMatch(cuenta -> cuenta.getNombre().equals(operador))) throw new AccountNotFoundException();
		return cuentasPorPeriodo.stream().filter(cuenta -> cuenta.getNombre().equals(operador)).collect(Collectors.toList()).get(0);
	}
	
	public static  boolean validarIndicadorRepetidoAntesDePrecargar(String nombre , String formula) {
		List<Indicador> indicadoresRepetidos = indicadores.stream().filter(line -> line.getNombre().equals(nombre)).collect(Collectors.toList());
		
		if (indicadoresRepetidos.size() >= 1){
			return true;
		} else {
			return false;
		}
	}
 	
	public static  boolean esFormulaIndicadorValida(String formula){  /*TODO: No estaria funcionando*/
		
		String[] result = formula.split("[-+*/]");
		int[] validacion = new int[result.length];
			
		for (int i = 0; i < validacion.length; i++) {
			if(NumberUtils.isNumber(result[i]) || esIndicador(result[i]) || esCuenta(result[i])) {
				validacion[i] = 1;
			}
			else {
				validacion[i] = 0;
			}
		}
				
		for(int k=0;k< validacion.length;k++){
			if(validacion[k] == 0){
				return false;
			}
		}

		
		return true;
	}

	public static void restart() {
		parserInstance = null;
	}
	
}
