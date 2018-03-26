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

                var txtUrl = objDados.cdCliente === null
                        ? "addCliente" : "updateCliente";

                txtUrl = `/api/cliente/${txtUrl}`;
                
                var txtTipo = objDados.cdCliente === null ? 'post' : 'put';

                var fnCallBack = objDados.cdCliente === null ?
                        res => $("#cnCliente").val(res.id) :
                        res => $bsModal.alerta('Cliente atualizado com sucesso!');

                $(e.currentTarget).find(".desabilitar").attr('disabled', '');
                enviarAjax(txtUrl, txtTipo, objDados, fnCallBack)
                        .always(() => $(e.currentTarget).find('.desabilitar').removeAttr('disabled'));

                return false;
            });
}
