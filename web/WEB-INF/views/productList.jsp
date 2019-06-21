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
        <c:forEach var="product" items="${products}" varStatus="loop">
            <c:if test="${loop.index%3 == 0}">
                <div class="row">
            </c:if>
            <div class="col-md-4 text-center animate-box" style="margin-top: 20px;">
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
                            <spring:message code="common.city"/>
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
            </div>
            <c:if test="${loop.index%3 == 2}">
                </div>
            </c:if>
        </c:forEach>
        <c:if test="${productsPage.content.size()%3 != 0}">
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
