package ui.windows;

import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.WindowOwner;

import java.awt.Color;
import java.io.IOException;

import org.uqbar.arena.bindings.PropertyAdapter;
import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import ui.vm.DatosEmpresasViewModel;
import usuario.*;

@SuppressWarnings("serial")
public class DatosEmpresasWindow extends Dialog<DatosEmpresasViewModel> {
	
	
	
	public DatosEmpresasWindow(WindowOwner parent) {
		super(parent, new DatosEmpresasViewModel());
	}
	
	protected void createFormPanel(Panel mainPanel) {
		this.setTitle("Consultor de cuentas");
		
		
		Panel form = new Panel(mainPanel);
		form.setLayout(new ColumnLayout(1));
		
		
		new Label(form).setText("Seleccione una empresa para ver sus cuentas").setBackground(Color.ORANGE);
		Selector<Empresa> selectorEmpresa = new Selector<Empresa>(form).allowNull(true);
		selectorEmpresa.bindItemsToProperty("empresas").setAdapter(new PropertyAdapter(Empresa.class, "nombre"));
		selectorEmpresa.bindValueToProperty("empresa");
		
		new Label(form).setText("Empresa Seleccionada").setBackground(Color.ORANGE);
		new Label(form).bindValueToProperty("empresa.nombre");
		new Label(form).setText("Cuentas").setBackground(Color.ORANGE);

		
		Table<Cuenta> table = new Table<Cuenta>(mainPanel, Cuenta.class);
		
		
		table.bindItemsToProperty("empresa.cuentas");
		table.bindValueToProperty("cuenta");
		
		new Column<Cuenta>(table).setTitle("Nombre").bindContentsToProperty("nombre");
		new Column<Cuenta>(table).setTitle("Valor").bindContentsToProperty("valor");
		new Column<Cuenta>(table).setTitle("Periodo").bindContentsToProperty("periodo");
	
		
	}
	
	
	
	protected void addActions(Panel actionsPanel) {
		// TODO Auto-generated method stub
		
	}
	

}		