package org.webcatalog.util;

import perso.webcatalog.bean.Produit;

public class Panier {
	
	private Produit produit;
	private int quantite;

	
	public Panier() {
		super();
		produit= new Produit();
		quantite=0;
	}
	
	public Panier(Produit produit, int quantite) {
		super();
		this.produit = produit;
		this.quantite = quantite;
	}
	
	
	public Produit getProduit() {
		return produit;
	}
	public void setProduit(Produit produit) {
		this.produit = produit;
	}
	public int getQuantite() {
		return quantite;
	}
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	
}
