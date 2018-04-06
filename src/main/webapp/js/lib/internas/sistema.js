/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* global BootstrapModal, $bsModal */

/**
 * Informa se o valor da string é nula ou vazia
 * 
 * @param {String} valor string a ser checada
 * @returns {Boolean}
 */
String.isNullOrEmpty = function (valor) {

    valor = valor || '';

    return valor === null || valor === '';
};

/**
 * 
 * @param {String} prUrl
 * @param {String} prMethod
 * @param {Object} prDados
 * @param {Function} prDoneCallBack
 * @param {Function} prFailCallBack
 * @returns {jqXHR} 
 */
function enviarAjax(prUrl, prMethod, prDados, prDoneCallBack, prFailCallBack) {
    prUrl = prUrl || '';
    prMethod = prMethod || 'GET';
    prDados = prDados || {};

    if (typeof prDados === 'object')
        prDados = JSON.stringify(prDados);

    prDoneCallBack = prDoneCallBack || (res => console.log(res));

    prFailCallBack = prFailCallBack || (res => {
        console.error(res);
        $bsModal.alerta('página não encontrada', `${res.status} - ${res.statusText}`, $bsModal.TIPO.ERRO);
    });
    return $.ajax({
        url: prUrl,
        type: prMethod.toUpperCase(),
        headers: {
            'Cache-Control': 'no-cache',
            'X-Requested-With': 'XMLHttpRequest',
            'Content-Type': 'application/json',
            'Access-Control-Allow-Origin': '*',
            'Access-Control-Allow-Methods': 'GET, POST, PUT, DELETE, OPTIONS',
            'Access-Control-Allow-Headers': 'Content-Type, Accept',
            'Access-Control-Max-Age': '1'
        },
        dataType: 'json',
        crossDomain: false,
        data: prDados,
        statusCode: {
            404: function (res) {
                //$bsModal.prototype.alerta("page not found");
                console.error(res.responseText, "page not found");
            }
        }
    }).done(prDoneCallBack).fail(prFailCallBack);

}

function mascaraTelefone(telefone) {
    telefone = telefone || 0;
    var modelo = '00000000000';
    var regex = /^([\d]{3})([\d]{4,5})([\d]{4})$/g;
    var mascara = '($1) $2-$3';
    var str = `${modelo}${telefone}`;

    str = str.slice(-modelo.length);
    if (str.substr(0, 1) !== '0')
        str = `0${str}`;

    return str.replace(regex, mascara);
}

/**
 * 
 * @param {HTMLForm} prForm
 * @returns {JSON}
 */
function getFormCampos(prForm) {
    var n = 0;
    var objRetorno = {};
    while (prForm[n]) {
        var campo = prForm[n];

        var valor = null;
        if ($.isNumeric(campo.value)) {
            valor = Number(campo.value);
        } else if (campo.value !== "") {
            valor = campo.value || null;
        }

        if (!String.isNullOrEmpty(campo.name))
            objRetorno[campo.name] = valor;

        n++;
    }

    return objRetorno;
}
