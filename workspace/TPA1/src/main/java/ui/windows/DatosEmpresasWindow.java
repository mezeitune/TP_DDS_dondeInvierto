package ui.windows;

import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.WindowOwner;
import java.awt.Color;
import java.io.IOException;

import org.uqbar.arena.bindings.PropertyAdapter;
import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
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
		form.setLayout(new ColumnLayout(2));
		
		
		new Label(form).setText("Seleccione una empresa ").setBackground(Color.ORANGE);
		Selector<Empresa> selectorEmpresa = new Selector<Empresa>(form).allowNull(true);
		selectorEmpresa.setWidth(100);
		selectorEmpresa.bindItemsToProperty("empresas").setAdapter(new PropertyAdapter(Empresa.class, "nombre"));
		selectorEmpresa.bindValueToProperty("empresa");
		

		new Label(form).setText("Seleccione un Periodo ").setBackground(Color.ORANGE);
		Selector<String> selectorPeriodo = new Selector<String>(form).allowNull(true);
		selectorPeriodo.setWidth(100);
		selectorPeriodo.bindItemsToProperty("periodos");
		selectorPeriodo.bindValueToProperty("periodo");
		
		new Label(form).setText("Cuentas").setBackground(Color.ORANGE);

		
		Table<Cuenta> table = new Table<Cuenta>(mainPanel, Cuenta.class);
		
		
		table.bindItemsToProperty("cuentasFiltradas");

		new Column<Cuenta>(table).setTitle("Nombre").bindContentsToProperty("nombre");
		new Column<Cuenta>(table).setTitle("Valor").bindContentsToProperty("valor");
		new Column<Cuenta>(table).setTitle("Periodo").bindContentsToProperty("periodo");
		
	}
	
	protected void addActions(Panel actionsPanel){
		new Button(actionsPanel).setCaption("Volver al Menu Principal")
								.onClick(() -> {
												try{
													this.getDelegate().close();
													MenuWindow();
												}catch (IOException e) {
													e.printStackTrace();
												}
								});
		new Button(actionsPanel).setCaption("Seleccionar otro archivo")
								.onClick(() -> {
												try {
													this.getDelegate().close();
													CargaArchivoEmpresaWindow();
												} catch (IOException e) {
													e.printStackTrace();
												}
								});
							}
	
	public void MenuWindow() throws IOException {
		Dialog<?> dialog = new MenuWindow(this);
		dialog.open();
		dialog.onAccept(() -> {});
	}
	public void CargaArchivoEmpresaWindow() throws IOException {
		Dialog<?> dialog = new SeleccionarArchivoWindow(this);
		dialog.open();
		dialog.onAccept(() -> {});
	}
}		