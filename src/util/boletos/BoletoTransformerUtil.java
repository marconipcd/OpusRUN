package util.boletos;

import java.io.InputStream;

import br.com.caelum.stella.boleto.Boleto;
import br.com.caelum.stella.boleto.transformer.BoletoWriter;

public class BoletoTransformerUtil {

	private final BoletoWriter writer;

	public BoletoTransformerUtil(BoletoWriter writer) {
		this.writer = writer;
	}

	/**
	 * Gera o boleto em memoria e aloca-o em um InputStream.
	 * 
	 * @param boleto
	 * 
	 */
	public InputStream transform(Boleto... boletos) {

		boolean firstPage = true;
		Integer numberPage = 0;
		for (Boleto boleto : boletos) {
			numberPage++;
			if (numberPage % 2 == 0) {
				this.writer.newPage();
			}
			this.writer.write(boleto);
			firstPage = false;
		}
		return this.writer.toInputStream();
	}
}
