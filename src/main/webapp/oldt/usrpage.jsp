<%-- 
    Document   : usrpage
    Created on : 17/10/2015, 19:36:47
    Author     : José Marcondes do Nascimento Junior
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Nome: ${usr.nome} Sobrenome: ${usr.sobrenome}</h1>
        <img src="${usr.foto}" alt="imagem">
    </body>
</html>
