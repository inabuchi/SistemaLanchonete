<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <title>Consulta de clientes</title>
        <meta charset="utf-8">
        <script src="js/jquery.min.js" type="text/javascript"></script>
        <script src="js/sistema.js" type="text/javascript"></script>
        <script src="js/cadastroClienteView.js" type="text/javascript"></script>
        <style type="text/css">

            body {
                background-color: #f8f8f8;	
            }

            form {		
                border: 1px solid #ddd;
                padding: 20px;
                margin: auto;
                box-shadow: 2px 2px 2px #ddd;
                border-radius: 2px;
                background-color: white;
            }

            .login {
                width: 30%;
                margin-top: 10%;
            }

            a {
                text-decoration: none;
                color: white;
            }

            a:hover {
                text-decoration: none;
                color: white;
            }

            .cad {
                width: 30%;
                margin-top: 1%;
            }

            .consulta {
                width: 60%;
                height: 80%;
                top: 7%;		
                margin: auto;
                margin-left: 20%;
                position: fixed;

            }

            .tableArea {
                width: 100%;
                max-height:81%;
                border:1px solid #ddd;
                overflow-x:auto;
                overflow-y:auto;
                margin:auto;
                margin-bottom: 2%;

            }

            table th, table tr {
                font-size: 15px;

            }


            a {
                text-decoration: none;
                color: white;
            }

            a:hover {
                text-decoration: none;
                color: white;
            }

            input[type=number]::-webkit-inner-spin-button, 
            input[type=number]::-webkit-outer-spin-button { 
                -webkit-appearance: none; 
                margin: 0; 
            }

            input[type="date"]::-webkit-inner-spin-button{
                -webkit-appearance: none;
            }

            .title {
                text-align: center;
                font-size: 16px;
                text-transform: none;
                font-weight: none;
                letter-spacing: 0px;
                margin-bottom: -10px;
            }

            i {
                color: #000;
            }

        </style>

        <script >


            /**
             * Cria um objeto com os campos do form passado por parametro.
             */
            function formToObject(form) {
                var fieldArray = form.serializeArray();
                var obj = {};
                fieldArray.forEach(element => {
                    obj[element.name] = element.value;
                });
                return obj;
            }

            function submeter() {
                var form = document.getElementById("form-cadastro")
                var obj = {
                    cnCliente: 1,
                    dsNome: form.getAttribute("nomeCli"),
                    dsEndereco: form.getAttribute("dsEndereco"),
                    caTelefone: form.getAttribute("numEndCli")
                }
                
                $.ajax({
                    method: "POST", 
                    dataType: 'json',
                    url: "action/cliente/addCliente",
                    contentType: "application/json",
                    data: JSON.stringify(formToObject($('form'))),
                    success: function (msg) {
                        alert('foi!')
                    },
                    error: function (msg) {
                        alert(msg)
                        console.log(msg)
                    }
                })
            }
            </script>
            
    </head>

    <body>
        <form id="form-cadastro" class="cad" onsubmit="submeter()">  
            <div class="form-row">	
                <div class="form-group col-md-5">
<!--                    <label for="codCli">C�digo</label>-->
                    <input class="form-control" id="cnCliente" name="cnCliente" type="hidden">
                </div>
            </div>
            <div class="form-group">
                <label for="nomeCli">Nome</label>
                <input class="form-control" id="dsNome" name="dsNome" type="text" maxlength="255" required>
            </div>
            <div class="form-row">
                <div class="form-group col-md-9">
                    <label for="endCli">Endere�o</label>
                    <input class="form-control" id="dsEndereco" name="dsEndereco" type="text" maxlength="255" required>
                </div>	
                <div class="form-group col-md-3">
                    <label for="numEndCli">Telefone</label>
                    <input class="form-control" id="caTelefone" name="caTelefone" type="tel" required>
                </div>
            </div>
            <button type="button" onclick="submeter()" >botao</button>
            <button type="submit" class="btn btn-primary"><a href="consultaCliente.html">Salvar</a></button>

        </form>
    </body>
</html>
