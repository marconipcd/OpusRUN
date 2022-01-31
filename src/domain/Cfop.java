package domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cfop")
public class Cfop {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID", nullable=false, unique=true)
	private Integer id;
	@Column(name="DESCRICAO", nullable=false)
	private String descricao;
	@Column(name="STATUS", nullable=false)
	private String status;
	@Column(name="DEFAULT_NFE_MOD21")
	private boolean default_nfe_mod21;
	
	public Cfop(){
		
	}

	public Cfop(Integer id, String descricao, String status) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean isDefault_nfe_mod21() {
		return default_nfe_mod21;
	}

	public void setDefault_nfe_mod21(boolean default_nfe_mod21) {
		this.default_nfe_mod21 = default_nfe_mod21;
	}
	
	
	
}
