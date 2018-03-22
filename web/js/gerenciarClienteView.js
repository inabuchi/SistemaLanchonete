/* global enviarAjax */

($ => {
    $(document).ready(() => {
        console.log('entrou');
        atribuirEventos($);
    });
})(jQuery);

function atribuirEventos($) {
    $('body').on('click', '#gerenciar-cliente a.excluir', function (e) {
        e.preventDefault();
        e.stopImmediatePropagation();

        if (confirm('Ao confirmar, todos os dados do cliente serão perdidos.\nDeseja continuar?')) {
            var elemento = this;
            $(elemento).parents('tr').find('th:first').each(function (i, elem) {
                deletar(Number(elem.innerHTML), $(elemento).parents('tr'));
            });
        }

        return false;
    })/*.on('click', '#gerenciar-cliente a.editar')*/;
}

function deletar(codigo, $tr) {
    codigo = codigo || 0;

    return enviarAjax('/getCliente', 'get', {codigo: codigo}, res => $tr.remove());
}