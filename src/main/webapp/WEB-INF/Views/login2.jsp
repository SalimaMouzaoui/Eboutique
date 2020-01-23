<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %> 

<head>
  <meta charset="UTF-8">
  <title>Bootstrap - Login Form</title>
  
  
  <link rel='stylesheet prefetch' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css'>

      <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">

  
</head>

<body>
  <div id="login" class="span3 well well-large offset4">
  <h4>Account Panel</h4>
    <form class="form">
      <input type="text" placeholder="Email" /> <br/><br/>
      <input type="password" placeholder="Password" />
      <label class="checkbox" for="rememberme">
        <input type="checkbox" /> Remember me
      </label> <br />
      <input class="btn btn-success" type="submit" value="Login" /> or <a class="btn" href="#">Create Account</a>
    </form>
</div>
  <script src='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js'></script>

  
</body>

