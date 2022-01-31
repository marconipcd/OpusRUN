package domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="radusergroup")
public class RadUserGroup {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID", unique=true,nullable=false)
	private Integer id;
	@Column(name="username", nullable=false)
	private String username;
	@Column(name="groupname", nullable=false)
	private String groupname;
	@Column(name="priority", nullable=false)
	private String priority;
	
	public RadUserGroup(){
		
	}

	public RadUserGroup(Integer id, String username, String groupname,
			String priority) {
		super();
		this.id = id;
		this.username = username;
		this.groupname = groupname;
		this.priority = priority;
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

	public String getGroupname() {
		return groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}
	
	
}
