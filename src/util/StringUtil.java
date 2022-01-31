package util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;

import javax.swing.text.MaskFormatter;


public class StringUtil {
    
	
	
	public static String md5(String s){
        String sen = "";
        MessageDigest md = null;
        try{
            md = MessageDigest.getInstance("MD5");
        }catch(NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        BigInteger hash = new BigInteger(1, md.digest(s.getBytes()));
        sen = hash.toString(16);
        
        
        return sen;
    	
    	
  
    	

    }
    
    public static String md52(String string){
  	
  	
  	MessageDigest m;

  	try
  	{
	    	m = MessageDigest.getInstance("MD5");
	    	m.update(string.getBytes(),0,string.length());
	    	BigInteger i = new BigInteger(1, m.digest());
	
	    	//Formatando o resuldado em uma cadeia de 32 caracteres, completando com 0 caso falte
	    	string = String.format("%1$032X", i);
	
	    	return string;
  	}catch (NoSuchAlgorithmException e)
  	{
	    	e.printStackTrace();
	    	return null;
  	}

  	

  }
    
    public static String format(String pattern, String value) {
        MaskFormatter mask;
        try {
            mask = new MaskFormatter(pattern);
            mask.setValueContainsLiteralCharacters(false);
            return mask.valueToString(value);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    
    private static String stringHexa(byte[] bytes) {
    	   StringBuilder s = new StringBuilder();
    	   for (int i = 0; i < bytes.length; i++) {
    	       int parteAlta = ((bytes[i] >> 4) & 0xf) << 4;
    	       int parteBaixa = bytes[i] & 0xf;
    	       if (parteAlta == 0) s.append('0');
    	       s.append(Integer.toHexString(parteAlta | parteBaixa));
    	   }
    	   return s.toString();
    	}
    
    public static boolean isDigit(String s){
    	if(s.matches("[0-9]+")){
    		return true;
    	}else{
    		return false;
    	}
    	
    }

    public static String preencheCom(String linha_a_preencher, String letra, int tamanho, int direcao){

        //Checa se Linha a preencher é nula ou branco
        if (linha_a_preencher == null || linha_a_preencher.trim() == "" ) {linha_a_preencher = "";}       

        //Enquanto Linha a preencher possuir 2 espaços em branco seguidos, substitui por 1 espaço apenas
//        while (linha_a_preencher.contains(" ")) {
//        	linha_a_preencher = linha_a_preencher.replaceAll(" "," ").trim();
//        	}
      

        //Retira caracteres estranhos
        linha_a_preencher = linha_a_preencher.replaceAll("[./-]","");

        StringBuffer sb = new StringBuffer(linha_a_preencher);

        if (direcao==1){ //a Esquerda

            for (int i=sb.length() ; i<tamanho ; i++){

                sb.insert(0,letra);

            }

        } else if (direcao==2) {//a Direita

            for (int i=sb.length() ; i<tamanho ; i++){
                sb.append(letra);
            }
        }

        return sb.toString();

    }
    
    public static String preencheCom2(String linha_a_preencher, String letra, int tamanho, int direcao){

        //Checa se Linha a preencher é nula ou branco
        if (linha_a_preencher == null || linha_a_preencher.trim() == "" ) {linha_a_preencher = "";}       

        //Enquanto Linha a preencher possuir 2 espaços em branco seguidos, substitui por 1 espaço apenas
//        while (linha_a_preencher.contains(" ")) {
//        	linha_a_preencher = linha_a_preencher.replaceAll(" "," ").trim();
//        	}
      

        //Retira caracteres estranhos
        //linha_a_preencher = linha_a_preencher.replaceAll("[./-]","");

        StringBuffer sb = new StringBuffer(linha_a_preencher);

        if (direcao==1){ //a Esquerda

            for (int i=sb.length() ; i<tamanho ; i++){

                sb.insert(0,letra);

            }

        } else if (direcao==2) {//a Direita

            for (int i=sb.length() ; i<tamanho ; i++){
                sb.append(letra);
            }
        }

        return sb.toString();

    }



 



   
}
