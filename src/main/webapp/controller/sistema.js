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

        var fnRetornoAjax = (res, status, xhr) => {
            if ('error' == status) {
                $(txtElementoDOM).html(res);
                console.error(xhr);
            }
        };

        // var obj = {
        // url: txtUrlEnvio,
        // selector: '.uma-classe'
        // };

        // var txtUrlCrypt = btoa(JSON.stringify(txtUrlEnvio));
        var txtUrlCrypt = btoa(txtUrlEnvio);
        window.bloqueio = true;
        window.location.href = `${txtUrl}#${txtUrlCrypt}`;

        $(txtElementoDOM).html("");
        $(txtElementoDOM).load(txtUrlEnvio, fnRetornoAjax);
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
    
    if (['POST', 'PUT'].includes(prMethod.toUpperCase())) {
        prDados = JSON.stringify(prDados);
    }

    prDoneCallBack = prDoneCallBack || (res => console.log(res));

    prFailCallBack = prFailCallBack || (res => {
        console.error(res);
        alert('página não encontrada');
    });
    return $.ajax({
        url: prUrl,
        type: prMethod.toUpperCase(),
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            //            'Cache-Control': 'no-cache',
            //            'X-Requested-With': 'XMLHttpRequest',
            //            'Access-Control-Allow-Origin': '*',
            //            'Access-Control-Allow-Methods': 'GET, POST, PUT, DELETE, OPTIONS',
            //            'Access-Control-Allow-Headers': 'Content-Type, Accept',
            //            'Access-Control-Max-Age': '1'
        },
        dataType: 'json',
        crossDomain: false,
        data: prDados,
        statusCode: {
        	200: res => prDoneCallBack(res),
        	201: res => prDoneCallBack(res),
            404: res =>console.error("404 - página não encontrada"),
            500: res => prFailCallBack(res)
        }
    }); //.done(prDoneCallBack).fail(prFailCallBack);

}

/**
 *
 * Retorna os dados preenchidos no formulário em formato JSON
 *
 * @author Igor Vieira Rodrigues
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

        if (campo.name !== '' && campo.name !== undefined) {
            objRetorno[campo.name] = campo.value === "" ? null : campo.value;
        }

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

        prForm[n].value = prJSON[txtNome] || '';


        n++;
    }

    return prForm;
}

/**
 * Função para retornar a url atual
 *
 * @returns
 */
function getUrl() {
    var nrFim = window.location.href.indexOf('#') || 0;
    var txtUrl = '';
    if (nrFim !== -1) {
        txtUrl = window.location.href.substr(nrFim + 1);
        return atob(txtUrl);
    } else {
        return '';
    }

}

function getId() {
    const url = getUrl();
    const p = url.indexOf('#') + 1;
    if (p >= 0)
        return parseInt(url.substr(p));
    else
        return 0;
}

var codEndereco;

function setCdEndereco(cd) {
    codEndereco = cd;
}

var objetoPessoa;

function popularObjetoCliente() {
    objetoPessoa = {
        cdPessoa: $('#cdPessoa').val(),
        dsNome: $('#dsNome').val(),
        dsTelefone1: $('#dsTelefone1').val(),
        dsTelefone2: $('#dsTelefone2').val(),
        dsObservacao: $('#dsObservacao').val()
    };
}

function populaDadosEndereco(cd, tipo) {
    setCdEndereco(cd);
    if (tipo === 'C') {
        popularObjetoCliente();
    } else {
        popularObjetoFuncionario();
    }
}

var objFuncionario;

function popularObjetoFuncionario() {
    objFuncionario = {
        cdFuncionario: $('#cdFuncionario').val(),
        dsNome: $('#dsNome').val(),
        dsTelefone1: $('#dsTelefone1').val(),
        dsTelefone2: $('#dsTelefone2').val(),
        cdNivel: $('#cdNivel').val(),
        ativo: $('#ativo').val(),
        dsCargo: $('#dsCargo').val(),
        dsLogin: $('#dsLogin').val() || null,
        dsSenha: $('#dsSenha').val() || null
    };
}