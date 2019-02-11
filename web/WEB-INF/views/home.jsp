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
        <spring:message code="home.welcome"/>
    </h2>
    <sec:authorize access="isAuthenticated()">
        <sec:authentication property="principal.username" var="username" />
        Vous êtes connecté en tant que : ${username}
    </sec:authorize>
</div><!-- End row -->
<div class="dates">
    <div class="start_date input-group mb-4">
        <input class="form-control start_date" type="text" placeholder="start date" id="startdate_datepicker">
        <div class="input-group-append">
            <span class="fa fa-calendar input-group-text start_date_calendar" aria-hidden="true "></span>
        </div>
    </div>
    <div class="end_date input-group mb-4">
        <input class="form-control end_date" type="text" placeholder="end date" id="enddate_datepicker">
        <div class="input-group-append">
            <span class="fa fa-calendar input-group-text end_date_calendar" aria-hidden="true "></span>
        </div>
    </div>
</div>

<script>
    $("#startdate_datepicker").datepicker();
    $("#enddate_datepicker").datepicker();
</script>
<jsp:include page="footer.jsp"/>

</body>

</html>