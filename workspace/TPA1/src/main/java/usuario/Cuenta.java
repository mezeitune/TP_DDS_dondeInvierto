package usuario;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.uqbar.commons.utils.Observable;

import parserIndicadores.Operacion;

@Observable
@Entity
@Table(name="Cuentas")
public class Cuenta implements Operacion {
	@Id @GeneratedValue
	private Long id;
	private String nombre;
	private String periodo;
	private int valor;
	
	public Cuenta(String nombre, String periodo, int valor){
		this.nombre=nombre;
		this.periodo=periodo;
		this.valor=valor;
	}
	
	public Cuenta(){
		
	}
	
	public String getNombre (){
		return this.nombre;
	}
	
	public String getPeriodo (){
		return this.periodo;
	}
	
	public int getValor (){
		return this.valor;
	}
	
	
	public void setNombre(String unNombre){
		this.nombre = unNombre;
	}
	
	public void setPeriodo(String unPeriodo){
		this.periodo = unPeriodo;
	}
	
	public void setValor(int unValor){
		this.valor = unValor;
	}
	
	public int calcular(){
		return valor;
	}

	@Override
	public void setOperador1(Operacion operador1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setOperador2(Operacion operador2) {
		// TODO Auto-generated method stub
		
	}
}



