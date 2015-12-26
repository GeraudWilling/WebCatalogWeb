package org.webcatalog.json;

import java.io.Serializable;

import perso.webcatalog.bean.Categorie;

public class CategorieJson{
	
	private long id;
	private String name;
	
	public CategorieJson() {

	}
	public CategorieJson(Categorie cat){
		this.id=cat.getId();
		this.name=cat.getNom(); 
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
