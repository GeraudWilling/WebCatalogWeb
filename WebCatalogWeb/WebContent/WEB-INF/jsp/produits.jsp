<!--@
Autor Géraud Willing 
www.geraudwilling.com
-->

   
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<?xml version="1.0" encoding="UTF-8" ?>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<div>
<h2>Produits</h2>
<table>
	<c:forEach var="curr"  items="${produits}">
	<tr>
		<td><span id=''><c:out value="${curr.nom}"/></span></td>
		<td><button class="addCartButton" onclick='addToCart(event,<c:out value="${curr.id}"/>)'>Add to chart</button></td>
	</tr>
	</c:forEach>
</table>
</div>


				   