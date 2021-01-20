<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<jsp:include page="header.jsp"></jsp:include>

<c:forEach items="${cocktail}" var="cocktail">

    <li class="list-group-item">
        <div class="container card" style="width: 30rem;">
            <img class="card-img-top" SRC="/pictureController/getPicture/${cocktail.id}" width="350px" height="600px">
            <div class="card-body">
                <h5 class="card-title">${cocktail.name}</h5>
                <p class="card-text">${cocktail.preparationDescription}</p>
            </div>
            <ul class="list-group list-group-flush">
                <c:forEach items="${cocktail.alcoholList}" var="alcohol">
                    <li class="list-group-item">${alcohol.name}</li>
                </c:forEach>
                <c:forEach items="${cocktail.ingredients}" var="ingredients">
                    <li class="list-group-item">${ingredients.name}</li>
                </c:forEach>
            </ul>
        </div>
    </li>
</c:forEach>