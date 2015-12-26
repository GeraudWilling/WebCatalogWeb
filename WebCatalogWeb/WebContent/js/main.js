function loadCategories(event){
	$.ajaxSetup({ cache: false });
	$("#index_content").load("categories.xhtml");
	$("#cat-content").load("categories.xhtml");
	event.preventDefault();
}

function loadProduits(event,id){
	$.ajaxSetup({ cache: false });
	$("#index_content").load("produits.xhtml?id="+ id);
	event.preventDefault();
}

function addToCart(event,id){
	$.ajaxSetup({ cache: false });
	$("#index_content").load("addToCart.xhtml?id="+ id, function(a,b,c){
		$('body').trigger('finishCartLoad');
	});
	event.preventDefault();
}

function actualise(event){
	$.ajaxSetup({ cache: false });
	$("#cart").load("cartDiv.xhtml");
	event.preventDefault();
}

function displayCart(event){
	$.ajaxSetup({ cache: false });
	$("#index_content").load("panier.xhtml");
	event.preventDefault();
}

function validate(event){
	$.ajaxSetup({ cache: false });
	$("#index_content").load("validate.xhtml");
	event.preventDefault();
}

function login(event){
	$.ajaxSetup({ cache: false });
	$("#index_content").load("login.xhtml");
	event.preventDefault();
}

function inscription(event){
	$.ajaxSetup({ cache: false });
	$("#index_content").load("inscription.xhtml");
	event.preventDefault();
}

function submitInscription(){
	var str = $("#form").serialize();

	$.ajax({
	    type:"post",
	    data:str,
	    url:"/inscription.xhtml",
	    async: false,
	    dataType: "json",
	    success: function(){
	       alert("success");
	    }
	});
}
