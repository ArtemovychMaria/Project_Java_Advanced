<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Login</title>
</head>
<body>

<jsp:include page="header.jsp"></jsp:include>

<form action="entrance" method="post">

    <label for="login">Login :</label> <input name="login">
    <br>
    <label for="password">Password : </label> <input name="password">
    <br>
    <input type="submit" name="logIn" value="Log in">
    <input type="submit" name="Registration" value="Registration">
</form>

<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>