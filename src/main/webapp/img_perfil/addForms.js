function addGenero(){
    var input = document.createElement("input");
    input.setAttribute("type", "text");
    input.setAttribute("name", "ator");
    var parent = document.getElementById("genero");
    parent.parentNode.insertBefore(input, parent.nextSibling);
}

function addAtor(){
    var input = document.createElement("input");
    input.setAttribute("type", "text");
    input.setAttribute("name", "ator");
    var parent = document.getElementById("ator");
    parent.parentNode.insertBefore(input, parent.nextSibling);
}
function addDiretor(){
    var input = document.createElement("input");
    input.setAttribute("type", "text");
    input.setAttribute("name", "diretor");
    var parent = document.getElementById("diretor");
    parent.parentNode.insertBefore(input, parent.nextSibling);
}
