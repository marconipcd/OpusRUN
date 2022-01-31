package util;

import java.text.DecimalFormat;

public class Real {

	public static String formatDbToString(String valor){
		try{
			Double valorDouble = Double.parseDouble(valor);															  
			DecimalFormat df = new DecimalFormat();  
			df.applyPattern("#,##0.00");  			
					
			String valorFormatado = df.format(valorDouble);
			
			return valorFormatado;
		}catch(Exception e){
			e.printStackTrace();
			return valor;
		}
	}
	public static String formatStringToDB(String valor){
		if(!valor.isEmpty())
		return valor.replace(".", "").replace(",", ".");
		else
		return null;
	}
	
	public static Double formatStringToDBDouble(String valor){	
		if(valor != null && !valor.isEmpty() && !valor.equals("")){
			return new Double(valor.replace(".", "").replace(",", "."));
		}
		
		return null;
	}
	
	public static Float formatStringToDBFloat(String valor){	
		if(valor != null && !valor.isEmpty())
		return new Float(valor.replace(".", "").replace(",", "."));
		else
		return null;
	}
}
