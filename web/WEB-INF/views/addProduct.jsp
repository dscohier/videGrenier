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
<body>
<!-- FIXME translate -->
<jsp:include page="menu.jsp"/>
<div>
    <c:if test="${addProductForm.id == null}">
        <c:set var="addOrUpdate" value="add" />
    </c:if>
    <c:if test="${addProductForm.id != null}">
        <c:set var="addOrUpdate" value="update" />
    </c:if>
    <form:form cssClass="form-horizontal" method="post" action="${addOrUpdate}" enctype="multipart/form-data" commandName="addProductForm" >
         <c:if test="${not empty error}">
             <label class="error"><spring:message code="${error}"/></label>
         </c:if>
        <div style="visibility: hidden;">
            <form:input type="text" path="id" cssClass="form-control" id="title"/>
        </div>
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
        <div class="custom-control custom-radio">
            <form:radiobutton value="fixPrice" checked="checked" path="auctionOrFixPrice" onclick="hideAuctionDiv()"/>
            <label class="col-lg-1 control-label" for="fixePrice">Prix fixe</label>
            <form:radiobutton value="auction" path="auctionOrFixPrice" onclick="hideFixPriceDiv()"/>
            <label class="col-lg-1 control-label" for="auction">Enchère</label>
        </div>
        <div class="form-group" id="fixePrice">
            <label for="title" class="col-lg-3 control-label"><spring:message code="product.add.price"/></label>
            <div class="col-lg-3">
                <form:input type="text" path="price" cssClass="form-control" id="price"/>
                <form:errors path="price" cssClass="error"/>
            </div>
        </div>

        <div class="form-group" id="auction">
            <label for="price" class="col-lg-3 control-label">Prix de base</label>
            <div class="col-lg-3">
                <form:input type="text" path="price" cssClass="form-control" id="price"/>
                <form:errors path="price" cssClass="error"/>
            </div>
            <label for="endDate" class="col-lg-3 control-label">Date de fin</label>
            <div class="col-lg-3">
                <div class="dates">
                    <div class="start_date input-group mb-4">
                        <form:input path="endDate" class="form-control end_date" type="text" placeholder="dd/mm/yyyy" id="endDate"/>
                        <div class="input-group-append">
                            <span class="fa fa-calendar input-group-text end_date_calendar" aria-hidden="true"></span>
                        </div>
                    </div>
            </div>
            <form:errors path="isEndDateCorrect" cssClass="error"/>
        </div>

        <div class="form-group">
            <div class="col-lg-3">
                <c:if test="${addProductForm.id == null}">
                    <spring:message code="product.add.add" var="message"/>
                </c:if>
                <c:if test="${addProductForm.id != null}">
                    <spring:message code="product.add.update" var="message"/>
                </c:if>
                <input type="submit" class="btn btn-primary" value="${message}">
            </div>
        </div>
    </form:form>
</div><!-- End row -->

<jsp:include page="footer.jsp"/>
<script>
    function hideAuctionDiv(){
        $("#fixePrice").show();
        $("#auction").hide();
    }

    function hideFixPriceDiv(){
        $("#fixePrice").hide();
        $("#auction").show();
    }
    $("#endDate").datepicker();
</script>

<c:if test="${addProductForm.auctionOrFixPrice ne 'auction'}">
    <script>
        $("#fixePrice").show();
        $("#auction").hide();
    </script>
</c:if>
<c:if test="${addProductForm.auctionOrFixPrice eq 'auction'}">
        <script>
            $("#fixePrice").hide();
            $("#auction").show();
        </script>
</c:if>
</body>
</html>