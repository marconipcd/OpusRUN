package domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="conta_bancaria")
public class ContaBancaria {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private Integer id;
	@Column(name="NOME")
	private String nome;
	@Column(name="EMPRESA_ID")
	private Integer empresa_id; 
	@Column(name="COD_BANCO")
	private String cod_banco;
	@Column(name="NOME_BANCO")
	private String nome_banco; 
	@Column(name="AGENCIA_BANCO")
	private String agencia_banco; 
	@Column(name="N_CONTA")
	private String n_conta; 
	@Column(name="COD_BENEFICIARIO")
	private String cod_beneficiario; 
	@Column(name="CONVENIO")
	private String convenio; 
	@Column(name="CONTRATO")
	private String contrato; 
	@Column(name="CARTEIRA")
	private String carteira; 
	@Column(name="VARIACAO_CARTEIRA")
	private String variacao_carteira; 
	@Column(name="IDENTIFICACAO")
	private String identificacao; 
	@Column(name="CPF_CNPJ")
	private String cpf_cnpj; 
	@Column(name="ENDERECO")
	private String endereco; 
	@Column(name="CIDADE_UF")
	private String cidade_uf; 
	@Column(name="CEDENTE")
	private String cedente; 
	@Column(name="MULTA")
	private String multa; 
	@Column(name="JUROS")
	private String juros; 
	@Column(name="TOLERANCIA")
	private String tolerancia; 
	@Column(name="TAXA_BOLETO")
	private String taxa_boleto; 
	@Column(name="DEMONSTRATIVO1")
	private String demonstrativo1; 
	@Column(name="DEMONSTRATIVO2")
	private String demonstrativo2; 
	@Column(name="DEMONSTRATIVO3")
	private String demonstrativo3; 
	@Column(name="INSTRUCOES1")
	private String instrucoes1; 
	@Column(name="INSTRUCOES2")
	private String instrucoes2; 
	@Column(name="INSTRUCOES3")
	private String instrucoes3;
	@Column(name="TIPO")
	private String tipo;
	@Column(name="TOLERANCIA_JUROS_MULTA")
	private String tolerancia_juros_multa;
	@Column(name="TAXA_NEGATIVACAO")
	private double taxa_negativacao;
	@Column(name="ATUALIZACAO_MONETARIA")
	private double atualizacao_monetaria;
	
	@Column(name="POSTO_BENEFICIARIO")
	private String posto_beneficiario;
	
	
	public ContaBancaria()
	{
		
	}

	

	public ContaBancaria(Integer id, Integer empresa_id, String cod_banco,
			String nome_banco, String agencia_banco, String n_conta,
			String convenio, String contrato, String carteira,
			String variacao_carteira, String identificacao, String cpf_cnpj,
			String endereco, String cidade_uf, String cedente, String multa,
			String juros, String tolerancia, String taxa_boleto,
			String demonstrativo1, String demonstrativo2,
			String demonstrativo3, String instrucoes1, String instrucoes2,
			String instrucoes3, String tipo) {
		super();
		this.id = id;
		this.empresa_id = empresa_id;
		this.cod_banco = cod_banco;
		this.nome_banco = nome_banco;
		this.agencia_banco = agencia_banco;
		this.n_conta = n_conta;
		this.convenio = convenio;
		this.contrato = contrato;
		this.carteira = carteira;
		this.variacao_carteira = variacao_carteira;
		this.identificacao = identificacao;
		this.cpf_cnpj = cpf_cnpj;
		this.endereco = endereco;
		this.cidade_uf = cidade_uf;
		this.cedente = cedente;
		this.multa = multa;
		this.juros = juros;
		this.tolerancia = tolerancia;
		this.taxa_boleto = taxa_boleto;
		this.demonstrativo1 = demonstrativo1;
		this.demonstrativo2 = demonstrativo2;
		this.demonstrativo3 = demonstrativo3;
		this.instrucoes1 = instrucoes1;
		this.instrucoes2 = instrucoes2;
		this.instrucoes3 = instrucoes3;
		this.tipo = tipo;
	}

    

	public String getCod_beneficiario() {
		return cod_beneficiario;
	}



	public void setCod_beneficiario(String cod_beneficiario) {
		this.cod_beneficiario = cod_beneficiario;
	}



	public String getCod_banco() {
		return cod_banco;
	}



	public void setCod_banco(String cod_banco) {
		this.cod_banco = cod_banco;
	}



	public String getTipo() {
		return tipo;
	}



