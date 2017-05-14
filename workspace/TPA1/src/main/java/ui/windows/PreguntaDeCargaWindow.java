package ui.windows;

import java.io.IOException;

import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.WindowOwner;

import ui.vm.PreguntaDeCargaViewModel;

@SuppressWarnings("serial")
public class PreguntaDeCargaWindow extends Dialog<PreguntaDeCargaViewModel> {

	public PreguntaDeCargaWindow(WindowOwner owner) {
		super(owner, new PreguntaDeCargaViewModel());
	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		
		Panel form = new Panel(mainPanel);
		form.setLayout(new ColumnLayout(2));
		
		new Label(mainPanel).setText("¿Desea agregar un nuevo identificador?");
				
	}
	
	protected void addActions(Panel actionsPanel){
		
		new Button(actionsPanel).setCaption("Si")
								.onClick(() -> {
												try{
													this.getDelegate().close();
													CargarIndicadoresWindow();
												}catch (IOException e) {
													e.printStackTrace();
												}
								});
	
		new Button(actionsPanel).setCaption("No")
									.onClick(() -> {
													try{
														this.getDelegate().close();
														MenuWindow();
													}catch (IOException e) {
														e.printStackTrace();
													}
									});
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
