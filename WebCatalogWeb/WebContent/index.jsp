<!--@Autor Géraud Willing -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<?xml version="1.0" encoding="UTF-8" ?>

<html>
<head>
	<title>WEB CATALOGUE</title>
	<META charset="utf-8"/>
	<style type="text/css">
	</style>
	<script type="text/javascript" src='<c:url value="/js/main.js"/>'></script>
	<%-- <script type="text/javascript" src='<c:url value="/js/jquery-ui.js"/>'></script> --%>
	<link href='<c:url value="/css/main.css"/>' rel="stylesheet">
	<script src="js/jquery-1.10.2.js"></script>
	<script src="js/jquery-ui.js"></script>
	
</head>
<body>
	
	<c:import url="/jsp/header.jsp" />
	
	<div id="content">
		<c:import url="/jsp/menu.jsp" />
		
		<div id="index_content" style="text-align:center">
			<c:import url="/jsp/welcome.jsp" />
		</div>
	</div>
	<c:import url="/jsp/footer.jsp" />
	
	
	
	<!--  Javascript functions -->
	<script type="text/javascript">
	
</script>
</body>
</html>