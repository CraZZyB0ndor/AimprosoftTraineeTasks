<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Create DEPARTMENT</title>
    <link rel="stylesheet" href="<c:url value="/resources/stylesheet/createItem.css"/>"/>
</head>
<body>
<h1>Create a Department</h1>
<form action="createOrEditDepartment" method="post" class="creation-form">

    <input type="hidden" name="id" value="${department.id}">
    <div class="input-name">
        <span>Enter department name: </span>
        <input type="text" maxlength="20" name="name" value="${department.name}">
        <c:forEach var="error" items="${errors.get('name')}">
                <div class="error">
                    <p style="color: darkred">${error}</p>
                </div>
        </c:forEach>
    </div>
    <div class="control-elements">
        <input type="submit" value="Save">
        <a href="displayDepartments">Back</a>
    </div>
</form>
</body>
</html>
