# 📝 TODO App

Aplicação de gerenciamento de tarefas desenvolvida em **Java 17**, com foco no aprimoramento dos fundamentos da linguagem e dos principais conceitos de **Programação Orientada a Objetos (POO)**.

O sistema permite criar, listar, atualizar, concluir e remover tarefas por meio de uma interface de linha de comando (CLI), utilizando **serialização de objetos Java** para persistência dos dados em arquivo.

---

## 🚀 Funcionalidades

* ✅ Criar tarefas
* 📋 Listar todas as tarefas
* 🏷️ Filtrar tarefas por status
* ▶️ Iniciar uma tarefa
* ✔️ Marcar tarefa como concluída
* 🗑️ Excluir tarefas
* 📂 Carregar tarefas automaticamente ao iniciar a aplicação
* ⚠️ Validação de regras de negócio
* 🛡️ Tratamento de exceções personalizadas
* 🧪 Testes unitários com JUnit 5

---

## 📖 Sobre o Projeto

O **TODO App** foi desenvolvido com o propósito de consolidar conhecimentos essenciais para o desenvolvimento profissional com Java.

Diferente de projetos baseados em Framework como Spring Boot, esta aplicação foi construída utilizando apenas recursos nativos da linguagem e bibliotecas padrão, permitindo um entendimento mais profundo dos mecanismos internos do Java antes da introdução de abstrações mais avançadas.

A aplicação é executada diretamente pelo terminal e mantém os dados persistidos localmente através de serialização de objetos.

---

## 🎯 Objetivos de Aprendizagem

Durante o desenvolvimento deste projeto foram praticados conceitos fundamentais como:

* Programação Orientada a Objetos (POO)
* Encapsulamento
* Herança e Polimorfismo
* Collections Framework
* Stream API
* Enums
* Manipulação de Arquivos
* Serialização de Objetos
* Tratamento de Exceções
* Testes Unitários
* Maven
* Git e Versionamento

---

## 🏗️ Arquitetura do Projeto

A aplicação foi organizada em camadas para promover separação de responsabilidades e facilitar a manutenção do código.

```text
data
└── tasks.dat
src
└── main
    └── java
        └── br.petersonaraujo.task_todo
            ├── exception
            ├── model
            ├── service
            └── ui
```

### Responsabilidades

| Camada        | Responsabilidade                            |
| ------------- | ------------------------------------------- |
| **model**     | Entidades e enums do domínio                |
| **service**   | Regras de negócio e manipulação das tarefas |
| **data**      | Persistência dos dados em arquivo           |
| **exception** | Exceções personalizadas da aplicação        |
| **ui**        | Interação com o usuário via console         |

---

## 🧠 Conceitos Praticados

### 🔹 Programação Orientada a Objetos

A entidade principal da aplicação é a classe `Task`, responsável por representar uma tarefa e seu ciclo de vida.

---

### 🔒 Encapsulamento

Os atributos das entidades são mantidos privados e acessados por métodos controlados, garantindo maior segurança e consistência dos dados.

```java
private String nome;
private Status descricao;
```

---

### 🔢 Enums

O status de cada tarefa é representado por um enum, evitando o uso de valores textuais espalhados pelo sistema.

```java
public enum Status {
    A_FAZER,
    EM_ANDAMENTO,
    CONCLUIDO
}
```

Benefícios:

* Evita erros de digitação
* Facilita validações
* Melhora a legibilidade do código

---

### 📦 Collections

As tarefas são armazenadas utilizando estruturas da Collections Framework.

```java
List<Task> tasks = new ArrayList<>();
```

Também são utilizadas operações de agrupamento e filtragem com base no status das tarefas.

---

### 🌊 Stream API

A Stream API é utilizada para consultas e filtros de forma declarativa.

```java
public List<Task> findByStatus(Status status) {
        return tasks.stream()
                .filter(task -> task.getStatus() == status)
                .toList();
}
```

---

### 💾 Persistência com Serialização

A persistência dos dados é realizada utilizando a serialização nativa do Java.

#### Fluxo de gravação

```text
Lista de Tasks
      ↓
ObjectOutputStream
      ↓
   tasks.dat
```

#### Fluxo de leitura

```text
   tasks.dat
      ↓
ObjectInputStream
      ↓
Lista de Tasks
```

Com isso, as tarefas permanecem disponíveis mesmo após o encerramento da aplicação.

---

### ⚠️ Tratamento de Exceções

A aplicação utiliza exceções personalizadas para representar erros de negócio e problemas de persistência.

```java
public class TaskException extends RuntimeException {

    public TaskException(String message) {
        super(message);
    }
}
```

Exemplos de validações implementadas:

* Data de término não pode estar no passado
* Tarefa não encontrada
* Erros durante leitura e gravação de arquivos

---

## 🧪 Testes Unitários

O projeto utiliza **JUnit 5** para garantir o correto funcionamento das regras de negócio.

Os testes cobrem cenários como:

* Criação de tarefas
* Mudança de status
* Exclusão de tarefas
* Validações de regras de negócio
* Tratamento de exceções

---

## 🛠️ Tecnologias Utilizadas

* Java 17
* Maven
* JUnit 5
* Java Serialization
* Git
* GitHub

---

## 📚 Aprendizados

Este projeto foi desenvolvido como parte da minha jornada de evolução como desenvolvedor Java, servindo como laboratório para praticar conceitos fundamentais da linguagem, boas práticas de programação e organização de código.

Além de reforçar conhecimentos em POO, o projeto proporcionou experiência com persistência de dados, testes automatizados, tratamento de exceções e estruturação de aplicações Java sem frameworks.

---

## 👨‍💻 Autor

**Peterson Araújo**

Desenvolvedor Java em constante evolução, apaixonado por tecnologia, boas práticas de desenvolvimento e construção de soluções backend.

🔗 GitHub: https://github.com/seu-usuario
