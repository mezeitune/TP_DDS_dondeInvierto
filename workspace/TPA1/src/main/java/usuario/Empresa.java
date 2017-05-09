package usuario;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.uqbar.commons.utils.Observable;

import parser.ParserJsonAEmpresaAdapter;


@Observable
public class Empresa {

	String nombre;
	private List<Cuenta> cuentas = new LinkedList<>(); 
	
	public  Empresa(String nombreEmpresa,List<Cuenta> cuentas){
		this.nombre = nombreEmpresa;
		this.cuentas = cuentas;
	}

	
	public String getNombre() {
		return nombre;
	}
	public  List<Cuenta> getCuentas() {
		return cuentas;
	}

	public  List<Cuenta> getCuentasPorPeriodo(String periodo) {
		
		
		List <Cuenta> listado=(List<Cuenta>) cuentas.stream().filter(line -> line.getPeriodo().equals(periodo)).collect(Collectors.toList()) ;

		return listado;
	}

	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setCuentas(List<Cuenta> cuentas) {
		this.cuentas=cuentas;
	}
	
	public List<String> getPeriodosSinRepetidos(){
		Set <String> unSetPeriodos = new HashSet <String> (cuentas.stream().map(cuenta -> cuenta.getPeriodo()).collect(Collectors.toList()));
		List <String> periodosSinRepetidos = new ArrayList <String> (unSetPeriodos);
		return periodosSinRepetidos;
	}
	
	/*
	public static void main(String[] args) {
		List<Cuenta> cuentas;
		List<Empresa> empresas;
		
		ParserJsonAEmpresaAdapter parser=new ParserJsonAEmpresaAdapter("empresas.json");
		empresas=parser.getEmpresasDelArchivo();
		
		cuentas=empresas.get(0).getCuentasPorPeriodo("2016");
		
    

        // Or like this...
        for(int i = 0; i < cuentas.size(); i++) {
            System.out.println(cuentas.get(i).getNombre());
        }
	}
	*/
	
}
