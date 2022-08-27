<div class="modal fade" id="remover-conta">
    <div class="modal-dialog">
        <div class="modal-content" style="padding: 10px">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title">Você está prestes a remover sua conta</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" action="del/" method="POST">
                    <div class="form-group">
                        <label for="nome">Sua senha:</label>
                        <input class="form-control" type="password" maxlength="30" name="senha" id="senha">
                    </div>
                    <input class="btn btn-default" type="submit" value="Remover">
                    <input type="hidden" value="${usrLog.email}" name="email">
                </form>
            </div>
        </div>

    </div>
</div>

<div class="modal fade" id="new-group">
    <div class="modal-dialog">
        <div class="modal-content" style="padding: 10px">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title">Crie um novo grupo</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" action="cadastrar/grupo/" method="POST">
                    <div class="form-group">
                        <label for="nome">Nome:</label>
                        <input type="text" maxlength="30" name="nome" id="nome">
                    </div>
                    <div class="form-group">
                        <label for="descricao">Descricao:</label>
                        <textarea class="form-control" name="descricao" id="descricao"></textarea>
                    </div>
                    <input type="submit" value="Criar">
                    <input type="hidden" value="${filme.id}" name="idFilme">
                </form>
            </div>
        </div>

    </div>
</div>


<div class="container-fluid">
    <div class="row">
        <div class="col-sm-2">
            <img id="pfimg" src="${usrLog.foto}" alt="Imagem de perfil">
            <div class="info-usr">          
                <a href="#" data-toggle="modal" data-target="#new-group"><img src="img/add_grupo.png" alt="Criar Novo Grupo"></a>
                <a href="#" data-toggle="modal" data-target="#remover-conta"><img src="img/rem_grupo.png" alt="Excluir Conta"></a>
            </div>
        </div>
        <div class="col-sm-6">
            <h1>Edite as suas informações</h1>
            <form action="editar/usr/" method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <label for="foto">Mude sua foto</label>
                    <input type="file" name="foto" id="foto">
                </div>
                <div class="form-group">
                    <label for="nome">Digite seu nome</label>
                    <input class="form-control" type="text" name="nome"  id="nome" value="${usrLog.nome}">
                </div>
                <div class="form-group">
                    <label for="sobrenome">Digite seu sobrenome</label>
                    <input class="form-control" type="text" name="sobrenome" id="sobrenome" value="${usrLog.sobrenome}">
                </div>
                <div class="form-group">
                    <label for="apelido">Digite seu apelido</label>
                    <input class="form-control" type="text" name="apelido" id="apelido" value="${usrLog.apelido}">
                </div>
                <div class="form-group">
                    <label for="email">Digite seu email</label>
                    <input class="form-control" type="email" name="email" id="email" value="${usrLog.email}">
                </div>
                <div class="form-group">
                    <label for="senhaOld">Digite sua senha antiga</label>
                    <input class="form-control" type="password" name="senhaOld" id="senhaOld" required>
                </div>
                <div class="form-group">
                    <label for="senha">Digite sua senha</label>
                    <input class="form-control" type="password" name="senha" id="senha">
                </div>
                <div class="form-group">
                    <label for="dataNasc">Digite sua data de Nascimento</label>
                    <input type="date" name="dataNasc" id="dataNasc">
                </div>
                <div class="form-group">
                    <label for="cidade">Digite sua cidade</label>
                    <input class="form-control" type="text" name="cidade" id="cidade" value="${usrLog.cidade}">
                </div>
                <div class="form-group">
                    <label for="estado">Digite seu estado</label>
                    <select id="estado" name="estado">
                        ${estados}
                        <c:forEach items="${estados}" var="estado">
                            <option <c:if test="${estado eq usrLog.estado}">selected</c:if> value="${estado}">${estado}</option>
                        </c:forEach>
                    </select>
                </div>
                <input type="submit" value="salvar"><br>  
            </form>
        </div>
        <%-- Coluna da Direita --%>
        <div class="col-sm-4">
            <%-- Linha de solicitacoes--%>
            <div class="row">
                <h2>Notificações</h2>
                <c:forEach items="${solicitacoes}" var="sol">
                    <div class="col-md-12">
                        <span><a href="usr/${sol.remetente.email}">${sol.remetente.nome}</a></span>
                        <a href="sol/acc/${sol.remetente.email}">Aceitar</a>
                        <a href="sol/rem/${sol.remetente.email}">Rejeitar</a>
                    </div>
                </c:forEach>
                <c:forEach items="${recomendacoes}" var="rec">
                    <div class="col-md-12">
                        <div class="col-md-2">
                            <a href="usr/${rec.rem.email}"> <img src="${rec.rem.foto}"></a>
                        </div>
                        <div class="col-md-8">
                            <h5><a href="filme/${rec.filme.id}">${rec.filme.titulo}</a></h5>
                        </div>
                        <div class="col-md-2">
                            <img src="${rec.filme.foto}" alt="Foto do Filme">
                        </div>
                    </div>
                </c:forEach>
            </div>
            <%-- Linha de amigos--%>
            <div class="row">
                <h2>Amigos</h2>
            </div>
            <div class="row">
                <c:forEach items="${usuarios}" var="usuario">
                    <div class="col-md-3">
                        <tagFile:mini-usr usr="${usuario}"/>
                    </div>
                </c:forEach>
            </div>
            <%-- Linha de grupos --%>
            <div class="row">
                <h2>Grupos</h2>
                <c:forEach items="${usrLog.grupos}" var="grupo">
                    <div class="col-md-3 col-lg-3">
                        <div class="mini-block">
                            <a href="grupo/${grupo.id}"> ${grupo.nome}</a>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>

</div>
