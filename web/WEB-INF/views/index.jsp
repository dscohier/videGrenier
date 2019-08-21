<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
	<jsp:include page="head.jsp"/>
	<body>
		<div id="page">
			<jsp:include page="menu.jsp"/>
			<h2>
				<spring:message code="home.welcome"/>
			</h2>
			<div style="border: 2px solid #d1c286; height: 300px">
				<spring:message code="home.lastAdded"/> :
				<br/>
				<c:forEach var="product" items="${lastAdded}">
					<div class="col-lg-2">
						<a href="<c:url value="/product/details?id=${product.id}"/>" style="display: inline-block">
							<c:if test = "${product.name.length() > 30}">
								${fn:substring(product.name.toLowerCase(),0,30)}...
							</c:if>
							<c:if test = "${product.name.length() <= 30}">
								${product.name.toLowerCase()}
							</c:if>
							<div style="height: 120px; background: url('data:image/png;base64,${product.displayPicture()}') center center no-repeat; background-size: contain;">
							</div>
							<div class="desc">
								<c:if test="${not product.auction}">
									<spring:message code="common.directSale"/>
									<span class="price">
										<spring:message code="common.price"/>
											${product.price}&euro;
									</span>
								</c:if>
								<c:if test="${product.auction}">
									<spring:message code="common.bid"/>
									<span class="price">
										<spring:message code="product.details.actualPrice"/>
											${product.price}&euro;
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
				</c:forEach>
			</div>
			<br/>
			<div style="border: 2px solid #d1c286; height: 300px">
				<spring:message code="home.mostViewed"/> :
				<br/>
				<c:forEach var="product" items="${mostViewed}">
					<div class="col-lg-2">
						<a href="<c:url value="/product/details?id=${product.id}"/>" style="display: inline-block">
							<c:if test = "${product.name.length() > 30}">
								${fn:substring(product.name.toLowerCase(),0,30)}...
							</c:if>
							<c:if test = "${product.name.length() <= 30}">
								${product.name.toLowerCase()}
							</c:if>
							<div style="height: 120px; background: url('data:image/png;base64,${product.displayPicture()}') center center no-repeat; background-size: contain;">
							</div>
							<div class="desc">
								<c:if test="${not product.auction}">
									<spring:message code="common.directSale"/>
									<span class="price">
										<spring:message code="common.price"/>
											${product.price}&euro;
									</span>
								</c:if>
								<c:if test="${product.auction}">
									<spring:message code="common.bid"/>
									<span class="price">
										<spring:message code="product.details.actualPrice"/>
											${product.price}&euro;
									</span>
								</c:if>
								<br/>
								<span class="price">
									<spring:message code="common.city"/>
										${product.seller.city.country}, ${product.seller.city.name}
								</span>
								<br/>
								<span class="price">
									&#128065; &nbsp;${product.view}
								</span>
							</div>
						</a>
					</div>
				</c:forEach>
			</div>
			<br/>
			<div style="border: 2px solid #d1c286; height: 620px">
				<spring:message code="home.categories"/> :
				<div>
					<a class="categories" href="<c:url value="/product/products"/>">
						<div class="categoriesPicture">
							<img src="<c:url value="/resources/img/all.jpg"/>" width="240px" height="240px" />
							<spring:message code="categorie.all"/>
						</div>
					</a>
					<a class="categories" href="<c:url value="/product/products?category=INFORMATIQUE"/>">
						<div class="categoriesPicture">
							<img src="<c:url value="/resources/img/computer.jpg"/>"/>
							<spring:message code="categorie.it"/>
						</div>
					</a>
					<a class="categories" href="<c:url value="/product/products?category=AUTO_MOTO"/>">
						<div class="categoriesPicture">
							<img src="<c:url value="/resources/img/car.jpg"/>"/>
							<spring:message code="categorie.vehicle"/>
						</div>
					</a>
					<a class="categories" href="<c:url value="/product/products?category=PHOTO_VIDEO"/>">
						<div class="categoriesPicture">
							<img src="<c:url value="/resources/img/camera.jpg"/>"/>
							<spring:message code="categorie.camera"/>
						</div>
					</a>
					<a class="categories" href="<c:url value="/product/products?category=MODE"/>">
						<div class="categoriesPicture">
							<img src="<c:url value="/resources/img/fashion.jpg"/>"/>
							<spring:message code="categorie.fashion"/>
						</div>
					</a>
					<a class="categories" href="<c:url value="/product/products?category=BIJOUX"/>">
						<div class="categoriesPicture">
							<img src="<c:url value="/resources/img/jewelry.jpg"/>"/>
							<spring:message code="categorie.jewelry"/>
						</div>
					</a>
					<a class="categories" href="<c:url value="/product/products?category=MUSIQUE"/>">
						<div class="categoriesPicture">
							<img src="<c:url value="/resources/img/audio.jpg"/>"/>
							<spring:message code="categorie.audio"/>
						</div>
					</a>
					<a class="categories" href="<c:url value="/product/products?category=JEUX_JOUET"/>">
						<div class="categoriesPicture">
							<img src="<c:url value="/resources/img/toys.jpg"/>"/>
							<spring:message code="categorie.toys"/>
						</div>
					</a>
					<a class="categories" href="<c:url value="/product/products?category=BOOK"/>">
						<div class="categoriesPicture">
							<img src="<c:url value="/resources/img/books.jpg"/>"/>
							<spring:message code="categorie.books"/>
						</div>
					</a>
					<a class="categories" href="<c:url value="/product/products?category=ANIMALERIE"/>">
						<div class="categoriesPicture">
							<img src="<c:url value="/resources/img/animals.jpg"/>"/>
							<spring:message code="categorie.animals"/>
						</div>
					</a>
					<a class="categories" href="<c:url value="/product/products?category=BRICOLAGE"/>">
						<div class="categoriesPicture">
							<img src="<c:url value="/resources/img/crafts.jpg"/>"/>
							<spring:message code="categorie.crafts"/>
						</div>
					</a>
					<a class="categories" href="<c:url value="/product/products?category=AUTRES"/>">
						<div class="categoriesPicture">
							<img src="<c:url value="/resources/img/other.jpg"/>"/>
							<spring:message code="categorie.other"/>
						</div>
					</a>
				</div>
			</div>
			<jsp:include page="footer.jsp"/>
		</div>
	</body>
</html>