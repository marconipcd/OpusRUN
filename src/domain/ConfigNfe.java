package domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="config_nfe")
public class ConfigNfe {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID", nullable=false)
	private Integer id;
	@Column(name="VALOR_APROX_TRIB")
	private double valor_aprox_trib;
	@Column(name="OBSERVACAO")
	private String observacao;	
		
	@Column(name="SERVER_EMAIL")
	private String server_email;
	@Column(name="PORTA_EMAIL")
	private String porta_email;
	@Column(name="LOGIN")
	private String login;
	@Column(name="SENHA")
	private String senha;
	@Column(name="COPIA_OCULTA")
	private String email_copia_oculta;
	@Column(name="MENSAGEM_EMAIL")
	private String msg_email;
		
	public ConfigNfe(){
		
	}
	
	public ConfigNfe(Integer id, double valor_aprox_trib, String observacao,
			String server_email, String porta_email, String login,
			String senha, String email_copia_oculta,String msg_email) {
		super();
		this.id = id;
		this.valor_aprox_trib = valor_aprox_trib;
		this.observacao = observacao;
		this.server_email = server_email;
		this.porta_email = porta_email;
		this.login = login;
		this.senha = senha;
		this.email_copia_oculta = email_copia_oculta;
		this.msg_email = msg_email;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public double getValor_aprox_trib() {
		return valor_aprox_trib;
	}
	public void setValor_aprox_trib(double valor_aprox_trib) {
		this.valor_aprox_trib = valor_aprox_trib;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public String getServer_email() {
		return server_email;
	}
	public void setServer_email(String server_email) {
		this.server_email = server_email;
	}
	public String getPorta_email() {
		return porta_email;
	}
	public void setPorta_email(String porta_email) {
		this.porta_email = porta_email;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getMsg_email() {
		return msg_email;
	}
	public void setMsg_email(String msg_email) {
		this.msg_email = msg_email;
	}
	public String getEmail_copia_oculta() {
		return email_copia_oculta;
	}
	public void setEmail_copia_oculta(String email_copia_oculta) {
		this.email_copia_oculta = email_copia_oculta;
	}
	
}
