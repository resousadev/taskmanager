# Task Manager

Um aplicativo de gerenciamento de tarefas desenvolvido com **Spring Boot** e **Java**, seguindo uma arquitetura em camadas bem organizada e clara.

## 🎯 Objetivo

O Task Manager é uma aplicação educacional que oferece funcionalidades básicas de gerenciamento de tarefas, demonstrando boas práticas de arquitetura de software e organização de código em camadas.

## 🏗️ Arquitetura

O projeto segue uma arquitetura em camadas:

```
dio.taskmanager/
├── TaskmanagerApplication      # Classe principal da aplicação
├── application/
│   └── TaskFacade              # Camada de aplicação
├── domain/
│   ├── Task                     # Entidade de domínio
│   ├── TaskId                   # Identificador único da tarefa
│   ├── TaskStatus               # Enum com status da tarefa
│   └── TaskRepository           # Interface do repositório
└── insfrastructure/
    └── repository/              # Implementações de repositório
        └── InMemoryTaskRepository
```

## 🚀 Funcionalidades

- ✅ **Criar Tarefas** - Crie novas tarefas com título e descrição opcional
- ✅ **Listar Tarefas** - Visualize todas as tarefas cadastradas
- ✅ **Buscar Tarefa** - Procure uma tarefa específica por ID
- ✅ **Marcar como Completa** - Altere o status de uma tarefa para completa
- ✅ **Marcar como Pendente** - Altere o status de uma tarefa para pendente
- ✅ **Deletar Tarefa** - Remova uma tarefa do sistema
- ✅ **Contadores** - Obtenha a quantidade de tarefas pendentes e completas
- ✅ **Progresso** - Calcule a porcentagem de conclusão das tarefas

## 📋 Status da Tarefa

As tarefas podem ter os seguintes status:

- **PENDING** - Tarefa pendente (status padrão)
- **IN_PROGRESS** - Tarefa em progresso
- **COMPLETED** - Tarefa concluída

## 🔧 Pré-requisitos

- **Java 25+** - Linguagem de programação
- **Gradle 8+** - Gerenciador de compilação e dependências
- **Spring Boot 4.0.6+** - Framework web

## 📥 Instalação

### 1. Clone ou acesse o repositório

```bash
cd taskmanager
```

### 2. Configure as dependências

O projeto usa Gradle com as seguintes dependências principais:

- `spring-boot-starter` - Núcleo do Spring Boot
- `spring-boot-starter-test` - Framework para testes
- `lombok` - Geração automática de getters, setters e construtores

### 3. Compile o projeto

```bash
./gradlew build
```

## 🏃 Como Usar

### Exemplo Básico

```java
// Criar uma instância do repositório
TaskRepository repository = new InMemoryTaskRepository();

// Criar uma tarefa
Task task = new Task("Título da Tarefa", Optional.of("Descrição"));
repository.save(task);

// Listar todas as tarefas
List<Task> todasTarefas = repository.findAll();

// Buscar uma tarefa por ID
Optional<Task> tarefa = repository.findById(task.getId());

// Deletar uma tarefa
repository.deleteById(task.getId());
```

## 📊 Interface do Repositório

### Operações CRUD

```java
// Salvar uma tarefa
Task savedTask = repository.save(task);

// Buscar todas as tarefas
List<Task> allTasks = repository.findAll();

// Buscar por ID
Optional<Task> task = repository.findById(taskId);

// Deletar por ID
repository.deleteById(taskId);
```

## 🧪 Testes

O projeto inclui testes automatizados para validar o funcionamento:

```bash
./gradlew test
```

### Cobertura de Testes

- **TaskRepositoryTest** - Testa operações do repositório
- **InMemoryTaskRepositoryTest** - Valida a implementação em memória
- **TaskmanagerApplicationTests** - Testa o contexto da aplicação

Os resultados dos testes estão disponíveis em:
```
build/reports/tests/test/index.html
```

## 📦 Dependências

### Build

| Dependência | Versão | Propósito |
|---|---|---|
| Spring Boot | 4.0.6 | Framework principal |
| Gradle | 9.4.0 | Gerenciador de build e Lombok |
| Java Language | 25 | Linguagem de programação |

### Test

| Dependência | Propósito |
|---|---|
| spring-boot-starter-test | Framework de testes integrado |
| junit-platform-launcher | Executor de testes JUnit |

## 📁 Estrutura de Diretórios

```
taskmanager/
├── src/
│   ├── main/
│   │   ├── java/dio/taskmanager/
│   │   │   ├── TaskmanagerApplication.java
│   │   │   ├── application/
│   │   │   ├── domain/
│   │   │   │   ├── Task.java
│   │   │   │   ├── TaskId.java
│   │   │   │   ├── TaskRepository.java
│   │   │   │   └── TaskStatus.java
│   │   │   └── insfrastructure/
│   │   │       └── repository/
│   │   │           └── InMemoryTaskRepository.java
│   │   └── resources/
│   │       └── application.yaml
│   └── test/
│       └── java/dio/taskmanager/
├── gradle/
│   └── wrapper/
├── build.gradle
├── settings.gradle
└── README.md
```

## 🔍 Detalhes da Implementação

### Task (Entidade)

Representa uma tarefa no sistema:

```java
public class Task {
    private TaskId id;              // Identificador único
    private String title;            // Título obrigatório
    private Optional<String> description; // Descrição opcional
    private TaskStatus status;       // Status padrão: PENDING
}
```

### TaskId

Identificador único utilizando UUID (record Java):

```java
public record TaskId(UUID id) { }
```

### TaskStatus

Enum com os possíveis estados de uma tarefa:

```java
public enum TaskStatus {
    PENDING,        // Tarefa pendente
    IN_PROGRESS,    // Tarefa em progresso
    COMPLETED       // Tarefa completa
}
```

### TaskRepository

Interface que define as operações CRUD:

```java
public interface TaskRepository {
    Task save(Task task);
    Optional<Task> findById(TaskId id);
    List<Task> findAll();
    void deleteById(TaskId id);
}
```

### InMemoryTaskRepository

Implementação em memória do repositório, utilizando HashMap para armazenar as tarefas.

## 🛠️ Configuração

O arquivo `application.yaml` contém as configurações básicas:

```yaml
spring:
  application:
    name: taskmanager
```

## 🤝 Contribuições

Este é um projeto educacional. Contribuições e sugestões são bem-vindas!

## 📄 Licença

Este projeto é fornecido como material educacional.

## 👨‍💻 Autor

Desenvolvido como parte do programa da [DIO - Digital Innovation One](https://www.dio.me/)

## 📞 Suporte

Para dúvidas ou problemas, consulte a documentação do projeto ou revise os exemplos de uso nos arquivo de testes.

---

**Última atualização:** 2026  
**Versão do Projeto:** 0.0.1-SNAPSHOT









