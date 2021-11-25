<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

		<!DOCTYPE html>
		<html>

		<head>
			<meta charset="ISO-8859-1">
			<title>Todos os Usuários</title>
			<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css"
				integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn"
				crossorigin="anonymous">
		</head>

		<body>
			<jsp:include page="navbar.html"></jsp:include>


			<div class="container" style="min-width: 100%;">



				<table class="text-center table table-bordered table-striped">
					<thead>
						<tr>
							<th scope="col">ID</th>
							<th scope="col">Usuario</th>
							<th scope="col">Email</th>
							<th scope="col">Remover</th>
							<th scope="col">Alterar</th>
						</tr>
					</thead>
					<tbody>
						<!-- se nao funcionar com usuario mudar o nome "usuario" pois é o nome da variavel usuario -->
						<c:forEach var="tempUsuario" items="${listaUsuario}">
							<tr>
								<th scope="row">
									<c:out value="${tempUsuario.id}" />
								</th>
								<td>${tempUsuario.usuario}</td>
								<td>${tempUsuario.email}</td>
								<td><a href="removerusuario?id=${tempUsuario.id}" onclick="return confirm('Deseja deletar o Usuario ${tempUsuario.usuario}?')">Remover</a></td>
								<td><a href="alterarusuario?id=${tempUsuario.id}">Alterar</a></td>
							</tr>

						</c:forEach>

					</tbody>
				</table>

			</div>














			<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"
				integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
				crossorigin="anonymous"></script>
			<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"
				integrity="sha384-fQybjgWLrvvRgtW6bFlB7jaZrFsaBXjsOMm/tB9LTS58ONXgqbR9W8oWht/amnpF"
				crossorigin="anonymous"></script>
		</body>

		</html>