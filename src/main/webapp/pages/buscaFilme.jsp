<div class="container">
    <div class="row">
        <form class="form-horizontal" action="control?command=BuscaFilme" method="POST">
            <div class="col-md-3">
                <label for="titulo">Titulo:</label>
                <input class="form-control" type="text" name="titulo" id="titulo">
            </div>
            <div class="col-md-2">
                <label for="genero">Genero:</label>
                <select class="form-control" name="genero" id="genero">
                    <option value=""></option>
                    <c:forEach items="${generos}" var="genero">
                        <option value="${genero.name}">${genero}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="col-md-2">
                <label for="ordem">Ordernar por:</label>
                <select class="form-control" name="ordem" id="ordem">
                    <option value="titulo">Titulo</option>
                    <option value="rating">Rating</option>
                    <option value="ano">Ano</option>
                </select>
            </div>
            <div class="col-md-2">
                <label for="ator">Ator</label>
                <input class="form-control" type="text" name="ator" id="ator">
            </div>
            <div class="col-md-2">
                <label for="diretor">Diretor</label>
                <input class="form-control" type="text" name="diretor" id="diretor">
            </div>
            <div class="col-md-1">
                <input type="submit" class="btn btn-lg btn-default" value="Buscar">
            </div>
        </form>
    </div>
    
    <div class="row">
        <c:forEach items="${filmes}" var="filme">
            <div class="col-md-2 busca-filme-foto">
                <a href="filme/${filme.id}"><img src="${filme.foto}"></a>
            </div>
        </c:forEach>
    </div>
</div>
