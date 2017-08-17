package ui.windows;

import java.awt.Color;

import org.uqbar.arena.bindings.PropertyAdapter;
import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.WindowOwner;


import ui.vm.EliminarEmpresaViewModel;
import usuario.Empresa;

public class EliminarEmpresaWindow extends Dialog<EliminarEmpresaViewModel>{
	public EliminarEmpresaWindow(WindowOwner parent)  {
		super(parent, new EliminarEmpresaViewModel());
	}
	protected void createFormPanel(Panel mainPanel) {
		mainPanel.setLayout(new HorizontalLayout());
		Panel Panel = new Panel(mainPanel);
		
		new Label(Panel).setText("Seleccione una empresa ").setBackground(Color.ORANGE);
		Selector<Empresa> selectorEmpresa = new Selector<Empresa>(Panel).allowNull(true);
		selectorEmpresa.setWidth(100);
		selectorEmpresa.bindItemsToProperty("empresas").setAdapter(new PropertyAdapter(Empresa.class, "nombre"));
		selectorEmpresa.bindValueToProperty("empresa");
		
	}

	protected void addActions(Panel actionsPanel){
		
		new Button(actionsPanel).setCaption("Eliminar")
		.onClick(() -> {
							this.getDelegate().close();
							MetodologiasEmpresasWindow();
			});
		new Button(actionsPanel).setCaption("Volver")
		.onClick(() -> {
							this.getDelegate().close();
							MetodologiasEmpresasWindow();
				});
	}
	
	public void MetodologiasEmpresasWindow()  {
		Dialog<?> dialog = new MetodologiasEmpresasWindow(this);
		dialog.open();
		dialog.onAccept(() -> {});
	}
}	
