<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="ISO-8859-1">
        <title>Alterar Telefone</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    </head>

    <body>
        <jsp:include page="navbar.html"></jsp:include>

        <div class="container">
            <div class="row">

                <div class="col"></div>


                <div class="col">
                    <form action="alterartelefone" method="post">
                        <div class="form-row">
                            <input type="hidden" name="telefoneId" id="telefoneId" value="${tempTelefone.id}">
                            <div class="form-group">
                                <label for="nome">DDD</label> <input type="number" min="1" max="99" required class="form-control" name="ddd" id="ddd" value="${tempTelefone.ddd}" placeholder="Novo DDD">
                            </div>
                            <div class="form-group">
                                <label for="numero">Numero</label>
                                <input type="text" maxlength="9" required class="form-control" value="${tempTelefone.numero}" name="numero" id="numero" placeholder="Insira o seu novo Numero">
                            </div>
                            <div class="form-group">
                                <label for="tipo">Tipo de telefone</label> <select id="tipo" class="form-control" name="tipoTelefone">
								<option selected>RESIDENCIAL</option>
								<option>COMERCIAL</option>
							</select>
                                <br>
                                <input type="submit" name="submit" class="btn btn-primary form-control" value="Alterar Registro">
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
                $(':input[name=submit]').prop('disabled', false);
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