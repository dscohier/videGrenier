<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%--
  Created by IntelliJ IDEA.
  User: Student
  Date: 03-02-16
  Time: 13:29
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html>
<head>
    <title>VIDE GRENIER</title>
</head>
<body>


<jsp:include page="menu.jsp"/>
<div>
    <h1 class="text-center"><spring:message code="home.welcome" var="signup"/></h1>
    test
    <spring:message code="home.welcome"/>
    <sec:authorize access="isAuthenticated()">
        <sec:authentication property="principal.username" var="username" />
        Vous êtes connecté en tant que : ${username}
    </sec:authorize>

    <h1>Spring MVC internationalization example</h1>

    Language : <a href="?language=en">English</a>|<a href="?language=zh_CN">Chinese</a>

    <h2>
        welcome.springmvc : <spring:message code="welcome.springmvc" text="default text" />
    </h2>

    Current Locale : ${pageContext.response.locale}
</div><!-- End row -->

<jsp:include page="footer.jsp"/>

</body>

</html>