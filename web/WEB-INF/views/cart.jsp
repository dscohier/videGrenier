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
							<h2><spring:message code="product.cart.myCart"/></h2>
						</div>
					</div>
					<br/>
					<div class="container">
						<div class="col-lg-10" style="border-bottom: #d1c286">
							<c:if test="${not empty error}">
								<label class="error"><spring:message code="${error}"/></label>
							</c:if>
							<c:forEach var="product" items="${products}">
							<div class="row">
								<div class="col-lg-3">
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
								</div>
								<a href="<c:url value="/product/details?id=${product.id}"/>">
								<div class="col-lg-9" style="height: 150px;">
										<div class="product">
											<div class="col-lg-3">
												<img src="data:image/jpg;base64,${product.displayPicture()}" style="height: 150px; width: 150px">
											</div>
											<div class="col-lg-7" style="height: auto">
												<h3>${product.name}</h3>
											</div>
											<div class="col-lg-2">
												<spring:message code="common.price"/>
													${product.price}&euro;
											</div>
										</div>
							</div>
								</a>
							</div>
							</c:forEach>
						</div>
						<div class="col-lg-2 filter text-center">
							<br/>
							<h4 style="color: red"><spring:message code="product.cart.total"/> = ${total}&euro; </h4>
						</div>
					</div>
				</div>
			</div>
			<jsp:include page="footer2.jsp"/>
		</div>
	</body>
</html>