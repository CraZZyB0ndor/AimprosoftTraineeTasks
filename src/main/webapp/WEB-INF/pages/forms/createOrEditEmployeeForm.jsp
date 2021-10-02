<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Add a new employee</title>
    <link rel="stylesheet" href="<c:url value="/resources/stylesheet/createItem.css"/>"/>
</head>
<body>

<h1>Add a new employee</h1>

<form action="createOrEditEmployee" method="post" class="creation-form">

    <input type="hidden" name="id" value="${employee.id}">
    <input type="hidden" name="departmentId" value="${employee.departmentId}">

    <div class="input-name">
        <span>Enter name: </span>
        <input type="text" maxlength="20" name="name" value="${employee.name}">
        <c:forEach var="error" items="${errors.get('name')}">
            <div class="error">
                <p style="color: darkred">${error}</p>
            </div>
        </c:forEach>
    </div>
    <div class="input-name">
        <span>Enter email: </span>
        <input type="email" maxlength="50" name="email" value="${employee.email}">
        <c:forEach var="error" items="${errors.get('email')}">
            <div class="error">
                <p style="color: darkred">${error}</p>
            </div>
        </c:forEach>
    </div>
    <div class="input-name">
        <span>Enter age: </span>
        <input type="number" max="65" min="18" name="age" value="${employee.age == null ? 18 : employee.age}">
        <c:forEach var="error" items="${errors.get('age')}">
            <div class="error">
                <p style="color: darkred">${error}</p>
            </div>
        </c:forEach>
    </div>
    <div class="input-name">
        <span>Enter start working date: </span>
        <input type="date" name="startWorkingDate" value="${employee.startWorkingDate}">
        <c:forEach var="error" items="${errors.get('startWorkingDate')}">
            <div class="error">
                <p style="color: darkred">${error}</p>
            </div>
        </c:forEach>
    </div>
    <div class="control-elements">
        <input type="submit" value="Save">
        <a href="displayEmployees?departmentId=${employee.departmentId}">Back</a>
    </div>
</form>
</body>
</html>
