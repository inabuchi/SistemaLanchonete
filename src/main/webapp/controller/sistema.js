/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * 
 * Carrega uma página em um elemento html por ajax
 * 
 * @author Igor Vieira Rodrigues
 * @param {String}
 *            txtUrlEnvio url de destino
 * @param {String}
 *            txtElementoDOM id ou class da tag de destino da página, isto é,
 *            onde a página deverá ser carregada
 */
function abrirPagina(txtUrlEnvio, txtElementoDOM) {
    try {
        var nrFim = window.location.href.indexOf('#') || 0;
        var txtUrl = window.location.href || '';
        if (nrFim !== -1)
            txtUrl = txtUrl.substr(0, nrFim);

        var fnRetornoAjax = conteudo => {

        };

        // var obj = {
        // url: txtUrlEnvio,
        // selector: '.uma-classe'
        // };

        // var txtUrlCrypt = btoa(JSON.stringify(txtUrlEnvio));
        var txtUrlCrypt = btoa(txtUrlEnvio);
        window.location.href = `${txtUrl}#${txtUrlCrypt}`;

        $(txtElementoDOM).html("");

        $(txtElementoDOM).load(txtUrlEnvio);
    } catch (ex) {
        console.debug(ex);
    }
}

/**
 * 
 * Envia objeto json para o restful via ajax
 * 
 * @author Igor Vieira Rodrigues
 * @param {String}
 *            prUrl
 * @param {String}
 *            prMethod
 * @param {Object}
 *            prDados
 * @param {Function}
 *            prDoneCallBack
 * @param {Function}
 *            prFailCallBack
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
        url: prUrl,
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
                console.error("404 - página não encontrada");
            }
        }
    }).done(prDoneCallBack).fail(prFailCallBack);

}

/**
 * 
 * Retorna os dados preenchidos no formulário em formato JSON
 * 
 * @author Igor Vieira Rodrigues
 * @param {HTMLForm}
 *            prForm
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

/**
 * 
 * Preenche um formulário com o objeto JSON, e retorna o próprio formulário
 * 
 * @author Igor Vieira Rodrigues
 * @param {HTMLForm}
 *            prForm
 * @param {JSON}
 *            prJSON
 * @returns {HTMLForm}
 */
function setFormCampos(prForm, prJSON) {
    var n = 0;
    var objRetorno = {};
    while (prForm[n]) {
        var txtNome = prForm[n].name;

        prForm[n].value = prJSON[txtNome];


        n++;
    }

    return prForm;
}

/**
 * Função para retornar a url atual
 * 
 * @returns
 */
function getUrl(){
	var nrFim = window.location.href.indexOf('#') || 0;
	var txtUrl = '';
	if (nrFim !== -1){
		txtUrl = window.location.href.substr(nrFim + 1);
		return atob(txtUrl);
	}else{
		return '';
	}
	
}

var cdPessoa;
function editarCliente(id) {
	cdPessoa = id;
}