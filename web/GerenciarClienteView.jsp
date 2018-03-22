<%-- 
    Document   : GerenciarClienteView
    Created on : 15/03/2018, 20:38:17
    Author     : Morgana
--%>

<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <title>Consulta de clientes</title>
        <meta charset="utf-8">
        <script src="js/jquery.min.js" type="text/javascript"></script>
        <script src="js/sistema.js" type="text/javascript"></script>
        <script src="js/gerenciarClienteView.js" type="text/javascript"></script>
    </head>

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

    <body>
        <form class="consulta">
            <div class="title">Consulta de clientes</div> 
            </br>
            <div class="tableArea">
                <table id="gerenciar-cliente" class="table table-striped">
                    <thead>
                        <tr>
                            <th scope="col">Código</th>
                            <th scope="col">Nome</th>
                            <th scope="col">Telefone</th>
                            <th scope="col">Endereço</th>
                            <th scope="col">Número</th>
                            <th scope="col">Bairro</th>
                            <th scope="col"></th>
                            <th scope="col"></th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <th scope="row">1</th>
                            <td>Mark Otto</td>
                            <td>(47) 98789-9878</td>
                            <td>Rua São Paulo</td>
                            <td>456</td>
                            <td>Centro</td>
                            <td><a href="cliente.html?status=A"><i class="fa fa-pencil"></i></a></td>
                            <td><a class="excluir" href="#"><i class="fa fa-trash"></i></a></td>
                        </tr>
                        <tr>
                            <th scope="row">2</th>
                            <td>Jacob Thornton</td>
                            <td>(47) 98756-5564</td>
                            <td>Avenida Duff</td>
                            <td>799</td>
                            <td>Fortaleza</td>
                            <td><a href="cliente.html?status=A"><i class="fa fa-pencil"></i></a></td>
                            <td><a class="excluir" href="#"><i class="fa fa-trash"></i></a></td>
                        </tr>
                        <tr>
                            <th scope="row">3</th>
                            <td>Larry the Bird</td>
                            <td>(47) 87985-9999</td>
                            <td>Rua XV de Novembro</td>
                            <td>1023</td>
                            <td>Centro</td>
                            <td><a href="cliente.html?status=A"><i class="fa fa-pencil"></i></a></td>
                            <td><a class="excluir" href="#"><i class="fa fa-trash"></i></a></td>
                        </tr>
                        <tr>
                            <th scope="row">4</th>
                            <td>Mark Otto</td>
                            <td>(47) 98789-9878</td>
                            <td>Rua São Paulo</td>
                            <td>456</td>
                            <td>Centro</td>
                            <td><a href="cliente.html?status=A"><i class="fa fa-pencil"></i></a></td>
                            <td><a class="excluir" href="#"><i class="fa fa-trash"></i></a></td>
                        </tr>
                        <tr>
                            <th scope="row">5</th>
                            <td>Jacob Thornton</td>
                            <td>(47) 98756-5564</td>
                            <td>Avenida Duff</td>
                            <td>799</td>
                            <td>Fortaleza</td>
                            <td><a href="cliente.html?status=A"><i class="fa fa-pencil"></i></a></td>
                            <td><a class="excluir" href="#"><i class="fa fa-trash"></i></a></td>
                        </tr>
                        <tr>
                            <th scope="row">6</th>
                            <td>Larry the Bird</td>
                            <td>(47) 87985-9999</td>
                            <td>Rua XV de Novembro</td>
                            <td>1023</td>
                            <td>Centro</td>
                            <td><a href="cliente.html?status=A"><i class="fa fa-pencil"></i></a></td>
                            <td><a class="excluir" href="#"><i class="fa fa-trash"></i></a></td>
                        </tr>
                        <tr>
                            <th scope="row">7</th>
                            <td>Mark Otto</td>
                            <td>(47) 98789-9878</td>
                            <td>Rua São Paulo</td>
                            <td>456</td>
                            <td>Centro</td>
                            <td><a href="cliente.html?status=A"><i class="fa fa-pencil"></i></a></td>
                            <td><a class="excluir" href="#"><i class="fa fa-trash"></i></a></td>
                        </tr>
                        <tr>
                            <th scope="row">8</th>
                            <td>Jacob Thornton</td>
                            <td>(47) 98756-5564</td>
                            <td>Avenida Duff</td>
                            <td>799</td>
                            <td>Fortaleza</td>
                            <td><a href="cliente.html?status=A"><i class="fa fa-pencil"></i></a></td>
                            <td><a class="excluir" href="#"><i class="fa fa-trash"></i></a></td>
                        </tr>
                        <tr>
                            <th scope="row">9</th>
                            <td>Larry the Bird</td>
                            <td>(47) 87985-9999</td>
                            <td>Rua XV de Novembro</td>
                            <td>1023</td>
                            <td>Centro</td>
                            <td><a href="cliente.html?status=A"><i class="fa fa-pencil"></i></a></td>
                            <td><a class="excluir" href="#"><i class="fa fa-trash"></i></a></td>
                        </tr>
                        <tr>
                            <th scope="row">10</th>
                            <td>Mark Otto</td>
                            <td>(47) 98789-9878</td>
                            <td>Rua São Paulo</td>
                            <td>456</td>
                            <td>Centro</td>
                            <td><a href="cliente.html?status=A"><i class="fa fa-pencil"></i></a></td>
                            <td><a class="excluir" href="#"><i class="fa fa-trash"></i></a></td>
                        </tr>
                        <tr>
                            <th scope="row">11</th>
                            <td>Jacob Thornton</td>
                            <td>(47) 98756-5564</td>
                            <td>Avenida Duff</td>
                            <td>799</td>
                            <td>Fortaleza</td>
                            <td><a href="cliente.html?status=A"><i class="fa fa-pencil"></i></a></td>
                            <td><a class="excluir" href="#"><i class="fa fa-trash"></i></a></td>
                        </tr>
                        <tr>
                            <th scope="row">12</th>
                            <td>Larry the Bird</td>
                            <td>(47) 87985-9999</td>
                            <td>Rua XV de Novembro</td>
                            <td>1023</td>
                            <td>Centro</td>
                            <td><a href="cliente.html?status=A"><i class="fa fa-pencil"></i></a></td>
                            <td><a class="excluir" href="#"><i class="fa fa-trash"></i></a></td>
                        </tr>
                    </tbody>
                </table>
            </div>

            <center><button type="submit" class="btn btn-primary"><a href="cliente.html?status=C">+ Cadastrar</a></button></center>
        </form>	
    </body>
</html>
