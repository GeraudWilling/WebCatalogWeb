<!--@Autor Géraud Willing -->

<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page isELIgnored="false" %>
   
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div id="menu">
<input type="hidden" id="categoriesUrl" value='<c:url value="/categories.service"/>'/> 
	<ul>
		<li><a href="">Accueil</a></li>
		<li><a onclick="loadCategories(event);" href=''>Categorie</a></li>
		<li><a href="" onclick="displayCart(event);">Panier</a></li>
		<li><a href="" onclick="login(event);">Connexion</a></li>	
	</ul>
</div>

