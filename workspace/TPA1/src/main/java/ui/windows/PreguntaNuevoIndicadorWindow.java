package ui.windows;

import java.io.IOException;

import org.omg.CORBA.UserException;
import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.WindowOwner;

import parserFormulaInidicador.ParserFormulaToIndicador;
import ui.vm.CargarIndicadoresViewModel;
import ui.vm.PreguntaNuevoIndicadorViewModel;


public class PreguntaNuevoIndicadorWindow extends Dialog<PreguntaNuevoIndicadorViewModel> {
	
	public PreguntaNuevoIndicadorWindow (WindowOwner owner) {
		super(owner, new PreguntaNuevoIndicadorViewModel());
	}
	protected void createFormPanel(Panel mainPanel) {
		
		Panel form = new Panel(mainPanel);
		form.setLayout(new ColumnLayout(2));
		
		new Label(mainPanel).setText("�Desea cargar un nuevo indicador?");
		
				
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
								}).setWidth(200);

		
		new Button(actionsPanel).setCaption("No")
								.onClick(() -> {
									
												try{
													this.getDelegate().close();
													MenuWindow();
												}catch (IOException e) {
													e.printStackTrace();
												}
								}).setWidth(200);
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
