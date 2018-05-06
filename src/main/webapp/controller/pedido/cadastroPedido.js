/**
 * 
 */

function getVal(idfield) {
	return $('#'+idfield).val();
}

function criarPedido() {
	if (validarPedido()) {
		const pedido = {
			
		};
	}
}

function validarPedido() {
	return true;
}

$('#formPedido').ready(() => {
	$('#btnSavePed').click(()=>{
		criarPedido();
	});
});
