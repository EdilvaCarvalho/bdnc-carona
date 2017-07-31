<%-- 
    Document   : caronasPesquisadas
    Created on : 31/07/2017, 17:05:25
    Author     : Edilva
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="df" uri="/WEB-INF/myTags.tld"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div><jsp:include page="paginaDoUsuario.jsp"/></div>
        <br><br><br>
        <div class="container text-center">
            <h2>Caronas</h2>
            <br>
        </div>
        <div class="container">
            <div class="row">
                <div class="form-group col-md-12">
                    <table class="table table-striped">
                        <tr class="row">
                            <td>ORIGEM</td>
                            <td>DESTINO</td>
                            <td>DATA DA VIAGEM</td>
                            <td>HORÁRIO DE SAÍDA</td>
                            <td>DETALHAR</td>
                        </tr>
                        <c:forEach var="carona" items="${caronas}">
                            <tr class="row">
                                <td>${carona.origemTxt}</td>
                                <td>${carona.destinoTxt}</td>
                                <td><df:dataFormatada data="${carona.dataViagem}"/></td>
                                <td>${carona.horaSaida}</td>
                                <td>
                                    <a id="btnCadastrar" class="btn btn-primary btn-block" href="Controller?command=DetalharCarona&id=${carona.id}">Detalhar</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>
