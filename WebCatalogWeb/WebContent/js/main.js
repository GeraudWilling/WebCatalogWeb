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
	$('body').on('finishCartLoad', function() {
		actualise(event);
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

function submitInscription(event){
	$.ajaxSetup({ cache: false });
	var mydata= $(".form").serialize();
	if($("#nom").val() == "" || $("#nom").val()== "" ||$("#nom").val()== ""
		|| $("#nom").val()== "" || $("#nom").val() == ""){
		alert("Veuillez remplir tous les champs");
	}
	else if($("#password1").val() != $("#password2").val() ){
		alert("Mots de passes differents");
	}
	else{
		$.ajax({
	        url: $(".form").attr('action'), 
	        type: $(".form").attr('method'), 
	        data: mydata, 
	        success: function(html) { 
	        	$("#index_content").load("validate.xhtml");
	        }
	    });
		event.preventDefault();
	}
}


function checkLogin(event){
	$.ajaxSetup({ cache: false });
	var mydata= $(".form").serialize();
	//$("#index_content").load("checkLogin.xhtml?mail="+$("#login").val()+"&pw="+ $("#password") );
	$.ajax({
        url: $(".form").attr('action'), 
        type: $(".form").attr('method'), 
        data: mydata, 
        success: function(html) { 
        	$("#index_content").load("validate.xhtml");
        }
    });
	event.preventDefault();
}

function documentReady(){
	$(document).ready(function(){
		$('#password2').on('keyup', function(){
			if($('#password1').val() !=  $('#password2').val()){
				$('#password2').attr("class", "error");
			}
			else{
				$('#password2').attr("class", "sucess");
			}
		});
		
	});
}
