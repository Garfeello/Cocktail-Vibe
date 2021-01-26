<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../mainPage/header.jsp"/>


<html lang="pl">
<head>
    <TITLE>Alcohol Form</TITLE>
</head>
<body>

<div class="card-body d-flex justify-content-center">
    <div class="shadow p-3 mb-5 bg-white rounded w-5 p-10">
        <h2>- Create Alcohol -</h2>
        <form:form method="post" modelAttribute="alcohol" enctype="multipart/form-data">
            <form:errors path="*" cssClass="alert alert-danger" element="div"/>
            <form:hidden path="id"/>
            <form:hidden path="picture"/>
            <form:hidden path="user"/>
            <div class="form-group">
                <label for="name">Alcohol name</label>
                <form:input class="form-control" placeholder="'Jack Daniels'" path="name"/>
            </div>
            <div class="form-group">
                <label for="alcoholType">Choose your alcohols</label>
                <form:select cssClass="form-control" items="${alcoholTypeList}" path="alcoholType"/>
            </div>
            <div class="form-group">
                <label for="age">Age of alcohol</label>
                <form:input cssClass="form-control" type="number" placeholder="0-100" path="age"/>
            </div>
            <div class="form-group">
                <label for="description">Write description of an alcohol</label>
                <form:textarea class="form-control" rows="3" placeholder="max 500 characters" path="description"/>
            </div>
            <div class="form-group">
                <label for="language">Choose language of your cocktail</label>
                <form:select class="form-control" path="language">
                    <form:option value="Pl" label="Pl"/>
                    <form:option value="Eng" label="Eng"/>
                </form:select>
            </div>
            <div class="form-group">
                <div class="form-group">
                    <label for="image">Input Picture</label>
                    <input class="form-control-file" type="file" name="image" id="image"/>
                </div>
            </div>
            <button class="btn btn-primary">Save</button>
        </form:form>
    </div>
</div>
</body>


