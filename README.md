# Floodrisk

## Desenvolvimento

Para iniciar a aplicação, use o comando `docker compose up` . O aplicativo se conectará aos serviços contidos. É necessário que
[Docker](https://www.docker.com/get-started/) esteja disponível no sistema atual.

Durante o desenvolvimento, é recomendável usar o perfil `local`. No IntelliJ, adicione `-Dspring.profiles.active=local` 
nas opções da VM da Configuração de Execução após habilitar essa propriedade em "Edit configurations". Crie seu próprio 
arquivo `application-local.yml` para substituir as configurações para desenvolvimento.

O Lombok deve ser suportado pelo seu IDE. Para o IntelliJ, instale o plugin Lombok e habilite o processamento de anotações -
[learn more](https://bootify.io/next-steps/spring-boot-with-lombok.html).

Após iniciar a aplicação, ela estará acessível em `localhost:8080`.

## Docker Compose e MongoDB

Este projeto utiliza o Docker Compose para iniciar uma imagem do MongoDB localmente. Certifique-se de ter o Docker 
instalado e execute docker compose up para iniciar a aplicação e o banco de dados MongoDB. A imagem do MongoDB é 
necessária para o funcionamento do projeto localmente.

## Leituras adicionais

* [Gradle user manual](https://docs.gradle.org/)  
* [Spring Boot reference](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)  
* [Spring Data MongoDB reference](https://docs.spring.io/spring-data/mongodb/reference/)
