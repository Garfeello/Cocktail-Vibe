<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<jsp:include page="../mainPage/header.jsp"/>
<!doctype html>
<html lang="pl">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="generator" content="Jekyll v4.1.1">
    <title>Translated Alcohol</title>
</head>
<body class="container">
<li class="list-group-item">
    <div class="container card">
        <img class="card-img-top" SRC="/pictureController/getAlcoholPicture/${alcohol.id}" width="350px" height="600px"
             alt="${alcohol.name}">
        <div class="card-body">
            <h5 class="card-title">${alcohol.name}</h5>
            <p class="card-text">${alcohol.description}</p>
        </div>
        <ul class="list-group list-group-flush">
            <li class="list-group-item"><spring:message code="lang.alcoholType"/>: ${alcohol.alcoholType}</li>
            <li class="list-group-item"><spring:message code="lang.alcoholAge"/>: ${alcohol.age}</li>
        </ul>
    </div>
</li>
</body>