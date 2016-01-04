<!--@
Autor Géraud Willing 
www.geraudwilling.com
-->


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<?xml version="1.0" encoding="UTF-8" ?>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<div>
<h2>Récapitulatif</h2>
<table border='1' cellspacing='0' >
	<thead>
		<tr>
			<th>Article</th>
			<th>Quantite</th>
			<th>Prix Unitaire</th>
			<th>Prix Total</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="curr"  items="${cart}">
		<tr>
			<td><span id=''><c:out value="${curr.produit.nom}"/></span></td>
			<td><span id=''><c:out value="${curr.quantite}"/></span></td>
			<td><span id=''><c:out value="${curr.produit.prix}"/>&euro;</span></td>
			<td><span id=''><c:out value="${curr.produit.prix*curr.quantite}"/>&euro;</span></td>
		</tr>
		</c:forEach>
	</tbody>
</table>
<p>Total               : <input type="text" value='${total}' readonly="readonly"/></p>
<p>Adresse de Livraison: <input type="text" value='${user.adresse}' readonly="readonly"/></p>
<button onclick="validatePurchase(event);">Valider</button>
</div>