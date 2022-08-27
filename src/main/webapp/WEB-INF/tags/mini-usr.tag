<%@tag description="put the tag description here" pageEncoding="UTF-8"%>
<%@attribute name="usr" required="true" rtexprvalue="true" type="io.github.marcondesnjr.sismovie.Usuario"%>

<div class="mini-block">
    <img src="${usr.foto}" alt="${usr.nome}">
    <span><a href="usr/${usr.email}">${usr.nome} ${usr.sobrenome}</a></span>
</div>
