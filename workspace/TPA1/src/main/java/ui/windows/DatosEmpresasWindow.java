package ui.windows;

import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.WindowOwner;

import java.awt.Color;
import java.io.IOException;

import org.uqbar.arena.bindings.PropertyAdapter;
import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.List;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import ui.vm.DatosEmpresasViewModel;
import usuario.*;

@SuppressWarnings("serial")
public class DatosEmpresasWindow extends Dialog<DatosEmpresasViewModel> {
	
	
	
	public DatosEmpresasWindow(WindowOwner parent) throws IOException {
		super(parent, new DatosEmpresasViewModel());
	}
	
	protected void createFormPanel(Panel mainPanel) {
		this.setTitle("Consultor de cuentas");
		
		
		Panel form = new Panel(mainPanel);
		form.setLayout(new ColumnLayout(1));
		
		
		new Label(form).setText("Seleccione una empresa ").setBackground(Color.ORANGE);
		Selector<Empresa> selectorEmpresa = new Selector<Empresa>(form).allowNull(true);
		selectorEmpresa.setWidth(100);
		selectorEmpresa.bindItemsToProperty("empresas").setAdapter(new PropertyAdapter(Empresa.class, "nombre"));
		selectorEmpresa.bindValueToProperty("empresa");
		
		new Label(form).setText("Empresa Seleccionada").setBackground(Color.ORANGE);
		new Label(form).bindValueToProperty("empresa.nombre");
		
		new Label(form).setText("Seleccione un Per�odo ").setBackground(Color.ORANGE);
		Selector<String> selectorPeriodo = new Selector<String>(form).allowNull(true);
		selectorPeriodo.setWidth(100);
		selectorPeriodo.bindItemsToProperty("empresa.cuentas").setAdapter(new PropertyAdapter(Cuenta.class, "periodo"));
		selectorPeriodo.bindValueToProperty("periodoo");
		
		new Label(form).setText("Periodo Seleccionado").setBackground(Color.ORANGE);
		new Label(form).bindValueToProperty("periodoo");
		
		new Label(form).setText("Cuentas").setBackground(Color.ORANGE);

		
		Table<Cuenta> table = new Table<Cuenta>(mainPanel, Cuenta.class);
		
		
		table.bindItemsToProperty("empresa.cuentas");
		
		
		
		new Column<Cuenta>(table).setTitle("Nombre").bindContentsToProperty("nombre");
		new Column<Cuenta>(table).setTitle("Valor").bindContentsToProperty("valor");
		
	}
	
	
	
	protected void addActions(Panel actionsPanel) {
		// TODO Auto-generated method stub
		
	}
	

}		