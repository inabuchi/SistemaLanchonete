var firstActivation = true;
var cdFunc;

function registrarOnSaveFuncionario() {
	 $("#btnSaveFunc").click(() => {
		 const codigo = getVal('cdFuncionario');
	        const validaFomularioFuncionario = () => {
	            let mandatoryFields = '';
	            if (!getVal('dsNome')) {
	                mandatoryFields += "Nome, ";
	            }

	            let res = validarTelefone(getVal('dsTelefone1'));
	            if (!res.message && !res.valido) {
	                mandatoryFields += 'Telefone, ';
	            } else if (res.message) {
	                alert(res.message);
	                return false;
	            }

	            if (!getVal('cdNivel')) {
	            	mandatoryFields += 'Nível de Permissão, ';
	            }

	            if (!cdFunc) {
	            	if (!getVal('dsLogin')) {
	 	            	mandatoryFields += 'Login, ';
	 	            }

	 	            if (!getVal('dsSenha')) {
	 	            	mandatoryFields += 'Senha, ';
	 	            }
	            }

	            if (!codigo) {
	            	res = validaEndereco(getVal('route'), getVal('street_number'), getVal('postal_code'), getVal('baiEnd'), getVal('locality'));
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

	        if (validaFomularioFuncionario()) {
	        	const params = {};
	            let type = 'POST';
	            let urlSave = 'http://localhost:8080/SistemaLanchonete/services/funcionario/funcionario';
	        	params.dsNome = getVal('dsNome');
	        	params.dsTelefone1 = getVal('dsTelefone1');
	        	params.dsTelefone2 = getVal('dsTelefone2');
	        	params.cdNivel = getVal('cdNivel');
	        	params.ativo = getVal('ativo');
	        	params.dsCargo = getVal('dsCargo')
	        	params.dsLogin = getVal('dsLogin');
	        	params.dsSenha = getVal('dsSenha');
	        	if (codigo) {
	        		type = 'PUT';
	        		urlSave = 'http://localhost:8080/SistemaLanchonete/services/funcionario/' + codigo;
	        		params.cdFuncionario = getVal('cdFuncionario');
	        	} else {

	        		params.enderecoPessoas = [
	        			{
		        			endereco: {
	        					logradouro: {
	        						bairro: {
	        							municipio: {
	        								estado: {
	        									dsEstado: "",
	        									dsSigla: getVal('administrative_area_level_1')
	        								},
	        								dsMunicipio: getVal('locality')
	        							},
	        							dsBairro: getVal('baiEnd')
	        						},
	        						cdCep: getVal('postal_code'),
	        						dsLogradouro: getVal('route')
	        					},
	        					cdNumero: getVal('street_number'),
	        					dsComplemento: getVal('compEnd'),
	        					dsObservacao: getVal('obsEndFunc')
	        				},
	        				enderecoPadrao: true
	        			}
	        		];
	        	}

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
	                		if (!codigo) {
	                			alert("Funcionário cadastrado com sucesso!");
	                		} else {
	                			alert("Funcionário alterado com sucesso!");
	                		}
	                        $(location).attr('href','ConsultaFuncionario.html');
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

function ativarDadosFuncionario() {
	const url = 'http://localhost:8080/SistemaLanchonete/services/funcionario/' + cdFunc;
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
       			$('#cdFuncionario').val(res.cdFuncionario);
       			$('#dsNome').val(res.dsNome);
	       	    $('#dsTelefone1').val(res.dsTelefone1 ||'');
	       	    $('#dsTelefone2').val(res.dsTelefone2 ||'');
	       	    $('#dsCargo').val(res.dsCargo ||'');
	       	    $('#cdNivel').val(res.cdNivel || '');
	       	    const enderecos = res.enderecoPessoas;
	       	    if (enderecos && enderecos.length > 0) {
	       	        let content = '';
	       	        enderecos.forEach(end => {
						listaEnderecos.push(end);
		       	        let logradouro = end.endereco.logradouro;
		       	        let url = '"CadastroEndereco.html#'+ end.endereco.cdEndereco + '"';
		       	        let botoes = '<a data-toggle="modal" data-target="#ModalEndereco" onClick="populaDadosEndereco('+ end.endereco.cdEndereco +')" class="end-edit">Editar</a>' +
		       	        			 '<a href="#" class="end-del">Excluir</a>';
		       	        let texto = 'Rua: ' + logradouro.dsLogradouro + '<br>' +
		       	        'Número: ' + end.endereco.cdNumero +
		       	        ' CEP: ' + logradouro.cdCep + '<br>' +
		       	        'Bairro: ' + logradouro.bairro.dsBairro + '<br>' +
		       	        'Cidade: ' + logradouro.bairro.municipio.dsMunicipio + '<br><br><br>';
		        	    let p = '<p>' + texto + botoes + '</p>';
		        	    content += p;
	       	        });
	       	        	$('#cardEnds').html(content + '<a data-toggle="modal" data-target="#ModalEndereco" onClick="populaDadosEndereco()" class="add-end"><i class="fa fa-plus"></i>Adicionar endereço</a>');
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

function getVal(idfield) {
	return $('#'+idfield).val();
}

function esconderCardsEndereco() {
	 if (cdFunc) {
		 $('#fsEnd').css('visibility', 'hidden');
		 $('#fsEnd').css('height', '0');
	 } else {
		 $('#fsEnds').css('visibility', 'hidden');
		 $('#fsEnds').css('height', '0');
	 }
}

function tratarVisibilidadeCampos() {
	if (!cdFunc) {
		$('#lCdFunc').hide();
		$('#cdFuncionario').hide();
	} else {
		$('#cdFuncionario').prop("readonly", true);
		$('#lDsLogin').hide();
		$('#dsLogin').hide();
		$('#lDsSenha').hide();
		$('#dsSenha').hide();
	}
}

$('#formFuncionario').ready(()=>{
	debugger;
	if (firstActivation) {
		debugger;
		firstActivation = false;
		cdFunc = getId();
		esconderCardsEndereco();
		tratarVisibilidadeCampos();
		if (cdFunc) {
			$('#btnSaveFunc').text('Editar Funcionário');
			ativarDadosFuncionario();
		} else {
			$('#btnSaveFunc').text('Cadastrar Funcionário');
		}
		registrarOnSaveFuncionario();
		cdFuncionario = null;
	}
});
