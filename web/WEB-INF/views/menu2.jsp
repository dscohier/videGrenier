<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<nav class="fh5co-nav" role="navigation">
    <div class="container">
        <div class="row">
            <div class="col-lg-3">
                <div id="fh5co-logo"><a href="index.jsp">Shop.</a></div>
            </div>
            <div class="col-lg-6 text-center menu-1">
                <ul>
                    <li class="has-dropdown">
                        <a href="product.html">Shop</a>
                        <ul class="dropdown">
                            <li><a href="single.html">Single Shop</a></li>
                        </ul>
                    </li>
                    <li><a href="<c:url value="/product/newProduct"/>"><spring:message code="menu.add"/></a></li>
                    <li class="has-dropdown">
                        <a href="services.html">Services</a>
                        <ul class="dropdown">
                            <li><a href="#">Web Design</a></li>
                            <li><a href="#">eCommerce</a></li>
                            <li><a href="#">Branding</a></li>
                            <li><a href="#">API</a></li>
                        </ul>
                    </li>
                    <li><a href="contact.html">Contact</a></li>
                </ul>
            </div>
            <div class="col-lg-3 hidden-xs menu-2">
                <ul>
                    <li class="search">
                        <div class="input-group">
                            <input type="text" placeholder="Search..">
                            <span class="input-group-btn">
						        <button class="btn btn-primary" type="button"><i class="icon-search"></i></button>
						      </span>
                        </div>
                    </li>
                    <sec:authorize access="isAuthenticated()">
                        <sec:authentication property="principal.panier.productsSize" var="countProduct" />
                        <li class="shopping-cart"><a href="#" class="cart"><span><small>${countProduct}</small><i class="icon-shopping-cart"></i></span></a></li>
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
                </ul>
            </div>
        </div>
    </div>
</nav>
</html>
