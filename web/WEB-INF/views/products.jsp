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
    <c:forEach var="product" items="${products}">
        <div>
            nom : ${product.name}
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
<jsp:include page="footer.jsp"/>
</body>
</html>