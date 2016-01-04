package org.webcatalog.controller;

import java.util.ArrayList;

import java.util.Date;
import java.util.List; 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.webcatalog.util.ClientView;
import org.webcatalog.util.Context;
import org.webcatalog.util.LoginView;
import org.webcatalog.util.Panier;
import org.webcatalog.util.SendMail;

import perso.webcatalog.bean.Categorie;
import perso.webcatalog.bean.Client;
import perso.webcatalog.bean.CommandeClient;
import perso.webcatalog.bean.Produit;
import perso.webcatalog.bean.ProduitCommande;
import perso.webcatalog.remote.FacadeCategorieRemote;
import perso.webcatalog.remote.FacadeClientRemote;
import perso.webcatalog.remote.FacadeCommandeClientRemote;
import perso.webcatalog.remote.FacadeProduitCommandeRemote;
import perso.webcatalog.remote.FacadeProduitRemote;



@Controller
public class MainController {
	@SuppressWarnings("unchecked")
	FacadeCategorieRemote<Categorie> facadeCategorieRemote = (FacadeCategorieRemote<Categorie>)Context.jndiLookup(FacadeCategorieRemote.class);
	@SuppressWarnings("unchecked")
	FacadeProduitRemote<Produit> facadeProduitRemote = (FacadeProduitRemote<Produit>)Context.jndiLookup(FacadeProduitRemote.class);
	@SuppressWarnings("unchecked")
	FacadeClientRemote<Client> facadeClientRemote = (FacadeClientRemote<Client>)Context.jndiLookup(FacadeClientRemote.class);
	@SuppressWarnings("unchecked")
	FacadeCommandeClientRemote<CommandeClient> facadeCommandeClientRemote = (FacadeCommandeClientRemote<CommandeClient>)Context.jndiLookup(FacadeCommandeClientRemote.class);
	@SuppressWarnings("unchecked")
	FacadeProduitCommandeRemote<ProduitCommande> facadeProduitCommandeRemote = (FacadeProduitCommandeRemote<ProduitCommande>)Context.jndiLookup(FacadeProduitCommandeRemote.class);
	
	
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
			res="redirect:/login.xhtml";
		}else{
			res="summary";
		}
		
		return res;
	}
	
	@RequestMapping(value="/inscription.xhtml", method = RequestMethod.GET)
	public String inscription(Model model){	
		model.addAttribute("client", new ClientView());
		return "inscription";
	}
	
	@RequestMapping(value="/login.xhtml", method = RequestMethod.GET)
	public String login(Model model){	
		model.addAttribute("clientLogin", new LoginView());
		return "login";     
	}
	
	
	
	@RequestMapping(value="/validatePourchase.xhtml", method = RequestMethod.GET)
	public String validatePourchase(Model model, HttpSession session){	
		Client user= (Client)session.getAttribute("user");
		List<Panier> cart= (List<Panier>)session.getAttribute("cart");
		double total= (Double)session.getAttribute("total");
		Date today= new Date();
		CommandeClient commandeClient = new CommandeClient();
		commandeClient.setClient(user);
		commandeClient.setDateCreation(new Date());
		commandeClient.setMontant(total);
		commandeClient.setNoConfirmation(today.getTime()+user.getId());//Numero de confirmation est la date + l'id de l'utilisateur
		
		
		
		try {
			commandeClient=facadeCommandeClientRemote.create(commandeClient);
			
			for(Panier panierItem: cart){
				ProduitCommande produitCommande= new ProduitCommande();
				produitCommande.setQuantite(panierItem.getQuantite());
				produitCommande.setCommandeClient(commandeClient);
				produitCommande.setProduit(panierItem.getProduit());
				facadeProduitCommandeRemote.update(produitCommande);
			}
			session.removeAttribute("total");
			session.removeAttribute("cart");
			session.setAttribute("numConf", today.getTime()+user.getId());
		} catch (Exception e) {
			e.printStackTrace();
			return "summary";
		}
		String text="<html><head></head><body><style type='text/css'>"
				+ "#h2VAL{"
				+ "color: red;"
				+ "}"+
				"#validatePourchase{"+
				"width: 100%;"+
	"text-align: center;"+
"}"+
".boldI{"+
	"font-weight: bold;"+
"}"+
"#logo{"+
	"font-style: italic;"+
	"color: rgb(167,3,3);"+
	"font-weight: bold;"+
"}</style><h2>Votre commande a été créer avec succès</h2>"+
	"<p>Le numero de confirmation de votre commande est: <span class='boldI'>"+today.getTime()+user.getId()+"</span>. <br/>"+
	"Votre commande sera expédié dans les meilleurs délais à l'adresse: <span class='boldI'>"+user.getAdresse() +"</span>.</p>"+
	"<p>Tout l''équipe  <span id='logo'>WEBCATALOGUE</span> vous remercie pour votre visite.</p>" +
	"</div></body></html>";
		Thread thread = new Thread(new SendMail(user.getEmail(), "webcatalog@webmail.com", "LOCALHOST", "Your commande on webcatalogue", text));
		thread.start();
		
		return "validatePourchaseSucessFully";
	}
	
	

}








