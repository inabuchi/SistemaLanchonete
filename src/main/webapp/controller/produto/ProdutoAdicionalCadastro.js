($ => $(document).ready(() => carregarPagina($)))(jQuery);

/**
 * Método contendo o carregamento de toda a página
 * @param {jQuery} $ Objeto jQuery
 * @author Igor Vieira Rodrigues
 */
function carregarPagina($) {
    const id = getId() || 0;

    window.isAlterarProdutoAdicional = id > 0;

    if (id > 0) {
        var form = document.getElementById('form-produto-adicional');

        enviarAjax(`services/produtoAdicional/${id}`,
            'GET', {},
            res => setFormCampos(form, res));
    }


    $('a#btnSaveAdic').on('click', e => salvar(e));
}

/**
 * 
 * @param {Event} e 
 * @author Igor Vieira Rodrigues
 */
function salvar(e) {
    e.preventDefault();
    e.stopImmediatePropagation();

    var dados = getFormCampos(document.getElementById('form-produto-adicional'));

    if (isAlterarProdutoAdicional)
        updateProdutoAdicional(dados);
    else
        insertProdutoAdicional(dados);

    return false;
}


/**
 * 
 * @param {Object} dados 
 */
function updateProdutoAdicional(dados) {
    return enviarAjax(`services/produtoAdicional/${dados.cdProdutoAdicional}`,
        'PUT',
        dados,
        res => window.history.back(),
        () => alert('Não foi possível alterar o cadastro'));
}

/**
 * 
 * @param {Object} dados 
 */
function insertProdutoAdicional(dados) {
    return enviarAjax('services/produtoAdicional/produtoAdicional',
        'POST',
        dados,
        res => abrirPagina(getUrl(), '#conteudo'),
        () => alert('Não foi possível alterar o cadastro'));
}