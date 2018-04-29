
const form = $("#formCliente");

form.ready(() => {
    const btnSave = $("#btnSaveCliente");

    btnSave.click(() => {
        const name = $('#nomeCli');
        const phone1 = $('#telCli01');
        const phone2 = $('#telCli02');
        const obsCli = $('#obsCli');
        const rua = $('#route');
        const nr = $('#street_number');
        const logradouro = $('#logrEndCli');
        const cep = $('#postal_code');
        const bairro = $('#baiCli');
        const complemento = $('#compEndCli');
        const cidade = $('#locality');
        const uf = $('#administrative_area_level_1');
        const obsEnd = $('#obsEndCli');
        const pais = $('#country');

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
                dsObservacao: obsCli.val()
                /*enredecoCliente: [
                	{	dsRua: rua.val(),
		                dsBairro: bairro.val(),
		                cep: cep.val(),
		                dsObservacaoEnd: obsEnd.val(),
		                dsLogradouro: logradouro.val(),
		                dsCidade: cidade.val(),
		                dsEstado: uf.val(),
                    dsPais: pais.val()
		            }
                ]*/
            };

            $.ajax({
            	headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                type: 'POST'
                , data: JSON.stringify(params)
                , dataType: 'json' //http://localhost:8080/SistemaLanchonete
                , url: '/services/cliente/cliente'
                , statusCode: {
                	200: ()=>{
                		debugger;
                		alert("Cliente cadastrado com sucesso!");
                        $(location).attr('href','ConsultaCliente.html');
                	}, 
                	404: ()=>{
                		debugger;
                		alert('Not Found');
                	},
                	415: ()=> {
                		debugger;
                		alert('Não suportado')
                	},
                	500: () => {
                		debugger;
                		alert('ErroInterno');
                	}
                }
            });
        }
    });
});
