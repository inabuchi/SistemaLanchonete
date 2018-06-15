($ => $(document).ready(() => carregarPagina($)))(jQuery);

/**
 * Método contendo o carregamento de toda a página
 * @param {jQuery} $ Objeto jQuery
 * @author Igor Vieira Rodrigues
 */
function carregarPagina($) {
    const id = getId() || 0;

    window.isAlterarProdutoCategoria = id > 0;

    if (id > 0) {
        var form = document.getElementById('form-produto-categoria');
        
        enviarAjax(`services/produtoCategoria/${id}`,
            'GET', {},
            res => setFormCampos(form, res));
    }


    $('a#btnSaveCate').on('click', e => salvar(e));
}

/**
 * 
 * @param {Event} e 
 * @author Igor Vieira Rodrigues
 */
function salvar(e) {
    e.preventDefault();
    e.stopImmediatePropagation();
    
    var dados = getFormCampos(document.getElementById('form-produto-categoria'));

    if (isAlterarProdutoCategoria)
        updateProdutoCategoria(dados);
    else
        insertProdutoCategoria(dados);

    return false;
}


/**
 * 
 * @param {Object} dados 
 */
function updateProdutoCategoria(dados) {
    return enviarAjax(`services/produtoCategoria/${dados.cdProdutoCategoria}`,
        'PUT',
        dados,
        res => window.history.back(),
        () => alert('Não foi possível alterar o cadastro'));
}

/**
 * 
 * @param {Object} dados 
 */
function insertProdutoCategoria(dados) {
    return enviarAjax('services/produtoCategoria/produtoCategoria',
        'POST',
        dados,
        res => abrirPagina(getUrl(), '#conteudo'),
        () => alert('Não foi possível alterar o cadastro'));
}