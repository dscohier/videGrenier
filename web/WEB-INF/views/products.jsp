<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE HTML>
<html>
	<jsp:include page="head.jsp"/>
	<body>
		<div id="page">
			<jsp:include page="menu2.jsp"/>
			<div id="fh5co-product">
				<div class="container">
					<div class="row animate-box">
						<div class="col-md-8 col-md-offset-2 text-center">
							<h2><spring:message code="product.products.productToSell"/></h2>
						</div>
					</div>
					<br/>
					<div class="container">
						<div class="col-lg-2 filter text-center">
                            <br/>
							<h4><spring:message code="common.filter"/></h4>
							<form:form cssClass="form-horizontal" method="get" action="filterProducts" commandName="filterProductsForm" autocomplete="off">
								<c:forEach var="categorie" items="${categories}">
									<label class="control control-checkbox">
											${categorie}
										<form:checkbox path="categories" value="${categorie}"/>
										<div class="control_indicator">
										</div>
									</label>
								</c:forEach>
								<br/>
								<spring:message code="common.city"/>
								<input type="text" id="autocomplete" name="city" value="${filterProductsForm.city}"/>
								<div style="visibility: hidden;">
									<table id="address">
										<td><form:input type="text" id="locality" name="city" path="city"></form:input></td>
										<td><form:input type="text" path="country" name="country"
														id="country"></form:input></td>
									</table>
								</div>
								<spring:message code="product.add.title"/>
								<input type="text" name="title" value="${filterProductsForm.title}"/>
								<br/><br/>
								<c:forEach var="typeOfSale" items="${typeOfSale}">
									<label class="control control-checkbox">
											${typeOfSale}
										<form:checkbox path="typeOfSale" value="${typeOfSale}"/>
										<div class="control_indicator">
										</div>
									</label>
								</c:forEach>
								<br/>
								<input type="submit" class="btn btn-primary" value="<spring:message code="common.search"/>">
								<script>
                                    var autocomplete;
                                    var componentForm = {
                                        locality: 'long_name',
                                        country: 'long_name'
                                    };

                                    function initSearch() {
                                        autocomplete = new google.maps.places.Autocomplete((
                                            document.getElementById('autocomplete')), {
                                            types: ['(cities)']
                                        });
                                        autocomplete.addListener('place_changed', fillInAddress);
                                    }

                                    function fillInAddress() {
                                        // Get the place details from the autocomplete object.
                                        var place = autocomplete.getPlace();
                                        for (var i = 0; i < place.address_components.length; i++) {
                                            var addressType = place.address_components[i].types[0];
                                            if (componentForm[addressType]) {
                                                var val = place.address_components[i][componentForm[addressType]];
                                                document.getElementById(addressType).value = val;
                                            }
                                        }

                                    }
								</script>
								<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCBaebZ5i6230uvRvSiAqecuRv_pEqnXcA&signed_in=true&libraries=places&callback=initSearch&language=fr"
										async defer></script>
							</form:form>
						</div>
						<c:if test="${filter}">
							<c:url var="action"  value="/product/filterProducts" />
						</c:if>
						<c:if test="${filter}">
							<c:url var="action"  value="/product/products" />
						</c:if>
						<jsp:include page="productList.jsp"/>
				</div>
			</div>
			<jsp:include page="footer2.jsp"/>
		</div>
	</body>
</html>

