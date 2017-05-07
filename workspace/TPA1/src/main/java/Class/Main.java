package Class;
import usuario.*;

import java.io.IOException;
import java.util.List;

import org.uqbar.arena.Application;
import org.uqbar.arena.windows.Window;
import org.uqbar.commons.utils.Observable;

import exceptions.CSVInexistenteException;
import ui.windows.CargaArchivoEmpresaWindow;
import ui.windows.DatosEmpresasWindow;

@Observable
public class Main extends Application{

	
	public static void main(String[] args){

	
			new Main().start();
	}
		@Override
	protected Window<?> createMainWindow() {

			try {
				return new CargaArchivoEmpresaWindow(this);
			} catch (CSVInexistenteException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
			return null;

	}	
		
}		
	

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


