<div class="row">
    <div class="col-md-12">
        <div class="col-md-1">
            <a href="control?command=ExibirUsuario&email=${topico.criador.email}"><img src="${topico.criador.foto}" alt="Foto Usuário"></a>
            <c:out value="${topico.criador.nome}"/>
        </div>
        <div class="col-md-10">
            <h4><a href="#"><c:out value="${topico.titulo}"/></a></h4>
            <p><c:out value="${topico.comentario}"/></p>
        </div>
        <div class="col-md-1">
            <img src="${topico.filme.foto}" alt="Foto Usuário">
        </div>
    </div>
</div>
            <c:forEach items="${topico.comentarios}" var="com">
    <div class="row">
        <div class="col-md-12">
        <div class="col-md-1">
            <a href="control?command=ExibirUsuario&email=${com.autor.email}"><img src="${com.autor.foto}" alt="Foto Usuário"></a>
            <c:out value="${com.autor.nome}"/>
        </div>
        <div class="col-md-11">
            <c:out value="${com.texto}"/>
        </div>
    </div>
    </div>
</c:forEach>
        