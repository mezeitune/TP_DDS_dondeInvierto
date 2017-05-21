package ui.windows;

import java.io.IOException;

import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.WindowOwner;

import ui.vm.CargaExitosaViewModel;




@SuppressWarnings("serial")
public class CargaExitosaWindow extends Dialog<CargaExitosaViewModel>{
	
	public CargaExitosaWindow (WindowOwner owner) {
		super(owner, new CargaExitosaViewModel());
	}
	@Override
	protected void createFormPanel(Panel mainPanel) {
		
		Panel form = new Panel(mainPanel);
		form.setLayout(new ColumnLayout(2));
		
		new Label(mainPanel).setText("Se ha Cargado Con Exito");
				
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
									})
									.setWidth(160);
		new Button(actionsPanel).setCaption("Volver a cargar")
		.onClick(() -> {
						try{
							this.getDelegate().close();
							CargarIndicadoresWindow();
						}catch (IOException e) {
							e.printStackTrace();
						}
		})
		.setWidth(160);
	}
	
	
	public void MenuWindow() throws IOException {
		Dialog<?> dialog = new MenuWindow(this);
		dialog.open();
		dialog.onAccept(() -> {});
	}
	public void CargarIndicadoresWindow() throws IOException {
		Dialog<?> dialog = new CargarIndicadoresWindow(this);
		dialog.open();
		dialog.onAccept(() -> {});
	}

}
