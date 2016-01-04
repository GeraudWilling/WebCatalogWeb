<!--@
Autor Géraud Willing 
www.geraudwilling.com
-->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:url var="insUrl" value="/inscription.service"/>

<div class="inscription">
      <h2>Inscription</h2>
      <form:form method="post" modelAttribute="client" class="form" action="${insUrl}" onsubmit="submitInscription(event)">
	        <p><form:input path="nom" class="input" placeholder="Nom" id="nom"/></p>
	        <p><form:input path="email" class="input" placeholder="email" id="email"/></p>
	        <p><form:input path="tel" class="input" placeholder="Telephone" id="tel"/></p>
	        <p><form:input path="adresse" class="input" placeholder="Adresse" id="adresse"/></p>
	        <p><form:password path="carte" class="input" id="password1" placeholder="Mot de Passe"/></p>
	        <p><input type="password" id="password2" class="input" placeholder="Confirmation password"/></p>
	        <p class="submit"><input type="button"  value="Inscription"  name="commit" onclick="submitInscription(event)"/></p>
      </form:form>
</div>

<style>
	.login{
		width: 100%;
	}
	.form{
		width: 50%;
		margin-left: 25%;		
	}
	.input{
		width: 100%;
		margin-top: 1%;
	}
	input[type=submit]{
		background-color: rgba(0,0,0,0.2);
	}
</style>