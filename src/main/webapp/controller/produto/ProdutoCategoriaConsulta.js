($ => $(document).ready(() => {
	const table = document.getElementById("table-produto-categoria");;

	table.innerHTML = '';

	enviarAjax('services/produtoCategoria/produtoCategorias', 'GET', {
		dsCategoria: ''
	}, res => popularTabela(table, res));
}))(jQuery);

/**
 * Esta funç;ao se destina a preencher uma DataTable com os valores de um array JSON
 * @param {HTMLTableElement} tabela Elemento HTML do DOM que será do tipo <i>table</i>, e receberá todo o valor
 * @param {Array} res objeto JSON com os valores
 * @returns {Boolean}
 */
function popularTabela(tabela, res) {

	const dataSet = [];
	const colunas = [{
			'data': 'cdProdutoCategoria',
			'title': 'Código'
		},
		{
			'data': 'dsCategoria',
			'title': 'Descrição'
		},
		{
			'data': 'cdProdutoCategoria',
			'title': 'Opções',
			'className': 'table-buttons',
			'render': (data, type, row) => {
				return `<a class="link-abrir" href="ProdutoCategoriaCadastro.html#${data}">` +
					'<button type="button" class="table-edit" title="Editar">' +
					'<i class="fa fa-pencil"></i>' +
					'</button>' +
					'</a>' +
					`<a href="#" onclick="excluirProdutoCategoria(event, ${data}, '${row.dsCategoria}')">` +
					'<button type="button" class="table-del" title="Excluir">' +
					'<i class="fa fa-close"></i>' +
					'</button>' +
					'</a>';
			}

		},
	];

	if ($(tabela).destroy !== undefined) {
		$(tabela).destroy();
	}
	$(tabela).DataTable({
		data: res,
		columns: colunas,
		columnDefs: [{
			createCell: (td, cellData, rowData, row, col) => {
				if (row === 0)
					$(td).attr('scope', 'col');
			}
		}],
		'language': {
			"sEmptyTable": "Nenhum registro encontrado",
			"sInfo": "Mostrando de _START_ até _END_ de _TOTAL_ registros",
			"sInfoEmpty": "Mostrando 0 até 0 de 0 registros",
			"sInfoFiltered": "(Filtrados de _MAX_ registros)",
			"sInfoPostFix": "",
			"sInfoThousands": ".",
			"sLengthMenu": "_MENU_ resultados por página",
			"sLoadingRecords": "Carregando...",
			"sProcessing": "Processando...",
			"sZeroRecords": "Nenhum registro encontrado",
			"sSearch": "Pesquisar",
			"oPaginate": {
				"sNext": "Próximo",
				"sPrevious": "Anterior",
				"sFirst": "Primeiro",
				"sLast": "Último"
			},
			"oAria": {
				"sSortAscending": ": Ordenar colunas de forma ascendente",
				"sSortDescending": ": Ordenar colunas de forma descendente"
			}
		},
	});

	$(tabela).find('th.table-buttons').removeClass('table-buttons');

	return true;
}

/**
 * Deleta a categoria do produto
 * 
 * @param {Event} e evento
 * @param {number} id Id da categoria
 * @returns {boolean}
 */
function excluirProdutoCategoria(e, id, dsCategoria) {
	e.preventDefault();
	e.stopImmediatePropagation();

	if (confirm(`Deseja excluir a categoria "${dsCategoria}"?`))
		enviarAjax(`services/produtoCategoria/${id}`,
			'DELETE', {}, 
			res => alert(`"${dsCategoria} foi excluído com sucesso.`),
			() => alert('Houve uma falha ao tentar excluir o produto.'));

	return false;
}