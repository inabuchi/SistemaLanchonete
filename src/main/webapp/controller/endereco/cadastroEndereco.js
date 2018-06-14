var listaEnderecos = [];

$(document).ready(() => {
	const cod = $('#cdEndereco');
	const rua = $('#endCli');
	const num = $('#numEndCli');
	const log = $('#logrEnd');
	const cep = $('#cep');
	const bairro = $('#bairro');
	const comp = $('#compEnd');
	const cidade = $('#cidade');
	const uf = $('#uf');
	const obs = $('#obsEnd');
	$('#btnSaveEndereco').click(() => {
		const validarFormEndereco = () => {
			const res = validaEndereco(rua.val(), num.val(), cep.val(), bairro.val(), cidade.val());
			if (res.message) {
		        alert(res.message);
		        return false;
		    } else if (!res.valido && res.mandatoryFields) {
		    	const length = res.mandatoryFields.length;
		        alert('Favor informe o(s) seguite(s) campo(s) ' + res.mandatoryFields.substr(0, (length - 2)) + '!');
		        return false;
		    }
			return  true;
		};

		const salvarEndereco = () => {
			if (validarFormEndereco()) {
				let url;
				let type;
				let pessoa;
				if (objetoPessoa) {
					pessoa = objetoPessoa;
					objetoPessoa = undefined;
					if (codEndereco) {
						url = 'http://localhost:8080/SistemaLanchonete/services/cliente/'+ pessoa.cdPessoa;
						type = 'PUT';
					} else {
						url = 'http://localhost:8080/SistemaLanchonete/services/cliente/cliente';
						type = 'POST';
					}
				} else if (objFuncionario) {
					pessoa = objFuncionario;
					objFuncionario = undefined;
					if (codEndereco) {
						debugger;
						url = 'http://localhost:8080/SistemaLanchonete/services/funcionario/'+ pessoa.cdFuncionario;
						type = 'PUT';
					} else {
						url = 'http://localhost:8080/SistemaLanchonete/services/funcionario/funcionario';
						type = 'POST';
					}
				}


				pessoa.enderecoPessoas = [
					{
						endereco: {
							logradouro: {
								bairro: {
									municipio: {
										estado: {
											dsEstado: "",
											dsSigla: uf.val()
										},
										dsMunicipio: cidade.val()
									},
									dsBairro: bairro.val()
								},
								cdCep: cep.val(),
								dsLogradouro: rua.val()
							},
							cdNumero: nr.val(),
							dsComplemento: complemento.val(),
							dsObservacao: obsEnd.val()
						},
						enderecoPadrao: true,
						cdEndereco = cod.val() || null
					}
				];

				$.ajax({
			    	headers: {
			            'Accept': 'application/json',
			            'Content-Type': 'application/json'
			        },
			        type: type
			        , data: JSON.stringify(pessoa)
			        , dataType: 'json' //
			        , url: url
			        , statusCode: {
			        	200: ()=>{
			        		if (codEndereco) {
			        			alert("Endereço alterado com sucesso!");
			        		} else {
			        			alert("Novo endereço cadastrado!");
			        		}
			        		setCdEndereco(undefined);
			        	},
			        	404: ()=>{
			        		debugger;
			        		alert('Página não encontrada!');
			        	},
			        	415: ()=> {
			        		debugger;
			        		alert('Erro ao montar o json para envio dos dados!')
			        	},
			        	500: () => {
			        		debugger;
			        		alert('Ocorreu um erro interno. Contate o administrador.');
			        	}
			        }
			    });
			}
		};

		salvarEndereco();
	});

	$('#ModalEndereco').on('shown.bs.modal', () =>{
	    const carregarEndereco = ()=> {
	    	debugger;
	    	if (codEndereco && listaEnderecos && listaEnderecos.length > 0) {
	    		listaEnderecos.forEach(end => {
	    			if (end.endereco.cdEndereco == codEndereco) {
	    				let logradouro = end.endereco.logradouro;
	    				cod.val(codEndereco);
	    				rua.val(logradouro.dsLogradouro);
	    				num.val(end.endereco.cdNumero);
	    				log.val(logradouro.dsLogradouro);
	    				cep.val(logradouro.cdCep);
	    				bairro.val(logradouro.bairro.dsBairro);
	    				comp.val(end.endereco.dsComplemento);
	    				cidade.val(logradouro.bairro.municipio.dsMunicipio);
	    				uf.val(logradouro.bairro.municipio.estado.dsEstado);
	    				obs.val(end.dsObservacao);
	    			}
	    		});
	    		$('#btnSaveEndereco').text('Editar endereço');
	    		$('#lcdEndereco').show();
	   		 	$('#cdEndereco').show();
	   		 	$('#cdEndereco').prop("readonly", true);
	    	} else {
	    		$('#btnSaveEndereco').text('Cadastrar');
	    		$('#lcdEndereco').hide();
	    		$('#cdEndereco').hide();
	    	}
		};

		carregarEndereco();


	}).on('hidden.bs.modal', () => {
	    setCdEndereco(undefined);
	    $('#cdEndereco').val('');
		$('#endCli').val('');
		$('#numEndCli').val('');
		$('#logrEnd').val('');
		$('#cep').val('');
		$('#bairro').val('');
		$('#compEnd').val('');
		$('#cidade').val('');
		$('#uf').val('');
		$('#obsEnd').val('');
		objFuncionario = undefined;
		objetoPessoa = undefined;
	});

});
