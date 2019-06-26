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
            <jsp:include page="menu.jsp"/>
            <c:if test="${signupForm.id == null}">
                <div class="col-lg-6">
                    <form:form cssClass="form-horizontal" method="post" action="/VideGrenier/connect/login" commandName="loginForm">
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
                                <div class="col-lg-8">
                                    <form:input type="text" path="userName" cssClass=" form-control" id="username"/>
                                    <form:errors path="userName" cssClass="error"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="pwd" class="col-lg-2 control-label"><spring:message
                                        code="connect.password"/></label>
                                <div class="col-lg-8">
                                    <form:input type="password" path="password" cssClass="form-control" id="pwd"/>
                                    <form:errors path="password" cssClass="error"/>
                                    <p class="small">
                                        <a href="#"><spring:message code="connect.forgotPassword"/></a>
                                    </p>
                                </div>
                            </div>
                            <div class="col-md-offset-5">
                                <spring:message code="connect.login" var="login"/>
                                <input type="submit" class="btn btn-primary" value="${login}">
                            </div>
                        </fieldset>
                    </form:form>
                </div>
            </c:if>
            <c:if test="${signupForm.id == null}">
                <c:set var="signupOrUpdate" value="/VideGrenier//connect/signup"/>
            </c:if>
            <c:if test="${signupForm.id != null}">
                <c:set var="signupOrUpdate" value="/VideGrenier//connect/update"/>
            </c:if>
            <c:if test="${empty success}">
                <div class="col-lg-6">
                    <form:form cssClass="form-horizontal" method="post" enctype="multipart/form-data"
                               action="${signupOrUpdate}" commandName="signupForm" autocomplete="off">
                        <fieldset>
                            <c:if test="${not empty errorSignup}">
                                <label class="error"><spring:message code="${errorSignup}"/></label>
                            </c:if>
                            <div style="visibility: hidden;">
                                <form:input type="text" path="id" cssClass="form-control" id="title"/>
                            </div>
                            <h2><spring:message code="connect.signup"/></h2>
                            <div class="form-group">
                                <label for="firstName" class="col-lg-2 control-label"><spring:message
                                        code="connect.firstName"/></label>
                                <div class="col-lg-8">
                                    <form:input type="text" path="firstName" cssClass=" form-control" id="firstName"/>
                                    <form:errors path="firstName" cssClass="error"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="lastName" class="col-lg-2 control-label"><spring:message
                                        code="connect.lastName"/></label>
                                <div class="col-lg-8">
                                    <form:input type="text" path="lastName" cssClass=" form-control" id="lastName"/>
                                    <form:errors path="lastName" cssClass="error"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="file" class="col-lg-2 control-label"><spring:message
                                        code="common.picture"/></label>
                                <div class="col-lg-7">
                                    <form:input path="file" type="file" id="file"/>
                                    <form:errors path="file" cssClass="error"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="email" class="col-lg-2 control-label"><spring:message
                                        code="connect.mail"/></label>
                                <div class="col-lg-8">
                                    <form:input type="text" path="email" cssClass=" form-control" id="email"/>
                                    <form:errors path="email" cssClass="error"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="city" class="col-lg-2 control-label"><spring:message
                                        code="common.city"/></label>
                                <div class="col-lg-8">
                                    <input type="text" id="autocomplete" name="city" value="${signupForm.city}"/>
                                    <br/>
                                    <form:errors path="country" cssClass="error"/>
                                </div>
                                <div style="visibility: hidden;">
                                    <table id="address">
                                        <td><form:input type="text" id="locality" name="city" path="city"></form:input></td>
                                        <td><form:input type="text" path="country" name="country"
                                                        id="country"></form:input></td>
                                    </table>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="userName" class="col-lg-2 control-label"><spring:message
                                        code="connect.userName"/></label>
                                <div class="col-lg-8">
                                    <c:if test="${signupForm.id == null}">
                                        <form:input type="text" path="userName" cssClass=" form-control" id="username"/>
                                        <form:errors path="userName" cssClass="error"/>
                                    </c:if>
                                    <c:if test="${signupForm.id != null}">
                                        <form:input type="text" path="userName" cssClass=" form-control" id="username"
                                                    readonly="true"/>
                                    </c:if>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="pwd" class="col-lg-2 control-label"><spring:message
                                        code="connect.password"/></label>
                                <div class="col-lg-8">
                                    <form:input type="password" path="password" cssClass="form-control" id="pwd"/>
                                    <form:errors path="password" cssClass="error"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="pwdCheck" class="col-lg-2 control-label"><spring:message
                                        code="connect.passwordCheck"/></label>
                                <div class="col-lg-8">
                                    <form:input type="password" path="passwordCheck" cssClass=" form-control"
                                                id="pwdCheck"/>
                                    <form:errors path="passwordCheck" cssClass="error"/>
                                    <br/>
                                    <form:errors path="isPasswordMatch" cssClass="error"/>
                                </div>
                            </div>
                            <div class="col-md-offset-5">
                                <c:if test="${signupForm.id == null}">
                                    <spring:message code="connect.signup" var="signup"/>
                                    <input type="submit" class="btn btn-primary" value="${signup}">
                                </c:if>
                                <c:if test="${signupForm.id != null}">
                                    <spring:message code="common.update" var="update"/>
                                    <input type="submit" class="btn btn-primary" value="${update}">
                                </c:if>
                            </div>
                        </fieldset>
                        <script>
                            var autocomplete;
                            var componentForm = {
                                locality: 'long_name',
                                country: 'long_name'
                            };

                            function initSearch() {
                                autocomplete = new google.maps.places.Autocomplete((
                                    document.getElementById('autocomplete')), {
                                    types: ['(cities)']
                                });
                                autocomplete.addListener('place_changed', fillInAddress);
                            }

                            function fillInAddress() {
                                // Get the place details from the autocomplete object.
                                var place = autocomplete.getPlace();
                                for (var i = 0; i < place.address_components.length; i++) {
                                    var addressType = place.address_components[i].types[0];
                                    if (componentForm[addressType]) {
                                        var val = place.address_components[i][componentForm[addressType]];
                                        document.getElementById(addressType).value = val;
                                    }
                                }

                            }
                        </script>
                        <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCBaebZ5i6230uvRvSiAqecuRv_pEqnXcA&signed_in=true&libraries=places&callback=initSearch&language=fr"
                                async defer></script>
                    </form:form>
                </div>
            </c:if>
            <jsp:include page="footer2.jsp"/>
        </div>
    </body>
</html>