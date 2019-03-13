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
    <!-- TODO translate -->
    <h2>
        <spring:message code="home.welcome"/>
    </h2>
    <sec:authorize access="isAuthenticated()">
        <sec:authentication property="principal.username" var="username" />
        Vous êtes connecté en tant que : ${username}
    </sec:authorize>
    <br/>
    <h3>Découvrez nos ventes par catégorie</h3>
    <div>
        <a class="nav-link" style="display: inline-block" href="<c:url value="/product/products"/>">
            <div class="categoriesPicture">
                <img src="<c:url value="/resources/img/all.jpg"/>" width="240px" height="240px" />
                Tout
            </div>
        </a>
        <a class="nav-link" style="display: inline-block" href="<c:url value="/product/products?category=INFORMATIQUE"/>">
            <div class="categoriesPicture">
                <img src="<c:url value="/resources/img/computer.jpg"/>"/>
                Informatique
            </div>
        </a>
        <a class="nav-link" style="display: inline-block" href="<c:url value="/product/products?category=AUTO_MOTO"/>">
            <div class="categoriesPicture">
                <img src="<c:url value="/resources/img/car.jpg"/>"/>
                Auto/moto
            </div>
        </a>
        <a class="nav-link" style="display: inline-block" href="<c:url value="/product/products?category=PHOTO_VIDEO"/>">
            <div class="categoriesPicture">
                <img src="<c:url value="/resources/img/camera.jpg"/>"/>
                Photo/vidéo
            </div>
        </a>
        <a class="nav-link" style="display: inline-block" href="<c:url value="/product/products?category=MODE"/>">
            <div class="categoriesPicture">
                <img src="<c:url value="/resources/img/fashion.jpg"/>"/>
                Mode
            </div>
        </a>
        <a class="nav-link" style="display: inline-block" href="<c:url value="/product/products?category=BIJOUX"/>">
            <div class="categoriesPicture">
                <img src="<c:url value="/resources/img/jewelry.jpg"/>"/>
                Bijoux
            </div>
        </a>
        <a class="nav-link" style="display: inline-block" href="<c:url value="/product/products?category=MUSIQUE"/>">
            <div class="categoriesPicture">
                <img src="<c:url value="/resources/img/audio.jpg"/>"/>
                Musique
            </div>
        </a>
        <a class="nav-link" style="display: inline-block" href="<c:url value="/product/products?category=JEUX_JOUET"/>">
            <div class="categoriesPicture">
                <img src="<c:url value="/resources/img/toys.jpg"/>"/>
                Jeux/jouet
            </div>
        </a>
        <a class="nav-link" style="display: inline-block" href="<c:url value="/product/products?category=BOOK"/>">
            <div class="categoriesPicture">
                <img src="<c:url value="/resources/img/books.jpg"/>"/>
                Livres
            </div>
        </a>
        <a class="nav-link" style="display: inline-block" href="<c:url value="/product/products?category=ANIMALERIE"/>">
            <div class="categoriesPicture">
                <img src="<c:url value="/resources/img/animals.jpg"/>"/>
                Animaux
            </div>
        </a>
        <a class="nav-link" style="display: inline-block" href="<c:url value="/product/products?category=BRICOLAGE"/>">
            <div class="categoriesPicture">
                <img src="<c:url value="/resources/img/crafts.jpg"/>"/>
                Bricolage
            </div>
        </a>
        <a class="nav-link" style="display: inline-block" href="<c:url value="/product/products?category=AUTRES"/>">
            <div class="categoriesPicture">
                <img src="<c:url value="/resources/img/other.jpg"/>"/>
                Autres
            </div>
        </a>
    </div>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>