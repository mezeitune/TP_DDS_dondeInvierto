package model;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name="Empresas")
public class Empresa  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "empresa_id")
	private long id;
	private String nombre;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "empresa_id",nullable = false)//el nullable es para poder eliminar deshabilitando las FK
	private List<Cuenta> cuentas = new LinkedList<>(); 
	
	public Empresa(){
		
	}
	
	public Empresa(String nombreEmpresa,List<Cuenta> cuentas){
		this.nombre = nombreEmpresa;
		this.cuentas = cuentas;
	}
	
	public Empresa(String nombreEmpresa){
		this.nombre=nombreEmpresa;
	}
	
	public String getCantidad() {
		return Integer.toString(this.getCuentas().size());
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

}
