package domain;

import java.util.Date;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



/**
 *
 * @author Marconi
 */
@Entity
@Table(name="empresa")
@Cacheable(value=false)
public class Empresa {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="ID", nullable=false, unique=true)
    private Integer id;
    @Column(name="RAZAO_SOCIAL", nullable=false, unique=false, length=200)
    private String razao_social;
    @Column(name="NOME_FANTASIA", nullable=false, unique=false, length=200)
    private String nome_fantasia;
    @Column(name="RESPONSAVEL", nullable=true, unique=false, length=200)
    private String responsavel;
   	@Column(name="CNPJ", nullable=false, unique=false, length=50)
    private String cnpj;

    @Column(name="INSCRICAO_ESTADUAL", nullable=false, unique=false, length=100)
    private String inscricao_estadual;
    @Column(name="INSCRICAO_MUNICIPAL", nullable=false, unique=false, length=100)
    private String inscricao_municipal;
    @Column(name="SOCIO1", nullable=true, unique=false, length=100)
    private String socio1;
    @Column(name="SOCIO2", nullable=true, unique=false, length=100)
    private String socio2;
    @Column(name="MATRIZ_FILIAL", nullable=false, unique=false, length=1)
    private String matriz_filial;
    @Column(name="ENDERECO", nullable=false, unique=false, length=200)
    private String endereco;
    @Column(name="COMPLEMENTO", nullable=false, unique=false, length=100)
    private String complemento;
    @Column(name="BAIRRO", nullable=false, unique=false, length=50)
    private String bairro;
    @Column(name="CIDADE", nullable=false, unique=false, length=50)
    private String cidade;
    @Column(name="UF", nullable=false, unique=false, length=2)
    private String uf;
    @Column(name="CEP", nullable=false, unique=false, length=8)
    private String cep;
    @Column(name="REFERENCIA", nullable=true, unique=false, length=100)
    private String referencia;
    @Column(name="PAIS", nullable=true, unique=false, length=100)
    private String pais;
    @Column(name="NUMERO", nullable=true, unique=false, length=30)
    private String numero;
    @Column(name="DDD_FONE1", nullable=false, unique=false, length=2)
    private String ddd_fone1;
    @Column(name="FONE1", nullable=false, unique=false, length=10)
    private String fone1;
    @Column(name="DDD_FONE2", nullable=true, unique=false, length=2)
    private String ddd_fone2;
    @Column(name="FONE2", nullable=true, unique=false, length=10)
    private String fone2;
    @Column(name="DDD_FONE3", nullable=true, unique=false, length=2)
    private String ddd_fone3;
    @Column(name="FONE3", nullable=true, unique=false, length=10)
    private String fone3;
    @Column(name="_0800", nullable=true, unique=false, length=11)
    private String _0800;
    @Column(name="EMAIL", nullable=true, unique=false, length=100)
    private String email;
    @Column(name="SITE", nullable=true, unique=false, length=100)
    private String site;
    @Column(name="END_LOGO_AREA_CLIENTE", nullable=false, unique=false, length=500)
    private String end_logo_area_cliente;
    @Column(name="END_LOGO_BOLETO", nullable=false, unique=false, length=500)
    private String end_logo_boleto;
    @Column(name="END_LOGO_GUIA_CANCELAMENTO", nullable=false, unique=false, length=500)
    private String end_logo_guia_cancelamento; 
    @Column(name="LOGO", nullable=true, unique=false, length=500)
    private String logo;
    @Column(name="OPTA_SIMPLES", nullable=true, unique=false, length=3)
    private String opta_pelo_simples;
    @Column(name="DATA_IMPLANTACAO", nullable=false, unique=false)
    private Date data_implantacao;
    @Column(name="STATUS", nullable=true, unique=false)
    private String status;
    
    @Column(name="ALIQUOTA_INTERNA")
    private double aliquota_interna;
    
    @Column(name="DATA_CADASTRO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date data_cadastro;
    
    @Column(name="DATA_ALTERACAO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date data_alteracao;
    
    @Lob
    private byte[] logo_empresa;
    

	
//    @OneToMany(mappedBy="empresa")
//    private List<setoresUsuario> setoresUsuario;
    
    
    
//    @ManyToMany
//    @JoinTable(name = "modulos_empresa", joinColumns = @JoinColumn(name = "empresa_id"),inverseJoinColumns = @JoinColumn(name = "modulo_id"))
//    @OrderBy(value="ordem")
//    private List<Modulo> modulos;
    
    @PrePersist
    protected void onCreate(){
    	data_alteracao = data_cadastro = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
    	data_alteracao = new Date();
    }
    
    
    public Empresa() {
    }
    public Empresa(Integer id) {
    	super();
    	this.id = id;
    }
    
    // CRIAR FUN��ES DE CADASTRO E ALTERA��O DE DATAS
    public Date getData_ateracao() {
		return data_alteracao;
	}
	public void setData_ateracao(Date data_ateracao) {
		this.data_alteracao = data_ateracao;
	}
	
	
	
	public Empresa(String razao_social, String nome_fantasia,
			String responsavel, String cnpj,
			Integer cenae_fiscal, String inscricao_estadual,
			String inscricao_municipal, String socio1, String socio2, String matriz_filial,
			String endereco, String complemento, String bairro, String cidade,
			String uf, String cep, String referencia, String pais,
			String numero, String fone1, String fone2, String fone3,
			String _0800, String email, String site,
			String end_logo_area_cliente, String end_logo_boleto,
			String end_logo_guia_cancelamento, String logo,
			String opta_pelo_simples,
			Date data_implantacao, String status, Date data_cadastro)
			{
		super();
		this.razao_social = razao_social;
		this.nome_fantasia = nome_fantasia;
		this.responsavel = responsavel;
		this.cnpj = cnpj;
		this.inscricao_estadual = inscricao_estadual;
		this.inscricao_municipal = inscricao_municipal;
		this.socio1 = socio1;
		this.socio2 = socio2;
		this.matriz_filial = matriz_filial;
		this.endereco = endereco;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cidade = cidade;
		this.uf = uf;
		this.cep = cep;
		this.referencia = referencia;
		this.pais = pais;
		this.numero = numero;
		this.fone1 = fone1;
		this.fone2 = fone2;
		this.fone3 = fone3;
		this._0800 = _0800;
		this.email = email;
		this.site = site;
		this.end_logo_area_cliente = end_logo_area_cliente;
		this.end_logo_boleto = end_logo_boleto;
		this.end_logo_guia_cancelamento = end_logo_guia_cancelamento;
		this.logo = logo;
		this.opta_pelo_simples = opta_pelo_simples;
		this.data_implantacao = data_implantacao;
		this.status = status;
		this.data_cadastro = data_cadastro;
	}
	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
//
//    public String getCpfResponsavel() {
//		return cpfResponsavel;
//	}
//	public void setCpfResponsavel(String cpfResponsavel) {
//		this.cpfResponsavel = cpfResponsavel;
//	}
	
    public String getResponsavel() {
		return responsavel;
	}
	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}
	
		public String getRazao_social() {
        return razao_social;
    }

    public void setRazao_social(String razao_social) {
        this.razao_social = razao_social;
    }

        public String getNome_fantasia() {
        return nome_fantasia;
    }

    public void setNome_fantasia(String nome_fantasia) {
        this.nome_fantasia = nome_fantasia;
    }

        public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

        public String getInscricao_estadual() {
        return inscricao_estadual;
    }

    public void setInscricao_estadual(String inscricao_estadual) {
        this.inscricao_estadual = inscricao_estadual;
    }

	public String getSocio1() {
		return socio1;
	}
	public void setSocio1(String socio1) {
		this.socio1 = socio1;
	}
	public String getSocio2() {
		return socio2;
	}
	public void setSocio2(String socio2) {
		this.socio2 = socio2;
	}
//	public String getCpfSocio1() {
//		return cpfSocio1;
//	}
//	public void setCpfSocio1(String cpfSocio1) {
//		this.cpfSocio1 = cpfSocio1;
//	}
//	public String getCpfSocio2() {
//		return cpfSocio2;
//	}
//	public void setCpfSocio2(String cpfSocio2) {
//		this.cpfSocio2 = cpfSocio2;
//	}
	public String getReferencia() {
		return referencia;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getFone3() {
		return fone3;
	}
	public void setFone3(String fone3) {
		this.fone3 = fone3;
	}
	public String get_0800() {
		return _0800;
	}
	public void set_0800(String _0800) {
		this._0800 = _0800;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}	
	public String getOpta_pelo_simples() {
		return opta_pelo_simples;
	}
	public void setOpta_pelo_simples(String opta_pelo_simples) {
		this.opta_pelo_simples = opta_pelo_simples;
	}
	public Date getData_implantacao() {
		return data_implantacao;
	}
	public void setData_implantacao(Date data_implantacao) {
		this.data_implantacao = data_implantacao;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	

        public String getInscricao_municipal() {
		return inscricao_municipal;
	}
	public void setInscricao_municipal(String inscricao_municipal) {
		this.inscricao_municipal = inscricao_municipal;
	}
		public String getMatriz_filial() {
        return matriz_filial;
    }

    public void setMatriz_filial(String matriz_filial) {
        this.matriz_filial = matriz_filial;
    }

        public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

        public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

        public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

        public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

        public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

        public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

        public String getFone1() {
        return fone1;
    }

    public void setFone1(String fone1) {
        this.fone1 = fone1;
    }

        public String getFone2() {
        return fone2;
    }

    public void setFone2(String fone2) {
        this.fone2 = fone2;
    }

        public String getEnd_logo_area_cliente() {
        return end_logo_area_cliente;
    }

    public void setEnd_logo_area_cliente(String end_logo_area_cliente) {
        this.end_logo_area_cliente = end_logo_area_cliente;
    }

        public String getEnd_logo_boleto() {
        return end_logo_boleto;
    }

    public void setEnd_logo_boleto(String end_logo_boleto) {
        this.end_logo_boleto = end_logo_boleto;
    }

        public String getEnd_logo_guia_cancelamento() {
        return end_logo_guia_cancelamento;
    }

    public void setEnd_logo_guia_cancelamento(String end_logo_guia_cancelamento) {
        this.end_logo_guia_cancelamento = end_logo_guia_cancelamento;
    }

        public Date getData_cadastro() {
        return data_cadastro;
    }

    public void setData_cadastro(Date data_cadastro) {
        this.data_cadastro = data_cadastro;
    }  

	public String getDdd_fone1() {
		return ddd_fone1;
	}
	public void setDdd_fone1(String ddd_fone1) {
		this.ddd_fone1 = ddd_fone1;
	}
	public String getDdd_fone2() {
		return ddd_fone2;
	}
	public void setDdd_fone2(String ddd_fone2) {
		this.ddd_fone2 = ddd_fone2;
	}
	public String getDdd_fone3() {
		return ddd_fone3;
	}
	public void setDdd_fone3(String ddd_fone3) {
		this.ddd_fone3 = ddd_fone3;
	}
	public byte[] getLogo_empresa() {
		return logo_empresa;
	}
	public void setLogo_empresa(byte[] logo_empresa) {
		this.logo_empresa = logo_empresa;
	}
	public double getAliquota_interna() {
		return aliquota_interna;
	}
	public void setAliquota_interna(double aliquota_interna) {
		this.aliquota_interna = aliquota_interna;
	}
	
    
    
}
