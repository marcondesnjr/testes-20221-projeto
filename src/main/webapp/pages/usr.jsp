<div class="container-fluid">
    <div class="row">
        <div class="col-md-2">
            <div class="panel-usr-left">
                <img id="foto-perfil" src="${usuario.foto}" alt="${usuario.nome}">
                <c:if test="${not hasSolicitacao}">
                    <a href="sol/${usuario.email}"><img alt="Mandar convite"></a>
                </c:if>    
            </div>
        </div>
        <div class="col-md-10">
            <div class="panel">
                <h2><c:out value="${usuario.nome} ${usuario.sobrenome}"/></h2>
                <ul>
                    <li>Email: <c:out value="${usuario.email}"/></li>
                    <li>Data de Nascimento: <ct:formatDate date="${usuario.dataNasc}"/></li>
                    <li>Cidade: <c:out value="${usuario.cidade}"/></li>
                    <li>Estado: <c:out value="${usuario.estado}"/></li>
                </ul>
            </div>
        </div>
    </div>
</div>
