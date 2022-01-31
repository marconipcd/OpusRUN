package domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="seriais_produto")
public class SeriaisProduto {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private Integer id;
	
	@Column(name="PRODUTO_ID")
	private Integer produto_id;
	
	@Column(name="SERIAL")
	private String serial;
	
	@Column(name="STATUS")
	private String status;
	
	@Column(name="DATA_CADASTRO")
	private Date data_cadastro;
	
	@Column(name="DATA_ALTERACAO")
	private Date data_alteracao;
	
	@Column(name="TIPO_SERIAL")
	private String tipo_serial;
	
	public SeriaisProduto(){
		
	}

	public SeriaisProduto(Integer id, Integer produto_id, String serial,
			String status, Date data_cadastro, Date data_alteracao,
			String tipo_serial) {
		super();
		this.id = id;
		this.produto_id = produto_id;
		this.serial = serial;
		this.status = status;
		this.data_cadastro = data_cadastro;
		this.data_alteracao = data_alteracao;
		this.tipo_serial = tipo_serial;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProduto_id() {
		return produto_id;
	}

	public void setProduto_id(Integer produto_id) {
		this.produto_id = produto_id;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getData_cadastro() {
		return data_cadastro;
	}

	public void setData_cadastro(Date data_cadastro) {
		this.data_cadastro = data_cadastro;
	}

	public Date getData_alteracao() {
		return data_alteracao;
	}

	public void setData_alteracao(Date data_alteracao) {
		this.data_alteracao = data_alteracao;
	}

	public String getTipo_serial() {
		return tipo_serial;
	}

	public void setTipo_serial(String tipo_serial) {
		this.tipo_serial = tipo_serial;
	}
	
	

}
