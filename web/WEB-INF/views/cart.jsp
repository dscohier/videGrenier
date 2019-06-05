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
						<div class="col-lg-10">
							<c:if test="${not empty error}">
								<label class="error"><spring:message code="${error}"/></label>
							</c:if>
							<c:forEach var="product" items="${products}">
							<div class="row"  style="border-bottom: 2px solid #d1c286; ">
								<div class="col-lg-3">
									<a href="<c:url value="/profile?username=${product.seller.username}"/>">
										<div class="fh5co-staff" style="margin-bottom: 5px; display: flex; height: 150px">
											<img src="data:image/jpg;base64,${product.seller.displayPicture()}" style="width: 70px; align-self: center"
												 alt="<c:url value="/resources/img/userProfile.png"/>">
											<span class="rate" style="align-self: center">
												<h4 style="display: inline-block;">${product.seller.username}</h4>
											</span>
										</div>
									</a>
								</div>
								<a href="<c:url value="/product/details?id=${product.id}"/>">
								<div class="col-lg-9">
										<div class="product">
											<div class="col-lg-3">
												<img src="data:image/jpg;base64,${product.displayPicture()}" style="height: 150px; width: 150px">
											</div>
											<div class="col-lg-6" style="display: flex; height: 150px">
												<h3 style="align-self: center">${product.name}</h3>
											</div>
											<div class="col-lg-2" style="display: flex; height: 150px">
												<h3 style="align-self: center"> <spring:message code="common.price"/>
														${product.price}&euro;</h3>
											</div>
											<div class="col-lg-1" style="display: flex; height: 150px">
												<form:form cssClass="form-horizontal" method="post" action="deleteFromCart">
													<div style="display: none;">
														<input type="text" name="idProduct" value="${product.id}"/>
													</div>
													<button class="btn btn-primary btn-outline btn-lg" type="submit" style="margin-top:33%">
														<i class="fa fa-trash"></i>
													</button>
												</form:form>
											</div>
										</div>
								</div>
								</a>
							</div>
							</c:forEach>
						</div>
						<div class="col-lg-2 filter text-center" style="margin-top: 1%">
							<br/>
							<h4 style="color: red;"><b><spring:message code="product.cart.total"/> = ${total}&euro; </b></h4>
						</div>
					</div>
				</div>
			</div>
			<jsp:include page="footer2.jsp"/>
		</div>
	</body>
</html>