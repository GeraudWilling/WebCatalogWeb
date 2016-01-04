package org.webcatalog.rest;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpSession;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.webcatalog.util.ClientView;
import org.webcatalog.util.Context;
import org.webcatalog.util.LoginView;
import org.webcatalog.util.SendMail;

import perso.webcatalog.bean.Categorie;
import perso.webcatalog.bean.Client;
import perso.webcatalog.remote.FacadeCategorieRemote;
import perso.webcatalog.remote.FacadeClientRemote;

@RestController
public class MainRest {
	@SuppressWarnings("unchecked")
	FacadeClientRemote<Client> facadeClientRemote = (FacadeClientRemote<Client>)Context.jndiLookup(FacadeClientRemote.class);
	@SuppressWarnings("unchecked")
	FacadeCategorieRemote<Categorie> facadeCategorieRemote = (FacadeCategorieRemote<Categorie>)Context.jndiLookup(FacadeCategorieRemote.class);
	
	
	@RequestMapping(value="/inscription.service", method = RequestMethod.POST)
	public boolean inscription(@ModelAttribute("client") ClientView clientview,BindingResult result, HttpSession session,HttpServletRequest req){	
		Client client= new Client();
		
		try {
			client.setAdresse(clientview.getAdresse());
			client.setCarte(clientview.getCarte());
			client.setEmail(clientview.getEmail());
			client.setNom(clientview.getNom());
			client.setTel(clientview.getTel());
			//if(facadeClientRemote.a)
				Client client2=facadeClientRemote.create(client);
			if(client2 == null)
				return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		System.out.println("insc**");
		session.setAttribute("user",client);
		return true;
	}
	
	
	@RequestMapping(value="/login.service", method = RequestMethod.POST)
	public boolean chekLogin(@ModelAttribute("clientLogin") LoginView clientLogin,BindingResult result,HttpSession session){	
		Client client= facadeClientRemote.identify(clientLogin.getEmail(), clientLogin.getCarte());
		if(client == null)
			return false;
		session.setAttribute("user", client);
		return true;
	}
	
	
	@RequestMapping(value="/passforgot.service", method = RequestMethod.GET)
	public boolean passForgot(@RequestParam(required=true) String email){	
		try {
			Client client=facadeClientRemote.findByMail(email);
			if(client == null)
				return false;
			String text="Votre mot de passe est: " + client.getCarte();
			Thread thread = new Thread(new SendMail(email, "webcatalog@webmail.com", "LOCALHOST", "Password recovering on WebCatalog", text));
			thread.start();
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}

