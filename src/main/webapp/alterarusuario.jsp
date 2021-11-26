<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="ISO-8859-1">
        <title>Alterar Usuário</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
    </head>

    <body>
        <jsp:include page="navbar.html"></jsp:include>

        <div class="container">
            <div class="row">

                <div class="col"></div>


                <div class="col">
                    <form action="alterarusuario" method="post">
                        <div class="form-row">
                            <input type="hidden" name="id" value="${tempUsuario.id}">
                            <div class="form-group">
                                <label for="nome">Nome</label>
                                <input type="text" required class="form-control" name="usuario" id="usuario" value="${tempUsuario.usuario}" placeholder="Novo Nome">
                            </div>
                            <div class="form-group">
                                <label for="email">Email</label>
                                <input type="email" required class="form-control" value="${tempUsuario.email}" name="email" placeholder="Insira o seu novo email">
                            </div>
                            <div class="form-group">
                                <label for="senha">Senha</label>
                                <input type="password" name="senha" value="${tempUsuario.senha}" required class="form-control" placeholder="Insira a sua nova senha">
                                <br>
                                <input type="submit" class="btn btn-primary form-control" value="Alterar Registro">
                            </div>
                        </div>
                    </form>
                </div>

                <div class="col"></div>

            </div>

        </div>


        <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-fQybjgWLrvvRgtW6bFlB7jaZrFsaBXjsOMm/tB9LTS58ONXgqbR9W8oWht/amnpF" crossorigin="anonymous"></script>
    </body>

    </html>