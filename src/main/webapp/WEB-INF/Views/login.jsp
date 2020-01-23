<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>

<head>
    <meta charset="UTF-8">
    <title>Authentification</title>

   <link href="<c:url value="/resources/css/style2.css" />" rel="stylesheet">
</head>

<body>
<div class="vid-container">
    <div class="inner-container">
        <form  action="j_spring_security_check" method="post">
        <div class="login">
            <h1>Authentification</h1>
            <input type="text" placeholder="Username"  name="j_username" />
            <input type="password" placeholder="Password" name="j_password" />
            <button>Login</button>
            <p>Not a member? <span>Sign Up</span></p>
        </div>
        </form>
    </div>
</div>
</body>

