# gerenciaTarefas
Desafio Gerenciamento de Tarefas

Um aplicativo simples para gerenciamento de tarefas utilizando Spring Boot com MongoDB e programação reativa.

Pré-requisitos
Java 17 ou superior
Maven 3.9.8 ou superior
MongoDB instalado e rodando localmente (ou configurado em outro ambiente)

Clonar repositorios

git clone https://github.com/RenatoFonsecaLima/gerenciadetarefas.git
cd gerenciatarefas

Configuração do MongoDB

MongoDB localmente 
Compilar e executar com Maven

APIs Disponíveis
POST /api/tarefas: Cria uma nova tarefa.
GET /api/tarefas: Retorna todas as tarefas.
GET /api/tarefas/{id}: Retorna uma tarefa específica pelo ID.
PUT /api/tarefas/{id}: Atualiza uma tarefa existente pelo ID.
DELETE /api/tarefas/{id}: Deleta uma tarefa pelo ID.
GET /api/tarefas/status/{realizada}: Retorna tarefas filtradas por status de realização (true ou false).


Estrutura do Projeto
GerenciaTarefasController: Controlador REST que define os endpoints da API.
GerenciaTarefas: Entidade que representa uma tarefa com atributos como nome, descrição, realizada (booleano) e prioridade.
GerenciaTarefasRepository: Interface do Spring Data MongoDB para acesso ao banco de dados.
GerenciaService: Serviço que encapsula a lógica de negócio para gerenciamento de tarefas, usando uma lista em memória e o repositório MongoDB.


Dependências Principais
Spring Boot Starter Web
Spring Boot Starter Data MongoDB
Spring Boot Starter WebFlux
Reactor Core
