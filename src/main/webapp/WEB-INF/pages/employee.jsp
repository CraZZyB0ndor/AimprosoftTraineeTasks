<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Department's employees</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/stylesheet/outputStyle.css"/>"/>
</head>
<body>
<h1 class="chapter-title">Department's employees</h1>
<div class="main-container">

    <c:if test="${empty employees}">
        <div class="no-info">
            <p>There is no employee!</p>
        </div>
    </c:if>

    <c:if test="${!empty employees}">
        <table>
            <tr>
                <th>Id</th>
                <th>Name</th>
                <th>Email</th>
                <th>Age</th>
                <th>Start working date</th>
                <th>Actions</th>
            </tr>
            <c:forEach var="employee" items="${employees}">
                <tr>
                    <td>${employee.id}</td>
                    <td><c:out value="${employee.name}"/></td>
                    <td>${employee.email}</td>
                    <td>${employee.age}</td>
                    <td>${employee.startWorkingDate}</td>
                    <td class="employee-control-table-elements">
                        <div>
                            <form action="openEmployeeForm" method="post">
                                <input type="hidden" name="id" value="${employee.id}">
                                <input type="hidden" name="department.id" value="${departmentId}">
                            <button type="submit">Edit</button>
                            </form>
                            |
                            <form action="deleteEmployee" method="post">
                                <input type="hidden" name="id" value="${employee.id}">
                                <input type="hidden" name="departmentId" value="${departmentId}">
                                <button>Delete</button>
                            </form>
                        </div>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>

    <div class="row-container">
        <form action="openEmployeeForm" method="post">
            <input type="hidden" name="department.id" value="${departmentId}">
            <button class="create-button" type="submit">Add an employee</button>
        </form>
        <a class="back-button" type="button" href="displayDepartments">Back</a>
    </div>
</div>

</body>
</html>
