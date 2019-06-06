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
			<jsp:include page="menu2.jsp"/>
			<h2>
				<spring:message code="home.welcome"/>
			</h2>
			<div style="border: 2px solid #d1c286; height: 200px">
				Dernier ajout :
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
							<div>
								<img src="data:image/jpg;base64,${product.displayPicture()}" style="height: 100px"/>
							</div>
						</a>
					</div>
				</c:forEach>
			</div>
			<br/>
			<div style="border: 2px solid #d1c286; height: 200px">
				Article les plus consulté :
			</div>
			<br/>
			<div style="border: 2px solid #d1c286; height: 200px">
				Nos catégorie :
			</div>
			<jsp:include page="footer2.jsp"/>
		</div>
	</body>
</html>