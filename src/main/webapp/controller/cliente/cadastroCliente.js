
const form = $("#formCliente");

form.ready(() => {
    const btnSave = $("#btnSaveCliente");

    btnSave.click(() => {
        const name = $('#nomeCli');
        const phone1 = $('#telCli01');
        const phone2 = $('#telCli02');
        const obsCli = $('#obsCli');
        const rua = $('#endCli');
        const nr = $('#numEndCli');
        const logradouro = $('#logrEndCli');
        const cep = $('#cepCli');
        const bairro = $('#baiCli');
        const complemento = $('#compEndCli');
        const cidade = $('#codCidCli');
        const uf = $('#ufCli');
        const obsEnd = $('#obsEndCli');

        const validaFomularioCliente = () => {
            let mandatoryFields = '';
            if (!name.val()) {
                mandatoryFields += "Nome, ";
            }

            let res = validarTelefone(phone1.val());
            if (!res.message && !res.valido) {
                mandatoryFields += 'Telefone, ';
            } else if (res.message) {
                alert(res.message);
                return false;
            }


            res = validaEndereco(rua.val(), nr.val(), cep.val(), bairro.val(), cidade.val());
            if (res.message) {
                alert(res.message);
                return false;
            } else if (!res.valido && res.mandatoryFields) {
                mandatoryFields += res.mandatoryFields
            }

            if (mandatoryFields) {
                length = mandatoryFields.length;
                alert('Favor informe o(s) seguite(s) campo(s) ' + mandatoryFields.substr(0, (length - 2)) + '!');
                return false;
            }
            return true;
        };

        
        if (validaFomularioCliente()) {
            const params = {
                dsNome: name.val(),
                dsTelefone1: phone1.val(),
                dsTelefone2: phone2.val(),
                dsObservacao: obsCli.val(),
                enredecoCliente: [ 
                	{	dsRua: rua.val(),
		                dsBairro: bairro.val(),
		                cep: cep.val(),
		                dsObservacaoEnd: obsEnd.val(),
		                dsLogradouro: logradouro.val(),
		                dsCidade: cidade.val(),
		                dsEstado: uf.val()
		            }
                ]
            };
    
            const urlRequest = "/clientes";
            $.ajax({
                type: 'POST'
                , dataType: 'json'
                , url: urlRequest
                , data: params
            }).done(response => {
                alert("Cliente cadastrado com sucesso!");
                $(location).attr('href','ConsultaCliente.html');
            }).fail(response => {
                alert("Falha no cadastro, tente novamente!");
            });
        }
    });
});