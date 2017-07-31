<%-- 
    Document   : oferecerCarona
    Created on : 12/07/2017, 11:31:19
    Author     : Edilva
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="df" uri="/WEB-INF/myTags.tld"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src='http://code.jquery.com/jquery-2.1.3.min.js'></script>
        <script src="js/jquery.maskedinput.min.js" type="text/javascript"></script>
        <script src="js/funcoes.js"></script>
        <script src="js/mapa.js"></script>
        <title>Ofertar Carona</title>
    </head>
    <body>
        <div><jsp:include page="paginaDoUsuario.jsp"/></div>
        <br><br><br>
        <div class="container text-center">
            <h2>Dados da Carona</h2>
            <br>
        </div>
        <div class="container">
            <div class="row">
                <div class="col-md-6">
                    <form method="post" id="oferecerCarona" action="Controller?command=EditarCarona&id=${carona.id}">
                        <div class="form-group col-md-12">
                            <label for="origem">Origem: </label>
                            <input type="text" class="form-control" name="origem" id="origem" autofocus required value="${carona.origemTxt}">
                        </div>
                        <div class="form-group col-md-6">
                            <input type="text" class="form-control" name="lat1" id="lat1" required>
                        </div>
                        <div class="form-group col-md-6">
                            <input type="text" class="form-control" name="lng1" id="lng1" required>
                        </div>
                        <div class="form-group col-md-12">
                            <label for="destino">Destino: </label>
                            <input type="text" class="form-control" name="destino" id="destino" required value="${carona.destinoTxt}">
                        </div>
                        <div class="form-group col-md-6">
                            <input type="text" class="form-control" name="lat2" id="lat2" required>
                        </div>
                        <div class="form-group col-md-6">
                            <input type="text" class="form-control" name="lng2" id="lng2" required>
                        </div>
                        <div class="form-group col-md-12">
                            <input type="button" value="Calcular distância e duração" onclick="CalculaDistancia()" id="btnCadastrar" class="btn btn-block"/>
                        </div>
                        <div class="form-group col-md-6">
                            <label for="distancia">Distância: </label>
                            <input type="text" class="form-control" name="distancia" id="distancia" required placeholder="Click em calcular distância e duração." title="Click em calcular distância e duração.">
                        </div>
                        <div class="form-group col-md-6">
                            <label for="duracao">Duração: </label>
                            <input type="text" class="form-control" name="duracao" id="duracao" required placeholder="Click em calcular distância e duração." title="Click em calcular distância e duração.">
                        </div>
                        <div class="form-group col-md-12">
                            <label for="dataNasc">Data de Viagem: </label>
                            <input type="text" class="form-control" name="dataViagem" value="<df:dataFormatada data="${carona.dataViagem}"/>" id="dataNasc" required>
                        </div>
                        <div class="form-group col-md-12">
                            <label for="horaSaida">Hora de saída: </label>
                            <input type="text" class="form-control" name="horaSaida" id="horaSaida" value="${carona.horaSaida}" pattern="[0-9]{2}:[0-9]{2}$" placeholder="HH:mm" maxlength="5" title="HH:mm" required>
                        </div>
                        <div class="form-group col-md-12">
                            <label for="custo">Ajuda de custo: </label>
                            <input type="text" class="form-control" name="custo" id="custo" value="${carona.ajudaCusto}" required>
                        </div>
                        <input type="text" class="form-control" name="lat1" id="lat1" style="display: none;">
                        <input type="text" class="form-control" name="lng1" id="lng1" style="display: none;">
                        <input type="text" class="form-control" name="lat2" id="lat2" style="display: none;">
                        <input type="text" class="form-control" name="lng2" id="lng2" style="display: none;">
                        <div class="form-group col-md-12">
                            <input id="btnCadastrar" type="submit" value="Atualizar" class="btn btn-block" onclick="codeAddress()">
                        </div>
                    </form>
                </div>
                <div class="row col-md-6" id="map">
                </div>
            </div>
        </div>
        <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&signed_in=true&libraries=places&key=AIzaSyAtOMx_YlkuwCos4m2Vxcm9Y1nS-tN6dZ0&callback=initMap">
        </script>
        <script type="text/javascript">
            google.maps.event.addDomListener(window, 'load', initialize);
            //google.maps.event.addDomListener(window, 'load', initMap);

            function initialize() {
                var autocomplete1 = new google.maps.places.Autocomplete(document.getElementById('origem'));
                google.maps.event.addListener(autocomplete1, 'place_changed', function () {
                    // Get the place details from the autocomplete object.
                    var place1 = autocomplete1.getPlace();

                    document.getElementById('lat1').value = place1.geometry.location.lat();
                    document.getElementById('lng1').value = place1.geometry.location.lng();
                });

                var autocomplete2 = new google.maps.places.Autocomplete(document.getElementById('destino'));
                google.maps.event.addListener(autocomplete2, 'place_changed', function () {
                    // Get the place details from the autocomplete object.
                    var place2 = autocomplete2.getPlace();

                    document.getElementById('lat2').value = place2.geometry.location.lat();
                    document.getElementById('lng2').value = place2.geometry.location.lng();
                });
            }

            function initMap() {
                var directionsService = new google.maps.DirectionsService;
                var directionsDisplay = new google.maps.DirectionsRenderer;
                var map = new google.maps.Map(document.getElementById('map'), {
                    zoom: 6,
                    center: {lat: -6.8897, lng: -38.5612}
                });
                directionsDisplay.setMap(map);

                var onChangeHandler = function () {
                    calculateAndDisplayRoute(directionsService, directionsDisplay);
                };
                document.getElementById('origem').addEventListener('change', onChangeHandler);
                document.getElementById('destino').addEventListener('change', onChangeHandler);

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

            function CalculaDistancia() {
                $('#litResultado').html('Aguarde...');
                //Instanciar o DistanceMatrixService
                var service = new google.maps.DistanceMatrixService();
                //executar o DistanceMatrixService
                service.getDistanceMatrix(
                        {
                            //Origem
                            origins: [$("#origem").val()],
                            //Destino
                            destinations: [$("#destino").val()],
                            //Modo (DRIVING | WALKING | BICYCLING)
                            travelMode: google.maps.TravelMode.DRIVING,
                            //Sistema de medida (METRIC | IMPERIAL)
                            unitSystem: google.maps.UnitSystem.METRIC
                                    //Vai chamar o callback
                        }, callback);
            }
            //Tratar o retorno do DistanceMatrixService
            function callback(response, status) {
                //Verificar o Status
                if (status != google.maps.DistanceMatrixStatus.OK)
                    //Se o status não for "OK"
                    $('#litResultado').html(status);
                else {

                    document.getElementById('distancia').value = response.rows[0].elements[0].distance.text;
                    document.getElementById('duracao').value = response.rows[0].elements[0].duration.text;

                }
            }

        </script>
    </body>
</html>
