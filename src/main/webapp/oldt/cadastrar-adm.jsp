<%-- 
    Document   : cadastraram
    Created on : 20/10/2015, 14:33:08
    Author     : José Marcondes do Nascimento Junior
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="CadastroUsuario" method="post" enctype="multipart/form-data">
                <label for="foto">Faça Upload da sua foto</label><br>
                <input type="file" name="foto" id="foto"><br>
                <label for="nome">Digite seu nome</label><br>
                <input type="text" name="nome"  id="nome" required><br>
                <label for="sobrenome">Digite seu sobrenome</label><br>
                <input type="text" name="sobrenome" id="sobrenome"><br>
                <label for="apelido">Digite seu apelido</label><br>
                <input type="text" name="apelido" id="apelido"><br>
                <label for="email">Digite seu email</label><br>
                <input type="email" name="email" id="email"><br>
                <label for="senha">Digite sua senha</label><br>
                <input type="password" name="senha" id="senha"><br>
                <label for="dataNasc">Digite sua data de Nascimento</label><br>
                <input type="date" name="dataNasc" id="dataNasc"><br>
                <label for="cidade">Digite sua cidade</label><br>
                <input type="text" name="cidade" id="cidade"><br>
                <label for="estado">Digite seu estado</label><br>
                <input type="text" name="estado" id="estado"><br>
                <input type="hidden" value="1" name="adm" id="adm">
                <input type="submit" value="enviar"><br>
        </form>
    </body>
</html>
