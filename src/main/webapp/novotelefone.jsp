<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="ISO-8859-1">
            <title>Cadastrar Novo Telefone</title>
            <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
            <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        </head>

        <body>
            <jsp:include page="navbar.html"></jsp:include>

            <div class="container">

                <div class="row">

                    <div class="col"></div>

                    <div class="col-8">
                        <form action="telefone" method="post">
                            <input type="hidden" name="idUsuario" value="${sessionScope.idUsuario}">

                            <div class="form-row">
                                <div class="form-group col">
                                    <label for="ddd">DDD</label>
                                    <input type="number" required class="form-control" name="ddd" id="ddd" min="1" max="99" placeholder="DDD">
                                </div>

                                <div class="form-group col">
                                    <label for="numero">Número</label>
                                    <input type="text" required class="form-control" id="numero" name="numero" minlength="9" maxlength="9" placeholder="Insira o seu Número">
                                    <br>
                                    <br>
                                    <input type="submit" id="submit" name="submit" class="btn btn-primary form-control" value="Registrar">
                                </div>

                                <div class="form-group col">
                                    <label for="tipo">Tipo de telefone</label>
                                    <select id="tipo" class="form-control" name="tipoTelefone">
										<option selected>RESIDENCIAL</option>
										<option>COMERCIAL</option>
									</select>
                                </div>
                            </div>


                        </form>
                    </div>

                    <div class="col"></div>

                </div>

            </div>

            <script>
                //Script para aceitar somente numeros no campo numero
                $('input[name="numero"]').on('input', function(event) {
                    this.value = this.value.replace(/[^0-9]/g, '');
                });

                $(document).ready(function() {
                    // desativa o botao submit do form 
                    $(':input[name=submit]').prop('disabled', true);
                    // toda vez que apertar alguma tecla, dentro de um tipo texto
                    $('input[type="text"]').keyup(function() {
                        //verifica tamanho do campo numero
                        var numero = $('input[name=numero]').val().length;
                        if (numero != 9) {
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