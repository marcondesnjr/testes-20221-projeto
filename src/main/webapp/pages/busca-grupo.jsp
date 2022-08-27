<div class="container">
    <div class="row">
        <div class="col-xs-12">
            <form class="form-inline" method="POST" action="busca/grupo/send/">
                <div class="input-group">
                    <input class="form-control" type="search" name="nome" id="nome">
                    <span class="input-group-btn">
                        <span class="sr-only">Pesquisar</span>
                        <button class="btn btn-default glyphicon glyphicon-search" type="submit"></button>
                    </span>
                </div>
            </form>
        </div>
    </div><%-- row --%>
    <div class="row">
        <c:forEach items="${grupos}" var="grupo">
            <div class="col-sm-2 col-xs-4">
                <div class="mini-block">
                    <span><a href="grupo/${grupo.id}"><c:out value="${grupo.nome}"/></a></span>
                </div>
            </div>

        </c:forEach>
    </div>
</div>