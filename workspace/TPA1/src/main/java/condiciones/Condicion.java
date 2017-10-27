package condiciones;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;

import model.Empresa;
import model.Indicador;
@Entity
@Table(name="Condiciones")
public class Condicion {

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@OneToOne(fetch =FetchType.EAGER, cascade = CascadeType.ALL)
	public TipoCondicion tipo;
	
	public int peso;
	
	@ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL) 
	@OrderColumn
	public Indicador indicador;
	
	public String nombre;
	
	
	public Condicion(){
		
	}
	
	public Condicion(String nombre,TipoCondicion tipo,Indicador indicador,int peso){
		this.nombre = nombre;
		this.tipo=tipo;
		this.indicador = indicador;
		this.peso=peso;
	}
	
	
	public List<Empresa> evaluar(List<Empresa> listaAEvaluar,List<String> periodos){
		List<Empresa> resultado = new LinkedList<Empresa>(this.tipo.evaluar(listaAEvaluar,periodos,this));
		
		return resultado;
	}
	
	public Indicador getIndicador(){
		return this.indicador;
	}
	public void setIndicador(Indicador indicador){
		this.indicador=indicador;
	}
	
	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}


	public TipoCondicion getEstado() {
		return tipo;
	}
	
	
	public void setEstado(TipoCondicion estado) {
		this.tipo = estado;
	}
	
	public String getNombre(){
		return this.nombre;
	}
	

	
}
