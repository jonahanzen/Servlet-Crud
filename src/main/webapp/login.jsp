<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="utf-8" />
        <title>Login</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    </head>

    <body>

        <div class="container">

            <div class="row">

                <div class="col"></div>


                <!-- Div para o registro-->
                <div class=" col">
                    <div id="registrar-usuario">
                        <h4 class="text-danger text-center">Registrar novo Usuário</h4>

                        <form action="login" method="post">

                            <label for="usuario" class="text-primary text-center col-form-label">Usuario</label>
                            <input type="text" id="usuario-registro" required class="form-control" name="usuario" placeholder="Insira o seu usuário">

                            <label for="senha" class="text-primary col-form-label">Senha</label>
                            <input type="password" id="senha-registro" name="senha" required class="form-control" placeholder="Insira a sua senha">


                            <label for="email" class="text-primary col-form-label">Email</label>
                            <input type="email" id="email-registro" required class="form-control" name="email" placeholder="Insira o seu email">

                            <input type="submit" id="submit-registro" name="submit" class="btn btn-primary form-control" style="margin-top: 4rem;" value="Registrar">

                        </form>
                    </div>

                    <!-- Div para o login-->
                    <div id="login-usuario" style="display: none;">

                        <form action="login" method="post">
                            <h4 class="text-danger text-center">Login</h4>
                            <label for="usuario" class="text-primary text-center col-form-label">Usuario</label>
                            <input type="text" id="usuario-login" required class="form-control" name="usuario" placeholder="Insira o seu usuário">
                            
                            <label for="senha" class="text-primary col-form-label">Senha</label>
                            <input type="password" id="senha-login" name="senha" required class="form-control" placeholder="Insira a sua senha">
                            
                            <input type="submit" id="submit-login" class="btn btn-primary form-control" name="submit" style="margin-top: 4rem;" value="Login">
                        </form>
                    </div>

                    <div>
                        <button style="margin-top: 1rem;" id="botao-login-registro" class="btn btn-danger form-control">Já Possuo Cadastro</button>
                    </div>
                </div>


                <div class="col"></div>


            </div>


        </div>


        <script>
            //Função para alterar botão de registro/login
            //Faz aparecer/desaparecer div de login/cadastro
            const registrarUsuario = document.getElementById("registrar-usuario");
            const loginUsuario = document.getElementById("login-usuario");
            const botaoLoginRegistro = document
                .getElementById("botao-login-registro");

            $(botaoLoginRegistro).click(function() {
                if (botaoLoginRegistro.innerText == "Novo Cadastro") {
                    botaoLoginRegistro.innerText = "Já Possuo Cadastro";
                    registrarUsuario.style.display = "block";
                    loginUsuario.style.display = "none";
                } else {
                    registrarUsuario.style.display = "none";
                    loginUsuario.style.display = "block";
                    botaoLoginRegistro.innerText = "Novo Cadastro";
                };

            });

            //Função para aceitar somente números em inputs do tipo tel(telefone)
            $('input[type="tel" ]').on('input', function(event) {
                this.value = this.value.replace(/[^0-9]/g, '');
            });

			// script para o form de registro 
            $(document).ready(function() {
                // desativa o botao submit do form 
                $(':input[id=submit-registro]').prop('disabled', true);
                // toda vez que apertar alguma tecla, dentro de um input
                $(':input').keyup(function() {
                    //verifica tamanho do do campo usuario e senha
                    let usuario = $('input[id=usuario-registro]').val().length;
                    let senha = $('input[id=senha-registro]').val().length;
                    if (usuario < 1 || senha < 1) {
                        // desabilita (continua desabilitado) 
                        $(':input[id=submit-registro]').prop('disabled', true);
                    } else {
                        // habilita 
                        $(':input[id=submit-registro]').prop('disabled', false);
                    }
                });
            });

        	// script para o form de login 
            $(document).ready(function() {
                // desativa o botao submit do form 
                $(':input[id=submit-login]').prop('disabled', true);
                // toda vez que apertar alguma tecla, dentro de um input
                $(':input').keyup(function() {
                    //verifica tamanho do do campo usuario e senha
                    let usuario = $('input[id=usuario-login]').val().length;
                    let senha = $('input[id=senha-login]').val().length;
                    if (usuario < 1 || senha < 1) {
                        // desabilita (continua desabilitado) 
                        $(':input[id=submit-login]').prop('disabled', true);
                    } else {
                        // habilita 
                        $(':input[id=submit-login]').prop('disabled', false);
                    }
                });
            });

       
        </script>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-fQybjgWLrvvRgtW6bFlB7jaZrFsaBXjsOMm/tB9LTS58ONXgqbR9W8oWht/amnpF" crossorigin="anonymous"></script>
    </body>

    </html>