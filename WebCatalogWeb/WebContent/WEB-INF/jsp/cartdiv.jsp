<!-- Cart -->
<?xml version="1.0" encoding="UTF-8" ?>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

			<a href="#" class="cart-link">Your  ping Cart</a>
			<div class="cl">&nbsp;</div>
			<span>Articles: <strong>${fn:length(cart)}</strong></span>
			&nbsp;&nbsp;
			<span>Cost: <strong>${total} &euro;</strong></span>
<!-- End Cart -->