package ui.windows;

import java.awt.Color;

import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.WindowOwner;

import excepciones.FormulaIndicadorNotFound;
import excepciones.FormulaIndicadorNotValidException;
import excepciones.IndicadorRepetidoException;
import excepciones.NombreIndicadorNotFound;
import ui.vm.CargarBDViewModel;
import ui.vm.CargarIndicadoresViewModel;
import usuario.Indicador;

@SuppressWarnings("serial")
public class CargarBDWindow extends Dialog<CargarBDViewModel> {

	public CargarBDWindow(WindowOwner owner) {
		super(owner, new CargarBDViewModel());
	}
	
	@Override
	protected void createFormPanel(Panel mainPanel) {
		
		this.setTitle("Carga y consultar BD");
		
		Panel form = new Panel(mainPanel);
		form.setLayout(new ColumnLayout(2));
		
		new Label(form).setText("Escriba el id del Indicador").setBackground(Color.orange);
		
		new TextBox(form).setWidth(300).bindValueToProperty("idIndicador");
		
	
		
		new Label(form).setText("Escriba la formula del Indicador").setBackground(Color.orange);
		
		new TextBox(form).setWidth(300).bindValueToProperty("formulaIndicador");
		
		new Label(form).setText("Resultado Indicador").setBackground(Color.green);
		
		new Label(form).setBackground(Color.GREEN).bindValueToProperty("resultadoIndicador");
		
		

		
		
	}
	
	
	protected void addActions(Panel actionsPanel){
		
		new Button(actionsPanel).setCaption("Consultar por ID")
		.onClick(() -> {
			this.getModelObject().traerResultadoPorID();
		}).setWidth(250);
		
		new Button(actionsPanel).setCaption("Consultar por formula")
		.onClick(() -> {
				this.getModelObject().traerResultadoPorFormula();
		}).setWidth(250);
	
		new Button(actionsPanel).setCaption("Volver")
		.onClick(() -> {
			this.getDelegate().close();
			MenuWindow();
		}).setWidth(200);
	}
	
	
	public void MenuWindow(){
		Dialog<?> dialog = new MenuWindow(this);
		dialog.open();
		dialog.onAccept(() -> {});
	}
	public void PreguntaNuevoIndicadorWindow()  {
		Dialog<?> dialog = new PreguntaNuevoIndicadorWindow(this);
		dialog.open();
		dialog.onAccept(() -> {});
		
	}
	
	
}
