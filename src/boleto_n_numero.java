import com.thoughtworks.xstream.annotations.XStreamAlias;


@XStreamAlias("boleto_n_numero")
public class boleto_n_numero {

	private Integer ID;
	private String N_NUMERO_SICRED;
	
	public boleto_n_numero(Integer iD, String n_NUMERO_SICRED) {
		super();
		ID = iD;
		N_NUMERO_SICRED = n_NUMERO_SICRED;
	}
	public Integer getID() {
		return ID;
	}
	public void setID(Integer iD) {
		ID = iD;
	}
	public String getN_NUMERO_SICRED() {
		return N_NUMERO_SICRED;
	}
	public void setN_NUMERO_SICRED(String n_NUMERO_SICRED) {
		N_NUMERO_SICRED = n_NUMERO_SICRED;
	}
	
	
}
