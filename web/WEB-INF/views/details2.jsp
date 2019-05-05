<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
    <jsp:include page="head.jsp"/>
    <body>
        <div id="page">
            <jsp:include page="menu2.jsp"/>

            <div id="fh5co-product">
                <div class="container">
                    <div class="row">
                        <div class="row animate-box">
                            <div class="col-md-8 col-md-offset-2 text-center fh5co-heading">
                                <h2>${product.name}</h2>
                            </div>
                        </div>
                        <div class="col-md-10 col-md-offset-1 animate-box">
                            <div class="owl-carousel owl-carousel-fullwidth product-carousel">
                                <div class="item">
                                    <div class="active text-center">
                                        <figure>
                                            <img src="data:image/jpg;base64,${product.displayPicture()}">
                                        </figure>
                                    </div>
                                </div>
                                <div class="item">
                                    <div class="active text-center">
                                        <figure>
                                            <img src="images/product-single-2.jpg" alt="user">
                                        </figure>
                                    </div>
                                </div>
                            </div>
                            <div class="row animate-box">
                                <div class="col-md-8 col-md-offset-2 text-center fh5co-heading">
                                    <p>
                                        <sec:authorize access="isAuthenticated()">
                                            <sec:authentication property="principal.username" var="username" />
                                            <c:if test = "${!product.seller.username.equals(username)}">
                                                <button class="btn btn-primary btn-outline btn-lg" type="submit" disabled><spring:message code="product.details.sendMessage"/></button>
                                            </c:if>
                                        </sec:authorize>
                                        <sec:authorize access="!isAuthenticated()">
                                            <spring:message code="error.details.sendMessage" var="message"/>
                                            <button class="btn btn-primary btn-outline btn-lg" type="submit" disabled data-toggle="tooltip" data-placement="top" title="${message}"><spring:message code="product.details.sendMessage"/></button>
                                        </sec:authorize>
                                        <a href="#" class="btn btn-primary btn-outline btn-lg">Compare</a>
                                    </p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-10 col-md-offset-1">
                            <div class="fh5co-tabs animate-box">
                                <ul class="fh5co-tab-nav">
                                    <li class="active" style="width: 50%"><a href="#" data-tab="1"><span
                                            class="icon visible-xs"><i class="icon-file"></i></span><span
                                            class="hidden-xs"><spring:message
                                            code="product.details.information"/></span></a></li>
                                    <li style="width: 50%"><a href="#" data-tab="2"><span class="icon visible-xs"><i
                                            class="icon-bar-graph"></i></span><span class="hidden-xs"><spring:message
                                            code="product.details.details"/></span></a></li>
                                </ul>

                                <!-- Tabs -->
                                <div class="fh5co-tab-content-wrap">

                                    <div class="fh5co-tab-content tab-content active" data-tab-content="1">
                                        <div class="col-md-10 col-md-offset-1">
                                            <a href="<c:url value="/profile?username=${product.seller.username}"/>">
                                                <div class="fh5co-staff">
                                                    <img src="data:image/jpg;base64,${product.seller.displayPicture()}"
                                                         alt="<c:url value="/resources/img/userProfile.png"/>">
                                                    <h3>${product.seller.username}</h3>

                                                    <span class="rate">
                                                    <c:forEach begin="1" end="${product.seller.averageRatingSeller}">
                                                        <i class="icon-star2" style="color:yellow"></i>
                                                    </c:forEach>
                                                    <c:forEach begin="${product.seller.averageRatingSeller + 1}" end="5">
                                                        <i class="icon-star"></i>
                                                    </c:forEach>
                                                    <p><spring:message code="profile.opinion"/> : ${product.seller.commentByBuyer.size()} </p>
                                                </span>
                                                </div>
                                            </a>
                                            <c:if test="${product.auction}">
                                                <span class="price"><spring:message code="product.details.actualPrice"/> : ${product.price}€ </span>
                                                <c:if test="${lastBidder ne null}">
                                                    <spring:message code="product.details.priceOfferedBy"/> : ${lastBidder.user.username} <spring:message code="product.details.on"/> ${lastBidder.insertionDate}
                                                </c:if>
                                            </c:if>
                                            <c:if test="${!product.auction}">
                                                <span class="price"><spring:message code="common.price"/> : ${product.price}€ </span>
                                            </c:if>
                                            <c:if test="${product.auction}">
                                                <p class="card-text"><spring:message code="common.typeOfSale"/> : <spring:message code="common.bid"/></p>
                                            </c:if>
                                            <c:if test="${!product.auction}">
                                                <p class="card-text"><spring:message code="common.typeOfSale"/> : <spring:message code="common.directSale"/>/></p>
                                            </c:if>
                                            <p class="card-text"><spring:message code="common.city"/> : ${product.seller.city.name}</p>
                                            <sec:authorize access="isAuthenticated()">
                                                <sec:authentication property="principal.username" var="username" />
                                                <c:if test = "${!product.seller.username.equals(username)}">
                                                    <p class="card-text"><spring:message code="product.details.distance"/> : <label id="output"></label></p></c:if>
                                            </sec:authorize>
                                            <p class="card-text"><spring:message code="product.details.creationDate"/> : <fmt:formatDate pattern="dd-MM-yyyy HH:mm" value="${product.creationDate}"/></p>
                                            <c:if test="${product.auction}">
                                                <p class="card-text"><spring:message code="product.details.endDate"/> : <fmt:formatDate pattern="dd-MM-yyyy HH:mm" value="${product.endDate}"/></p>
                                            </c:if>
                                        </div>
                                    </div>

                                    <div class="fh5co-tab-content tab-content" data-tab-content="2">
                                        <div class="col-md-10 col-md-offset-1">
                                            ${product.description}

                                                '' + ${product.seller.city.name} + ', ' + ${product.seller.city.country}
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script>
            function initMap() {
                var markersArray = [];
                <c:set value="${product.seller.city.name}" var="citySeller"></c:set>
                <c:set value="${product.seller.city.country}" var="countrySeller"></c:set>
                <c:set value="${user.city.name}" var="cityBuyer"></c:set>
                <c:set value="${user.city.country}" var="countryBuyer"></c:set>
                //var origin2 = '${cityBuyer}' + ', ' + '${countryBuyer}';
                var origin2 = 'Soignies, Belgique';
                var destinationA = '${citySeller}' + ', ' + '${countrySeller}';

                var service = new google.maps.DistanceMatrixService;
                service.getDistanceMatrix({
                    origins: [origin2],
                    destinations: [destinationA],
                    travelMode: 'DRIVING',
                    unitSystem: google.maps.UnitSystem.METRIC,
                    avoidHighways: false,
                    avoidTolls: false
                }, function(response, status) {
                    if (status !== 'OK') {
                        alert('Error was: ' + status);
                    } else {
                        var originList = response.originAddresses;
                        var destinationList = response.destinationAddresses;
                        var outputDiv = document.getElementById('output');
                        outputDiv.innerHTML = '';


                        for (var i = 0; i < originList.length; i++) {
                            var results = response.rows[i].elements;
                            for (var j = 0; j < results.length; j++) {
                                outputDiv.innerHTML += originList[i] + ' to ' + destinationList[j] +
                                    ': ' + results[j].distance.text + ' in ' +
                                    results[j].duration.text + '<br>';
                            }
                        }
                    }
                });
            }


        </script>
        <script async defer
                src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCBaebZ5i6230uvRvSiAqecuRv_pEqnXcA&callback=initMap&language=fr">
        </script>
        <jsp:include page="footer2.jsp"/>
    </body>
</html>
