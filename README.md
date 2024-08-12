<p align="center">
  <a href="https://floodrisk.fly.dev/swagger-ui/index.html#"><img src="https://github.com/user-attachments/assets/46d00ea7-63c5-42aa-8432-5187796e2dd7"/></a>
</p>

<h4 align="center">
  🌊 FloodRiskAPI - API de áreas de riscos de alagamento no Brasil ⚠️
</h4>

<p align="center">
 <a href="#-sobre">Sobre</a> •
 <a href="#endpoints">Endpoints</a> •
 <a href="#configuração">Configuração</a> •
 <a href="#%EF%B8%8F-autores">Autores</a>
</p>

## 💻 Sobre

Este repositório foi criado para colocar o projeto "Áreas de risco de alagamentos no Brasil", que é uma API desenvolvida para gerenciar informações sobre áreas de risco de alagamentos. 

A API permite consultar, adicionar, atualizar e remover áreas de risco de alagamentos.

<h3 align="center">
⚙️ Tecnologias utilizadas

<p>&nbsp;</p>
  <img src="https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white"/>
  <img src="https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white"/>
  <img src="https://img.shields.io/badge/Gradle-02303A.svg?style=for-the-badge&logo=Gradle&logoColor=white"/>
  <img src="https://img.shields.io/badge/mongodb-%47A248.svg?style=for-the-badge&logo=mongodb&logoColor=white"/>
  <img src="https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white"/>
  <img src="https://img.shields.io/badge/junit5-%25A162.svg?style=for-the-badge&logo=junit5&logoColor=white"/>
  <img src="https://img.shields.io/badge/swagger-%85EA2D.svg?style=for-the-badge&logo=swagger&logoColor=white"/>
</h3>

---

## Endpoints

A aplicação expõe os seguintes endpoints:

### 1. **Obter uma área de risco de alagamento por ID**

- **Método:** GET
- **URL:** `/api/areas-riscos-alagamento/{id}`
- **Descrição:** Retorna os detalhes de uma área de risco de alagamento específica identificada pelo seu ID.

### 2. **Atualizar uma área de risco de alagamento por ID**

- **Método:** PUT
- **URL:** `/api/areas-riscos-alagamento/{id}`
- **Descrição:** Atualiza os detalhes de uma área de risco de alagamento específica identificada pelo seu ID.

### 3. **Remover uma área de risco de alagamento por ID**

- **Método:** DELETE
- **URL:** `/api/areas-riscos-alagamento/{id}`
- **Descrição:** Remove uma área de risco de alagamento específica identificada pelo seu ID.

### 4. **Obter todas as áreas de risco de alagamento**

- **Método:** GET
- **URL:** `/api/areas-riscos-alagamento`
- **Descrição:** Retorna uma lista de todas as áreas de risco de alagamento cadastradas na aplicação.

### 5. **Adicionar uma nova área de risco de alagamento**

- **Método:** POST
- **URL:** `/api/areas-riscos-alagamento`
- **Descrição:** Adiciona uma nova área de risco de alagamento à aplicação.

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

---

## ✒️ Autores

| [<img src="https://avatars.githubusercontent.com/u/75590326?v=4" width=115 > <br> <sub> Bruno Machado </sub>](https://github.com/brunomdrrosa) | [<img src="https://avatars.githubusercontent.com/u/85200529?v=4" width=115 > <br> <sub> Rian Oliveira </sub>](https://github.com/riandeoliveira) |
| :--------------------------------------------------------------------------------------------------------------------------------------------: | :---------------------------------------------------------------------------------------------------------------------------------------: |

<h2 >Entre em contato 🤙🏽</h2>

<div align="center">
<a href="https://linkedin.com/in/bruno-machado-da-rosa/" target="_blank"><img src="https://img.shields.io/badge/Bruno Machado da Rosa-0077B5?style=for-the-badge&logo=linkedin&logoColor=white" alt=""></a>
<a href="mailto:brunomdr46@gmail.com" target="_blank"><img src="https://img.shields.io/badge/brunomdr46@gmail.com-D14836?style=for-the-badge&logo=gmail&logoColor=white" alt=""></a>
</div>
