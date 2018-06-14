($ => $(document).ready(() =>{
    const codAdicional = $('#codAdi').val();
    const codReferencia = $('#refAdi').val();
    const descProduto = $('#descProd').val();
    const valProduto = $('#valorProd').val();
    const isAtivo = $('#ativoCat').val();

    const validarProduto = () => {
        return true;
    };

    const salvarProduto = () => {
        const params = {
            codAdicional,
            codReferencia,
            descProduto,
            valProduto,
            isAtivo
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
                    $(location).attr('href','ProdutoAdicionalConsulta.html');
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
}))(jQuery);