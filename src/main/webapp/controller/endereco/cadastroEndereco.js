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
			debugger
			if (validarFormEndereco()) {
				let url = 'http://localhost:8080/SistemaLanchonete/services/endereco/endereco';
				let type = 'POST';
				const params = {
						dsRua: rua.val(),
						cdNumero: num.val(),
			            dsBairro: bairro.val(),
			            cep: cep.val(),
			            dsObservacaoEnd: obs.val(),
			            dsLogradouro: log.val(),
			            dsCidade: cidade.val(),
			            dsEstado: uf.val()
			    	   };
				if (codEndereco) {
					params.cdEndereco = cod.val();
					url = 'http://localhost:8080/SistemaLanchonete/services/endereco/'+ cod.val();
					type = 'PUT';
				}
				$.ajax({
			    	headers: {
			            'Accept': 'application/json',
			            'Content-Type': 'application/json'
			        },
			        type: type
			        , data: JSON.stringify(params)
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
	});
	
});

