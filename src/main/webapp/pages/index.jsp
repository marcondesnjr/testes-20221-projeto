<c:if test="${usrLog eq null}">
    <!-- //requried-jsfiles-for owl -->
    <div class="welcome text-center">
        <div class="container">
            <h2>Conecte-se Com Sua Conta</h2>
            <div class="form-submit">
                <form  class="center" action="login/" method="post">
                    <div class="form-group">
                        <label for="login">Email:</label>
                        <input type="text" name="login" id="login" required><br>
                    </div>
                    <div class="form-group">
                        <label for="senha">Senha:</label>
                        <input type="password" name="senha" id="senha" required><br>
                    </div>
                    <input type="submit">
                </form>
            </div>
        </div>
    </div>
</c:if>
<br><br><br><br><br><br><br>
<!--latest designs-->
<div class="exb-filme">
    <div class="container">
        <div class="row row-centered">
            <div class="col-xs-2 col-centered">
                <h3>Ultimos Filmes</h3>
            </div>
        </div>
        <div class="row row-centered">
            <c:forEach items="${filmes}" var="filme">

                <div class="col-md-4 col-xs-8 col-sm-6 col-centered">

                    <div class="ih-item square colored effect4">
                        <a href="filme/${filme.id}">
                            <div class="img"><img src="${filme.foto}" alt="Capa do Filme"></div>
                            <div class="mask1"></div>
                            <div class="mask2"></div>
                            <div class="info">
                                <h3><c:out value="${filme.titulo}"/></h3>
                                <p><c:out value="${filme.sinopse}"/></p>
                            </div>
                        </a>
                    </div>
                </div>

            </c:forEach>
        </div>
    </div>
</div>
<!--//latest designs-->
