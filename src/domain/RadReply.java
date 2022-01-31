package domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.persistence.Entity;

@Entity
@Table(name="radreply")
public class RadReply {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id", nullable=false, unique=true)
	private Integer id;
	@Column(name="username", nullable=false)
	private String username;
	@Column(name="attribute", nullable=false)
	private String attribute;
	@Column(name="op", nullable=false)
	private String op;
	@Column(name="value", nullable=false)
	private String value;
	
	public RadReply(){
		
	}

	public RadReply(Integer id, String username, String attribute, String op,
			String value) {
		super();
		this.id = id;
		this.username = username;
		this.attribute = attribute;
		this.op = op;
		this.value = value;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
}
