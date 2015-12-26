<!--@
Autor Géraud Willing 
www.geraudwilling.com
-->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<div class="inscription">
      <h2>Inscription</h2>
      <form:form method="post" modelAttribute="client" class="form">
	        <p><form:input path="nom" placeholder="Nom"/></p>
	        <p><form:input path="email" placeholder="email"/></p>
	        <p><form:input path="tel" placeholder="Telephone"/></p>
	        <p><form:input path="adresse" placeholder="Adresse"/></p>
	        <p><form:password path="carte" id="password1" placeholder="Mot de Passe"/></p>
	        <p><input type="password" id="password2" placeholder="Confirmation password"/></p>
	        <p class="submit"><input type="submit" name="commit" value="Inscription"></p>
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