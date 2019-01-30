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
<c:if test="${not empty error}">
    <label class="error"><spring:message code="${error}"/></label>
</c:if>
<div class="panel panel-primary">
    <div class="panel-heading">
        <font color="#0ce3ac">
            <h3>${product.name}</h3>
        </font>
    </div>
    <div class="panel-body">
        <div class="col-sm-3">
            <img  class="img-responsive" src="data:image/jpg;base64,${picture}"/>
        </div>
    </div>

    <div class="panel panel-default">
        <div class="panel-heading">
            <font color="#0ce3ac">
                <h3>Description</h3>
            </font>
        </div>
        <div class="panel-body">
            ${product.description}
        </div>
    </div>

    <br />
    créateur : ${product.seller.lastName}
    <br />
    date de création: ${product.creationDate}
    <br />
</div>

<jsp:include page="footer.jsp"/>

</body>

</html>