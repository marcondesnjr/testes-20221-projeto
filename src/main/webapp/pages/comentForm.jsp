<form class="form-horizontal" action="control?command=AddComentario" method="POST">
    <div class="form-group">
        <label for="texto">Comentario:</label>
        <textarea name="texto" id="texto" rows="10"></textarea>
        <input type="hidden" value="${topico.id}" name="tid" id="tid">
        <input type="submit">
    </div>
</form>
