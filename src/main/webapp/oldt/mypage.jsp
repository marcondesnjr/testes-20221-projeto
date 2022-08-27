<%-- 
    Document   : mypage
    Created on : 20/10/2015, 20:53:46
    Author     : José Marcondes do Nascimento Junior
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Bem vindo</h1>
        ${usr.nome} ${usr.sobrenome} <br>
        ${usr.email}
        <c:if test="${adm eq 1}">
            <a href="cadastrar-adm.jsp">Cadastrar adm</a>
            <a href="cadastrar-filme.jsp">Cadastrar filme</a>
        </c:if>
    </body>
</html>
