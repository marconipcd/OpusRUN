package util.boletos.boleto.bancos;

import java.text.SimpleDateFormat;

import org.joda.time.DateTime;
import org.joda.time.Days;

import util.Real;
import util.StringUtil;
import util.boletos.boleto.Banco;
import util.boletos.boleto.Boleto;
import util.boletos.boleto.Emissor;
import domain.ContaBancaria;


public class Sicredi extends AbstractBanco implements Banco {

	private static final String NUMERO_BB = "748";
	private static final String DIGITO_NUMERO_BB = "X";
	
	@Override
	public String geraCodigoDeBarras44(Boleto boleto){
		
		
		//---- 748900000000000000011177000020 22060109980100
		
		
		try{
			
			Emissor emissor = boleto.getEmissor();
			
			ContaBancaria cb = boleto.getContaBancaria();
			
			//----Primeiro Campo
			//----------------------------------
			
			//Código do Banco
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			DateTime dt1 = new DateTime(boleto.getDatas().getVencimento());
			DateTime dt2 = new DateTime(sdf.parse("07/10/1997"));
			int fator_vencimento = Days.daysBetween(dt2, dt1).getDays();	

			StringBuilder codBarras = new StringBuilder();
			
			codBarras.append(cb.getCod_banco());			
			codBarras.append("9");			
			codBarras.append(fator_vencimento);
			codBarras.append(StringUtil.preencheCom(Real.formatDbToString(new String().valueOf(boleto.getValorBoleto())).replace(",", ""), "0", 10, 1));
			
			
			String cp = "11"+boleto.getNossoNumero().replace("/", "").replace("-", "")+cb.getAgencia_banco().substring(0, 4)+"01"+cb.getCod_beneficiario()+
					"1"+"0";
			
			codBarras.append(cp);	
			
			int dig_veri_cl = getMod11(cp);
			if(dig_veri_cl > 9){
				dig_veri_cl = 0;
			}
			codBarras.append(dig_veri_cl);
				
			
			int digito_geral = getMod11(codBarras.toString());
			if(digito_geral == 1 || digito_geral > 9){
				digito_geral = 1;
			}
			
			codBarras.insert(4, digito_geral);
				
			return codBarras.toString();
			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public String geraCodigoDeBarrasPara(Boleto boleto) {
		
		try{
			StringBuilder campoLivre = new StringBuilder();
			Emissor emissor = boleto.getEmissor();
			
			ContaBancaria cb = boleto.getContaBancaria();
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			DateTime dt1 = new DateTime(boleto.getDatas().getVencimento());
			DateTime dt2 = new DateTime(sdf.parse("07/10/1997"));
			int fator_vencimento = Days.daysBetween(dt2, dt1).getDays();	
			
			//----Primeiro Campo
			//----------------------------------
			
			//Código do Banco
			campoLivre.append(cb.getCod_banco());
			//Código da Moeda
			campoLivre.append("9");
			
			//Tipo Cobrança
			if(cb.getTipo().equals("COM REGISTRO")){
				campoLivre.append("1");
			}else{
				campoLivre.append("3");
			}
			
			//campoLivre.append(".");
			
			//Carteira
			campoLivre.append(cb.getCarteira());
			//1º, 2º e 3º Digito Nosso Numero
			String nn = boleto.getNossoNumero().replace("/", "").replace("-", "");
			campoLivre.append(nn.substring(0, 1));
			campoLivre.append(nn.substring(1, 2));
			campoLivre.append(nn.substring(2,3));
			
			//Digito Verificador do Primeiro Campo
			String digito = String.valueOf(getMod10(campoLivre.toString().replace(".", "").substring(0,9)));
			campoLivre.append(digito);
			
			//campoLivre.append(" ");
					
			//----Segundo Campo
			//---------------------------------
			campoLivre.append(nn.substring(3, 4));
			campoLivre.append(nn.substring(4, 5));
			campoLivre.append(nn.substring(5, 6));
			campoLivre.append(nn.substring(6, 7));
			campoLivre.append(nn.substring(7, 8));
			//campoLivre.append(".");
			campoLivre.append(nn.substring(nn.length()-1, nn.length()));
			campoLivre.append(cb.getAgencia_banco().substring(0,4));
			
			//Digito Verificador do Segundo Campo
			String digito2 = String.valueOf(getMod10(campoLivre.substring(10,20)));
			campoLivre.append(digito2);
			
			//campoLivre.append(" ");
			
			//----Terceiro Campo
			//--------------------------------
			campoLivre.append(cb.getPosto_beneficiario());
			campoLivre.append(cb.getCod_beneficiario().substring(0, 1));
			campoLivre.append(cb.getCod_beneficiario().substring(1, 2));
			campoLivre.append(cb.getCod_beneficiario().substring(2, 3));
			//campoLivre.append(".");
			campoLivre.append(cb.getCod_beneficiario().substring(3, 4));
			campoLivre.append(cb.getCod_beneficiario().substring(4, 5));
			campoLivre.append("1");
			campoLivre.append("0");
			
			
			//Calcular Digito Verificador do Campo Livre
			StringBuilder campoLivreDig = new StringBuilder();
			
			if(cb.getTipo().equals("COM REGISTRO")){
				campoLivreDig.append("1");
			}else{
				campoLivreDig.append("3");
			}
			
			campoLivreDig.append("1");
			campoLivreDig.append(nn);
			campoLivreDig.append(cb.getAgencia_banco().substring(0,4));
			campoLivreDig.append(cb.getPosto_beneficiario());
			campoLivreDig.append(cb.getCod_beneficiario());
			campoLivreDig.append("1");
			campoLivreDig.append("0");
			
			int digito_campo_livre = getMod11(campoLivreDig.toString());
			if(digito_campo_livre == 10 || digito_campo_livre == 11){
				digito_campo_livre = 0;
			}
			
			campoLivre.append(String.valueOf(digito_campo_livre));
			
			
			
			
			//Digito Verificador do Terceiro Campo
			String digito3 = String.valueOf(getMod10(campoLivre.toString().replace(".","").replaceAll(" ", "").substring(21,31)));
			campoLivre.append(digito3);
			
			//campoLivre.append(" ");
			
			StringBuilder codBarras = new StringBuilder();
			
			codBarras.append(cb.getCod_banco());			
			codBarras.append("9");			
			codBarras.append(fator_vencimento);
			codBarras.append(StringUtil.preencheCom(Real.formatDbToString(new String().valueOf(boleto.getValorBoleto())).replace(",", ""), "0", 10, 1));
			
			
			String cp = "11"+boleto.getNossoNumero().replace("/", "").replace("-", "")+cb.getAgencia_banco().substring(0, 4)+"01"+cb.getCod_beneficiario()+
					"1"+"0";
			
			codBarras.append(cp);	
			
			int dig_veri_cl = getMod11(cp);
			if(dig_veri_cl > 9){
				dig_veri_cl = 0;
			}
			codBarras.append(dig_veri_cl);
				
			
			int digito_geral = getMod11(codBarras.toString());
			if(digito_geral == 1 || digito_geral > 9){
				digito_geral = 1;
			}
			
			campoLivre.append(digito_geral);
			
			//campoLivre.append(" ");
			
			//Digito Fator Vencimento
					
			campoLivre.append(fator_vencimento);
			
			//Digito Valor Vencimento
			campoLivre.append(StringUtil.preencheCom(Real.formatDbToString(new String().valueOf(boleto.getValorBoleto())).replace(",", ""), "0", 10, 1));
			
	 			
			return new CodigoDeBarrasBuilderSicred(boleto).comCampoLivre(campoLivre);
			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public static int getMod11(String num){
	    
	    String[] numeros,parcial;
	    numeros = new String[num.length()+1];
	    parcial = new String[num.length()+1];
	    
	    int peso = 2;
	    
	    for (int i = num.length(); i > 0; i--) {
	    	
	    	if(peso > 9){
	    		peso = 2;
	    	}
	    
	        numeros[i] = num.substring(i-1,i);	    
	        String r1 = String.valueOf(Integer.parseInt(numeros[i]) * peso);
	        	          
	       // if(Integer.parseInt(r1) >= 10 && r1.length() == 2){	        	
	       // 	parcial[i] = String.valueOf(Integer.parseInt(r1.substring(0, 1))+Integer.parseInt(r1.substring(1, 2)));
	       // }else{
	        	parcial[i] = r1;
	       // }
	        
	        peso++;
	    }
	    
	    int resultado_soma = 0;
	    
	    for (int i = parcial.length-1; i > 0; i--) {
			
	    	resultado_soma = resultado_soma + Integer.parseInt(parcial[i]);
		}
	    
	    
	    int divisao = resultado_soma / 11;
	    
	    int digito =11 - (resultado_soma - (Integer.parseInt(String.valueOf(divisao).split(",")[0]) * 11)) ;
	    
	    
	    System.out.println("Divisao: "+divisao);
	    
	    return digito == 10 && digito == 11 ? 0 : digito;
	}
	
	public static int getMod10(String num){
	    
	    String[] numeros,parcial;
	    numeros = new String[num.length()+1];
	    parcial = new String[num.length()+1];
	    
	    int peso_2 = 2;
	    int peso_1 = 1;
	    int ultimo_peso = 0;

	    for (int i = num.length(); i > 0; i--) {
	    	
	    	if(ultimo_peso == 0){
	    		ultimo_peso = peso_1;
	    	}

	        numeros[i] = num.substring(i-1,i);
	        ultimo_peso = ultimo_peso == peso_2 ? peso_1 : peso_2;
	        String r1 = String.valueOf(Integer.parseInt(numeros[i]) * ultimo_peso);
	        	          
	        if(Integer.parseInt(r1) >= 10 && r1.length() == 2){	        	
	        	parcial[i] = String.valueOf(Integer.parseInt(r1.substring(0, 1))+Integer.parseInt(r1.substring(1, 2)));
	        }else{
	        	parcial[i] = r1;
	        }
	        
	    }
	    
	    int resultado_soma = 0;
	    
	    for (int i = parcial.length-1; i > 0; i--) {
			
	    	resultado_soma = resultado_soma + Integer.parseInt(parcial[i]);
		}
	    
	    //System.out.println("Somas: "+resultado_soma);
	    
	    int multiplo;
	    if(resultado_soma%10==0){
	        multiplo = resultado_soma;
	    }else{
	        multiplo = (resultado_soma-(resultado_soma%10))+10;
	    }
	    
	    //System.out.println("Multiplo: "+multiplo);
	    
	    int digito = multiplo > resultado_soma ? (multiplo - resultado_soma) : (resultado_soma - multiplo);
	    //System.out.println("Digito: "+ digito);
	    	
	    return digito;
	}

	@Override
	public String getNumeroFormatado() {
		return NUMERO_BB;
	}

	@Override
	public java.net.URL getImage() {
		String arquivo = "/br/com/caelum/stella/boleto/img/%s.png";
		String imagem = String.format(arquivo, getNumeroFormatado());
		return getClass().getResource(imagem);
	}

	public String getNumeroConvenioDoEmissorFormatado(Emissor emissor) {
		if (emissor.getNumeroConvenio() < 1000000) {
			return String.format("%06d", emissor.getNumeroConvenio());
		} else {
			return String.format("%07d", emissor.getNumeroConvenio());
		}
	}

	@Override
	public String getContaCorrenteDoEmissorFormatado(Emissor emissor) {
		return String.format("%08d", emissor.getContaCorrente());
	}

	@Override
	public String getCarteiraDoEmissorFormatado(Emissor emissor) {
		return String.format("%02d", emissor.getCarteira());
	}

	@Override
	public String getNossoNumeroDoEmissorFormatado(Emissor emissor) {
		if (emissor.getCarteira() == 18) {
			return String.format("%017d", emissor.getNossoNumero());
		} else {
			return String.format("%011d", emissor.getNossoNumero());
		}
	}

	@Override
	public String getNumeroFormatadoComDigito() {
		return NUMERO_BB + "-" + DIGITO_NUMERO_BB;
	}

	@Override
	public String getNossoNumeroECodDocumento(Emissor emissor) {
		return getNossoNumeroDoEmissorFormatado(emissor);
	}

	@Override
	public String getAgenciaECodigoCedente(Emissor emissor) {
		// TODO Auto-generated method stub
		return null;
	}

}

