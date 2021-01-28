<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
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
    <title>CocktailVibe Login</title>
</head>
<body class="text-center" style="background-color: lightgray">
<div class="card-body d-flex justify-content-center">
    <div class="shadow p-3 mb-5 bg-white rounded w-25 p-3 ">

        <form class="form-signin" action="/login" method="post">
            <h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>

            <label for="inputEmail" class="sr-only">Email address</label>
            <input type="text" id="inputEmail" class="form-control" placeholder="Email address" required autofocus
                   name="username">

            <label for="inputPassword" class="sr-only">Password</label>
            <input type="password" id="inputPassword" class="form-control" placeholder="Password" required
                   name="password">
            <br/>
            <button class="btn btn-lg btn-primary btn-block" type="submit">Login in</button>
            <br/>
<%--            <div class="alert alert-danger" role="alert">--%>
<%--                <%=((Exception) request.getSession().getAttribute("SPRING_SECURITY_LAST_EXCEPTION")).getMessage()%>;--%>
<%--            </div>--%>
            <p class="mt-5 mb-3 text-muted">&copy; CocktailVibe 2021</p>
            <a href="${pageContext.request.contextPath}/cocktailVibe/">Home</a>
            <div class="card-footer">
                <small class="text-muted">
                    <a href="${pageContext.request.contextPath}/register">Register here !</a>
                </small>
            </div>
        </form>
    </div>
</div>
</body>
</html>