/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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

    prDoneCallBack = prDoneCallBack || (res => console.log(res));

    prFailCallBack = prFailCallBack || (res => {
        console.error(res);
        alert('página não encontrada');
    });
    return $.ajax({
        url: prUrl.toLocaleLowerCase(),
        type: prMethod.toUpperCase(),
        headers: {
            'Cache-Control': 'no-cache',
            'X-Requested-With': 'XMLHttpRequest',
            'Access-Control-Allow-Origin': '*',
            'Access-Control-Allow-Methods': 'GET, POST, PUT, DELETE, OPTIONS',
            'Access-Control-Allow-Headers': 'Content-Type, Accept',
            'Access-Control-Max-Age': '1'
        },
        dataType: 'json',
        crossDomain: false,
        data: prDados,
        statusCode: {
            404: function () {
                alert("page not found");
            }
        }
    }).done(prDoneCallBack).fail(prFailCallBack);

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
            valor = campo.value;
        }
        objRetorno[campo.name] = campo.value === "" ? null : campo.value;

        n++;
    }

    return objRetorno;
}
