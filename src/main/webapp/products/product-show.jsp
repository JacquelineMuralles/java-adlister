<%--
  Created by IntelliJ IDEA.
  User: jacquelinemuralles
  Date: 4/22/20
  Time: 2:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="../partials/head.jsp">
    <jsp:param name="title" value="Product Show Page"/>
</jsp:include>
    <title>Title</title>
</head>
<body>
<%-- This is where all the content for the Product Show page will go --%>
<jsp:include page="../partials/navbar.jsp"/>
<div class="container">
    <%-- Title of our Product --%>
    <h1>Current Product: ${product.title}</h1>

    <%-- Price of the Product --%>
    <p>Price: ${product.priceInCents/100}</p>

    <%-- Descriptiob of our Product --%>
    <h3>Description</h3>
    <p>${product.description}</p>
</div>
</body>
</html>
