# 🌊🛑 FloodRisk API

## Descrição

A aplicação "Áreas de Risco de Enchentes" é uma API desenvolvida para gerenciar informações sobre áreas de risco de enchentes. 
A API permite consultar, adicionar, atualizar e remover áreas de risco de enchentes.

O projeto está hospedado no [Fly.io](https://floodrisk.fly.dev/swagger-ui/index.html#/).

## Tecnologias

- **Java:** 17
- **Gradle:** 8.8
- **Spring Boot:** 3.3.2

## Endpoints

A aplicação expõe os seguintes endpoints:

### 1. **Obter uma área de risco de enchente por ID**

- **Método:** GET
- **URL:** `/api/areas-riscos-enchente/{id}`
- **Descrição:** Retorna os detalhes de uma área de risco de enchente específica identificada pelo seu ID.

### 2. **Atualizar uma área de risco de enchente por ID**

- **Método:** PUT
- **URL:** `/api/areas-riscos-enchente/{id}`
- **Descrição:** Atualiza os detalhes de uma área de risco de enchente específica identificada pelo seu ID.

### 3. **Remover uma área de risco de enchente por ID**

- **Método:** DELETE
- **URL:** `/api/areas-riscos-enchente/{id}`
- **Descrição:** Remove uma área de risco de enchente específica identificada pelo seu ID.

### 4. **Obter todas as áreas de risco de enchente**

- **Método:** GET
- **URL:** `/api/areas-riscos-enchente`
- **Descrição:** Retorna uma lista de todas as áreas de risco de enchente cadastradas na aplicação.

### 5. **Adicionar uma nova área de risco de enchente**

- **Método:** POST
- **URL:** `/api/areas-riscos-enchente`
- **Descrição:** Adiciona uma nova área de risco de enchente à aplicação.

## Configuração

Para configurar o projeto localmente, siga as instruções abaixo:

1. **Clone o Repositório**

   ```bash
   git clone https://github.com/Flood-Risk/FloodRiskAPI.git
   ```

2. **Navegue até o Diretório do Projeto** 📂

   ```bash
   cd FloodRiskAPI
   ```

3. **Configure as Dependências** 🔧

   Certifique-se de ter o Gradle 8.8 e o JDK 17 instalados. Você pode usar o Gradle Wrapper incluído no projeto:

   ```bash
   ./gradlew build
    ```

4. **Defina as Variáveis de Ambiente** 🌐

   Defina as seguintes variáveis de ambiente para configurar o acesso ao banco de dados MongoDB:

   ```bash
   MONGODB_USER=seu_usuario
   MONGODB_PASSWORD=sua_senha
   MONGODB_DATABASE=nome_do_banco
   ```
   
5. **Execute o Projeto** 🚀

    ```bash
   ./gradlew bootRun
   ```

   O aplicativo será iniciado e estará disponível na URL padrão: http://localhost:8080.

## Documentação da API

A documentação da API está disponível no Swagger UI. Acesse a URL do Swagger UI para explorar os endpoints e testar 
as operações da API.

http://localhost:8080/swagger-ui.html

## Contribuição

Contribuições são bem-vindas! Se você deseja colaborar, por favor, faça um fork do repositório e envie um pull request com suas melhorias ou correções.

## Licença

Este projeto está licenciado sob a [Licença MIT](https://github.com/Flood-Risk/FloodRiskAPI/blob/main/LICENSE).
