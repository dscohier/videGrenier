<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE HTML>
<html>
    <jsp:include page="head.jsp"/>
    <body>
        <div id="page">
            <jsp:include page="menu.jsp"/>
            <div class="fh5co-staff">
                <img src="data:image/jpg;base64,${user.displayPicture()}" alt="<c:url value="/resources/img/userProfile.png"/>">
                <h3>${user.username}</h3>
                <strong class="role">${user.city.name}</strong>
                <div class="col-lg-3">
                    <p><spring:message code="profile.overallSellerRating"/> : </p>
                </div>
                <div class="col-lg-3">
                    <span class="rate">
                        <c:forEach begin="1" end="${user.averageRatingSeller}">
                            <i class="icon-star2" style="color:yellow"></i>
                        </c:forEach>
                        <c:forEach begin="${user.averageRatingSeller + 1}" end="5">
                            <i class="icon-star"></i>
                        </c:forEach>
                        <p><spring:message code="profile.opinion"/> : ${user.commentByBuyer.size()} </p>
                    </span>
                </div>
                <div class="col-lg-3">
                    <p><spring:message code="profile.overallBuyerRating"/> : </p>
                </div>
                <div class="col-lg-3">
                    <span class="rate">
                        <c:forEach begin="1" end="${user.averageRatingBuyer}">
                            <i class="icon-star2" style="color:yellow"></i>
                        </c:forEach>
                        <c:forEach begin="${user.averageRatingBuyer + 1}" end="5">
                            <i class="icon-star"></i>
                        </c:forEach>
                        <p><spring:message code="profile.opinion"/> : ${user.commentBySeller.size()} </p>
                    </span>
                </div>
            </div>
            <div class="row">
                <div class="col-md-10 col-md-offset-1">
                    <div class="fh5co-tabs animate-box">
                        <ul class="fh5co-tab-nav">
                            <li class="active"><a href="#" data-tab="1"><span class="icon visible-xs"><i class="icon-file"></i></span><span class="hidden-xs"><spring:message code="profile.informations"/></span></a></li>
                            <li><a href="#" data-tab="2"><span class="icon visible-xs"><i class="icon-bar-graph"></i></span><span class="hidden-xs"><spring:message code="profile.opinionFromBuyer"/></span></a></li>
                            <li><a href="#" data-tab="3"><span class="icon visible-xs"><i class="icon-star"></i></span><span class="hidden-xs"><spring:message code="profile.opinionFromSeller"/></span></a></li>
                        </ul>

                        <!-- Tabs -->
                        <div class="fh5co-tab-content-wrap">
                            <div class="fh5co-tab-content tab-content active" data-tab-content="1">
                                <div class="col-md-10 col-md-offset-1">
                                    <div class="row">
                                        <div class="col-md-6">
                                            <h2 class="uppercase"><spring:message code="connect.lastName"/></h2>
                                            <p>${user.lastName}</p>
                                        </div>
                                        <div class="col-md-6">
                                            <h2 class="uppercase"><spring:message code="connect.firstName"/></h2>
                                            <p>${user.firstName}</p>
                                        </div>
                                        <div class="col-md-6">
                                            <h2 class="uppercase"><spring:message code="connect.mail"/></h2>
                                            <p>${user.email}</p>
                                        </div>
                                        <div class="col-md-6">
                                            <h2 class="uppercase"><spring:message code="profile.registrationDate"/></h2>
                                            <p>${user.creationDate}</p>
                                        </div>
                                        <div class="col-md-6">
                                            <h2 class="uppercase"><spring:message code="profile.salesNumber"/></h2>
                                            <p>${saleNumber}</p>
                                        </div>
                                        <div class="col-md-6">
                                            <h2 class="uppercase"><spring:message code="profile.purchaseNumber"/></h2>
                                            <p>${purchaseNumber}</p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="fh5co-tab-content tab-content" data-tab-content="2">
                                <div class="col-md-10 col-md-offset-1">
                                    <h3><spring:message code="profile.opinionFromBuyer"/></h3>
                                </div>
                                <div class="col-md-10 col-md-offset-1">
                                    <div class="feed">
                                        <div>
                                            <c:if test="${user.commentByBuyer.size() > 0}">
                                                <c:forEach var="comment" items="${user.commentByBuyer}">
                                                    <a href="<c:url value="/profile?username=${comment.given.username}"/>">
                                                        <div class="comment" style="display: inline;">
                                                            <img src="data:image/jpg;base64,${comment.given.displayPicture()}" alt="<c:url value="/resources/img/userProfile.png"/>">
                                                            <h3>${comment.given.username}</h3>
                                                            <span class="rate">
                                                                <c:forEach begin="1" end="${comment.note}">
                                                                    <i class="icon-star2" style="color:yellow"></i>
                                                                </c:forEach>
                                                                <c:forEach begin="${comment.note + 1}" end="5">
                                                                    <i class="icon-star"></i>
                                                                </c:forEach>
                                                            </span>
                                                        </div>
                                                    </a>
                                                    <div>
                                                        <blockquote>
                                                            <p>${comment.comment}</p>
                                                        </blockquote>
                                                    </div>
                                                </c:forEach>
                                            </c:if>
                                            <c:if test="${user.commentByBuyer.size() == 0}">
                                                <h3><spring:message code="profile.noComment"/></h3>
                                            </c:if>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="fh5co-tab-content tab-content" data-tab-content="3">
                                <div class="col-md-10 col-md-offset-1">
                                    <h3><spring:message code="profile.opinionFromSeller"/></h3>
                                </div>
                                <div class="col-md-10 col-md-offset-1">
                                    <div class="feed">
                                        <div>
                                            <c:if test="${user.commentBySeller.size() > 0}">
                                                <c:forEach var="comment" items="${user.commentBySeller}">
                                                    <a href="<c:url value="/profile?username=${comment.given.username}"/>">
                                                        <div class="comment" style="display: inline;">
                                                            <img src="data:image/jpg;base64,${comment.given.displayPicture()}" alt="<c:url value="/resources/img/userProfile.png"/>">
                                                            <h3>${comment.given.username}</h3>
                                                            <span class="rate">
                                                                <c:forEach begin="1" end="${comment.note}">
                                                                    <i class="icon-star2" style="color:yellow"></i>
                                                                </c:forEach>
                                                                <c:forEach begin="${comment.note + 1}" end="5">
                                                                    <i class="icon-star"></i>
                                                                </c:forEach>
                                                            </span>
                                                        </div>
                                                    </a>
                                                    <div>
                                                        <blockquote>
                                                            <p>${comment.comment}</p>
                                                        </blockquote>
                                                    </div>
                                                </c:forEach>
                                            </c:if>
                                            <c:if test="${user.commentBySeller.size() == 0}">
                                                <h3><spring:message code="profile.noComment"/></h3>
                                            </c:if>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <jsp:include page="footer2.jsp"/>
        </div>
    </body>
</html>

