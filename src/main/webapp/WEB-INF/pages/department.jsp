<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Departments</title>
    <link rel="stylesheet" href="<c:url value="/resources/stylesheet/outputStyle.css"/>"/>
</head>
<body>
<h1 class="chapter-title">Departments</h1>
<div class="main-container">
    <c:if test="${empty departments}">
        <div class="no-info">
            <p>There is no department!</p>
        </div>
    </c:if>
    <c:if test="${!empty departments}">
        <table>
            <tr>
                <th>Id</th>
                <th>Name</th>
                <th>Actions</th>
            </tr>
            <c:forEach var="department" items="${departments}">
                <tr>
                    <td>${department.id}</td>
                    <td><c:out value="${department.name}"/></td>
                    <td class="control-table-elements">
                        <div>
                            <a href="displayEmployees?departmentId=${department.id}">List of
                                employees</a>
                            |
                            <a href="openDepartmentForm?id=${department.id}&name=<c:out value="${department.name}"/>">Edit</a>
                            |
                            <form action="deleteDepartment">
                                <input type="hidden" name="departmentId" value="${department.id}">
                                <button>Delete</button>
                            </form>
                        </div>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
    <a class="create-button" type="button" href="openDepartmentForm">Create a department</a>
</div>
</body>
</html>
