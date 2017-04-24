package ui.vm;


import java.util.List;

import org.uqbar.commons.utils.Observable;
import com.sun.jersey.api.client.Client;


import com.google.gson.Gson;
import usuario.*;


@SuppressWarnings("unused")
@Observable
public class DatosEmpresasViewModel{
	
	private Empresa unaEmpresa;
	private String nombre;
	private List<Cuenta> cuentas;
	private List<Empresa> empresas;
	private Cuenta cuenta;


	public DatosEmpresasViewModel() {
		this.setearEmpresaSeleccionada();
	}
	
	public String getNombre(){
		return unaEmpresa.getNombre();
	}
	
	public void setCuenta(Cuenta cuenta){
		this.cuenta=cuenta;
	}
	
	
	public Empresa getEmpresaSeleccionada(){
		return this.unaEmpresa;
	}
	public List<Empresa> getEmpresas(){
		return this.empresas;
	}

	public void setearEmpresaSeleccionada() {
		
		List<Empresa> empresasPrueba = new Adapter().getEmpresasDelArchivo();
		this.empresas=empresasPrueba;
	}
}