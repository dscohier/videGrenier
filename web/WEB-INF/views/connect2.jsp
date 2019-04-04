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
            <div class="col-lg-6">
                <form:form cssClass="form-horizontal" method="post" action="connect/login" commandName="loginForm">
                    <fieldset>
                        <c:if test="${not empty error}">
                            <label class="error"><spring:message code="${error}"/></label>
                        </c:if>
                        <c:if test="${not empty success}">
                            <label class="success"><spring:message code="${success}"/></label>
                        </c:if>
                        <h2><spring:message code="connect.login" text="test"/></h2>
                        <div class="form-group">
                            <label for="userName" class="col-lg-2 control-label"><spring:message
                                    code="connect.userName"/></label>
                            <div class="col-lg-4">
                                <form:input type="text" path="userName" cssClass=" form-control" id="username"/>
                                <form:errors path="userName" cssClass="error"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="password" class="col-lg-2 control-label"><spring:message
                                    code="connect.password"/></label>
                            <div class="col-lg-4">
                                <form:input type="password" path="password" cssClass="form-control" id="password"/>
                                <form:errors path="password" cssClass="error"/>
                                <p class="small">
                                    <a href="#"><spring:message code="connect.forgotPassword"/></a>
                                </p>
                            </div>
                        </div>
                        <br>
                        <div class="col-md-offset-3">
                                <spring:message code="connect.login" var="login"/>
                                <input type="submit" class="btn btn-primary" value="${login}">
                        </div>
                    </fieldset>
                </form:form>
            </div>
        </div>
        <jsp:include page="footer2.jsp"/>
    </body>
</html>