package Mocks;

import java.util.ArrayList;
import java.util.List;

import usuario.Cuenta;
import usuario.Empresa;

public class ListaEmpresasMock {
	
	List<Empresa> empresas = new ArrayList<>();
	List<Cuenta> cuentas = new ArrayList<>();
	
	public List<Empresa> mockearListaEmpresas(){
		Cuenta cuenta1 = new Cuenta("EBITDA","2016",200);
		Cuenta cuenta2 = new Cuenta("EBITDA","2015",300);
		Cuenta cuenta3 = new Cuenta("Free CashFlow","2015",1100);
		this.cuentas.add(cuenta1);
		this.cuentas.add(cuenta2);
		this.cuentas.add(cuenta3);
		Empresa empresa1 = new Empresa("Facebook", cuentas);
		this.cuentas.clear();
		cuenta1 = new Cuenta("Free CashFlow","2016",50);
		cuenta2 = new Cuenta("Revenue","2014",1000);
		this.cuentas.add(cuenta1);
		this.cuentas.add(cuenta2);
		Empresa empresa2 = new Empresa("Apple", cuentas);
		this.empresas.add(empresa1);
		this.empresas.add(empresa2);
		
		return this.empresas;
	}
	
	public List<Cuenta> mockearListaCuentas(){
		Cuenta cuenta1 = new Cuenta("EBITDA","2016",200);
		Cuenta cuenta2 = new Cuenta("EBITDA","2015",300);
		Cuenta cuenta3 = new Cuenta("Free CashFlow","2015",1100);
		Cuenta cuenta4 = new Cuenta("Free CashFlow","2016",50);
		Cuenta cuenta5 = new Cuenta("Revenue","2014",1000);
		this.cuentas.add(cuenta1);
		this.cuentas.add(cuenta2);
		this.cuentas.add(cuenta3);
		this.cuentas.add(cuenta4);
		this.cuentas.add(cuenta5);
		return this.cuentas;
	}
}