package domain;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="veiculos")
@Cacheable(value=false)
public class Veiculos {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID", nullable=false,unique=true)
	private Integer id;	
	
	@Column(name="EMPRESA_ID", nullable=false, unique=false)
    private Integer empresa_id;	
	
	@Column(name="COD_VEICULO",nullable=false, unique=false, length=10)
	private String cod_veiculo;	
	
	@Column(name="TIPO",nullable=false, unique=false, length=200)
	private String tipo;	
	
	@Column(name="MARCA",nullable=false, unique=false, length=200)
	private String marca;	
	
	@Column(name="MODELO",nullable=false, unique=false, length=200)
	private String  modelo;	
	
	@Column(name="GARANTIA",nullable=false, unique=false, length=200)
	private String  garantia;
	
	@Column(name="COR",nullable=false, unique=false, length=200)
	private String cor;	
	
	@Column(name="OPCIONAIS",nullable=false, unique=false, length=200)
	private String opcionais;	
	
	@Column(name="PLACA",nullable=false, unique=false, length=10)
	private String placa;	
	
	@Column(name="CHASSI",nullable=false, unique=false, length=100)
	private String chassi;	
	
	@Column(name="COMBUSTIVEL",nullable=false, unique=false, length=100)
	private String combustivel;
	
	@Column(name="ANO_FAB",nullable=false, unique=false, length=10)
	private String  ano_fab;

	@Column(name="ANO_MODELO",nullable=false, unique=false, length=10)
	private String  ano_modelo;	
	
	@Column(name="CPF_CNPJ_PROPRIETARIO",nullable=false, unique=false, length=30)
	private String cpf_cnpj_proprietario;
	
	@Column(name="NOME_PROPRIETARIO",nullable=false, unique=false, length=100)
	private String nome_proprietario;
	
	@Column(name="VENC_IPVA_IMPOSTOS",nullable=false, unique=false)
	private Date venc_ipva_impostos;//data	
	
	@Column(name="SEGURADORA",nullable=false, unique=false, length=10)
	private Integer seguradora;	
	
	@Column(name="REVISAO_GERAL",nullable=false, unique=false, length=30)
	private Integer revisao_geral;	
	
	@Column(name="KM_ATUAL",nullable=false, unique=false, length=30)
	private Integer km_atual;
	
	@Column(name="DATA_ULTIMA_REVISAO",nullable=false, unique=false)
	private Date data_ultima_revisao;//data
	
	@Column(name="VENC_SEGURO",nullable=false, unique=false)
	private Date venc_seguro;//data
	
	@Column(name="DATA_CADASTRO", nullable=false, unique=false)
	@Basic(optional=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date data_cadastro; //ok
	
	@Column(name="DATA_GARANTIA",nullable=false, unique=false)
	private Date data_garantia;//data
	
	@Column(nullable=true, unique=false, length=100, name="STATUS")
	private String status;
	
	
	public Veiculos(Integer id) {
		this.id = id;
	}

	public Veiculos() {
	}
	
	@PrePersist
    protected void onCreate() {
		data_cadastro = new Date();
    }

	public Veiculos(String cod_veiculo, Integer empresa_id, String	tipo, 	String marca, 	String modelo,
			String garantia,String cor,	String	opcionais,String placa,String	chassi, String	combustivel, String	ano_fab, 
			String	ano_modelo, String cpf_cnpj_proprietario, String nome_proprietario,Date	venc_ipva_impostos, Integer	km_atual,
			Date data_ultima_revisao,Date	venc_seguro,Date	data_cadastro,Date 	data_garantia, String status) {
			
			this.cod_veiculo = cod_veiculo;
			this.empresa_id = empresa_id;
	        this.tipo = tipo;
	        this.marca = marca;
	        this.modelo = modelo;
	        this.garantia = garantia;
	        this.cor = cor;
	        this.opcionais = opcionais;
	        this.placa = placa;
	        this.chassi = chassi;
	        this.combustivel = combustivel;
	        this.ano_fab = ano_fab;
	        this.ano_modelo = ano_modelo;
	        this.cpf_cnpj_proprietario = cpf_cnpj_proprietario;
	        this.nome_proprietario = nome_proprietario;
	        this.venc_ipva_impostos = venc_ipva_impostos;
	        this.km_atual = km_atual;
	        this.data_ultima_revisao = data_ultima_revisao;
	        this.venc_seguro = venc_seguro;
	        this.data_cadastro = data_cadastro;
	        this.data_garantia = data_garantia;
	        this.status = status;
		
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

	public String getCod_veiculo() {
		return cod_veiculo;
	}

	public void setCod_veiculo(String cod_veiculo) {
		this.cod_veiculo = cod_veiculo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getGarantia() {
		return garantia;
	}

	public void setGarantia(String garantia) {
		this.garantia = garantia;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public String getOpcionais() {
		return opcionais;
	}

	public void setOpcionais(String opcionais) {
		this.opcionais = opcionais;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getChassi() {
		return chassi;
	}

	public void setChassi(String chassi) {
		this.chassi = chassi;
	}

	public String getCombustivel() {
		return combustivel;
	}

	public void setCombustivel(String combustivel) {
		this.combustivel = combustivel;
	}

	public String getAno_fab() {
		return ano_fab;
	}

	public void setAno_fab(String ano_fab) {
		this.ano_fab = ano_fab;
	}

	public String getAno_modelo() {
		return ano_modelo;
	}

	public void setAno_modelo(String ano_modelo) {
		this.ano_modelo = ano_modelo;
	}

	public String getCpf_cnpj_proprietario() {
		return cpf_cnpj_proprietario;
	}

	public void setCpf_cnpj_proprietario(String cpf_cnpj_proprietario) {
		this.cpf_cnpj_proprietario = cpf_cnpj_proprietario;
	}

	public String getNome_proprietario() {
		return nome_proprietario;
	}

	public void setNome_proprietario(String nome_proprietario) {
		this.nome_proprietario = nome_proprietario;
	}

	public Date getVenc_ipva_impostos() {
		return venc_ipva_impostos;
	}

	public void setVenc_ipva_impostos(Date venc_ipva_impostos) {
		this.venc_ipva_impostos = venc_ipva_impostos;
	}

	public Integer getSeguradora() {
		return seguradora;
	}

	public void setSeguradora(Integer seguradora) {
		this.seguradora = seguradora;
	}

	public Integer getRevisao_geral() {
		return revisao_geral;
	}

	public void setRevisao_geral(Integer revisao_geral) {
		this.revisao_geral = revisao_geral;
	}

	public Integer getKm_atual() {
		return km_atual;
	}

	public void setKm_atual(Integer km_atual) {
		this.km_atual = km_atual;
	}

	public Date getData_ultima_revisao() {
		return data_ultima_revisao;
	}

	public void setData_ultima_revisao(Date data_ultima_revisao) {
		this.data_ultima_revisao = data_ultima_revisao;
	}

	public Date getVenc_seguro() {
		return venc_seguro;
	}

	public void setVenc_seguro(Date venc_seguro) {
		this.venc_seguro = venc_seguro;
	}

	public Date getData_cadastro() {
		return data_cadastro;
	}

	public void setData_cadastro(Date data_cadastro) {
		this.data_cadastro = data_cadastro;
	}

	public Date getData_garantia() {
		return data_garantia;
	}

	public void setData_garantia(Date data_garantia) {
		this.data_garantia = data_garantia;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	


	
}
