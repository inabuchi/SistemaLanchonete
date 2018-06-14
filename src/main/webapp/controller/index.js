/**
 * @author Igor Vieira Rodrigues páginas abertas
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

        console.log(e.currentTarget.attributes);

        var nrId = parseInt(e.currentTarget.getAttribute('data-id') || '0');
        var txtServico = e.currentTarget.getAttribute('data-servico');
        var txtKey = e.currentTarget.getAttribute('data-key');

        abrirPagina(e.currentTarget.href, '#conteudo');

        setTimeout(() => $('#conteudo form').each((index, formulario) => {

            enviarAjax(`/SistemaLanchonete/services/${txtServico}/${nrId}`,
                'GET', {
                    txtKey: nrId
                },
                res => setFormCampos(formulario, res),
                res => console.error(res));
        }), 500);

        return false;
    });

}))(jQuery);

function carregarPaginaInicial() {
    var bloqueio = window.bloqueio || false;

    if (!bloqueio) {
        var txtUrl = getUrl();
        if (txtUrl.length > 0)
            abrirPagina(txtUrl, '#conteudo');
        else
            abrirPagina('TelaDefault.html', '#conteudo');
    }
    window.bloqueio = false;
}