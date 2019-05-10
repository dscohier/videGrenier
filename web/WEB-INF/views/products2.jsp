<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

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
						<div class="col-lg-1 filter">
							FILTRE
							<br/>
							Quod cum ita sit, paucae domus studiorum seriis cultibus antea celebratae nunc ludibriis ignaviae torpentis exundant, vocali sonu, perflabili tinnitu fidium resultantes. denique pro philosopho cantor et in locum oratoris doctor artium ludicrarum accitur et bybliothecis sepulcrorum ritu in perpetuum clausis organa fabricantur hydraulica, et lyrae ad speciem carpentorum ingentes tibiaeque et histrionici gestus instrumenta non levia.

							Montius nos tumore inusitato quodam et novo ut rebellis et maiestati recalcitrantes Augustae per haec quae strepit incusat iratus nimirum quod contumacem praefectum, quid rerum ordo postulat ignorare dissimulantem formidine tenus iusserim custodiri.

						</div>
						<div class="col-lg-11">
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
											</c:if>
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

