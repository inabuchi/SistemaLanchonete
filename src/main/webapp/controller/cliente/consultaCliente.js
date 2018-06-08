var clientes = [];
$(document).ready(() => {
	const table = $("#table-cliente");
	$.ajax({
		headers: {
	        'Accept': 'application/json',
	        'Content-Type': 'application/json'
	    },
	    type: 'GET'
	    , dataType: 'json' //
	    , url: 'http://localhost:8080/SistemaLanchonete/services/cliente/clientes?dsNome='
	    , statusCode: {
	    	200: (res)=>{
	    		const dataSet = [];
	    		res.forEach(val => {
	    			let data = []
	    			let endereco = '';
    				if (val.enderecoPessoas[0]) {
    					endereco = (val.enderecoPessoas[0].endereco.logradouro.dsLogradouro || '') + ', Nº:' + (val.enderecoPessoas[0].endereco.cdNumero || '')
    							   + ', Cep:' + (val.enderecoPessoas[0].endereco.logradouro.cdCep || '') + ', ' + 'B:'+(val.enderecoPessoas[0].endereco.logradouro.dsBairro || '');
    				}
    				const opcoes = '<td>' + '<td class="table-buttons">' +
    				'<a data-toggle="modal" data-target="#ModalHistorico"><button type="button" class="table-hist" title="Histórico de pedidos"><i class="fa fa-file-text-o"></i></button></a>'+
    				`<a href="ClienteCadastro.html#${val.cdPessoa}"  class="link-abrir" ><button type="button" class="table-edit" title="Editar"><i class="fa fa-pencil"></i></button></a>` +
    				'</td>';
    				data.push(val.cdPessoa);
    				data.push(val.dsNome);
    				data.push(val.dsTelefone1);
    				data.push(endereco);
    				data.push(opcoes);
    				dataSet.push(data);
	    		});
	    		table.DataTable( {
    		        data: dataSet,    		       
    		        "language" : {
	    				"sEmptyTable" : "Nenhum registro encontrado",
	    				"sInfo" : "Mostrando de _START_ até _END_ de _TOTAL_ registros",
	    				"sInfoEmpty" : "Mostrando 0 até 0 de 0 registros",
	    				"sInfoFiltered" : "(Filtrados de _MAX_ registros)",
	    				"sInfoPostFix" : "",
	    				"sInfoThousands" : ".",
	    				"sLengthMenu" : "_MENU_ resultados por página",
	    				"sLoadingRecords" : "Carregando...",
	    				"sProcessing" : "Processando...",
	    				"sZeroRecords" : "Nenhum registro encontrado",
	    				"sSearch" : "Pesquisar",
	    				"oPaginate" : {
	    					"sNext" : "Próximo",
	    					"sPrevious" : "Anterior",
	    					"sFirst" : "Primeiro",
	    					"sLast" : "Último"
	    				},
	    				"oAria" : {
	    					"sSortAscending" : ": Ordenar colunas de forma ascendente",
	    					"sSortDescending" : ": Ordenar colunas de forma descendente"
	    				}
	    			}
    		    });
	    	}, 
	    	404: ()=>{
	    		alert('Página não encontrada!');
	    	},
	    	415: ()=> {
	    		alert('Não suportado')
	    	},
	    	500: () => {
	    		alert('Ocorreu um erro interno, verifique com o administrador do sistema.');
	    	}
	    }
	});
});