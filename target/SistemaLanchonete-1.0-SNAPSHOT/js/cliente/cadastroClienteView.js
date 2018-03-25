/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* global enviarAjax, BootstrapModal */

($ => {
    $(document).ready(() => {
        atribuirEventos($);
    });
})(jQuery);

/**
 * 
 * @param {jQuery} $
 */
function atribuirEventos($) {
    $('form#form-cadastro').on('submit',
            /**
             * 
             * @param {Event} e
             * @returns {Boolean}
             */
                    (e) => {
                e.preventDefault();
                e.stopImmediatePropagation();

                var objDados = getFormCampos(e.currentTarget);

                var strUrl = objDados.cnCliente === null
                        ? "/addCliente" : "/updateCliente";

                var fnCallBack = objDados.cnCliente === null
                        ? res => $("#cnCliente").val(res.id)
                : res => BootstrapModal.alerta('Cliente atualizado com sucesso!');

                $(e.currentTarget).find(".desabilitar").attr('disabled', '');
                enviarAjax(strUrl, 'post', objDados, fnCallBack)
                        .always(() => $(e.currentTarget).find('.desabilitar').removeAttr('disabled'));

                return false;
            });
}
