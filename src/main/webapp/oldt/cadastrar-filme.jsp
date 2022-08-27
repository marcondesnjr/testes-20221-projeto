<%-- 
    Document   : cadastrar-filme
    Created on : 20/10/2015, 21:29:46
    Author     : José Marcondes do Nascimento Junior
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="js/addForms.js" type="text/javascript"></script>
    </head>
    <body>
        <form action="cadastrarFilme" method="post" enctype="multipart/form-data">
                <label for="foto">Capa do Filme</label><br>
                <input type="file" name="foto" id="foto"><br>
                <label for="titulo">Titulo do Filme</label><br>
                <input type="text" name="titulo"  id="titulo" required><br>
                <label for="sinopse">sinopse</label><br>
                <textarea name="sinopse" id="sinopse"></textarea>
                <label for="ano">ano</label><br>
                <input type="number" name="ano" id="ano"><br>
                <label for="genero">Gerero</label><br>
                <input type="text" name="genero" id="genero">
                <a href="#" onclick="addGenero()"><input type="button" value="MORE"></a><br>
                <label for="ator">Atores</label><br>
                <input type="text" name="ator" id="ator">
                <a href="#" onclick="addAtor()"><input type="button" value="MORE"></a><br>
                <label for="diretor">Diretores</label><br>
                <input type="text" name="diretor" id="diretor">
                <a href="#" onclick="addDiretor()"><input type="button" value="MORE"></a><br>
                <label for="cidade">Digite sua cidade</label><br>
                <input type="text" name="cidade" id="cidade"><br>
                <label for="estado">Digite seu estado</label><br>
                <input type="text" name="estado" id="estado"><br>
                <input type="submit" value="enviar"><br>
            </form>
    </body>
</html>
