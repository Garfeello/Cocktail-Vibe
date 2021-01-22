<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="pl">
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
</head>
<body>
<jsp:include page="../mainPage/header.jsp"/>
<div class="card-body d-flex justify-content-center">
    <div class="shadow p-3 mb-5 bg-white rounded w-25 p-3 ">
        <form:form method="post" modelAttribute="userDTO">
            <div class="form-group">
                <label for="nickName">Nickname</label>
                <form:input class="form-control" placeholder="'Jeremy28'" path="nickName"/>
            </div>
            <div class="form-group">
                <label for="email">Email</label>
                <form:input class="form-control" placeholder="'Jeremy28@gmail.com'" path="email"/>
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <form:input class="form-control" placeholder=" 8-16 characters'" path="password"/>
            </div>
            <div class="form-group">
                <label for="matchingPassword">Repeat Password</label>
                <form:input class="form-control" placeholder="repeat password" path="matchingPassword"/>
            </div>
            <button class="btn btn-primary">Register</button>
        </form:form>
    </div>
</div>
</body>
</html>