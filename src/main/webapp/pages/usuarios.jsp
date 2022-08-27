<div class="container-fluid">
    <div class="row">
        <form action="control?command=Pesquisar" method="POST">
            <div class="form-group">
                <label for="nome">Pesquise: </label>
                <input type="search" name="nome" id="nome">
                <input type="submit" value="Pesquisar">
            </div>
        </form>
        <c:forEach items="${usuarios}" var="usuario">
            <div class="col-lg-1 col-md-2">
                <tagFile:mini-usr usr="${usuario}"/>
            </div>
        </c:forEach>
    </div>
</div>