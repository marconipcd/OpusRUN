package util.boletos.boleto.bancos;


import br.com.caelum.stella.boleto.bancos.gerador.GeradorDeDigito;
import br.com.caelum.stella.boleto.bancos.gerador.GeradorDeDigitoPadrao;

import util.boletos.boleto.Banco;
import util.boletos.boleto.Emissor;

public abstract class AbstractBanco implements Banco {

	protected final GeradorDeDigito geradorDeDigito = new GeradorDeDigitoPadrao();

	@Override
	public GeradorDeDigito getGeradorDeDigito() {
		return geradorDeDigito;
	}

	@Override
	public String getNossoNumeroECodDocumento(Emissor emissor) {
		StringBuilder builder = new StringBuilder().append(emissor.getCarteira());
		System.out.println("carteira" + emissor.getCarteira());
		builder.append("/").append(getNossoNumeroDoEmissorFormatado(emissor));
		return builder.toString();
	}

	@Override
	public String getAgenciaECodigoCedente(Emissor emissor) {
		StringBuilder builder = new StringBuilder();
		builder.append(emissor.getAgenciaFormatado());
		builder.append("-").append(emissor.getDigitoAgencia());
		builder.append(getContaCorrenteDoEmissorFormatado(emissor));
		builder.append("-").append(emissor.getDigitoContaCorrente());
		return builder.toString();
	}

}