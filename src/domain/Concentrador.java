package domain;



import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="bases")
@Cacheable(value=false)
public class Concentrador {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID", unique=true, nullable=false)
	private Integer id;
	
	@Column(name="EMPRESA_ID", unique=false, nullable=false)
	private Integer empresa_id;
	
	@Column(name="NOME", unique=false, nullable=false, length=100)
	private String identificacao;
	
	
	@Column(name="BASE_ID", nullable=false)
	private Integer base_id;
	
	@Column(name="MODELO")
	private String modelo;
	
	@Column(name="ANTENA")
	private String antena;		
	
	@Column(name="ENDERECO_IP", unique=false, nullable=false, length=20)
	private String endereco_ip;
	
	@Column(name="USUARIO", unique=false, nullable=false,length=40)
	private String usuario;
	
	@Column(name="SENHA", unique=false, nullable=false, length=40)
	private String senha;
	
	@Column(name="WIRELESS", unique=false, nullable=false, length=3)
	private String wireless;
	
	@Column(name="PORTA_API", unique=false, nullable=false, length=20)
	private String porta_api;
	
	@Column(name="OBS")
	private String observacao;
	
	@Column(name="INFRAESTRUTURA")
	private String infraestrutura;
	
	@Column(name="STATUS")
	private String status;
	
	@Column(name="LOOP_BACK", unique=false, nullable=false, length=20)
	private String loop_back;
	
	public Concentrador(){
		
	}

	public Concentrador(Integer id) {
		super();
		this.id = id;
	}
	
	
	

	public Concentrador(Integer id, Integer empresa_id, String identificacao,
			Integer base_id, String modelo, String antena, String endereco_ip,
			String usuario, String senha, String wireless, String porta_api,
			String observacao, String infraestrutura, String status,
			String loop_back) {
		super();
		this.id = id;
		this.empresa_id = empresa_id;
		this.identificacao = identificacao;
		this.base_id = base_id;
		this.modelo = modelo;
		this.antena = antena;
		this.endereco_ip = endereco_ip;
		this.usuario = usuario;
		this.senha = senha;
		this.wireless = wireless;
		this.porta_api = porta_api;
		this.observacao = observacao;
		this.infraestrutura = infraestrutura;
		this.status = status;
		this.loop_back = loop_back;
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

	

	
	public String getEndereco_ip() {
		return endereco_ip;
	}

	public void setEndereco_ip(String endereco_ip) {
		this.endereco_ip = endereco_ip;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getWireless() {
		return wireless;
	}

	public void setWireless(String wireless) {
		this.wireless = wireless;
	}

	public String getPorta_api() {
		return porta_api;
	}

	public void setPorta_api(String porta_api) {
		this.porta_api = porta_api;
	}

	
	

	public Integer getBase_id() {
		return base_id;
	}

	public void setBase_id(Integer base_id) {
		this.base_id = base_id;
	}

	public String getIdentificacao() {
		return identificacao;
	}

	public void setIdentificacao(String identificacao) {
		this.identificacao = identificacao;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getAntena() {
		return antena;
	}

	public void setAntena(String antena) {
		this.antena = antena;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}


	public String getInfraestrutura() {
		return infraestrutura;
	}

	public void setInfraestrutura(String infraestrutura) {
		this.infraestrutura = infraestrutura;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLoop_back() {
		return loop_back;
	}

	public void setLoop_back(String loop_back) {
		this.loop_back = loop_back;
	}
	
	
	
}

