<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="ISO-8859-1">
            <title>Todos os Telefones</title>
            <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
        </head>

        <body>
            <jsp:include page="navbar.html"></jsp:include>

            <div class="container" style="min-width: 100%;">

                <div class="table-responsive">

                    <table class="text-center table table-bordered table-striped" style="border-radius: 3em;">
                        <thead>
                            <tr>
                                <th scope="col">ID</th>
                                <th scope="col">ID Usuario</th>
                                <th scope="col">DDD</th>
                                <th scope="col">Numero</th>
                                <th scope="col">Tipo</th>
                                <th scope="col">Remover</th>
                                <th scope="col">Alterar</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="telefone" items="${listaTelefone}">
                                <tr>
                                    <th scope="row">${telefone.id}</th>
                                    <td>${telefone.usuarioId}</td>
                                    <td>${telefone.ddd}</td>
                                    <td>${telefone.numero}</td>
                                    <td>${telefone.tipo}</td>
                                    <td><a href="removertelefone?idTelefone=${telefone.id}" onclick="return confirm('Deseja deletar o numero ${telefone.numero}?')">Remover</a></td>
                                    <td><a href="alterartelefone?id=${telefone.id}">Alterar</a></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>

                </div>
            </div>



            <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-fQybjgWLrvvRgtW6bFlB7jaZrFsaBXjsOMm/tB9LTS58ONXgqbR9W8oWht/amnpF" crossorigin="anonymous"></script>
        </body>

        </html>