	public void setTipo(String tipo) {
		this.tipo = tipo;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getEmpresa_id() {
		return empresa_id;
	}

	public void setEmpresa_id(Integer empresa_id) {
		this.empresa_id = empresa_id;
	}

	public String getNome_banco() {
		return nome_banco;
	}

	public void setNome_banco(String nome_banco) {
		this.nome_banco = nome_banco;
	}

	public String getAgencia_banco() {
		return agencia_banco;
	}

	public void setAgencia_banco(String agencia_banco) {
		this.agencia_banco = agencia_banco;
	}

	public String getN_conta() {
		return n_conta;
	}

	public void setN_conta(String n_conta) {
		this.n_conta = n_conta;
	}

	public String getConvenio() {
		return convenio;
	}

	public void setConvenio(String convenio) {
		this.convenio = convenio;
	}

	public String getContrato() {
		return contrato;
	}

	public void setContrato(String contrato) {
		this.contrato = contrato;
	}

	public String getCarteira() {
		return carteira;
	}

	public void setCarteira(String carteira) {
		this.carteira = carteira;
	}

	public String getVariacao_carteira() {
		return variacao_carteira;
	}

	public void setVariacao_carteira(String variacao_carteira) {
		this.variacao_carteira = variacao_carteira;
	}

	public String getIdentificacao() {
		return identificacao;
	}

	public void setIdentificacao(String identificacao) {
		this.identificacao = identificacao;
	}

	public String getCpf_cnpj() {
		return cpf_cnpj;
	}

	public void setCpf_cnpj(String cpf_cnpj) {
		this.cpf_cnpj = cpf_cnpj;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getCidade_uf() {
		return cidade_uf;
	}

	public void setCidade_uf(String cidade_uf) {
		this.cidade_uf = cidade_uf;
	}

	public String getCedente() {
		return cedente;
	}

	public void setCedente(String cedente) {
		this.cedente = cedente;
	}

	public String getMulta() {
		return multa;
	}

	public void setMulta(String multa) {
		this.multa = multa;
	}

	public String getJuros() {
		return juros;
	}

	public void setJuros(String juros) {
		this.juros = juros;
	}

	public String getTolerancia() {
		return tolerancia;
	}

	public void setTolerancia(String tolerancia) {
		this.tolerancia = tolerancia;
	}

	public String getTaxa_boleto() {
		return taxa_boleto;
	}

	public void setTaxa_boleto(String taxa_boleto) {
		this.taxa_boleto = taxa_boleto;
	}

	public String getDemonstrativo1() {
		return demonstrativo1;
	}

	public void setDemonstrativo1(String demonstrativo1) {
		this.demonstrativo1 = demonstrativo1;
	}

	public String getDemonstrativo2() {
		return demonstrativo2;
	}

	public void setDemonstrativo2(String demonstrativo2) {
		this.demonstrativo2 = demonstrativo2;
	}

	public String getDemonstrativo3() {
		return demonstrativo3;
	}

	public void setDemonstrativo3(String demonstrativo3) {
		this.demonstrativo3 = demonstrativo3;
	}

	public String getInstrucoes1() {
		return instrucoes1;
	}

	public void setInstrucoes1(String instrucoes1) {
		this.instrucoes1 = instrucoes1;
	}

	public String getInstrucoes2() {
		return instrucoes2;
	}

	public void setInstrucoes2(String instrucoes2) {
		this.instrucoes2 = instrucoes2;
	}

	public String getInstrucoes3() {
		return instrucoes3;
	}

	public void setInstrucoes3(String instrucoes3) {
		this.instrucoes3 = instrucoes3;
	}



	public String getNome() {
		return nome;
	}



	public void setNome(String nome) {
		this.nome = nome;
	}



	public String getTolerancia_juros_multa() {
		return tolerancia_juros_multa;
	}



	public void setTolerancia_juros_multa(String tolerancia_juros_multa) {
		this.tolerancia_juros_multa = tolerancia_juros_multa;
	}



	public double getTaxa_negativacao() {
		return taxa_negativacao;
	}



	public void setTaxa_negativacao(double taxa_negativacao) {
		this.taxa_negativacao = taxa_negativacao;
	}



	public double getAtualizacao_monetaria() {
		return atualizacao_monetaria;
	}



	public void setAtualizacao_monetaria(double atualizacao_monetaria) {
		this.atualizacao_monetaria = atualizacao_monetaria;
	}



	public String getPosto_beneficiario() {
		return posto_beneficiario;
	}



	public void setPosto_beneficiario(String posto_beneficiario) {
		this.posto_beneficiario = posto_beneficiario;
	}
	
	
	
}
