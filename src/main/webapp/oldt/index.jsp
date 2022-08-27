<%-- 
    Document   : index
    Created on : 17/10/2015, 16:35:18
    Author     : José Marcondes do Nascimento Junior
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="io.github.marcondesnjr.sismovie.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>        
        <h1>
            <c:if test="${sistema.usuarioLogado ne null}" var="isLogado">
                ${sistema.usuarioLogado.email}
            </c:if>
            <c:if test="${not isLogado}">
                Ninguem está logado
            </c:if>
        </h1>    
        <ul>
            <li><a href="cadastrar-usuario.html">Cadastrar usuario</a></li>
            <li><a href="index.jsp">index</a></li>
            <li><a href="login-usuario.jsp">login</a></li>
            <li><a href="exibirUsuario?email=123@32">usuario test</a></li>
        </ul>
    </body>
</html>
