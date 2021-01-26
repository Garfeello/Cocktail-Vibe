<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!doctype html>
<html lang="pl">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="generator" content="Jekyll v4.1.1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light container">
    <a class="navbar-brand" href="/cocktailVibe/">CocktailVibe</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="/cocktailVibe/cocktailList">All cocktails<span
                        class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="/cocktailVibe/alcoholList">All alcohols<span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="/cocktailVibe/ingredient/ingredientList">All ingredients <span
                        class="sr-only">(current)</span></a>
            </li>
        </ul>
        <form class="d-flex" action="${pageContext.request.contextPath}/cocktailVibe/search" method="get">
            <input class="form-control mr-sm-2" type="text" name="searchedString" placeholder="Search"
                   aria-label="Search">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
        </form>
        <%--DROPDOWN--%>
        <sec:authorize access="isAnonymous()">
            <ul class="navbar-nav mr-auto">
                <div class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                       data-toggle="dropdown"
                       aria-haspopup="true" aria-expanded="false">
                        Login
                    </a>
                    <div class="dropdown-menu">
                        <form cssClass="px-4 py-3" action="/login" method="post">
                            <div class="form-group">
                                <label for="exampleDropdownFormEmail1">Email address</label>
                                <input type="email" class="form-control" id="exampleDropdownFormEmail1"
                                       placeholder="email@example.com" name="username">
                            </div>
                            <div class="form-group">
                                <label for="exampleDropdownFormPassword1">Password</label>
                                <input type="password" class="form-control" id="exampleDropdownFormPassword1"
                                       placeholder="Password" name="password">
                            </div>
                            <button type="submit" class="btn btn-primary">Sign in</button>
                        </form>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/register">New around here?
                            Sign
                            up</a>
                    </div>
                </div>
            </ul>
        </sec:authorize>
        <sec:authorize access="isAuthenticated()">
            <ul class="navbar-nav mr-auto">
                <div class="btn-group">
                    <button type="button" class="btn btn-light dropdown-toggle" data-toggle="dropdown"
                            aria-haspopup="true" aria-expanded="false">
                        <sec:authentication property="principal.username"/>
                    </button>
                    <div class="dropdown-menu">
                        <a class="nav-link" href="/cocktailVibe/addCocktail">Add cocktail</a>
                        <a class="nav-link" href="/cocktailVibe/addAlcohol">Add alcohol</a>
                        <a class="nav-link" href="/cocktailVibe/ingredient/addIngredient">Add ingredient</a>
                        <a class="nav-link" href="/cocktailVibe/user/cocktails">My cocktails</a>
                        <a class="nav-link" href="/cocktailVibe/user/alcohols">My alcohols </a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="/logout">Logout</a>
                    </div>
                </div>

            </ul>
        </sec:authorize>

    </div>
</nav>
</body>
</html>