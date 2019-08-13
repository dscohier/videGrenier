<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<nav class="fh5co-nav" role="navigation">
    <div class="container">
        <div class="row">
            <div class="col-lg-3">
                <div id="fh5co-logo"><a href="<c:url value="/"/>">Vide Grenier</a></div>
            </div>
            <div class="col-lg-5 text-center menu-1">
                <ul>
                    <li class="has-dropdown">
                        <a href="<c:url value="/product/products"/>"><spring:message code="menu.deals"/></a>
                        <ul class="dropdown">
                            <li><a href="<c:url value="/product/products"/>"><spring:message code="menu.allDeals"/></a></li>
                        </ul>
                    </li>
                    <li><a href="<c:url value="/product/newProduct"/>"><spring:message code="menu.add"/></a></li>
                    <li>
                        <a href="<c:url value="/product/mySales"/>"><spring:message code="common.mySales"/></a>
                    </li>
                    <li>
                        <a href="<c:url value="/product/myPurchases"/>"><spring:message code="common.myPurchases"/></a>
                    </li>
                </ul>
            </div>
            <div class="col-lg-4 hidden-xs menu-2">
                <ul>
                    <li class="search">
                        <c:url var="action"  value="/product/title" />
                        <form:form cssClass="form-horizontal" method="get" action="${action}">
                            <div class="input-group">
                                <input type="text" id="title" name="title" placeholder="<spring:message code="common.search"/>"/>
                                <span class="input-group-btn">
                                    <button class="btn btn-primary" type="submit"><i class="icon-search"></i></button>
                                </span>
                            </div>
                        </form:form>
                    </li>
                    <sec:authorize access="isAuthenticated()">
                        <sec:authentication property="principal.panier.productsSize" var="countProduct" />
                        <li class="shopping-cart"><a href="<c:url value="/product/cart"/>" class="cart"><span><small>${countProduct}</small><i class="icon-shopping-cart"></i></span></a></li>
                    </sec:authorize>
                    <sec:authorize access="isAuthenticated()">
                        <sec:authentication property="principal.username" var="username" />
                        <li class="has-dropdown">
                            <a href="<c:url value="/profile?username=${username}"/>"><spring:message code="menu.profile"/></a>
                            <ul class="dropdown">
                                <li><a href="<c:url value="/profile?username=${username}"/>"><spring:message code="menu.myProfile"/></a></li>
                                <li><a href="<c:url value="/connect/modifyProfil"/>"><spring:message code="menu.modify"/></a></li>
                                <li><a href="<c:url value="/logout"/>"><spring:message code="menu.logout"/></a></li>
                            </ul>
                        </li>
                    </sec:authorize>
                    <sec:authorize access="isAnonymous()">
                        <li><a href="<c:url value="/connect"/>"><spring:message code="menu.login"/></a></li>
                    </sec:authorize>
                    <li><p><a href="?language=en">EN</a>/<a href="?language=fr">FR</a></li>
                </ul>
            </div>
        </div>
    </div>
</nav>
</html>
