<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="ISO-8859-1">
        <title>Cadastrar Novo Usuário</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
   		 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    </head>

    <body>
        <jsp:include page="navbar.html"></jsp:include>

        <div class="container">
            <div class="row">

                <div class="col"></div>

                <div class="col">
                    <form action="usuario" method="post">
                        <div class="form-row">
                            <div class="form-group">
                                <label for="nome">Nome</label>
                                <input type="text" name="nome" required class="form-control" maxlength="50" id="usuario" placeholder="Nome Usuário">
                            </div>
                            <div class="form-group">
                                <label for="email">Email</label>
                                <input type="email" name="email" required class="form-control" placeholder="Insira o seu email">
                            </div>
                            <div class="form-group">
                                <label for="senha">Senha</label>
                                <input type="password" name="senha" required class="form-control" placeholder="Insira a sua senha">
                                <br>
                                <input type="submit" name="submit" class="btn btn-primary form-control" value="Registrar">
                            </div>
                        </div>
                    </form>
                </div>

                <div class="col"></div>

            </div>

        </div>
        
        <script>
        $(document).ready(function() {
            // desativa o botao submit do form 
            $(':input[name=submit]').prop('disabled', true);
            // toda vez que apertar alguma tecla, dentro de um input
            $(':input').keyup(function() {
                //verifica tamanho do campo nome e senha
                var nome = $('input[name=nome]').val().length;
                var senha = $('input[name=senha]').val().length;
                if ( nome < 1 || senha < 1) {
                    // desabilita (continua desabilitado) 
                    $(':input[name=submit]').prop('disabled', true);
                } else {
                    // habilita 
                    $(':input[name=submit]').prop('disabled', false);
                }
            });
        });
        </script>


        <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-fQybjgWLrvvRgtW6bFlB7jaZrFsaBXjsOMm/tB9LTS58ONXgqbR9W8oWht/amnpF" crossorigin="anonymous"></script>
    </body>

    </html>