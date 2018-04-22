
const table = $("#data-table");
const opcoes = '<td>' + '<td class="table-buttons">' +
'<a href="#"><button type="button" class="table-visu" title="Visualizar"><i class="fa fa-eye"></i></button></a>'+   
'<a href="#"><button type="button" class="table-hist" title="Histórico de pedidos"><i class="fa fa-file-text-o"></i></button></a>'+
'<a href="CadastroCliente.html?state=e"><button type="button" class="table-edit" title="Editar"><i class="fa fa-pencil"></i></button></a>' +
'<a href="#"><button type="button" class="table-del" title="Excluir"><i class="fa fa-close"></i></button></a>' +                             
'</td>';
let clientes = [{id: 50, nome: 'Alberto', telefone: '4733356666', endereco: 'Rua teste, 3332'}];
const montarGridCliente = () => {
	const montarLinha = (id, nome, telefone, endereco) => {
		let newRow = $("<tr>");
		let cols = '<td>'+ id + '</td>';
		cols += '<td>'+ nome + '</td>';
		cols += '<td>'+ telefone + '</td>';
		cols += '<td>'+ endereco + '</td>';
		cols += '<td>'+ opcoes + '</td>';
		newRow.append(cols);
		table.append(newRow);
	};
	
	clientes.forEach(cli => {
		montarLinha(cli.id, cli.nome, cli.telefone, cli.endereco);
	});
};

$(document).ready(() => {
	montarGridCliente();
});