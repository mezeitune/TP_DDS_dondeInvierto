package Class;
import usuario.*;
import java.util.List;

import org.uqbar.arena.Application;
import org.uqbar.arena.windows.Window;
import org.uqbar.commons.utils.Observable;

import ui.windows.DatosEmpresasWindow;

@Observable
public class Main extends Application{

	
	public static void main(String[] args){
<<<<<<< HEAD
		  
		List<Empresa> empresasPrueba = new Adapter("empresas.json").getEmpresasDelArchivo();
		Main.consultarCuentas(empresasPrueba);
	
	}
=======
	
			new Main().start();
	}
		@Override
	protected Window<?> createMainWindow() {
			return new DatosEmpresasWindow(this);
		}	
		
}		
	

>>>>>>> 2954f52886f72e7ed04fb2195af9f45b4e14e855

/*List<Empresa> empresasPrueba = new Adapter().getEmpresasDelArchivo();
	Main.consultarCuentas(empresasPrueba);*/
	
	

	/*public static void consultarCuentas(List<Empresa> empresas){
		int i,j;
		for(i=0; i< empresas.size();i++){
			System.out.println(empresas.get(i).getNombre());
			for(j=0; j < empresas.get(i).getCuentas().size(); j++){
				System.out.println(empresas.get(i).getCuentas().get(j).getNombre());
				System.out.println(empresas.get(i).getCuentas().get(j).getPeriodo());
				System.out.println(empresas.get(i).getCuentas().get(j).getValor());
			}
		}	  	
	}*/


