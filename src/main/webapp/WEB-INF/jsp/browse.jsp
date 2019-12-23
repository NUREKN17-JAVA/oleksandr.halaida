<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>User management</title>
</head>
<body>
<table id="userTable" border="1">
    <tr>
        <th></th>
        <th>First name</th>
        <th>Last name</th>
        <th>Date of birth</th>
        <th>Actions</th>
    </tr>
    <c:forEach var="user" items="${userList}">
        <url value="/users/edit.html" var="editUrl">
            < name="id" value="${user.id}" />
        </url>
        < value="/users/delete.html" var="deleteUrl">
            < name="id" value="${user.id}" />
        </>
        < value="/users/details.html" var="detailsUrl">
            < name="id" value="${user.id}" />
        </>
        <tr>
            <td></td>
            <td>${user.firstName}</td>
            <td>${user.lastName}</td>
            <td>${user.dateOfBirth}</td>
            <td>
                <a href="${editUrl}">Edit</a>
                <a href="${deleteUrl}">Delete</a>
                <a href="${detailsUrl}">Details</a>
            </td>
        </tr>
    </c:forEach>
</table>
<spring:url value="/users/add.jsp" var="addUrl" />
<a href="${addUrl}">Add user</a>

<c:if test="${requestScope.error != null}">
    <script>
        alert('${requestScope.error}');
    </script>
</c:if>
<c:if test="${requestScope.message != null}">
    <script>
        alert('${requestScope.message}');
    </script>
</c:if>
</body>
</html>