package util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;  
import java.util.Calendar;
import java.util.Date;  
import java.util.GregorianCalendar;
import java.text.ParseException; 

import org.joda.time.DateTime;

	public class DataUtil {  
		
	   private SimpleDateFormat formatIso;  
	   private SimpleDateFormat formatIsoDtHr;  
	   private SimpleDateFormat formatBra;  
	   private SimpleDateFormat formatBraDtHr;  
	   private Date date;  
	  
	   
	   public DataUtil(){  
	      formatIso = new SimpleDateFormat("yyyy-MM-dd");  
	      formatIsoDtHr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	      formatBra = new SimpleDateFormat("dd/MM/yyyy");  
	      formatBraDtHr = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
	   }  
	  
	   /** 
	    * Converte uma data no formato ABNT em formato ISO; 
	    * @param dataBra Argumento que recebe data no formato ABNT(dd/MM/yyyy); 
	    * @return Uma data no formato ISO(yyyy-MM-dd). 
	    */  
	  
	   public String parseDataIso(String dataBra)  
	   {  
	      try   
	      {  
	         date = formatBra.parse(dataBra);  
	         return(formatIso.format(date));  
	      }  
	      catch(Exception e)   
	      {  
	         e.printStackTrace();  
	         return("");  
	      }  
	   }  
	        
	   public String parseDataHoraBra(String braDtHr)  
	   {  
		   try   
		   {  
			   date = formatIsoDtHr.parse(braDtHr);  
			   return(formatBraDtHr.format(date));  
		   }  
		   catch(Exception e)   
		   {  
			   e.printStackTrace();  
			   return("");  
		   }  
	   }  
	   /** 
	    * Converte uma data no formato ISO em formato ABNT; 
	    * @param dataIso Argumento que recebe data no formato ISO(yyyy-MM-dd); 
	    * @return Uma data no formato ABNT(dd/MM/yyyy). 
	    */  
	  
	   public String parseDataBra(String dataIso)  
	   {  
	      try   
	      {  
	         date = formatIso.parse(dataIso);  
	         return(formatBra.format(date));  
	      }  
	      catch(Exception e)   
	      {  
	         e.printStackTrace();  
	         return("");  
	      }  
	   }  
	   
	   
	   public String parseDataBra(Date dataIso)  
	   {  
	      try   
	      {  	           
	         return(formatBra.format(dataIso));  
	      }  
	      catch(Exception e)   
	      {  
	         e.printStackTrace();  
	         return("");  
	      }  
	   }  
	   
	   public static String parseDataUs(Date dataIso)  
	   {  
	      try   
	      {  	      
	    	  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
	         return(sdf.format(dataIso));  
	      }  
	      catch(Exception e)   
	      {  
	         e.printStackTrace();  
	         return("");  
	      }  
	   }  
	   
	   
	    public static String getDataExtenso(Date date){  
	        String diaf = null;  
	        String mesf = null;  
	        String DataExtenso = null;  
	        Calendar calendar = new GregorianCalendar();  
//	        Date hoje = new Date();  
	        calendar.setTime(date);  
	        int semana = calendar.get(Calendar.DAY_OF_WEEK);  
	        int mes = calendar.get(Calendar.MONTH);  
	        int dia = calendar.get(Calendar.DAY_OF_MONTH);  
	        int ano = calendar.get(Calendar.YEAR);  
	          
	        // semana  
	        switch(semana){  
	         case 1: diaf = "Domingo";break;  
	         case 2: diaf = "Segunda-feira";break;
	         case 3: diaf = "Terça-feira";break;
	         case 4: diaf = "Quarta-feira";break;
	         case 5: diaf = "Quinta-feira";break;
	         case 6: diaf = "Sexta-feira";break;
	         case 7: diaf = "Sábado";break;
	        }  
	        // mês  
	        switch(mes) {  
	         case 0:  mesf = "Janeiro";break;  
	         case 1:  mesf = "Fevereiro";break;  
	         case 2:  mesf = "Março";break;  
	         case 3:  mesf = "Abril";break;  
	         case 4:  mesf = "Maio";break;  
	         case 5:  mesf = "Junho";break;  
	         case 6:  mesf = "Julho";break;  
	         case 7:  mesf = "Agosto";break;  
	         case 8:  mesf = "Setembro";break;  
	         case 9: mesf = "Outubro";break;  
	         case 10: mesf = "Novembro";break;  
	         case 11: mesf = "Dezembro";break;  
	        }  
	        DataExtenso = diaf+" "+dia+" de "+mesf+" de "+ano;  
	        return DataExtenso;  
	      }   
	    
	    public static String getDataExtensoMes(Date date){  
	    	String mesf = null;  
	    	String DataExtenso = null;  
	    	Calendar calendar = new GregorianCalendar();  
//	        Date hoje = new Date();  
	    	calendar.setTime(date);  
	    	int mes = calendar.get(Calendar.MONTH);  
	    	int dia = calendar.get(Calendar.DAY_OF_MONTH);  
	    	int ano = calendar.get(Calendar.YEAR);  
	    	
	    	// mês  
	    	switch(mes) {  
	    	case 0:  mesf = "Janeiro";break;  
	    	case 1:  mesf = "Fevereiro";break;  
	    	case 2:  mesf = "Março";break;  
	    	case 3:  mesf = "Abril";break;  
	    	case 4:  mesf = "Maio";break;  
	    	case 5:  mesf = "Junho";break;  
	    	case 6:  mesf = "Julho";break;  
	    	case 7:  mesf = "Agosto";break;  
	    	case 8:  mesf = "Setembro";break;  
	    	case 9: mesf = "Outubro";break;  
	    	case 10: mesf = "Novembro";break;  
	    	case 11: mesf = "Dezembro";break;  
	    	}  
	    	DataExtenso = dia+" de "+mesf+" de "+ano;  
	    	return DataExtenso;  
	    }   
	      
	   
	    
	    public static String formatDateBra(Date d){
	    	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	    	return  d != null ? sdf.format(d) : "";
	    }
	
	    
	    
	    public static String formatHoraBra(Date d){
	    	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
	    	return sdf.format(d);
	    }
	    
	    public static Date stringToDate(String s){
			try {
				DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
				Date date;
				date = (java.util.Date)formatter.parse(s);
				return date;
			} catch (ParseException e) {
				
				e.printStackTrace();
				return null;
			}
	    }
	}  
	
