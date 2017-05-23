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
		
		new Label(mainPanel).setText("Se cargo exitosamente el archivo");
				
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
									.setWidth(200);

	}
	
	
	public void MenuWindow() throws IOException {
		Dialog<?> dialog = new MenuWindow(this);
		dialog.open();
		dialog.onAccept(() -> {});
	}

}
