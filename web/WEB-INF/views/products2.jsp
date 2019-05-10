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
							<h4><spring:message code="product.products.filter"/></h4>
							<br/>
							<form:form cssClass="form-horizontal" method="get" action="filter" commandName="filterForm" autocomplete="off">
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
								<input type="text" id="autocomplete" name="city" value="${filterForm.city}"/>
								<div style="visibility: hidden;">
									<table id="address">
										<td><form:input type="text" id="locality" name="city" path="city"></form:input></td>
										<td><form:input type="text" path="country" name="country"
														id="country"></form:input></td>
									</table>
								</div>
								<c:forEach var="typeOfSale" items="${typeOfSale}">
									<label class="control control-checkbox">
											${typeOfSale}
										<form:checkbox path="typeOfSale" value="${typeOfSale}"/>
										<div class="control_indicator">
										</div>
									</label>
								</c:forEach>
								<br/>
								<input type="submit" class="btn btn-primary" value="<spring:message code="product.products.filter"/>">
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
						<div class="col-lg-10">
							<c:forEach var="product" items="${products}" varStatus="loop">
								<c:if test="${loop.index%3 == 0}">
									<div class="row">
								</c:if>
								<div class="col-md-4 text-center animate-box" style="margin-top: 20px;">
									<a href="<c:url value="/profile?username=${product.seller.username}"/>">
										<div class="fh5co-staff" style="margin-bottom: 5px">
											<img src="data:image/jpg;base64,${product.seller.displayPicture()}" style="width: 70px"
												 alt="<c:url value="/resources/img/userProfile.png"/>">
											<span class="rate">
												<h4 style="display: inline-block">${product.seller.username}</h4>
                                                    <c:forEach begin="1" end="${product.seller.averageRatingSeller}">
														<i class="icon-star2" style="color:yellow"></i>
													</c:forEach>
                                                    <c:forEach begin="${product.seller.averageRatingSeller + 1}" end="5">
														<i class="icon-star"></i>
													</c:forEach>
                                                    <p style="display: inline-block">${product.seller.commentByBuyer.size()}</p>
                                                </span>
										</div>
									</a>
									<a href="<c:url value="/product/details?id=${product.id}"/>" style="margin-top: 100px">
										<h3>${product.name}</h3>
										<div class="product">
											<img src="data:image/jpg;base64,${product.displayPicture()}">
										</div>
										<div class="desc" style="margin-top: 10px;">
											<c:if test="${not product.auction}">
												<h4 style="color: #d1c286;"><spring:message code="common.directSale"/></h4>
												<span class="price">
													<spring:message code="common.price"/>
													${product.price}&euro;
												</span>
											</c:if>
											<c:if test="${product.auction}">
												<h4 style="color: #d1c286"><spring:message code="common.bid"/></h4>
												<span class="price">
													<spring:message code="product.details.actualPrice"/>
													${product.price}&euro;
												</span>
												<br/>
												<span class="price">
													<spring:message code="product.details.endDate"/>
													<fmt:formatDate pattern="dd-MM-yyyy HH:mm" value="${product.endDate}"/>
												</span>
											</c:if>
											<br/>
											<span class="price">
													<spring:message code="common.city"/>
													${product.seller.city.country}, ${product.seller.city.name}
											</span>
											<br/>
											<span class="price">
													<spring:message code="product.products.added"/>
													<fmt:formatDate pattern="dd-MM-yyyy HH:mm" value="${product.creationDate}"/>
											</span>
										</div>
									</a>
								</div>
								<c:if test="${loop.index%3 == 2}">
									</div>
								</c:if>
							</c:forEach>
							<c:if test="${products.size()%3 != 0}">
						</div>
						</c:if>
					</div>
				</div>
			</div>
			<jsp:include page="footer2.jsp"/>
		</div>
	</body>
</html>

