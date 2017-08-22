package usuario;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.uqbar.commons.utils.Observable;


@Observable
@Entity
public class Empresa {

	@Id @GeneratedValue
	private int id;
	private String nombre;
	int peso=0;
	@Transient
	private List<Cuenta> cuentas = new LinkedList<>(); 
	
	public Empresa(String nombreEmpresa,List<Cuenta> cuentas){
		this.nombre = nombreEmpresa;
		this.cuentas = cuentas;
	}
	
	public Empresa(String nombreEmpresa){
		this.nombre=nombreEmpresa;
	}
	
	public Empresa(){
		
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
	
	public void actualizarPeso(int peso){
		this.peso+=peso;
	}
	public int getPeso(){
		return this.peso;
	}

}
