package parser;

import com.opencsv.bean.CsvBind;


@SuppressWarnings("deprecation")
public class CSVObject {

		@CsvBind
		private String empresa;
		@CsvBind
		private String cuenta;
		@CsvBind
		private String periodo;
		@CsvBind
		private int valor;
		
		public String getEmpresa(){
			return empresa;
		}
		
		public String getCuenta(){
			return cuenta;
		}
		
		public String getPeriodo(){
			return periodo;
		}
		
		public int getValor(){
			return valor;
		}
}
