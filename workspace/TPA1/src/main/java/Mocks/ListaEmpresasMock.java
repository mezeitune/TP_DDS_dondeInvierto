package Mocks;

import java.util.ArrayList;
import java.util.List;

import usuario.Cuenta;
import usuario.Empresa;

public class ListaEmpresasMock {
	
	List<Empresa> empresas = new ArrayList<>();
	List<Cuenta> cuentas = new ArrayList<>();
	
	public List<Empresa> mockearListaEmpresas(){
		List<Cuenta> cuentasEmpresa1 = new ArrayList<>();
		Cuenta cuenta1 = new Cuenta("EBITDA","2016",200);
		Cuenta cuenta2 = new Cuenta("EBITDA","2015",300);
		Cuenta cuenta3 = new Cuenta("Free CashFlow","2015",1100);
		Cuenta cuenta4 = new Cuenta("Free CashFlow","2016",900);
		cuentasEmpresa1.add(cuenta1);
		cuentasEmpresa1.add(cuenta2);
		cuentasEmpresa1.add(cuenta3);
		cuentasEmpresa1.add(cuenta4);
		Empresa empresa1 = new Empresa(/*"Facebook", cuentasEmpresa1*/);
		empresa1.setNombre("Facebook");
		empresa1.setCuentas(cuentasEmpresa1);
		
		
		List<Cuenta> cuentasEmpresa2 = new ArrayList<>();
		Cuenta cuenta5 = new Cuenta("Free CashFlow","2016",50);
		Cuenta cuenta6 = new Cuenta("Revenue","2010",1000);
		Cuenta cuenta7 = new Cuenta("EBITDA","2016",400);
		Cuenta cuenta8 = new Cuenta("Revenue","2016",1000);
		cuentasEmpresa2.add(cuenta5);
		cuentasEmpresa2.add(cuenta6);
		cuentasEmpresa2.add(cuenta7);
		cuentasEmpresa2.add(cuenta8);
		Empresa empresa2 = new Empresa(/*"Apple", cuentasEmpresa2*/);
		empresa2.setCuentas(cuentasEmpresa2);
		empresa2.setNombre("Apple");
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
		Cuenta cuenta6 = new Cuenta("Free CashFlow","2016",900);
		this.cuentas.add(cuenta1);
		this.cuentas.add(cuenta2);
		this.cuentas.add(cuenta3);
		this.cuentas.add(cuenta4);
		this.cuentas.add(cuenta5);
		this.cuentas.add(cuenta6);
		return this.cuentas;
	}
}