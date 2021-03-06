<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../mainPage/header.jsp"/>
<html lang="pl">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="generator" content="Jekyll v4.1.1">
    <title>CocktailVibe</title>
</head>
<body>
<div class="card-body d-flex justify-content-center">
    <div class="shadow p-3 mb-5 bg-white rounded w-5 p-10">
        <h2>- <spring:message code="lang.createIngredient"/> -</h2>
        <form:form method="post" modelAttribute="ingredient">
            <form:errors path="*" cssClass="alert alert-danger" element="div"/>
            <form:hidden path="language"/>
            <div class="form-group">
                <label for="name"><spring:message code="lang.createIngredientName"/></label>
                <form:input class="form-control" placeholder="'Orange Juice'" path="name"/>
            </div>
            <div class="form-group">
                <label for="type"><spring:message code="lang.createIngredientType"/></label>
                <form:input placeholder="'Herb/soft beverage/mineral'" class="form-control" path="type"/>
            </div>
            <button class="btn btn-primary">Save</button>
        </form:form>
    </div>
</div>
</body>
</html>


