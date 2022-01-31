package domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="tipos_subgrupo")
public class TipoSubGrupoOse {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID", nullable=false, unique=true)
	private Integer id;
	
	@OneToOne
	@JoinColumn(name="SUB_GRUPO_OSE_ID")
	private SubGrupoOse subgrupo_id;
	
	@Column(name="NOME", nullable=false)
	private String nome;
	
	@Column(name="DATA_CADASTRO", nullable=false)
	private Date data_cadastro;
	
	@Column(name="VALOR", nullable=true)
	private Double valor;
	
	@Column(name="STATUS")
	private String status;
	
	public TipoSubGrupoOse()
	{
		
	}

	public TipoSubGrupoOse(Integer id, SubGrupoOse subgrupo_id, String nome,
			Date data_cadastro) {
		super();
		this.id = id;
		this.subgrupo_id = subgrupo_id;
		this.nome = nome;
		this.data_cadastro = data_cadastro;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public SubGrupoOse getSubgrupo_id() {
		return subgrupo_id;
	}

	public void setSubgrupo_id(SubGrupoOse subgrupo_id) {
		this.subgrupo_id = subgrupo_id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getData_cadastro() {
		return data_cadastro;
	}

	public void setData_cadastro(Date data_cadastro) {
		this.data_cadastro = data_cadastro;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
