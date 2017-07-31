<%-- 
    Document   : detalhesDaCarona
    Created on : 31/07/2017, 13:15:08
    Author     : Edilva
--%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="df" uri="/WEB-INF/myTags.tld"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src='http://code.jquery.com/jquery-2.1.3.min.js'></script>
        <script src="js/jquery.maskedinput.min.js" type="text/javascript"></script>
        <script src="js/funcoes.js"></script>
        <title>JSP Page</title>
    </head>
    <body>
        <div><jsp:include page="paginaDoUsuario.jsp"/></div>
        <br><br><br>
        <div class="container text-center">
            <h2>Detalhes da Carona</h2>
            <br>
        </div>
        <div class="container">
            <div class="row">
                <div class="form-group col-md-6">
                    <p style="font-size:1.5em;"><strong>Origem: </strong>${carona.origemTxt}</p>
                    <p style="font-size:1.5em;"><strong>Destino: </strong>${carona.destinoTxt}</p>
                    <p style="font-size:1.5em;"><strong>Data da Viagem: </strong> <df:dataFormatada data="${carona.dataViagem}"/></p>
                    <p style="font-size:1.5em;"><strong>Horário de Saída: </strong>${carona.horaSaida}</p>
                    <p style="font-size:1.5em;"><strong>Distância: </strong>${carona.distancia}</p>
                    <p style="font-size:1.5em;"><strong>Duração da Viagem: </strong>${carona.duracao}</p>
                    <p style="font-size:1.5em;"><strong>Ajuda de Custo: </strong>${carona.ajudaCusto}</p>
                    <p style="font-size:1.5em;"><strong>Ofertante: </strong>${carona.usuario.nome}</p>
                    <p style="font-size:1.5em;"><strong>Telefone para contato: </strong>${carona.usuario.telefone}</p>
                </div>
                <div class="row col-md-6" id="map"></div>
            </div>
        </div>
    </body>
</html>
