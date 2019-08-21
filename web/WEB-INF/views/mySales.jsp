<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE HTML>
<html>
	<jsp:include page="head.jsp"/>
	<body>
		<div id="page">
			<jsp:include page="menu.jsp"/>
			<div id="fh5co-product">
				<div class="container">
					<div class="row animate-box">
						<div class="col-md-8 col-md-offset-2 text-center">
							<h2><spring:message code="common.mySales"/></h2>
						</div>
					</div>
					<br/>
					<div class="container">
						<div class="col-lg-2 filter text-center">
							<br/>
							<h4><spring:message code="common.filter"/></h4>
							<form:form cssClass="form-horizontal" method="get" action="filterSales" commandName="filterSalesForm" autocomplete="off">
								<c:forEach var="sellOrNot" items="${sellOrNot}">
									<label class="control control-checkbox">
											${sellOrNot}
										<form:checkbox path="sellOrNot" value="${sellOrNot}"/>
										<div class="control_indicator">
										</div>
									</label>
								</c:forEach>
								<br/><br/>
								<c:forEach var="categorie" items="${categories}">
									<label class="control control-checkbox">
											${categorie}
										<form:checkbox path="categories" value="${categorie}"/>
										<div class="control_indicator">
										</div>
									</label>
								</c:forEach>
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
							</form:form>
						</div>
						<c:if test="${filter}">
							<c:url var="action"  value="/product/filterSales" />
						</c:if>
						<c:if test="${filter}">
							<c:url var="action"  value="/product/mySales" />
						</c:if>
						<jsp:include page="productList.jsp"/>
					</div>
				</div>
				<jsp:include page="footer.jsp"/>
			</div>
	</body>
</html>

