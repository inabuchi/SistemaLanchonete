/**
 * 
 */
$(document).ready(() =>{
	const rua = $('#endCli');
	const num = $('#numEndCli');
	const log = $('#logrEndCli');
	const cep = $('#cepCli');
	const bairro = $('#baiCli');
	const comp = $('#compEndCli');
	const cidade = $('#codCidCli');
	const uf = $('#ufCli');
	const obs = $('#obsEndCli');
	
	$('#btnSave').click(() => {
		salvarEndereco();
	});
	
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
			$.ajax({
		    	headers: {
		            'Accept': 'application/json',
		            'Content-Type': 'application/json'
		        },
		        type: 'POST'
		        , data: JSON.stringify(params)
		        , dataType: 'json' //
		        , url: 'http://localhost:8080/SistemaLanchonete/services/endereco/endereco'
		        , statusCode: {
		        	200: ()=>{
		        		debugger;
		        		alert("Endereço cadastrado com sucesso!");
		                $(location).attr('href','GerenciamentoCaixa.html');
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
	};
});

