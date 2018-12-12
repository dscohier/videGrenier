<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
	<jsp:include page="menu.jsp"/>
</head>
</head>
<body>

<form:form cssClass="form-horizontal" method="post" action="connect/login" commandName="loginForm" >
	<fieldset>
        <c:if test="${not empty message}">
            <label class="error"><spring:message code="${message}"/></label>
        </c:if>
        <h2><spring:message code="connect.login" text="test"/></h2>
		<div class="form-group">
			<label for="userName" class="col-lg-3 control-label"><spring:message code="connect.userName"/></label>
			<div class="col-lg-3">
				<form:input type="text" path="userName" cssClass=" form-control" id="username"/>
				<form:errors path="userName" cssClass="error"/>
			</div>
		</div>
		<div class="form-group">
			<label for="password" class="col-lg-3 control-label"><spring:message code="connect.password"/></label>
			<div class="col-lg-3">
					<form:input type="password" path="password" cssClass="form-control" id="password"/>
					<form:errors path="password" cssClass="error"/>
				</div>
			</div>
			<br>
			<div class="col-lg-3">
				<p class="small">
					<a href="#"><spring:message code="connect.forgotPassword"/></a>
				</p>
			</div>
		<div class="form-group">
			<div class="col-lg-3">
				<spring:message code="connect.login" var="login"/>
				<input type="submit" class="btn btn-primary" value="${login}">
			</div>
		</div>
	</div>
		</fieldset>
</form:form>

<form:form cssClass="form-horizontal" method="post" action="connect/signup" commandName="signupForm" >
	<fieldset>
		<h2><spring:message code="connect.signup"/></h2>
		<div class="form-group">
			<label for="firstName" class="col-lg-3 control-label"><spring:message code="connect.firstName"/></label>
			<div class="col-lg-3">
				<form:input type="text" path="firstName" cssClass=" form-control" id="firstName"/>
				<form:errors path="firstName" cssClass="error"/>
			</div>
		</div>
		<div class="form-group">
			<label for="lastName" class="col-lg-3 control-label"><spring:message code="connect.lastName"/></label>
			<div class="col-lg-3">
				<form:input type="text" path="lastName" cssClass=" form-control" id="lastName"/>
				<form:errors path="lastName" cssClass="error"/>
			</div>
		</div>
		<div class="form-group">
			<label for="email" class="col-lg-3 control-label"><spring:message code="connect.mail"/></label>
			<div class="col-lg-3">
				<form:input type="text" path="email" cssClass=" form-control" id="email"/>
				<form:errors path="email" cssClass="error"/>
			</div>
		</div>
		<div class="form-group">
			<label for="city" class="col-lg-3 control-label"><spring:message code="connect.city"/></label>
			<div class="col-lg-3">
				<form:input type="text" path="city" cssClass=" form-control" id="city"/>
				<form:errors path="city" cssClass="error"/>
			</div>
		</div>
		<div class="form-group">
			<label for="userName" class="col-lg-3 control-label"><spring:message code="connect.userName"/></label>
			<div class="col-lg-3">
				<form:input type="text" path="userName" cssClass=" form-control" id="username"/>
				<form:errors path="userName" cssClass="error"/>
			</div>
		</div>
		<div class="form-group">
			<label for="password" class="col-lg-3 control-label"><spring:message code="connect.password"/></label>
			<div class="col-lg-3">
				<form:input type="password" path="password" cssClass="form-control" id="password"/>
				<form:errors path="password" cssClass="error"/>
			</div>
		</div>
		<div class="form-group">
			<label for="passwordCheck" class="col-lg-3 control-label"><spring:message code="connect.passwordCheck"/></label>
			<div class="col-lg-3">
			<form:input type="password" path="passwordCheck" cssClass=" form-control" id="passwordCheck"/>
			<form:errors path="passwordCheck" cssClass="error"/>
			<br/>
			<form:errors path="isPasswordMatch" cssClass="error"/>
			</div>
        <br/>
		<div class="form-group">
				<spring:message code="connect.signup" var="signup"/>
				<input type="submit" class="btn btn-primary" value="${signup}">
		</div>
		</div>
	</fieldset>
</form:form>

</font>
<jsp:include page="footer.jsp"/>
</body>
</html>