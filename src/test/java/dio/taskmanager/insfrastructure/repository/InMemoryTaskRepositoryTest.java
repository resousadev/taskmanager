package dio.taskmanager.insfrastructure.repository;

import dio.taskmanager.domain.Task;
import dio.taskmanager.domain.TaskId;
import dio.taskmanager.domain.TaskStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class InMemoryTaskRepositoryTest {

    private InMemoryTaskRepository repository;

    @BeforeEach
    void setUp() {
        repository = new InMemoryTaskRepository();
    }

    @Test
    void hsould_save_and_retrieve_task_by_id() {
        // Given
        var task = new Task( "Passar na padaria", Optional.empty());

        // When
        Task saved = repository.save(task);
        Optional<Task> retrieved = repository.findById(saved.getId());

        // Then
        assertThat(retrieved).isPresent();
        assertThat(retrieved.get().getId()).isEqualTo(task.getId());
        assertThat(retrieved.get().getDescription()).isEqualTo(task.getDescription());
        assertThat(retrieved.get().getStatus()).isEqualTo(task.getStatus());
    }

    @Test
    void should_find_all_persisted_tasks() {
        // Given
        var task1 = new Task("Passar na padaria", Optional.empty());
        var task2 = new Task("Comprar leite", Optional.empty());
        repository.save(task1);
        repository.save(task2);

        // When
        var allTasks = repository.findAll();

        // Then
        assertThat(allTasks).hasSize(2);
        assertThat(allTasks).extracting(Task::getId).containsExactlyInAnyOrder(task1.getId(), task2.getId());
    }

    @Test
    void should_delete_task_by_id() {
        // Given
        var task = repository.save(new Task( "Treinar na academia", Optional.empty()));
        var taskId = task.getId();

        // When
        repository.deleteById(task.getId());
        Optional<Task> retrieved = repository.findById(taskId);

        // Then
        assertThat(retrieved).isEmpty();
    }

    @Test
    void should_return_empty_when_searching_non_existing_task() {
        // Given
        var nonExistingId = new TaskId();

        // When
        Optional<Task> retrieved = repository.findById(nonExistingId);

        // Then
        assertThat(retrieved).isEmpty();
    }

    @Test
    void should_update_task_status_successfully() {
        // Given
        var task = repository.save(new Task( "Lavar o carro", Optional.empty()));
        var taskId = task.getId();

        task.setDescription(Optional.of("Lavar o carro no sábado"));
        task.setStatus(TaskStatus.IN_PROGRESS);

        // When
        repository.save(task);
        Optional<Task> retrieved = repository.findById(taskId);

        // Then
        assertThat(retrieved).isPresent();
        assertThat(retrieved.get().getDescription()).isEqualTo(Optional.of("Lavar o carro no sábado"));
        assertThat(retrieved.get().getStatus()).isEqualTo(TaskStatus.IN_PROGRESS);
    }
}