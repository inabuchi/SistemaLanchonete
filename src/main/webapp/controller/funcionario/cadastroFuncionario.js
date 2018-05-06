/**
 * 
 */
function salvarFuncionario() {
	if (validarFormularioFunc()) {
		const funcionario = {
			dsNome: getVal('nomeFun'),
			dsTelefone1: getVal('telFun01'),
            dsTelefone2: getVal('telFun02'),
            dtAdmissao: getVal('DatAdFun'),
            dsCargo: getVal('CargoFun'),
            cdNivel: getVal('PermFun'),
            dsLogin: getVal('LoginFun'),
            dsSenha: getVal('SenhaFun'),
            /*enredecoFuncionario: [
            	{	dsRua: getVal('endFun'),
            		nrEndereco: getVal('numEndFun'),
	                dsBairro: getVal('baiFun'),
	                cep: getVal('cepFun'),
	                dsComplemento: getVal('compEndFun'),
	                dsObservacaoEnd: getVal('obsEndFun'),
	                dsLogradouro: getVal('logrEndFun'),
	                dsCidade: getVal('codCidFun'),
	                dsEstado: getVal('ufFun'))
	            }
            ]*/
		};
		$.ajax({
        	headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            type: 'POST'
            , data: JSON.stringify(funcionario)
            , dataType: 'json' //
            , url: 'http://localhost:8080/SistemaLanchonete/services/funcionario/funcionario'
            , statusCode: {
            	200: ()=>{
            		debugger;
            		alert("Funcionário cadastrado com sucesso!");
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
}
 
function validarFormularioFunc() {
	return true;
}

function getVal(idfield) {
	return $('#'+idfield).val();
}

$('#formFuncionario').ready(()=>{
	$('#btnSaveFunc').click(()=>{
		salvarFuncionario();
	});
});