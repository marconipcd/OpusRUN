package util.boletos.boleto;

public class EmissorNovo {
	private int agencia;
	private char digitoAgencia;
	private String contaCorrente;
	private int carteira;
	private long numeroConvenio;
	private String nossoNumero;
	private String cedente;
	private char digitoContaCorrente;
	private String digitoNossoNumero;
	private int codigoOperacao;
	private int codigoFornecidoPelaAgencia;
	private String endereco;
	private String posto_beneficiario;

	private EmissorNovo() {
	}

	/**
	 * @return um novo Emissor
	 */
	public static EmissorNovo novoEmissor() {
		return new EmissorNovo();
	}
	
	public EmissorNovo comPostoBeneficiario(String postoBeneficiario){
		this.posto_beneficiario = postoBeneficiario;
		return this;
	}
	
	public String getPostoBeneficiario(){
		return posto_beneficiario;
	}

	/**
	 * @param endereco, que será associado ao emissor
	 * @return este emissor
	 */
	public EmissorNovo comEndereco(String endereco) {
		this.endereco = endereco;
		return this;
	}

	/**
	 * @return o endereço do Emissor
	 */
	public String getEndereco() {
		return endereco;
	}

	/**
	 * @return número da agencia sem o digito
	 */
	public int getAgencia() {
		return this.agencia;
	}

	/**
	 * @param agencia, que deverá ser informada
	 * <strong>sem</strong> o digito verificador
	 * 
	 * @return este emissor
	 */
	public EmissorNovo comAgencia(int agencia) {
		this.agencia = agencia;
		return this;
	}

	/**
	 * @return número da conta corrente sem o digito
	 */
	public String getContaCorrente() {
		return this.contaCorrente;
	}

	/**
	 * @param contaCorrente, que deverá ser informada
	 * <strong>sem</strong> o digito verificador.
	 * 
	 * @return este emissor
	 */
	public EmissorNovo comContaCorrente(String contaCorrente) {
		this.contaCorrente = contaCorrente;
		return this;
	}

	/**
	 * @return carteira <br/> Valor informado pelo 
	 * banco para identificação do tipo de boleto
	 */
	public int getCarteira() {
		return this.carteira;
	}

	/**
	 * @param carteira <br/> Valor informado pelo 
	 * banco para identificação do tipo de boleto
	 * 
	 * @return este emissor
	 */
	public EmissorNovo comCarteira(int carteira) {
		this.carteira = carteira;
		return this;
	}

	/**
	 * @return número do convênio <br/> Valor que identifica 
	 * um emissor junto ao seu banco para associar seus boletos
	 */
	public long getNumeroConvenio() {
		return this.numeroConvenio;
	}

	/**
	 * @param numConvenio <br/> Valor que identifica um 
	 * emissor junto ao seu banco para associar seus boletos
	 * 
	 * @return este emissor
	 */
	public EmissorNovo comNumeroConvenio(long numConvenio) {
		this.numeroConvenio = numConvenio;
		return this;
	}

	/**
	 * @return nosso número <br/> Valor que o cedente escolhe 
	 * para manter controle sobre seus boletos. Esse valor serve 
	 * para o cedente identificar quais boletos foram pagos ou não.
	 * Recomenda-se o uso de números sequenciais, na geração de 
	 * diversos boletos, para facilitar a identificação dos pagos
	 */
	public String getNossoNumero() {
		return this.nossoNumero;
	}

	/**
	 * @param nossoNumero <br/> Valor que o cedente escolhe 
	 * para manter controle sobre seus boletos. Esse valor serve 
	 * para o cedente identificar quais boletos foram pagos ou não.
	 * Recomenda-se o uso de números sequenciais, na geração de 
	 * diversos boletos, para facilitar a identificação dos pagos
	 * 
	 * @return este emissor
	 */
	public EmissorNovo comNossoNumero(String nossoNumero) {
		this.nossoNumero = nossoNumero;
		return this;
	}

	/**
	 * @return cedente deste emissor (nome fornecido para boleto)
	 */
	public String getCedente() {
		return this.cedente;
	}

	/**
	 * @param cedente, que será associado a este emissor.
	 * @return este emissor
	 */
	public EmissorNovo comCedente(String cedente) {
		this.cedente = cedente;
		return this;
	}

	/**
	 * @return digito verificador (DV) da conta corrente
	 */
	public char getDigitoContaCorrente() {
		return this.digitoContaCorrente;
	}

	/**
	 * @param digito - verificador (DV) da conta corrente
	 * @return este emissor
	 */
	public EmissorNovo comDigitoContaCorrente(char digito) {
		this.digitoContaCorrente = digito;
		return this;
	}

	/**
	 * @return digito verificador (DV) da agencia
	 */
	public char getDigitoAgencia() {
		return this.digitoAgencia;
	}

	/**
	 * @param digito - verificador (DV) da agencia
	 * @return este emissor
	 */
	public EmissorNovo comDigitoAgencia(char digito) {
		this.digitoAgencia = digito;
		return this;
	}

	/**
	 * @return agencia formatada com 4 digitos
	 * Para o valor de agencia 123 retorna a String 0123
	 */
	public String getAgenciaFormatado() {
		String valor = String.valueOf(this.agencia);
		valor = String.format("%04d", Integer.parseInt(valor));
		return valor.substring(0, 4);
	}

	/**
	 * @return código de operação do emissor
	 */
	public int getCodigoOperacao() {
		return this.codigoOperacao;
	}

	/**
	 * @param codigoOperacao, que será associado ao emissor
	 * @return este emissor
	 */
	public EmissorNovo comCodigoOperacao(int codigoOperacao) {
		this.codigoOperacao = codigoOperacao;
		return this;
	}

	/**
	 * @return código fornecido pela agência do emissor.
	 */
	public int getCodigoFornecidoPelaAgencia() {
		return this.codigoFornecidoPelaAgencia;
	}

	/**
	 * @param codigoFornecidoPelaAgencia, que será associado ao emissor
	 * @return este emissor
	 */
	public EmissorNovo comCodigoFornecidoPelaAgencia(int codigoFornecidoPelaAgencia) {
		this.codigoFornecidoPelaAgencia = codigoFornecidoPelaAgencia;
		return this;
	}

	/**
	 * @param digitoNossoNumero, que será associado ao emissor
	 * @return este emissor
	 */
	public EmissorNovo comDigitoNossoNumero(String digitoNossoNumero) {
		this.digitoNossoNumero = digitoNossoNumero;
		return this;
	}

	/**
	 * @return digito verificador do nosso número associado ao emissor
	 */
	public String getDigitoNossoNumero() {
		return this.digitoNossoNumero;
	}
}

