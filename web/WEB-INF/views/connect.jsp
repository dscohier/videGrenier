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

<form:form cssClass="form-horizontal" method="post" action="connect/login" commandName="connectForm" >
	<fieldset>
		<h2><spring:message code="connect.login"/></h2>
		<div class="form-group">
			<label for="userName" class="col-lg-1 control-label"><spring:message code="connect.userName"/></label>
			<div class="col-lg-3">
				<form:input type="text" path="userName" cssClass=" form-control" id="username"/>
				<form:errors path="userName"/>
			</div>
		</div>
		<div class="form-group">
			<label for="password" class="col-lg-1 control-label"><spring:message code="connect.password"/></label>
			<div class="col-lg-3">
					<form:input type="password" path="password" cssClass="form-control" id="password"/>
					<form:errors path="password"/>
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
				<button class="btn_full">Create an account</button>
			</div>
		</div>
	</div>
		</fieldset>
</form:form>

</font>
<jsp:include page="footer.jsp"/>
</body>
</html>