package domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="crm_perguntas_rel")
public class CrmPerguntasRel {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private Integer id;
	
	@Column(name="COD_CRM")
	private Integer cod_crm;
	
	@Column(name="COD_PERGUNTA")
	private Integer cod_pergunta;
	
	@Column(name="PERGUNTA")
	private String pergunta;
	
	@Column(name="RESPOSTA")
	private String resposta;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATA_CADASTRO")
	private Date data_cadastro;
	
	public CrmPerguntasRel(){
		
	}
	
	public CrmPerguntasRel(Integer id, Integer cod_crm, Integer cod_pergunta, String pergunta, String resposta, Date data_Cadastro){
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCod_crm() {
		return cod_crm;
	}

	public void setCod_crm(Integer cod_crm) {
		this.cod_crm = cod_crm;
	}

	public Integer getCod_pergunta() {
		return cod_pergunta;
	}

	public void setCod_pergunta(Integer cod_pergunta) {
		this.cod_pergunta = cod_pergunta;
	}

	public String getPergunta() {
		return pergunta;
	}

	public void setPergunta(String pergunta) {
		this.pergunta = pergunta;
	}

	public String getResposta() {
		return resposta;
	}

	public void setResposta(String resposta) {
		this.resposta = resposta;
	}

	public Date getData_cadastro() {
		return data_cadastro;
	}

	public void setData_cadastro(Date data_cadastro) {
		this.data_cadastro = data_cadastro;
	}
	
	
}
