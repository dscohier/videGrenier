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
<div>
    <form:form cssClass="form-horizontal" method="post" action="add" enctype="multipart/form-data" commandName="addProductForm" >
     <c:if test="${not empty error}">
         <label class="error"><spring:message code="${error}"/></label>
     </c:if>
    <h2><spring:message code="product.add.titre"/></h2>

        <div class="form-group">
            <label for="title" class="col-lg-3 control-label"><spring:message code="product.add.title"/></label>
            <div class="col-lg-3">
                <form:input type="text" path="name" cssClass=" form-control" id="title"/>
                <form:errors path="name" cssClass="error"/>
            </div>
        </div>

        <div class="form-group">
            <label for="title" class="col-lg-3 control-label"><spring:message code="product.add.description"/></label>
            <div class="col-lg-3">
                <form:textarea  path="description" cssClass=" form-control" id="description"/>
                <form:errors path="description" cssClass="error"/>
            </div>
        </div>

        <div class="form-group">
            <label for="title" class="col-lg-3 control-label"><spring:message code="product.add.picture"/></label>
            <div class="col-lg-3">
                <form:input path="file" type="file" id="file"/>
                <form:errors path="file" cssClass="error"/>
            </div>
        </div>
        <div class="form-group">
            <label for="title" class="col-lg-3 control-label"><spring:message code="product.add.category"/></label>
            <div class="col-lg-3">
                <form:select path="category" id="category">
                    <form:options items="${categoryList}" />
                </form:select>
            </div>
        </div>
        <div class="form-group">
            <label for="title" class="col-lg-3 control-label"><spring:message code="product.add.price"/></label>
            <div class="col-lg-3">
                <form:input type="text"  path="price" cssClass=" form-control" id="price"/>
                <form:errors path="price" cssClass="error"/>
            </div>
        </div>

        <div class="form-group">
            <div class="col-lg-3">
                <spring:message code="product.add.add" var="add"/>
                <input type="submit" class="btn btn-primary" value="${add}">
            </div>
        </div>
    </form:form>
</div><!-- End row -->

<jsp:include page="footer.jsp"/>

</body>

</html>