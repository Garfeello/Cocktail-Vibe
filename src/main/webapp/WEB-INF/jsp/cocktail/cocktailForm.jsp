<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../mainPage/header.jsp"/>
<html lang="pl">
<head>
    <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <script src="//ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"/>
    <script type="text/javascript">
        // add row
        function addRow() {
            var html = '';
            html += '<div id="inputFormRow">';
            html += '<div class="input-group mb-3">';
            html += '<input type="text" name="title[]" class="form-control m-input" placeholder="Enter title" autocomplete="off">';
            html += '<div class="input-group-append">';
            html += '<button id="removeRow" type="button" class="btn btn-danger">Remove</button>';
            html += '</div>';
            html += '</div>';

            $("#newRow").append(html);
        }

        // remove row
        $(document).on('click', '#removeRow', function () {
            $(this).closest('#inputFormRow').remove();
        });
    </script>
</head>
<body>
<div class="card-body d-flex justify-content-center">
    <div class="shadow p-3 mb-5 bg-white rounded w-5 p-10">
        <h2>- CREATE COCKTAIL -</h2>
        <form:form method="post" modelAttribute="cocktail" enctype="multipart/form-data">
            <form:errors path="*" cssClass="alert alert-danger" element="div" />
            <form:hidden path="id"/>
            <form:hidden path="user.id"/>
            <div class="form-group">
                <label for="name">Cocktail name</label>
                <form:input class="form-control" placeholder="'Margarita'" path="name"/>
            </div>
            <div class="form-group">
                <label for="alcoholList">Choose your alcohols</label>
                <form:select class="form-control" items="${alcoholList}" itemLabel="name" path="alcoholList"
                             multiple="true"/>
            </div>
            <div class="form-group">
                <label for="ingredients">Choose your ingredients</label>
                <form:select class="form-control" items="${ingredientList}" itemLabel="name" path="ingredients"
                             multiple="true"/>
            </div>
            <div class="form-group">
                <label for="preparationDescription">Write preparation description</label>
                <form:textarea class="form-control" rows="3" placeholder="max 500 characters"
                               path="preparationDescription"/>
            </div>
            <div class="form-group">
                <label for="userInspiration">What inspired you ?</label>
                <form:textarea class="form-control" rows="2" placeholder="max 200 characters" path="userInspiration"/>
            </div>
            <div class="form-group">
                <label for="ingredients">Choose language of your cocktail</label>
                <form:select class="form-control" path="language" >
                    <form:option value="Pl" label="Pl"/>
                    <form:option value="Eng" label="Eng"/>
                </form:select>
            </div>
            <div class="form-group">
                <label for="image">Input Picture</label>
                <input class="form-control-file" type="file" name="image" id="image"/>
            </div>
            <button class="btn btn-primary">Save</button>
        </form:form>
    </div>
</div>
</body>


