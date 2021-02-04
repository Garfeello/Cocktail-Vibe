<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<jsp:include page="../mainPage/header.jsp"/>
<html lang="pl">
<head>
    <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <script src="//ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"/>
    <title>CocktailVibe</title>
</head>
<body>
<div class="card-body d-flex justify-content-center">
    <div class="shadow p-3 mb-5 bg-white rounded w-5 p-10">
        <h2>- <spring:message code="lang.createCocktail"/> -</h2>
        <form:form method="post" modelAttribute="cocktail" enctype="multipart/form-data">
            <form:errors path="*" cssClass="alert alert-danger" element="div"/>
            <form:hidden path="language"/>
            <form:hidden path="picture"/>
            <form:hidden path="user"/>
            <form:hidden path="createdOn"/>
            <form:hidden path="id"/>
            <div class="form-group">
                <label for="name"><spring:message code="lang.cocktailName"/></label>
                <form:input class="form-control" placeholder="'Margarita'" path="name"/>
            </div>
            <div class="form-group">
                <label for="alcoholList"><spring:message code="lang.chooseCocktailAlcohols"/></label>
                <form:select class="form-control" items="${alcoholList}" itemLabel="name" path="alcoholList"
                             multiple="true"/>
            </div>
            <div class="form-group">
                <label for="ingredients"><spring:message code="lang.chooseCocktailIngredients"/></label>
                <form:select class="form-control" items="${ingredientList}" itemLabel="name" path="ingredients"
                             multiple="true"/>
            </div>
            <div class="form-group">
                <label for="preparationDescription"><spring:message code="lang.preparationDescription"/></label>
                <form:textarea class="form-control" rows="3" placeholder="max 500 characters"
                               path="preparationDescription"/>
            </div>
            <div class="form-group">
                <label for="image"><spring:message code="lang.inputPicture"/></label>
                <input class="form-control-file" type="file" name="image" id="image"/>
            </div>
            <button class="btn btn-primary"><spring:message code="lang.save"/></button>
        </form:form>
    </div>
</div>
</body>


