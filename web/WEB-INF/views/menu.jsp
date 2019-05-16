<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <!-- Latest compiled and minified CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <!-- Optional theme -->
    <link href="https://stackpath.bootstrapcdn.com/bootswatch/4.1.3/minty/bootstrap.min.css" rel="stylesheet" integrity="sha384-w+yWASP3zYNxxvwoQBD5fUSc1tctKq4KUiZzxgkBSJACiUp+IbweVKvsEhMI+gz7" crossorigin="anonymous">
    <!-- datepicker -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css" integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css" />

    <!-- Include Bootstrap Datepicker -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.8.0/css/bootstrap-datepicker.min.css" />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.8.0/js/bootstrap-datepicker.min.js"></script>

    <!-- my css -->
    <link href="<c:url value="/resources/css/css.css"/>" rel="stylesheet">
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
    <a class="navbar-brand" href="<c:url value="/"/>">VIDE GRENIER</a>
    <button class="navbar-toggler" aria-expanded="false" aria-controls="navbarColor01" aria-label="Toggle navigation" type="button" data-target="#navbarColor01" data-toggle="collapse">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarColor01">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="<c:url value="/"/>"><spring:message code="menu.home"/><span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<c:url value="/product/newProduct"/>"><spring:message code="menu.add"/></a>
            </li>
            <sec:authorize access="isAuthenticated()">
                <sec:authentication property="principal.username" var="username" />
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/product/mySales"/>"><spring:message code="common.mySales"/></a>
                </li>
            </sec:authorize>
            <li class="nav-item">
                <a class="nav-link" href="#">About</a>
            </li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <sec:authorize access="isAuthenticated()">
                <li class="nav-item"><a class="nav-link" href="<c:url value="/logout"/>"><spring:message code="menu.logout"/></a></li>
            </sec:authorize>
            <sec:authorize access="isAnonymous()">
                <li class="nav-item"><a class="nav-link" href="<c:url value="/connect"/>"><spring:message code="menu.login"/></a></li>
            </sec:authorize>
        </ul>
    </div>
    <a href="?language=en" style="color:white">EN</a>/<a href="?language=fr" style="color:white">FR</a>
</nav>
</body>
</html>
