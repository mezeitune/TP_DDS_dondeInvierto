package parserIndicadores;

import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import excepciones.AccountNotFoundException;

import java.util.LinkedList;
import java.util.List;

import repository.IndicadoresRepository;
import repository.EmpresasRepository;
import usuario.Cuenta;
import usuario.Empresa;
import usuario.Indicador;

public class ParserFormulaToIndicador {

	private static List<String> nombreCuentas = new LinkedList<String> ();
	private static List<Indicador> indicadores = new LinkedList<Indicador>();
	
	private static List<Cuenta> cuentasPorPeriodo;
	
	private static boolean modeTest = false;
	
	/*Este conjunto de atributos {Empresa,Periodo} tiene que estar seteado antes de usar el parser
	*Ya que el parser calcula la formula de un indicador esepecificamente para una empresa en un periodo.
	 */
	private static Empresa empresa ; 
	private static String periodo; 
	
	private static String operadorSumaSplit = "(.*)[+](.*)";
	private static String operadorRestaSplit = "(.*)[-](.*)";
	private static String operadorMultiplicacionSplit = "(.*)[*](.*)";
	private static String operadorDivisionSplit = "(.*)[/](.*)";
	
	public static void setModeTest(boolean bool){
		modeTest = bool;
	}
	
	
	/*Para testear*/
	public static void init(List<Indicador> indicadoresTest, List<Cuenta> cuentasTest){
		indicadores = indicadoresTest;
		nombreCuentas = cuentasTest.stream().map(cuenta -> cuenta.getNombre()).collect(Collectors.toList());
		cuentasPorPeriodo = cuentasTest; 
	}
	/*Para testear*/
	public static void init(List<Cuenta> cuentasTest){
		nombreCuentas = cuentasTest.stream().map(cuenta -> cuenta.getNombre()).collect(Collectors.toList());
	}
	
	public static void setEmpresa(Empresa unaEmpresa){
		empresa= unaEmpresa;
	}
	
	public static Empresa getEmpresa(){
		return ParserFormulaToIndicador.empresa;
	}
	
	public static void setPeriodo(String unPeriodo){
		periodo=unPeriodo;
		cuentasPorPeriodo = empresa.getCuentasPorPeriodo(periodo);
	}
	
	public static int getCalculoIndicador(String formula) throws AccountNotFoundException{
			return ParserFormulaToIndicador.construirArbolOperaciones(formula).calcular();
	}
	
	public static Operacion construirArbolOperaciones(String operandos) throws AccountNotFoundException{
		System.out.println(modeTest);
		if(!modeTest)ParserFormulaToIndicador.update(); //Hay que actualizar las cuentas e indicadores de la empresa!
		
		if(operandos.matches(operadorSumaSplit)) return ParserFormulaToIndicador.getOperacion(operandos.split("[+]"),new Suma());
		if(operandos.matches(operadorRestaSplit)) return ParserFormulaToIndicador.getOperacion(operandos.split("[-]"),new Resta());
		if(operandos.matches(operadorMultiplicacionSplit)) return ParserFormulaToIndicador.getOperacion(operandos.split("[*]"),new Multiplicacion());
		if(operandos.matches(operadorDivisionSplit)) return ParserFormulaToIndicador.getOperacion(operandos.split("[/]"),new Division());
		
		return ParserFormulaToIndicador.getClaseOperador(operandos);
				
		}
	
	public static void update() {
		nombreCuentas = EmpresasRepository.getNombreCuentas(); 
		indicadores = IndicadoresRepository.getIndicadores();
	}
	
	public static Operacion getOperacion(String [] formula,Operacion nuevaOperacion) throws AccountNotFoundException{
		List<String> operandos = new LinkedList<>();
		int i;
		for(i=0;i<formula.length;i++){
			operandos.add(i, formula[i]);
		}
		nuevaOperacion.setOperador1(ParserFormulaToIndicador.construirArbolOperaciones(operandos.remove(0)));
		
		for(i=1;i<=operandos.size() && operandos.size()!= 1;i+=2){
			operandos.add(i,"/");
		}
		
		nuevaOperacion.setOperador2(ParserFormulaToIndicador.construirArbolOperaciones(operandos.remove(0)));
		
		return nuevaOperacion;
	}
	
	
	public static Operacion getClaseOperador(String operador) throws AccountNotFoundException{
		if (ParserFormulaToIndicador.esIndicador(operador)) return ParserFormulaToIndicador.buscarYObtenerIndicador(operador);
		if (ParserFormulaToIndicador.esCuenta(operador)) return ParserFormulaToIndicador.buscarYObtenerCuenta(operador);
		else return new Constante(Integer.parseInt(operador));
	}
	
	public static boolean esIndicador(String operador){
		return indicadores.stream().anyMatch(indicador -> indicador.getNombre().equals(operador));
	}
	
	public static boolean esCuenta(String operador){
		return nombreCuentas.stream().anyMatch(cuenta -> cuenta.equals(operador));
	}
	
	public static Indicador buscarYObtenerIndicador(String operador){
		return indicadores.stream().filter(indicador -> indicador.getNombre().equals(operador)).collect(Collectors.toList()).get(0);
	}
	
	
	
	public static Cuenta buscarYObtenerCuenta(String operador) throws AccountNotFoundException{
		if(!cuentasPorPeriodo.stream().anyMatch(cuenta -> cuenta.getNombre().equals(operador))) throw new AccountNotFoundException();
		return cuentasPorPeriodo.stream().filter(cuenta -> cuenta.getNombre().equals(operador)).collect(Collectors.toList()).get(0);
	}
	
	public static boolean validarIndicadorRepetidoAntesDePrecargar(String nombre , String formula) {
		List<Indicador> indicadoresRepetidos = indicadores.stream().filter(line -> line.getNombre().equals(nombre)).collect(Collectors.toList());
		
		if (indicadoresRepetidos.size() >= 1){
			return true;
		} else {
			return false;
		}
	}
 	
	public static boolean formulaIndicadorValida(String formula){  /*TODO: No estaria funcionando*/
		indicadores = IndicadoresRepository.getIndicadoresDefinidosPorElUsuario();
		
		String[] result = formula.split("[-+*/]");
		String[] ver = new String[result.length];
		
		for(int j=0;j<ver.length;j++){
			ver[j]="no";
		}
		
		for (int i = 0; i < result.length; i++) {
			
			if( StringUtils.isNumeric(result[i])){
				ver[i]="si";
			}else{
				for(Indicador s : indicadores){
					  if(s.getNombre().equals(result[i])){
						  ver[i]="si";
					  }
				}
				
				for(String s : nombreCuentas){
					  if(s.equals(result[i])){
						  ver[i]="si";
					  }
				}
			}
		}
		
		for(int k=0;k<ver.length;k++){
			if(ver[k].equals("no")){
				return false;
			}
		}

		
		return true;
		
	}
	
}
