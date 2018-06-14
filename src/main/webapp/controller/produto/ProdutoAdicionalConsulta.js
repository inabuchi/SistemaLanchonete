($ => $(document).ready(() => {
	const table = document.getElementById("table-produto-adicional");;

	table.innerHTML = '';
	// table.innerHTML = '\
	// <thead>\
	// 	<tr>\
	// 		<th scope="col">Código</th>\
	// 		<!-- <th scope="col">Referência</th> -->\
	// 		<th scope="col">Descrição</th>\
	// 		<th scope="col">Valor</th>\ 
	// 		<th scope="col">Opções</th>\
	// 	</tr>\
	// </thead>\
	// <tbody></tbody>';

	enviarAjax('services/produtoAdicional/produtoAdicionais', 'GET', {
		dsAdicional: ''
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
			'data': 'cdProdutoAdicional',
			'title': 'Código'
		},
		{
			'data': 'dsAdicional',
			'title': 'Descrição'
		},
		{
			'data': 'vlAdicional',
			'title': 'Valor',
			'type': 'num-fmt',
			'render': (data, type, row) => data.toLocaleString('pt-BR', {
				style: 'currency',
				currency: 'BRL'
			}),
		},
		{
			'data': 'cdProdutoAdicional',
			'title': 'Opções',
			'className': 'table-buttons',
			'render': (data, type, row) => {
				return `<a class="link-abrir" href="ProdutoAdicionalCadastro.html#${data}">` +
					'<button type="button" class="table-edit" title="Editar">' +
					'<i class="fa fa-pencil"></i>' +
					'</button>' +
					'</a>' +
					`<a href="#" onclick="excluirProdutoAdcional(event, ${data}, '${row.dsAdicional}')">` +
					'<button type="button" class="table-del" title="Excluir">' +
					'<i class="fa fa-close"></i>' +
					'</button>' +
					'</a>';
			}

		},
	];

	// res.forEach((item, i, lista) => {
	// 	let dados = [];
	// 	dados.push(item.cdProdutoAdicional);
	// 	dados.push(item.dsAdicional);
	// 	dados.push(item.vlAdicional);
	// 	dados.push('');
	// 	dataSet.push(dados);
	// });

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
 * Deleta o produto adcional
 * 
 * @param {Event} e evento
 * @param {number} id Id do produto adicional
 * @returns {boolean}
 */
function excluirProdutoAdcional(e, id, dsAdicional) {
	e.preventDefault();
	e.stopImmediatePropagation();

	if (confirm(`Deseja excluir o produto adicional "${dsAdicional}"?`))
		enviarAjax(`services/produtoAdicional/${id}`,
			'DELETE', {}, 
			res => alert(`"${dsAdicional} foi excluído com sucesso.`),
			() => alert('Houve uma falha ao tentar excluir o produto.'));

	return false;
}