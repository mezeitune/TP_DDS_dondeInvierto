package ui.windows;

import java.io.IOException;

import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.WindowOwner;

import ui.vm.PreguntaNuevaMetodologiaViewModel;

public class PreguntaNuevaMetodologiaWindow extends Dialog<PreguntaNuevaMetodologiaViewModel>{

	public PreguntaNuevaMetodologiaWindow (WindowOwner owner) {
		super(owner, new PreguntaNuevaMetodologiaViewModel());
	}
	protected void createFormPanel(Panel mainPanel) {
		
		Panel form = new Panel(mainPanel);
		form.setLayout(new ColumnLayout(2));
		
		new Label(mainPanel).setText("Metodologia cargada correctamente, Desea cargar una nueva metodologia?");
		
				
	}
	protected void addActions(Panel actionsPanel){
		new Button(actionsPanel).setCaption("Si")
								.onClick(() -> {
												this.getDelegate().close();
								}).setWidth(200);

		
		new Button(actionsPanel).setCaption("No")
								.onClick(() -> {
												this.getDelegate().close();
												MenuWindow();
												
								}).setWidth(200);
	}
	
	public void MenuWindow()  {
		Dialog<?> dialog = new MenuWindow(this);
		dialog.open();
		dialog.onAccept(() -> {});
	}
	
	public void CargarMetodologiaWindow() throws IOException {
		Dialog<?> dialog = new CargarMetodologiaWindow(this);
		dialog.open();
		dialog.onAccept(() -> {});
	}
	
}
