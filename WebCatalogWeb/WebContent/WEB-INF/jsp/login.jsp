<!--@
Autor Géraud Willing 
www.geraudwilling.com
-->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<c:url var="logUrl" value="/login.service"/>

<div class="login">
      <h2>Login</h2>
      <form:form method="post" modelAttribute="clientLogin" class="form" action="${logUrl}" >
	        <p><form:input   path="email" class="input" value="" placeholder="Username or Email" id="login" /></p>
	        <p><form:password   path="carte" class="input" value="" placeholder="Password" id= "password" /></p>
	        <p class="submit"><button  type="button" name="commit" onclick="checkLogin(event);">Login</button></p>
	        <p>Pas encore inscrit, créer un compte <a href="" onclick="inscription(event);">ici</a></p>
	        <p><a href="" onclick="mdp(event);">Mot de pass oublié? </a></p>
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