package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckNdocUtil {

	public static boolean checkNdocAcesso(String nDoc){
		Pattern p1 = Pattern.compile("^[0-9]{1,}-[a-z]{2}[0-9]{1,}");
	    Matcher m1 = p1.matcher(nDoc);
	    
	    Pattern p2 = Pattern.compile("^[0-9]{1,}/[0-9]{2}-[0-9]{2}/[0-9]{2}");
	    Matcher m2 = p2.matcher(nDoc);
	    
	    Pattern p3 = Pattern.compile("^[0-9]{1,}/[0-9]{2}/[0-9]{2}");
	    Matcher m3 = p3.matcher(nDoc);
	    
	    if(m1.matches() || m2.matches() || m3.matches()){
	    	return true;
	    }
	    
	    return false;
	}
}
