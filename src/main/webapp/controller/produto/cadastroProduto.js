$(document).ready(() =>{
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
		$.ajax({
	    	headers: {
	            'Accept': 'application/json',
	            'Content-Type': 'application/json'
	        },
	        type: 'POST'
	        , data: JSON.stringify(params)
	        , dataType: 'json' //
	        , url: 'http://localhost:8080/SistemaLanchonete/services/produtos/produto'
	        , statusCode: {
	        	200: ()=>{
	        		debugger;
	        		alert("Produto cadastrado com sucesso!");
	                $(location).attr('href','ConsultaProduto.html');
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
	};

	$('#btnSave').click(() => {
		salvarProduto();
	});
});