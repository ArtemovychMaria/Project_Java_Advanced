<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">

    <title>Login</title>

    <link rel="stylesheet" href="css/login.css">

</head>
<body>

<div class="login">
  <h1>SIGN IN</h1>
  <form class="loginForm">
    <label for="email">Email:</label>
    <input class="userEmail" name="username" type="text">
    <label for="password">Password:</label>
    <input class="loginPassword" name="password" type="password">
    <div class="buttons">
     <button class="login">login</button>
     <p class="message">Not registered? <a href="#">Create an account</a></p>
    </div>
  </form>
</div>

 <div class="register">
  <h1>REGISTER</h1>
  <form class="registerForm">
    <label for="firstName">First name:</label>
    <input class="userName" type="text">
     <label for="lastName">Last name:</label>
     <input class="userSurname" type="text">
    <label for="email">Email:</label>
    <input class="registerEmail" type="email">
    <label for="password">Password:</label>
    <input class="registerPassword" type="password">
      <button class="register">create</button>
  </form>
</div>


<jsp:include page="footer.jsp"></jsp:include>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<script src="js/login.js"></script>
</body>
</html>
<body>