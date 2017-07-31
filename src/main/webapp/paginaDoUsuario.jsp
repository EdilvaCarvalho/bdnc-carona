<%-- 
    Document   : paginaDoUsuario
    Created on : 12/07/2017, 10:54:04
    Author     : Edilva
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Usuário | Carona</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src='http://code.jquery.com/jquery-2.1.3.min.js'></script>
        <script src='//maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js'></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/estilo.css" rel="stylesheet">
    </head>
    <body>
        <header>
            <nav class="navbar navbar-inverse navbar-fixed-top">
                <div class="container">
                    <!-- Brand and toggle get grouped for better mobile display -->
                    <div class="navbar-header">
                        <button class="navbar-toggle" type="button" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"> 
                            <span class="sr-only">Toggle navigation</span> 
                        </button> 
                        <a class="navbar-brand" href="paginaDoUsuario.jsp">Caronas</a>
                    </div>
                    <!-- Collect the nav links, forms, and other content for toggling -->
                    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                        <ul class="nav navbar-nav">
                            <li class="dropdown">
                                <a href="" class="dropdown-toggle" data-toggle="dropdown">Oferecer Carona</a>
                                <ul class="nav dropdown-menu">
                                    <li><a href="oferecerCarona.jsp">Oferecer</a></li>
                                    <li><a href="caronas.jsp">Minhas Ofertas</a></li>
                                </ul>
                            </li>
                            <li>
                                <a href="pesquisarCarona.jsp">Pesquisar ofertas de caronas</a>
                            </li>
                            <li>
                                <a href="Controller?command=Sair">Sair</a>
                            </li>
                        </ul>
                        <a class="navbar-brand navbar-right">${usuario.nome}</a>
                    </div><!-- /.navbar-collapse -->
                </div><!-- /.container -->
            </nav>
        </header>
        <footer>
            <h4>Caronas 2017. Todos os direitos reservados</h4>
        </footer>
    </body>
</html>
