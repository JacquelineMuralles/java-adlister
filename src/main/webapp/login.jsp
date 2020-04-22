<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String redirecturl = "http://localhost:0000/profile.jsp";
    if((request.getParameter("button") != null)){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if(username.equals("admin") && password.equals("password")){
            response.sendRedirect(redirecturl);
        }else {
            response.sendRedirect("http://localhost:0000/login.jsp");
        }
    }
%>
<html>
<head>
    <title>Title</title>
</head>
<body>



<form method="POST" action="login.jsp">

    <label for="username">Username</label>
    <input id="username" name="username" type="text">
    <br>

    <label for="password">Password</label>
    <input id="password" name="password" type="password">
    <br>

    <input type="button" value="Submit">
</form>


<%-- this is a JSP comment, you will *not* see this in the html --%>

<!-- this is an HTML comment, you *will* see this in the html -->

</body>
</html>