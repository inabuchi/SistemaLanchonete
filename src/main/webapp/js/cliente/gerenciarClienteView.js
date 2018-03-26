/* global enviarAjax */

($ => {
    $(document).ready(() => {
        console.log('entrou');
        construirGrid($);
        atribuirEventos($);
    });
})(jQuery);

function construirGrid($) {
    enviarAjax('/SistemaLanchonete/action/cliente/getCliente', 'get',{},(res) => $('#gerenciar-cliente').jqGrid({
//            url: '/api/cliente/getCliente',
//            datatype: 'json',
//            mtype: 'post',
            colNames: [],
            colModel: [],
            pager: '#pager',
            rowNum: 10,
            rowList: [10,20,30],
            sortName: 'cdCliente',
            sprtprder: 'desc',
            autoencode: true,
            caption: 'Clientes'
        }));
}


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