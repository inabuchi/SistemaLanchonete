
var form = $("#formCliente");
debugger;
form.ready(() => {
	 const href = getUrl() || '';
	 const index =href.indexOf('#');
     const cdPessoa = index !== -1 ? href.substr(index+1, href.length) : '';
     tratarCamposCadastro(cdPessoa);
     if (cdPessoa) {
    	 $('#btnSaveCliente').text('Editar Cliente');
    	 ativarDadosCliente();
     } else {
    	 $('#btnSaveCliente').text('Cadastrar Cliente');
     }
     registrarOnSaveCliente();
     
     function registrarOnSaveCliente() {
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
    	            if (!cdPessoa) {
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
    	            const type = 'PUT';
    	        	params.dsNome = name.val();
    	        	params.dsTelefone1 = phone1.val();
    	        	params.dsTelefone2 = phone2.val();
    	        	params.dsObservacao = obsCli.val();
    	        	if (!cdPessoa) {
    	        		type = 'POST';
    	        		/*
						 * params.enderecoCliente = [ { dsRua: rua.val(),
						 * dsBairro: bairro.val(), cep: cep.val(),
						 * dsObservacaoEnd: obsEnd.val(), dsLogradouro:
						 * logradouro.val(), dsCidade: cidade.val(), dsEstado:
						 * uf.val(), dsPais: pais.val() } ];
						 */
    	        	}

    	            const urlSave = cdPessoa ? 'http://localhost:8080/SistemaLanchonete/services/cliente/' + cdPessoa
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
    	                		debugger;
    	                		alert("Cliente cadastrado com sucesso!");
    	                        $(location).attr('href','ConsultaCliente.html');
    	                	}, 
    	                	404: ()=>{
    	                		debugger;
    	                		alert('Página não encontrada!');
    	                	},
    	                	415: ()=> {
    	                		debugger;
    	                		alert('Erro no envio do json para o servidor!')
    	                	},
    	                	500: () => {
    	                		debugger;
    	                		alert('Erro interno, contacte o administrador!');
    	                	}
    	                }
    	            });
    	        }
    	    });
     }
     
     function ativarDadosCliente() {
    	 const url = 'http://localhost:8080/SistemaLanchonete/services/cliente/' + cdPessoa;
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
             			$('#codCli').val(res.cdPessoa);
             			$('#nomeCli').val(res.dsNome);
            	        $('#telCli01').val(res.dsTelefone1 ||'');
            	        $('#telCli02').val(res.dsTelefone2 ||'');
            	        $('#obsCli').val(res.dsObservacao ||'');
            	        const enderecos = res.enderecoPessoas;
            	        if (enderecos && enderecos.length > 0) {
            	        	let content = '';
            	        	enderecos.forEach(end => {
            	        		debugger;
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
            	        	$('#endCards').html(content + '<a href="CadastroEndereco.html" class="add-end"><i class="fa fa-plus"></i>Adicionar endereço</a>');
            	        }
             		}
             	}, 
             	404: ()=>{
             		debugger;
             		alert('Página não encontrada!');
             	},
             	415: ()=> {
             		debugger;
             		alert('Erro no envio do json para o servidor!')
             	},
             	500: () => {
             		debugger;
             		alert('Erro interno, contacte o administrador!');
             	}
             }
         });
     }
     
     function tratarCamposCadastro(cdPessoa) {
    	 if (cdPessoa) {
    		 $('#fsEnd').css('visibility', 'hidden');
    		 $('#fsEnd').css('height', '0');
    	 } else {
    		 $('#fsEnds').css('visibility', 'hidden'); 
    		 $('#fsEnds').css('height', '0');
    	 }
     }
 });



