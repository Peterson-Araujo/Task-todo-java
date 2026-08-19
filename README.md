📝 TODO App

Aplicação de gerenciamento de tarefas desenvolvida em Java 17, criada com o objetivo de praticar e demonstrar conceitos fundamentais de Programação Orientada a Objetos (OOP) e recursos essenciais da linguagem Java.

A aplicação permite criar, listar, atualizar, concluir e excluir tarefas, utilizando serialização de objetos Java para persistência dos dados em arquivo.

📋 Sobre o Projeto

O TODO App é um sistema de gerenciamento de tarefas executado através de uma interface de linha de comando (console).

O projeto foi desenvolvido sem utilização de frameworks, como Spring Boot, para focar nos fundamentos da linguagem Java e compreender melhor seus principais recursos antes de introduzir camadas adicionais de abstração.

Principais funcionalidades
Criar tarefas
Listar todas as tarefas
Buscar tarefa por ID
Filtrar tarefas por status
Atualizar tarefas
Iniciar uma tarefa
Marcar tarefa como concluída
Excluir tarefas
Salvar tarefas em arquivo
Carregar tarefas ao iniciar a aplicação
Validação de operações
Tratamento de exceções
Testes unitários com JUnit 5
🎯 Objetivos do Projeto

O principal objetivo deste projeto é fortalecer os fundamentos necessários para o desenvolvimento profissional com Java.

Durante o desenvolvimento são praticados conceitos como:

Programação Orientada a Objetos
Encapsulamento
Classes e objetos
Herança
Polimorfismo
Enums
Builder Pattern
Collections
Stream API
Tratamento de exceções
Manipulação de arquivos
Serialização de objetos
Testes unitários
Maven
Git e commits semânticos
🧠 Conceitos Praticados
🔹 Programação Orientada a Objetos

A aplicação utiliza classes e objetos para representar o domínio da aplicação.

A principal entidade do projeto é a classe Task, responsável por representar uma tarefa e seu estado.

Task task = Task.builder()
        .title("Aprender Java")
        .description("Estudar fundamentos de OOP")
        .build();
🔒 Encapsulamento

Os atributos das classes são mantidos como private, permitindo controlar como o estado dos objetos pode ser acessado ou alterado.

Isso evita que outras partes da aplicação modifiquem diretamente os dados internos das entidades.

🔢 Enums

O status da tarefa é representado através de um enum:

public enum TaskStatus {

    TODO,
    IN_PROGRESS,
    DONE
}

Dessa forma, a aplicação evita trabalhar com valores String espalhados pelo código, como "TODO" ou "DONE".

🏗️ Builder Pattern

O padrão Builder é utilizado para facilitar a criação dos objetos Task.

Exemplo:

Task task = Task.builder()
        .title("Aprender Java")
        .description("Estudar Stream API")
        .build();

Além de deixar a criação dos objetos mais legível, esse padrão permite adicionar novos atributos posteriormente sem deixar os construtores excessivamente grandes.

📦 Collections

O projeto utiliza diferentes estruturas de dados disponíveis na API de Collections do Java.

Exemplos:

List<Task>
ArrayList<Task>
Map<TaskStatus, List<Task>>

Essas estruturas são utilizadas para armazenar, pesquisar, filtrar e agrupar tarefas.

🌊 Stream API

A Stream API é utilizada para realizar operações sobre as coleções.

Exemplo:

public List<Task> findByStatus(TaskStatus status) {

    return tasks.stream()
            .filter(task -> task.getStatus() == status)
            .toList();
}

Nesse caso, as tarefas são filtradas de acordo com seu status.

💾 Serialização

A persistência dos dados é realizada através da serialização nativa do Java.

As tarefas são armazenadas em um arquivo .dat.

Fluxo de gravação:

Lista de Tasks
      ↓
ObjectOutputStream
      ↓
   tasks.dat

Fluxo de leitura:

   tasks.dat
      ↓
ObjectInputStream
      ↓
Lista de Tasks

Dessa forma, as tarefas continuam disponíveis mesmo depois que a aplicação é encerrada.

⚠️ Tratamento de Exceções

O projeto possui uma exceção personalizada:

public class TaskException extends RuntimeException {

    public TaskException(String message) {
        super(message);
    }
}

Ela é utilizada para representar erros relacionados às regras da aplicação e à persistência dos dados.
