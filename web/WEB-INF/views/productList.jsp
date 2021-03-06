<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: dscohier
  Date: 15/05/2019
  Time: 17:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <div class="col-lg-10">
        <c:if test="${not empty error}">
            <label class="error"><spring:message code="${error}"/></label>
        </c:if>
        <c:if test="${not empty success}">
            <label class="success"><spring:message code="${success}"/></label>
        </c:if>
        <c:forEach var="product" items="${products}" varStatus="loop">
            <c:if test="${loop.index%3 == 0}">
                <div class="row">
            </c:if>
            <div class="col-md-4 text-center animate-box" style="margin-top: 20px;">
                <c:if test="${empty mySale}">
                <a href="<c:url value="/profile?username=${product.seller.username}"/>">
                    <div class="fh5co-staff" style="margin-bottom: 5px">
                        <img src="data:image/jpg;base64,${product.seller.displayPicture()}" style="width: 70px"
                             alt="<c:url value="/resources/img/userProfile.png"/>">
                        <span class="rate">
                            <h4 style="display: inline-block">${product.seller.username}</h4>
                            <c:forEach begin="1" end="${product.seller.averageRatingSeller}">
                                <i class="icon-star2" style="color:yellow"></i>
                            </c:forEach>
                            <c:forEach begin="${product.seller.averageRatingSeller + 1}" end="5">
                                <i class="icon-star"></i>
                            </c:forEach>
                            <p style="display: inline-block">${product.seller.commentByBuyer.size()}</p>
                        </span>
                    </div>
                </a>
            </c:if>
                <c:if test="${not empty mySale and !product.sell}">
                    <h4 style="margin-top: 57px"><spring:message code="product.products.notSell"/></h4>
                </c:if>
                <c:if test="${not empty mySale and product.sell}">
                    <a href="<c:url value="/profile?username=${product.buyer.username}"/>">
                        <div class="fh5co-staff" style="margin-bottom: 5px">
                            <img src="data:image/jpg;base64,${product.buyer.displayPicture()}" style="width: 70px"
                                 alt="<c:url value="/resources/img/userProfile.png"/>">
                            <span class="rate">
                            <h4 style="display: inline-block">${product.buyer.username}</h4>
                            <c:forEach begin="1" end="${product.buyer.averageRatingSeller}">
                                <i class="icon-star2" style="color:yellow"></i>
                            </c:forEach>
                            <c:forEach begin="${product.buyer.averageRatingSeller + 1}" end="5">
                                <i class="icon-star"></i>
                            </c:forEach>
                            <p style="display: inline-block">${product.buyer.commentByBuyer.size()}</p>
                        </span>
                        </div>
                    </a>
                </c:if>
                <a href="<c:url value="/product/details?id=${product.id}"/>" style="margin-top: 100px">
                    <h3>${product.name}</h3>
                    <div class="product">
                        <img src="data:image/jpg;base64,${product.displayPicture()}">
                    </div>
                    <div class="desc" style="margin-top: 10px;">
                        <c:if test="${not product.auction}">
                            <h4 style="color: #d1c286;"><spring:message code="common.directSale"/></h4>
                            <span class="price">
                                <spring:message code="common.price"/>
                                    ${product.price}&euro;
                            </span>
                        </c:if>
                        <c:if test="${product.auction}">
                            <h4 style="color: #d1c286"><spring:message code="common.bid"/></h4>
                            <span class="price">
                                <spring:message code="product.details.actualPrice"/>
                                    ${product.price}&euro;
                            </span>
                            <br/>
                            <span class="price">
                                <spring:message code="product.details.endDate"/>
                                <fmt:formatDate pattern="dd-MM-yyyy HH:mm" value="${product.endDate}"/>
                            </span>
                        </c:if>
                        <br/>
                        <span class="price">
                                ${product.seller.city.country}, ${product.seller.city.name}
                        </span>
                        <br/>
                        <span class="price">
                            <spring:message code="product.products.added"/>
                            <fmt:formatDate pattern="dd-MM-yyyy HH:mm" value="${product.creationDate}"/>
                        </span>
                        <br/>
                        <span class="price">
                            &#128065; &nbsp;${product.view}
                        </span>
                    </div>
                </a>
            <c:if test="${not empty displayNote and product.sell}">
                <!-- Button trigger modal -->
                <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#${product.id}">
                    <spring:message code="product.products.rate"/>
                </button>
                <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#sendMessage${product.id}">
                    <spring:message code="product.details.sendMessage"/>
                </button>
            </c:if>
            </div>
            <c:if test="${not empty displayNote and product.sell}">
            <!-- Modal -->
                <div class="modal fade" id="${product.id}" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                    <div class="modal-dialog modal-dialog-centered" role="document">
                        <form:form cssClass="form-horizontal" method="post" action="rate" commandName="ratingForm">
                        <div class="modal-content">
                            <div class="modal-header">
                                <c:if test="${not empty mySale}">
                                    <form:input type="number" style="display:none" value="${product.buyer.id}" path="idUserToRate"/>
                                    <form:input type="boolean" style="display:none" value="${false}" path="isForSeller"/>
                                </c:if>
                                <c:if test="${empty mySale}">
                                    <form:input type="number" style="display:none" value="${product.seller.id}" path="idUserToRate"/>
                                    <form:input type="boolean" style="display:none" value="${true}" path="isForSeller"/>
                                </c:if>
                                <form:input type="number" class="kv-svg rating-loading" value="2.5" data-size="lg" title="" path="rating"/>
                                <script>
                                    $(document).on('ready', function () {
                                        $('.kv-svg').rating({
                                            theme: 'krajee-svg',
                                            filledStar: '<span class="krajee-icon krajee-icon-star"></span>',
                                            emptyStar: '<span class="krajee-icon krajee-icon-star"></span>'
                                        });
                                    });
                                </script>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <form:textarea  path="description" cssClass="form-control" id="description" cssStyle="height: 350px; width: 570px;"/>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal"><spring:message code="common.close"/></button>
                                <button type="submit" class="btn btn-primary"><spring:message code="common.send"/></button>
                            </div>
                        </div>
                        </form:form>
                    </div>
                </div>

                <div class="modal fade" id="sendMessage${product.id}" tabindex="-1" role="dialog" aria-labelledby="Send Message" aria-hidden="true">
                    <div class="modal-dialog modal-dialog-centered" role="document">
                        <form:form cssClass="form-horizontal" method="post" action="sendMessage" commandName="sendMessageForm">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <c:if test="${not empty mySale}">
                                    <spring:message code="common.sendMessageToBuyer"/>
                                    </c:if>
                                    <c:if test="${empty mySale}">
                                        <spring:message code="common.sendMessageToSeller"/>
                                    </c:if>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <form:textarea  path="content" cssClass="form-control" id="content" cssStyle="height: 350px; width: 570px;"/>
                                    <c:if test="${not empty mySale}">
                                        <form:input type="number" style="display:none" value="${product.buyer.id}" path="idUserToSend"/>
                                    </c:if>
                                    <c:if test="${empty mySale}">
                                        <form:input type="number" style="display:none" value="${product.seller.id}" path="idUserToSend"/>
                                    </c:if>
                                    <form:input type="number" style="display:none" value="${product.id}" path="idProduct"/>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal"><spring:message code="common.close"/></button>
                                    <button type="submit" class="btn btn-primary"><spring:message code="common.send"/></button>
                                </div>
                            </div>
                        </form:form>
                    </div>
                </div>
            </c:if>
            <c:if test="${loop.index%3 == 2}">
                </div>
            </c:if>
        </c:forEach>
        <c:if test="${products.size()%3 != 0}">
    </div>
    </c:if>
    <c:if test="${productsPage.totalPages > 0}">
        <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
            <div class="btn-group mr-2" role="group" aria-label="First group">
                <c:forEach begin="1" end="${productsPage.totalPages}" varStatus="i">
                    <c:if test="${i.count eq currentPage}">
                        <input type="submit" class="btn btn-primary" value="${i.count}" disabled/>
                    </c:if>
                    <c:if test="${i.count ne currentPage}">
                        <form:form cssClass="form-horizontal" method="get" action="${action}">
                            <div style="display: none;">
                                <input type="text" id="pageNumber" name="pageNumber" value="${i.count-1}"/>
                                <c:if test="${not empty title}">
                                    <input type="text" id="title" name="title" value="${title}"/>
                                </c:if>
                                <c:if test="${not empty category}">
                                    <input type="text" id="category" name="category" value="${category}"/>
                                </c:if>
                            </div>
                            <input type="submit" class="btn btn-primary" value="${i.count}"/>
                        </form:form>
                    </c:if>
                </c:forEach>
            </div>
        </div>
    </c:if>
</html>
