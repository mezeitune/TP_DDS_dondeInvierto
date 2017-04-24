package Class;
import usuario.*;
import java.util.List;
import org.uqbar.commons.utils.Observable;

@Observable
public class Main {

	
	public static void main(String[] args){
		  
		List<Empresa> empresasPrueba = new Adapter("empresas.json").getEmpresasDelArchivo();
		Main.consultarCuentas(empresasPrueba);
	
	}

	public static void consultarCuentas(List<Empresa> empresas){
		int i,j;
		for(i=0; i< empresas.size();i++){
			System.out.println(empresas.get(i).getNombre());
			for(j=0; j < empresas.get(i).getCuentas().size(); j++){
				System.out.println(empresas.get(i).getCuentas().get(j).getNombre());
				System.out.println(empresas.get(i).getCuentas().get(j).getPeriodo());
				System.out.println(empresas.get(i).getCuentas().get(j).getValor());
			}
		}	  	
	}
}


