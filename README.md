##   Exercicio Servlet Crud

Projeto de exercício em JavaSE/JavaEE utilizando Servlet, o projeto conta com login e um Crud de Usuário e Telefone.

###  Ferramentas utilizadas

 Java SE 8, Java EE 8, PostgreSQL, Maven

Por ser um projeto menor, eu optei por utilizar um design que lembra microsserviços, mas no geral o projeto utiliza a arquitetura MVC, tendo então as classes representando as entidades, os repositórios que são os métodos com o banco de dados, e os Controllers que lidam com o fluxo de dados, sendo então as servlets que direcionam para páginas e chamam os devidos métodos.  Neste primeiro momento, o projeto não conta com validações extras, porém validações adicionais poderiam facilmente ser criadas com a criação de uma camada de service, que seria responsável por implementar toda a lógica antes de propriamente chamar os repositórios. 
