<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>User management. Add</title></head>
<body>
<form:form modelAttribute="user" method="post">
    <form:hidden path="id" /><br />
    First name: <form:input type="text" path="firstName" /><br />
    Last name: <form:input type="text" path="lastName" /><br />
    Date of birth: <form:input type="text" path="dateOfBirth" /><br />
    <input type="submit" name="okButton" value="Ok">
    <input type="button" name="cancelButton" value="Cancel">
</form:form>
<c:if test="${requestScope.error != null}">
    <script>
        alert('${requestScope.error}');
    </script>
</c:if>
</body>
</html>