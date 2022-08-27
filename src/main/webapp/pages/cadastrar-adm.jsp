<div class="container">
    <div class="row row-centered">
        <div class="col-sm-6 col-centered">
            <form class="form-horizontal" action="singin/send/" method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <label for="foto">Faça Upload da sua foto</label>
                    <input class="form-control" type="file" name="foto" id="foto" required>
                </div>
                <div class="form-group">
                    <label for="nome">Digite seu nome</label>
                    <input class="form-control" type="text" name="nome"  id="nome" required>
                </div>
                <div class="form-group">
                    <label for="sobrenome">Digite seu sobrenome</label>
                    <input class="form-control" type="text" name="sobrenome" id="sobrenome" required>
                </div>
                <div class="form-group">
                    <label for="apelido">Digite seu apelido</label>
                    <input class="form-control" type="text" name="apelido" id="apelido">
                </div>
                <div class="form-group">
                    <label for="email">Digite seu email</label>
                    <input class="form-control" type="email" name="email" id="email" required>
                </div>
                <div class="form-group">
                    <label for="senha">Digite sua senha</label>
                    <input class="form-control" type="password" name="senha" id="senha" required>
                </div>
                <div class="form-group">
                    <label for="dataNasc">Digite sua data de Nascimento</label>
                    <input class="form-control" type="date" name="dataNasc" id="dataNasc" required>
                </div>
                <div class="form-group">
                    <label for="cidade">Digite sua cidade</label>
                    <input class="form-control" type="text" name="cidade" id="cidade" required>
                </div>
                <div class="form-group" >
                    <label for="estado">Digite seu estado</label>
                    <select id="estado" name="estado" required>
                        ${estados}
                        <c:forEach items="${estados}" var="estado">
                            <option value="${estado}">${estado}</option>
                        </c:forEach>
                    </select>
                </div>
                <input type="hidden" value="1" name="adm" id="adm">
                <div class="form-group">
                    <input type="submit" value="enviar"><br>
                </div>
            </form>
        </div>
    </div>
</div>
        
