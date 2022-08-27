<%-- 
    Document   : login-usuario
    Created on : 17/10/2015, 19:27:54
    Author     : José Marcondes do Nascimento Junior
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <c:if test="${sistema.usuarioLogado ne null}">
            <c:redirect url="index.jsp"/>
        </c:if>
        <div>
            <form action="realizarLogin" method="post">
                <input type="text" name="login" id="login">
                <input type="password" name="senha" id="senha">
                <input type="submit">
            </form>
        </div>
    </body>
</html>

