$(document).ready(() =>{
    const cdCategoria = $('#codCat').val();
    const descProdCategoria = $('#descCat').val();

    const validarProduto = () => {
        return true;
    };

    const salvarProduto = () => {
        const params = {
            cdCategoria,
            descProdCategoria
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
                    $(location).attr('href','ProdutoCategoriaConsulta.html');
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