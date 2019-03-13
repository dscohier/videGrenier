<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>VIDE GRENIER</title>
</head>
<body>
<jsp:include page="menu.jsp"/>
<div>
    <c:if test="${not empty error}">
        <label class="error"><spring:message code="${error}"/></label>
    </c:if>
    <div class="row">
        <div class="col-lg-1">
            FILTRE
        </div>
        <div class="col-lg-offset-1 col-lg-11">
        <c:forEach var="product" items="${products}">
                    <div class="card text-white bg-secondary mb-3" style="margin-top: 15px">
                        <div class="card-header">
                            <div class="col-lg-8" style="display: inline-block">
                                <h3>${product.name}</h3>
                            </div>
                            <div class="col-lg-2" style="display: inline-block; margin-left:16%">
                                <h3 style="text-align: right">${product.price}€</h3>
                            </div>
                        </div>
                        <div class="card-body">
                            <div class="col-lg-2" style="display: inline-block;">
                                <img  class="img-responsive" src="data:image/jpg;base64,${product.displayPicture()}" width="150" height="150"/>
                            </div>
                            <div class="col-lg-7" style="display: inline-block;">
                                <c:if test="${not product.auction}">
                                    <h5 style="text-align: center;"><spring:message code="common.directSale"/></h5>
                                </c:if>
                                <c:if test="${product.auction}">
                                    <h5 style="text-align: center;"><spring:message code="common.bid"/></h5>
                                </c:if>
                                DETAILS
                            </div>
                            <div class="col-lg-1" style="display: inline-block;">
                                Info vente
                            </div>
                        </div>
                    </div>
            </c:forEach>
            <c:if test="${not empty size}">
                <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
                    <div class="btn-group mr-2" role="group" aria-label="First group">
                        <c:forEach begin="1" end="${size}" varStatus="i">
                            <c:if test="${i.count eq currentPage}">
                                <button type="button" class="btn btn-secondary" disabled>${i.count}</button
                            </c:if>
                            <c:if test="${i.count ne currentPage}">
                                <button type="button" class="btn btn-secondary">${i.count}</button
                            </c:if>
                        </c:forEach>
                    </div>
                </div>
            </c:if>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>