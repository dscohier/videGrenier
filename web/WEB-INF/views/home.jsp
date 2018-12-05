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
    <h2>
        <spring:message code="home.welcome" text="default text" />
    </h2>
    <sec:authorize access="isAuthenticated()">
        <sec:authentication property="principal.username" var="username" />
        Vous êtes connecté en tant que : ${username}
    </sec:authorize>

    Language : <a href="?language=en">English</a>|<a href="?language=fr">French</a>

</div><!-- End row -->

<jsp:include page="footer.jsp"/>

</body>

</html>