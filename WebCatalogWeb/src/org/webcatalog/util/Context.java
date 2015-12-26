package org.webcatalog.util;

import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Context {
	
	public static Object jndiLookup(Class ejb){
		String parts[]=ejb.getName().split("\\.");
		String name=parts[parts.length-1].replace("Remote", "");
		InitialContext initialContext=null;
		Object res= null;
		try {
			initialContext = new InitialContext();
			//java:global/WebCatalogWeb/FacadeCategorie!perso.webcatalog.remote.FacadeCategorieRemote
			//java:global/WebCatalogWeb/FacadeCategorie!perso.webcatalog.remote.FacadeCategorieRemote
			String jndiName= 	"java:global/WebCatalogWeb/"+name+"!"+ejb.getCanonicalName();
			System.out.println("**" + jndiName);
			res= initialContext.lookup(jndiName);
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return res;
	}

}
