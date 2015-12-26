package org.webcatalog.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.webcatalog.json.CategorieJson;
import org.webcatalog.util.Context;

import perso.webcatalog.bean.Categorie;
import perso.webcatalog.remote.FacadeCategorieRemote;

@RestController
public class MainRest {

	@RequestMapping(value="/categories.service", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public List<CategorieJson> getAllCategories() {
		System.out.println("**getAllCategories()**");
		List<Categorie> categories= null;
		List<CategorieJson> categoriesJson= new ArrayList<CategorieJson>();
		@SuppressWarnings("unchecked")
		FacadeCategorieRemote<Categorie> facadeCategorieRemote = (FacadeCategorieRemote<Categorie>)Context.jndiLookup(FacadeCategorieRemote.class);
		categories= facadeCategorieRemote.findAll();
		
		for(Categorie cat: categories){
			categoriesJson.add(new CategorieJson(cat));
		}
		System.out.println("exiting *"+ categoriesJson.size());
		return categoriesJson;
	}

}
