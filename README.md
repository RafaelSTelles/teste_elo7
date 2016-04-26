# Teste elo7

Para soluciar o problema proposto pelo teste foi utilizado o servidor de aplicação [Wildfly 10.0.0.Final](http://wildfly.org/downloads/). Nele todo o ambiente JavaEE 7 já está disponível e é necessário apenas fazer algumas pequenas configurações de acordo com o serviço que você deseja utilizar. Nesse teste foi utilizado JAX-RS para desenvolver a api Rest. Além disso, foi utilizado também a SDK 8 do java, mas especificadamente a 1.8.0_73-b02.

Resumidamente foi utilizado:
- Wildfly 10.0.0.Final
- JDK 8
- TestNG

O projeto foi desenvolvido utilizando a IDE IntelliJ IDEA. Para realizar os testes do serviço rest foi utilizado um plugin do chrome chamado [ApplicationPostman - REST Client](https://chrome.google.com/webstore/detail/postman-rest-client/fdmmgilgnpjigdojojpjoooidkmcomcm).

Para realizar os testes, basta gerar um war do projeto utilizando o comando 'mvn clean install' (é necessário maven instalado). Após gerar o war, basta copiar o mesmo para o Wildfly e subir o sevidor de aplicação. Concluído o passo anterior com sucesso, basta fazer um post na url 'rest/sonda/comandar', encaminhando junto ao corpo da requisição uma String contendo os comandos para movimentar a sonda. O parâmetro tem o nome de 'comandos'.

Abaixo segue um exemplo de como foi utilizando usando o plugin citado acima.

https://snag.gy/WnQ7ei.jpg
