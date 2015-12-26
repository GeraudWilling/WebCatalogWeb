<!--@
Autor Géraud Willing 
www.geraudwilling.com
-->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<div class="login">
      <h2>Login</h2>
      <form method="post" action="/" class="form">
	        <p><input class="input" type="text" name="login" value="" placeholder="Username or Email"></p>
	        <p><input class="input" type="password" name="password" value="" placeholder="Password"></p>
	        <p class="submit"><input type="submit" name="commit" value="Login"></p>
	        <p>Pas encore inscrit, créer un compte <a href="" onclick="inscription(event);">ici</a></p>
      </form>
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