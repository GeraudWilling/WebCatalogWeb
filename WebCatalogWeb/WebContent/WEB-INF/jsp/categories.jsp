<!--@
Autor Géraud Willing 
www.geraudwilling.com
-->

   
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div>
<h2>Categories</h2>
<ul id="categories_ul">
	<c:forEach var="curr"  items="${categories}">
		<li><a href="" id="<c:out value="${curr.id}"/>" onclick="loadProduits(event,${curr.id} )"><c:out value="${curr.nom}"/></a></li>
	</c:forEach>
</ul>
</div>