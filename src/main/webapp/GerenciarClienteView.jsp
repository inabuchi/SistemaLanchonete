<%-- 
    Document   : GerenciarClienteView
    Created on : 15/03/2018, 20:38:17
    Author     : Morgana
--%>

<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <link href="<%=request.getContextPath()%>/css/bootstrap/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <title>Consulta de clientes</title>
        <meta charset="utf-8">
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
                            <th scope="col">Código Cliente</th>
                            <th scope="col">Código Pessoa</th>
                            <th scope="col">Observação</th>
                            <th scope="col">Situação</th>
                            <th scope="col"></th>
                            <th scope="col"></th>
                        </tr>
                        <tr>
                            <th scope="col"></th>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>
                    </thead>
                    </tbody>
                </table>
            </div>

            <center><button type="submit" class="btn btn-primary"><a href="cliente.html?status=C">+ Cadastrar</a></button></center>
        </form>	
    </body>

    <!-- -- Igor Vieira Rodrigues -->
    <!-- -- As declarações javascript devem ser feitas aqui -->
    <script src="<%=request.getContextPath()%>/js/lib/externas/jquery/jquery.min.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/js/lib/externas/jqgrid/i18n/grid.locale-pt-br.js" type="text/javascript"></script>
    <!--<script src="<%=request.getContextPath()%>/js/lib/externas/jqgrid/jquery.jqGrid.src.js" type="text/javascript"></script>-->
    <script src="<%=request.getContextPath()%>/js/lib/externas/jqgrid/jquery.jqGrid.min.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/js/lib/externas/propper/popper.min.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/js/lib/externas/bootstrap/bootstrap.min.js" type="text/javascript"></script>
    
    <script src="<%=request.getContextPath()%>/js/lib/internas/bootstrap-modal.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/js/lib/internas/sistema.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/js/cliente/gerenciarClienteView.js" type="text/javascript"></script>
</html>
