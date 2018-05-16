/**
 * @author Igor Vieira Rodrigues
 * páginas abertas
 */
($ => $(document).ready(() => {

    $("#includeMenu").load("Menu.html");

    carregarPaginaInicial();

    window.onhashchange = () => carregarPaginaInicial();


    var fnRetornoAjax = conteudo => {
        var txtUrlCrypt = btoa(txtUrlEnvio);
        window.location.href = `${txtUrl}#${txtUrlCrypt}`;

        $(txtElementoDOM).html(conteudo);

    };

    $(document.body).on('click', '#includeMenu ul.collapse li > a',
        /**
		 * 
		 * Evento que visa abrir páginas dentro de um div específico
		 * 
		 * @param {Event}
		 *            e
		 * @returns {Boolean}
		 */
        e => {
            e.preventDefault();
            e.stopImmediatePropagation();
            var self = e.currentTarget;

            $(self).parents('#includeMenu').find('ul.collapse > li').removeClass('active').find('i.fa.fa-caret-left').remove();
            $liPrincipal = $(self).parent('li');
            $liPrincipal.addClass('active');
            $(self).append('<i class="fa fa-caret-left"></i>');

            // -- Abrir página via ajax na div de id conteudo
            abrirPagina(self.href, '#conteudo');

            return false;
        }).on('click', 'a.link-abrir', e => {
            e.preventDefault();
            e.stopImmediatePropagation();

            abrirPagina(e.currentTarget.href, '#conteudo');

            return false;
        }).on('click', 'a.link-editar', e => {
            e.preventDefault();
            e.stopImmediatePropagation();


            abrirPagina(e.currentTarget.href, '#conteudo');

            $('#conteudo form').each((index, formulario) => {

                var cdCliente = Number($(e.currentTarget.href).attr('data-id'));
                enviarAjax('<caminho do restful para obter clientes>',
                    'GET',
                    { 'cdCliente': cdCliente },
                    res => setFormCampos(formulario, res),
                    prFailCallBack);
            });

            return false;
        });

}))(jQuery);

function carregarPaginaInicial() {
    var nrFim = window.location.href.indexOf('#') || 0;
    var txtUrl = '';
    if (nrFim !== -1)
        txtUrl = window.location.href.substr(nrFim + 1);

    if (txtUrl.length > 0)
        abrirPagina(atob(txtUrl), '#conteudo');
}

