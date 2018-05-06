$(document).ready(() =>{
	const dsCargo = $('#descCar');

	
	$('#btnSave').click(() => {
		salvarCargo();
	});
	
	const validarFormCargo = () => {
		if (!dsCargo.val()) {
			alert('Informe a descrição do cargo.');
			return false;
		}
		return  true;
	};

	const salvarCargo = () => {
		if (validarFormCargo()) {
			const params = {	
				dsCargo: dsCargo.val(),
			};
			$.ajax({
		    	headers: {
		            'Accept': 'application/json',
		            'Content-Type': 'application/json'
		        },
		        type: 'POST'
		        , data: JSON.stringify(params)
		        , dataType: 'json' //
		        , url: 'http://localhost:8080/SistemaLanchonete/services/cargo/cargo'
		        , statusCode: {
		        	200: ()=>{
		        		debugger;
		        		alert("Cargo cadastrado com sucesso!");
		                $(location).attr('href','ConsultaCargo.html');
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