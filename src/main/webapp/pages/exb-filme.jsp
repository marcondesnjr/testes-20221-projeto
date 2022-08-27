<div class="modal fade" id="rec">
    <div class="modal-dialog">
        <div class="modal-content" style="padding: 10px">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title">Recomende Este Filme</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" action="control?command=AddRecomendacao" method="POST">
                    <input type="hidden" value="${filme.id}" name="idFilme" id="idFilme">
                    <input type="hidden" value="" name="dest" id="dest">
                    <div class="container container-filme">
                        <div class="row">
                            <c:forEach items="${amigos}" var="amigo">
                                <div class="col-sm-3">
                                    <div class="mini-filme">
                                        <img id="${amigo.email}" src="${amigo.foto}" onclick="setFormValue('dest', '${amigo.email}')">
                                    </div>

                                </div>
                            </c:forEach>
                        </div>
                    </div>

                    <input type="submit">
                </form>
            </div>
        </div>

    </div>
</div>

<div class="modal fade" id="alv">
    <div class="modal-dialog">
        <div class="modal-content" style="padding: 10px">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title">Avalie este filme</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" action="control?command=AvaliarFilme" method="POST">
                    <div class="form-group">
                        <label for="rating">Rating:</label>
                        <input type="number" max="5" min="1" name="rating" id="rating" required>
                    </div>
                    <div class="form-group">
                        <label for="coment">Comentário:</label>
                        <textarea class="form-control" name="coment" id="comment" required></textarea>
                    </div>
                    <input type="submit" value="Avaliar">
                    <input type="hidden" value="${filme.id}" name="idFilme">
                </form>
            </div>
        </div>

    </div>
</div>

<div class="container">
    <div class="row">
        <%-- Coluna da esquerda --%> 
        <div class="col-xs-12 col-sm-3 col-md-3">
            <div class="panel-filme-left">
                <img class="foto-movie" src="${filme.foto}" alt="Foto do Filme">
                <div class="info">
                    <span>Rating: ${media}</span>
                    <c:if test="${usrLog ne null}">
                        <a href="#" data-toggle="modal" data-target="#alv"><img src alt="AvaliarFilme"></a>
                        <a href="#" data-toggle="modal" data-target="#rec"><img src alt="Recomendar Filme"></a>
                        </c:if> 
                </div>
            </div>
        </div>
        <%-- Coluna da direita --%>
        <div class="col-xs-12 col-sm-9 col-md-9">
            <div class="panel-filme">
                <h2><strong><c:out value="${filme.titulo}"/></strong></h2>
                <p><c:out value="${filme.sinopse}"/></p>
                <ul style="list-style: none">
                    <li><h3>Informações Adicionais</h3></li>
                    <li>${filme.ano}</li>
                    <li><h4>Atores</h4></li>
                    <c:forEach items="${filme.atores}" var="item">
                        <li>${item}</li>
                    </c:forEach>
                    <li><h4>Diretores</h4></li>
                    <c:forEach items="${filme.diretores}" var="item">
                        <li>${item}</li>
                    </c:forEach>
                        <li><h4>Generos</h4></li>
                    <c:forEach items="${filme.generos}" var="item">
                        <li>${item}</li>
                    </c:forEach>
                </ul>
            </div>

            <div class="panel-filme" style="margin-top: 50px">
                <c:forEach items="${avaliacoes}" var="avl">
                    <div class="alv-separpator">
                        <div class="row">
                            <div class="col-xs-4 col-sm-2">
                                <a href="usr/${avl.usr.email}"><img class="miniature-usr" src="${avl.usr.foto}" alt="foto do usuário"></a>
                            </div>
                            <div class="col-xs-8 col-sm-10">
                                <div class="row">
                                    <div class="col-xs-10">
                                        <h4><c:out value="${avl.usr.nome} ${avl.usr.sobrenome}"/></h4>
                                    </div>
                                    <div class="col-xs-2">
                                        <h5>Rating: ${avl.rating}</h5>
                                    </div> 
                                </div>
                                <div class="row">
                                    <p>
                                        <c:out value="${avl.desc}"/>
                                    </p>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>

    </div>
</div>
