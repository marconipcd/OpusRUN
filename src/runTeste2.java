import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import domain.ContaBancaria;
import domain.ContasReceber;
import domain.ControleTitulo;


public class runTeste2 {
	
	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("OpusBloqueio");
	static EntityManager em = emf.createEntityManager();
	
	public static void main(String[] args) {
		
				
		
		start();
			
	}
	
	public static void start() {
				
				
		
		System.out.println("teste");
		
		Query q = em.createNativeQuery("SELECT * FROM  contas_receber WHERE  N_NUMERO_SICRED IS NULL AND  N_NUMERO IS NOT NULL AND  STATUS_2 =  'ABERTO' ", ContasReceber.class);
		
		for (ContasReceber boleto: (List<ContasReceber>)q.getResultList()) {
				gerarNossoNumero(boleto.getId());
				System.out.println(boleto.getId());
				
		}
	}
	
	public static boolean gerarNossoNumero(Integer codBoleto){
		 
		
		try{
			
			ContasReceber boleto = em.find(ContasReceber.class, codBoleto);
			if(boleto != null){
				if(boleto.getN_numero_sicred() == null || boleto.getN_numero_sicred().isEmpty() || boleto.getN_numero_sicred().equals("")){
					
					Query qControleNovo = em.createQuery("select c from ControleTitulo c where c.nome=:nome and c.empresa_id =:empresa", ControleTitulo.class);
					qControleNovo.setParameter("nome", boleto.getControle());
					qControleNovo.setParameter("empresa", 1);
					
					ContaBancaria cb = null;
					if(qControleNovo.getResultList().size() == 1){
						
						ControleTitulo ct = (ControleTitulo)qControleNovo.getSingleResult();
						cb = ct.getConta_bancaria_bkp();
					}
					
					String NumeroNovo = "";
					if(cb != null){
						if(cb.getNome_banco().equals("SICRED")){					
							NumeroNovo = calcularNossoNumeroSicred(boleto.getCliente().getId(),cb);				
						}
						
						if(cb.getNome_banco().equals("BANCO DO BRASIL")){
							//NumeroNovo = calcularNossoNumero(boleto.getCliente().getId());
						}
					}
					
					//boleto.setRemessa_enviada(null);
					boleto.setN_numero_sicred(NumeroNovo);
					em.getTransaction().begin();
					em.merge(boleto);
					em.getTransaction().commit();
					
					return true;
				}
				
				return false ;
			}
			
			
			return false ;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public static String calcularNossoNumeroSicred(Integer codCliente,ContaBancaria cb){
		
		
		try{
			StringBuilder numero = new StringBuilder(); 
			Integer QtdZeros = 5;
			Integer Qtd = 1;
			
			while(true){
				
				numero = new StringBuilder();
				
				SimpleDateFormat sdf = new SimpleDateFormat("YY");
				
				 //-//------//--  Padrão : AA/B XXXXX-D  --//-------------
				//-//------//-----------------------------------------------------------------------------------------------------------------------//--
			   	
				// agencia(AAAA)+posto(PP)+conta(NNNN)+ano(YY)+byte(B)+sequencia(SSSSS)
				
				String caract1 = sdf.format(new Date()).toString()+"/2";
				String caract2 = String.format("%0"+QtdZeros.toString()+"d", Qtd);
				int digito = getMod11(cb.getAgencia_banco()+cb.getPosto_beneficiario()+cb.getCod_beneficiario()+caract1.replace("/", "")+caract2);
				if(digito == 10 || digito == 11){
					digito = 0;
				}
				String caract3 = "-"+digito;
				
				numero.append(caract1);
				numero.append(caract2);
				numero.append(caract3);
				
				Query consultaNNumero = em.createQuery("select cr from ContasReceber cr where cr.n_numero_sicred = :codNossoNumero", ContasReceber.class);
				consultaNNumero.setParameter("codNossoNumero", numero.toString());
				
				boolean exists = false;
				if(consultaNNumero.getResultList().size() == 0){
					exists = true;
				}else{
					Qtd++;
				}
				
				boolean valid = false;
				if(numero.toString().length() == 11){
					valid = true;
				}else{
					if(numero.toString().length() > 11){
						QtdZeros--;
					}else{
						QtdZeros++;
					}
				}
				
				if(valid && exists){
					break;
				}
					
			}
			
			return numero.toString();
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
	
	

}
