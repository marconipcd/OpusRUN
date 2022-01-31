package util.mk_bean;

import br.com.eits.m4j.bean.ann.MkMapping;

public class Layer7Protocol {

	@MkMapping(from="id")
	private String id;
	
	@MkMapping(from="id")
	private String name;
	
	@MkMapping(from="id")
	private String regexp;
	
	@MkMapping(from="id")
	private String comment;

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

	public String getRegexp() {
		return regexp;
	}

	public void setRegexp(String regexp) {
		this.regexp = regexp;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
}
