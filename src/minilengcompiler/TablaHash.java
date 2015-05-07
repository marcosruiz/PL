package minilengcompiler;

import java.util.Enumeration;
import java.util.Properties;

public class TablaHash {
	Properties p;
	public TablaHash(){
		p = new Properties();
	}
	public void insertar(String s){
		String repS = p.getProperty(s);
		if(repS == null){
			p.setProperty(s, "1");
		}
		else{
			int repI = Integer.parseInt(repS);
			repI = repI+1;
			repS = Integer.toString(repI);
			p.setProperty(s, repS);
		}
	}
	public String leer(String s){
		return p.getProperty(s);
	}
	@SuppressWarnings("unchecked")
	public Enumeration<String> dameClaves(){
		return (Enumeration<String>) p.propertyNames();
	}
	
}
