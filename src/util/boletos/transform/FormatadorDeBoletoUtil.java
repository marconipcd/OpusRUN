package util.boletos.transform;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class FormatadorDeBoletoUtil {

	private static NumberFormat formatador = NumberFormat
			.getNumberInstance(new Locale("pt", "BR"));
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); 
	
	static {
		formatador.setMinimumFractionDigits(2);
		formatador.setMaximumFractionDigits(2);
	}
	
	static String formataData(final Calendar data) {
		return sdf.format(data.getTime());
	}

	static String formataValor(final double valor) {
		return formatador.format(valor);
	}
}
