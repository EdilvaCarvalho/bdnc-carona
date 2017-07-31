<%-- 
    Document   : paginaDeResposta
    Created on : 26/04/2017, 13:00:34
    Author     : Edilva
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div><jsp:include page="paginaDoUsuario.jsp"/></div>
        <div>
            <br><br><br>
            <div class="container text-center">
                <h3>${mensagem}</h3>
                <div class="col-md-12 text-center">
                    <a href="${pagina}" class="btn btn-primary" id="btnCadastrar">Voltar</a>
                </div>
            </div>
        </div>
    </body>
</html>
