var firstActivation = true;
var cdEdit;
function registrarOnSaveCliente() {
	 const btnSave = $("#btnSaveCliente");
	 btnSave.click(() => {
	        const name = $('#dsNome');
	        const phone1 = $('#dsTelefone1');
	        const phone2 = $('#dsTelefone2');
	        const obsCli = $('#dsObservacao');
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
	            if (!cdEdit) {
	            	res = validaEndereco(rua.val(), nr.val(), cep.val(), bairro.val(), cidade.val());
	            }
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
	        	const params = {};
	            let type = 'PUT';
	        	params.dsNome = name.val();
	        	params.dsTelefone1 = phone1.val();
	        	params.dsTelefone2 = phone2.val();
	        	params.dsObservacao = obsCli.val();
	        	if (!cdEdit) {
	        		type = 'POST';
	        		/*
					 * params.enderecoCliente = [ { dsRua: rua.val(),
					 * dsBairro: bairro.val(), cep: cep.val(),
					 * dsObservacaoEnd: obsEnd.val(), dsLogradouro:
					 * logradouro.val(), dsCidade: cidade.val(), dsEstado:
					 * uf.val(), dsPais: pais.val() } ];
					 */
	        	}

	            const urlSave = cdEdit ? 'http://localhost:8080/SistemaLanchonete/services/cliente/' + cdEdit
	            						 :'http://localhost:8080/SistemaLanchonete/services/cliente/cliente';
	            $.ajax({
	            	headers: {
	                    'Accept': 'application/json',
	                    'Content-Type': 'application/json'
	                },
	                type: type
	                , data: JSON.stringify(params)
	                , dataType: 'json' //
	                , url: urlSave
	                , statusCode: {
	                	200: ()=>{
	                		alert("Cliente cadastrado com sucesso!");
	                        $(location).attr('href','ConsultaCliente.html');
	                	}, 
	                	404: ()=>{
	                		alert('Página não encontrada!');
	                	},
	                	415: ()=> {
	                		alert('Erro no envio do json para o servidor!')
	                	},
	                	500: () => {
	                		alert('Erro interno, contacte o administrador!');
	                	}
	                }
	            });
	        }
	    });
}

function ativarDadosCliente() {
	const url = 'http://localhost:8080/SistemaLanchonete/services/cliente/' + cdEdit;
	$.ajax({
   	headers: {
           'Accept': 'application/json',
           'Content-Type': 'application/json'
       },
       type: 'GET'
       , dataType: 'json' //
       , url: url
       , statusCode: {
      	 200: res => {
       		if (res) {
       			$('#cdCliente').val(res.cdPessoa);
       			$('#dsNome').val(res.dsNome);
	       	        $('#dsTelefone1').val(res.dsTelefone1 ||'');
	       	        $('#dsTelefone2').val(res.dsTelefone2 ||'');
	       	        $('#dsObservacao').val(res.dsObservacao ||'');
	       	        const enderecos = res.enderecoPessoas;
	       	        if (enderecos && enderecos.length > 0) {
	       	        	let content = '';
	       	        	enderecos.forEach(end => {
	       	        		let logradouro = end.endereco.logradouro;
	       	        		let url = '"CadastroEndereco.html#'+ end.endereco.cdEndereco + '"';
	       	        		let botoes = '<a href=' + url + 'class="end-edit">Editar</a>' + 
	       	        					 '<a href="#" class="end-del">Excluir</a>';
	       	        		let texto = 'Rua: ' + logradouro.dsLogradouro + '<br>' +
	       	        					'Número: ' + end.endereco.cdNumero + 
	       	        					' CEP: ' + logradouro.cdCep + '<br>' +
	       	        					'Bairro: ' + logradouro.bairro.dsBairro + '<br>' +
	       	        					'Cidade: ' + logradouro.bairro.municipio.dsMunicipio + '<br><br><br>';
	        	        		let p = '<p>' + texto + botoes + '</p>';
	        	        		content += p; 
	       	        	});
	       	        	$('#cardEnds').html(content + '<a href="CadastroEndereco.html" class="add-end"><i class="fa fa-plus"></i>Adicionar endereço</a>');
	       	        }
       		}
       	},
       	404: ()=>{
       		alert('Página não encontrada!');
       	},
       	415: ()=> {
       		alert('Erro no envio do json para o servidor!')
       	},
       	500: () => {
       		alert('Erro interno, contacte o administrador!');
       	}
       }
   });
}

function tratarVisibilidadeCampos() {
	 if (cdEdit) {
		 $('#fsEnd').css('visibility', 'hidden');
		 $('#fsEnd').css('height', '0');
		 $('#lcdCliente').show();
		 $('#cdCliente').show();
		 $('#cdCliente').prop("readonly", true);
	 } else {
		 $('#fsEnds').css('visibility', 'hidden'); 
		 $('#fsEnds').css('height', '0');
		 $('#lcdCliente').hide();
		 $('#cdCliente').hide();
	 }
}

$("#formCliente").ready(() => {
	if (firstActivation) {
		cdEdit = cdPessoa;
		firstActivation = false;
		if (cdEdit) {
			$('#btnSaveCliente').text('Editar Cliente');
			ativarDadosCliente();
		} else {
			$('#btnSaveCliente').text('Cadastrar Cliente');
		}
		registrarOnSaveCliente();
		tratarVisibilidadeCampos();
		cdPessoa = undefined;
		cdEdit = undefined;
	}
});










