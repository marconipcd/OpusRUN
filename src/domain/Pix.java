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
@Table(name="pix_boleto")
public class Pix {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private Integer id;
	
	@Column(name="COD_BOLETO")
	private Integer cod_boleto;
	
	@Column(name="COD_PIX")
	private String cod_pix;
	
	@Column(name="VALOR_PIX")
	private double valor_pix;
	
	@Column(name="QRCODE")
	private String qrcode;
	
	@Column(name="QRCODE_IMG")
	private String qrcode_img;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATA")
	private Date data;
	
	@Column(name="STATUS")
	private String status;
	
	public Pix(){
		
	}

	public Pix(Integer id, Integer cod_boleto, String cod_pix,
			double valor_pix, String qrcode, String qrcode_img, Date data,
			String status) {
		super();
		this.id = id;
		this.cod_boleto = cod_boleto;
		this.cod_pix = cod_pix;
		this.valor_pix = valor_pix;
		this.qrcode = qrcode;
		this.qrcode_img = qrcode_img;
		this.data = data;
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCod_boleto() {
		return cod_boleto;
	}

	public void setCod_boleto(Integer cod_boleto) {
		this.cod_boleto = cod_boleto;
	}

	public String getCod_pix() {
		return cod_pix;
	}

	public void setCod_pix(String cod_pix) {
		this.cod_pix = cod_pix;
	}

	public double getValor_pix() {
		return valor_pix;
	}

	public void setValor_pix(double valor_pix) {
		this.valor_pix = valor_pix;
	}

	public String getQrcode() {
		return qrcode;
	}

	public void setQrcode(String qrcode) {
		this.qrcode = qrcode;
	}

	public String getQrcode_img() {
		return qrcode_img;
	}

	public void setQrcode_img(String qrcode_img) {
		this.qrcode_img = qrcode_img;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
