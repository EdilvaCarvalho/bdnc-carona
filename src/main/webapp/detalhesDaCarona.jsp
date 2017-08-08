<%-- 
    Document   : detalhesDaCarona
    Created on : 31/07/2017, 13:15:08
    Author     : Edilva
--%>
<%@page import="org.apache.taglibs.standard.lang.jstl.test.PageContextImpl"%>
<%@page import="br.edu.ifpb.bdnc.projeto.entidades.Carona"%>
<%@page import="com.sun.xml.internal.ws.client.RequestContext"%>
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
                    <%
                        Carona carona = (Carona) request.getAttribute("carona");
                        double lat1 = carona.getOrigem().getCoordinate().x;
                        double lng1 = carona.getOrigem().getCoordinate().y;
                        double lat2 = carona.getDestino().getCoordinate().x;
                        double lng2 = carona.getDestino().getCoordinate().y;
                        request.setAttribute("lat1", lat1);
                        request.setAttribute("lng1", lng1);
                        request.setAttribute("lat2", lat2);
                        request.setAttribute("lng2", lng2);
                    %>
                    <div class="form-group col-md-6">
                        <input type="text" class="form-control" name="origem" id="origem" value="${lat1} ${lng1}" style="display: none;">
                    </div>
                    <div class="form-group col-md-6">
                        <input type="text" class="form-control" name="destino" id="destino" value="${lat2} ${lng2}" style="display: none;">
                    </div>
                </div>
                <div class="row col-md-6" id="map"></div>
            </div>
        </div>
        <script>
            function initMap() {
                var directionsService = new google.maps.DirectionsService;
                var directionsDisplay = new google.maps.DirectionsRenderer;
                var map = new google.maps.Map(document.getElementById('map'), {
                    zoom: 7,
                    center: {lat: ${lat1}, lng: ${lng1}}
                });
                directionsDisplay.setMap(map);

                //var onChangeHandler = function () {
                    calculateAndDisplayRoute(directionsService, directionsDisplay);
                //};
                //document.getElementById('origem').addEventListener('change', onChangeHandler);
                //document.getElementById('destino').addEventListener('change', onChangeHandler);
            }

            function calculateAndDisplayRoute(directionsService, directionsDisplay) {
                directionsService.route({
                    origin: document.getElementById('origem').value,
                    destination: document.getElementById('destino').value,
                    travelMode: 'DRIVING'
                }, function (response, status) {
                    if (status === 'OK') {
                        directionsDisplay.setDirections(response);
                    } else {
                        window.alert('Directions request failed due to ' + status);
                    }
                });
            }
        </script>
        <script async defer
                src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAtOMx_YlkuwCos4m2Vxcm9Y1nS-tN6dZ0&callback=initMap">
        </script>
    </body>
</html>
