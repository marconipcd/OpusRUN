package util.mk_bean;

import br.com.eits.m4j.bean.ann.MkMapping;

public class Profile {

	@MkMapping(from="id")
    private String id;
    @MkMapping(from="name")
    private String name;
    @MkMapping(from="idle-timeout")
    private String idleTimeout;
    @MkMapping(from="shared-users")
    private String sharedUsers;
    @MkMapping(from="rate-limit")
    private String rateLimit;
 
    public void print() {
 
        StringBuilder builder = new StringBuilder();
 
        builder.append("[{id=");
        builder.append(id);
        builder.append("}{name=");
        builder.append(name);
        builder.append("}{idle-timeout=");
        builder.append(idleTimeout);
        builder.append("}{shared-users=");
        builder.append(sharedUsers);
        builder.append("}{rate-limit=");
        builder.append(rateLimit);
        builder.append("}]");
 
        System.out.println(builder.toString());
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdleTimeout() {
		return idleTimeout;
	}

	public void setIdleTimeout(String idleTimeout) {
		this.idleTimeout = idleTimeout;
	}

	public String getSharedUsers() {
		return sharedUsers;
	}

	public void setSharedUsers(String sharedUsers) {
		this.sharedUsers = sharedUsers;
	}

	public String getRateLimit() {
		return rateLimit;
	}

	public void setRateLimit(String rateLimit) {
		this.rateLimit = rateLimit;
	}
    
    
    
    
    

}
