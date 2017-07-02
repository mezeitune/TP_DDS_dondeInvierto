package metodologias;

import java.util.List;

import org.uqbar.commons.utils.Observable;

import usuario.Empresa;
import usuario.Indicador;
@Observable
public class Condicion {

	private EstadoCondicion estado;
	private ParametroOperacion parametroOperacionTaxativa; 
	private ParametroOperacion parametroOperacionComparativa; 
	private int peso;
	private Indicador indicador;
	
	public Condicion(int peso, EstadoCondicion estado){
		this.peso=peso;
		this.estado=estado;
	}
	
	public List<Empresa> evaluar(List<Empresa> listaAEvaluar,String periodo){
		return estado.evaluar(listaAEvaluar,periodo,this);
	}
	
	public Indicador getIndicador(){
		return this.indicador;
	}
	public void setIndicador(Indicador indicador){
		this.indicador=indicador;
	}

/*
	public EmpresaCumplimiento evaluar(Empresa empresa1,Empresa empresa2){
		int cumplimiento=estado.evaluar(empresa1,empresa2,this);
		
		return new EmpresaCumplimiento(empresa1, cumplimiento, this.getPeso());
	}
	*/
	
	public void setParametrosOperacion(ParametroOperacion parametroOperacionTaxativas, ParametroOperacion parametroOperacionComparativas){
		//estan las dos juntas aca , por que puede ser que la defina como una combinada , entonces en el caso de que sea combinada tiene que evaluar las dos juntas
		this.parametroOperacionComparativa=parametroOperacionComparativas;
		this.parametroOperacionTaxativa=parametroOperacionTaxativas;
	}
	
	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}


	public EstadoCondicion getEstado() {
		return estado;
	}
	
	
	public void setEstado(EstadoCondicion estado) {
		this.estado = estado;
	}
	
	
	public ParametroOperacion getParametroOperacionTaxativa() {
		return parametroOperacionTaxativa;
	}


	public ParametroOperacion getParametroOperacionComparativa() {
		return parametroOperacionComparativa;
	}

	
}
