const cdCategoria = $('#codCategoria').val();
const descRefProduto = $('#refProd').val();
const descProduto = $('#descProd').val();
const vlProduto = $('#valorProd').val();
const isAtivo = $('#prodAtivo').val() == 'S';

const validarProduto = () => {
	return true;
};

const salvarProduto = () => {
	const params = {
		cdCategoria,
		descRefProduto,
		descProduto,
		isAtivo,
		vlProduto
	};
	actionRequestService('http://localhost:8080/SistemaLanchonete/services/produtos/produto', params).done(data => {
		alert('Produto cadastrado com sucesso');
		$(location).attr('href','ConsultaProduto.html');
	}).fail(data => {
		alert('Erro na requisição. Erro: '+ data.status + ' ' + data.statusText);
	});
};


$(document).ready(() =>{
	$('#btnSave').click(() => {
		salvarProduto();
	});
});