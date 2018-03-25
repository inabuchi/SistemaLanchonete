/**
 *  Classe para o modal bootstrap
 * @returns {Class}
 */
function BootstrapModal() {
    this.TIPO = {
        SUCESSO: 0,
        INFO: 1,
        AVISO: 2,
        ERRO: 3
    };

    this.BOTAO = {
        SimNao: 0,
        SimNaoCancelar: 1,
        OK: 2,
        FECHAR: 4
    };
}

/**
 * 
 * @param {String} mensagem
 * @param {String} titulo
 * @param {Number} tipo
 * @param {Number} botao
 * @returns {Element|BootstrapModal.prototype.getModal.div}
 */
BootstrapModal.prototype.getModal = function (mensagem, titulo, tipo, botao) {

    mensagem = mensagem || '';
    titulo = titulo || $(document.head).find('title').text();
    tipo = tipo || this.TIPO.INFO;
    botao = botao || this.BOTAO.OK;

    /**
     * 
     * @type String
     */
    var idTag = 'idBootstrapModal';

    /**
     * 
     * @type Element
     */
    var div = document.getElementById(idTag);

    if (div === null) {
        div = document.createElement('div');
        div.id = idTag;

        $(div).addClass('modal fade')
                .attr({
                    'tabindex': -1,
                    'role': "dialog",
                    'aria-labelledby': idTag,
                    'aria-hidden': true
                });

        $(div).append($('<div class="modal-dialog modal-dialog-centered" role="document">')
                .append($('<div class="modal-content">')
                        .append($(`<div id="${idTag}-header" class="modal-header" hidden>`)
                                .append('<h5 class="modal-title" id="exampleModalLongTitle">&nbsp;</h5>'))
                        .append($(`<div id="${idTag}-body" class="modal-body" hidden>`)
                                .append('<p>'))
                        .append($(`<div id="${idTag}-footer" class="modal-footer" hidden>`)
                                .append('<button type="button" class="btn btn-primary ok" data-dismiss="modal" hidden>Ok</button>')
                                .append('<button type="button" class="btn btn-primary sim" data-dismiss="modal" hidden>Sim</button>')
                                .append('<button type="button" class="btn btn-secondary nao" data-dismiss="modal" hidden>Não</button>')
                                .append('<button type="button" class="btn btn-danger nao-cancelar" data-dismiss="modal" hidden>Não</button>')
                                .append('<button type="button" class="btn btn-secondary cancelar" data-dismiss="modal" hidden>Cancelar</button>')
                                .append('<button type="button" class="btn btn-primary fechar" data-dismiss="modal" hidden>Fechar</button>'))));

        $('body').append(div);

    }

    var divTitulo = document.getElementById(`${idTag}-header`);
    $(divTitulo).removeAttr('class').addClass('modal-header');
    if (titulo !== null && titulo !== '')
        $(divTitulo).removeAttr('hidden').find('h5').text(titulo);
    else
        $(divTitulo).removeAttr('hidden').find('h5').text('');

    switch (tipo) {
        case this.TIPO.AVISO:
            $(divTitulo).addClass('text-white bg-warning');
            break;
        case this.TIPO.ERRO:
            $(divTitulo).addClass('text-white bg-danger');
            break;
        case this.TIPO.INFO:
            $(divTitulo).addClass('text-white bg-info');
            break;
        case this.TIPO.SUCESSO:
            $(divTitulo).addClass('text-white bg-success');
            break;

        default:
            $(divTitulo).addClass('text-white bg-dark');
            break;
    }

    var divCorpo = document.getElementById(`${idTag}-body`);
    if (mensagem !== null && mensagem !== '')
        $(divCorpo).removeAttr('hidden').find('p').text(mensagem);
    else
        $(divCorpo).attr('hidden', '').find('p').text('');

    var divRodape = document.getElementById(`${idTag}-footer`);
    $(divRodape).removeAttr('hidden').find('button').attr('hidden', '');
    switch (botao) {
        case this.BOTAO.FECHAR:
            $(divRodape).find('button.fechar').removeAttr('hidden');
            break;
        case this.BOTAO.OK:
            $(divRodape).find('button.ok').removeAttr('hidden');
            break;
        case this.BOTAO.SimNao:
            $(divRodape).find('button.sim').removeAttr('hidden');
            $(divRodape).find('button.nao').removeAttr('hidden');
            break;
        case this.BOTAO.SimNaoCancelar:
            $(divRodape).find('button.sim').removeAttr('hidden');
            $(divRodape).find('button.nao-cancelar').removeAttr('hidden');
            $(divRodape).find('button.cancelar').removeAttr('hidden');
            break;
    }

    return div;
};

/**
 * @syntax HTMLDialogo.alerta(mensagem);
 * @param {String} mensagem
 * @param {String} titulo
 * @param {Number} tipo
 * @static
 */
BootstrapModal.prototype.alerta = function (mensagem, titulo, tipo) {
    var modal = this.getModal(mensagem, titulo, tipo);

    $(modal).modal({
        keyboard: false,
        backdrop: 'static'
    });
};

window.$bsModal = new BootstrapModal();