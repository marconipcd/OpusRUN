package domain;

import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="planos_acesso")
@Cacheable(value=false)
public class PlanoAcesso {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID",nullable=false, unique=true)
	private Integer id;
	
	@OneToOne
	@JoinColumn(name="contratos_id")
	private ContratosAcesso contrato_acesso;
		
	@Column(name="NOME",nullable=false, unique=false, length=50)
	private String nome;
	@Column(name="DOWNLOAD",nullable=false, unique=false, length=50)
	private String download;
	@Column(name="UPLOAD",nullable=false, unique=false, length=50)
	private String upload;
	@Column(name="RATE_LIMIT",nullable=false, unique=false, length=100)
	private String rate_limit;
	@Column(name="SHARED_USERS",nullable=false, unique=false, length=10)
	private String shared_users;
	@Column(name="SESSION_TIMEOUT",nullable=false, unique=false, length=50)
	private String session_timeout;
	@Column(name="KEEPALIVE_TIMEOUT",nullable=false, unique=false, length=50)
	private String keepalive_timeout;
	@Column(name="IDLE_TIMEOUT",nullable=false, unique=false, length=50)
	private String idle_timeout;
	@Column(name="STATUS_AUTOREFRESH",nullable=false, unique=false, length=5)
	private String status_autorefresh;
	@Column(name="VALOR",nullable=false, unique=false, length=50)
	private String valor;
	
	@Column(name="STATUS",nullable=false, unique=false, length=50)
	private String status;
	
	@Column(name="QTD_CENSURA",nullable=false, unique=false, length=50)
	private Integer qtd_censura;
	
	@Column(name="DESCONTO")
	private String desconto;
	@Column(name="TAXA_BOLETO")
	private String taxa_boleto;
	
	@OneToOne
	@JoinColumn(name="PLANO_RENOVACAO", nullable=true)
	private PlanoAcesso plano_Renovacao;
	
	@Column(name="SERVICO_PLANO_ID")
	private Integer servico_plano_id;
	
	public PlanoAcesso(){
		
	}
	
	public PlanoAcesso(Integer codPlano){
		this.id = codPlano;
	}
	

	public PlanoAcesso(Integer id, String nome,
			String download, String upload, String rate_limit,
			String shared_users, String session_timeout,
			String keepalive_timeout, String idle_timeout,
			String status_autorefresh, String status) {
		super();
		this.id = id;
		this.nome = nome;
		this.download = download;
		this.upload = upload;
		this.rate_limit = rate_limit;
		this.shared_users = shared_users;
		this.session_timeout = session_timeout;
		this.keepalive_timeout = keepalive_timeout;
		this.idle_timeout = idle_timeout;
		this.status_autorefresh = status_autorefresh;
		this.status = status;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDownload() {
		return download;
	}

	public void setDownload(String download) {
		this.download = download;
	}

	public String getUpload() {
		return upload;
	}

	public void setUpload(String upload) {
		this.upload = upload;
	}

	public String getRate_limit() {
		return rate_limit;
	}

	public void setRate_limit(String rate_limit) {
		this.rate_limit = rate_limit;
	}

	public String getShared_users() {
		return shared_users;
	}

	public void setShared_users(String shared_users) {
		this.shared_users = shared_users;
	}

	public String getSession_timeout() {
		return session_timeout;
	}

	public void setSession_timeout(String session_timeout) {
		this.session_timeout = session_timeout;
	}

	public String getKeepalive_timeout() {
		return keepalive_timeout;
	}

	public void setKeepalive_timeout(String keepalive_timeout) {
		this.keepalive_timeout = keepalive_timeout;
	}

	public String getIdle_timeout() {
		return idle_timeout;
	}

	public void setIdle_timeout(String idle_timeout) {
		this.idle_timeout = idle_timeout;
	}

	public String getStatus_autorefresh() {
		return status_autorefresh;
	}

	public void setStatus_autorefresh(String status_autorefresh) {
		this.status_autorefresh = status_autorefresh;
	}


	public ContratosAcesso getContrato_acesso() {
		return contrato_acesso;
	}


	public void setContrato_acesso(ContratosAcesso contrato_acesso) {
		this.contrato_acesso = contrato_acesso;
	}
	
	public String getValor() {
		return valor;
	}


	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getQtd_censura() {
		return qtd_censura;
	}

	public void setQtd_censura(Integer qtd_censura) {
		this.qtd_censura = qtd_censura;
	}

	public String getDesconto() {
		return desconto;
	}

	public void setDesconto(String desconto) {
		this.desconto = desconto;
	}

	public String getTaxa_boleto() {
		return taxa_boleto;
	}

	public void setTaxa_boleto(String taxa_boleto) {
		this.taxa_boleto = taxa_boleto;
	}

	public PlanoAcesso getPlano_Renovacao() {
		return plano_Renovacao;
	}

	public void setPlano_Renovacao(PlanoAcesso plano_Renovacao) {
		this.plano_Renovacao = plano_Renovacao;
	}

	public Integer getServico_plano_id() {
		return servico_plano_id;
	}

	public void setServico_plano_id(Integer servico_plano_id) {
		this.servico_plano_id = servico_plano_id;
	}
  
	
	
	
}
