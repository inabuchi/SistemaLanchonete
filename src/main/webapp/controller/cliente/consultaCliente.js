
const table = $("#data-table");
const opcoes = '<td>' + '<td class="table-buttons">' +
'<a href="#"><button type="button" class="table-visu" title="Visualizar"><i class="fa fa-eye"></i></button></a>'+   
'<a href="#"><button type="button" class="table-hist" title="Histórico de pedidos"><i class="fa fa-file-text-o"></i></button></a>'+
'<a href="CadastroCliente.html?state=e"><button type="button" class="table-edit" title="Editar"><i class="fa fa-pencil"></i></button></a>' +
'<a href="#"><button type="button" class="table-del" title="Excluir"><i class="fa fa-close"></i></button></a>' +                             
'</td>';

let clientes = [];

function inserirLinhaTabela(data) {

    // Captura a refer�ncia da tabela com id 'minhaTabela'
    var table = document.getElementById("data-table");
    // Captura a quantidade de linhas j� existentes na tabela
    var numOfRows = table.rows.length;
    // Captura a quantidade de colunas da �ltima linha da tabela
    var numOfCols = table.rows[numOfRows - 1].cells.length;

    // Insere uma linha no fim da tabela.
    var newRow = table.insertRow(numOfRows);

    // Faz um loop para criar as colunas
    for (var j = 0; j < numOfCols; j++) {
        // Insere uma coluna na nova linha 
        newCell = newRow.insertCell(j);
        // Insere um conte�do na coluna
        newCell.innerHTML = data[j];
    }

}

function popularTabela(lista) {
    let lineData;
    lista.forEach(cli => {
        lineData = [];
        for (let key in cli) {
            lineData.push(cli[key]); 
        }
        inserirLinhaTabela(lineData);
    });
}

$(document).ready(() => {
	$.ajax({
		headers: {
	        'Accept': 'application/json',
	        'Content-Type': 'application/json'
	    },
	    type: 'GET'
	    //, data: JSON.stringify({id:1})
	    , dataType: 'json' //
	    , url: 'http://localhost:8080/SistemaLanchonete/services/cliente/clientes/dsNome=jo'
	    , statusCode: {
	    	200: (res)=>{
	    		debugger;
	    		if (res.length > 0) {
	    			res.forEach(val => {
		    			let obj = {
		    					id: val.cdPessoa,
		    					nome: val.dsNome,
		    					telefone: val.dsTelefone1,
		    					endereco: val.enderecoPessoas[0].endereco.logradouro.dsLogradouro,
		    					opcoes
		    			}
		    			clientes.push(obj);
		    		});
		    		popularTabela(clientes);
		    		$('#data-table').dataTable({
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
	    		}
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
	
});