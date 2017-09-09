package ui.windows;


import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.WindowOwner;

import parserArchivos.CsvFile;
import ui.vm.ArchivoCuentasImportadosViewModel;

@SuppressWarnings("serial")
public class ArchivoCuentasImportadosWindow extends Dialog<ArchivoCuentasImportadosViewModel> {


		public ArchivoCuentasImportadosWindow(WindowOwner cargaExitosaWindow) {
			super(cargaExitosaWindow, new ArchivoCuentasImportadosViewModel());
		}

		@Override
		protected void createFormPanel(Panel mainPanel) {
			this.setTitle("Archivos de cuentas importados");
			
			new Label(mainPanel).setText("Archivos .csv de empresas y cuentas cargados");
			
			Table<CsvFile> csvFilesTable = new Table<CsvFile>(mainPanel, CsvFile.class);
			
			
			csvFilesTable.bindItemsToProperty("archivosImportados");
			new Column<CsvFile>(csvFilesTable).setTitle("Nombre").bindContentsToProperty("nombre");
			new Column<CsvFile>(csvFilesTable).setTitle("Directorio").bindContentsToProperty("directorio");
			
			
			new Button(mainPanel).setCaption("Volver al Menu Principal")
			.onClick(() -> {
					this.getDelegate().close();
					MenuWindow();
			});
		}
		
		public void MenuWindow()  {
			Dialog<?> dialog = new MenuWindow(this);
			dialog.open();
			dialog.onAccept(() -> {});
		}
}
