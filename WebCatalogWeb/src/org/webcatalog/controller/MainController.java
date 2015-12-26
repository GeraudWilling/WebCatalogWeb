package org.webcatalog.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.webcatalog.util.Context;
import org.webcatalog.util.Panier;

import perso.webcatalog.bean.Categorie;
import perso.webcatalog.bean.Client;
import perso.webcatalog.bean.Produit;
import perso.webcatalog.remote.FacadeCategorieRemote;
import perso.webcatalog.remote.FacadeClientRemote;
import perso.webcatalog.remote.FacadeProduitRemote;



@Controller
public class MainController {
	@SuppressWarnings("unchecked")
	FacadeCategorieRemote<Categorie> facadeCategorieRemote = (FacadeCategorieRemote<Categorie>)Context.jndiLookup(FacadeCategorieRemote.class);
	@SuppressWarnings("unchecked")
	FacadeProduitRemote<Produit> facadeProduitRemote = (FacadeProduitRemote<Produit>)Context.jndiLookup(FacadeProduitRemote.class);
	@SuppressWarnings("unchecked")
	FacadeClientRemote<Client> facadeClientRemote = (FacadeClientRemote<Client>)Context.jndiLookup(FacadeClientRemote.class);
	
	
	@RequestMapping(value="/categories.xhtml", method = RequestMethod.GET)
	public String getAllCategories(HttpServletRequest req) {
		System.out.println("**getAllCategories()**");
		List<Categorie> categories= null;
		categories= facadeCategorieRemote.findAll();
		req.setAttribute("categories", categories);
		System.out.println("sortie*");
		return "categories";
	}
	
	@RequestMapping(value="/produits.xhtml", method = RequestMethod.GET)
	public String getCategorieProduits(HttpServletRequest req, @RequestParam(value="id" ,required=true) long id) {
		System.out.println("**getCategorieProduits()**");
		List<Produit> produits= null;
		produits= facadeCategorieRemote.findAllProduct(id);
		req.setAttribute("produits", produits);
		System.out.println("sortie*");
		return "produits";
	}
	
	@RequestMapping(value="/addToCart.xhtml", method = RequestMethod.GET)
	public String addIncart(@RequestParam(required=true) int id, HttpSession session){
		Produit newProduit= facadeProduitRemote.findById(id);
		Panier panier = new Panier(newProduit, 1);
		List<Panier> cart= (List<Panier>)session.getAttribute("cart");
		double total= 0.00;
		
		if(cart == null){
			cart= new ArrayList<Panier>();
			cart.add(panier);
			session.setAttribute("cart", cart);
			total+=(newProduit.getPrix());
			session.setMaxInactiveInterval(30*60); //All session will last for 30min after user stop activities
		}else{
			cart= (List<Panier>)session.getAttribute("cart");
			boolean find=false;
			for(Panier pan: cart){
				if(pan.getProduit().getId() == newProduit.getId()){
					pan.setQuantite(pan.getQuantite()+1);
					find=true;
				}
				total+=(pan.getProduit().getPrix()*pan.getQuantite());
			}
			if(!find){
				cart.add(panier);
				total+=(newProduit.getPrix());
			}
		}
		session.setAttribute("cart", cart);
		session.setAttribute("total", total);
		return "panier";
	}
	
	@RequestMapping(value="/cartDiv.xhtml", method = RequestMethod.GET)
	public String getCartTotal(HttpSession session) {
		double total= 0.0;
		if(session.getAttribute("total") == null)
			session.setAttribute("total", total);
		return "cartdiv";
	}
	
	
	@RequestMapping(value="/panier.xhtml", method = RequestMethod.GET)
	public String getPanier(HttpSession session) {
		List<Panier> cart= (List<Panier>)session.getAttribute("cart");
		
		if(cart == null){
			cart= new ArrayList<Panier>();
			session.setAttribute("cart", cart);
		}
		return "panier";
	}
	
	
	@RequestMapping(value="/validate.xhtml", method = RequestMethod.GET)
	public String validate(HttpSession session) {
		String res="";
		Client user= (Client)session.getAttribute("user");
		if(user == null){
			res="login";
		}else{
			res="summary";
		}
		
		return res;
	}
	
	@RequestMapping(value="/inscription.xhtml", method = RequestMethod.GET)
	public String inscription(Model model){	
		model.addAttribute("client", new Client());
		return "inscription";
	}
	
	@RequestMapping(value="/inscription.xhtml", method = RequestMethod.POST)
	public String inscription(@ModelAttribute Client client,BindingResult result, HttpSession session){	
		try {
			facadeClientRemote.create(client);
		} catch (Exception e) {
			e.printStackTrace();
			return "inscription";
		}
		System.out.println("insc**");
		session.setAttribute("user",client);
		return "summary";
	}

}
