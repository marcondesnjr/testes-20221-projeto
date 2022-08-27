<c:forEach items="${grupo.topicos}" var="tpc">
    <div class="row">
        <div class="col-md-12 background-white">
            <div class="col-md-1">
                <a href="usr/${tpc.criador.email}"><img src="${tpc.criador.foto}" alt="Foto Usu�rio"></a>
            </div>
            <div class="col-md-10">
                <h4><a href="control?command=ExbTopico&tpid=${tpc.id}"><c:out value="${tpc.titulo}"/></a></h4>
                <p><c:out value="${tpc.comentario}"/></p>
            </div>
            <div class="col-md-1">
                <img src="${tpc.filme.foto}" alt="Foto Usu�rio">
            </div>
        </div>
    </div>
</c:forEach>
