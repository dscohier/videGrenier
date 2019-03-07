<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>VIDE GRENIER</title>
</head>
<body>
<jsp:include page="menu.jsp"/>
<c:if test="${not empty error}">
    <label class="error"><spring:message code="${error}"/></label>
</c:if>
    <div class="panel-heading">
        <font color="#0ce3ac">
            <h3>${product.name}</h3>
        </font>
    </div>
<div class="row">
    <div class="col-lg-4">
        <img  class="img-responsive" src="data:image/jpg;base64,${picture}" width="350" height="350"/>
    </div>
    <div class="col-lg-4">
        <div class="card text-white bg-primary mb-3" style="width: 500px;">
            <div class="card-header"><h3 style="text-align: center"><spring:message code="product.details.seller"/></h3></div>
            <div class="card-body">
                <h5 class="card-title"><spring:message code="product.details.addedBy"/> : ${product.seller.lastName}</h5>
                <p class="card-text"><spring:message code="common.city"/> : ${product.seller.city.name}</p>
                <sec:authorize access="isAuthenticated()">
                    <sec:authentication property="principal.username" var="username" />
                    <c:if test = "${!product.seller.username.equals(username)}">
                        <p class="card-text"><spring:message code="product.details.distance"/> : </p></c:if>
                </sec:authorize>
                <p class="card-text"><spring:message code="product.details.creationDate"/> : ${product.creationDate}</p>
                <c:if test="${product.auction}">
                    <p class="card-text"><spring:message code="product.details.endDate"/> : ${product.endDate}</p>
                </c:if>
                <sec:authorize access="isAuthenticated()">
                    <sec:authentication property="principal.username" var="username" />
                    <c:if test = "${!product.seller.username.equals(username)}">
                        <button class="btn btn-primary btn-lg btn-block" type="submit" disabled><spring:message code="product.details.sendMessage"/></button>
                    </c:if>
                </sec:authorize>
                <sec:authorize access="!isAuthenticated()">
                    <button class="btn btn-primary btn-lg btn-block" type="submit" disabled><spring:message code="product.details.sendMessage"/></button>
                    <label class="error"><spring:message code="error.details.sendMessage"/></label>
                </sec:authorize>
            </div>
        </div>
    </div>
    <div class="col-lg-4">
        <div class="card text-white bg-primary mb-3" style="width: 500px;">
            <c:if test="${product.auction}">
                <div class="card-header"><h4 style="text-align: center"><spring:message code="product.details.actualPrice"/> : ${product.price}€ </h4></div>
                <c:if test="${lastBidder ne null}">
                    <spring:message code="product.details.priceOfferedBy"/> : ${lastBidder.user.username} <spring:message code="product.details.on"/> ${lastBidder.insertionDate}
                </c:if>
            </c:if>
            <c:if test="${!product.auction}">
                <div class="card-header"><h4 style="text-align: center"><spring:message code="common.price"/> : ${product.price}€ </h4></div>
            </c:if>

            <div class="card-body">
                <sec:authorize access="isAuthenticated()">
                    <sec:authentication property="principal.username" var="username" />

                    <c:if test = "${!product.seller.username.equals(username)}">
                        <c:if test="${product.auction}">
                            <form:form cssClass="form-horizontal" method="post" action="bid" enctype="multipart/form-data" commandName="bidForm">
                                <form:input type="text" path="newPrice" id="newPrice" style="margin-left: 25%;font-size:18px;"/>
                                <button class="btn btn-primary btn-lg btn-block" type="submit"><spring:message code="common.bid"/></button>
                                <div style="visibility: hidden;">
                                    <form:input type="text" path="idProduct" cssClass="form-control" id="idProduct"/>
                                </div>
                            </form:form>
                        </c:if>
                        <c:if test="${!product.auction}">
                             <button class="btn btn-primary btn-lg btn-block" type="submit"><spring:message code="product.details.addToBasket"/></button>
                        </c:if>
                    </c:if>
                </sec:authorize>
                <sec:authorize access="!isAuthenticated()">
                    <c:if test="${!product.auction}">
                        <button class="btn btn-primary btn-lg btn-block" type="submit" disabled><spring:message code="product.details.addToBasket"/></button>
                        <label class="error"><spring:message code="error.details.addToBasket"/></label>
                    </c:if>
                    <c:if test="${product.auction}">
                            <input type="text" cssClass="form-control" id="newPrice" style="margin-left: 25%;" readonly/>
                            <button class="btn btn-primary btn-lg btn-block" type="submit" disabled><spring:message code="common.bid"/></button>
                            <label class="error"><spring:message code="error.details.bid"/></label>
                    </c:if>
                </sec:authorize>
            </div>
        </div>
        <sec:authorize access="isAuthenticated()">
            <sec:authentication property="principal.username" var="username" />
            <c:if test = "${product.seller.username.equals(username)}">
                <form:form cssClass="form-horizontal" method="get" action="updateProduct" enctype="multipart/form-data" commandName="updateProductForm">
                    <div style="visibility: hidden;">
                        <form:input type="text" path="id" cssClass="form-control" id="id"/>
                    </div>
                    <button class="btn btn-primary btn-lg btn-block" type="submit" style="width: 500px"><spring:message code="product.details.edit"/></button>
                </form:form>
            </c:if>
        </sec:authorize>
    </div>

    <div class="card text-white bg-secondary mb-3" style="width: 99%; height: 300px">
        <div class="card-header"><spring:message code="common.description"/></div>
        <div class="card-body">
            <p class="card-text">
                <div style="overflow: auto; height: 350px;">
                    ${product.description}
                </div>
            </p>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"/>
</body>

</html>